/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.18-log : Database - project_scope
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`project_scope` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `project_scope`;

/*Table structure for table `log` */

DROP TABLE IF EXISTS `log`;

CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `projects_id` int(11) NOT NULL,
  `hours` double DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `projects_id` (`projects_id`),
  CONSTRAINT `log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `log_ibfk_2` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `log` */

insert  into `log`(`id`,`user_id`,`projects_id`,`hours`,`date`) values (2,10,3,7,'2020-11-05 17:32:00');

/*Table structure for table `projects` */

DROP TABLE IF EXISTS `projects`;

CREATE TABLE `projects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deadline` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `hours` double DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `usar_id` (`user_id`),
  CONSTRAINT `projects_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `projects` */

insert  into `projects`(`id`,`name`,`date`,`deadline`,`hours`,`user_id`) values (1,'testName','2020-10-31 23:09:20','2020-10-31 23:09:20',0.5,7),(3,'asdf','2020-11-04 20:45:00','2020-11-17 20:45:00',7,8);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `type` enum('TEAM_LEADER','TEAM_MEMBER') NOT NULL,
  `password` varchar(255) NOT NULL,
  `otp` varchar(255) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`surname`,`email`,`profile_picture`,`type`,`password`,`otp`,`active`) values (7,'Arzuman','Kochoyan','arzuman.tast@mail.ru','348aadd3bc0a46af93649b4e82a5513b7.jpg','TEAM_LEADER','$2a$10$jgpi41URir6f5bP/ww3tHeyFokCOaL7vjwskK7483xOlosSjnunGq','51463ac5-4e76-4914-8004-b4149bdca913',1),(8,'Arzuman','Kochoyan','arzuman.kochoyan98@mail.ru','348aadd3bc0a46af93649b4e82a5513b7.jpg','TEAM_LEADER','$2a$10$f20TkI14RqC.TNkT9fs78.SkIwEo6gNhGYLBbfYhW3MGF5cfz9ZPe','',1),(9,'Arzuman','Kochoyan','test.ok@mail.ru','C:\\Users\\Arzuman\\Desktop\\Folder\\rest\\projectScope\\upload\\348aadd3bc0a46af93649b4e82a5513b7.jpg','TEAM_MEMBER','$2a$10$szgYPgEU52JtNt3ovBTxo.Kzkr70zk3SMyPbWksh1IrDvmpvYbSUi','459ff1c2-a75e-40f8-8dba-315c1ff122e1',0),(10,'poxos','poxos','poxostest@mail.ru','348aadd3bc0a46af93649b4e82a5513b7.jpg','TEAM_MEMBER','$2a$10$ER5QGmRcVGD/DwHYCn9PlOlAjtBsRddMD5g72g1LNAdfDXz.1r4aS','ac3a5dc7-b424-4f4b-bb33-a17dae2e259f',0);

/*Table structure for table `user_projects` */

DROP TABLE IF EXISTS `user_projects`;

CREATE TABLE `user_projects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `projects_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `projects_id` (`projects_id`),
  CONSTRAINT `user_projects_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_projects_ibfk_2` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user_projects` */

insert  into `user_projects`(`id`,`user_id`,`projects_id`) values (2,8,1),(3,10,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
