CREATE DATABASE  IF NOT EXISTS `furniture_store` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `furniture_store`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: furniture_store
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL,
  `name` text NOT NULL,
  `describe` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Bàn','Bàn học và làm việc'),(2,'Ghế gaming','Ghế có thiết kế độc đáo, bắt mắt, phần da sang trọng'),(3,'Ghế công thái học','Ghế có thiết kế giúp người dùng có tư thế ngồi chuẩn'),(4,'Ghế chân quỳ','Ghế có phần chân cố định không thể xoay'),(5,'Ghế văn phòng','Phù hợp với công sở, thiết kế lịch sự ');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_sp` int NOT NULL,
  `id_kh` int NOT NULL,
  `content` varchar(255) NOT NULL,
  `vote` int NOT NULL,
  `date` date DEFAULT (curdate()),
  PRIMARY KEY (`id`),
  KEY `fk_sp_cmt_idx` (`id_sp`),
  KEY `fk_kh_cmt_idx` (`id_kh`),
  CONSTRAINT `fk_kh_cmt` FOREIGN KEY (`id_kh`) REFERENCES `khach_hang` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sp_cmt` FOREIGN KEY (`id_sp`) REFERENCES `san_pham` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (28,1,5,'Sản phẩm trên cả truyệt vời <3',5,'2023-04-20'),(29,5,5,'Sản phẩm đẹp, phù hợp với giá tiền',5,'2023-04-20'),(30,2,5,'Bàn chất lượng thấp hơn với giá tiền',3,'2023-04-20'),(31,16,5,'Sản phẩm đẹp, màu trắng trông sang',5,'2023-04-20'),(32,7,5,'Tầm tiền này mua con edra fresh ngon hơn mn nhé :)',2,'2023-04-20'),(33,20,1,'ghế lưới ngồi rất thoáng',5,'2023-04-20'),(34,16,1,' ',5,'2023-04-20'),(35,5,1,'Ghế ổn tuy nhiên hơi đắt so với chất lượng',3,'2023-04-20'),(36,5,6,' ',4,'2023-04-20'),(39,21,7,'khá mỏng manh so với giá',2,'2023-04-20'),(44,6,1,' ',4,'2023-04-20'),(45,6,3,'Cảm ơn quý khách đã ủng hộ <3',5,'2023-04-20'),(46,10,5,'Ghế khá chắc chắn, êm',5,'2023-04-20'),(47,10,3,'Cảm ơn quý khách đã ủng hộ',5,'2023-04-20'),(48,21,3,'Cảm ơn quý khách đã ủng hộ <3',5,'2023-04-20'),(49,21,5,'Ghế ổn nhưg hơi đắt so vs chất lượng',5,'2023-04-20'),(50,21,1,'Ghế lưới ngồi mùa hè khá thoáng, sẽ ủng hộ shop thêm',5,'2023-04-20'),(51,21,3,'Cảm ơn bạn đã ủng hộ shop <3',5,'2023-04-20'),(52,17,10,'Ghế rẻ hơn so với chất lượng',5,'2023-04-20'),(53,9,10,'Đẹp quá!!!',5,'2023-04-20'),(54,8,5,'Đẹp',5,'2023-04-20'),(55,11,5,' ',5,'2023-04-20'),(56,14,5,'Ghế khá ọp ẹp',2,'2023-04-20'),(57,14,4,'tệ',1,'2023-04-20'),(58,14,3,'Cảm ơn quý khách đã ủng hộ',5,'2023-04-20'),(59,5,3,'Cảm ơn quý khách đã ủng hộ',5,'2023-04-20'),(60,5,33,'màu này đẹp quá',4,'2023-04-20'),(61,21,33,'mình thấy ổn mà nhỉ mn ?',5,'2023-04-20'),(62,5,3,'<3',5,'2023-04-20'),(63,11,2,'Sản phẩm rất bắt mắt',5,'2023-04-21'),(64,7,3,'Con này ở phân khúc cao hơn bạn nhé :)',5,'2023-04-21'),(65,11,3,'cảm ơn quý khách ',5,'2023-04-21'),(66,21,30,'Ghế rẻ khá chắc chắn nhưg ship hơi lâu',4,'2023-04-21'),(67,17,30,'Ngồi khá êm',5,'2023-04-21'),(68,17,3,'Cảm ơn quý khách',5,'2023-04-21'),(69,1,3,'<3',5,'2023-04-21'),(70,5,10,'hi',5,'2023-04-22'),(71,16,3,'Cảm ơn quý khách đã đánh giá',5,'2023-04-22'),(72,21,5,' ',5,'2023-04-22'),(73,9,5,'Giao lâu quá, sản phẩm thì ok',3,'2023-04-22'),(78,1,24,'Bàn có chức năng nâng hạ rất tiện',5,'2023-04-22'),(79,7,24,'Ghế khá đắt so với chất lg :(',4,'2023-04-22'),(80,18,9,' ',5,'2023-04-22'),(81,7,3,'<3',5,'2023-04-23'),(82,5,4,'Sản phẩm rất oke nhé',5,'2023-04-23'),(83,9,3,'<3',5,'2023-04-23'),(84,2,3,'<3',5,'2023-04-23'),(85,1,3,'<3',5,'2023-04-23'),(86,5,3,'Cảm ơn quý khách đã đánh giá 5*',5,'2023-04-23'),(87,9,3,'<3',5,'2023-04-23'),(88,28,3,'<3',5,'2023-04-23'),(89,8,3,'<3',5,'2023-04-24'),(90,5,12,' ',5,'2023-04-24'),(91,28,1,'Ghế ngồi thoáng mát ',5,'2023-04-24'),(92,27,1,'Ghế ổn, chắc chắn nhưg ship lâu quá',4,'2023-04-24'),(93,5,3,'<3',5,'2023-04-25'),(95,19,3,'<3',5,'2023-04-26'),(96,12,3,'hehe',5,'2023-04-26'),(97,16,3,'<3',5,'2023-04-26'),(98,25,5,'sản phẩm khá ổn',4,'2023-04-26'),(99,21,3,'<3',5,'2023-04-27'),(100,9,3,'<3',5,'2023-04-30'),(101,23,3,'hehe',5,'2023-04-30'),(102,23,3,'<3',5,'2023-05-02'),(103,21,3,' ',5,'2023-05-02'),(104,5,3,'<3',5,'2023-05-02'),(105,17,3,'<3',5,'2023-05-02'),(106,5,3,' ',5,'2023-05-03'),(107,27,5,'Ghế oke lăms ',5,'2023-05-03'),(108,3,5,'Bàn rất sang',5,'2023-05-03'),(109,5,21,'sanr pham ok',4,'2023-05-05'),(110,5,3,'<3',5,'2023-05-05'),(111,5,38,'<#',5,'2023-05-05'),(112,6,3,'<3',5,'2023-05-07'),(113,8,3,'<3',1,'2023-05-07'),(115,2,3,'<3',5,'2023-05-11'),(116,28,3,'<3',5,'2023-05-11'),(117,23,3,'? :)',5,'2023-05-12'),(118,5,3,'<3',5,'2023-05-14');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gio_hang`
--

DROP TABLE IF EXISTS `gio_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gio_hang` (
  `id` int NOT NULL,
  `id_kh` int NOT NULL,
  `id_sp` int NOT NULL,
  `so_luong` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_kh_gio` (`id_kh`),
  KEY `fk_sp_gio` (`id_sp`),
  CONSTRAINT `fk_kh_gio` FOREIGN KEY (`id_kh`) REFERENCES `khach_hang` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sp_gio` FOREIGN KEY (`id_sp`) REFERENCES `san_pham` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gio_hang`
--

LOCK TABLES `gio_hang` WRITE;
/*!40000 ALTER TABLE `gio_hang` DISABLE KEYS */;
INSERT INTO `gio_hang` VALUES (10,17,2,2),(11,17,12,1),(12,17,5,1),(21,19,7,5),(22,24,3,3),(30,30,5,3),(31,30,3,1),(32,30,12,2),(36,31,11,10),(37,31,5,2),(43,7,5,2),(53,32,2,1),(54,32,5,2),(61,34,4,2),(73,33,1,3),(86,20,1,2),(104,5,12,1),(111,3,12,4),(124,12,6,1),(140,7,7,2),(142,10,17,1),(146,16,9,1),(147,9,18,1),(151,4,19,1),(153,3,1,2),(154,3,2,1),(155,3,7,1),(156,3,9,2);
/*!40000 ALTER TABLE `gio_hang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khach_hang`
--

DROP TABLE IF EXISTS `khach_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khach_hang` (
  `id` int NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL,
  `email` varchar(45) NOT NULL,
  `ten` text,
  `sdt` varchar(10) DEFAULT NULL,
  `diachi` text,
  `role` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khach_hang`
--

LOCK TABLES `khach_hang` WRITE;
/*!40000 ALTER TABLE `khach_hang` DISABLE KEYS */;
INSERT INTO `khach_hang` VALUES (1,'acc1','123','demo02@gmail.com','Phu Le','0158585789','Hà Nội',1),(2,'acc2','123','','Vũ Văn Dũng','0123456789',NULL,1),(3,'admin','123','levankienyk02@gmail.com','ADMIN','0961016881','Thôn Yên Khê, Yên Thường, Gia Lâm, Hà Nội',2),(4,'acc3','123','siu','Chú bé loắt choắt','0674125487','khu 2 , hoàng cương, thanh ba, phú thọ',1),(5,'sa','123','Levankienyk02@gmail.com','Nguyễn Văn Chiến','0961016881','Hà Nội',1),(6,'sa2','123','chuoismilenun@gmail.com','Long Đặng','012345678','Hà Nội',1),(7,'sa3','123','chuoismilenun@gmail.com','Hùng Phạm','0961016881','Hà Nội',1),(8,'sa4','123','Levankienyk02@gmail.com','','','',1),(9,'root','root','chuoismilenun@gmail.com','','0345555544','92 Tôn Đức Thắng, Đống Đa, HN',1),(10,'sa5','123','','Khá Ngô','0512364785','Số 192, Đình bảng, Từ sơn ,Bắc Ninh',1),(11,'sa6','123','Levankienyk02@gmail.com','','0961016881','Hà Nội',1),(12,'sa7','123','','','0654789357','Hà nội 2',1),(13,'root2','123','Levankienyk02@gmail.com','','','',1),(14,'sa9','123','Levankienyk02@gmail.com','','','',1),(15,'sa10','123','chuoismilenun@gmail.com','','','',1),(16,'sa11','123','chuoismilenun@gmail.com','','','',1),(17,'B20DCCN355','18082002','Levankienyk02@gmail.com','','','',1),(18,'sa12','123','Levankienyk02@gmail.com','','','',1),(19,'12','12','12','','','',1),(20,'sa13','123','123','','0961016881','Hà Nội',1),(21,'sa14','123','123','','0961016881','92 Tôn Đức Thắng, Đống Đa, HN',1),(22,'1','123','','','','',1),(23,'2','123','','','','',1),(24,'admin1','1234','','Ngọc Lê','0343039144','Thôn Yên Khê, Yên Thường, Gia Lâm, Hà Nội',1),(25,'admi','123','','','','',1),(26,'admin2','1234','chuoismilenun4@gmail.com','','','',1),(27,'admin3','123','chuoismilenun4@gmail.com','','','',1),(28,'admin4','123','Levankienyk02@gmail.com','','','',1),(29,'admin5','123','chuoismilenun4@gmail.com','','','',1),(30,'admin7','123','chuoismilenun4@gmail.com','Uchiha Itabu','012345678','Hà Nội',1),(31,'sa15','123','','Test User 8','0961016881','Hà Nội',1),(32,'sa111','123','Levankienyk02@gmail.com','','','',1),(33,'sa1111','123','Levankienyk02@gmail.com','LVK Shop test','0123456789','Hà nội 2',1),(34,'sa222','123','Levankienyk02@gmail.com','','0961016881','Hà Nội',1),(35,'sa20','123','123','','','',1),(36,'sa1234','123','chuoismilenun@gmail.com','Nguyễn Văn Dũng','0123459876','Khu 2 Hoàng Cương, Thanh Ba, Phú Thọ',1),(37,'admin13','12','12','','','',1),(38,'sa21','123','','','0345555544','Da Nang',1);
/*!40000 ALTER TABLE `khach_hang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL,
  `id_kh` int NOT NULL,
  `thoi_gian` date NOT NULL DEFAULT (curdate()),
  `trang_thai` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'chờ xác nhận',
  `tong_tien` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_kh_order_idx` (`id_kh`),
  CONSTRAINT `fk_kh_order` FOREIGN KEY (`id_kh`) REFERENCES `khach_hang` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,3,'2023-04-13','Hoàn thành',17660000),(2,3,'2023-04-13','Hoàn thành',7090000),(4,3,'2023-04-13','Hoàn thành',11270000),(5,5,'2023-04-13','Chờ xác nhận',15870000),(7,3,'2023-04-13','Đã xác nhận',7090000),(8,1,'2023-04-13','Chờ xác nhận',22450000),(9,3,'2023-04-13','Hoàn thành',8780000),(11,3,'2023-04-13','Đã xác nhận',7680000),(12,3,'2023-04-13','Chờ xác nhận',16360000),(13,1,'2023-04-13','Chờ xác nhận',19998),(14,3,'2023-04-15','Đang giao hàng',11970000),(15,3,'2023-04-15','Đang giao hàng',45300000),(16,36,'2023-04-15','Đã xác nhận',5690000),(17,12,'2023-04-15','Đã xác nhận',2700000),(18,12,'2023-04-15','Chờ xác nhận',11970000),(20,3,'2023-04-16','Chờ xác nhận',8780000),(21,5,'2023-04-17','Hoàn thành',6890000),(22,3,'2023-04-18','Đang giao hàng',1560000),(24,3,'2023-04-19','Đang giao hàng',550000),(25,1,'2023-04-19','Đã xác nhận',2798000),(27,1,'2023-04-19','Chờ xác nhận',400000),(28,3,'2023-04-20','Đã xác nhận',3289000),(29,2,'2023-04-20','Chờ xác nhận',3390000),(30,5,'2023-04-20','Chờ xác nhận',3990000),(31,11,'2023-04-20','Chờ xác nhận',3990000),(32,5,'2023-04-20','Chờ xác nhận',3390000),(33,5,'2023-04-20','Chờ xác nhận',800000),(34,6,'2023-04-20','Đã xác nhận',4590000),(35,7,'2023-04-20','Chờ xác nhận',790000),(36,1,'2023-04-20','Chờ xác nhận',4390000),(37,5,'2023-04-20','Chờ xác nhận',3690000),(38,5,'2023-04-20','Chờ xác nhận',790000),(39,1,'2023-04-20','Đang giao hàng',790000),(40,10,'2023-04-20','Chờ xác nhận',999000),(41,10,'2023-04-20','Chờ xác nhận',4390000),(42,5,'2023-04-20','Chờ xác nhận',2590000),(43,5,'2023-04-20','Chờ xác nhận',699000),(44,4,'2023-04-20','Chờ xác nhận',699000),(45,3,'2023-04-20','Đã xác nhận',1040000),(46,33,'2023-04-20','Chờ xác nhận',4590000),(47,33,'2023-04-20','Chờ xác nhận',790000),(48,30,'2023-04-21','Chờ xác nhận',790000),(49,30,'2023-04-21','Đã xác nhận',1998000),(50,10,'2023-04-22','Chờ xác nhận',4590000),(51,5,'2023-04-22','Đang giao hàng',7090000),(52,24,'2023-04-22','Đang giao hàng',14070000),(53,9,'2023-04-22','Chờ xác nhận',520000),(54,4,'2023-04-23','Hoàn thành',9990000),(55,3,'2023-04-23','Đã xác nhận',2990000),(56,1,'2023-04-24','Đã xác nhận',15850000),(57,3,'2023-04-26','Đã xác nhận',8700000),(58,5,'2023-04-26','Chờ xác nhận',4250000),(59,3,'2023-04-27','Đã xác nhận',4390000),(60,3,'2023-05-02','Hoàn thành',19150000),(61,1,'2023-05-02','Đã xác nhận',22530000),(62,3,'2023-05-03','Chờ xác nhận',2990000),(63,5,'2023-05-03','Đã xác nhận',12150000),(64,21,'2023-05-05','Đã xác nhận',13770000),(65,38,'2023-05-05','Hoàn thành',17620000),(66,3,'2023-05-07','Đã xác nhận',13840000),(67,5,'2023-05-07','Chờ xác nhận',15660000);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_sp`
--

DROP TABLE IF EXISTS `order_sp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_sp` (
  `id` int NOT NULL,
  `id_order` int NOT NULL,
  `id_sp` int NOT NULL,
  `so_luong` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_order_sp` (`id_order`),
  KEY `fk_sp_order_sp_idx` (`id_sp`),
  CONSTRAINT `fk_order_order_sp` FOREIGN KEY (`id_order`) REFERENCES `order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sp_order_sp` FOREIGN KEY (`id_sp`) REFERENCES `san_pham` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_sp`
--

LOCK TABLES `order_sp` WRITE;
/*!40000 ALTER TABLE `order_sp` DISABLE KEYS */;
INSERT INTO `order_sp` VALUES (1,1,9,3),(2,1,7,1),(3,2,6,1),(4,2,3,1),(7,4,11,2),(8,4,7,1),(9,5,5,1),(10,5,7,2),(11,5,2,1),(13,7,3,1),(14,7,9,1),(15,8,6,2),(16,8,5,2),(17,8,7,1),(18,9,9,2),(20,11,10,1),(21,11,1,1),(22,12,6,1),(23,12,1,3),(24,13,12,2),(25,14,1,3),(26,15,5,4),(27,15,7,6),(28,16,11,1),(29,16,2,1),(30,17,3,1),(31,18,10,2),(32,18,5,1),(35,20,9,2),(36,21,5,1),(37,21,2,1),(38,22,18,3),(40,24,19,1),(41,25,16,1),(42,25,17,2),(44,27,20,1),(45,28,14,1),(46,28,8,1),(47,29,11,1),(48,30,1,1),(49,31,1,1),(50,32,11,1),(51,33,16,1),(52,34,5,1),(53,35,21,1),(54,36,6,1),(55,37,10,1),(56,38,21,1),(57,39,21,1),(58,40,17,1),(59,41,9,1),(60,42,8,1),(61,43,14,1),(62,44,14,1),(63,45,18,2),(64,46,5,1),(65,47,21,1),(66,48,21,1),(67,49,17,2),(68,50,5,1),(69,51,9,1),(70,51,3,1),(71,52,7,1),(72,52,1,2),(73,52,16,2),(74,53,18,1),(75,54,5,1),(76,54,3,2),(77,55,24,1),(78,56,27,1),(79,56,28,1),(80,57,25,1),(81,57,26,1),(82,58,25,1),(83,59,6,1),(84,60,9,1),(85,60,8,1),(86,60,5,2),(87,60,24,1),(88,61,11,1),(89,61,28,1),(90,61,26,1),(91,61,15,2),(92,61,21,1),(93,62,24,1),(94,63,26,1),(95,63,27,2),(96,64,5,3),(97,65,27,1),(98,65,5,3),(99,66,21,1),(100,66,20,1),(101,66,4,3),(102,66,25,1),(103,67,10,1),(104,67,1,3);
/*!40000 ALTER TABLE `order_sp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `san_pham`
--

DROP TABLE IF EXISTS `san_pham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `san_pham` (
  `id` int NOT NULL,
  `anh` text NOT NULL,
  `ten` text NOT NULL,
  `gia_cu` int DEFAULT NULL,
  `gia_moi` int NOT NULL,
  `yeu_thich` tinyint DEFAULT '0',
  `so_luong` int NOT NULL DEFAULT '100',
  `da_ban` int NOT NULL DEFAULT '0',
  `info` text,
  `categoryID` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sp_category_idx` (`categoryID`),
  CONSTRAINT `fk_ct_sp` FOREIGN KEY (`categoryID`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `san_pham`
--

LOCK TABLES `san_pham` WRITE;
/*!40000 ALTER TABLE `san_pham` DISABLE KEYS */;
INSERT INTO `san_pham` VALUES (1,'product1.jpg','Bàn điện nâng hạ công thái học E-Dra ELT1460',0,3990000,0,100,20,'<p><span style=\"color: rgb(224, 62, 45); font-size: 14pt;\"><strong>B&agrave;n điện n&acirc;ng hạ E-Dra ELT1460 &ndash; Gaming MasterE-DRA ELT1460 Gaming Master</strong></span></p>\r\n<p><span style=\"font-size: 12pt; color: rgb(241, 196, 15);\">Mặt b&agrave;n hai mảnh</span></p>\r\n<p><span style=\"font-size: 12pt; color: rgb(241, 196, 15);\">C&oacute; thể điều chỉnh độ cao bằng điện 74-119cm</span></p>\r\n<p><span style=\"font-size: 12pt; color: rgb(241, 196, 15);\">Ch&acirc;n b&agrave;n hợp kim nh&ocirc;m&nbsp;</span></p>\r\n<p><span style=\"font-size: 12pt; color: rgb(241, 196, 15);\">Bộ nhớ cao th&ocirc;ng minh</span></p>\r\n<p><span style=\"font-size: 12pt; color: rgb(241, 196, 15);\">Dễ d&agrave;ng điều chỉnh độ cao thấp của b&agrave;n cho ph&ugrave; hợp với c&ocirc;ng năng sử dụng</span></p>\r\n<p><span style=\"font-size: 12pt; color: rgb(241, 196, 15);\">Dừng lại trong trường hợp bị cản trở</span></p>\r\n<p><span style=\"font-size: 12pt; color: rgb(241, 196, 15);\">Mặt b&agrave;n bằng sợi carbon P2PB d&agrave;y 15mm</span></p>\r\n<p><span style=\"font-size: 12pt; color: rgb(241, 196, 15);\">M&agrave;u sắc: Đen</span></p>\r\n<p><span style=\"font-size: 12pt; color: rgb(241, 196, 15);\">C&oacute; sẵn B&agrave;n di chuột (80*30*0,2cm)</span></p>',1),(2,'product2.png','Bàn ikea 1 hộc gỗ MDF 1600*600*750',2900000,2300000,0,100,26,'<p>B&agrave;n chơi game, l&agrave;m việc phong c&aacute;ch IKEA K&iacute;ch thước: 1600*600*750mm</p>\r\n<p>Loại 1 hộc tủ</p>\r\n<p>Chất liệu: Gỗ MDF l&otilde;i n&acirc;u</p>\r\n<p>Nhận đặt theo k&iacute;ch thước y&ecirc;u cầu</p>',1),(3,'product3.jpg','Bàn Ikea 2 hộc gỗ MDF 180*60*75',3500000,2700000,0,100,15,'<p>B&agrave;n chơi game, l&agrave;m việc phong c&aacute;ch IKEA</p>\r\n<p>K&iacute;ch thước: 1800*600*750mm</p>\r\n<p>Chất liệu: Gỗ MDF l&otilde;i n&acirc;u</p>\r\n<p>Nhận đặt theo k&iacute;ch thước y&ecirc;u cầu</p>',1),(4,'product4.jpg','Bàn ikea 2 hộc gỗ MDF 1800*600*750 màu trắng viền đen',3500000,2800000,1,100,9,'<p>B&agrave;n chơi game, l&agrave;m việc phong c&aacute;ch IKEA</p>\r\n<p>K&iacute;ch thước: 1800*600*750mm</p>\r\n<p>Chất liệu: Gỗ MDF l&otilde;i n&acirc;u</p>\r\n<p>Nhận đặt theo k&iacute;ch thước y&ecirc;u cầu</p>',1),(5,'product5.jpg','Ghế chơi game E-dra Hunter EGC206 Sierra Blue',5590000,4590000,1,100,30,'<p>Hunter Gaming Chair &ndash; EGC 206&nbsp;Ghế si&ecirc;u cao cấp d&agrave;nh cho Game</p>\r\n<p>Chất liệu: Da cao cấp PU dễ d&agrave;ng bảo quản.</p>\r\n<p>Đệm đ&uacute;c nguy&ecirc;n khối mật độ cao</p>\r\n<p>K&ecirc; tay 4D.C&oacute; n&uacute;m vặn điều chỉnh độ nh&ocirc; của lưng ghế G&oacute;c đứng: 92&deg; &plusmn; 2&deg;</p>\r\n<p>G&oacute;c nằm max: 180&deg;</p>\r\n<p>G&oacute;c quay điểm tựa tay: 360&deg;</p>\r\n<p>Độ cao của điểm tựa tay: 70 &plusmn; 5mm</p>\r\n<p>Đường k&iacute;nh ch&acirc;n: 70cm</p>\r\n<p>Trọng tải theo g&oacute;c đứng: 150kg</p>\r\n<p>Khung ch&acirc;n: Khung, ch&acirc;n nh&ocirc;m, b&aacute;nh xe được thiết kế ko g&acirc;y tiếng ồn.</p>\r\n<p>Gầm Frog(ch&acirc;n ếch) đem lại cảm gi&aacute;c ngồi thoải m&aacute;i. Piston thủy lực Class 4</p>\r\n<p>M&agrave;u sắc: Sierra Blue</p>\r\n<p>Bảo h&agrave;nh 12 th&aacute;ng ch&iacute;nh h&atilde;ng</p>',2),(6,'product7.jpg','Ghế chơi game E-dra Fresh EGC230 Plus xám',4990000,4390000,0,100,6,'<p>Ghế cao cấp d&agrave;nh cho game thủ&nbsp;</p>\r\n<p>Chất liệu: Fabric</p>\r\n<p>Đệm cao su: nguy&ecirc;n khối</p>\r\n<p>K&ecirc; tay: 4D</p>\r\n<p>Trụ thủy lực: Class 4</p>\r\n<p>G&oacute;c đứng: 90&deg; &plusmn; 2&deg;</p>\r\n<p>G&oacute;c nằm max: 160&deg;</p>\r\n<p>G&oacute;c quay điểm tựa tay: 360&deg;</p>\r\n<p>Độ cao của điểm tựa tay: 70 &plusmn; 5mm</p>\r\n<p>Đường k&iacute;nh ch&acirc;n: 70cm</p>\r\n<p>Khung-ch&acirc;n: Khung kim loại, ch&acirc;n th&eacute;p chịu lực cao&nbsp;</p>\r\n<p>B&aacute;nh xe được thiết kế ko g&acirc;y tiếng ồn.</p>\r\n<p>Trọng tải theo g&oacute;c đứng: 150kg</p>\r\n<p>Trang bị sẵn bộ gối cao cấp Memory Foam</p>\r\n<p>K&iacute;ch thước: 85*75*37cm</p>\r\n<p>M&agrave;u sắc: M&agrave;u x&aacute;m, hồng, v&agrave;ng v&agrave; xanh dương</p>\r\n<p>Bảo h&agrave;nh ch&iacute;nh h&atilde;ng 12 th&aacute;ng</p>',2),(7,'product8.jpg','ghế chơi game e-dra iris egc228 – black',4790000,4490000,1,100,26,'<p>T&ecirc;n model: EGC228</p>\r\n<p>Grey</p>\r\n<p>M&agrave;u sắc: X&aacute;m</p>\r\n<p>Thương hiệu: E-dra</p>\r\n<p>Chất liệu: Vải bọc Fabric</p>\r\n<p>G&oacute;c đứng: 92&deg; &plusmn; 2&deg;</p>\r\n<p>G&oacute;c nằm max: 180&deg;</p>\r\n<p>G&oacute;c quay điểm tựa tay: 360&deg;</p>\r\n<p>Độ cao tỳ tay 4D: 70 &plusmn; 5mm</p>\r\n<p>Đường k&iacute;nh ch&acirc;n: 70cm</p>\r\n<p>Trọng tải theo g&oacute;c đứng: 150kg</p>\r\n<p>Khung ch&acirc;n: Kim loại</p>\r\n<p>K&iacute;ch thước: 86x70x33 cm</p>',2),(8,'product9.jpg','Ghế chơi game E-dra Midnight EGC205 – Midnight',2990000,2590000,0,100,6,'<p>E-Dra Midnight EGC205 l&agrave; d&ograve;ng ghế game ở ph&acirc;n kh&uacute;c thấp b&aacute;n chạy nhất h&atilde;ng.</p>\r\n<p>Kết cấu khung ch&acirc;n chắc chắn c&ugrave;ng nệm ngồi &ecirc;m &aacute;i, d&agrave;y dặn, m&agrave;u sắc thu h&uacute;t l&agrave; điểm nhấn của ghế EGC205 m&agrave;u Midnight.</p>\r\n<p>Ghế được bảo h&agrave;nh ch&iacute;nh h&atilde;ng 12 th&aacute;ng.</p>',2),(9,'product10.jpg','ghế chơi game e-dra spider egc226',4690000,4390000,0,100,12,'<p>Model: Spider EGC226</p>\r\n<p>Điểm kh&aacute;c bieeth: M&ocirc; phỏng dựa theo Spider-Man</p>\r\n<p>Chất liệu: PVC -Đệm cao su nguy&ecirc;n khối</p>\r\n<p>K&ecirc; tay 4D -Trụ thủy lực Class 4</p>\r\n<p>G&oacute;c đứng: 90&deg; &plusmn; 2&deg;</p>\r\n<p>G&oacute;c nằm max: 160&deg;</p>\r\n<p>G&oacute;c quay điểm tựa tay: 360&deg;</p>\r\n<p>Độ cao của điểm tựa tay: 70 &plusmn; 5mm</p>\r\n<p>Đường k&iacute;nh ch&acirc;n: 70cm</p>\r\n<p>Khung-ch&acirc;n: Khung kim loại</p>\r\n<p>Trọng tải theo g&oacute;c đứng: 150kg</p>',2),(10,'product11.png','ghế e-dra ares egc207 – black white',3890000,3690000,1,100,7,'<p>Thiết kế đa dạng nhiều m&agrave;u sắc</p>\r\n<p>Đệm ghế bọt kh&iacute;, ngồi &ecirc;m hơn</p>\r\n<p>Tỳ tay thiết kế 2D tiện lợi</p>\r\n<p>Trụ thủy lực chịu trọng tải tốt</p>\r\n<p>G&oacute;c đứng: 92&deg; &plusmn; 2&deg;</p>\r\n<p>G&oacute;c nằm max: 180&deg;</p>\r\n<p>Trọng tải theo g&oacute;c đứng: 80kg</p>',2),(11,'product12.jpg','Ghế game Warrior Raider Series – WGC206 Plus White/Pink\"',3690000,3390000,0,15,8,'<p>Ghế cao cấp d&agrave;nh cho Game</p>\r\n<p>Chất liệu: Da cao cấp PU dễ d&agrave;ng lau ch&ugrave;i sạch sẽ, tựa lưng dạng lưới chịu lực cao cấp.</p>\r\n<p>G&oacute;c đứng: 92&deg; &plusmn; 2&deg;</p>\r\n<p>G&oacute;c nằm max: 180&deg;</p>\r\n<p>G&oacute;c quay điểm tựa tay: 360&deg;</p>\r\n<p>Độ cao của điểm tựa tay: 70 &plusmn; 5mm</p>\r\n<p>Đường k&iacute;nh ch&acirc;n: 70cm</p>\r\n<p>Trọng tải theo g&oacute;c đứng: 150kg</p>\r\n<p>Tay 2D</p>\r\n<p>Khung ch&acirc;n: hung, ch&acirc;n nhựa, b&aacute;nh xe được thiết kế ko g&acirc;y tiếng ồn.</p>\r\n<p>K&iacute;ch thước: 83x65x32cm</p>\r\n<p>M&agrave;u sắc: Hồng</p>',2),(12,'facebookimg.png','Test product',10000,9999,0,4,4,'<p>Đ&acirc;y l&agrave; th&ocirc;ng tin sản phẩm</p>',5),(13,'muc-tieu1.jpg','Siuuu2',1,1,0,1,0,'<p><img src=\"https://znews-photo.zingcdn.me/w660/Uploaded/lce_jwqqc/2023_01_11/FF4lj5_XIAAPCn1_1.jpg\" alt=\"Cuộc sống hiện tại của hiện tượng meme nổi tiếng một thời - Đời sống\" width=\"200\" height=\"200\"></p>',4),(14,'product13.jpg','Ghế Văn Phòng Lưng Xương Cá Tựa Đầu Chân Xoay Gx410',900000,699000,0,100,4,'<p>Lưng nhựa, bọc lưới, c&oacute; gật g&ugrave;</p>\r\n<p>Tay nhựa cong cố định</p>\r\n<p>Đệm m&uacute;t bọc lưới</p>\r\n<p>Piston thủy lực</p>\r\n<p>Ch&acirc;n cao mạ Crom</p>\r\n<p>Bao gồm gối tựa đầu bọc lưới B&aacute;nh xe nhựa bọc cao su</p>',5),(15,'product14.jpg','Ghế Lưng Lưới Cao Chân Xoay GX206',0,950000,0,100,4,'Lưng cao, có gật gù\r\nThân ghế bằng thép mạ crom\r\nTựa lưng lưới\r\nPiston thủy lực',5),(16,'product15.jpg','Ghế Văn Phòng Tay Gập Chân Xoay GHE_VP682',0,800000,0,50,7,'<p>Lưng nhựa, bọc lưới</p>\r\n<p>Tay nhựa c&oacute; thể điều chỉnh</p>\r\n<p>Đệm m&uacute;t bọc lưới</p>\r\n<p>Piston thủy lực</p>\r\n<p>Ch&acirc;n nhựa PP cao cấp</p>\r\n<p>B&aacute;nh xe nhựa bọc cao su</p>',5),(17,'product16.jpg','Ghế Văn Phòng Cao Cấp TMS04',1000000,999000,0,20,5,'<p>Khung ghế l&agrave;m từ nhựa PP cao cấp</p>\r\n<p>Tựa lưng lưới đ&agrave;n hồi tốt</p>\r\n<p>Tay nhựa gập điều chỉnh được l&ecirc;n xuống</p>\r\n<p>Đệm m&uacute;t bọc lưới</p>\r\n<p>Pitton thủy lực&nbsp;</p>\r\n<p>Ch&acirc;n sao nhựa đ&uacute;c nguy&ecirc;n khối</p>\r\n<p>B&aacute;nh xe cao su</p>',5),(18,'product17.jpg','Ghế Văn Phòng Lưng Quạt Chân Xoay GHE_VP405',600000,520000,0,100,12,'<p>Lưng nhựa, bọc lưới, c&oacute; gật g&ugrave;</p>\r\n<p>Tay nhựa cong cố định</p>\r\n<p>Đệm m&uacute;t bọc lưới</p>\r\n<p>Piston thủy lực</p>\r\n<p>Ch&acirc;n cao mạ Crom</p>\r\n<p>B&aacute;nh xe nhựa bọc cao su</p>',5),(19,'product18.jpg','Ghế Lưng Cao Chân Quỳ CQ410',0,550000,0,1,1,'<p>Chiều cao mặt ngồi 45 cm</p>\r\n<p>Ngang 48 cm</p>\r\n<p>S&acirc;u 49 cm</p>\r\n<p>Cao 108 cm Lưng cao, khung sắt mạ crom</p>\r\n<p>Tay mạ crom ốp nhựa</p>\r\n<p>Ch&acirc;n mạ crom đ&uacute;c nguy&ecirc;n khối</p>',4),(20,'product19.jpg','Ghế Văn Phòng Lưng Lưới Chân Quỳ GHE_VP4001',0,400000,0,10,4,'<p><img src=\"https://media3.giphy.com/media/g7GKcSzwQfugw/giphy.gif\" alt=\"Rick-roll GIFs - Get the best GIF on GIPHY\"></p>',4),(21,'product20.jpg','Ghế Lưng Lưới Cao Chân Quỳ CQ206',0,790000,0,10,7,'<p>Chiều cao mặt ngồi 44 cm</p>\r\n<p>Ngang 58 cm</p>\r\n<p>S&acirc;u 48 cm</p>\r\n<p>Cao 107 cm</p>',4),(23,'images.jpg','d',0,1,0,1,0,'<div style=\"left: 0px; width: 100%; height: 0px; position: relative; padding-bottom: 56.25%; max-width: 800px; max-height: 600px;\" data-ephox-embed-iri=\"https://www.youtube.com/watch?v=LM-ljMCEF3k&amp;ab_channel=Kietisme\"><iframe style=\"top: 0; left: 0; width: 100%; height: 100%; position: absolute; border: 0;\" src=\"https://www.youtube.com/embed/LM-ljMCEF3k?rel=0\" scrolling=\"no\" allow=\"accelerometer; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share;\" allowfullscreen=\"allowfullscreen\"></iframe></div>\r\n<p>&nbsp;</p>',1),(24,'product21.jpg','GHẾ CÔNG THÁI HỌC SMA SPIDER 02 - SP02',4600000,2990000,0,10,3,'<p><strong>Tựa đầu:</strong>&nbsp;thiết kế 2D, N&acirc;ng hạ 5-7 cm, ngửa v&agrave; cụp</p>\r\n<p><strong>Tựa lưng:</strong>&nbsp;Thiết kế h&igrave;nh chữ S theo cột sống gi&uacute;p &ocirc;m s&aacute;t lưng</p>\r\n<p><strong>K&ecirc; tay:&nbsp;</strong>Thiết kế 2D điều chỉnh trượt ra v&agrave;o, n&acirc;ng hạ 8 cm</p>\r\n<p><strong>Ngả lưng:</strong>&nbsp;ngả lưng l&ecirc;n tới 135 độ, kh&oacute;a đ&oacute;ng cố định 4 vị tr&iacute;</p>\r\n<p><strong>M&acirc;m ngồi:</strong>&nbsp;trượt m&acirc;m 5cm</p>\r\n<p><strong>Piston:</strong>&nbsp;Piston 3 cấp độ đạt chứng nhận SGS an to&agrave;n, chống ch&aacute;y nổ tốt</p>',3),(25,'product22.jpg','GHẾ CÔNG THÁI HỌC SMA LUMBAR 03 FULL LƯỚI',6250000,4250000,0,10,3,'<table style=\"border-collapse: collapse; width: 700.713px; border-width: 1px;\" border=\"1\">\r\n<tbody>\r\n<tr>\r\n<td style=\"width: 83px; border-width: 1px;\">Sản phẩm</td>\r\n<td style=\"width: 289.712px; border-width: 1px;\">Ghế c&ocirc;ng th&aacute;i học SMA Lumbar&nbsp; 03</td>\r\n</tr>\r\n<tr>\r\n<td style=\"width: 83px; border-width: 1px;\">Tựa đầu</td>\r\n<td style=\"width: 289.712px; border-width: 1px;\">Thiết kế 3D điều chỉnh n&acirc;ng hạ, cụp ngửa 45 độ, tịnh tiến trước sau 3cm</td>\r\n</tr>\r\n<tr>\r\n<td style=\"width: 83px; border-width: 1px;\">Tựa lưng</td>\r\n<td style=\"width: 289.712px; border-width: 1px;\">Lưng đ&ocirc;i, điều chỉnh 2D n&acirc;ng hạ 5 nấc, điều chỉnh độ nghi&ecirc;ng 5 cấp ph&ugrave; hợp với thể trạng của từng người</td>\r\n</tr>\r\n<tr>\r\n<td style=\"width: 83px; border-width: 1px;\">Tay vịn</td>\r\n<td style=\"width: 289.712px; border-width: 1px;\">3D n&acirc;ng hạ, trượt trước sau, xoay tr&aacute;i phải</td>\r\n</tr>\r\n<tr>\r\n<td style=\"width: 83px; border-width: 1px;\">Ngả lưng</td>\r\n<td style=\"width: 289.712px; border-width: 1px;\">Ghế c&ocirc;ng th&aacute;i học SMA Lumbar 03 ngả lưng, kh&oacute;a cố định 3 vị&nbsp; tr&iacute; 90-120 -135 độ</td>\r\n</tr>\r\n<tr>\r\n<td style=\"width: 83px; border-width: 1px;\">Nệm ghế</td>\r\n<td style=\"width: 289.712px; border-width: 1px;\">K&iacute;ch thước: 50x50 cm, được bọc đệm m&uacute;t ở phần m&eacute;p ngồi, M&acirc;m ghế trượt ra v&agrave;o 5 cm.</td>\r\n</tr>\r\n<tr>\r\n<td style=\"width: 83px; border-width: 1px;\">Chất liệu</td>\r\n<td style=\"width: 289.712px; border-width: 1px;\">Ghế c&ocirc;ng th&aacute;i học SMA Lumbar 03 được sử dụng chất liệu lưới Matrex Mỹ</td>\r\n</tr>\r\n<tr>\r\n<td style=\"width: 83px; border-width: 1px;\">Piston</td>\r\n<td style=\"width: 289.712px; border-width: 1px;\">Piston 3 cấp độ đạt chứng nhận SGS an to&agrave;n, chống ch&aacute;y nổ tốt</td>\r\n</tr>\r\n<tr>\r\n<td style=\"width: 83px; border-width: 1px;\">Khung ghế</td>\r\n<td style=\"width: 289.712px; border-width: 1px;\">Khung ghế được sử dụng chất liệu nhựa PP đạt ti&ecirc;u chuẩn nhựa văn ph&ograve;ng quốc tế&nbsp;</td>\r\n</tr>\r\n</tbody>\r\n</table>',3),(26,'product23.webp','GHẾ CÔNG THÁI HỌC SMA LUMBAR 01 TRẮNG',5250000,4450000,0,10,3,'<table style=\"border-collapse: collapse; width: 700.713px;\" border=\"1\">\r\n<tbody>\r\n<tr>\r\n<td>Sản phẩm</td>\r\n<td>Ghế c&ocirc;ng th&aacute;i học SMA Lumbar&nbsp; 01 Trắng</td>\r\n</tr>\r\n<tr>\r\n<td>Tựa đầu</td>\r\n<td>Thiết kế 3D điều chỉnh n&acirc;ng hạ, cụp ngửa 45 độ, tịnh tiến trước sau 3cm</td>\r\n</tr>\r\n<tr>\r\n<td>Tựa lưng</td>\r\n<td>Lưng đ&ocirc;i, điều chỉnh 2D n&acirc;ng hạ 5 nấc, điều chỉnh độ nghi&ecirc;ng 5 cấp ph&ugrave; hợp với thể trạng của từng người</td>\r\n</tr>\r\n<tr>\r\n<td>Tay vịn</td>\r\n<td>3D n&acirc;ng hạ, trượt trước sau, xoay tr&aacute;i phải</td>\r\n</tr>\r\n<tr>\r\n<td>Ngả lưng</td>\r\n<td>Ghế c&ocirc;ng th&aacute;i học SMA Lumbar 03 ngả lưng, kh&oacute;a cố định 3 vị&nbsp; tr&iacute; 90-120 -135 độ</td>\r\n</tr>\r\n<tr>\r\n<td>Nệm ghế</td>\r\n<td>M&acirc;m ngồi đệm m&uacute;t với mật độ bọt biển cao, chống sụt l&uacute;n.</td>\r\n</tr>\r\n<tr>\r\n<td>Chất liệu</td>\r\n<td>Ghế c&ocirc;ng th&aacute;i học SMA Lumbar 03 được sử dụng chất liệu lưới Matrex Mỹ</td>\r\n</tr>\r\n</tbody>\r\n</table>',3),(27,'product24.jpg','GHẾ CÔNG THÁI HỌC SMA BUTTERFLY - LƯỚI XÁM - B01',5590000,3850000,0,10,4,'<table style=\"border-collapse: collapse; width: 700.713px;\" border=\"1\">\r\n<tbody>\r\n<tr>\r\n<td>Sản phẩm</td>\r\n<td>&nbsp;Ghế c&ocirc;ng th&aacute;i học Butterfly 01&nbsp;</td>\r\n</tr>\r\n<tr>\r\n<td>Tựa đầu</td>\r\n<td>&nbsp;Được thiết kế 3D, Ngả l&ecirc;n, cụp xuống 45 độ n&acirc;ng hạ chất liệu lưới tho&aacute;ng kh&iacute;</td>\r\n</tr>\r\n<tr>\r\n<td>Tựa lưng</td>\r\n<td>K&iacute;ch thước: 49x60cm, lưng lưới, thiết kế đi&ecirc;u chỉnh thắt lưng l&ecirc;n xuống 5cm&nbsp;</td>\r\n</tr>\r\n<tr>\r\n<td>Tay vịn</td>\r\n<td>3D điều chỉnh n&acirc;ng hạ 60-74,5 cm, xoay tr&aacute;i phải, trượt ra, v&agrave;o,&nbsp; bọc da PU sang trọng, &ecirc;m &aacute;i</td>\r\n</tr>\r\n<tr>\r\n<td>Ngả lưng</td>\r\n<td>Ghế c&ocirc;ng th&aacute;i học Butterfly 01-B01 c&oacute; thể ngả lưng 135 độ, kh&oacute;a cố định 2-3 vị tr&iacute;.</td>\r\n</tr>\r\n<tr>\r\n<td>Nệm ghế</td>\r\n<td>\r\n<p>M&acirc;m ghế trượt&nbsp; ra v&agrave;o khoảng 3&nbsp;cm. K&iacute;ch thước: 50x52 cm.&nbsp;</p>\r\n</td>\r\n</tr>\r\n<tr>\r\n<td>Ghế văn ph&ograve;ng thiết kế lưới</td>\r\n<td>Ghế c&ocirc;ng th&aacute;i học B01 được sử dụng chất liệu lưới Đ&agrave;i Loan nhập khẩu được l&agrave;m bằng sợi nhựa đan xen với c&aacute;c sợi nỉ mềm mại, th&acirc;n thiện với da.</td>\r\n</tr>\r\n<tr>\r\n<td>Piston</td>\r\n<td>Piston 3 cấp Chứng nhận SGS, trục h&agrave;nh tr&igrave;nh 85mm, n&acirc;ng hạ từ&nbsp; 46-52 cm&nbsp;</td>\r\n</tr>\r\n<tr>\r\n<td>Khung ghế</td>\r\n<td>Khung nhựa PA cao cấp đạt chứng nhận ti&ecirc;u chuẩn nhựa Văn ph&ograve;ng</td>\r\n</tr>\r\n</tbody>\r\n</table>',3),(28,'product25.png','Ghế ergonomic Fly Pro',13500000,12000000,0,10,2,'<p>Ưu điểm vượt trội của ghế c&ocirc;ng th&aacute;i học ergonomic Fly Pro:<br>&ndash; Thiết kế m&acirc;m kim loại nguy&ecirc;n khối cực k&igrave; chắc chắn, điều chỉnh t&iacute;nh năng ghế được t&iacute;ch hợp ở r&igrave;a m&acirc;m ghế cực k&igrave; tiện lợi</p>\r\n<p>&ndash; Tay điều chỉnh 4D</p>\r\n<p>&ndash; Ghế sử dụng c&ocirc;ng nghệ lưới ti&ecirc;n tiến wintex nhập khẩu h&agrave;n quốc, tạo cảm gi&aacute;c đ&agrave;n hồi, &ecirc;m &aacute;i thoải m&aacute;i khi ngồi</p>\r\n<p>&ndash; Pistol samhongsa nhập h&agrave;n quốc chuẩn cấp 4</p>\r\n<p>&ndash; Phần lưng lưới thiết kế điều chỉnh l&ecirc;n xuống linh động, kh&ocirc;ng bị gắn cố định, ngả 140 độ,&nbsp;hỗ trợ chống g&ugrave;, đỡ lưng đem lại cảm gi&aacute;c dễ chịu, chống đau cột sống cho anh em d&acirc;n văn ph&ograve;ng đang bị đau</p>\r\n<p>&ndash; Ch&acirc;n ghế thiết kế sang trọng lịch l&atilde;m, kết hợp g&oacute;c nghi&ecirc;ng 130 độ phần th&acirc;n, v&agrave; t&ugrave;y chỉnh phần cổ, gi&uacute;p thư gian mỗi khi cần nghỉ ngơi.</p>\r\n<p>&ndash; Ghế bảo h&agrave;nh 30 th&aacute;ng ch&iacute;nh h&atilde;ng</p>',3);
/*!40000 ALTER TABLE `san_pham` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-14 21:18:11
