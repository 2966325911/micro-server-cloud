
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cloud` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
​
USE `cloud`;
​
/*Table structure for table `payment` */
​
DROP TABLE IF EXISTS `payment`;
​
CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `serial` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
​
/*Data for the table `payment` */
​
insert  into `payment`(`id`,`serial`) values (1,'尚硅谷'),(2,'alibaba'),(3,'京东'),(4,'头条');
