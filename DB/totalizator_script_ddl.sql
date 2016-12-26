-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema totalizator
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema totalizator
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `totalizator` DEFAULT CHARACTER SET utf8 ;
USE `totalizator` ;

-- -----------------------------------------------------
-- Table `totalizator`.`status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `totalizator`.`status` ;

CREATE TABLE IF NOT EXISTS `totalizator`.`status` (
  `status_id` TINYINT(3) UNSIGNED NOT NULL AUTO_INCREMENT,
  `status_description` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`status_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `totalizator`.`game_cupon`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `totalizator`.`game_cupon` ;

CREATE TABLE IF NOT EXISTS `totalizator`.`game_cupon` (
  `game_cupon_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `start_date` DATETIME NOT NULL,
  `end_date` DATETIME NOT NULL,
  `min_bet_amount` MEDIUMINT(8) UNSIGNED NOT NULL COMMENT 'Минимальная сумма ставки ',
  `game_cupon_pull` INT(10) UNSIGNED NOT NULL COMMENT 'Сумма игрового пула',
  `jackpot` INT(10) UNSIGNED NOT NULL COMMENT 'Сумма джекпота',
  `status_id` TINYINT(3) UNSIGNED NOT NULL,
  PRIMARY KEY (`game_cupon_id`),
  CONSTRAINT `fk_game_cupon_status`
    FOREIGN KEY (`status_id`)
    REFERENCES `totalizator`.`status` (`status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Таблица, отображающая одну игру тотализатора. В рамках одной игры пользователи делают ставки на 15 событий.';

CREATE INDEX `fk_game_cupon_status_idx` ON `totalizator`.`game_cupon` (`status_id` ASC);


-- -----------------------------------------------------
-- Table `totalizator`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `totalizator`.`user` ;

CREATE TABLE IF NOT EXISTS `totalizator`.`user` (
  `user_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(90) NOT NULL,
  `login` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `sex` ENUM('M', 'F', 'Unknown') NOT NULL,
  `e_mail` VARCHAR(50) NOT NULL,
  `country` VARCHAR(50) NOT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(80) NULL DEFAULT NULL,
  `role` ENUM('user', 'admin') NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Таблица, отображающая аккаунт пользователя или админа, что отражено в поле role. Возможности пользователя и админа будут разграничены на уровне приложения.';

CREATE UNIQUE INDEX `login_UNIQUE` ON `totalizator`.`user` (`login` ASC);

CREATE INDEX `i_login_password` ON `totalizator`.`user` (`login` ASC, `password` ASC)  COMMENT 'К этой таблице часто будут обращаться с помошью операции SELECT у которой в опции WHERE будет происходить поиск по логину и паролю при аутентификации пользователя.';


-- -----------------------------------------------------
-- Table `totalizator`.`bet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `totalizator`.`bet` ;

CREATE TABLE IF NOT EXISTS `totalizator`.`bet` (
  `bet_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) UNSIGNED NOT NULL,
  `game_cupon_id` INT(10) UNSIGNED NOT NULL,
  `credit_card_number` VARCHAR(50) NOT NULL COMMENT 'Номер кредитной карты пользователя, необходимо хранить для осуществления операций через банк по переводу денежных средств.',  -- changed was CHAR(16)
  `bet_money_amount` INT(10) UNSIGNED NOT NULL COMMENT 'Сумма ставки пользователя',
  `transaction_date` DATETIME NOT NULL,
  `win_event_count` TINYINT(3) UNSIGNED NULL DEFAULT NULL COMMENT 'Кол-во событий, которые пользоватль угадал правильно.',
  `win_bet_amount` INT(10) UNSIGNED NULL DEFAULT NULL COMMENT 'Сумма выйгрыша пользователя по ставке',
  PRIMARY KEY (`bet_id`),
  CONSTRAINT `fk_bet_game_cupon`
    FOREIGN KEY (`game_cupon_id`)
    REFERENCES `totalizator`.`game_cupon` (`game_cupon_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bet_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `totalizator`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Таблица, отображающая ставку, сделанную пользователем.';

CREATE INDEX `fk_bet_user_idx` ON `totalizator`.`bet` (`user_id` ASC);

CREATE INDEX `fk_bet_game_cupon_idx` ON `totalizator`.`bet` (`game_cupon_id` ASC);


-- -----------------------------------------------------
-- Table `totalizator`.`result`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `totalizator`.`result` ;

CREATE TABLE IF NOT EXISTS `totalizator`.`result` (
  `result_id` TINYINT(3) UNSIGNED NOT NULL AUTO_INCREMENT,
  `result_abbreviation` CHAR(1) NOT NULL COMMENT 'Аббревиатура, обозначающая определенный результат',
  `result_note` VARCHAR(300) NOT NULL COMMENT 'Описание соответствующего результата.\nНапример: выйгрыш первой команды.',
  PRIMARY KEY (`result_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Таблица, хранящая возможные результаты, которые могут принимать события ';


-- -----------------------------------------------------
-- Table `totalizator`.`event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `totalizator`.`event` ;

CREATE TABLE IF NOT EXISTS `totalizator`.`event` (
  `event_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `event_name` VARCHAR(200) NOT NULL,
  `game_cupon_id` INT(11) UNSIGNED NULL DEFAULT NULL,
  `team_one` VARCHAR(300) NOT NULL,
  `team_two` VARCHAR(300) NOT NULL,
  `result_id` TINYINT(3) UNSIGNED , -- changed prev NOT NULL
  `start_date` DATETIME NOT NULL,
  `end_date` DATETIME NOT NULL,
  `status_id` TINYINT(4) UNSIGNED NULL DEFAULT NULL COMMENT 'Статус события. Например: отменено.',
  PRIMARY KEY (`event_id`),
  CONSTRAINT `fk_event_gamecupon`
    FOREIGN KEY (`game_cupon_id`)
    REFERENCES `totalizator`.`game_cupon` (`game_cupon_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_result`
    FOREIGN KEY (`result_id`)
    REFERENCES `totalizator`.`result` (`result_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_status`
    FOREIGN KEY (`status_id`)
    REFERENCES `totalizator`.`status` (`status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Таблица, отображающая события.\nКаждой записи соответствует одно событие, например: футбольный матч.';

CREATE INDEX `fk_event_result_idx` ON `totalizator`.`event` (`result_id` ASC);

CREATE INDEX `fk_event_gamecupon_idx` ON `totalizator`.`event` (`game_cupon_id` ASC);

CREATE INDEX `fk_event_status_idx` ON `totalizator`.`event` (`status_id` ASC);


-- -----------------------------------------------------
-- Table `totalizator`.`user_bet_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `totalizator`.`user_bet_detail` ;

CREATE TABLE IF NOT EXISTS `totalizator`.`user_bet_detail` (
  `user_bet_detail_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `bet_id` INT(10) UNSIGNED NOT NULL,
  `event_id` INT(10) UNSIGNED NOT NULL,
  `result_id` TINYINT(4) UNSIGNED NOT NULL COMMENT 'Результат события, по мнению пользователя',
  `win_flag` TINYINT(4) NULL DEFAULT NULL COMMENT 'Индикатор того правильно или неправильно было угадано событие',
  PRIMARY KEY (`user_bet_detail_id`),
  CONSTRAINT `fk_betdetail_bet`
    FOREIGN KEY (`bet_id`)
    REFERENCES `totalizator`.`bet` (`bet_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_betdetail_event`
    FOREIGN KEY (`event_id`)
    REFERENCES `totalizator`.`event` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_betdetail_result`
    FOREIGN KEY (`result_id`)
    REFERENCES `totalizator`.`result` (`result_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Таблица, отображающая детали ставки пользователя.На какие именно результаты событий пользователь делает ставку.';

CREATE UNIQUE INDEX `uq_betid_eventid` ON `totalizator`.`user_bet_detail` (`bet_id` ASC, `event_id` ASC);

CREATE INDEX `fk_betdetail_bet_idx` ON `totalizator`.`user_bet_detail` (`bet_id` ASC);

CREATE INDEX `fk_betdetail_event_idx` ON `totalizator`.`user_bet_detail` (`event_id` ASC);

CREATE INDEX `fk_betdetail_result_idx` ON `totalizator`.`user_bet_detail` (`result_id` ASC);


-- -----------------------------------------------------
-- Table `totalizator`.`win_category_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `totalizator`.`win_category_type` ;

CREATE TABLE IF NOT EXISTS `totalizator`.`win_category_type` (
  `win_category_type_id` TINYINT(3) UNSIGNED NOT NULL AUTO_INCREMENT,
  `win_category_description` VARCHAR(300) NOT NULL COMMENT 'Описание соответствующей категории.',
  `min_win_event_count` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`win_category_type_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Таблица, отображающая возможные категории выйгрыша.\nНапример : ставки, в которых угаданы все 15 событий участвуют в розыгрыше первой категории(20% от пула).';


-- -----------------------------------------------------
-- Table `totalizator`.`user_bet_result`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `totalizator`.`user_bet_result` ;

CREATE TABLE IF NOT EXISTS `totalizator`.`user_bet_result` (
  `user_bet_result_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `bet_id` INT(10) UNSIGNED NOT NULL,
  `win_category_type_id` TINYINT(3) UNSIGNED NOT NULL COMMENT 'К какой категории относится выйгрыш.',
  `win_category_amount` INT(10) UNSIGNED NOT NULL COMMENT 'Сумма выйгрыша ставки по данной категории.',
  PRIMARY KEY (`user_bet_result_id`),
  CONSTRAINT `fk_betresult_bet`
    FOREIGN KEY (`bet_id`)
    REFERENCES `totalizator`.`bet` (`bet_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_betresult_wincategorytype`
    FOREIGN KEY (`win_category_type_id`)
    REFERENCES `totalizator`.`win_category_type` (`win_category_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Таблица, отображающая результат ставки пользователя.';

CREATE UNIQUE INDEX `bet_id_win_category_type_id_UNIQUE` ON `totalizator`.`user_bet_result` (`bet_id` ASC,`win_category_type_id` ASC);

CREATE INDEX `fk_betresult_bet_idx` ON `totalizator`.`user_bet_result` (`bet_id` ASC);

CREATE INDEX `fk_betresult_wincategorytype_idx` ON `totalizator`.`user_bet_result` (`win_category_type_id` ASC);


-- -----------------------------------------------------
-- Table `totalizator`.`win_per_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `totalizator`.`win_per_category` ;

CREATE TABLE IF NOT EXISTS `totalizator`.`win_per_category` (
  `win_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `game_cupon_id` INT(10) UNSIGNED NOT NULL,
  `win_category_type_id` TINYINT(3) UNSIGNED NOT NULL,
  `win_pool_for_category` DECIMAL(15,2) UNSIGNED NOT NULL,
  `win_bet_category_count` INT(10) UNSIGNED NOT NULL COMMENT 'Количество ставок, которые выйграли',
  `win_bet_category_amount` INT(10) UNSIGNED NOT NULL COMMENT 'Сумма денежных средств всех выйгрышных ставок',
  PRIMARY KEY (`win_id`),
  CONSTRAINT `fk_winbetpercategory_gamecupon`
    FOREIGN KEY (`game_cupon_id`)
    REFERENCES `totalizator`.`game_cupon` (`game_cupon_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_winbetpercategory_wincategorytype`
    FOREIGN KEY (`win_category_type_id`)
    REFERENCES `totalizator`.`win_category_type` (`win_category_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Таблица, в которой хранится сумма пула, разыгрываемая в каждой выйгрышной категории, а так же сумма всех выйгрышных ставок и их количесиво. Предназначена для статистики, а так же для того, чтобы не вычислять эти данные каждый раз в приложении';

CREATE UNIQUE INDEX `uq_win_per_category` ON `totalizator`.`win_per_category` (`game_cupon_id` ASC, `win_category_type_id` ASC);

CREATE INDEX `fk_winbetpercategory_gamecupon_idx` ON `totalizator`.`win_per_category` (`game_cupon_id` ASC);

CREATE INDEX `fk_winbetpercategory_wincategorytype_idx` ON `totalizator`.`win_per_category` (`win_category_type_id` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET SQL_SAFE_UPDATES = 0;
