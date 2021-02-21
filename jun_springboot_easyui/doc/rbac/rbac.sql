/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50624
 Source Host           : localhost
 Source Database       : rbac

 Target Server Type    : MySQL
 Target Server Version : 50624
 File Encoding         : utf-8

 Date: 12/26/2017 01:58:11 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `rbac_permission`
-- ----------------------------
DROP TABLE IF EXISTS `rbac_permission`;
CREATE TABLE `rbac_permission` (
  `id`             BIGINT(20)                       NOT NULL              AUTO_INCREMENT,
  `description`    VARCHAR(255) COLLATE utf8mb4_bin                       DEFAULT NULL,
  `enable`         BIT(1)                                                 DEFAULT NULL,
  `name`           VARCHAR(255) COLLATE utf8mb4_bin NOT NULL,
  `path`           VARCHAR(255) COLLATE utf8mb4_bin                       DEFAULT NULL,
  `permission_key` VARCHAR(32) COLLATE utf8mb4_bin  NOT NULL,
  `resource`       VARCHAR(255) COLLATE utf8mb4_bin                       DEFAULT NULL,
  `type`           VARCHAR(255) COLLATE utf8mb4_bin                       DEFAULT NULL,
  `weight`         INT(11)                                                DEFAULT NULL,
  `parent_id`      BIGINT(20)                                             DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKarkyg4p1bouosuixjo7rebdjn` (`parent_id`),
  CONSTRAINT `FKarkyg4p1bouosuixjo7rebdjn` FOREIGN KEY (`parent_id`) REFERENCES `rbac_permission` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
--  Records of `rbac_permission`
-- ----------------------------
BEGIN;
INSERT INTO `rbac_permission` VALUES ('1', '', b'1', '系统管理', '', 'system', '', 'MENU', '0', NULL), ('2', '权限管理', b'1', '权限管理', '/system/permission', 'system/permission', '/system/permission/list', 'MENU', '0', '1'), ('3', '角色管理', b'1', '角色管理', '/system/role', 'system:role', '/system/role/list', 'MENU', '1', '1'), ('4', '用户管理', b'1', '用户管理', '/system/user', 'system/user', '/system/user/list', 'MENU', '1', '1'), ('5', '', b'1', '创建用户', '', 'system:user:create', '/system/user/from,/system/user/save', 'FUNCTION', NULL, '4'), ('6', '', b'1', '编辑', '', 'system:user:edit', '/system/user/load/*,/system/user/update,/system/user/check', 'FUNCTION', NULL, '4'), ('7', '', b'1', '删除', '', 'system:user:delete', '/system/user/delete', 'FUNCTION', NULL, '4');
COMMIT;

-- ----------------------------
--  Table structure for `rbac_role`
-- ----------------------------
DROP TABLE IF EXISTS `rbac_role`;
CREATE TABLE `rbac_role` (
  `id`          BIGINT(20)                      NOT NULL              AUTO_INCREMENT,
  `description` VARCHAR(255) COLLATE utf8mb4_bin                      DEFAULT NULL,
  `enable`      BIT(1)                                                DEFAULT NULL,
  `role_key`    VARCHAR(32) COLLATE utf8mb4_bin NOT NULL,
  `role_name`   VARCHAR(32) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_iace11lm41qsi7dstkaiecion` (`role_key`),
  UNIQUE KEY `UK_eu9uvi1fl9j2kmtul6bmcu0mh` (`role_name`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
--  Records of `rbac_role`
-- ----------------------------
BEGIN;
INSERT INTO `rbac_role` VALUES ('1', '', b'1', 'admin', '管理员'), ('3', '测试', b'1', 'normal', '普通用户');
COMMIT;

-- ----------------------------
--  Table structure for `rbac_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `rbac_role_permission`;
CREATE TABLE `rbac_role_permission` (
  `role_id`       BIGINT(20) NOT NULL,
  `permission_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`),
  KEY `FK6l1rpsk0jgvg41t538728fjm4` (`permission_id`),
  CONSTRAINT `FK4ehlewddmsjetvam13ef633iw` FOREIGN KEY (`role_id`) REFERENCES `rbac_role` (`id`),
  CONSTRAINT `FK6l1rpsk0jgvg41t538728fjm4` FOREIGN KEY (`permission_id`) REFERENCES `rbac_permission` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
--  Records of `rbac_role_permission`
-- ----------------------------
BEGIN;
INSERT INTO `rbac_role_permission` VALUES ('1', '1'), ('3', '1'), ('3', '3'), ('1', '4'), ('3', '4'), ('3', '5'), ('3', '7');
COMMIT;

-- ----------------------------
--  Table structure for `rbac_user`
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user`;
CREATE TABLE `rbac_user` (
  `id`        BIGINT(20)                       NOT NULL AUTO_INCREMENT,
  `account`   VARCHAR(16) COLLATE utf8mb4_bin  NOT NULL,
  `enable`    BIT(1)                                    DEFAULT NULL,
  `password`  VARCHAR(128) COLLATE utf8mb4_bin NOT NULL,
  `tel`       VARCHAR(255) COLLATE utf8mb4_bin          DEFAULT NULL,
  `user_name` VARCHAR(32) COLLATE utf8mb4_bin           DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dxesfklauarqhov4147i100ud` (`account`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
--  Records of `rbac_user`
-- ----------------------------
BEGIN;
INSERT INTO `rbac_user` VALUES ('0', 'admin', b'1', 'e10adc3949ba59abbe56e057f20f883e', '1234567', '超级管理员'), ('5', 'admin2', b'1', 'e10adc3949ba59abbe56e057f20f883e', '测试2', '测试2'), ('6', 'test', b'1', 'e10adc3949ba59abbe56e057f20f883e', '12345678', '李四');
COMMIT;

-- ----------------------------
--  Table structure for `rbac_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user_role`;
CREATE TABLE `rbac_user_role` (
  `user_id` BIGINT(20) NOT NULL,
  `role_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  KEY `FKnviybsn4jexeg8t4n0n4bagi5` (`role_id`),
  CONSTRAINT `FKnviybsn4jexeg8t4n0n4bagi5` FOREIGN KEY (`role_id`) REFERENCES `rbac_role` (`id`),
  CONSTRAINT `FKrgdhhtcvdp38598e9uhxd3pb` FOREIGN KEY (`user_id`) REFERENCES `rbac_user` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- ----------------------------
--  Records of `rbac_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `rbac_user_role` VALUES ('5', '1'), ('5', '3'), ('6', '3');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
