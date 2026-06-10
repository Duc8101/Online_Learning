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
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_account` (
  `user_account_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` bigint NOT NULL,
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role_id` int NOT NULL,
  `failed_login_count` int NOT NULL,
  `lockout_end_time` datetime DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`user_account_id`),
  UNIQUE KEY `IX_user_account_user_id` (`user_id`),
  UNIQUE KEY `IX_user_account_username` (`username`),
  KEY `IX_user_account_role_id` (`role_id`),
  CONSTRAINT `FK_user_account_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE,
  CONSTRAINT `FK_user_account_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES ('17ab4b5e-308c-4a9a-a4ed-39e7eb799e33',2,'AnhTuan','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',3,0,NULL,'2026-06-10 06:59:23',NULL),('1d847cbc-67d1-4674-b787-f63e4355c0a7',7,'Nicky1','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',3,0,NULL,'2026-06-10 06:59:23',NULL),('2be293a5-4461-4d2c-b14e-dd6309cf81f0',3,'QuangQuan','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',3,0,NULL,'2026-06-10 06:59:23',NULL),('339cd215-71b4-4a2b-b807-30c80c7d721e',10,'Terrell','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',2,0,NULL,'2026-06-10 06:59:23',NULL),('41acd1a2-b243-4c87-951d-c3c5b4b1e17c',1,'ThuThu','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',3,0,NULL,'2026-06-10 06:59:23',NULL),('535fa998-4236-4e4e-a5ac-61f7e195efc5',4,'NhatAnh','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',3,0,NULL,'2026-06-10 06:59:23',NULL),('62c26e9c-d821-45b7-a1ed-329f4deefcf2',11,'Virge1','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',2,0,NULL,'2026-06-10 06:59:23',NULL),('7f3a258b-45a2-4bed-b100-30c3bb319f6f',6,'VuongDepTrai','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',3,0,NULL,'2026-06-10 06:59:23',NULL),('9dd7ff7a-bb9c-4d1d-bb9f-0a9eb0937046',13,'Admin123','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',1,0,NULL,'2026-06-10 06:59:23',NULL),('a91230d4-8209-4762-b686-d0ff73e2cad6',8,'Kirk12','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',2,0,NULL,'2026-06-10 06:59:23',NULL),('b0dc3b2c-07f0-4047-b62d-6b2cbc6845c3',12,'Tommy1','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',2,0,NULL,'2026-06-10 06:59:23',NULL),('c108879f-b004-4164-94a3-ca3806cf0c52',5,'MinhDuc','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',3,0,NULL,'2026-06-10 06:59:23',NULL),('fc963acf-a463-41ce-b553-685076d3aaba',9,'Ulises','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',2,0,NULL,'2026-06-10 06:59:23',NULL);
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-10 14:00:13
