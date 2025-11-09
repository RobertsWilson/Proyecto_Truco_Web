-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: trucoweb
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
-- Table structure for table `bots`
--

DROP TABLE IF EXISTS `bots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bots` (
  `id_bot` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id_bot`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bots`
--

LOCK TABLES `bots` WRITE;
/*!40000 ALTER TABLE `bots` DISABLE KEYS */;
/*!40000 ALTER TABLE `bots` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partida_jugadores`
--

DROP TABLE IF EXISTS `partida_jugadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partida_jugadores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_partida` int NOT NULL,
  `id_usuario` int DEFAULT NULL,
  `id_bot` int DEFAULT NULL,
  `equipo` enum('A','B') NOT NULL,
  `puntos` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id_partida` (`id_partida`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_bot` (`id_bot`),
  CONSTRAINT `partida_jugadores_ibfk_1` FOREIGN KEY (`id_partida`) REFERENCES `partidas` (`id_partida`),
  CONSTRAINT `partida_jugadores_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `partida_jugadores_ibfk_3` FOREIGN KEY (`id_bot`) REFERENCES `bots` (`id_bot`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partida_jugadores`
--

LOCK TABLES `partida_jugadores` WRITE;
/*!40000 ALTER TABLE `partida_jugadores` DISABLE KEYS */;
/*!40000 ALTER TABLE `partida_jugadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partidas`
--

DROP TABLE IF EXISTS `partidas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partidas` (
  `id_partida` int NOT NULL AUTO_INCREMENT,
  `fecha_inicio` datetime DEFAULT CURRENT_TIMESTAMP,
  `fecha_fin` datetime DEFAULT NULL,
  `estado` enum('esperando','en_curso','finalizada') DEFAULT 'esperando',
  `modo` enum('2j','4j','vs_bot') DEFAULT '2j',
  `es_ranking` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id_partida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partidas`
--

LOCK TABLES `partidas` WRITE;
/*!40000 ALTER TABLE `partidas` DISABLE KEYS */;
/*!40000 ALTER TABLE `partidas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ranking`
--

DROP TABLE IF EXISTS `ranking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ranking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `partidas_jugadas` int DEFAULT '0',
  `partidas_ganadas` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `ranking_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ranking`
--

LOCK TABLES `ranking` WRITE;
/*!40000 ALTER TABLE `ranking` DISABLE KEYS */;
/*!40000 ALTER TABLE `ranking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `fecha_registro` datetime DEFAULT CURRENT_TIMESTAMP,
  `victorias` int NOT NULL DEFAULT '0',
  `avatar` varchar(255) DEFAULT 'img/avatar_default.jpg',
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'wilfeder44@gmail.com','$2a$12$EtxgmsQummCzcUAPTqwBSuSJD3X3THx57Ep8djE0MhAFe.A31J5hW','Wilson','2025-10-16 01:52:11',0,'img/avatar_default.jpg'),(2,'Armenio@hotmail.com','$2a$12$hbXYYdESg.cHkyFAWVP4veTzO8LXZbPSAb6VXVSxGm7Ejo1aK9X7G','Gerundio','2025-10-16 16:32:02',0,'img/avatar_default.jpg'),(6,'Prueba01@gmail.com','$2a$12$ilmX3DUL7OOIxHxGmRPmL.eerkLrqxoh2hmxrdNKUHdewMnZ3cI72','Prueba01','2025-10-20 14:32:45',0,'img/avatar_default.jpg'),(9,'Wils@gmail.com','$2a$12$o1ac2Uu0fnOcTf.Uv2o8Ce1IzimDiUu9FS7l2eXsl/jzBD/X3oxXq','Wils','2025-10-23 14:01:31',0,'img/avatar_default.jpg'),(11,'wil1234@gmail.com','$2a$12$K7Yj9.54uk01trxSsPilouoaQm8lLfzeftGSRigUzHvCS0v6SGKVW','Wilson','2025-11-03 18:21:16',0,'img/avatar_default.jpg'),(12,'wilfeder@gmail.com','$2a$12$dkgvmDJuULM5.kibIX1tEebP63T/OliEAhQ8Mba6G7Av24JrollWS','Wilson','2025-11-03 18:30:42',0,'img/avatar_default.jpg'),(15,'Wil@gmail.com123','$2a$12$UsU8cTBQaGNZBBUFIVba6.LY9iIbJ3ZKuSJlxAGZHCsPdIyecJkae','Wil','2025-11-08 21:31:59',0,'img/avatar_default.jpg');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-08 22:16:08
