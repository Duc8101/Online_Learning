-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: online_learn
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `full_name` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `image` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` enum('MALE','FEMALE','OTHER') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `RoleId` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `IX_user_email` (`email`),
  KEY `IX_user_RoleId` (`RoleId`),
  CONSTRAINT `FK_user_role_RoleId` FOREIGN KEY (`RoleId`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Nguyen Thi Thu','0984739845','/img/carousel-2.jpg','7683 Ruskin Avenue','oparagreen0@gmail.com','MALE','2026-06-01 02:23:31',NULL,NULL),(2,'Nguyen Anh Tuan','6298446654','https://i.pinimg.com/564x/68/cb/39/68cb398abe7964a7e9eb9f1e9e0da8a6.jpg','0341 Everett Court','kfleet1@gmail.com','MALE','2026-06-01 02:23:31',NULL,NULL),(3,'Chu Quang Quan','8851738015','https://i.pinimg.com/736x/95/0b/21/950b21a6422cf2f2db7579c6494d4acb.jpg','7023 Algoma Street','fellcock2@gmail.com','FEMALE','2026-06-01 02:23:31',NULL,NULL),(4,'Ta Nhat Anh','9306711406','https://pdp.edu.vn/wp-content/uploads/2021/05/hinh-anh-avatar-nam-1-600x600.jpg','943 Heath Pass','phatherell3@gmail.com','FEMALE','2026-06-01 02:23:31',NULL,NULL),(5,'Nguyen Minh Duc','5541282702','/img/avatar.jpg','005 Prairie Rose Point','bkervin4@gmail.com','FEMALE','2026-06-01 02:23:31',NULL,NULL),(6,'Nguyen Minh Vuong','9544569704','https://demoda.vn/wp-content/uploads/2022/04/avatar-cap-doi-chibi-lang-man-ve-nu.jpg','4 Aberg Drive','rrushworth5@gmail.com','MALE','2026-06-01 02:23:31',NULL,NULL),(7,'Nicky Gaitone','7583151589','https://i.pinimg.com/564x/08/51/e6/0851e61234e5341c687dbb716158e320.jpg','11007 Cherokee Drive','ngaitone6@gmail.com','FEMALE','2026-06-01 02:23:31',NULL,NULL),(8,'Kirk Nelson','6481628081','https://s3.amazonaws.com/cms-assets.tutsplus.com/uploads/users/8/profiles/18494/profileImage/KirkHeadShot.jpg',NULL,'KirkNelson@gmail.com','FEMALE','2026-06-01 02:23:31',NULL,NULL),(9,'Ulises Ayliffe','6511678528','/img/team-1.jpg',NULL,'uayliffe1@gmail.com','FEMALE','2026-06-01 02:23:31',NULL,NULL),(10,'Terrell Cordobes','7908661977','/img/team-2.jpg',NULL,'tcordobes2@gmail.com','MALE','2026-06-01 02:23:31',NULL,NULL),(11,'Virge Aldred','2934629124','/img/team-3.jpg',NULL,'valdred3@gmail.com','FEMALE','2026-06-01 02:23:31',NULL,NULL),(12,'Tommy Walbrun','9661305299','https://img.lovepik.com/free-png/20210919/lovepik-male-teacher-teaching-png-image_400770642_wh1200.png',NULL,'twalbrun4@gmail.com','MALE','2026-06-01 02:23:31',NULL,NULL),(13,'Admin','9661231236','/img/team-1.jpg',NULL,'fiadjrf8@gmail.com','MALE','2026-06-01 02:23:31',NULL,NULL);
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

-- Dump completed on 2026-06-03 10:24:38
