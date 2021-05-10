-- MySQL dump 10.18  Distrib 10.3.27-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: BDAngelMartinez
-- ------------------------------------------------------
-- Server version	10.3.27-MariaDB-0+deb10u1

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
-- Table structure for table `alumnes`
--

DROP TABLE IF EXISTS `alumnes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumnes` (
  `codiAlumne` varchar(5) NOT NULL,
  `nomAlumne` varchar(40) NOT NULL,
  `codiTutorAlumne` int(11) NOT NULL,
  PRIMARY KEY (`codiAlumne`),
  KEY `fk_codi_tutor` (`codiTutorAlumne`),
  CONSTRAINT `fk_codi_tutor` FOREIGN KEY (`codiTutorAlumne`) REFERENCES `tutors` (`codiTutor`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnes`
--

LOCK TABLES `alumnes` WRITE;
/*!40000 ALTER TABLE `alumnes` DISABLE KEYS */;
INSERT INTO `alumnes` VALUES ('AAA22','Alumno 2',111),('AAA33','Alumno 1',111),('AAA65','Alumno 3',112),('AAA87','Alumno 4',112),('BBB24','Alumno 5',144),('BBB53','Alumno 6',144),('BBB85','Alumno 7',456),('CCC23','Alumno 11',123),('CCC43','Alumno 10',457),('CCC65','Alumno 9',457),('CCC99','Carme Costa Coll',999),('DDD02','Alumno 12',123),('DDD67','Alumno 14',124),('DDD89','Alumno 13',124),('EEE34','Alumno 17',126),('EEE45','Alumno 16',125),('EEE65','Alumno 15',125),('FFF12','Alumno 20',127),('FFF32','Alumno 19',127),('FFF45','Alumno 18',126);
/*!40000 ALTER TABLE `alumnes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutors`
--

DROP TABLE IF EXISTS `tutors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tutors` (
  `codiTutor` int(11) NOT NULL,
  `nomTutor` varchar(40) NOT NULL,
  PRIMARY KEY (`codiTutor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutors`
--

LOCK TABLES `tutors` WRITE;
/*!40000 ALTER TABLE `tutors` DISABLE KEYS */;
INSERT INTO `tutors` VALUES (111,'Fernando Fernandez Guerrero'),(112,'Sofia Lombardo Guerrero'),(123,'Tutor Modificado'),(124,'Eustaquio Colmenero Romero'),(125,'Andres Calamaro Pez'),(126,'Julian Muñoz Gil'),(127,'Ambrosio Macaroni Pero'),(144,'Antonia Pajares Romuo'),(456,'Noelia Jimenez Perez'),(457,'Maria Rodriguez Pecos'),(999,'Catalina Salas Simon');
/*!40000 ALTER TABLE `tutors` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-26 19:06:01
