-- MySQL dump 10.13  Distrib 5.5.15, for Win64 (x86)
--
-- Host: localhost    Database: openmentor
-- ------------------------------------------------------
-- Server version	5.5.15

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
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `course_id` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `course_id` (`course_id`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment`
--

LOCK TABLES `assignment` WRITE;
/*!40000 ALTER TABLE `assignment` DISABLE KEYS */;
INSERT INTO `assignment` VALUES (1,0,'TMA01','CM2006',NULL),(2,0,'TMA02','CM2006',NULL),(3,0,'TMA03','CM2006',NULL),(4,0,'TMA01','CM2007',NULL),(5,0,'TMA01','CM3010',NULL),(6,0,'TMA01','AA1003',NULL);
/*!40000 ALTER TABLE `assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category` varchar(8) NOT NULL,
  `version` bigint(20) NOT NULL,
  `band` varchar(2) NOT NULL,
  PRIMARY KEY (`category`),
  KEY `band_index` (`band`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('A1',0,'A'),('A2',0,'A'),('A3',0,'A'),('B1',0,'B'),('B2',0,'B'),('B3',0,'B'),('C1',0,'C'),('C2',0,'C'),('C3',0,'C'),('D1',0,'D'),('D2',0,'D'),('D3',0,'D');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `submission_id` bigint(20) NOT NULL,
  `text` varchar(4096) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK38A5EE5FB5AFCD5D` (`submission_id`),
  CONSTRAINT `FK38A5EE5FB5AFCD5D` FOREIGN KEY (`submission_id`) REFERENCES `submission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_category`
--

DROP TABLE IF EXISTS `comment_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment_category` (
  `comment_categories_id` bigint(20) DEFAULT NULL,
  `category_id` varchar(8) DEFAULT NULL,
  KEY `FKA7CEBADE4866F885` (`category_id`),
  KEY `FKA7CEBADE4D411E5A` (`comment_categories_id`),
  CONSTRAINT `FKA7CEBADE4D411E5A` FOREIGN KEY (`comment_categories_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `FKA7CEBADE4866F885` FOREIGN KEY (`category_id`) REFERENCES `category` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_category`
--

LOCK TABLES `comment_category` WRITE;
/*!40000 ALTER TABLE `comment_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `course_id` varchar(255) NOT NULL,
  `version` bigint(20) NOT NULL,
  `course_title` varchar(255) NOT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE KEY `course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('AA1003',0,'Multimedia Programming'),('CM2006',0,'Interface Design'),('CM2007',0,'Intranet Systems Development'),('CM3010',0,'Information Retrieval');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_students`
--

DROP TABLE IF EXISTS `course_students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_students` (
  `student_id` varchar(255) NOT NULL,
  `course_id` varchar(255) NOT NULL,
  PRIMARY KEY (`course_id`,`student_id`),
  KEY `FKD6EF053C6C6E4376` (`student_id`),
  KEY `FKD6EF053CF6F414BE` (`course_id`),
  CONSTRAINT `FKD6EF053CF6F414BE` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `FKD6EF053C6C6E4376` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_students`
--

LOCK TABLES `course_students` WRITE;
/*!40000 ALTER TABLE `course_students` DISABLE KEYS */;
INSERT INTO `course_students` VALUES ('09000231','CM2006'),('09000231','CM2007'),('09000232','AA1003'),('09000232','CM2006'),('09000232','CM2007'),('09000233','AA1003'),('09000233','CM2006'),('09000233','CM3010'),('09000234','AA1003'),('09000234','CM2006'),('09000234','CM2007'),('09000234','CM3010'),('09000235','CM2006'),('09000235','CM3010'),('09000236','AA1003'),('09000236','CM2007'),('09000237','AA1003'),('09000237','CM2006');
/*!40000 ALTER TABLE `course_students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_tutors`
--

DROP TABLE IF EXISTS `course_tutors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_tutors` (
  `tutor_id` varchar(255) NOT NULL,
  `course_id` varchar(255) NOT NULL,
  PRIMARY KEY (`course_id`,`tutor_id`),
  KEY `FK6C8A28A195B1496` (`tutor_id`),
  KEY `FK6C8A28A1F6F414BE` (`course_id`),
  CONSTRAINT `FK6C8A28A1F6F414BE` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `FK6C8A28A195B1496` FOREIGN KEY (`tutor_id`) REFERENCES `tutor` (`tutor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_tutors`
--

LOCK TABLES `course_tutors` WRITE;
/*!40000 ALTER TABLE `course_tutors` DISABLE KEYS */;
INSERT INTO `course_tutors` VALUES ('M4000061','AA1003'),('M4000061','CM2006'),('M4000062','CM2006'),('M4000062','CM2007'),('M4000063','CM2006'),('M4000063','CM3010');
/*!40000 ALTER TABLE `course_tutors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade` (
  `grade` varchar(2) NOT NULL,
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`grade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES ('A',0),('B',0),('C',0),('D',0),('E',0),('F',0);
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration_code`
--

DROP TABLE IF EXISTS `registration_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registration_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` datetime NOT NULL,
  `token` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration_code`
--

LOCK TABLES `registration_code` WRITE;
/*!40000 ALTER TABLE `registration_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `registration_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `authority` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `authority` (`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,0,'ROLE_OPENMENTOR-USER'),(2,0,'ROLE_OPENMENTOR-ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` varchar(255) NOT NULL,
  `version` bigint(20) NOT NULL,
  `family_name` varchar(255) NOT NULL,
  `given_name` varchar(255) NOT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `student_id` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('09000231',0,'Blane','Gwenda'),('09000232',0,'Zucker','Fred'),('09000233',0,'Respass','Caitlyn'),('09000234',0,'Naccarato','Luke'),('09000235',0,'Busse','Pierre'),('09000236',0,'Montalvo','Ami'),('09000237',0,'Nicolas','Jackie');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submission`
--

DROP TABLE IF EXISTS `submission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `assignment_id` bigint(20) NOT NULL,
  `date_submitted` datetime NOT NULL,
  `file_contents` mediumblob,
  `filename` varchar(255) NOT NULL,
  `grade_id` varchar(2) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK84363B4CB7A962AF` (`grade_id`),
  KEY `FK84363B4C37E291BD` (`assignment_id`),
  CONSTRAINT `FK84363B4C37E291BD` FOREIGN KEY (`assignment_id`) REFERENCES `assignment` (`id`),
  CONSTRAINT `FK84363B4CB7A962AF` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`grade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission`
--

LOCK TABLES `submission` WRITE;
/*!40000 ALTER TABLE `submission` DISABLE KEYS */;
/*!40000 ALTER TABLE `submission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submission_student_ids`
--

DROP TABLE IF EXISTS `submission_student_ids`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submission_student_ids` (
  `submission_id` bigint(20) DEFAULT NULL,
  `student_ids_string` varchar(255) DEFAULT NULL,
  KEY `FK4BE1BA41B5AFCD5D` (`submission_id`),
  CONSTRAINT `FK4BE1BA41B5AFCD5D` FOREIGN KEY (`submission_id`) REFERENCES `submission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission_student_ids`
--

LOCK TABLES `submission_student_ids` WRITE;
/*!40000 ALTER TABLE `submission_student_ids` DISABLE KEYS */;
/*!40000 ALTER TABLE `submission_student_ids` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submission_tutor_ids`
--

DROP TABLE IF EXISTS `submission_tutor_ids`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submission_tutor_ids` (
  `submission_id` bigint(20) DEFAULT NULL,
  `tutor_ids_string` varchar(255) DEFAULT NULL,
  KEY `FK2E2A58FCB5AFCD5D` (`submission_id`),
  CONSTRAINT `FK2E2A58FCB5AFCD5D` FOREIGN KEY (`submission_id`) REFERENCES `submission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission_tutor_ids`
--

LOCK TABLES `submission_tutor_ids` WRITE;
/*!40000 ALTER TABLE `submission_tutor_ids` DISABLE KEYS */;
/*!40000 ALTER TABLE `submission_tutor_ids` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutor`
--

DROP TABLE IF EXISTS `tutor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tutor` (
  `tutor_id` varchar(255) NOT NULL,
  `version` bigint(20) NOT NULL,
  `family_name` varchar(255) NOT NULL,
  `given_name` varchar(255) NOT NULL,
  PRIMARY KEY (`tutor_id`),
  UNIQUE KEY `tutor_id` (`tutor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutor`
--

LOCK TABLES `tutor` WRITE;
/*!40000 ALTER TABLE `tutor` DISABLE KEYS */;
INSERT INTO `tutor` VALUES ('M4000061',0,'Beatrice','Zena'),('M4000062',0,'Evert','Levi'),('M4000063',0,'Denman','Jeanie');
/*!40000 ALTER TABLE `tutor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `password_expired` bit(1) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,0,'\0','\0','','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','\0','admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FK143BF46AD9ABBC1F` (`role_id`),
  KEY `FK143BF46A7ED67FFF` (`user_id`),
  CONSTRAINT `FK143BF46A7ED67FFF` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK143BF46AD9ABBC1F` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (2,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weight`
--

DROP TABLE IF EXISTS `weight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weight` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `band` varchar(2) NOT NULL,
  `grade` varchar(2) NOT NULL,
  `weight` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD0D14278B8341403` (`grade`),
  KEY `band_grade_index` (`band`,`grade`),
  CONSTRAINT `FKD0D14278B8341403` FOREIGN KEY (`grade`) REFERENCES `grade` (`grade`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weight`
--

LOCK TABLES `weight` WRITE;
/*!40000 ALTER TABLE `weight` DISABLE KEYS */;
INSERT INTO `weight` VALUES (1,0,'A','A',0.3),(2,0,'B','A',0.5),(3,0,'C','A',0.15),(4,0,'D','A',0.05),(5,0,'A','B',0.25),(6,0,'B','B',0.5),(7,0,'C','B',0.2),(8,0,'D','B',0.05),(9,0,'A','C',0.15),(10,0,'B','C',0.6),(11,0,'C','C',0.2),(12,0,'D','C',0.05),(13,0,'A','D',0.1),(14,0,'B','D',0.6),(15,0,'C','D',0.25),(16,0,'D','D',0.05),(17,0,'A','E',0.05),(18,0,'B','E',0.65),(19,0,'C','E',0.25),(20,0,'D','E',0.05),(21,0,'A','F',0.05),(22,0,'B','F',0.65),(23,0,'C','F',0.25),(24,0,'D','F',0.05);
/*!40000 ALTER TABLE `weight` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-05-07 17:35:12
