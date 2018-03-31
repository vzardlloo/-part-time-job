/*
SQLyog Ultimate v9.20 
MySQL - 5.6.32-log : Database - zhanghuan
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zhanghuan` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `zhanghuan`;

/*Table structure for table `customers` */

DROP TABLE IF EXISTS `customers`;

CREATE TABLE `customers` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `score` varchar(20) DEFAULT NULL COMMENT '积分',
  `location` varchar(20) DEFAULT NULL COMMENT '位置',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(20) DEFAULT NULL COMMENT '邮件',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

/*Data for the table `customers` */

insert  into `customers`(`id`,`score`,`location`,`phone`,`email`,`name`,`password`) values (7,NULL,'asdasdasdas','sdasdasd','sadasdas','wqeqweqw',NULL),(8,NULL,'sdadasdasd','21321321','321312312','wqeqwe',NULL),(9,NULL,'sadasdasdasd','asadasda','sadsadas','213123','1234567'),(10,'23','213123123123','12323123','21312312','qeqwq','1234567'),(11,NULL,NULL,'13213123123','dasdasd@asds','eqweqw','213123123'),(12,NULL,NULL,'1312312312','weqwe@dads','ewqe','qweeqweqw'),(13,NULL,NULL,'1312312312','weqwe@dads','ewqe','2313123123'),(14,NULL,NULL,'12312312312','asdas@qwq','wqewqe','1231312312'),(15,NULL,NULL,'12312312312','wqeqwe@weweq','wqeqwe','123123123123'),(16,NULL,NULL,'12312312312','wqeqwe@weweq','wqeqwe','133213123'),(17,NULL,NULL,'12213123','weqweq@qwe','13123','123123'),(18,NULL,NULL,'12213123','weqweq@qwe','13123','123123'),(19,NULL,NULL,'131312312321','eqwe@qewe','qweqe','qewewqeq'),(20,NULL,NULL,'21312312','1213@123','123','123456');

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(10) NOT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `message` */

insert  into `message`(`id`,`title`,`content`) values (1,'好的','666'),(2,'5555','535435345345'),(3,'神龙商城打折啦','神农商城所有商品即日起一律5折！！！！');

/*Table structure for table `products` */

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `count` varchar(20) DEFAULT '”“' COMMENT '数量',
  `name` varchar(20) DEFAULT '”“' COMMENT '名称',
  `detail` text NOT NULL COMMENT '细节',
  `info` varchar(20) NOT NULL DEFAULT '”“' COMMENT '信息',
  `image` varchar(500) NOT NULL DEFAULT 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=336696470,3934453781&fm=200&gp=0.jpg' COMMENT '图片',
  `price` varchar(20) DEFAULT '”“' COMMENT '价格',
  `category` varchar(20) DEFAULT '”“' COMMENT '种类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;

/*Data for the table `products` */

insert  into `products`(`id`,`count`,`name`,`detail`,`info`,`image`,`price`,`category`) values (20,'34','羊毛毯','6666','666','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=336696470,3934453781&fm=200&gp=0.jpg','”“','”“'),(21,'56','羊毛毯','66666','666','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=336696470,3934453781&fm=200&gp=0.jpg','”“','”“'),(22,'67','羊毛毯','6666','66','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=336696470,3934453781&fm=200&gp=0.jpg','”“','”“'),(23,'24','羊毛毯','6666','66','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=336696470,3934453781&fm=200&gp=0.jpg','”“','”“'),(24,'56','羊毛毯','8888','66','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=336696470,3934453781&fm=200&gp=0.jpg','”“','”“'),(25,'67','羊毛毯','8888','66','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=336696470,3934453781&fm=200&gp=0.jpg','”“','”“'),(26,'68','羊毛毯','wqeqwe','66666','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=336696470,3934453781&fm=200&gp=0.jpg','”“','”“'),(27,'46','羊毛毯','5555','6666','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=336696470,3934453781&fm=200&gp=0.jpg','”“','”“'),(28,'88','羊毛毯','6666','666','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=336696470,3934453781&fm=200&gp=0.jpg','”“','”“'),(29,'78','羊毛毯','qeweqweqw','666','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=336696470,3934453781&fm=200&gp=0.jpg','”“','”“');

/*Table structure for table `shopingcar` */

DROP TABLE IF EXISTS `shopingcar`;

CREATE TABLE `shopingcar` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `cid` varchar(11) DEFAULT '""' COMMENT '顾客id',
  `pid` varchar(4) DEFAULT '""' COMMENT '产品id',
  `cname` varchar(20) NOT NULL DEFAULT '""' COMMENT '顾客姓名',
  `pname` varchar(20) NOT NULL DEFAULT '""' COMMENT '产品名称',
  `value` varchar(20) NOT NULL DEFAULT '""' COMMENT '价格',
  `payment` varchar(11) NOT NULL DEFAULT '未支付' COMMENT '是否符款',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8mb4;

/*Data for the table `shopingcar` */

insert  into `shopingcar`(`id`,`cid`,`pid`,`cname`,`pname`,`value`,`payment`) values (85,'20','4','123','羊毛毯','56','未支付'),(90,'20','4','123','羊毛毯','56','未支付'),(91,'20','4','123','羊毛毯','56','已支付'),(92,'20','4','123','羊毛毯','56','未支付'),(93,'0','0','test','test','2sds','已支付'),(94,'20','4','123','羊毛毯','56','未支付'),(105,'20','4','123','羊毛毯','56','已支付'),(107,'0','0','test','test','2sds','已支付'),(109,'20','4','123','羊毛毯','56','未支付'),(110,'20','4','123','羊毛毯','56','未支付'),(111,'20','4','123','羊毛毯','56','未支付'),(113,'20','4','123','羊毛毯','56','已支付'),(114,'20','4','123','羊毛毯','56','已支付'),(115,'20','4','123','羊毛毯','56','未支付'),(117,'20','4','123','羊毛毯','56','未支付'),(118,'20','4','123','羊毛毯','56','未支付'),(119,'20','4','123','羊毛毯','56','未支付'),(120,'20','7','123','拉舍尔毛毯','32','未支付'),(121,'20','4','123','羊毛毯','56','未支付'),(122,'20','4','123','羊毛毯','56','已支付'),(123,'20','4','123','羊毛毯','56','未支付'),(124,'20','5','123','牛毛毯','234','未支付'),(125,'20','5','123','牛毛毯','234','未支付'),(126,'20','4','123','羊毛毯','56','未支付'),(127,'20','4','123','羊毛毯','56','未支付'),(128,'20','4','123','羊毛毯','56','已支付'),(129,'20','4','123','羊毛毯','56','未支付'),(130,'20','4','123','羊毛毯','56','未支付'),(131,'20','4','123','羊毛毯','56','未支付'),(132,'20','24','123','羊毛毯','”“','未支付'),(133,'20','21','123','羊毛毯','”“','未支付'),(134,'20','20','123','羊毛毯','”“','已支付');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(20) DEFAULT NULL COMMENT '邮件',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `email` varchar(20) DEFAULT NULL COMMENT '名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `users` */

insert  into `users`(`id`,`name`,`password`,`email`) values (1,'root@gmail.com','root','root');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
