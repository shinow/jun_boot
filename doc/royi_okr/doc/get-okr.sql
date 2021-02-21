/*
Navicat MySQL Data Transfer

Source Server         : 118-内部测试服务器
Source Server Version : 50725
Source Host           : 172.16.1.118:3306
Source Database       : get-okr

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2021-01-07 11:26:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table` (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
  `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='代码生成业务表';

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES ('20', 'okr_project_user_role', '项目权限表', 'OkrProjectUserRole', 'crud', 'com.ruoyi.system', 'system', 'role', '项目权限', 'powervision', null, 'admin', '2020-07-03 18:32:52', '', null, null);

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column` (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) DEFAULT '' COMMENT '字典类型',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8 COMMENT='代码生成业务表字段';

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES ('141', '20', 'user_id', null, 'bigint(20)', 'Long', 'userId', '1', '0', null, '1', null, null, null, 'EQ', 'input', '', '1', 'admin', '2020-07-03 18:32:52', '', null);
INSERT INTO `gen_table_column` VALUES ('142', '20', 'project_id', null, 'int(11)', 'Long', 'projectId', '1', '0', null, '1', null, null, null, 'EQ', 'input', '', '2', 'admin', '2020-07-03 18:32:52', '', null);

-- ----------------------------
-- Table structure for okr_align_info
-- ----------------------------
DROP TABLE IF EXISTS `okr_align_info`;
CREATE TABLE `okr_align_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `okr_id` int(11) DEFAULT NULL COMMENT 'OKR主键',
  `parent_id` int(11) DEFAULT NULL COMMENT '父OKR主键',
  `okr_keys` varchar(200) DEFAULT '' COMMENT 'KR集合',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1475 DEFAULT CHARSET=utf8 COMMENT='OKR对齐关系表';

-- ----------------------------
-- Records of okr_align_info
-- ----------------------------
INSERT INTO `okr_align_info` VALUES ('1474', '71801', '71805', '71802,71803,71804');

-- ----------------------------
-- Table structure for okr_attach
-- ----------------------------
DROP TABLE IF EXISTS `okr_attach`;
CREATE TABLE `okr_attach` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `okr_id` varchar(36) DEFAULT '' COMMENT 'OKR标识',
  `key_name` varchar(100) DEFAULT '' COMMENT '附加字段类型',
  `key_value` longtext COMMENT '附加字段值',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `okr_id` (`okr_id`),
  KEY `key_name` (`key_name`)
) ENGINE=InnoDB AUTO_INCREMENT=33020 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of okr_attach
-- ----------------------------
INSERT INTO `okr_attach` VALUES ('33018', '20210107111147836004', 'grade_remark', null, '2021-01-07 11:12:03');
INSERT INTO `okr_attach` VALUES ('33019', '20210107111147836004', 'okr_remark_content', null, '2021-01-07 11:12:03');

-- ----------------------------
-- Table structure for okr_comment
-- ----------------------------
DROP TABLE IF EXISTS `okr_comment`;
CREATE TABLE `okr_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论',
  `user_id` bigint(20) NOT NULL COMMENT '评论人id',
  `object_id` bigint(20) NOT NULL COMMENT '评论的对象 ID，相对评论类型的ID\r\n比如：\r\ntype=0 针对 o进行评论，\r\n           则object_id 为o的ID\r\ntype=1 针对 o进行评论，\r\n           则object_id 为kr的ID\r\ntype=2 针对 o进行评论，\r\n           则object_id 为被评论（一级评论）的评论ID\r\n           且parent_id 为被评论（二级评论）的评论ID\r\n\r\n注意：只有评论的子集评论会存在\r\n         object_id = parent_id的情况(评论后面不带@)',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '评论父类，上一次评论的父ID',
  `content` longtext COMMENT '评论的具体内容',
  `user_ip` varchar(255) DEFAULT NULL COMMENT '评论者IP',
  `type` tinyint(4) DEFAULT '0' COMMENT '评论的类型：（0 :关键目标，1 :关键结果，2:评论）',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态（0:默认、1:删除）',
  `add_time` datetime DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `okr_id` (`object_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `comment_parent` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=332 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='评论功能';

-- ----------------------------
-- Records of okr_comment
-- ----------------------------

-- ----------------------------
-- Table structure for okr_comment_meta
-- ----------------------------
DROP TABLE IF EXISTS `okr_comment_meta`;
CREATE TABLE `okr_comment_meta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(20) NOT NULL COMMENT '评论的ID',
  `meta_key` varchar(255) DEFAULT NULL COMMENT '1 file url\r\n2 image url\r\n3 thumb number',
  `meta_value` longtext COMMENT '对应的值',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `comment_id` (`comment_id`) USING BTREE,
  KEY `meta_key` (`meta_key`(191)) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='评论附加表';

-- ----------------------------
-- Records of okr_comment_meta
-- ----------------------------

-- ----------------------------
-- Table structure for okr_cycle
-- ----------------------------
DROP TABLE IF EXISTS `okr_cycle`;
CREATE TABLE `okr_cycle` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cycle_name` varchar(100) DEFAULT NULL COMMENT '周期名称',
  `begin_time` varchar(20) DEFAULT NULL COMMENT '起始时间',
  `end_time` varchar(20) DEFAULT NULL COMMENT '结束时间',
  `cycle_type` int(11) DEFAULT '0' COMMENT '周期类型（年度1 季度0）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` int(11) DEFAULT '0' COMMENT '状态（0进行中、1已失效）',
  `is_del` int(11) DEFAULT '0' COMMENT '是否删除（0默认、1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='OKR周期表';

-- ----------------------------
-- Records of okr_cycle
-- ----------------------------
INSERT INTO `okr_cycle` VALUES ('8', '2021-Q1', '2021-01-01', '2021-03-31', '0', '2020-12-28 13:20:34', '0', '0');

-- ----------------------------
-- Table structure for okr_info
-- ----------------------------
DROP TABLE IF EXISTS `okr_info`;
CREATE TABLE `okr_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `row_id` varchar(32) DEFAULT '' COMMENT '数据标识',
  `sort_id` int(11) DEFAULT '0' COMMENT '排序编号',
  `row_code` varchar(20) DEFAULT 'O' COMMENT '编号',
  `content` longtext COMMENT '内容描述',
  `row_type` int(11) DEFAULT '0' COMMENT '类型（0为目标、1为KR）',
  `confidence_star` varchar(10) DEFAULT '0' COMMENT '信心指数',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `score_star` varchar(10) DEFAULT '0' COMMENT '目标评分',
  `parent_id` varchar(32) DEFAULT '' COMMENT '父级ID',
  `cycle_id` int(11) DEFAULT NULL COMMENT '周期ID',
  `process` varchar(20) DEFAULT '0' COMMENT '目标进度',
  `status` int(11) DEFAULT '0' COMMENT '状态（默认为0、删除为1）',
  `priority` varchar(10) DEFAULT 'P1' COMMENT '优先级',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71810 DEFAULT CHARSET=utf8 COMMENT='OKR信息表';

-- ----------------------------
-- Records of okr_info
-- ----------------------------
INSERT INTO `okr_info` VALUES ('71801', '20210107110030145000', '0', '1', '先定个小目标三个月收益30%', '0', '0', '458', '2021-01-07 11:00:30', '0', '', '8', '0', '0', 'P1', '2021-01-07 11:00:30');
INSERT INTO `okr_info` VALUES ('71802', '20210107110030154001', '0', '1', '第一个月收益5%', '1', '0', '458', '2021-01-07 11:00:30', '0', '20210107110030145000', '8', '0', '0', 'P1', '2021-01-07 11:00:30');
INSERT INTO `okr_info` VALUES ('71803', '20210107110030161002', '0', '2', '第2个月收益10%', '1', '0', '458', '2021-01-07 11:00:30', '0', '20210107110030145000', '8', '0', '0', 'P1', '2021-01-07 11:00:30');
INSERT INTO `okr_info` VALUES ('71804', '20210107110030165003', '0', '3', '第三个月收益15%', '1', '0', '458', '2021-01-07 11:00:30', '0', '20210107110030145000', '8', '0', '0', 'P1', '2021-01-07 11:00:30');
INSERT INTO `okr_info` VALUES ('71805', '20210107111147836004', '0', '1', '改变世界', '0', '0', '460', '2021-01-07 11:11:48', '0', '', '8', '0', '0', 'P1', '2021-01-07 11:12:02');
INSERT INTO `okr_info` VALUES ('71806', '20210107111147842005', '0', '1', '剃个头', '1', '0', '460', '2021-01-07 11:11:48', '0', '20210107111147836004', '8', '0', '0', 'P1', '2021-01-07 11:11:48');
INSERT INTO `okr_info` VALUES ('71807', '20210107111147846006', '0', '2', '洗把脸', '1', '0', '460', '2021-01-07 11:11:48', '0', '20210107111147836004', '8', '0', '0', 'P1', '2021-01-07 11:11:48');
INSERT INTO `okr_info` VALUES ('71808', '20210107111147849007', '0', '3', '擦个油', '1', '0', '460', '2021-01-07 11:11:48', '0', '20210107111147836004', '8', '0', '0', 'P1', '2021-01-07 11:11:48');
INSERT INTO `okr_info` VALUES ('71809', '20210107111147853008', '0', '4', '抹个蜡', '1', '0', '460', '2021-01-07 11:11:48', '0', '20210107111147836004', '8', '0', '0', 'P1', '2021-01-07 11:11:48');

-- ----------------------------
-- Table structure for okr_message
-- ----------------------------
DROP TABLE IF EXISTS `okr_message`;
CREATE TABLE `okr_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL COMMENT '生产者',
  `consume_id` bigint(20) NOT NULL COMMENT '消费者（应该消费者）',
  `aconsume_id` bigint(20) NOT NULL COMMENT '消费者（实际消费者）',
  `object_id` bigint(20) NOT NULL COMMENT '消息关联的对象id',
  `content` longtext COMMENT '消息具体内容',
  `type` tinyint(4) DEFAULT '0' COMMENT '消息发送对象类型（0 :评论，1 :点赞, 2 系统，3 关联）',
  `category` tinyint(4) DEFAULT '0' COMMENT '消息类型（0 :通知，1 :系统, 2 全部，3 广播）',
  `degree` tinyint(4) DEFAULT '1' COMMENT '消息重要程度（0 :紧急，1 :一般，2:重要，保留字段，默认为1）',
  `status` tinyint(4) DEFAULT '0' COMMENT '消费状态（0 :未消费，1 :待处理，2 :待处理，3及其以上以消费）',
  `address` text COMMENT '消息应该发送的对象地址',
  `add_time` datetime DEFAULT NULL COMMENT '消息发起时间',
  `read_time` datetime DEFAULT NULL COMMENT '消息阅读时间（被标记为已读，但是没有处理）',
  `end_time` datetime DEFAULT NULL COMMENT '消息消费时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `id` (`product_id`,`consume_id`,`aconsume_id`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `product_id` (`product_id`) USING BTREE,
  KEY `consume_id` (`consume_id`) USING BTREE,
  KEY `object_id` (`object_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='okr 消息列表';

-- ----------------------------
-- Records of okr_message
-- ----------------------------

-- ----------------------------
-- Table structure for okr_message_meta
-- ----------------------------
DROP TABLE IF EXISTS `okr_message_meta`;
CREATE TABLE `okr_message_meta` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `message_id` bigint(20) DEFAULT NULL COMMENT '群发消息主键',
  `consume_id` bigint(20) DEFAULT NULL COMMENT '单个消费者id',
  `consume_time` datetime DEFAULT NULL COMMENT '消费时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `message_id` (`message_id`) USING BTREE,
  KEY `consume_id` (`consume_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='消息消费日志：主要是针对群发消息，记录那些人消费的消息';

-- ----------------------------
-- Records of okr_message_meta
-- ----------------------------

-- ----------------------------
-- Table structure for okr_project
-- ----------------------------
DROP TABLE IF EXISTS `okr_project`;
CREATE TABLE `okr_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(50) NOT NULL COMMENT '项目编码',
  `name` varchar(100) NOT NULL COMMENT '项目名称',
  `company` varchar(100) NOT NULL COMMENT '公司主体',
  `stage` varchar(50) NOT NULL COMMENT '项目阶段',
  `product_manager_id` bigint(20) NOT NULL COMMENT '产品经理',
  `project_manager_id` bigint(20) NOT NULL COMMENT '项目经理',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '数据状态（0正常、非正常）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='OKR项目管理表';

-- ----------------------------
-- Records of okr_project
-- ----------------------------

-- ----------------------------
-- Table structure for okr_project_group
-- ----------------------------
DROP TABLE IF EXISTS `okr_project_group`;
CREATE TABLE `okr_project_group` (
  `group_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `group_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `project_code` varchar(50) DEFAULT NULL COMMENT '项目编码',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=utf8 COMMENT='产品项目组';

-- ----------------------------
-- Records of okr_project_group
-- ----------------------------

-- ----------------------------
-- Table structure for okr_project_role
-- ----------------------------
DROP TABLE IF EXISTS `okr_project_role`;
CREATE TABLE `okr_project_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_code` varchar(50) DEFAULT '' COMMENT '项目编码',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `okr_id` int(11) DEFAULT NULL COMMENT 'OKR主键标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4984 DEFAULT CHARSET=utf8 COMMENT='项目OKR关系表';

-- ----------------------------
-- Records of okr_project_role
-- ----------------------------

-- ----------------------------
-- Table structure for okr_project_user
-- ----------------------------
DROP TABLE IF EXISTS `okr_project_user`;
CREATE TABLE `okr_project_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` bigint(11) DEFAULT NULL COMMENT '项目组id',
  `userid` bigint(11) DEFAULT NULL COMMENT '用户标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=254 DEFAULT CHARSET=utf8 COMMENT='项目组用户关系表';

-- ----------------------------
-- Records of okr_project_user
-- ----------------------------

-- ----------------------------
-- Table structure for okr_project_user_role
-- ----------------------------
DROP TABLE IF EXISTS `okr_project_user_role`;
CREATE TABLE `okr_project_user_role` (
  `user_id` bigint(20) NOT NULL,
  `project_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目权限表';

-- ----------------------------
-- Records of okr_project_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for okr_synergy
-- ----------------------------
DROP TABLE IF EXISTS `okr_synergy`;
CREATE TABLE `okr_synergy` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `okr_id` int(11) DEFAULT NULL COMMENT 'OKR主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103565 DEFAULT CHARSET=utf8 COMMENT='协同关系表';

-- ----------------------------
-- Records of okr_synergy
-- ----------------------------
INSERT INTO `okr_synergy` VALUES ('103564', '458', '71805');

-- ----------------------------
-- Table structure for okr_thumbs_up
-- ----------------------------
DROP TABLE IF EXISTS `okr_thumbs_up`;
CREATE TABLE `okr_thumbs_up` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `object_id` bigint(20) DEFAULT NULL COMMENT '点赞对象ID（O,KR,评论，对应的主键ID）',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `user_ip` varchar(255) NOT NULL COMMENT '点赞IP',
  `type` tinyint(4) DEFAULT NULL COMMENT '点赞类型（O,KR,评论，等等）',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `object_id` (`object_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='点赞';

-- ----------------------------
-- Records of okr_thumbs_up
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `blob_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `sched_name` varchar(120) NOT NULL,
  `calendar_name` varchar(200) NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `cron_expression` varchar(200) NOT NULL,
  `time_zone_id` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', '0/10 * * * * ?', 'GMT+08:00');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', '0/15 * * * * ?', 'GMT+08:00');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', '0/20 * * * * ?', 'GMT+08:00');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `entry_id` varchar(95) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `fired_time` bigint(13) NOT NULL,
  `sched_time` bigint(13) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) NOT NULL,
  `job_name` varchar(200) DEFAULT NULL,
  `job_group` varchar(200) DEFAULT NULL,
  `is_nonconcurrent` varchar(1) DEFAULT NULL,
  `requests_recovery` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `sched_name` varchar(120) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `job_class_name` varchar(250) NOT NULL,
  `is_durable` varchar(1) NOT NULL,
  `is_nonconcurrent` varchar(1) NOT NULL,
  `is_update_data` varchar(1) NOT NULL,
  `requests_recovery` varchar(1) NOT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', null, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3130202A202A202A202A203F74001172795461736B2E72794E6F506172616D7374000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000001740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E697A0E58F82EFBC8974000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', null, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3135202A202A202A202A203F74001572795461736B2E7279506172616D7328277279272974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000002740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E69C89E58F82EFBC8974000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', null, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3230202A202A202A202A203F74003872795461736B2E72794D756C7469706C65506172616D7328277279272C20747275652C20323030304C2C203331362E3530442C203130302974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000003740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E5A49AE58F82EFBC8974000133740001317800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `sched_name` varchar(120) NOT NULL,
  `lock_name` varchar(40) NOT NULL,
  PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RuoyiScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RuoyiScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `sched_name` varchar(120) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval` bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('RuoyiScheduler', 'LAPTOP-9OKVOK0N1609987448875', '1609990021680', '15000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `repeat_count` bigint(7) NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `str_prop_1` varchar(512) DEFAULT NULL,
  `str_prop_2` varchar(512) DEFAULT NULL,
  `str_prop_3` varchar(512) DEFAULT NULL,
  `int_prop_1` int(11) DEFAULT NULL,
  `int_prop_2` int(11) DEFAULT NULL,
  `long_prop_1` bigint(20) DEFAULT NULL,
  `long_prop_2` bigint(20) DEFAULT NULL,
  `dec_prop_1` decimal(13,4) DEFAULT NULL,
  `dec_prop_2` decimal(13,4) DEFAULT NULL,
  `bool_prop_1` varchar(1) DEFAULT NULL,
  `bool_prop_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `next_fire_time` bigint(13) DEFAULT NULL,
  `prev_fire_time` bigint(13) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `trigger_state` varchar(16) NOT NULL,
  `trigger_type` varchar(8) NOT NULL,
  `start_time` bigint(13) NOT NULL,
  `end_time` bigint(13) DEFAULT NULL,
  `calendar_name` varchar(200) DEFAULT NULL,
  `misfire_instr` smallint(2) DEFAULT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `sched_name` (`sched_name`,`job_name`,`job_group`),
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', 'TASK_CLASS_NAME1', 'DEFAULT', null, '1609987450000', '-1', '5', 'PAUSED', 'CRON', '1609987450000', '0', null, '2', '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', 'TASK_CLASS_NAME2', 'DEFAULT', null, '1609987455000', '-1', '5', 'PAUSED', 'CRON', '1609987451000', '0', null, '2', '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', 'TASK_CLASS_NAME3', 'DEFAULT', null, '1609987460000', '-1', '5', 'PAUSED', 'CRON', '1609987451000', '0', null, '2', '');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='参数配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-red', 'Y', 'admin', '2018-03-16 11:33:00', 'admin', '2020-06-23 18:12:42', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES ('2', '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '初始化密码 123456');
INSERT INTO `sys_config` VALUES ('3', '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '深黑主题theme-dark，浅色主题theme-light，深蓝主题theme-blue');
INSERT INTO `sys_config` VALUES ('4', '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2018-03-16 11:33:00', 'admin', '2020-06-03 17:30:09', '是否开启注册用户功能');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=223 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('100', '0', '0', '若依科技', '0', '若依', '15888888888', 'xiaoshuai2233@sina.com', '0', '0', 'admin', '2020-12-29 14:20:35', '', '2020-12-29 14:20:35');
INSERT INTO `sys_dept` VALUES ('101', '100', '0,100', '深圳总公司', '1', '若依', '15888888888', 'xiaoshuai2233@sina.com', '0', '0', 'admin', '2020-12-29 14:20:35', '', '2020-12-29 14:20:35');
INSERT INTO `sys_dept` VALUES ('102', '100', '0,100', '长沙分公司', '2', '若依', '15888888888', 'xiaoshuai2233@sina.com', '0', '0', 'admin', '2020-12-29 14:20:35', '', '2020-12-29 14:20:35');
INSERT INTO `sys_dept` VALUES ('103', '101', '0,100,101', '研发部门', '1', '若依', '15888888888', 'xiaoshuai2233@sina.com', '0', '0', 'admin', '2020-12-29 14:20:35', '', '2020-12-29 14:20:35');
INSERT INTO `sys_dept` VALUES ('104', '101', '0,100,101', '市场部门', '2', '若依', '15888888888', 'xiaoshuai2233@sina.com', '0', '0', 'admin', '2020-12-29 14:20:35', '', '2020-12-29 14:20:35');
INSERT INTO `sys_dept` VALUES ('105', '101', '0,100,101', '测试部门', '3', '若依', '15888888888', 'xiaoshuai2233@sina.com', '0', '0', 'admin', '2020-12-29 14:20:35', '', '2020-12-29 14:20:35');
INSERT INTO `sys_dept` VALUES ('106', '101', '0,100,101', '财务部门', '4', '若依', '15888888888', 'xiaoshuai2233@sina.com', '0', '0', 'admin', '2020-12-29 14:20:35', '', '2020-12-29 14:20:35');
INSERT INTO `sys_dept` VALUES ('107', '101', '0,100,101', '运维部门', '5', '若依', '15888888888', 'xiaoshuai2233@sina.com', '0', '0', 'admin', '2020-12-29 14:20:35', '', '2020-12-29 14:20:35');
INSERT INTO `sys_dept` VALUES ('108', '102', '0,100,102', '市场部门', '1', '若依', '15888888888', 'xiaoshuai2233@sina.com', '0', '0', 'admin', '2020-12-29 14:20:35', '', '2020-12-29 14:20:35');
INSERT INTO `sys_dept` VALUES ('109', '102', '0,100,102', '财务部门', '2', '若依', '15888888888', 'xiaoshuai2233@sina.com', '0', '0', 'admin', '2020-12-29 14:20:35', '', '2020-12-29 14:20:35');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(500) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES ('1', '1', '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别男');
INSERT INTO `sys_dict_data` VALUES ('2', '2', '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别女');
INSERT INTO `sys_dict_data` VALUES ('3', '3', '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别未知');
INSERT INTO `sys_dict_data` VALUES ('4', '1', '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '显示菜单');
INSERT INTO `sys_dict_data` VALUES ('5', '2', '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES ('6', '1', '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('7', '2', '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES ('8', '1', '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('9', '2', '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES ('10', '1', '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '默认分组');
INSERT INTO `sys_dict_data` VALUES ('11', '2', '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统分组');
INSERT INTO `sys_dict_data` VALUES ('12', '1', '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认是');
INSERT INTO `sys_dict_data` VALUES ('13', '2', '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认否');
INSERT INTO `sys_dict_data` VALUES ('14', '1', '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知');
INSERT INTO `sys_dict_data` VALUES ('15', '2', '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '公告');
INSERT INTO `sys_dict_data` VALUES ('16', '1', '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('17', '2', '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '关闭状态');
INSERT INTO `sys_dict_data` VALUES ('18', '99', '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '其他操作');
INSERT INTO `sys_dict_data` VALUES ('19', '1', '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '新增操作');
INSERT INTO `sys_dict_data` VALUES ('20', '2', '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '修改操作');
INSERT INTO `sys_dict_data` VALUES ('21', '3', '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '删除操作');
INSERT INTO `sys_dict_data` VALUES ('22', '4', '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '授权操作');
INSERT INTO `sys_dict_data` VALUES ('23', '5', '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导出操作');
INSERT INTO `sys_dict_data` VALUES ('24', '6', '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导入操作');
INSERT INTO `sys_dict_data` VALUES ('25', '7', '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '强退操作');
INSERT INTO `sys_dict_data` VALUES ('26', '8', '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '生成操作');
INSERT INTO `sys_dict_data` VALUES ('27', '9', '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '清空操作');
INSERT INTO `sys_dict_data` VALUES ('28', '1', '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('29', '2', '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES ('100', '1', '年度OKR', '1', 'okr_date_type', 'btn btn-info', 'default', 'Y', '0', 'admin', '2020-04-28 11:02:28', 'admin', '2020-06-10 11:24:22', '');
INSERT INTO `sys_dict_data` VALUES ('101', '0', '季度OKR', '0', 'okr_date_type', 'btn btn-primary', 'default', 'Y', '0', 'admin', '2020-04-28 11:02:50', 'admin', '2020-06-10 11:24:11', '');
INSERT INTO `sys_dict_data` VALUES ('102', '0', '生效状态', '0', 'row_status', '', 'info', 'Y', '0', 'admin', '2020-04-28 11:08:47', 'admin', '2020-04-28 17:55:21', '');
INSERT INTO `sys_dict_data` VALUES ('103', '1', '失效状态', '1', 'row_status', '', 'danger', 'Y', '0', 'admin', '2020-04-28 11:09:05', 'admin', '2020-04-28 17:55:27', '');
INSERT INTO `sys_dict_data` VALUES ('104', '0', '0', '0', 'ork_fraction', '', 'success', 'Y', '0', 'admin', '2020-04-28 13:14:27', 'admin', '2020-06-15 16:33:15', '0分');
INSERT INTO `sys_dict_data` VALUES ('105', '1', '0.1', '0.1', 'ork_fraction', '', 'success', 'Y', '0', 'admin', '2020-04-28 13:14:53', 'admin', '2020-06-15 15:53:32', '0.1分');
INSERT INTO `sys_dict_data` VALUES ('106', '2', '0.2', '0.2', 'ork_fraction', '', 'success', 'Y', '0', 'admin', '2020-04-28 13:16:13', 'admin', '2020-06-15 15:53:28', '0.2分');
INSERT INTO `sys_dict_data` VALUES ('107', '3', '0.3', '0.3', 'ork_fraction', '', 'success', 'Y', '0', 'admin', '2020-04-28 13:16:31', 'admin', '2020-06-15 15:53:25', '0.3分');
INSERT INTO `sys_dict_data` VALUES ('108', '4', '0.4', '0.4', 'ork_fraction', '', 'success', 'Y', '0', 'admin', '2020-04-28 13:17:12', 'admin', '2020-06-15 15:53:21', '0.4分');
INSERT INTO `sys_dict_data` VALUES ('109', '5', '0.5', '0.5', 'ork_fraction', '', 'success', 'Y', '0', 'admin', '2020-04-28 13:17:31', 'admin', '2020-06-15 15:53:18', '0.5分');
INSERT INTO `sys_dict_data` VALUES ('110', '6', '0.6', '0.6', 'ork_fraction', '', 'success', 'Y', '0', 'admin', '2020-04-28 13:17:50', 'admin', '2020-06-15 15:53:15', '0.6分');
INSERT INTO `sys_dict_data` VALUES ('111', '7', '0.7', '0.7', 'ork_fraction', '', 'success', 'Y', '0', 'admin', '2020-04-28 13:18:18', 'admin', '2020-06-15 15:53:10', '0.7分');
INSERT INTO `sys_dict_data` VALUES ('112', '8', '0.8', '0.8', 'ork_fraction', '', 'success', 'Y', '0', 'admin', '2020-04-28 13:18:41', 'admin', '2020-06-15 15:53:05', '0.8分');
INSERT INTO `sys_dict_data` VALUES ('113', '9', '0.9', '0.9', 'ork_fraction', '', 'success', 'Y', '0', 'admin', '2020-04-28 13:19:11', 'admin', '2020-06-15 15:52:49', '0.9分');
INSERT INTO `sys_dict_data` VALUES ('114', '10', '1', '1', 'ork_fraction', '', 'success', 'Y', '0', 'admin', '2020-04-28 13:19:39', 'admin', '2020-06-15 15:52:58', '1分');
INSERT INTO `sys_dict_data` VALUES ('115', '1', 'P1', 'P1', 'okr_level', null, 'success', 'Y', '0', 'admin', '2020-04-28 13:25:35', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('116', '2', 'P2', 'P2', 'okr_level', null, 'success', 'Y', '0', 'admin', '2020-04-28 13:25:48', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('117', '3', 'P3', 'P3', 'okr_level', null, 'success', 'Y', '0', 'admin', '2020-04-28 13:26:04', '', null, null);
INSERT INTO `sys_dict_data` VALUES ('118', '0', '0', '0', 'okr_row_type', '', 'warning', 'Y', '0', 'admin', '2020-04-28 13:37:00', 'admin', '2020-06-15 15:54:28', '目标');
INSERT INTO `sys_dict_data` VALUES ('119', '1', '1', '1', 'okr_row_type', '', 'danger', 'Y', '0', 'admin', '2020-04-28 13:37:29', 'admin', '2020-06-15 15:54:18', '关键结果');
INSERT INTO `sys_dict_data` VALUES ('120', '1', 'Concept', 'Concept', 'okr_project_stage', '', 'success', 'Y', '0', 'admin', '2020-05-29 11:23:36', 'admin', '2020-05-29 11:24:10', 'Concept');
INSERT INTO `sys_dict_data` VALUES ('121', '2', 'EVT', 'EVT', 'okr_project_stage', null, 'primary', 'Y', '0', 'admin', '2020-05-29 11:23:51', '', null, 'EVT');
INSERT INTO `sys_dict_data` VALUES ('122', '3', 'PVT', 'PVT', 'okr_project_stage', null, 'info', 'Y', '0', 'admin', '2020-05-29 11:24:30', '', null, 'PVT');
INSERT INTO `sys_dict_data` VALUES ('123', '4', 'MP', 'MP', 'okr_project_stage', null, 'warning', 'Y', '0', 'admin', '2020-05-29 11:24:51', '', null, 'MP');
INSERT INTO `sys_dict_data` VALUES ('124', '1', '中文', 'zh_CN', 'sys_show_language', '', '', 'Y', '0', 'admin', '2020-06-03 17:41:03', 'admin', '2020-06-04 18:46:29', '系统多语言 -- 中文');
INSERT INTO `sys_dict_data` VALUES ('125', '2', 'English', 'en_US', 'sys_show_language', '', '', 'Y', '0', 'admin', '2020-06-03 17:41:20', 'admin', '2020-06-04 18:46:27', '系统多语言 -- 英文');
INSERT INTO `sys_dict_data` VALUES ('126', '3', '日本語', 'ja_JP', 'sys_show_language', '', '', 'Y', '0', 'admin', '2020-06-03 17:41:35', 'admin', '2020-06-04 18:46:24', '系统多语言 -- 日文');
INSERT INTO `sys_dict_data` VALUES ('127', '1', 'quarterly_desc_zh', 'Objective 不是工作的罗列，而是经过筛选的重点目标', 'okr_quarterly_info', '', '', 'Y', '0', 'admin', '2020-06-16 15:58:24', 'admin', '2020-06-23 21:24:34', '');
INSERT INTO `sys_dict_data` VALUES ('128', '2', 'quarterly_desc_en', 'Objective is not a list of jobs, or a key target that passes the screening', 'okr_quarterly_info', '', '', 'Y', '0', 'admin', '2020-06-16 15:58:50', 'admin', '2020-06-23 21:24:38', '');
INSERT INTO `sys_dict_data` VALUES ('129', '3', 'quarterly_desc_ja', '目的は、仕事のリストではなく、スクリーニングに合格する主要な目標ではありません', 'okr_quarterly_info', '', '', 'Y', '0', 'admin', '2020-06-16 15:59:12', 'admin', '2020-06-23 21:24:54', '');
INSERT INTO `sys_dict_data` VALUES ('130', '1', 'year_desc_cn', '今年用你的雄心壮志，去征服这个世界吧！', 'okr_year_info', '', '', 'Y', '0', 'admin', '2020-06-16 16:07:32', 'admin', '2020-06-16 16:08:02', '');
INSERT INTO `sys_dict_data` VALUES ('131', '2', 'year_desc_en', 'Use your ambition to conquer the world this year!', 'okr_year_info', '', '', 'Y', '0', 'admin', '2020-06-16 16:09:10', 'admin', '2020-06-16 16:10:35', '');
INSERT INTO `sys_dict_data` VALUES ('132', '3', 'year_desc_ja', '今年はあなたの壮大な志で、この世界を征服しましょう。', 'okr_year_info', '', '', 'Y', '0', 'admin', '2020-06-16 16:09:35', 'admin', '2020-06-16 17:28:35', '');
INSERT INTO `sys_dict_data` VALUES ('133', '1', 'okr_krplaceholder_cn', '用数字衡量价值，用结果对自己负责。', 'okr_kr_placeholder', '', '', 'Y', '0', 'admin', '2020-06-17 15:34:02', 'admin', '2020-06-17 15:45:04', '');
INSERT INTO `sys_dict_data` VALUES ('134', '2', 'okr_krplaceholder_en', 'Measure value with numbers, and take responsibility for yourself with results.', 'okr_kr_placeholder', '', '', 'Y', '0', 'admin', '2020-06-17 15:34:18', 'admin', '2020-06-17 15:45:26', '');
INSERT INTO `sys_dict_data` VALUES ('135', '3', 'okr_krplaceholder_ja', '数字で価値を量り、結果で自分に責任を持つ。', 'okr_kr_placeholder', '', '', 'Y', '0', 'admin', '2020-06-17 15:34:29', 'admin', '2020-06-17 15:45:12', 'fasf');
INSERT INTO `sys_dict_data` VALUES ('136', '1', 'okr_oplaceholder_cn', '如果不是需要团队付出额外努力才能达成，请不要写,目标要与上下级配合团队、人员、部门对齐，避免各自为政。', 'okr_o_placeholder', '', '', 'Y', '0', 'admin', '2020-06-17 15:40:27', 'admin', '2020-06-23 17:20:12', '');
INSERT INTO `sys_dict_data` VALUES ('137', '2', 'okr_oplaceholder_en', 'If it doesn\'t take the team to make extra efforts to achieve it, please don\'t write. The goal should be aligned with the team, personnel and departments of the superior and subordinate to avoid being separated.', 'okr_o_placeholder', '', '', 'Y', '0', 'admin', '2020-06-17 15:40:34', 'admin', '2020-06-23 17:20:33', '');
INSERT INTO `sys_dict_data` VALUES ('138', '3', 'okr_oplaceholder_ja', 'チームが追加の努力を払わないと達成できません。書かないでください。目標は上と下のチーム、人員、部門と揃えて、各自が自制しないようにしてください。', 'okr_o_placeholder', '', '', 'Y', '0', 'admin', '2020-06-17 15:40:39', 'admin', '2020-06-23 17:20:23', '');
INSERT INTO `sys_dict_data` VALUES ('139', '1', 'okr_remark_cn', '请在此处填写备注', 'okr_remark_placeholder', '', '', 'Y', '0', 'admin', '2020-06-23 10:59:52', 'admin', '2020-06-23 22:16:07', '');
INSERT INTO `sys_dict_data` VALUES ('140', '2', 'okr_remark_en', 'Please fill in the remarks here', 'okr_remark_placeholder', '', '', 'Y', '0', 'admin', '2020-06-23 11:00:11', 'admin', '2020-06-23 22:16:26', '');
INSERT INTO `sys_dict_data` VALUES ('141', '3', 'ork_remark_ja', 'こちらに備考をご記入ください', 'okr_remark_placeholder', '', '', 'Y', '0', 'admin', '2020-06-23 11:00:27', 'admin', '2020-06-23 22:16:10', '');
INSERT INTO `sys_dict_data` VALUES ('142', '1', 'ork_great_remark_cn', '1. 评分规则', 'okr_grade_remark', '', '', 'Y', '0', 'admin', '2020-06-24 15:04:19', 'admin', '2020-06-24 17:04:09', '');
INSERT INTO `sys_dict_data` VALUES ('143', '2', 'ork_great_remark_en', '1. Grading rules', 'okr_grade_remark', '', '', 'Y', '0', 'admin', '2020-06-24 15:04:38', 'admin', '2020-06-24 17:04:18', '');
INSERT INTO `sys_dict_data` VALUES ('144', '3', 'ork_great_remark_ja', '1.採点ルール', 'okr_grade_remark', '', '', 'Y', '0', 'admin', '2020-06-24 15:04:55', 'admin', '2020-06-24 17:04:27', '');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES ('1', '用户性别', 'sys_user_sex', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户性别列表');
INSERT INTO `sys_dict_type` VALUES ('2', '菜单状态', 'sys_show_hide', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES ('3', '系统开关', 'sys_normal_disable', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统开关列表');
INSERT INTO `sys_dict_type` VALUES ('4', '任务状态', 'sys_job_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务状态列表');
INSERT INTO `sys_dict_type` VALUES ('5', '任务分组', 'sys_job_group', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务分组列表');
INSERT INTO `sys_dict_type` VALUES ('6', '系统是否', 'sys_yes_no', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统是否列表');
INSERT INTO `sys_dict_type` VALUES ('7', '通知类型', 'sys_notice_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知类型列表');
INSERT INTO `sys_dict_type` VALUES ('8', '通知状态', 'sys_notice_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知状态列表');
INSERT INTO `sys_dict_type` VALUES ('9', '操作类型', 'sys_oper_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作类型列表');
INSERT INTO `sys_dict_type` VALUES ('10', '系统状态', 'sys_common_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录状态列表');
INSERT INTO `sys_dict_type` VALUES ('11', '系统语言', 'sys_show_language', '0', 'admin', '2020-06-03 17:38:42', 'admin', '2020-06-03 18:09:26', '系统多语言');
INSERT INTO `sys_dict_type` VALUES ('100', 'OKR周期类型', 'okr_date_type', '0', 'admin', '2020-04-28 11:01:05', '', null, '年度季度区分');
INSERT INTO `sys_dict_type` VALUES ('101', '数据状态', 'row_status', '0', 'admin', '2020-04-28 11:07:27', '', null, '数据状态');
INSERT INTO `sys_dict_type` VALUES ('102', 'OKR分数', 'ork_fraction', '0', 'admin', '2020-04-28 13:13:38', '', null, 'OKR分数打分');
INSERT INTO `sys_dict_type` VALUES ('103', 'OKR优先级', 'okr_level', '0', 'admin', '2020-04-28 13:22:38', '', null, 'OKR优先级');
INSERT INTO `sys_dict_type` VALUES ('104', 'OKR类型', 'okr_row_type', '0', 'admin', '2020-04-28 13:36:39', 'admin', '2020-06-16 15:56:58', 'OKR类型： 目标和关键结果');
INSERT INTO `sys_dict_type` VALUES ('105', '项目阶段', 'okr_project_stage', '0', 'admin', '2020-05-29 11:22:40', '', null, '项目阶段');
INSERT INTO `sys_dict_type` VALUES ('107', 'OKR季度信息', 'okr_quarterly_info', '0', 'admin', '2020-06-16 15:54:50', 'admin', '2020-06-16 16:00:32', '季度OKR信息：显示在添加OKR页面');
INSERT INTO `sys_dict_type` VALUES ('108', 'OKR年度信息', 'okr_year_info', '0', 'admin', '2020-06-16 15:56:24', 'admin', '2020-06-16 16:00:26', '年度OKR信息：显示在添加OKR页面');
INSERT INTO `sys_dict_type` VALUES ('111', 'OKR O Placeholder', 'okr_o_placeholder', '0', 'admin', '2020-06-17 15:26:32', 'admin', '2020-06-17 15:47:16', 'OKR O 描述：显示在添加OKR页面');
INSERT INTO `sys_dict_type` VALUES ('112', 'OKR KR Placeholder', 'okr_kr_placeholder', '0', 'admin', '2020-06-17 15:28:50', 'admin', '2020-06-17 15:47:08', 'OKR K 描述：显示在添加OKR页面');
INSERT INTO `sys_dict_type` VALUES ('113', 'KR 备注默认', 'okr_remark_placeholder', '0', 'admin', '2020-06-23 10:58:05', '', null, 'KR 备注默认');
INSERT INTO `sys_dict_type` VALUES ('114', 'KR 评分标准', 'okr_grade_remark', '0', 'admin', '2020-06-24 15:03:11', '', null, 'KR 评分标准');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES ('1', '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_job` VALUES ('2', '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_job` VALUES ('3', '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log` (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------
INSERT INTO `sys_job_log` VALUES ('1', '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '系统默认（无参） 总共耗时：5毫秒', '0', '', '2020-06-05 17:11:50');
INSERT INTO `sys_job_log` VALUES ('2', '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', '', '2020-06-05 17:11:51');
INSERT INTO `sys_job_log` VALUES ('3', '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', '', '2020-06-05 17:12:01');
INSERT INTO `sys_job_log` VALUES ('4', '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', '', '2020-06-05 17:12:11');
INSERT INTO `sys_job_log` VALUES ('5', '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '系统默认（无参） 总共耗时：4毫秒', '0', '', '2020-06-19 15:33:20');
INSERT INTO `sys_job_log` VALUES ('6', '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '系统默认（无参） 总共耗时：0毫秒', '0', '', '2020-06-19 15:33:30');
INSERT INTO `sys_job_log` VALUES ('7', '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：3毫秒', '0', '', '2020-06-22 14:27:35');

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor` (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12322 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES ('12299', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-01-07 09:49:23');
INSERT INTO `sys_logininfor` VALUES ('12300', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2021-01-07 10:27:56');
INSERT INTO `sys_logininfor` VALUES ('12301', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-01-07 10:44:47');
INSERT INTO `sys_logininfor` VALUES ('12302', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2021-01-07 10:58:43');
INSERT INTO `sys_logininfor` VALUES ('12303', 'y1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-01-07 10:58:51');
INSERT INTO `sys_logininfor` VALUES ('12304', 'y1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2021-01-07 11:00:47');
INSERT INTO `sys_logininfor` VALUES ('12305', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-01-07 11:00:55');
INSERT INTO `sys_logininfor` VALUES ('12306', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2021-01-07 11:06:50');
INSERT INTO `sys_logininfor` VALUES ('12307', 'f1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-01-07 11:07:00');
INSERT INTO `sys_logininfor` VALUES ('12308', 'f1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2021-01-07 11:07:07');
INSERT INTO `sys_logininfor` VALUES ('12309', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-01-07 11:07:12');
INSERT INTO `sys_logininfor` VALUES ('12310', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2021-01-07 11:07:37');
INSERT INTO `sys_logininfor` VALUES ('12311', 'f1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-01-07 11:07:48');
INSERT INTO `sys_logininfor` VALUES ('12312', 'f1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2021-01-07 11:12:07');
INSERT INTO `sys_logininfor` VALUES ('12313', 'y1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2021-01-07 11:12:14');
INSERT INTO `sys_logininfor` VALUES ('12314', 'y1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2021-01-07 11:12:17');
INSERT INTO `sys_logininfor` VALUES ('12315', 'y1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2021-01-07 11:13:15');
INSERT INTO `sys_logininfor` VALUES ('12316', 'y1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-01-07 11:13:19');
INSERT INTO `sys_logininfor` VALUES ('12317', 'y1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2021-01-07 11:13:34');
INSERT INTO `sys_logininfor` VALUES ('12318', 'f1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-01-07 11:13:42');
INSERT INTO `sys_logininfor` VALUES ('12319', 'f1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2021-01-07 11:13:54');
INSERT INTO `sys_logininfor` VALUES ('12320', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-01-07 11:14:09');
INSERT INTO `sys_logininfor` VALUES ('12321', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2021-01-07 11:26:08');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) DEFAULT '#' COMMENT '请求地址',
  `target` varchar(20) DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2094 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', '1', '#', '', 'M', '0', '', 'fa fa-gear', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统管理目录');
INSERT INTO `sys_menu` VALUES ('2', '系统监控', '0', '2', '#', '', 'M', '0', '', 'fa fa-video-camera', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统监控目录');
INSERT INTO `sys_menu` VALUES ('3', '系统工具', '0', '3', '#', '', 'M', '0', '', 'fa fa-bars', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统工具目录');
INSERT INTO `sys_menu` VALUES ('100', '用户管理', '1', '1', '/system/user', '', 'C', '0', 'system:user:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户管理菜单');
INSERT INTO `sys_menu` VALUES ('101', '角色管理', '1', '2', '/system/role', '', 'C', '0', 'system:role:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '角色管理菜单');
INSERT INTO `sys_menu` VALUES ('102', '菜单管理', '1', '3', '/system/menu', '', 'C', '0', 'system:menu:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES ('103', '部门管理', '1', '4', '/system/dept', '', 'C', '0', 'system:dept:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '部门管理菜单');
INSERT INTO `sys_menu` VALUES ('104', '岗位管理', '1', '5', '/system/post', '', 'C', '0', 'system:post:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES ('105', '字典管理', '1', '6', '/system/dict', '', 'C', '0', 'system:dict:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '字典管理菜单');
INSERT INTO `sys_menu` VALUES ('106', '参数设置', '1', '7', '/system/config', '', 'C', '0', 'system:config:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '参数设置菜单');
INSERT INTO `sys_menu` VALUES ('107', '通知公告', '1', '8', '/system/notice', '', 'C', '0', 'system:notice:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知公告菜单');
INSERT INTO `sys_menu` VALUES ('108', '日志管理', '1', '9', '#', '', 'M', '0', '', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '日志管理菜单');
INSERT INTO `sys_menu` VALUES ('109', '在线用户', '2', '1', '/monitor/online', '', 'C', '0', 'monitor:online:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '在线用户菜单');
INSERT INTO `sys_menu` VALUES ('110', '定时任务', '2', '2', '/monitor/job', '', 'C', '0', 'monitor:job:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '定时任务菜单');
INSERT INTO `sys_menu` VALUES ('111', '数据监控', '2', '3', '/monitor/data', '', 'C', '0', 'monitor:data:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '数据监控菜单');
INSERT INTO `sys_menu` VALUES ('112', '服务监控', '2', '3', '/monitor/server', '', 'C', '0', 'monitor:server:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '服务监控菜单');
INSERT INTO `sys_menu` VALUES ('113', '表单构建', '3', '1', '/tool/build', '', 'C', '0', 'tool:build:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '表单构建菜单');
INSERT INTO `sys_menu` VALUES ('114', '代码生成', '3', '2', '/tool/gen', '', 'C', '0', 'tool:gen:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '代码生成菜单');
INSERT INTO `sys_menu` VALUES ('115', '系统接口', '3', '3', '/tool/swagger', '', 'C', '0', 'tool:swagger:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统接口菜单');
INSERT INTO `sys_menu` VALUES ('500', '操作日志', '108', '1', '/monitor/operlog', '', 'C', '0', 'monitor:operlog:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作日志菜单');
INSERT INTO `sys_menu` VALUES ('501', '登录日志', '108', '2', '/monitor/logininfor', '', 'C', '0', 'monitor:logininfor:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录日志菜单');
INSERT INTO `sys_menu` VALUES ('1000', '用户查询', '100', '1', '#', '', 'F', '0', 'system:user:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1001', '用户新增', '100', '2', '#', '', 'F', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1002', '用户修改', '100', '3', '#', '', 'F', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1003', '用户删除', '100', '4', '#', '', 'F', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1004', '用户导出', '100', '5', '#', '', 'F', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1005', '用户导入', '100', '6', '#', '', 'F', '0', 'system:user:import', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1006', '重置密码', '100', '7', '#', '', 'F', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1007', '角色查询', '101', '1', '#', '', 'F', '0', 'system:role:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1008', '角色新增', '101', '2', '#', '', 'F', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1009', '角色修改', '101', '3', '#', '', 'F', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1010', '角色删除', '101', '4', '#', '', 'F', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1011', '角色导出', '101', '5', '#', '', 'F', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1012', '菜单查询', '102', '1', '#', '', 'F', '0', 'system:menu:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1013', '菜单新增', '102', '2', '#', '', 'F', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1014', '菜单修改', '102', '3', '#', '', 'F', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1015', '菜单删除', '102', '4', '#', '', 'F', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1016', '部门查询', '103', '1', '#', '', 'F', '0', 'system:dept:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1017', '部门新增', '103', '2', '#', '', 'F', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1018', '部门修改', '103', '3', '#', '', 'F', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1019', '部门删除', '103', '4', '#', '', 'F', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1020', '岗位查询', '104', '1', '#', '', 'F', '0', 'system:post:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1021', '岗位新增', '104', '2', '#', '', 'F', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1022', '岗位修改', '104', '3', '#', '', 'F', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1023', '岗位删除', '104', '4', '#', '', 'F', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1024', '岗位导出', '104', '5', '#', '', 'F', '0', 'system:post:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1025', '字典查询', '105', '1', '#', '', 'F', '0', 'system:dict:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1026', '字典新增', '105', '2', '#', '', 'F', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1027', '字典修改', '105', '3', '#', '', 'F', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1028', '字典删除', '105', '4', '#', '', 'F', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1029', '字典导出', '105', '5', '#', '', 'F', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1030', '参数查询', '106', '1', '#', '', 'F', '0', 'system:config:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1031', '参数新增', '106', '2', '#', '', 'F', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1032', '参数修改', '106', '3', '#', '', 'F', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1033', '参数删除', '106', '4', '#', '', 'F', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1034', '参数导出', '106', '5', '#', '', 'F', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1035', '公告查询', '107', '1', '#', '', 'F', '0', 'system:notice:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1036', '公告新增', '107', '2', '#', '', 'F', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1037', '公告修改', '107', '3', '#', '', 'F', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1038', '公告删除', '107', '4', '#', '', 'F', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1039', '操作查询', '500', '1', '#', '', 'F', '0', 'monitor:operlog:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1040', '操作删除', '500', '2', '#', '', 'F', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1041', '详细信息', '500', '3', '#', '', 'F', '0', 'monitor:operlog:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1042', '日志导出', '500', '4', '#', '', 'F', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1043', '登录查询', '501', '1', '#', '', 'F', '0', 'monitor:logininfor:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1044', '登录删除', '501', '2', '#', '', 'F', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1045', '日志导出', '501', '3', '#', '', 'F', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1046', '账户解锁', '501', '4', '#', '', 'F', '0', 'monitor:logininfor:unlock', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1047', '在线查询', '109', '1', '#', '', 'F', '0', 'monitor:online:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1048', '批量强退', '109', '2', '#', '', 'F', '0', 'monitor:online:batchForceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1049', '单条强退', '109', '3', '#', '', 'F', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1050', '任务查询', '110', '1', '#', '', 'F', '0', 'monitor:job:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1051', '任务新增', '110', '2', '#', '', 'F', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1052', '任务修改', '110', '3', '#', '', 'F', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1053', '任务删除', '110', '4', '#', '', 'F', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1054', '状态修改', '110', '5', '#', '', 'F', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1055', '任务详细', '110', '6', '#', '', 'F', '0', 'monitor:job:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1056', '任务导出', '110', '7', '#', '', 'F', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1057', '生成查询', '114', '1', '#', '', 'F', '0', 'tool:gen:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1058', '生成修改', '114', '2', '#', '', 'F', '0', 'tool:gen:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1059', '生成删除', '114', '3', '#', '', 'F', '0', 'tool:gen:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1060', '预览代码', '114', '4', '#', '', 'F', '0', 'tool:gen:preview', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1061', '生成代码', '114', '5', '#', '', 'F', '0', 'tool:gen:code', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('2000', 'OKR周期', '3', '1', '/system/cycle', '', 'C', '0', 'system:cycle:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', 'OKR周期菜单');
INSERT INTO `sys_menu` VALUES ('2001', 'OKR周期查询', '2000', '1', '#', '', 'F', '0', 'system:cycle:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2002', 'OKR周期新增', '2000', '2', '#', '', 'F', '0', 'system:cycle:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2003', 'OKR周期修改', '2000', '3', '#', '', 'F', '0', 'system:cycle:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2004', 'OKR周期删除', '2000', '4', '#', '', 'F', '0', 'system:cycle:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2005', 'OKR周期导出', '2000', '5', '#', '', 'F', '0', 'system:cycle:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2006', 'OKR信息', '3', '1', '/system/info', '', 'C', '0', 'system:info:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', 'OKR信息菜单');
INSERT INTO `sys_menu` VALUES ('2007', 'OKR信息查询', '2006', '1', '#', '', 'F', '0', 'system:info:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2008', 'OKR信息新增', '2006', '2', '#', '', 'F', '0', 'system:info:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2009', 'OKR信息修改', '2006', '3', '#', '', 'F', '0', 'system:info:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2010', 'OKR信息删除', '2006', '4', '#', '', 'F', '0', 'system:info:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2011', 'OKR信息导出', '2006', '5', '#', '', 'F', '0', 'system:info:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2012', 'OKR管理', '0', '4', '#', 'menuItem', 'M', '0', null, 'fa fa-paper-plane-o', 'admin', '2020-04-28 14:32:44', '', null, '');
INSERT INTO `sys_menu` VALUES ('2013', '我的OKR', '2012', '1', '/okr/info', 'menuItem', 'C', '0', 'okr:info:view', 'fa fa-dot-circle-o', 'admin', '2020-04-28 15:09:54', '', null, '');
INSERT INTO `sys_menu` VALUES ('2014', '查询OKR', '2013', '1', '#', 'menuItem', 'F', '0', 'okr:info:list', '#', 'admin', '2020-04-28 15:15:58', 'admin', '2020-04-28 15:16:29', '');
INSERT INTO `sys_menu` VALUES ('2015', '添加OKR', '2013', '2', '#', 'menuItem', 'F', '0', 'okr:info:add', '#', 'admin', '2020-04-28 15:16:59', '', null, '');
INSERT INTO `sys_menu` VALUES ('2016', '编辑OKR', '2013', '3', '#', 'menuItem', 'F', '0', 'okr:info:edit', '#', 'admin', '2020-04-28 15:17:52', '', null, '');
INSERT INTO `sys_menu` VALUES ('2017', '删除OKR', '2013', '4', '#', 'menuItem', 'F', '0', 'okr:info:remove', '#', 'admin', '2020-04-28 15:18:42', '', null, '');
INSERT INTO `sys_menu` VALUES ('2018', '查询OKR周期', '2013', '5', '#', 'menuItem', 'F', '0', 'okr:cycle:useList', '#', 'admin', '2020-04-29 11:26:02', '', null, '');
INSERT INTO `sys_menu` VALUES ('2019', 'OKR协同关系', '3', '1', '/system/synergy', 'menuItem', 'C', '0', 'system:synergy:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2020-06-04 17:33:55', '协同关系菜单');
INSERT INTO `sys_menu` VALUES ('2020', '协同关系查询', '2019', '1', '#', '', 'F', '0', 'system:synergy:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2021', '协同关系新增', '2019', '2', '#', '', 'F', '0', 'system:synergy:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2022', '协同关系修改', '2019', '3', '#', '', 'F', '0', 'system:synergy:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2023', '协同关系删除', '2019', '4', '#', '', 'F', '0', 'system:synergy:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2024', '协同关系导出', '2019', '5', '#', '', 'F', '0', 'system:synergy:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2025', '协同OKR', '2012', '3', '/okr/synergy', 'menuItem', 'C', '0', 'okr:synergy:view', 'fa fa-user-plus', 'admin', '2020-05-09 11:13:05', 'admin', '2020-06-23 16:50:39', '');
INSERT INTO `sys_menu` VALUES ('2026', '查询协同OKR', '2025', '1', '#', 'menuItem', 'F', '0', 'okr:synergy:list', '#', 'admin', '2020-05-09 13:30:40', '', null, '');
INSERT INTO `sys_menu` VALUES ('2027', 'OKR项目管理', '3', '1', '/system/project', '', 'C', '0', 'system:project:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', 'OKR项目管理菜单');
INSERT INTO `sys_menu` VALUES ('2028', 'OKR项目管理查询', '2027', '1', '#', 'menuItem', 'F', '0', 'system:project:list,system:project:projectLis', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2020-06-08 18:03:23', '');
INSERT INTO `sys_menu` VALUES ('2029', 'OKR项目管理新增', '2027', '2', '#', '', 'F', '0', 'system:project:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2030', 'OKR项目管理修改', '2027', '3', '#', '', 'F', '0', 'system:project:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2031', 'OKR项目管理删除', '2027', '4', '#', '', 'F', '0', 'system:project:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2032', 'OKR项目管理导出', '2027', '5', '#', '', 'F', '0', 'system:project:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2033', 'OKR项目组管理', '3', '1', '/system/group', 'menuItem', 'C', '0', 'system:group:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2020-05-29 13:40:39', '产品项目组菜单');
INSERT INTO `sys_menu` VALUES ('2034', 'OKR项目组查询', '2033', '1', '#', 'menuItem', 'F', '0', 'system:group:treeList', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2020-05-29 15:06:21', '');
INSERT INTO `sys_menu` VALUES ('2035', 'OKR项目组新增', '2033', '2', '#', 'menuItem', 'F', '0', 'system:group:add', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2020-05-29 13:41:28', '');
INSERT INTO `sys_menu` VALUES ('2036', 'OKR项目组修改', '2033', '3', '#', 'menuItem', 'F', '0', 'system:group:edit', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2020-05-29 13:41:16', '');
INSERT INTO `sys_menu` VALUES ('2037', 'OKR项目组删除', '2033', '4', '#', 'menuItem', 'F', '0', 'system:group:remove', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2020-05-29 13:41:44', '');
INSERT INTO `sys_menu` VALUES ('2038', 'OKR项目组导出', '2033', '5', '#', 'menuItem', 'F', '0', 'system:group:export', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2020-05-29 13:41:54', '');
INSERT INTO `sys_menu` VALUES ('2046', 'OKR项目组用户关系', '3', '1', '/system/projectUser', 'menuItem', 'C', '0', 'system:projectUser:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2020-06-02 16:36:01', '项目组用户关系菜单');
INSERT INTO `sys_menu` VALUES ('2047', '项目组用户关系查询', '2046', '1', '#', '', 'F', '0', 'system:projectUser:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2048', '项目组用户关系新增', '2046', '2', '#', '', 'F', '0', 'system:projectUser:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2049', '项目组用户关系修改', '2046', '3', '#', '', 'F', '0', 'system:projectUser:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2050', '项目组用户关系删除', '2046', '4', '#', '', 'F', '0', 'system:projectUser:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2051', '项目组用户关系导出', '2046', '5', '#', '', 'F', '0', 'system:projectUser:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2064', '选择OKR对齐视图', '2025', '2', '#', 'menuItem', 'F', '0', 'okr:info:alignList', '#', 'admin', '2020-06-07 17:48:03', '', null, '');
INSERT INTO `sys_menu` VALUES ('2065', '追加KR', '2013', '3', '#', 'menuItem', 'F', '0', 'okr:info:saveKeyResult', '#', 'admin', '2020-06-08 15:05:52', '', null, '');
INSERT INTO `sys_menu` VALUES ('2066', '查询项目列表', '2046', '5', '#', 'menuItem', 'F', '0', 'system:project:list', '#', 'admin', '2020-06-08 17:48:45', '', null, '');
INSERT INTO `sys_menu` VALUES ('2067', '项目组用户权限', '2046', '6', '#', 'menuItem', 'F', '0', 'system:project:projectList', '#', 'admin', '2020-06-08 17:50:43', '', null, '');
INSERT INTO `sys_menu` VALUES ('2068', '我的视图', '2012', '2', '/okr/treeMap', 'menuItem', 'C', '0', 'okr:tree:view', 'fa fa-address-card-o', 'admin', '2020-06-10 18:08:57', 'admin', '2020-06-23 16:50:31', '');
INSERT INTO `sys_menu` VALUES ('2069', '查询KR清单', '2068', '1', '#', 'menuItem', 'F', '0', 'okr:tree:list', '#', 'admin', '2020-06-14 15:35:25', '', null, '');
INSERT INTO `sys_menu` VALUES ('2070', '项目OKR', '3', '8', '/okr/projectUser', 'menuItem', 'C', '0', 'okr:projectUser:view', '#', 'admin', '2020-06-15 14:22:29', 'admin', '2020-07-01 11:18:56', '');
INSERT INTO `sys_menu` VALUES ('2071', '查询分组项目OKR', '2070', '1', '#', 'menuItem', 'F', '0', 'system:projectUser:okrList', '#', 'admin', '2020-06-17 10:53:34', '', null, '');
INSERT INTO `sys_menu` VALUES ('2072', '查询项目清单', '2070', '2', '#', 'menuItem', 'F', '0', 'system:project:projectList', '#', 'admin', '2020-06-17 11:01:30', '', null, '');
INSERT INTO `sys_menu` VALUES ('2073', 'OKR附加属性表', '3', '1', '/system/attach', '', 'C', '0', 'system:attach:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', 'OKR附加属性表菜单');
INSERT INTO `sys_menu` VALUES ('2074', 'OKR附加属性表查询', '2073', '1', '#', '', 'F', '0', 'system:attach:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2075', 'OKR附加属性表新增', '2073', '2', '#', '', 'F', '0', 'system:attach:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2076', 'OKR附加属性表修改', '2073', '3', '#', '', 'F', '0', 'system:attach:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2077', 'OKR附加属性表删除', '2073', '4', '#', '', 'F', '0', 'system:attach:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2078', 'OKR附加属性表导出', '2073', '5', '#', '', 'F', '0', 'system:attach:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2079', '批量协同用户查询', '2013', '7', '#', 'menuItem', 'F', '0', 'system:user:selectList', '#', 'admin', '2020-06-20 14:30:28', 'admin', '2020-06-22 14:32:30', '');
INSERT INTO `sys_menu` VALUES ('2080', '批量协同用户插入', '2013', '8', '#', 'menuItem', 'F', '0', 'okr:info:batchCollaboration', '#', 'admin', '2020-06-22 14:32:55', '', null, '');
INSERT INTO `sys_menu` VALUES ('2081', '批量协同用户查询', '100', '2', '#', 'menuItem', 'F', '0', 'system:user:selectList', '#', 'admin', '2020-06-25 13:01:19', '', null, '');
INSERT INTO `sys_menu` VALUES ('2082', '项目人员OKR', '3', '11', '/system/GroupManager', 'menuItem', 'C', '0', 'okr:GroupManager:view', 'fa fa-address-card', 'admin', '2020-06-28 13:24:52', 'admin', '2020-07-01 11:18:23', '');
INSERT INTO `sys_menu` VALUES ('2083', '查询项目组成员', '2082', '1', '#', 'menuItem', 'F', '0', 'system:projectUser:list', '#', 'admin', '2020-06-28 13:25:25', '', null, '');
INSERT INTO `sys_menu` VALUES ('2084', '部门OKR', '3', '13', '/okr/orgUser', 'menuItem', 'C', '0', 'okr:orgUser:userView', 'fa fa-address-book-o', 'admin', '2020-06-28 13:26:16', 'admin', '2020-06-28 14:08:34', '');
INSERT INTO `sys_menu` VALUES ('2085', '查询用户信息', '2084', '1', '#', 'menuItem', 'F', '0', 'okr:orgUser:userList', '#', 'admin', '2020-06-28 13:38:14', '', null, '');
INSERT INTO `sys_menu` VALUES ('2086', '查询项目列表', '2082', '2', '#', 'menuItem', 'F', '0', 'system:project:projectList', '#', 'admin', '2020-06-28 14:22:20', '', null, '');
INSERT INTO `sys_menu` VALUES ('2087', '项目权限', '3', '1', '/system/projectRole', '', 'C', '0', 'system:projectRole:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '项目权限菜单');
INSERT INTO `sys_menu` VALUES ('2088', '项目权限查询', '2087', '1', '#', '', 'F', '0', 'system:projectRole:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2089', '项目权限新增', '2087', '2', '#', '', 'F', '0', 'system:projectRole:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2090', '项目权限修改', '2087', '3', '#', '', 'F', '0', 'system:projectRole:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2091', '项目权限删除', '2087', '4', '#', '', 'F', '0', 'system:projectRole:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2092', '项目权限导出', '2087', '5', '#', '', 'F', '0', 'system:projectRole:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2093', '查询用户协同清单', '2084', '2', '#', 'menuItem', 'F', '0', 'okr:info:synergyOkrUsers', '#', 'admin', '2020-07-03 18:39:50', '', null, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) DEFAULT NULL COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='通知公告表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES ('3', 'OKR系统通知', '1', '<p>各位同事：税局紧急提醒！6月30日前务必完成《2019年个税汇算清缴》！否则将被罚款，影响个人信用！&nbsp;</p><p>有任何问题请及时联系我，此次为最后一次提醒，请未处理的同事抓紧时间，谢谢。</p>', '0', 'admin', '2020-06-28 17:00:42', 'admin', '2020-06-28 17:02:18', null);
INSERT INTO `sys_notice` VALUES ('4', 'OKR系统公告', '2', '<p><font color=\"#676a6c\">各位同事：税局紧急提醒！6月30日前务必完成《2019年个税汇算清缴》！否则将被罚款，影响个人信用！&nbsp;</font></p><p><font color=\"#676a6c\">有任何问题请及时联系我，此次为最后一次提醒，请未处理的同事抓紧时间，谢谢。</font></p>', '0', 'admin', '2020-06-28 17:04:38', '', null, null);

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=69479 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES ('69468', '重置密码', '2', 'com.ruoyi.web.controller.system.SysProfileController.resetPwd()', 'POST', '1', 'admin', null, '/system/user/profile/resetPwd', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"1\" ],\r\n  \"loginName\" : [ \"admin\" ],\r\n  \"oldPassword\" : [ \"PowerVision2020\" ],\r\n  \"newPassword\" : [ \"admin123\" ],\r\n  \"confirm\" : [ \"admin123\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2021-01-07 09:49:39');
INSERT INTO `sys_oper_log` VALUES ('69469', '个人信息', '2', 'com.ruoyi.web.controller.system.SysProfileController.update()', 'POST', '1', 'admin', null, '/system/user/profile/update', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"\" ],\r\n  \"userName\" : [ \"策码奔腾\" ],\r\n  \"phonenumber\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"xiaoshuai2233@sina.com\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"language\" : [ \"zh_CN\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2021-01-07 09:51:42');
INSERT INTO `sys_oper_log` VALUES ('69470', '用户管理', '1', 'com.ruoyi.web.controller.system.SysUserController.addSave()', 'POST', '1', 'admin', null, '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"103\" ],\r\n  \"userName\" : [ \"员工1\" ],\r\n  \"deptName\" : [ \"研发部门\" ],\r\n  \"phonenumber\" : [ \"1888888887\" ],\r\n  \"email\" : [ \"y1@sina.com\" ],\r\n  \"loginName\" : [ \"y1\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"2\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"2\" ],\r\n  \"postIds\" : [ \"4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2021-01-07 10:58:36');
INSERT INTO `sys_oper_log` VALUES ('69471', 'OKR信息', '1', 'com.ruoyi.web.controller.okr.MyOkrInfoController.addSave()', 'POST', '1', 'y1', '研发部门', '/okr/info/add', '127.0.0.1', '内网IP', '{\r\n  \"seasonObjective\" : [ \"先定个小目标三个月收益30%\" ],\r\n  \"yearObjective\" : [ \"\" ],\r\n  \"seasonItem[]\" : [ \"第一个月收益5%\", \"第2个月收益10%\", \"第三个月收益15%\" ],\r\n  \"yearItem[]\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2021-01-07 11:00:30');
INSERT INTO `sys_oper_log` VALUES ('69472', '用户管理', '1', 'com.ruoyi.web.controller.system.SysUserController.addSave()', 'POST', '1', 'admin', null, '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"107\" ],\r\n  \"userName\" : [ \"y2\" ],\r\n  \"deptName\" : [ \"运维部门\" ],\r\n  \"phonenumber\" : [ \"1888888886\" ],\r\n  \"email\" : [ \"y2@sina.com\" ],\r\n  \"loginName\" : [ \"y2\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"2\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"2\" ],\r\n  \"postIds\" : [ \"4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2021-01-07 11:01:56');
INSERT INTO `sys_oper_log` VALUES ('69473', '用户管理', '1', 'com.ruoyi.web.controller.system.SysUserController.addSave()', 'POST', '1', 'admin', null, '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"106\" ],\r\n  \"userName\" : [ \"副总\" ],\r\n  \"deptName\" : [ \"财务部门\" ],\r\n  \"phonenumber\" : [ \"1888888885\" ],\r\n  \"email\" : [ \"f1@sina.com\" ],\r\n  \"loginName\" : [ \"f1\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"2\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"2\" ],\r\n  \"postIds\" : [ \"4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2021-01-07 11:05:14');
INSERT INTO `sys_oper_log` VALUES ('69474', '角色管理', '1', 'com.ruoyi.web.controller.system.SysRoleController.addSave()', 'POST', '1', 'admin', null, '/system/role/add', '127.0.0.1', '内网IP', '{\r\n  \"roleName\" : [ \"管理者角色\" ],\r\n  \"roleKey\" : [ \"test_manager\" ],\r\n  \"roleSort\" : [ \"3\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"menuIds\" : [ \"3,2082,2083,2086,2084,2085,2093,2012,2013,2014,2015,2065,2016,2017,2018,2079,2080,2068,2069,2025,2026,2064\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2021-01-07 11:06:46');
INSERT INTO `sys_oper_log` VALUES ('69475', '用户管理', '2', 'com.ruoyi.web.controller.system.SysUserController.editSave()', 'POST', '1', 'admin', null, '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"460\" ],\r\n  \"deptId\" : [ \"106\" ],\r\n  \"userName\" : [ \"副总\" ],\r\n  \"dept.deptName\" : [ \"财务部门\" ],\r\n  \"phonenumber\" : [ \"18888888884\" ],\r\n  \"email\" : [ \"f1@sina.com\" ],\r\n  \"loginName\" : [ \"f1\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"2\", \"128\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"2,128\" ],\r\n  \"postIds\" : [ \"4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2021-01-07 11:07:34');
INSERT INTO `sys_oper_log` VALUES ('69476', 'OKR信息', '1', 'com.ruoyi.web.controller.okr.MyOkrInfoController.addSave()', 'POST', '1', 'f1', '财务部门', '/okr/info/add', '127.0.0.1', '内网IP', '{\r\n  \"seasonObjective\" : [ \"改变世界\" ],\r\n  \"yearObjective\" : [ \"\" ],\r\n  \"seasonItem[]\" : [ \"剃个头\", \"洗把脸\", \"擦个油\", \"抹个蜡\" ],\r\n  \"yearItem[]\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2021-01-07 11:11:48');
INSERT INTO `sys_oper_log` VALUES ('69477', 'OKR信息', '2', 'com.ruoyi.web.controller.okr.MyOkrInfoController.editSave()', 'POST', '1', 'f1', '财务部门', '/okr/info/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"71805\" ],\r\n  \"rowId\" : [ \"20210107111147836004\" ],\r\n  \"synergyUsers\" : [ \"458\" ],\r\n  \"sortId\" : [ \"0\" ],\r\n  \"process\" : [ \"0\" ],\r\n  \"scoreStar\" : [ \"\" ],\r\n  \"confidenceStar\" : [ \"\" ],\r\n  \"rowCode\" : [ \"1\" ],\r\n  \"content\" : [ \"改变世界\" ],\r\n  \"rowType\" : [ \"0\" ],\r\n  \"userId\" : [ \"460\" ],\r\n  \"parentId\" : [ \"\" ],\r\n  \"cycleId\" : [ \"8\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"priority\" : [ \"P1\" ],\r\n  \"remarkTextContent\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2021-01-07 11:12:03');
INSERT INTO `sys_oper_log` VALUES ('69478', '保存OKR对齐关系', '4', 'com.ruoyi.web.controller.okr.MyOkrInfoController.saveAlignOkr()', 'POST', '1', 'y1', '研发部门', '/okr/info/saveAlignOkr', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"71801,71802,71803,71804\" ],\r\n  \"parentId\" : [ \"71805\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2021-01-07 11:13:29');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='岗位信息表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('1', 'ceo', '董事长', '1', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('2', 'se', '项目经理', '2', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('3', 'hr', '人力资源', '3', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('4', 'user', '普通员工', '4', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('5', 'finance', '财务总监', '5', '0', 'admin', '2020-06-03 15:35:41', '', null, '财务总监');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'admin', '1', '1', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');
INSERT INTO `sys_role` VALUES ('2', '普通角色', 'common', '2', '2', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-06-15 09:48:41', '普通角色');
INSERT INTO `sys_role` VALUES ('128', '管理者角色', 'test_manager', '3', '1', '0', '0', 'admin', '2021-01-07 11:06:45', '', null, null);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和部门关联表';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('2', '100');
INSERT INTO `sys_role_dept` VALUES ('2', '101');
INSERT INTO `sys_role_dept` VALUES ('2', '105');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('2', '2012');
INSERT INTO `sys_role_menu` VALUES ('2', '2013');
INSERT INTO `sys_role_menu` VALUES ('2', '2014');
INSERT INTO `sys_role_menu` VALUES ('2', '2015');
INSERT INTO `sys_role_menu` VALUES ('2', '2016');
INSERT INTO `sys_role_menu` VALUES ('2', '2017');
INSERT INTO `sys_role_menu` VALUES ('2', '2018');
INSERT INTO `sys_role_menu` VALUES ('2', '2025');
INSERT INTO `sys_role_menu` VALUES ('2', '2026');
INSERT INTO `sys_role_menu` VALUES ('2', '2064');
INSERT INTO `sys_role_menu` VALUES ('2', '2065');
INSERT INTO `sys_role_menu` VALUES ('2', '2068');
INSERT INTO `sys_role_menu` VALUES ('2', '2069');
INSERT INTO `sys_role_menu` VALUES ('128', '3');
INSERT INTO `sys_role_menu` VALUES ('128', '2012');
INSERT INTO `sys_role_menu` VALUES ('128', '2013');
INSERT INTO `sys_role_menu` VALUES ('128', '2014');
INSERT INTO `sys_role_menu` VALUES ('128', '2015');
INSERT INTO `sys_role_menu` VALUES ('128', '2016');
INSERT INTO `sys_role_menu` VALUES ('128', '2017');
INSERT INTO `sys_role_menu` VALUES ('128', '2018');
INSERT INTO `sys_role_menu` VALUES ('128', '2025');
INSERT INTO `sys_role_menu` VALUES ('128', '2026');
INSERT INTO `sys_role_menu` VALUES ('128', '2064');
INSERT INTO `sys_role_menu` VALUES ('128', '2065');
INSERT INTO `sys_role_menu` VALUES ('128', '2068');
INSERT INTO `sys_role_menu` VALUES ('128', '2069');
INSERT INTO `sys_role_menu` VALUES ('128', '2079');
INSERT INTO `sys_role_menu` VALUES ('128', '2080');
INSERT INTO `sys_role_menu` VALUES ('128', '2082');
INSERT INTO `sys_role_menu` VALUES ('128', '2083');
INSERT INTO `sys_role_menu` VALUES ('128', '2084');
INSERT INTO `sys_role_menu` VALUES ('128', '2085');
INSERT INTO `sys_role_menu` VALUES ('128', '2086');
INSERT INTO `sys_role_menu` VALUES ('128', '2093');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) DEFAULT '' COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户 01注册用户）',
  `language` varchar(30) DEFAULT 'zh_CN' COMMENT '用户设置显示的语言（zh_CN,en_US,ja_JP等）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `avatar` varchar(150) DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `salt` varchar(20) DEFAULT '' COMMENT '盐加密',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=461 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '195', 'admin', '策码奔腾', '00', 'zh_CN', 'xiaoshuai2233@sina.com', '15888888888', 'http://gcsbucket.oss-cn-hongkong.aliyuncs.com//okr/file/1593076213736/1593076213732.png', 'f24fa9fe64b95904b98ae773f1ae4349', '4ff7a7', '0', '0', '0', '127.0.0.1', '2021-01-07 11:14:09', 'admin', '2018-03-16 11:33:00', 'ry', '2021-01-07 11:14:09', '管理员');
INSERT INTO `sys_user` VALUES ('458', '103', 'y1', '员工1', '00', 'zh_CN', 'y1@sina.com', '1888888887', '', '9b0a51544f1147dd12406d5deec78b05', '41b2cd', '0', '0', '0', '127.0.0.1', '2021-01-07 11:13:19', 'admin', '2021-01-07 10:58:36', '', '2021-01-07 11:13:19', null);
INSERT INTO `sys_user` VALUES ('459', '107', 'y2', 'y2', '00', 'zh_CN', 'y2@sina.com', '1888888886', '', 'beb9f83c19d42ce927a4911edace8b8a', 'f957c5', '0', '0', '0', '', null, 'admin', '2021-01-07 11:01:56', '', null, null);
INSERT INTO `sys_user` VALUES ('460', '106', 'f1', '副总', '00', 'zh_CN', 'f1@sina.com', '18888888884', '', '6a56d70e8b115dc6d92fef1224d5cc2e', 'a78b48', '0', '0', '0', '127.0.0.1', '2021-01-07 11:13:41', 'admin', '2021-01-07 11:05:14', 'admin', '2021-01-07 11:13:42', '');

-- ----------------------------
-- Table structure for sys_user_online
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_online`;
CREATE TABLE `sys_user_online` (
  `sessionId` varchar(50) NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) DEFAULT '0' COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在线用户记录';

-- ----------------------------
-- Records of sys_user_online
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与岗位关联表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES ('1', '1');
INSERT INTO `sys_user_post` VALUES ('2', '2');
INSERT INTO `sys_user_post` VALUES ('458', '4');
INSERT INTO `sys_user_post` VALUES ('459', '4');
INSERT INTO `sys_user_post` VALUES ('460', '4');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');
INSERT INTO `sys_user_role` VALUES ('458', '2');
INSERT INTO `sys_user_role` VALUES ('459', '2');
INSERT INTO `sys_user_role` VALUES ('460', '2');
INSERT INTO `sys_user_role` VALUES ('460', '128');

-- ----------------------------
-- Table structure for user_test
-- ----------------------------
DROP TABLE IF EXISTS `user_test`;
CREATE TABLE `user_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_test
-- ----------------------------
