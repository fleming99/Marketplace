CREATE DATABASE  IF NOT EXISTS `marketplace_online` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `marketplace_online`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: marketplace_online
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart_details`
--

DROP TABLE IF EXISTS `cart_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_details` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `buyer_id` int NOT NULL,
  `seller_id` int NOT NULL,
  `order_date` date NOT NULL,
  `cart_amount` float(10,2) NOT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `person_id_idx` (`buyer_id`),
  KEY `seller_id_idx` (`seller_id`),
  CONSTRAINT `buyer_id` FOREIGN KEY (`buyer_id`) REFERENCES `customer_details` (`customer_id`),
  CONSTRAINT `seller_id` FOREIGN KEY (`seller_id`) REFERENCES `company_details` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_details`
--

LOCK TABLES `cart_details` WRITE;
/*!40000 ALTER TABLE `cart_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `cart_item_id` int NOT NULL AUTO_INCREMENT,
  `cart_id` int NOT NULL,
  `product_id` int NOT NULL,
  `item_quantity` int NOT NULL,
  `unit_price` float(10,2) NOT NULL,
  `subtotal` float(10,2) NOT NULL,
  PRIMARY KEY (`cart_item_id`),
  KEY `carrinho_id_idx` (`cart_id`),
  KEY `product_id_fk_idx` (`product_id`),
  CONSTRAINT `product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `product_details` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_details`
--

DROP TABLE IF EXISTS `company_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_details` (
  `company_id` int NOT NULL AUTO_INCREMENT,
  `company_name` varchar(70) NOT NULL,
  `trading_name` varchar(70) NOT NULL,
  `company_email` varchar(70) NOT NULL,
  `company_cnpj` varchar(18) NOT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_details`
--

LOCK TABLES `company_details` WRITE;
/*!40000 ALTER TABLE `company_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_details`
--

DROP TABLE IF EXISTS `customer_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_details` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `customer_first_name` varchar(50) NOT NULL,
  `customer_last_name` varchar(75) NOT NULL,
  `customer_email` varchar(70) NOT NULL,
  `customer_cpf` varchar(14) NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_details`
--

LOCK TABLES `customer_details` WRITE;
/*!40000 ALTER TABLE `customer_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entity_address`
--

DROP TABLE IF EXISTS `entity_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entity_address` (
  `entity_address_id` int NOT NULL AUTO_INCREMENT,
  `entity_address` varchar(70) NOT NULL,
  `entity_address_number` varchar(45) NOT NULL,
  `entity_address_neighborhood` varchar(50) NOT NULL,
  `entity_address_city` varchar(50) NOT NULL,
  `entity_address_state` varchar(50) NOT NULL,
  `entity_address_country` varchar(50) NOT NULL,
  `customer_id` int DEFAULT NULL,
  `company_id` int DEFAULT NULL,
  PRIMARY KEY (`entity_address_id`),
  KEY `personId_idx` (`customer_id`),
  KEY `company_id_idx` (`company_id`),
  CONSTRAINT `company_id` FOREIGN KEY (`company_id`) REFERENCES `company_details` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entity_address`
--

LOCK TABLES `entity_address` WRITE;
/*!40000 ALTER TABLE `entity_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `entity_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) NOT NULL,
  `category_description` varchar(100) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,'Electronics','Seccion for electronics products.'),(2,'Computers','Seccion for the computers and laptop\'s.'),(3,'Smart Home','Seccion for smart home products.'),(4,'Automotive','Seccion for automotive products.'),(5,'Clothes','Seccion for clothes for all genders.'),(6,'Home and Kitchen','Seccion for home and kitchen products.'),(7,'Beauty and Personal Care','Seccion for beauty and personal care products.');
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_details`
--

DROP TABLE IF EXISTS `product_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_details` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) NOT NULL,
  `product_description` varchar(1000) NOT NULL,
  `product_quantity` int DEFAULT NULL,
  `product_price` float(5,2) DEFAULT NULL,
  `product_status` tinyint NOT NULL,
  `category_id` int NOT NULL,
  `seller_id` int DEFAULT NULL,
  `company_id` int DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `seller_id_idx` (`seller_id`),
  KEY `company_id_idx` (`company_id`),
  KEY `product_id_fk_idx` (`category_id`),
  CONSTRAINT `company_id_fk` FOREIGN KEY (`company_id`) REFERENCES `company_details` (`company_id`),
  CONSTRAINT `product_id_foreign_key` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`category_id`),
  CONSTRAINT `seller_id_fk` FOREIGN KEY (`seller_id`) REFERENCES `customer_details` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_details`
--

LOCK TABLES `product_details` WRITE;
/*!40000 ALTER TABLE `product_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Drop user first if they exist

DROP USER IF EXISTS 'marketplaceuser'@'localhost';
CREATE USER 'marketplaceuser'@'localhost' IDENTIFIED BY 'marketplaceuser';
GRANT ALL PRIVILEGES ON *.* TO 'marketplaceuser'@'localhost' WITH GRANT OPTION;

-- Dump completed on 2024-04-20 21:48:34
