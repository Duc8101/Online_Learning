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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_name` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `image` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `category_id` int NOT NULL,
  `creator_id` bigint NOT NULL,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`course_id`),
  KEY `IX_course_category_id` (`category_id`),
  KEY `IX_course_creator_id` (`creator_id`),
  CONSTRAINT `FK_course_category_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE,
  CONSTRAINT `FK_course_user_creator_id` FOREIGN KEY (`creator_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Working With Color Color in Adobe Photoshop','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTtAJzLDx1VGYsdC9Mg1rqYkhswW8kQ4dLRw&usqp=CAU',1,8,'Any artist will tell you that the use of color is a major component of the design process, regardless of the medium. Digital art and photography are no exceptions. Color can be powerful and evocative, but only if you know how to use it properly. In this course you will learn about the basics of color theory. We will then take a look at the different ways Photoshop handles colors using color modes, and what the advantages and disadvantages are. After that we will use this knowledge to assemble a colorful and vibrant digital scene, using several different techniques for controlling color in Photoshop.','2026-06-10 06:59:23',NULL,0),(2,'CertNexus Certified Ethical Emerging Technologist Professional Certificate','http://img.youtube.com/vi/3OJOEo6MTaI/hqdefault.jpg',2,9,'Learners will create a portfolio of assets highlighting their skills as ethical leaders. The assets consist of written documents and video communications required of ethical leaders, including Op-Ed articles, risk management reports, strategy memos, media releases, and video press briefings.Learners who complete the Honors projects will also author an industry feature article; a recommendation memo for the most appropriate ethical framework to guide an organization or a project; an Algorithmic Impact Assessment; a change management presentation to a Board of Directors; and a strategic business document such as a cost-benefit analysis, design plan, or business continuity plan.','2026-06-10 06:59:23',NULL,0),(3,'Learning How to Learn : Powerful mental tools to help you master tough subjects','https://s3-ap-southeast-1.amazonaws.com/images.spiderum.com/sp-images/ecc93e90203511ec9c8281a41a921537.png',3,10,'Although living brains are very complex, this module uses metaphor and analogy to help simplify matters. You will discover several fundamentally different modes of thinking, and how you can use these modes to improve your learning. You will also be introduced to a tool for tackling procrastination, be given some practical information about memory, and discover surprisingly useful insights about learning and sleep.','2026-06-10 06:59:23',NULL,0),(4,'Better Business Writing in English','https://technicalwriterhq.com/wp-content/uploads/2022/02/41-Business-Writing-Course.jpg',4,11,'Learning outcomes: After this module, you will be able to develop your personal voice and increase your accuracy, and appropriateness in written English, and produce a written document which displays your personal voice.','2026-06-10 06:59:23',NULL,0),(5,'Social Media Marketing','https://social.vn/wp-content/uploads/2020/11/Social-Media-Marketing.jpg',5,8,'In a 2018 survey of businesses, Buffer found that only 29% had effective social media marketing programs.    A recent survey of consumers by Tomoson found 92% of consumers trust recommendations from other people over brand content, 70% found consumer reviews to be their second most trusted source, 47% read blogs developed by influencers and experts to discover new trends and new ideas and 35% used blogs to discover new products and services.  Also, 20% of women who used social considered products promoted by bloggers they knew.   Today, businesses and consumers use social media to make their purchase decisions.','2026-06-10 06:59:23',NULL,0),(6,'Object Oriented Programming in C++','https://res.cloudinary.com/practicaldev/image/fetch/s--rPvSn38T--/c_imagga_scale,f_auto,fl_progressive,h_420,q_auto,w_1000/https://dev-to-uploads.s3.amazonaws.com/i/h2917prj7ldo0ow5x5ih.png',2,9,'Object-Oriented-Programming is an object-based programming method to find the essence of the problem. C++ OOP course helps programmers learn programming techniques where all logic','2026-06-10 06:59:23',NULL,0),(7,'Basic Cross-Platform Application Programming With .NET','https://coodesh.com/blog/wp-content/uploads/2021/11/Artigo-148-scaled.jpg',2,9,'.NET Core is a free and open source framework for developing cross-platform applications targeting Windows, Linux and macOS. It is capable of running applications on devices, the cloud and the IoT. It supports four cross-platform scenarios: ASP.NET Core Web apps, command line apps, libraries and Web APIs. The recently released .NET Core 3 (preview bits) supports Windows rendering forms like WinForms, WPF and UWP, but only on Windows.','2026-06-10 06:59:23',NULL,0),(8,'Skill Play Game','https://cdn.sforum.vn/sforum/wp-content/uploads/2022/01/thum-960x540.jpg',3,10,'You can master all the skills in league of legends wild rift with the instructional tips learned from this course','2026-06-10 06:59:23',NULL,0),(9,'Design JSP','https://wiki.tino.org/wp-content/uploads/2021/04/servlet-jsp-tutorial.png',5,8,'good','2026-06-10 06:59:23',NULL,0);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
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
