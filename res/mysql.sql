-- MySQL dump 10.15  Distrib 10.0.25-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: scutcmsdb
-- ------------------------------------------------------
-- Server version	10.0.25-MariaDB-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `EMPLOYEE_ID` char(12) NOT NULL,
  `EMPLOYEE_NAME` varchar(16) NOT NULL,
  `EMPLOYEE_SEX` char(2) NOT NULL,
  `EMPLOYEE_SFZH` char(18) NOT NULL,
  `EMPLOYEE_AGE` int(11) NOT NULL,
  `EMPLOYEE_ADDRESS` varchar(120) NOT NULL,
  `EMPLOYEE_TELPHONE` char(11) NOT NULL,
  `ENTER_TIME` date NOT NULL,
  `POSITION_NAME` varchar(16) NOT NULL,
  PRIMARY KEY (`EMPLOYEE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('123456789012','陈耀佛','男','963214587123456789',20,'华南理工','13052163214','2016-06-08','经理'),('123456789101','胡少游','男','123456789101112131',20,'华南理工','12345678910','2014-09-01','主厨');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procurement`
--

DROP TABLE IF EXISTS `procurement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procurement` (
  `PRODUCT_NAME` varchar(20) NOT NULL,
  `UNIT_PRICE` double NOT NULL,
  `AMOUT` double NOT NULL,
  `PROCURE_DATE` date NOT NULL,
  `USERNAME` varchar(18) NOT NULL,
  PRIMARY KEY (`PRODUCT_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procurement`
--

LOCK TABLES `procurement` WRITE;
/*!40000 ALTER TABLE `procurement` DISABLE KEYS */;
/*!40000 ALTER TABLE `procurement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary`
--

DROP TABLE IF EXISTS `salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salary` (
  `EMPLOYEE_ID` char(12) NOT NULL,
  `SALARY_DATE` date NOT NULL,
  `SALARY` double NOT NULL,
  PRIMARY KEY (`EMPLOYEE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary`
--

LOCK TABLES `salary` WRITE;
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tposition`
--

DROP TABLE IF EXISTS `tposition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tposition` (
  `POSITION_NAME` varchar(16) NOT NULL,
  `BASE_SALARY` int(11) NOT NULL,
  `WAGE_HOURLY` int(11) NOT NULL,
  `PUNISHMENT` double NOT NULL,
  PRIMARY KEY (`POSITION_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tposition`
--

LOCK TABLES `tposition` WRITE;
/*!40000 ALTER TABLE `tposition` DISABLE KEYS */;
INSERT INTO `tposition` VALUES ('主厨',5000,30,1.2),('经理',6000,30,0.1);
/*!40000 ALTER TABLE `tposition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `USERNAME` varchar(18) NOT NULL,
  `PASSWORD_MD5` char(32) NOT NULL,
  `SALT` char(32) NOT NULL,
  `TOKEN` char(32) NOT NULL,
  `REAL_NAME` varchar(16) NOT NULL,
  `TELEPHONE` char(11) NOT NULL,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','AB05419EAE480519352B76A53A851A69','F1DN3EaVkAmOBfJRD1uAD1SjvqNoqXmt','YVT2k75HDmJX6H0Zsdt09nRQ4cedJlew','林云龙','13570050739'),('root','8164C3FC1C6F2D2D1D49DBCBBECE984E','N3DV39ohMbJEAdQXgNGnbOTJAIz6Fq0c','Tka1LIXthY1aOT5PQnUNDqnuPAwmOHRG','陈耀佛','13076208199'),('sonic','252E8C435796BD243F59D5BCBF51E75F','M4fGeaOVLfOKD3Nz4rqBcArMGQNxdIwU','','胡少游','12345685213');
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

-- Dump completed on 2016-07-08  9:12:38
