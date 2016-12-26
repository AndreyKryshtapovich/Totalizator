CREATE DEFINER=`root`@`localhost` PROCEDURE `close_game_coupon`(IN p_game_coupon_id INT, OUT st_result INT)
BEGIN
		DECLARE EXIT HANDLER FOR sqlexception ROLLBACK;
           SELECT -3 INTO st_result; -- rollback occured
        START TRANSACTION;
        
	-- перенос джекпота из последней рассчитанной и закрытой игры в текущую 
    SELECT MAX(game_cupon_id) INTO @last_closed_gc_id FROM game_cupon WHERE status_id = 3;
    
		IF COALESCE(@last_closed_gc_id, 0) != 0
		THEN
    
			UPDATE `totalizator`.`game_cupon`
			SET `jackpot` = ( SELECT  COALESCE(sum(win_pool_for_category), 0) AS new_jackpot
							  FROM win_per_category
							  WHERE win_bet_category_count = 0 AND game_cupon_id = @last_closed_gc_id
							)
            WHERE `game_cupon_id` = p_game_coupon_id;

		END IF;
        
        
		SELECT count(*) INTO @events_count
        FROM event as ev 
		WHERE ev.game_cupon_id = p_game_coupon_id and ev.status_id  in (3,4);
        
        IF(@events_count = 15) 
        THEN  -- if there are 15 events in statuses Closed or Canselled
			
             select  count(*) INTO @canselled_events_count 
			 from event as ev 
			 where ev.game_cupon_id = p_game_coupon_id and ev.status_id = 4;
             
             IF (@canselled_events_count >= 5) then -- cansel all game_coupon
             
				UPDATE `totalizator`.`game_cupon`
				SET
				`status_id` = 4
				WHERE `game_cupon_id` = p_game_coupon_id;
                
               --  SET st_result = -2; -- game_cupon is canselled  
               SELECT -2 INTO st_result;
			
			else 
                
				UPDATE `user_bet_detail` as ubd 
				JOIN event as ev ON ubd.event_id = ev.event_id and ev.game_cupon_id = p_game_coupon_id
				SET ubd.win_flag = CASE
				WHEN ubd.result_id = ev.result_id or ev.status_id = 4 then true
				ELSE false
				END;
                
                -- подсчет количества правильно угаданных собтий в ставке win_event_count
				UPDATE `bet` AS b
				JOIN
				(SELECT bet_id, count(*) AS win_event_count
                FROM user_bet_detail
				WHERE win_flag = 1
				GROUP BY bet_id) AS q
				ON b.bet_id = q.bet_id
				SET b.win_event_count = q.win_event_count
                WHERE b.game_cupon_id = p_game_coupon_id;
                
				-- user_bet_result population
				INSERT INTO `user_bet_result`(bet_id,win_category_type_id,win_category_amount)
				SELECT bet_id, win_category_type_id, 0 AS win_category_amount FROM (
				SELECT b.bet_id, b.win_event_count -- ev.event_id-- ev.result_id
				FROM bet b
				WHERE b.win_event_count >= 12 and b.game_cupon_id = p_game_coupon_id) AS a
				CROSS JOIN (
				SELECT win_category_type_id, min_win_event_count
				FROM `win_category_type`
				) AS b
				WHERE a.win_event_count >= b.min_win_event_count;
                
			-- подсчет пула общего и обновление game_cupon
			UPDATE `game_cupon`
			SET  `game_cupon_pull` = (
			SELECT SUM(`bet_money_amount`) FROM `bet`  
			WHERE `game_cupon_id` = p_game_coupon_id) 
			WHERE `game_cupon_id` = p_game_coupon_id;
            
		-- на каждую игру надо создавать всегда по всем category_id чтобы знать сколько пула разыгралось
		-- а сколько пошло в джек-пот
		INSERT INTO `win_per_category` (game_cupon_id,win_category_type_id,win_pool_for_category,win_bet_category_count,win_bet_category_amount)
		SELECT p_game_coupon_id, win_category_type.win_category_type_id,0,0,0
        FROM win_category_type;
        
        
	-- при суммировании всегда будет не хватать 10%, т.к. по бизнесс логике их забирают организаторы тотализатора
    -- в распроцентовке по категориям это уже учтено
   UPDATE `win_per_category` as wpc
    JOIN `game_cupon` as gc
    ON wpc.game_cupon_id = gc.game_cupon_id and gc.game_cupon_id = p_game_coupon_id
    SET `win_pool_for_category` = 
    CASE
		WHEN wpc.win_category_type_id = 1 THEN CAST(gc.game_cupon_pull as decimal(15,2)) * 0.2
		WHEN wpc.win_category_type_id = 2 THEN CAST(gc.game_cupon_pull as decimal(15,2)) * 0.15
        WHEN wpc.win_category_type_id = 3 THEN CAST(gc.game_cupon_pull as decimal(15,2)) * 0.2
		WHEN wpc.win_category_type_id = 4 THEN CAST(gc.game_cupon_pull as decimal(15,2)) * 0.35
        ELSE gc.jackpot
        -- jackpot from the previous game 
    END;
	
    -- подсчет win_per_category_count	
	UPDATE `win_per_category`AS wpc
	JOIN
	(SELECT ubr.win_category_type_id, b.game_cupon_id, count(*) AS bet_count
	FROM user_bet_result AS ubr 
	JOIN bet AS b 
	ON ubr.bet_id = b.bet_id
    WHERE b.game_cupon_id = p_game_coupon_id
	GROUP BY win_category_type_id, game_cupon_id) as q
	ON wpc.game_cupon_id = q.game_cupon_id
	SET wpc.win_bet_category_count = q.bet_count
	WHERE wpc.win_category_type_id = q.win_category_type_id AND q.game_cupon_id = p_game_coupon_id;
	
	-- подсчет win_bet_category_amount 
	UPDATE `win_per_category` AS wpc
	JOIN
	(SELECT win_category_type_id, SUM(bet_money_amount) as total_sum
	FROM user_bet_result AS ubr 
	JOIN bet AS b
	ON ubr.bet_id = b.bet_id and b.game_cupon_id = p_game_coupon_id
	GROUP BY win_category_type_id) as q
	ON wpc.win_category_type_id = q.win_category_type_id
	SET wpc.win_bet_category_amount = q.total_sum
	    WHERE wpc.game_cupon_id = p_game_coupon_id;
    
	-- подсчет win_category_amount
  UPDATE `user_bet_result`as ubr
  JOIN 
  ( 
  
  select ubr.user_bet_result_id, ubr.bet_id, b.bet_money_amount, win_pool_for_category, win_bet_category_amount
  from user_bet_result as ubr  
  join bet as b on ubr.bet_id = b.bet_id
  join win_per_category as wpc on b.game_cupon_id = wpc.game_cupon_id and ubr.win_category_type_id = wpc.win_category_type_id
  WHERE b.game_cupon_id = p_game_coupon_id
  
  ) as q
  ON ubr.user_bet_result_id = q.user_bet_result_id
  SET ubr.win_category_amount = (q.win_pool_for_category / q.win_bet_category_amount) * q.bet_money_amount;
    
	-- подсчет сколько в итоге выйграл пользователь по всем категориям win_bet_amount
	UPDATE `bet` AS b
	JOIN
	(SELECT b.bet_id, sum(ubr.win_category_amount) AS total
	FROM `bet` AS b 
	JOIN `user_bet_result` AS ubr ON b.bet_id = ubr.bet_id and b.game_cupon_id = p_game_coupon_id
	GROUP BY b.bet_id ) AS q
	ON b.bet_id = q.bet_id
	SET b.win_bet_amount = q.total;
    
    -- finally set game status as closed (id = 3)
		UPDATE `totalizator`.`game_cupon`
		SET
		`status_id` = 3
		WHERE `game_cupon_id` = p_game_coupon_id;
		 
         SELECT 1 INTO st_result;
        
          END IF;
		 ELSE 
			SELECT -1 INTO st_result; -- number of events in game_cupon < 15
		END IF;
	COMMIT;
END