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
INSERT INTO `user_account` VALUES ('0a8cf717-7211-4c28-bea7-aa9eb1a75d99',4,'NhatAnh','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',3,0,NULL,'2026-06-01 02:23:31',NULL),('119e8184-2293-4ef2-bb94-eed016a57976',9,'Ulises','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',2,0,NULL,'2026-06-01 02:23:31',NULL),('13b3f8d6-6268-47ee-8c34-8a8cf0f865d7',5,'MinhDuc','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',3,0,NULL,'2026-06-01 02:23:31',NULL),('1574cf48-270a-4ede-9ea3-b2a1006db1b6',6,'VuongDepTrai','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',3,0,NULL,'2026-06-01 02:23:31',NULL),('2ddf778b-827a-44a5-a90d-07d114601931',10,'Terrell','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',2,0,NULL,'2026-06-01 02:23:31',NULL),('30d90605-8d9d-41c8-8c07-af07856d3363',2,'AnhTuan','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',3,0,NULL,'2026-06-01 02:23:31',NULL),('699ab0e1-9f1c-4706-87fa-f4de63f02aad',12,'Tommy1','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',2,0,NULL,'2026-06-01 02:23:31',NULL),('7a84fb21-bb17-4dd3-bb57-d629f4a4592b',3,'QuangQuan','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',3,0,NULL,'2026-06-01 02:23:31',NULL),('80d28c41-a9df-4d5d-8a3b-c3546e3f93f8',11,'Virge1','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',2,0,NULL,'2026-06-01 02:23:31',NULL),('bf04b229-9af0-435d-a519-6f250054cdbf',8,'Kirk12','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',2,0,NULL,'2026-06-01 02:23:31',NULL),('d360c5c3-f75c-4fef-8619-774d273c4bfc',7,'Nicky1','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',3,0,NULL,'2026-06-01 02:23:31',NULL),('e5d842bb-2a67-4446-9095-16dd8ffb4737',13,'Admin123','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',1,0,NULL,'2026-06-01 02:23:31',NULL),('eb4da074-6b9e-4cde-bd68-5b3233549cc7',1,'ThuThu','b3da3d33827b5556f7d7005e7a2de3eb737e6a0ce509d9fa2854ca30df4f8197',3,0,NULL,'2026-06-01 02:23:31',NULL);
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

-- Dump completed on 2026-06-03 10:24:37
