SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `java2` DEFAULT CHARACTER SET utf8 ;
USE `java2` ;

-- -----------------------------------------------------
-- Table `Java2_test`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `users` ;

CREATE TABLE IF NOT EXISTS `users` (
  `UserID` INT(11) NOT NULL AUTO_INCREMENT,
  `FirstName` CHAR(32) NOT NULL,
  `LastName` CHAR(32) NOT NULL,
  PRIMARY KEY (`UserID`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;




ALTER TABLE `users` ADD `usename` VARCHAR(255) NOT NULL FIRST, ADD `password` VARCHAR(255) NOT NULL AFTER `usename`;
ALTER TABLE `users` ADD `date_of_birth` DATE NOT NULL ,
                    ADD `city` VARCHAR(255) NOT NULL ,
                    ADD `country` VARCHAR(255) NOT NULL ,
                    ADD `sex` SET('M','F') NOT NULL DEFAULT 'M' ,
                    ADD `looking_for` SET('M','F') NOT NULL DEFAULT 'F' ,
                    ADD `age_from` SMALLINT NOT NULL ,
                    ADD `age_to` SMALLINT NOT NULL ,
                    ADD `about` TEXT NOT NULL ;


INSERT INTO `java2app`.`users`
  (`usename`, `password`, `UserID`, `FirstName`, `LastName`, `date_of_birth`, `city`, `country`, `sex`, `looking_for`, `age_from`, `age_to`, `about`)
  VALUES
  ('user1', 'user1pass', NULL, 'User1FirstName', 'User1LastName', '1998-10-04', 'Riga', 'Latvia', 'M', 'F', '18', '30', 'Very cool about text!');