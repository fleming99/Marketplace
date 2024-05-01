-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema marketplace_online
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema marketplace_online
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `marketplace_online` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `marketplace_online` ;

-- -----------------------------------------------------
-- Table `marketplace_online`.`customer_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketplace_online`.`customer_details` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `customer_first_name` VARCHAR(50) NOT NULL,
  `customer_last_name` VARCHAR(75) NOT NULL,
  `customer_cpf` VARCHAR(14) NOT NULL,
  `customer_email` VARCHAR(70) NOT NULL,
  `customer_password` VARCHAR(68) NOT NULL,
  `active_profile` TINYINT NOT NULL,
  PRIMARY KEY (`customer_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `marketplace_online`.`cart_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketplace_online`.`cart_details` (
  `cart_id` INT NOT NULL AUTO_INCREMENT,
  `order_date` DATE NOT NULL,
  `cart_amount` FLOAT(10,2) NOT NULL,
  `buyer_id` INT NOT NULL,
  PRIMARY KEY (`cart_id`),
  INDEX `fk_cart_details_customer_id_idx` (`buyer_id` ASC) VISIBLE,
  CONSTRAINT `fk_cart_details_customer_id`
    FOREIGN KEY (`buyer_id`)
    REFERENCES `marketplace_online`.`customer_details` (`customer_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `marketplace_online`.`company_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketplace_online`.`company_details` (
  `company_id` INT NOT NULL AUTO_INCREMENT,
  `company_name` VARCHAR(70) NOT NULL,
  `trading_name` VARCHAR(70) NOT NULL,
  `company_email` VARCHAR(70) NOT NULL,
  `company_cnpj` VARCHAR(18) NOT NULL,
  PRIMARY KEY (`company_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `marketplace_online`.`product_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketplace_online`.`product_category` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(45) NOT NULL,
  `category_description` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `marketplace_online`.`product_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketplace_online`.`product_details` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(50) NOT NULL,
  `product_description` VARCHAR(1000) NOT NULL,
  `product_quantity` INT NOT NULL,
  `product_price` FLOAT(5,2) NOT NULL,
  `product_status` TINYINT NOT NULL,
  `category_id` INT NOT NULL,
  `seller_id` INT NOT NULL,
  PRIMARY KEY (`product_id`),
  INDEX `product_id_fk_idx` (`category_id` ASC) VISIBLE,
  INDEX `fk_product_details_company_details1_idx` (`seller_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_details_company_details1`
    FOREIGN KEY (`seller_id`)
    REFERENCES `marketplace_online`.`company_details` (`company_id`),
  CONSTRAINT `product_id_foreign_key`
    FOREIGN KEY (`category_id`)
    REFERENCES `marketplace_online`.`product_category` (`category_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `marketplace_online`.`cart_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketplace_online`.`cart_items` (
  `cart_item_id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `item_quantity` INT NOT NULL,
  `unit_price` FLOAT(10,2) NOT NULL,
  `subtotal` FLOAT(10,2) NOT NULL,
  `cart_id` INT NOT NULL,
  PRIMARY KEY (`cart_item_id`),
  INDEX `product_id_fk_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_cart_items_cart_details1_idx` (`cart_id` ASC) VISIBLE,
  CONSTRAINT `fk_cart_items_cart_details1`
    FOREIGN KEY (`cart_id`)
    REFERENCES `marketplace_online`.`cart_details` (`cart_id`),
  CONSTRAINT `product_id_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `marketplace_online`.`product_details` (`product_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `marketplace_online`.`entity_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketplace_online`.`entity_address` (
  `entity_address_id` INT NOT NULL AUTO_INCREMENT,
  `entity_address` VARCHAR(70) NOT NULL,
  `entity_address_number` VARCHAR(45) NOT NULL,
  `entity_address_neighborhood` VARCHAR(50) NOT NULL,
  `entity_address_city` VARCHAR(50) NOT NULL,
  `entity_address_state` VARCHAR(50) NOT NULL,
  `entity_address_country` VARCHAR(50) NOT NULL,
  `customer_id` INT NULL DEFAULT NULL,
  `company_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`entity_address_id`),
  INDEX `fk_entity_address_customer_details1_idx` (`customer_id` ASC) VISIBLE,
  INDEX `fk_entity_address_company_details1_idx` (`company_id` ASC) VISIBLE,
  CONSTRAINT `fk_entity_address_company_details1`
    FOREIGN KEY (`company_id`)
    REFERENCES `marketplace_online`.`company_details` (`company_id`),
  CONSTRAINT `fk_entity_address_customer_details1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `marketplace_online`.`customer_details` (`customer_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `marketplace_online`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketplace_online`.`role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `marketplace_online`.`users_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketplace_online`.`users_role` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  INDEX `users_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `role_id_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `marketplace_online`.`role` (`role_id`),
  CONSTRAINT `users_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `marketplace_online`.`customer_details` (`customer_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
