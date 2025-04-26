/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 10.4.32-MariaDB : Database - ps_projekat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ps_projekat` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `ps_projekat`;

/*Table structure for table `instruktor` */

DROP TABLE IF EXISTS `instruktor`;

CREATE TABLE `instruktor` (
  `idInstruktor` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(30) NOT NULL,
  `prezime` varchar(30) NOT NULL,
  `kontakt` varchar(30) NOT NULL,
  `korisnickoIme` varchar(30) NOT NULL,
  `sifra` varchar(30) NOT NULL,
  PRIMARY KEY (`idInstruktor`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `instruktor` */

insert  into `instruktor`(`idInstruktor`,`ime`,`prezime`,`kontakt`,`korisnickoIme`,`sifra`) values 
(1,'Marko','Samardzic','0644661418','admin','admin0202'),
(11,'Danica','Prodan','0644661515','Danica','daca2004'),
(12,'Vuk','Lecic','0698983241','vukvuk','vuk0202'),
(20,'Milutin','Milankovic','0655671212','milutin','milutin23'),
(21,'Radovan','Stepanovic','0633330199','radeStep','radisa02');

/*Table structure for table `instruktorlicenca` */

DROP TABLE IF EXISTS `instruktorlicenca`;

CREATE TABLE `instruktorlicenca` (
  `instruktor` bigint(20) NOT NULL,
  `licenca` bigint(20) NOT NULL,
  `godinaSticanja` int(11) NOT NULL,
  PRIMARY KEY (`instruktor`,`licenca`,`godinaSticanja`),
  UNIQUE KEY `instruktor_godina` (`instruktor`,`godinaSticanja`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `instruktorlicenca` */

insert  into `instruktorlicenca`(`instruktor`,`licenca`,`godinaSticanja`) values 
(1,1,2018),
(1,1,2019),
(1,2,2020),
(1,2,2021),
(1,3,2022),
(1,3,2024),
(1,4,2025),
(11,1,2023),
(11,1,2024),
(11,2,2025);

/*Table structure for table `licenca` */

DROP TABLE IF EXISTS `licenca`;

CREATE TABLE `licenca` (
  `idLicenca` bigint(20) NOT NULL AUTO_INCREMENT,
  `zvanjeInstruktora` varchar(40) NOT NULL,
  `nazivLicence` varchar(40) NOT NULL,
  PRIMARY KEY (`idLicenca`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `licenca` */

insert  into `licenca`(`idLicenca`,`zvanjeInstruktora`,`nazivLicence`) values 
(1,'Instruktor skijanja I stepena','IVSI'),
(2,'Instruktor skijanja II stepena','IVSI '),
(3,'Instruktor skijanja III stepena','ISIA'),
(4,'Instruktor skijanja IV stepena','ISIA');

/*Table structure for table `nivoskijanja` */

DROP TABLE IF EXISTS `nivoskijanja`;

CREATE TABLE `nivoskijanja` (
  `idNivoSkijanja` bigint(20) NOT NULL AUTO_INCREMENT,
  `nazivNivoa` varchar(30) NOT NULL,
  PRIMARY KEY (`idNivoSkijanja`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `nivoskijanja` */

insert  into `nivoskijanja`(`idNivoSkijanja`,`nazivNivoa`) values 
(1,'pocetnik'),
(4,'amater'),
(16,'profesionalac');

/*Table structure for table `skijas` */

DROP TABLE IF EXISTS `skijas`;

CREATE TABLE `skijas` (
  `idSkijas` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(30) NOT NULL,
  `prezime` varchar(30) NOT NULL,
  `brojTelefona` varchar(15) NOT NULL,
  `nivoSkijanja` bigint(20) NOT NULL,
  PRIMARY KEY (`idSkijas`),
  KEY `nivoSkijanja` (`nivoSkijanja`),
  CONSTRAINT `skijas_ibfk_1` FOREIGN KEY (`nivoSkijanja`) REFERENCES `nivoskijanja` (`idNivoSkijanja`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `skijas` */

insert  into `skijas`(`idSkijas`,`ime`,`prezime`,`brojTelefona`,`nivoSkijanja`) values 
(1,'Andrej','Maksimovic','0655676788',16),
(5,'Vuk','Lecic','0658757444',4),
(6,'Danica','Prodan','0677834599',1),
(8,'Milan','Miletic','0643824894',16),
(10,'Zivojin','Zicicc','0654778383',1),
(12,'Nikola','Vujic','0655676781',1);

/*Table structure for table `termin` */

DROP TABLE IF EXISTS `termin`;

CREATE TABLE `termin` (
  `idTermin` bigint(20) NOT NULL AUTO_INCREMENT,
  `ukupanIznos` double NOT NULL,
  `vremeOd` time NOT NULL,
  `vremeDo` time NOT NULL,
  `brojSati` int(11) NOT NULL,
  `maxBrojSkijasa` int(11) NOT NULL,
  `datum` date NOT NULL,
  `tip` bigint(20) NOT NULL,
  `instruktor` bigint(20) NOT NULL,
  PRIMARY KEY (`idTermin`),
  KEY `tip` (`tip`),
  KEY `instruktor` (`instruktor`),
  CONSTRAINT `termin_ibfk_1` FOREIGN KEY (`tip`) REFERENCES `tiptermina` (`idTip`),
  CONSTRAINT `termin_ibfk_2` FOREIGN KEY (`instruktor`) REFERENCES `instruktor` (`idInstruktor`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `termin` */

insert  into `termin`(`idTermin`,`ukupanIznos`,`vremeOd`,`vremeDo`,`brojSati`,`maxBrojSkijasa`,`datum`,`tip`,`instruktor`) values 
(16,11400,'11:00:00','13:00:00',2,3,'2025-03-27',8,1),
(18,1600,'16:00:00','17:00:00',1,1,'2025-04-16',1,20),
(22,6600,'10:15:00','12:15:00',2,3,'2025-04-19',13,1),
(23,6400,'17:22:00','19:22:05',2,2,'2025-04-21',8,1),
(24,3200,'11:15:00','12:15:00',1,2,'2025-05-06',8,11),
(25,1900,'12:15:00','13:15:00',1,1,'2025-05-06',1,11);

/*Table structure for table `terminskijas` */

DROP TABLE IF EXISTS `terminskijas`;

CREATE TABLE `terminskijas` (
  `termin` bigint(20) NOT NULL,
  `skijas` bigint(20) NOT NULL,
  `datumPrijave` date NOT NULL,
  PRIMARY KEY (`termin`,`skijas`),
  KEY `skijas` (`skijas`),
  CONSTRAINT `terminskijas_ibfk_1` FOREIGN KEY (`termin`) REFERENCES `termin` (`idTermin`),
  CONSTRAINT `terminskijas_ibfk_2` FOREIGN KEY (`skijas`) REFERENCES `skijas` (`idSkijas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `terminskijas` */

insert  into `terminskijas`(`termin`,`skijas`,`datumPrijave`) values 
(16,1,'2025-03-27'),
(16,10,'2025-03-27'),
(16,12,'2025-03-27'),
(18,12,'2025-04-10'),
(22,1,'2025-04-15'),
(22,5,'2025-04-15'),
(23,6,'2025-04-21'),
(23,10,'2025-04-21'),
(24,5,'2025-04-26'),
(24,12,'2025-04-26'),
(25,8,'2025-04-26');

/*Table structure for table `tiptermina` */

DROP TABLE IF EXISTS `tiptermina`;

CREATE TABLE `tiptermina` (
  `idTip` bigint(20) NOT NULL AUTO_INCREMENT,
  `cenaSata` double NOT NULL,
  `nazivTipa` varchar(30) NOT NULL,
  PRIMARY KEY (`idTip`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tiptermina` */

insert  into `tiptermina`(`idTip`,`cenaSata`,`nazivTipa`) values 
(1,1900,'Personalni za odrasle'),
(4,2100,'Personalni za decu'),
(8,1600,'Grupni trening za decu'),
(9,2500,'Osobe sa PP - personalni'),
(13,1650,'Grupni trening za odrasle');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
