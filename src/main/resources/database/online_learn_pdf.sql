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
-- Table structure for table `pdf`
--

DROP TABLE IF EXISTS `pdf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pdf` (
  `pdf_id` int NOT NULL AUTO_INCREMENT,
  `pdf_name` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `file_pdf` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `lesson_id` int NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`pdf_id`),
  KEY `IX_pdf_lesson_id` (`lesson_id`),
  CONSTRAINT `FK_pdf_lesson_lesson_id` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`lesson_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pdf`
--

LOCK TABLES `pdf` WRITE;
/*!40000 ALTER TABLE `pdf` DISABLE KEYS */;
INSERT INTO `pdf` VALUES (1,'Get More from the Georgia Tech Language Institute','Get More from the Georgia Tech Language Institute.pdf',18,'2026-06-10 06:59:23',NULL),(2,'Reading','Reading.pdf',17,'2026-06-10 06:59:23',NULL),(3,'Farewell and Hello','Farewell and Hello.pdf',20,'2026-06-10 06:59:23',NULL),(4,'Welcome and Course Information','Welcome and Course Information.pdf',11,'2026-06-10 06:59:23',NULL),(5,'Written Communication','Written Communication.pdf',19,'2026-06-10 06:59:23',NULL),(6,'Get to Know Your Classmates','Get to Know Your Classmates.pdf',12,'2026-06-10 06:59:23',NULL),(7,'Focused versus Diffuse Thinking','Focused versus Diffuse Thinking.pdf',13,'2026-06-10 06:59:23',NULL),(8,'A Posting about Anxiety','A Posting about Anxiety.pdf',14,'2026-06-10 06:59:23',NULL),(9,'Chunking','Chunking.pdf',15,'2026-06-10 06:59:23',NULL),(10,'Ethical Considerations for Data Science','Ethical Considerations for Data Science.pdf',7,'2026-06-10 06:59:23',NULL),(11,'Benefits of Ethical Data Science','Benefits of Ethical Data Science.pdf',8,'2026-06-10 06:59:23',NULL),(12,'A Day in the Life of an Ethical Data Scientis','A Day in the Life of an Ethical Data Scientist.pdf',7,'2026-06-10 06:59:23',NULL),(13,'How to Teach Artificial Intelligence Some Common Sense','How to Teach Artificial Intelligence Some Common Sense.pdf',9,'2026-06-10 06:59:23',NULL),(14,'Ethical Considerations for AI','Ethical Considerations for AI.pdf',10,'2026-06-10 06:59:23',NULL),(15,'How AI detectives are cracking open the black box of deep learning','How AI detectives are cracking open the black box of deep learning.pdf',9,'2026-06-10 06:59:23',NULL),(16,'Reading Summary','Chunking.pdf',26,'2026-06-10 06:59:23',NULL);
/*!40000 ALTER TABLE `pdf` ENABLE KEYS */;
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
