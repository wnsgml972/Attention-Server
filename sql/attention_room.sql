-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: attention
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `room_uuid` varchar(100) NOT NULL,
  `time` varchar(100) DEFAULT NULL,
  `sender_name` varchar(100) DEFAULT NULL,
  `chat_content` varchar(200) DEFAULT NULL,
  `sender_uuid` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`room_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES ('052ce6fbedfb497c965d26d5af311028','07:36','Server','채팅방에 초대되셨습니다.','Server'),('0ad5bf0830ca4167be801f6a00f3ef43','07:29','Server','채팅방에 초대되셨습니다.','Server'),('1b200b80d8a24599b553ac483afceaf5','07:31','Server','채팅방에 초대되셨습니다.','Server'),('1be17c93f561422c8fbd8393777dd966','08:02','Server','채팅방에 초대되셨습니다.','Server'),('2e197f3e5da14139b9b45f9fb04b9a45','07:38','Server','채팅방에 초대되셨습니다.','Server'),('311ea044bc624f0cbae077917b7da6cf','08:11','Server','채팅방에 초대되셨습니다.','Server'),('4a227584bb724cd2ba2693669dd78fce','08:07','Server','채팅방에 초대되셨습니다.','Server'),('5036403298f343e5ab0208fac88ed01d','06:12','Server','채팅방에 초대되셨습니다.','Server'),('5dfd10eff1724d278fc486bf8245b775','07:44','Server','채팅방에 초대되셨습니다.','Server'),('66cddb840ba04b05885d2c4512cc151c','06:39','Server','채팅방에 초대되셨습니다.','Server'),('6e7d41c378914f69bf5111a3de77a631','04:19','최용석','안녕','95bd2799968144219f5bda244ed4f14c'),('8f21d75324bd402588a6a06baea278f0','06:35','Server','채팅방에 초대되셨습니다.','Server'),('a08444ac6acb487ba7a469e70d48b46b','06:42','Server','채팅방에 초대되셨습니다.','Server'),('a5f3354054214b1f87fe6b198bb75b77','08:14','Server','채팅방에 초대되셨습니다.','Server'),('bf36a4afc10f46b2b470225a507b6d9e','06:49','Server','채팅방에 초대되셨습니다.','Server'),('c0b9ab1c925c43ddb75c3ab398afe081','04:32','진소린','ㅎㅇㅎㅇ','af77ddba7afd416cb3bb624516a5dbb1'),('c3ede5c14d7c4009a5552f1b8e329983','06:23','Server','채팅방에 초대되셨습니다.','Server'),('c802deb1d95743c6a63a2b364826f642','06:54','Server','채팅방에 초대되셨습니다.','Server'),('e0c98dfff4714990b459e208dde5faf2','06:28','Server','채팅방에 초대되셨습니다.','Server'),('e4885527593041eda23a966d84ca5909','06:48','Server','채팅방에 초대되셨습니다.','Server'),('e5636a3d7b964012bd9daf2e4b6aab35','04:31','김준희','ㅎㅇ','4da15c84c60b4fe2af692e55d173d30a'),('f84511e3a4c94bfd81da5cc90f4385dd','08:01','Server','채팅방에 초대되셨습니다.','Server');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-21  8:20:22
