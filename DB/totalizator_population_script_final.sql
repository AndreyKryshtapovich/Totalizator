USE totalizator;
 -- result population
 INSERT INTO `result` (result_abbreviation,result_note)
 VALUES
 ('1','First team win'),
 ('2','Second team win'),
 ('X','Draw'),
 ('4','Unknown') -- added
 ;
    
    
 -- status population
 INSERT INTO `status` (status_description)
 VALUES
 ('Opened'),
 ('In progress'),
 ('Closed'),
 ('Canselled'),
 ('In developing')-- added
 ;
 
    
   -- user population
   INSERT INTO `user` (first_name,last_name,login,password,sex,e_mail,country,city,address,role)
   VALUES
   ('John','Brown','johnB','827ccb0eea8a706c4c34a16891f84e7b','M','john@gmail.com','England','London','45 Maydwell House','user'), -- changed password 12345
   ('Ron','Grey','ronG','e10adc3949ba59abbe56e057f20f883e','M','ron@gmail.com','USA','NY',' 9 East Street','user'), -- changed password 123456
   ('Albert','Cao','Calbert','fcea920f7412b5da7be0cf42b8c93759','M','albert@gmail.com','USA', null,'12 East Street','user'), -- changed password 1234567
   ('Admin','Brown','adminB','c6f057b86584942e415435ffb1fa93d4','M','admin@gmail.com','England','London','43 Maydwell House','admin')-- added -- changed password 000
   ;
   
	
    -- game_cupon population
    INSERT INTO `game_cupon`(start_date,end_date,min_bet_amount,game_cupon_pull,jackpot,status_id)
	VALUES
	('2016-10-10','2016-10-14',30,0,0,1),
	('2016-12-03 14:14:00','2016-12-03 15:15:00',15,0,0,5), -- added
	('2016-11-21 00:00:00','2016-11-25 23:59:00',100,0,0,5), -- added
    
    ('2017-01-30 00:00:00','2017-02-03 23:59:00',50,0,0,5) -- added
	;
    
        
	-- bet population
	INSERT INTO `bet` (user_id,game_cupon_id,credit_card_number,bet_money_amount,transaction_date,win_event_count,win_bet_amount)
	VALUES
	(1,1,'1234567890123456',100,CURRENT_TIMESTAMP(),null,null),
    (2,1,'4567342367893476',200,CURRENT_TIMESTAMP(),null,null),
    (1,1,'1234567890123456',300,CURRENT_TIMESTAMP(),null,null);
    
 INSERT INTO `event`(event_name,game_cupon_id,team_one,team_two,result_id,start_date,end_date,status_id)
    VALUES
    ('Elche - Alcorcon football match',1,'Elche','Alcorcon',1,'2016-10-15 20:11:07','2016-10-15 21:41:07',3),
    ('Metropolitan Police - Worthing football match',1,'Metropolitan Police','Worthing',2,'2016-10-15 10:11:07','2016-10-15 12:41:07',3),
	('Weymouth - Basingstoke football match',1,'Weymouth','Basingstoke',3,'2016-10-15 10:11:07','2016-10-15 12:41:07',3),
    ('Ilkeston - Barwell football match',1,'Ilkeston','Barwell',1,'2016-10-15 08:00:00','2016-10-15 09:30:00',3),
    ('Manchester United - Real Madrid football match',1,'Manchester United','Real Madrid',2,'2016-10-15 09:00:00','2016-10-15 10:30:00',3),
	('FC Barcelona - BATE football match',1,'FC Barcelona','BATE',2,'2016-10-15 07:00:00','2016-10-15 08:30:00',3),
    ('Chelsea - Dinamo Minsk football match',1,'Chelsea','Dinamo Minsk',1,'2016-10-16 07:00:00','2016-10-16 08:30:00',3),
    ('Arsenal - Lokomotiv football match',1,'Arsenal','Lokomotiv',3,'2016-10-16 11:00:00','2016-10-16 12:30:00',3),
    ('Liverpool - AC Milan football match',1,'Liverpool','AC Milan',1,'2016-10-16 07:00:00','2016-10-16 12:30:00',3),
    ('Juventus - Dinamo Kiev football match',1,'Juventus','Dinamo Kiev',1,'2016-10-16 07:00:00','2016-10-16 12:30:00',3),
	('Celta Vigo - Espanyol football match',1,'Celta Vigo','Espanyol',2,'2016-10-16 18:00:00','2016-10-16 19:30:00',3),
    ('Sevilla - Valencia football match',1,'Sevilla','Valencia',3,'2016-10-16 18:00:00','2016-10-16 19:30:00',3),
    ('Las Palmas - Granada football match',1,'Las Palmas','Granada',1,'2016-10-16 19:00:00','2016-10-16 20:30:00',3),
    ('Osasuna - Real Betis football match',1,'Osasuna','Real Betis',1,'2016-10-16 19:00:00','2016-10-16 20:30:00',3),	
    ('Eibar - Tenerife football match',1,'Eibar','Tenerife',3,'2016-10-16 19:30:00','2016-10-16 21:00:00',3),
	('Lokomotiv - Traktor',3,'Lokomotiv','Traktor',3,'2016-11-26 13:30:00','2016-11-26 15:00:00',3), -- added
	('Stroitel-Shahter',3,'Stroitel','Shahter',1,'2016-11-27 15:30:00','2016-11-27 17:00:00',3), -- added
	('Lokomotiv-Shahter_yeees',3,'Lokomotiv','Shahter',1,'2016-11-26 13:00:00','2016-11-26 14:30:00',3), -- added
	('Ludogorets-Razgrad football match',3,'Ludogorets','Razgrad',1,'2016-11-26 20:11:07','2016-11-26 21:41:07',3), -- added
	('Levski-Sofia football match',3,'Levski','Sofia',2,'2016-11-26 10:11:07','2016-11-26 12:41:07',3), -- added
	('Cherno-More football match',3,'Cherno','More',3,'2016-11-27 10:11:07','2016-11-27 12:41:07',3), -- added
	('Botev-Plovdiv football match',3,'Botev','Plovdiv',2,'2016-11-27 08:00:00','2016-11-27 09:30:00',3), -- added
	('Slavia-Sofia football match',3,'Slavia','Sofia',2,'2016-11-27 09:00:00','2016-11-27 10:30:00',3), -- added
	('Waasland-Beveren football match',3,'Waasland','Beveren',2,'2016-11-27 07:00:00','2016-11-27 08:30:00',3), -- added
	('Mouscron-Peruwelz football match',3,'Mouscron','Peruwelz',1,'2016-11-26 07:00:00','2016-11-26 08:30:00',3), -- added
	('Arsenal - Lokomotiv football match',3,'Arsenal','Lokomotiv',1,'2016-11-26 11:00:00','2016-11-26 12:30:00',3), -- added
	('Liverpool - AC Milan football match',3,'Liverpool','AC Milan',1,'2016-11-26 08:05:00','2016-11-26 12:30:00',3), -- added
	('Juventus - Dinamo Kiev football match',3,'Juventus','Dinamo Kiev',1,'2016-11-27 07:00:00','2016-11-27 12:30:00',3), -- added
	('Celta Vigo - Espanyol football match',3,'Celta Vigo','Espanyol',1,'2016-11-27 18:00:00','2016-11-27 19:30:00',3), -- added
	('Sevilla - Valencia football match',3,'Sevilla','Valencia',1,'2016-11-27 18:00:00','2016-11-27 19:30:00',3), -- added
    
	('Tunisia - Senegal football match',4,'Tunisia','Senegal',4,'2017-02-04 13:30:00','2017-02-04 15:00:00',1), -- added
	('El Salvador - Honduras football match',4,'El Salvador','Honduras',4,'2017-02-04 15:30:00','2017-02-04 17:00:00',1), -- added
	('Real Sociedad Honduras - Olimpia CD football match',4,'Real Sociedad Honduras','Olimpia CD',1,'2017-02-04 13:00:00','2017-02-04 14:30:00',1), -- added
	('CDS Vida - Real Espana football match',4,'CDS Vida','Real Espana',4,'2017-02-04 20:11:00','2017-02-04 21:41:00',1), -- added
	('Motagua - CD Honduras football match',4,'Motagua','CD Honduras',4,'2017-02-04 10:11:07','2017-02-04 12:41:07',1), -- added
	('Sevilla - Real Madrid football match',4,'Sevilla','Real Madrid',4,'2017-02-04 10:11:07','2017-02-04 12:41:07',1), -- added
	('Toluca - Club America football match',4,'Toluca','Club America',4,'2017-02-05 08:00:00','2017-02-05 09:30:00',1), -- added
	('Santa Gema - SD Atletico Nacional football match',4,'Santa Gema','SD Atletico Nacional',4,'2017-02-05 09:00:00','2017-02-05 10:30:00',1), -- added
	('Arabe Unido - San Francisco football match',4,'Arabe Unido','San Francisco',4,'2017-02-05 07:00:00','2017-02-05 08:30:00',1), -- added
	('Belenenses - Rio Ave football match',4,'Belenenses','Rio Ave',4,'2017-02-05 07:00:00','2017-02-05 08:30:00',1), -- added
	('Chalatenango - AD Isidro Metapan football match',4,'Chalatenango','AD Isidro Metapan',4,'2017-02-05 11:00:00','2017-02-05 12:30:00',1), -- added
	('Dragon San Miguel - Municipal Limeno football match',4,'Dragon San Miguel','Municipal Limeno',4,'2017-02-05 08:05:00','2017-02-05 12:30:00',1), -- added
	('Marseilles - Monaco football match',4,'Marseilles','Monaco',4,'2017-02-05 07:00:00','2017-02-05 12:30:00',1), -- added
	('Magallanes - Union San Felipe football match',4,'Magallanes','Union San Felipe',4,'2017-02-05 18:00:00','2017-02-05 19:30:00',1), -- added
	('Sevilla - Valencia football match',4,'Sevilla','Valencia',4,'2017-02-05 18:00:00','2017-02-05 19:30:00',1) -- added
	;	
    -- user_bet_detail population
    INSERT INTO `user_bet_detail` (bet_id,event_id,result_id,win_flag)
    VALUES
    (1,1,1,null),
    (1,2,1,null),
    (1,3,1,null),
	(1,4,3,null),
    (1,5,2,null),
    (1,6,2,null),
    (1,7,3,null),
    (1,8,1,null),
    (1,9,3,null),
    (1,10,1,null),
    (1,11,2,null),
    (1,12,3,null),
    (1,13,1,null),
    (1,14,1,null),
    (1,15,3,null),
    (2,1,1,null),
    (2,2,2,null),
    (2,3,3,null),
    (2,4,1,null),
    (2,5,2,null),
    (2,6,2,null),
    (2,7,1,null),
    (2,8,3,null),
    (2,9,1,null),
    (2,10,1,null),
    (2,11,2,null),
    (2,12,3,null),
    (2,13,3,null),
    (2,14,3,null),
    (2,15,2,null),
	(3,1,1,null),
    (3,2,2,null),
    (3,3,3,null),
	(3,4,1,null),
    (3,5,2,null),
    (3,6,2,null),
    (3,7,1,null),
    (3,8,3,null),
    (3,9,1,null),
    (3,10,1,null),
    (3,11,2,null),
    (3,12,3,null),
    (3,13,2,null),
    (3,14,2,null),
    (3,15,3,null);
   
      -- win_category_type population
    INSERT INTO `win_category_type`(win_category_description,min_win_event_count)
    VALUES
    ('20% от пула; в распределении этой части призового фонда участвуют ставки, в которых угаданы результаты 15 событий',15),
    ('15% от пула; в распределении участвуют все ставки, где угаданы результаты 15 или 14 событий',14),
    ('20% от пула; в распределении участвуют все ставки, где угаданы результаты 15, 14 или 13 событий',13),
    ('35% от пула; в распределении участвуют все ставки, где угаданы результаты 15, 14, 13 или 12 событий',12),
    ('джекпот; в распределении участвуют все ставки, где угаданы результаты 15 событий',15);