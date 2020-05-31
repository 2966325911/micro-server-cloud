
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


CREATE DATABASE seata_order;
/* seata_order下创建t_order表*/
CREATE TABLE t_order (
	`id` BIGINT ( 11 ) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`user_id` BIGINT ( 11 ) DEFAULT NULL COMMENT '用户id',
	`product_id` BIGINT ( 11 ) DEFAULT NULL COMMENT '产品id',
	`count` INT ( 11 ) DEFAULT NULL COMMENT '数量',
	`money` DECIMAL NULL COMMENT '金额',
`status` INT ( 1 ) DEFAULT NULL COMMENT '订单状态：0： 创建中； 1：已完结'
) ENGINE = INNODB AUTO_INCREMENT = 7 DEFAULT CHARSET = utf8;

CREATE DATABASE seata_storage;
/* seata_storage下创建t_storage表*/
CREATE TABLE t_storage (
	`id` BIGINT ( 11 ) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`product_id` BIGINT ( 11 ) DEFAULT NULL COMMENT '产品id',
	`total` INT ( 11 ) DEFAULT NULL COMMENT '总库存',
	`used` INT ( 11 ) DEFAULT NULL COMMENT '已用库存',
	`residue` INT ( 11 ) DEFAULT NULL COMMENT '剩余库存'
) ENGINE = INNODB AUTO_INCREMENT = 2 DEFAULT CHARSET = utf8;

INSERT INTO t_storage(`id`,`product_id`,`total`,`used`,`residue`)
VALUES('1','1','100','0','100')

CREATE DATABASE seata_account;
/* seata_account下创建t_account表*/
CREATE TABLE t_account (
	`id` BIGINT ( 11 ) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`user_id` BIGINT ( 11 ) DEFAULT NULL COMMENT '用户id',
	`total` INT ( 11 ) DEFAULT NULL COMMENT '总库存',
	`used` INT ( 11 ) DEFAULT NULL COMMENT '已用库存',
	`residue` INT ( 11 ) DEFAULT NULL COMMENT '剩余库存'
) ENGINE = INNODB AUTO_INCREMENT = 2 DEFAULT CHARSET = utf8;

INSERT INTO t_account(`id`,`user_id`,`total`,`used`,`residue`)
VALUES('1','1','1000','0','1000')




