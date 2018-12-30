-- MySQL dump 10.13  Distrib 8.0.13, for Linux (x86_64)
--
-- Host: localhost    Database: ihotel
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dinnerTable`
--

DROP TABLE IF EXISTS `dinnerTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dinnerTable` (
  `id` int(11) NOT NULL,
  `tableName` varchar(255) NOT NULL,
  `tableStatus` tinyint(4) NOT NULL,
  `orderDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dinnerTable`
--

LOCK TABLES `dinnerTable` WRITE;
/*!40000 ALTER TABLE `dinnerTable` DISABLE KEYS */;
INSERT INTO `dinnerTable` VALUES (1,'1号',0,NULL),(2,'2号',0,NULL),(3,'3号',0,NULL),(4,'4号',0,NULL),(5,'5号',0,NULL),(7,'7号',0,NULL);
/*!40000 ALTER TABLE `dinnerTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `food` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `foodName` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `mprice` double NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `foodType_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `foodType_id` (`foodType_id`),
  CONSTRAINT `food_ibfk_1` FOREIGN KEY (`foodType_id`) REFERENCES `foodType` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'西红柿',5.7,5.1,'不好吃','/home/gxf/project/hotel/IHotel/target/IHotel/Screenshot from 2018-10-11 14-35-21.png',2),(2,'JL',5.8,4.7,'jl社区提供','/home/gxf/project/hotel/IHotel/target/IHotel/zhifu02.png',2),(3,'HHHFF',5.6,5.5,NULL,NULL,2),(4,'HHHFF',5.6,5.5,NULL,NULL,2),(5,'HHHFF',5.6,5.5,NULL,NULL,2),(6,'巨难吃的菜',8.9,7.5,NULL,NULL,2),(7,'程志涛',0.02,0.01,'不值钱01','/home/gxf/project/hotel/IHotel/target/IHotel/zhifu01.png',1),(8,'白虾',36,23,'真的白瞎','/home/gxf/project/hotel/IHotel/target/IHotel/nologin.png',2);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foodType`
--

DROP TABLE IF EXISTS `foodType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `foodType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodType`
--

LOCK TABLES `foodType` WRITE;
/*!40000 ALTER TABLE `foodType` DISABLE KEYS */;
INSERT INTO `foodType` VALUES (1,'蔬菜'),(2,'店家推荐'),(4,'赣菜');
/*!40000 ALTER TABLE `foodType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderDetail`
--

DROP TABLE IF EXISTS `orderDetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orderDetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `foodCount` int(11) NOT NULL,
  `orderid` int(11) NOT NULL,
  `food_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `orderid` (`orderid`),
  KEY `food_id` (`food_id`),
  CONSTRAINT `orderDetail_ibfk_1` FOREIGN KEY (`orderid`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orderDetail_ibfk_2` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderDetail`
--

LOCK TABLES `orderDetail` WRITE;
/*!40000 ALTER TABLE `orderDetail` DISABLE KEYS */;
INSERT INTO `orderDetail` VALUES (1,1,1,4),(2,1,1,1),(3,1,1,6),(4,1,2,6),(5,1,3,6),(6,1,3,1),(7,2,4,6),(8,1,4,1),(9,1,5,5),(10,2,6,6),(11,1,6,1);
/*!40000 ALTER TABLE `orderDetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `totalPrice` double NOT NULL,
  `orderStatus` tinyint(4) NOT NULL DEFAULT '0',
  `orderDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `table_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `table_id` (`table_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`table_id`) REFERENCES `dinnerTable` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,20.200000000000003,1,'2018-10-10 18:05:23',1),(2,8.9,1,'2018-10-10 19:23:19',1),(3,14.600000000000001,1,'2018-10-10 19:23:55',1),(4,23.5,1,'2018-10-11 08:56:08',2),(5,5.6,1,'2018-10-11 10:10:19',1),(6,23.5,1,'2018-10-11 14:37:36',1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `role` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('8d10055b7c404a72b985957082411a88','aa@aa.aa','JL','123456',0,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-30 10:36:37
