-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: social_media
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `post_id` varchar(200) NOT NULL,
  `status` enum('LIKE','NOT_LIKE') DEFAULT NULL,
  `user_code` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (1,'1','LIKE','U0002'),(2,'6','LIKE','U0002'),(3,'7','NOT_LIKE','U0002');
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_image_url`
--

DROP TABLE IF EXISTS `post_image_url`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_image_url` (
  `post_id` bigint NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  KEY `FKr02bm6k20ydy439vteuj0lu43` (`post_id`),
  CONSTRAINT `FKr02bm6k20ydy439vteuj0lu43` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_image_url`
--

LOCK TABLES `post_image_url` WRITE;
/*!40000 ALTER TABLE `post_image_url` DISABLE KEYS */;
INSERT INTO `post_image_url` VALUES (1,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715448246/atqzg5svt4hwlci9yt6l.jpg'),(1,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715448248/otukstrp6y1egxecuzga.jpg'),(1,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715448250/a01hgqmafscqtjdkx32j.jpg'),(2,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715448478/isqbz1uyppbmgf7wmkut.jpg'),(2,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715448479/ksfazn4e5hi1je2rqqkg.jpg'),(2,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715448480/fa3pufdgkwtbklja1wxk.jpg'),(2,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715448481/bavujfscdyf0mmgvwfrd.jpg'),(3,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715448555/mzw2uohvhr8mptlltjbf.jpg'),(3,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715448556/glotjjoegtmteagrehfw.jpg'),(3,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715448557/ofklz7ivyzqtzgyfmmm9.jpg'),(5,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715448718/vriloptggzek0jmy4cxu.webp'),(5,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715448720/lezpj7dsmudmeeyonjyo.webp'),(4,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715448618/hsa97vmwgl6n8lroqmxd.jpg'),(4,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715448620/yx05akoq6gzqvqs9afps.jpg'),(6,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715449376/jrj714gd6pjxvfu3m4hl.jpg'),(6,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715449377/egzrlnqhcdfhidwfihpo.png'),(7,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715449430/gvwrlmjr7adgstftvbya.png'),(7,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715449431/hhfflwu3aiuj3gfrrqdx.jpg'),(7,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715449432/ogetwwkfx0rbxwbi1j5b.jpg'),(8,'http://res.cloudinary.com/dbehok9oq/image/upload/v1715450044/arzxrkqijebwt4xm7ema.png');
/*!40000 ALTER TABLE `post_image_url` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(5000) NOT NULL,
  `like_count` int NOT NULL,
  `status` int NOT NULL,
  `title` varchar(200) NOT NULL,
  `user_id` varchar(200) NOT NULL,
  `username` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (1,'A rose is either a woody perennial flowering plant of the genus Rosa, in the family Rosaceae, or the flower it bears. There are over three hundred species and tens of thousands of cultivars.',1,1,'Rose Flower','U0001','admin'),(2,'The iPhone is a line of smartphones produced by Apple that use Apple\'s own iOS mobile operating system. The first-generation iPhone was announced by then–Apple CEO Steve Jobs on January 9, 2007. Since then, Apple has annually released new iPhone models and iOS updates. As of November 1, 2018, more than 2.2 billion iPhones had been sold.',0,1,'Iphone','U0001','admin'),(3,'Jeep is an American automobile marque, now owned by multi-national corporation Stellantis. Jeep has been part of Chrysler since 1987, when Chrysler acquired the Jeep brand, along with other assets, from their previous owner American Motors Corporation.',0,1,'Jeep','U0001','admin'),(4,'Description: Asus was founded in Taipei in 1989 by T.H. Tung, Ted Hsu, Wayne Hsieh and M.T. Liao, all four having previously worked at Acer as hardware engineers. At this time, Taiwan had yet to establish a leading position in the computer hardware business.',0,1,'ASUS','4','admin'),(5,'Microsoft Corporation is an American multinational corporation and technology company headquartered in Redmond, Washington. Microsoft\'s best-known software products are the Windows line of operating systems, the Microsoft 365 suite of productivity',0,1,'Microsoft Corporation','U0001','admin'),(6,'Mark Elliot Zuckerberg is an American businessman and philanthropist. He co-founded the social media service Facebook, along with his Harvard roommates in 2004, and its parent company Meta Platforms, of which he is chairman, chief executive officer and controlling shareholder.',1,0,'Mark Zuckerberg','U0001','admin'),(7,'Tesla, Inc. is an American multinational automotive and clean energy company headquartered in Austin, Texas, which designs, manufactures and sells battery electric vehicles, stationary battery energy storage devices from home to grid-scale, solar panels and solar shingles, and related products and services.',0,1,'Testla','U0001','admin'),(8,'Angular is a TypeScript-based, free and open-source single-page web application framework run on Node.js. It is led by the Angular Team at Google and by a community of individuals and corporations. Angular is a complete rewrite from the same team that built AngularJS.',0,1,'Angular','U0001','admin');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_active` int DEFAULT '1',
  `name` enum('ROLE_ADMIN','ROLE_USER') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,1,'ROLE_ADMIN'),(2,1,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date_time` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `is_active` int DEFAULT '0',
  `password` varchar(120) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `user_code` varchar(255) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_t4oh2dnaf9b4jc7qj8rxswgyh` (`user_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2024-05-11','admin@gmail.com',1,'$2a$10$L1BiIlA9aVMcf3S5Kq0nFusr42Vq76KvA9TsAzBB2ptoRtLcyyRwC','ROLE_ADMIN','U0001','admin'),(2,'2024-05-11','user@gmail.com',1,'$2a$10$oK653GHvqs/y1MeN.eKQ3etrxYeRC1/vpF1DCn4zSgXiaYPeqQA3i','ROLE_USER','U0002','user');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-11 23:59:53
