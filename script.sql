-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ProjetoJTicket
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ProjetoJTicket
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ProjetoJTicket` DEFAULT CHARACTER SET latin1 ;
USE `ProjetoJTicket` ;

-- -----------------------------------------------------
-- Table `ProjetoJTicket`.`profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProjetoJTicket`.`profile` (
  `idProfile` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `modifiedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idProfile`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProjetoJTicket`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProjetoJTicket`.`user` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `idProfile` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `slug` VARCHAR(60),
  `modifiedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idUser`, `idProfile`),
  INDEX `fk_user_profile1` (`idProfile` ASC) VISIBLE,
  CONSTRAINT `fk_user_profile1`
    FOREIGN KEY (`idProfile`)
    REFERENCES `ProjetoJTicket`.`profile` (`idProfile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProjetoJTicket`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProjetoJTicket`.`status` (
  `idStatus` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `modifiedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idStatus`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProjetoJTicket`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProjetoJTicket`.`ticket` (
  `idTicket` INT NOT NULL AUTO_INCREMENT,
  `idUser` INT NOT NULL,
  `idStatus` INT NOT NULL,
  `title` VARCHAR(90) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `slug` VARCHAR(60),
  `createdDate` DATETIME  NOT NULL,
  `modifiedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (`idTicket`, `idUser`, `idStatus`),
  INDEX `fk_ticket_status` (`idStatus` ASC) VISIBLE,
  INDEX `fk_ticket_user1` (`idUser` ASC) VISIBLE,
  CONSTRAINT `fk_ticket_status`
    FOREIGN KEY (`idStatus`)
    REFERENCES `ProjetoJTicket`.`status` (`idStatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_user1`
    FOREIGN KEY (`idUser`)
    REFERENCES `ProjetoJTicket`.`user` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
