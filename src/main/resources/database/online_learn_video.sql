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
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video` (
  `video_id` int NOT NULL AUTO_INCREMENT,
  `video_name` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `file_video` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `lesson_id` int NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`video_id`),
  KEY `IX_video_lesson_id` (`lesson_id`),
  CONSTRAINT `FK_video_lesson_lesson_id` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`lesson_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (1,'Introduction','1.Introduction.mp4',1,'2026-06-10 06:59:23',NULL),(2,'The Color Wheel','2.1.The Color Wheel.mp4',2,'2026-06-10 06:59:23',NULL),(3,'Warm vs Cool','2.2.Warm vs Cool.mp4',2,'2026-06-10 06:59:23',NULL),(4,'Color Schemes','2.3.Color Schemes.mp4',2,'2026-06-10 06:59:23',NULL),(5,'Hue, Sturation and Linghtness','2.4.Hue, Sturation and Linghtness.mp4',2,'2026-06-10 06:59:23',NULL),(6,'When Colors Collide','2.5.When Colors Collide.mp4',2,'2026-06-10 06:59:23',NULL),(7,'RGB','3.1.RGB.mp4',3,'2026-06-10 06:59:23',NULL),(8,'CMYK','3.2.CMYK.mp4',3,'2026-06-10 06:59:23',NULL),(9,'LAB','3.3.LAB.mp4',3,'2026-06-10 06:59:23',NULL),(10,'Scene Planning','4.1.Scene Planning.mp4',4,'2026-06-10 06:59:23',NULL),(11,'Controlling Color With the Hue/Saturation Adjustment Layer','4.2.Controlling Color With the Hue_Saturation Adjustment Layer.mp4',4,'2026-06-10 06:59:23',NULL),(12,'Controlling Color With Blending Modes','4.3.Controlling Color With Blending Modes.mp4',4,'2026-06-10 06:59:23',NULL),(13,'Controlling Color With Gradient Maps','4.4.Controlling Color With Gradient Maps.mp4',4,'2026-06-10 06:59:23',NULL),(14,'GUI Color Wheel','5.1.GUI Color Wheel.mp4',5,'2026-06-10 06:59:23',NULL),(15,'Adobe Color Themes','5.2.Adobe Color Themes.mp4',5,'2026-06-10 06:59:23',NULL),(16,'Color Look-Up Tables (CLUT)','5.3.Color Look-Up Tables (CLUT).mp4',5,'2026-06-10 06:59:23',NULL),(17,'Understanding Data Protection','Understanding Data Protection.mp4',21,'2026-06-10 06:59:23',NULL),(18,'Security, Privacy, and Governance Pt. 1','Security, Privacy, and Governance Pt. 1.mp4',21,'2026-06-10 06:59:23',NULL),(19,'Managing Social Programs','Managing Social Programs.mp4',22,'2026-06-10 06:59:23',NULL),(20,'Identifying Social Successes','Identifying Social Successes.mp4',22,'2026-06-10 06:59:23',NULL),(21,'A New Model for Marketers','A New Model for Marketers.mp4',22,'2026-06-10 06:59:23',NULL),(22,'Measuring Engagement','Measuring Engagement.mp4',22,'2026-06-10 06:59:23',NULL),(23,'Finding Relevant Performance Metrics','Finding Relevant Performance Metrics.mp4',22,'2026-06-10 06:59:23',NULL),(24,'Performance Funnels and KPIs','Performance Funnels and KPIs.mp4',22,'2026-06-10 06:59:23',NULL),(25,'Developing Your Budget','Developing Your Budget.mp4',23,'2026-06-10 06:59:23',NULL),(26,'Justification Metrics','Justification Metrics.mp4',23,'2026-06-10 06:59:23',NULL),(27,'Calculating Performance Metrics','Calculating Performance Metrics.mp4',23,'2026-06-10 06:59:23',NULL),(28,'Program Testing','Program Testing.mp4',24,'2026-06-10 06:59:23',NULL),(29,'Program Management','Program Management.mp4',24,'2026-06-10 06:59:23',NULL),(30,'Learn More with Medill IMC','Learn More with Medill IMC.mp4',24,'2026-06-10 06:59:23',NULL),(31,'Moore Law and the 3 Accelerations that changed business forever','Moore\'s Law and the 3 Accelerations that changed business forever.mp4',25,'2026-06-10 06:59:23',NULL),(32,'Thomas Friedman on the 3 Accelerations [book link in resources]','Thomas Friedman on the 3 Accelerations [book link in resources].mp4',25,'2026-06-10 06:59:23',NULL),(33,'Using Social Data','Using Social Data.mp4',25,'2026-06-10 06:59:23',NULL),(34,'Social Data Flows from a Single Source','Social Data Flows from a Single Source.mp4',25,'2026-06-10 06:59:23',NULL),(35,'CEET Specialization Introduction','CEET Specialization Introduction.mp4',6,'2026-06-10 06:59:23',NULL),(36,'Promote the Ethical Use of Data-Driven Technologies Course Introduction','Promote the Ethical Use of Data-Driven Technologies Course Introduction.mp4',6,'2026-06-10 06:59:23',NULL),(37,'Course Welcome & Success Tips','Course Welcome & Success Tips.mp4',6,'2026-06-10 06:59:23',NULL),(38,'Ethics Make a Difference in Emerging Technologies','Ethics Make a Difference in Emerging Technologies.mp4',6,'2026-06-10 06:59:23',NULL),(39,'Big Data','Big Data.mp4',7,'2026-06-10 06:59:23',NULL),(40,'Working with Big Data','Working with Big Data.mp4',7,'2026-06-10 06:59:23',NULL),(41,'Data Analytics','Data Analytics.mp4',7,'2026-06-10 06:59:23',NULL),(42,'Data Science Pipeline','Data Science Pipeline.mp4',7,'2026-06-10 06:59:23',NULL),(43,'Artificial Intelligence','Artificial Intelligence.mp4',8,'2026-06-10 06:59:23',NULL),(44,'Narrow AI','Narrow AI.mp4',8,'2026-06-10 06:59:23',NULL),(45,'General AI and Superintelligence','General AI and Superintelligence.mp4',8,'2026-06-10 06:59:23',NULL),(46,'Ambient Intelligence and IoT','Ambient Intelligence and IoT.mp4',8,'2026-06-10 06:59:23',NULL),(47,'Data Privacy','Data Privacy.mp4',9,'2026-06-10 06:59:23',NULL),(48,'PII','PII.mp4',9,'2026-06-10 06:59:23',NULL),(49,'Privacy Risks in IoT/Ambient Intelligence Technologies','Privacy Risks in IoT Ambient Intelligence Technologies.mp4',9,'2026-06-10 06:59:23',NULL),(50,'Privacy Protection through Individual Authorization','Privacy Protection through Individual Authorization.mp4',9,'2026-06-10 06:59:23',NULL),(51,'Legal Terminology: Responsibility, Accountability, and Liability','Legal Terminology Responsibility, Accountability, and Liability.mp4',10,'2026-06-10 06:59:23',NULL),(52,'Technology Contract Types','Technology Contract Types.mp4',10,'2026-06-10 06:59:23',NULL),(53,'Smart Contracts','Smart Contracts.mp4',10,'2026-06-10 06:59:23',NULL),(54,'Introduction to the Focused and Diffuse Modes','Introduction to the Focused and Diffuse Modes.mp4',11,'2026-06-10 06:59:23',NULL),(55,'Terrence Sejnowski and Barbara Oakley--Introduction to the Course Structure','Terrence Sejnowski and Barbara Oakley--Introduction to the Course Structure.mp4',12,'2026-06-10 06:59:23',NULL),(56,'Using the Focused and Diffuse Modes--Or, a Little Dali will do You','Using the Focused and Diffuse Modes--Or, a Little Dali will do You.mp4',12,'2026-06-10 06:59:23',NULL),(57,'What is Learning?','What is Learning.mp4',12,'2026-06-10 06:59:23',NULL),(58,'A Procrastination Preview','A Procrastination Preview.mp4',13,'2026-06-10 06:59:23',NULL),(59,'Practice Makes Permanent','Practice Makes Permanent.mp4',13,'2026-06-10 06:59:23',NULL),(60,'Introduction to Memory','Introduction to Memory.mp4',13,'2026-06-10 06:59:23',NULL),(61,'The Importance of Sleep in Learning','The Importance of Sleep in Learning.mp4',13,'2026-06-10 06:59:23',NULL),(62,'Summary','Summary video for Module 1.mp4',14,'2026-06-10 06:59:23',NULL),(63,'Excitement About Whats Next! MaryAnne Nestor Gives Special Hints','Excitement About Whats Next! MaryAnne Nestor Gives Special Hints.mp4',14,'2026-06-10 06:59:23',NULL),(64,'Introduction to Renaissance Learning and Unlocking Your Potential','Introduction to Renaissance Learning and Unlocking Your Potential.mp4',15,'2026-06-10 06:59:23',NULL),(65,'How to Become a Better Learner','How to Become a Better Learner.mp4',15,'2026-06-10 06:59:23',NULL),(66,'Create a Lively Visual Metaphor or Analogy','Create a Lively Visual Metaphor or Analogy.mp4',15,'2026-06-10 06:59:23',NULL),(67,'Intro to Course','Intro to Course.mp4',16,'2026-06-10 06:59:23',NULL),(68,'Personal Voice','Personal Voice.mp4',16,'2026-06-10 06:59:23',NULL),(69,'Sentence Types Part 1','Sentence Types Part 1.mp4',17,'2026-06-10 06:59:23',NULL),(70,'Strong Sentences Part 1: Verb Tense & Parallel Structure','Strong Sentences Part 1 Verb Tense & Parallel Structure.mp4',17,'2026-06-10 06:59:23',NULL),(71,'Strong Sentences Part 2: Subject-Verb Agreement','Strong Sentences Part 2  Subject-Verb Agreement.mp4',17,'2026-06-10 06:59:23',NULL),(72,'3 Key Parts of a Proposal','3 Key Parts of a Proposal.mp4',18,'2026-06-10 06:59:23',NULL),(73,'How to Connect Ideas & Sentences','How to Connect Ideas & Sentences.mp4',18,'2026-06-10 06:59:23',NULL),(74,'How to Write a Process','How to Write a Process.mp4',18,'2026-06-10 06:59:23',NULL),(75,'No Need for Genius Envy','No Need for Genius Envy.mp4',19,'2026-06-10 06:59:23',NULL),(76,'Organize, Write, and Design Effective Slides','Organize, Write, and Design Effective Slides.mp4',19,'2026-06-10 06:59:23',NULL),(77,'How to Use Articles and Count/Non-count Nouns','How to Use Articles and Count Non-count Nouns.mp4',20,'2026-06-10 06:59:23',NULL),(78,'Change Your Thoughts, Change Your Life','Change Your Thoughts, Change Your Life.mp4',20,'2026-06-10 06:59:23',NULL),(79,'Summary','Working with Big Data.mp4',26,'2026-06-10 06:59:23',NULL),(80,'Summary2','Privacy Protection through Individual Authorization.mp4',26,'2026-06-10 06:59:23',NULL);
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
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
