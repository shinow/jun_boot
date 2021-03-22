/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.7.20-log : Database - eboot
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`eboot` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `eboot`;

/*Table structure for table `pt_dict` */

DROP TABLE IF EXISTS `pt_dict`;

CREATE TABLE `pt_dict` (
  `id` varchar(32) NOT NULL,
  `name` varchar(1024) NOT NULL COMMENT '名称',
  `type_code` varchar(256) NOT NULL COMMENT '类型',
  `sort_no` int(4) DEFAULT NULL COMMENT '排序号',
  `is_edit` char(1) NOT NULL COMMENT '是否可编辑',
  `is_del` char(1) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pt_dict` */

insert  into `pt_dict`(`id`,`name`,`type_code`,`sort_no`,`is_edit`,`is_del`) values 

('2bf6257fc8fe483c84c1ad7e89d632f6','人妖','sex',3,'1','1'),

('58ce515474cd454fb6266f49a01833c0','男','sex',1,'0','0'),

('b390d79f447b489aa9eb2dddf4d53c53','女','sex',2,'0','0');

/*Table structure for table `sys_accessory` */

DROP TABLE IF EXISTS `sys_accessory`;

CREATE TABLE `sys_accessory` (
  `id` varchar(32) DEFAULT NULL,
  `original_name` varchar(256) DEFAULT NULL COMMENT '原文件名\n',
  `file_size` int(11) DEFAULT NULL COMMENT '文件大小（单位：字节）',
  `path` varchar(256) DEFAULT NULL COMMENT '存储路径',
  `category` varchar(32) DEFAULT NULL COMMENT '分类',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `is_del` char(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件表';

/*Data for the table `sys_accessory` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` varchar(32) NOT NULL,
  `name` varchar(56) DEFAULT NULL,
  `path` varchar(512) DEFAULT NULL,
  `icon` varchar(512) DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `menu_type` char(1) DEFAULT NULL COMMENT '1:资源菜单，2：权限',
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(32) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_user` varchar(32) DEFAULT NULL,
  `sort_no` int(11) DEFAULT '1',
  `code` varchar(128) DEFAULT NULL COMMENT '权限编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`path`,`icon`,`parent_id`,`menu_type`,`create_time`,`create_user`,`modify_time`,`modify_user`,`sort_no`,`code`) values 

('05b612c3790c412a9ace26ddb431e4af','绑定权限',NULL,NULL,'8dda78d9574711e8964b509a4c2d7e6d','2','2018-05-14 14:58:13','1','2018-05-14 14:58:21','1',2,'sys:role:basic:br'),

('1','根',NULL,NULL,'0','1','2018-03-27 06:04:37','1',NULL,NULL,0,NULL),

('11b80033574711e8964b509a4c2d7e6d','用户管理','/sys/user/to-page',NULL,'a89e0032574711e8964b509a4c2d7e6d','1',NULL,NULL,NULL,NULL,1,NULL),

('126e56389cef422ea514422e28d5472f','字典删除',NULL,NULL,'6bf55a3e547e44e6adab7a33ff2ab8dc','2','2018-05-14 15:02:19','1',NULL,NULL,1,'sys:dict:basic:del'),

('1d29c95c338f46f0b761b41c7e74bad3','角色基本操作（增、改）',NULL,NULL,'8dda78d9574711e8964b509a4c2d7e6d','2','2018-05-14 14:56:13','1',NULL,NULL,0,'sys:role:basic:op'),

('4b74f80c3089473981bb089208654077','字典基本操作（增、改）',NULL,NULL,'6bf55a3e547e44e6adab7a33ff2ab8dc','2','2018-05-14 15:01:52','1','2018-05-14 15:14:43','1',0,'sys:dict:basic:op'),

('6bf55a3e547e44e6adab7a33ff2ab8dc','字典管理','pt/dict/to-page','','a89e0032574711e8964b509a4c2d7e6d','1','2018-05-02 11:25:29','1','2018-05-14 15:02:35','1',5,'code'),

('813c1e5088004484818a9e1a12f485e7','用户删除',NULL,NULL,'11b80033574711e8964b509a4c2d7e6d','2','2018-05-14 14:54:40','1',NULL,NULL,3,'sys:user:del'),

('8dda78d9574711e8964b509a4c2d7e6d','角色管理','/sys/role/to-page',NULL,'a89e0032574711e8964b509a4c2d7e6d','1',NULL,NULL,NULL,NULL,2,NULL),

('8f8cb513fc1547f5a8ff6091aadb9b4c','报表管理',NULL,NULL,'1','1','2018-05-03 09:42:29','1',NULL,NULL,3,NULL),

('9c102353574711e8964b509a4c2d7e6d','菜单管理','/sys/menu/to-page',NULL,'a89e0032574711e8964b509a4c2d7e6d','1',NULL,NULL,NULL,NULL,3,NULL),

('a89e0032574711e8964b509a4c2d7e6d','系统管理',NULL,'','1','1',NULL,NULL,NULL,NULL,1,NULL),

('b5a9608e6b8e409a8a36bb776694b721','用户新增',NULL,NULL,'11b80033574711e8964b509a4c2d7e6d','2','2018-05-02 14:20:49','1','2018-05-14 14:55:01','1',1,'sys:user:add'),

('bd8f73c7574711e8964b509a4c2d7e6d','内容管理',NULL,NULL,'1','1',NULL,NULL,NULL,NULL,2,NULL),

('c50adb23574711e8964b509a4c2d7e6d','新闻资讯',NULL,NULL,'bd8f73c7574711e8964b509a4c2d7e6d','1',NULL,NULL,NULL,NULL,1,NULL),

('d1c3e979fa6a4ac2935fa85bbe027c9c','用户报表','report/platform/user-report',NULL,'8f8cb513fc1547f5a8ff6091aadb9b4c','1','2018-05-03 09:43:05','1',NULL,NULL,0,NULL),

('d59ac74d574711e8964b509a4c2d7e6d','菜单基本操作（增、改）',NULL,NULL,'9c102353574711e8964b509a4c2d7e6d','2','2018-05-03 09:41:24','1','2018-05-14 15:01:04','1',1,'sys:menu:basic:op'),

('e3131b4446974a61824c4f2ed8f6a5fa','菜单删除',NULL,NULL,'9c102353574711e8964b509a4c2d7e6d','2','2018-05-14 14:59:12','1',NULL,NULL,2,'sys:menu:basic:del'),

('e339f3d6fc8c4f35a663299b58fd8c6a','用户修改',NULL,NULL,'11b80033574711e8964b509a4c2d7e6d','2','2018-05-02 14:59:00','1','2018-05-14 14:54:53','1',2,'sys:user:edit'),

('e41838d6a1f04fe5b77d6eb8826c13fd','角色删除',NULL,NULL,'8dda78d9574711e8964b509a4c2d7e6d','2','2018-05-14 14:56:40','1',NULL,NULL,1,'sys:role:basic:del');

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` varchar(32) NOT NULL,
  `menu_id` varchar(32) DEFAULT NULL,
  `code` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_permission` */

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(56) DEFAULT NULL,
  `is_del` char(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`is_del`,`create_time`,`create_user`) values 

('1074ea4d56db49dcba6e6811e4d7af4c','系统管理员','0','2018-05-02 09:44:18','1'),

('819ed004feae468bb03fa5dec660e40b','网络管理员','0','2018-03-20 16:08:00','1'),

('960ba4ae3c544f768c0d7afd3dbf93cc','啥也不是','1','2018-03-20 16:08:10','1'),

('dd1a1c774368495bbb973f0913207328','假的超级管理员','0','2018-03-20 16:07:49','1');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL,
  `menu_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_menu` */

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL,
  `permission_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_permission` */

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(128) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `is_del` char(1) DEFAULT NULL,
  `nickname` varchar(1024) DEFAULT NULL,
  `mobile` varchar(24) DEFAULT NULL,
  `photo` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(32) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `ip` varchar(32) DEFAULT NULL,
  `disabled` char(1) DEFAULT NULL,
  `locked` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`,`is_del`,`nickname`,`mobile`,`photo`,`create_time`,`create_user`,`last_login_time`,`ip`,`disabled`,`locked`) values 

('1','superadmin','e10adc3949ba59abbe56e057f20f883e','0','超级管理员','18236980519','7f381b7682104bfca944c12cccb0387b','2018-01-13 19:55:01','0','2018-05-14 15:03:33','192.168.37.1','0','0');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `role_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values 

('04b8733f889248e0b238985856670aae','3','819ed004feae468bb03fa5dec660e40b'),

('5816cd37aa1249d8aff36fc5b7619938','2','819ed004feae468bb03fa5dec660e40b'),

('806feb9fed274404905d306a13bdfa02','2','1074ea4d56db49dcba6e6811e4d7af4c'),

('97a03aaaadd446c8b9666ac7effb3803','12','1074ea4d56db49dcba6e6811e4d7af4c'),

('a587da4e053a46abb51fc22667a1e1fe','10','1074ea4d56db49dcba6e6811e4d7af4c'),

('b996d1e9abb148bcb0e731ce881c9fea','11','dd1a1c774368495bbb973f0913207328'),

('c0981fba692345588465c6555d8844f6','11','819ed004feae468bb03fa5dec660e40b'),

('e972845d8c8f4cf08c400bf6597312c1','2','dd1a1c774368495bbb973f0913207328');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
