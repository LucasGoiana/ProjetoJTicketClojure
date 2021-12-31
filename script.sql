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
-- Table `ProjetoJTicket`.`perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProjetoJTicket`.`perfil` (
  `idPerfil` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `DataModifcacao` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idPerfil`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProjetoJTicket`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProjetoJTicket`.`usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `idPerfil` INT NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Senha` VARCHAR(45) NOT NULL,
  `Slug` VARCHAR(60) NOT NULL,
  `DataModficacao` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idUsuario`, `idPerfil`),
  INDEX `fk_usuario_perfil1` (`idPerfil` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_perfil1`
    FOREIGN KEY (`idPerfil`)
    REFERENCES `ProjetoJTicket`.`perfil` (`idPerfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProjetoJTicket`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProjetoJTicket`.`status` (
  `idStatus` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `DataModficacao` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idStatus`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProjetoJTicket`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProjetoJTicket`.`ticket` (
  `idTicket` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `idStatus` INT NOT NULL,
  `Titulo` VARCHAR(90) NOT NULL,
  `Descricao` VARCHAR(255) NOT NULL,
  `Slug` VARCHAR(60) NOT NULL,
  `DataCriacao` DATETIME NOT NULL,
  `DataModificacao` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idTicket`, `idUsuario`, `idStatus`),
  INDEX `fk_ticket_status` (`idStatus` ASC) VISIBLE,
  INDEX `fk_ticket_usuario1` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_ticket_status`
    FOREIGN KEY (`idStatus`)
    REFERENCES `ProjetoJTicket`.`status` (`idStatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `ProjetoJTicket`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
