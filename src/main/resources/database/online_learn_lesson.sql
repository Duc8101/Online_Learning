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
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lesson` (
  `lesson_id` int NOT NULL AUTO_INCREMENT,
  `lesson_name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `course_id` int NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`lesson_id`),
  KEY `IX_lesson_course_id` (`course_id`),
  CONSTRAINT `FK_lesson_course_course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson`
--

LOCK TABLES `lesson` WRITE;
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
INSERT INTO `lesson` VALUES (1,'Introduction',1,'2026-06-01 02:23:31',NULL),(2,'Basic Color Theory',1,'2026-06-01 02:23:31',NULL),(3,'Color Modes',1,'2026-06-01 02:23:31',NULL),(4,'Working With Color',1,'2026-06-01 02:23:31',NULL),(5,'Tips and Tricks',1,'2026-06-01 02:23:31',NULL),(6,'Overview',2,'2026-06-01 02:23:31',NULL),(7,'Data Science Fundamentals',2,'2026-06-01 02:23:31',NULL),(8,'Artificial Intelligence Fundamentals',2,'2026-06-01 02:23:31',NULL),(9,'Data and Privacy',2,'2026-06-01 02:23:31',NULL),(10,'Legal Concepts Related to Data-Driven Technologies',2,'2026-06-01 02:23:31',NULL),(11,'Introduction',3,'2026-06-01 02:23:31',NULL),(12,'Forcused versus Diffuse Thinking',3,'2026-06-01 02:23:31',NULL),(13,'Procrastination, Memory, and Sleep',3,'2026-06-01 02:23:31',NULL),(14,'Summary',3,'2026-06-01 02:23:31',NULL),(15,'Optional Further Readings and Interviews',3,'2026-06-01 02:23:31',NULL),(16,'Perosonal Voice',4,'2026-06-01 02:23:31',NULL),(17,'Sentence Types',4,'2026-06-01 02:23:31',NULL),(18,'3 Key Parts of a Proposal',4,'2026-06-01 02:23:31',NULL),(19,'Write Better Sentences',4,'2026-06-01 02:23:31',NULL),(20,'Connect Ideas & Sentences',4,'2026-06-01 02:23:31',NULL),(21,'Security, Privacy & Governance Concerns',5,'2026-06-01 02:23:31',NULL),(22,'Most Important Metrics to Observe',5,'2026-06-01 02:23:31',NULL),(23,'Building a Successful Social Marketing Program',5,'2026-06-01 02:23:31',NULL),(24,'Sustaining Your Social Programs',5,'2026-06-01 02:23:31',NULL),(25,'Why is listening critical to your social programs?',5,'2026-06-01 02:23:31',NULL),(26,'Summary',1,'2026-06-01 02:23:31',NULL);
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;
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
