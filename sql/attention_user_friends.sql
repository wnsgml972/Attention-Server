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
-- Table structure for table `user_friends`
--

DROP TABLE IF EXISTS `user_friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_friends` (
  `uuid` varchar(100) NOT NULL,
  `friend_uuid` varchar(100) NOT NULL,
  `p2p_chat_uuid` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`uuid`,`friend_uuid`),
  KEY `fk_friends_idx` (`uuid`),
  CONSTRAINT `fk_friends` FOREIGN KEY (`uuid`) REFERENCES `user` (`uuid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_friends`
--

LOCK TABLES `user_friends` WRITE;
/*!40000 ALTER TABLE `user_friends` DISABLE KEYS */;
INSERT INTO `user_friends` VALUES ('4da15c84c60b4fe2af692e55d173d30a','95bd2799968144219f5bda244ed4f14c','6e7d41c378914f69bf5111a3de77a631'),('4da15c84c60b4fe2af692e55d173d30a','af77ddba7afd416cb3bb624516a5dbb1','e5636a3d7b964012bd9daf2e4b6aab35'),('95bd2799968144219f5bda244ed4f14c','4da15c84c60b4fe2af692e55d173d30a','6e7d41c378914f69bf5111a3de77a631'),('95bd2799968144219f5bda244ed4f14c','af77ddba7afd416cb3bb624516a5dbb1','c0b9ab1c925c43ddb75c3ab398afe081'),('af77ddba7afd416cb3bb624516a5dbb1','4da15c84c60b4fe2af692e55d173d30a','e5636a3d7b964012bd9daf2e4b6aab35'),('af77ddba7afd416cb3bb624516a5dbb1','95bd2799968144219f5bda244ed4f14c','c0b9ab1c925c43ddb75c3ab398afe081');
/*!40000 ALTER TABLE `user_friends` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-21  8:20:21
