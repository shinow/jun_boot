/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : ry_act6

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2020-04-08 11:36:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for biz_leave
-- ----------------------------
DROP TABLE IF EXISTS `biz_leave`;
CREATE TABLE `biz_leave` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` char(20) DEFAULT NULL COMMENT '请假类型',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `reason` varchar(500) DEFAULT NULL COMMENT '原因',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `total_time` bigint(11) DEFAULT NULL COMMENT '请假时长，单位秒',
  `instance_id` varchar(32) DEFAULT NULL COMMENT '流程实例ID',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `apply_user` varchar(64) DEFAULT NULL COMMENT '申请人',
  `apply_time` datetime DEFAULT NULL COMMENT '申请时间',
  `reality_start_time` datetime DEFAULT NULL COMMENT '实际开始时间',
  `reality_end_time` datetime DEFAULT NULL COMMENT '实际结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of biz_leave
-- ----------------------------

-- ----------------------------
-- Table structure for biz_product
-- ----------------------------
DROP TABLE IF EXISTS `biz_product`;
CREATE TABLE `biz_product` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `NAME` varchar(100) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of biz_product
-- ----------------------------
INSERT INTO `biz_product` VALUES ('1', '生发剂');
INSERT INTO `biz_product` VALUES ('2', '假发');
INSERT INTO `biz_product` VALUES ('3', '帽子');
INSERT INTO `biz_product` VALUES ('4', '颈椎病康复指南');
INSERT INTO `biz_product` VALUES ('5', '板砖');
INSERT INTO `biz_product` VALUES ('6', '速效救心丸');
INSERT INTO `biz_product` VALUES ('7', '一日老板体验券');

-- ----------------------------
-- Table structure for biz_purchase
-- ----------------------------
DROP TABLE IF EXISTS `biz_purchase`;
CREATE TABLE `biz_purchase` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `TITLE` varchar(100) DEFAULT NULL COMMENT '标题',
  `AMOUNT` decimal(16,2) DEFAULT '0.00' COMMENT '金额',
  `INSTANCE_ID` varchar(32) DEFAULT NULL,
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建者',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '更新者',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `APPLY_USER` varchar(64) DEFAULT NULL COMMENT '申请人',
  `APPLY_TIME` datetime DEFAULT NULL COMMENT '申请时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of biz_purchase
-- ----------------------------

-- ----------------------------
-- Table structure for biz_purchase_product
-- ----------------------------
DROP TABLE IF EXISTS `biz_purchase_product`;
CREATE TABLE `biz_purchase_product` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `PURCHASE_ID` bigint(20) DEFAULT NULL COMMENT '采购ID',
  `PRODUCT_ID` bigint(20) DEFAULT NULL COMMENT '产品ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of biz_purchase_product
-- ----------------------------

-- ----------------------------
-- Table structure for biz_todo_item
-- ----------------------------
DROP TABLE IF EXISTS `biz_todo_item`;
CREATE TABLE `biz_todo_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `item_name` varchar(100) DEFAULT NULL COMMENT '事项标题',
  `item_content` varchar(500) DEFAULT NULL COMMENT '事项内容',
  `module` varchar(50) DEFAULT NULL COMMENT '模块名称 (必须以 uri 一致)',
  `task_id` varchar(64) DEFAULT NULL COMMENT '任务 ID',
  `instance_id` varchar(32) DEFAULT NULL COMMENT '流程实例 ID',
  `task_name` varchar(50) DEFAULT NULL COMMENT '任务名称 (必须以表单页面名称一致)',
  `node_name` varchar(50) DEFAULT NULL COMMENT '节点名称',
  `is_view` char(1) DEFAULT '0' COMMENT '是否查看 default 0 (0 否 1 是)',
  `is_handle` char(1) DEFAULT '0' COMMENT '是否处理 default 0 (0 否 1 是)',
  `todo_user_id` varchar(20) DEFAULT NULL COMMENT '待办人 ID',
  `todo_user_name` varchar(30) DEFAULT NULL COMMENT '待办人名称',
  `handle_user_id` varchar(20) DEFAULT NULL COMMENT '处理人 ID',
  `handle_user_name` varchar(30) DEFAULT NULL COMMENT '处理人名称',
  `todo_time` datetime DEFAULT NULL COMMENT '通知时间',
  `handle_time` datetime DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='待办事项表';

-- ----------------------------
-- Records of biz_todo_item
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='代码生成业务表';

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES ('1', 'biz_purchase', '', 'BizPurchase', 'crud', 'com.ruoyi.system', 'system', 'purchase', null, 'ruoyi', null, 'admin', '2020-04-02 19:56:11', '', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='代码生成业务表字段';

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES ('1', '1', 'ID', '主键ID', 'bigint(20)', 'Long', 'id', '1', '1', null, '1', null, null, null, 'EQ', 'input', '', '1', 'admin', '2020-04-02 19:56:11', '', null);
INSERT INTO `gen_table_column` VALUES ('2', '1', 'TITLE', '标题', 'varchar(100)', 'String', 'title', '0', '0', null, '1', '1', '1', '1', 'EQ', 'input', '', '2', 'admin', '2020-04-02 19:56:11', '', null);
INSERT INTO `gen_table_column` VALUES ('3', '1', 'AMOUNT', '金额', 'decimal(16,2)', 'Double', 'amount', '0', '0', null, '1', '1', '1', '1', 'EQ', 'input', '', '3', 'admin', '2020-04-02 19:56:11', '', null);
INSERT INTO `gen_table_column` VALUES ('4', '1', 'INSTANCE_ID', null, 'varchar(32)', 'String', 'instanceId', '0', '0', null, '1', '1', '1', '1', 'EQ', 'input', '', '4', 'admin', '2020-04-02 19:56:11', '', null);
INSERT INTO `gen_table_column` VALUES ('5', '1', 'CREATE_BY', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', null, '1', '1', '1', '1', 'EQ', 'input', '', '5', 'admin', '2020-04-02 19:56:11', '', null);
INSERT INTO `gen_table_column` VALUES ('6', '1', 'CREATE_TIME', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', null, '1', '1', '1', '1', 'EQ', 'datetime', '', '6', 'admin', '2020-04-02 19:56:11', '', null);
INSERT INTO `gen_table_column` VALUES ('7', '1', 'UPDATE_BY', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', null, '1', '1', '1', '1', 'EQ', 'input', '', '7', 'admin', '2020-04-02 19:56:11', '', null);
INSERT INTO `gen_table_column` VALUES ('8', '1', 'UPDATE_TIME', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', null, '1', '1', '1', '1', 'EQ', 'datetime', '', '8', 'admin', '2020-04-02 19:56:11', '', null);
INSERT INTO `gen_table_column` VALUES ('9', '1', 'APPLY_USER', '申请人', 'varchar(64)', 'String', 'applyUser', '0', '0', null, '1', '1', '1', '1', 'EQ', 'input', '', '9', 'admin', '2020-04-02 19:56:11', '', null);
INSERT INTO `gen_table_column` VALUES ('10', '1', 'APPLY_TIME', '申请时间', 'datetime', 'Date', 'applyTime', '0', '0', null, '1', '1', '1', '1', 'EQ', 'datetime', '', '10', 'admin', '2020-04-02 19:56:11', '', null);

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
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
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
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', '0/15 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', '0/20 * * * * ?', 'Asia/Shanghai');

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
INSERT INTO `qrtz_scheduler_state` VALUES ('RuoyiScheduler', 'DESKTOP-JBK8SRU1586177762889', '1586177799104', '15000');

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
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
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
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
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
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', 'TASK_CLASS_NAME1', 'DEFAULT', null, '1586177770000', '-1', '5', 'PAUSED', 'CRON', '1586177762000', '0', null, '2', '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', 'TASK_CLASS_NAME2', 'DEFAULT', null, '1586177775000', '-1', '5', 'PAUSED', 'CRON', '1586177763000', '0', null, '2', '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', 'TASK_CLASS_NAME3', 'DEFAULT', null, '1586177780000', '-1', '5', 'PAUSED', 'CRON', '1586177763000', '0', null, '2', '');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='参数配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES ('2', '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '初始化密码 123456');
INSERT INTO `sys_config` VALUES ('3', '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '深黑主题theme-dark，浅色主题theme-light，深蓝主题theme-blue');

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
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('100', '0', '0', '若依科技', '0', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('101', '100', '0,100', '深圳总公司', '1', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('102', '100', '0,100', '长沙分公司', '2', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('103', '101', '0,100,101', '研发部门', '1', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('104', '101', '0,100,101', '市场部门', '2', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('105', '101', '0,100,101', '测试部门', '3', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('106', '101', '0,100,101', '财务部门', '4', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('107', '101', '0,100,101', '运维部门', '5', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('108', '102', '0,100,102', '市场部门', '1', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('109', '102', '0,100,102', '财务部门', '2', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
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
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COMMENT='字典数据表';

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
INSERT INTO `sys_dict_data` VALUES ('18', '1', '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '新增操作');
INSERT INTO `sys_dict_data` VALUES ('19', '2', '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '修改操作');
INSERT INTO `sys_dict_data` VALUES ('20', '3', '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '删除操作');
INSERT INTO `sys_dict_data` VALUES ('21', '4', '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '授权操作');
INSERT INTO `sys_dict_data` VALUES ('22', '5', '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导出操作');
INSERT INTO `sys_dict_data` VALUES ('23', '6', '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导入操作');
INSERT INTO `sys_dict_data` VALUES ('24', '7', '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '强退操作');
INSERT INTO `sys_dict_data` VALUES ('25', '8', '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '生成操作');
INSERT INTO `sys_dict_data` VALUES ('26', '9', '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '清空操作');
INSERT INTO `sys_dict_data` VALUES ('27', '1', '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('28', '2', '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES ('100', '1', '普通', 'leave', 'biz_leave_type', '', 'success', 'Y', '0', 'admin', '2020-02-28 21:27:30', 'admin', '2020-03-27 22:38:19', '');
INSERT INTO `sys_dict_data` VALUES ('101', '2', '会签', 'leaveCounterSign', 'biz_leave_type', '', 'warning', 'Y', '0', 'admin', '2020-02-28 21:27:58', 'admin', '2020-03-27 22:38:10', '');

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
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

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
INSERT INTO `sys_dict_type` VALUES ('100', '请假类型', 'biz_leave_type', '0', 'admin', '2020-02-28 21:25:16', '', null, null);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=689 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES ('100', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-24 21:08:59');
INSERT INTO `sys_logininfor` VALUES ('101', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-25 20:56:36');
INSERT INTO `sys_logininfor` VALUES ('102', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-25 21:31:33');
INSERT INTO `sys_logininfor` VALUES ('103', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-02-25 21:40:02');
INSERT INTO `sys_logininfor` VALUES ('104', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-25 21:40:05');
INSERT INTO `sys_logininfor` VALUES ('105', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-25 21:45:47');
INSERT INTO `sys_logininfor` VALUES ('106', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-02-25 22:03:12');
INSERT INTO `sys_logininfor` VALUES ('107', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-25 22:03:16');
INSERT INTO `sys_logininfor` VALUES ('108', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-25 22:05:48');
INSERT INTO `sys_logininfor` VALUES ('109', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-25 23:35:01');
INSERT INTO `sys_logininfor` VALUES ('110', 'admin', '127.0.0.1', '内网IP', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2020-02-25 23:35:27');
INSERT INTO `sys_logininfor` VALUES ('111', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-26 19:53:17');
INSERT INTO `sys_logininfor` VALUES ('112', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-27 19:34:13');
INSERT INTO `sys_logininfor` VALUES ('113', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-02-27 20:17:30');
INSERT INTO `sys_logininfor` VALUES ('114', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-27 20:17:35');
INSERT INTO `sys_logininfor` VALUES ('115', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-27 21:15:10');
INSERT INTO `sys_logininfor` VALUES ('116', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-28 20:31:57');
INSERT INTO `sys_logininfor` VALUES ('117', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-28 21:06:42');
INSERT INTO `sys_logininfor` VALUES ('118', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-28 21:06:45');
INSERT INTO `sys_logininfor` VALUES ('119', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-28 21:32:41');
INSERT INTO `sys_logininfor` VALUES ('120', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-28 21:32:53');
INSERT INTO `sys_logininfor` VALUES ('121', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-28 21:47:59');
INSERT INTO `sys_logininfor` VALUES ('122', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-28 21:48:13');
INSERT INTO `sys_logininfor` VALUES ('123', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-28 21:50:27');
INSERT INTO `sys_logininfor` VALUES ('124', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-28 21:50:32');
INSERT INTO `sys_logininfor` VALUES ('125', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-28 21:51:58');
INSERT INTO `sys_logininfor` VALUES ('126', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-28 21:52:05');
INSERT INTO `sys_logininfor` VALUES ('127', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-28 22:28:12');
INSERT INTO `sys_logininfor` VALUES ('128', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-28 22:28:25');
INSERT INTO `sys_logininfor` VALUES ('129', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-28 22:34:47');
INSERT INTO `sys_logininfor` VALUES ('130', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-02-28 22:34:54');
INSERT INTO `sys_logininfor` VALUES ('131', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-28 22:34:58');
INSERT INTO `sys_logininfor` VALUES ('132', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-29 19:24:41');
INSERT INTO `sys_logininfor` VALUES ('133', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-29 20:02:25');
INSERT INTO `sys_logininfor` VALUES ('134', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-29 20:02:33');
INSERT INTO `sys_logininfor` VALUES ('135', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-29 20:04:24');
INSERT INTO `sys_logininfor` VALUES ('136', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-29 20:04:35');
INSERT INTO `sys_logininfor` VALUES ('137', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-29 20:09:21');
INSERT INTO `sys_logininfor` VALUES ('138', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-29 20:09:26');
INSERT INTO `sys_logininfor` VALUES ('139', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-29 20:12:05');
INSERT INTO `sys_logininfor` VALUES ('140', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-29 20:12:14');
INSERT INTO `sys_logininfor` VALUES ('141', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-29 20:17:08');
INSERT INTO `sys_logininfor` VALUES ('142', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-29 20:17:12');
INSERT INTO `sys_logininfor` VALUES ('143', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-29 20:20:18');
INSERT INTO `sys_logininfor` VALUES ('144', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-29 20:20:21');
INSERT INTO `sys_logininfor` VALUES ('145', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-29 20:22:40');
INSERT INTO `sys_logininfor` VALUES ('146', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-29 20:23:06');
INSERT INTO `sys_logininfor` VALUES ('147', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-29 20:24:12');
INSERT INTO `sys_logininfor` VALUES ('148', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-29 20:24:21');
INSERT INTO `sys_logininfor` VALUES ('149', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-29 20:24:56');
INSERT INTO `sys_logininfor` VALUES ('150', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-29 20:25:07');
INSERT INTO `sys_logininfor` VALUES ('151', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-29 20:25:31');
INSERT INTO `sys_logininfor` VALUES ('152', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-29 20:25:39');
INSERT INTO `sys_logininfor` VALUES ('153', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-29 20:26:13');
INSERT INTO `sys_logininfor` VALUES ('154', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-29 20:26:23');
INSERT INTO `sys_logininfor` VALUES ('155', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-29 20:26:39');
INSERT INTO `sys_logininfor` VALUES ('156', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-29 20:26:48');
INSERT INTO `sys_logininfor` VALUES ('157', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-29 20:27:12');
INSERT INTO `sys_logininfor` VALUES ('158', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-29 20:27:16');
INSERT INTO `sys_logininfor` VALUES ('159', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-29 20:27:19');
INSERT INTO `sys_logininfor` VALUES ('160', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-02-29 20:27:24');
INSERT INTO `sys_logininfor` VALUES ('161', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-29 20:27:30');
INSERT INTO `sys_logininfor` VALUES ('162', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-02-29 20:27:48');
INSERT INTO `sys_logininfor` VALUES ('163', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-02-29 20:27:57');
INSERT INTO `sys_logininfor` VALUES ('164', 'axianlu', '127.0.0.1', '内网IP', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2020-02-29 20:34:08');
INSERT INTO `sys_logininfor` VALUES ('165', 'axianlu', '127.0.0.1', '内网IP', 'Internet Explorer 11', 'Windows 10', '0', '退出成功', '2020-02-29 20:34:29');
INSERT INTO `sys_logininfor` VALUES ('166', 'rensm', '127.0.0.1', '内网IP', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2020-02-29 20:34:41');
INSERT INTO `sys_logininfor` VALUES ('167', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-19 08:28:13');
INSERT INTO `sys_logininfor` VALUES ('168', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-19 08:28:19');
INSERT INTO `sys_logininfor` VALUES ('169', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-19 08:28:47');
INSERT INTO `sys_logininfor` VALUES ('170', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-19 08:28:53');
INSERT INTO `sys_logininfor` VALUES ('171', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-19 08:28:56');
INSERT INTO `sys_logininfor` VALUES ('172', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-19 08:29:08');
INSERT INTO `sys_logininfor` VALUES ('173', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-19 08:39:04');
INSERT INTO `sys_logininfor` VALUES ('174', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-19 08:39:18');
INSERT INTO `sys_logininfor` VALUES ('175', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-19 22:20:33');
INSERT INTO `sys_logininfor` VALUES ('176', 'admin', '127.0.0.1', '内网IP', 'Microsoft Edge', 'Windows 10', '0', '登录成功', '2020-03-19 22:24:11');
INSERT INTO `sys_logininfor` VALUES ('177', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-20 10:08:45');
INSERT INTO `sys_logininfor` VALUES ('178', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-20 21:28:23');
INSERT INTO `sys_logininfor` VALUES ('179', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-21 15:35:26');
INSERT INTO `sys_logininfor` VALUES ('180', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-21 17:10:45');
INSERT INTO `sys_logininfor` VALUES ('181', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-21 17:13:33');
INSERT INTO `sys_logininfor` VALUES ('182', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-21 19:08:22');
INSERT INTO `sys_logininfor` VALUES ('183', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-21 19:35:54');
INSERT INTO `sys_logininfor` VALUES ('184', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-21 19:36:07');
INSERT INTO `sys_logininfor` VALUES ('185', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-21 19:37:20');
INSERT INTO `sys_logininfor` VALUES ('186', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-21 19:37:27');
INSERT INTO `sys_logininfor` VALUES ('187', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-21 19:50:35');
INSERT INTO `sys_logininfor` VALUES ('188', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-21 22:58:38');
INSERT INTO `sys_logininfor` VALUES ('189', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-21 23:05:33');
INSERT INTO `sys_logininfor` VALUES ('190', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-21 23:05:38');
INSERT INTO `sys_logininfor` VALUES ('191', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-21 23:05:42');
INSERT INTO `sys_logininfor` VALUES ('192', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-21 23:10:26');
INSERT INTO `sys_logininfor` VALUES ('193', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-21 23:24:30');
INSERT INTO `sys_logininfor` VALUES ('194', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-21 23:24:36');
INSERT INTO `sys_logininfor` VALUES ('195', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-21 23:24:39');
INSERT INTO `sys_logininfor` VALUES ('196', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-21 23:24:53');
INSERT INTO `sys_logininfor` VALUES ('197', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-21 23:25:03');
INSERT INTO `sys_logininfor` VALUES ('198', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-22 00:05:58');
INSERT INTO `sys_logininfor` VALUES ('199', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-22 00:06:07');
INSERT INTO `sys_logininfor` VALUES ('200', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-22 01:17:36');
INSERT INTO `sys_logininfor` VALUES ('201', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-22 01:17:45');
INSERT INTO `sys_logininfor` VALUES ('202', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-22 01:17:47');
INSERT INTO `sys_logininfor` VALUES ('203', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-22 01:32:28');
INSERT INTO `sys_logininfor` VALUES ('204', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-22 01:32:36');
INSERT INTO `sys_logininfor` VALUES ('205', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-22 01:32:40');
INSERT INTO `sys_logininfor` VALUES ('206', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-22 01:48:56');
INSERT INTO `sys_logininfor` VALUES ('207', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-22 01:49:05');
INSERT INTO `sys_logininfor` VALUES ('208', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-22 01:54:23');
INSERT INTO `sys_logininfor` VALUES ('209', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-22 02:10:29');
INSERT INTO `sys_logininfor` VALUES ('210', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-22 02:10:34');
INSERT INTO `sys_logininfor` VALUES ('211', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-22 02:11:12');
INSERT INTO `sys_logininfor` VALUES ('212', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-22 02:11:22');
INSERT INTO `sys_logininfor` VALUES ('213', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-22 02:11:25');
INSERT INTO `sys_logininfor` VALUES ('214', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-22 02:20:20');
INSERT INTO `sys_logininfor` VALUES ('215', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 19:00:05');
INSERT INTO `sys_logininfor` VALUES ('216', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-23 19:05:40');
INSERT INTO `sys_logininfor` VALUES ('217', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 19:05:46');
INSERT INTO `sys_logininfor` VALUES ('218', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-23 19:49:50');
INSERT INTO `sys_logininfor` VALUES ('219', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 19:49:54');
INSERT INTO `sys_logininfor` VALUES ('220', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-23 20:01:33');
INSERT INTO `sys_logininfor` VALUES ('221', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 20:01:37');
INSERT INTO `sys_logininfor` VALUES ('222', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-23 20:01:47');
INSERT INTO `sys_logininfor` VALUES ('223', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 20:01:55');
INSERT INTO `sys_logininfor` VALUES ('224', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-23 20:02:24');
INSERT INTO `sys_logininfor` VALUES ('225', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-23 20:02:29');
INSERT INTO `sys_logininfor` VALUES ('226', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 20:02:33');
INSERT INTO `sys_logininfor` VALUES ('227', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-23 20:03:01');
INSERT INTO `sys_logininfor` VALUES ('228', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 20:03:11');
INSERT INTO `sys_logininfor` VALUES ('229', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-23 20:03:28');
INSERT INTO `sys_logininfor` VALUES ('230', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 20:03:31');
INSERT INTO `sys_logininfor` VALUES ('231', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-23 20:05:30');
INSERT INTO `sys_logininfor` VALUES ('232', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 20:05:39');
INSERT INTO `sys_logininfor` VALUES ('233', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-23 20:05:51');
INSERT INTO `sys_logininfor` VALUES ('234', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 20:06:04');
INSERT INTO `sys_logininfor` VALUES ('235', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-23 20:06:25');
INSERT INTO `sys_logininfor` VALUES ('236', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误1次', '2020-03-23 20:06:32');
INSERT INTO `sys_logininfor` VALUES ('237', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 20:06:39');
INSERT INTO `sys_logininfor` VALUES ('238', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-23 20:07:17');
INSERT INTO `sys_logininfor` VALUES ('239', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 20:07:22');
INSERT INTO `sys_logininfor` VALUES ('240', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-23 20:07:24');
INSERT INTO `sys_logininfor` VALUES ('241', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 20:07:28');
INSERT INTO `sys_logininfor` VALUES ('242', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-23 20:08:00');
INSERT INTO `sys_logininfor` VALUES ('243', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 20:08:20');
INSERT INTO `sys_logininfor` VALUES ('244', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-23 20:19:53');
INSERT INTO `sys_logininfor` VALUES ('245', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-23 20:20:02');
INSERT INTO `sys_logininfor` VALUES ('246', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 20:20:07');
INSERT INTO `sys_logininfor` VALUES ('247', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-23 20:21:36');
INSERT INTO `sys_logininfor` VALUES ('248', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 20:21:46');
INSERT INTO `sys_logininfor` VALUES ('249', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-23 20:22:18');
INSERT INTO `sys_logininfor` VALUES ('250', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 20:22:21');
INSERT INTO `sys_logininfor` VALUES ('251', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-23 20:23:28');
INSERT INTO `sys_logininfor` VALUES ('252', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 20:23:37');
INSERT INTO `sys_logininfor` VALUES ('253', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-23 20:23:45');
INSERT INTO `sys_logininfor` VALUES ('254', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 20:23:52');
INSERT INTO `sys_logininfor` VALUES ('255', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-23 21:17:17');
INSERT INTO `sys_logininfor` VALUES ('256', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-23 21:17:21');
INSERT INTO `sys_logininfor` VALUES ('257', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-23 21:19:08');
INSERT INTO `sys_logininfor` VALUES ('258', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-24 19:05:46');
INSERT INTO `sys_logininfor` VALUES ('259', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-24 19:09:06');
INSERT INTO `sys_logininfor` VALUES ('260', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-24 19:09:15');
INSERT INTO `sys_logininfor` VALUES ('261', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-24 19:47:04');
INSERT INTO `sys_logininfor` VALUES ('262', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-24 19:48:00');
INSERT INTO `sys_logininfor` VALUES ('263', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-24 19:48:20');
INSERT INTO `sys_logininfor` VALUES ('264', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-24 19:56:04');
INSERT INTO `sys_logininfor` VALUES ('265', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-24 20:03:12');
INSERT INTO `sys_logininfor` VALUES ('266', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-24 20:03:20');
INSERT INTO `sys_logininfor` VALUES ('267', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-24 20:03:35');
INSERT INTO `sys_logininfor` VALUES ('268', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-24 20:03:43');
INSERT INTO `sys_logininfor` VALUES ('269', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-24 20:07:28');
INSERT INTO `sys_logininfor` VALUES ('270', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-24 20:07:37');
INSERT INTO `sys_logininfor` VALUES ('271', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-24 20:25:29');
INSERT INTO `sys_logininfor` VALUES ('272', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-24 20:25:38');
INSERT INTO `sys_logininfor` VALUES ('273', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-24 20:25:41');
INSERT INTO `sys_logininfor` VALUES ('274', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-24 21:06:26');
INSERT INTO `sys_logininfor` VALUES ('275', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-24 21:06:35');
INSERT INTO `sys_logininfor` VALUES ('276', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-24 21:10:47');
INSERT INTO `sys_logininfor` VALUES ('277', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-24 21:10:59');
INSERT INTO `sys_logininfor` VALUES ('278', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-24 21:11:18');
INSERT INTO `sys_logininfor` VALUES ('279', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-24 21:11:27');
INSERT INTO `sys_logininfor` VALUES ('280', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-25 19:32:11');
INSERT INTO `sys_logininfor` VALUES ('281', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-25 19:35:52');
INSERT INTO `sys_logininfor` VALUES ('282', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-25 19:36:02');
INSERT INTO `sys_logininfor` VALUES ('283', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-25 19:37:19');
INSERT INTO `sys_logininfor` VALUES ('284', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-25 19:37:23');
INSERT INTO `sys_logininfor` VALUES ('285', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-25 20:23:23');
INSERT INTO `sys_logininfor` VALUES ('286', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-25 20:23:31');
INSERT INTO `sys_logininfor` VALUES ('287', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-25 20:23:38');
INSERT INTO `sys_logininfor` VALUES ('288', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-25 20:23:41');
INSERT INTO `sys_logininfor` VALUES ('289', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-25 20:23:55');
INSERT INTO `sys_logininfor` VALUES ('290', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-25 20:24:02');
INSERT INTO `sys_logininfor` VALUES ('291', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-25 20:24:39');
INSERT INTO `sys_logininfor` VALUES ('292', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-25 20:33:43');
INSERT INTO `sys_logininfor` VALUES ('293', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-25 20:34:06');
INSERT INTO `sys_logininfor` VALUES ('294', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-25 20:34:17');
INSERT INTO `sys_logininfor` VALUES ('295', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 09:43:07');
INSERT INTO `sys_logininfor` VALUES ('296', 'axianlu', '127.0.0.1', '内网IP', 'Safari 5', 'Windows 8', '0', '登录成功', '2020-03-26 09:44:20');
INSERT INTO `sys_logininfor` VALUES ('297', 'axianlu', '127.0.0.1', '内网IP', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2020-03-26 09:46:11');
INSERT INTO `sys_logininfor` VALUES ('298', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-26 09:53:06');
INSERT INTO `sys_logininfor` VALUES ('299', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 09:53:16');
INSERT INTO `sys_logininfor` VALUES ('300', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-26 09:56:41');
INSERT INTO `sys_logininfor` VALUES ('301', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 09:56:45');
INSERT INTO `sys_logininfor` VALUES ('302', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 10:42:16');
INSERT INTO `sys_logininfor` VALUES ('303', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 19:52:12');
INSERT INTO `sys_logininfor` VALUES ('304', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-26 19:56:16');
INSERT INTO `sys_logininfor` VALUES ('305', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 19:56:24');
INSERT INTO `sys_logininfor` VALUES ('306', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-26 19:56:50');
INSERT INTO `sys_logininfor` VALUES ('307', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 19:56:57');
INSERT INTO `sys_logininfor` VALUES ('308', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-26 19:57:27');
INSERT INTO `sys_logininfor` VALUES ('309', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 19:57:37');
INSERT INTO `sys_logininfor` VALUES ('310', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-26 20:22:39');
INSERT INTO `sys_logininfor` VALUES ('311', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 20:22:44');
INSERT INTO `sys_logininfor` VALUES ('312', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 20:28:30');
INSERT INTO `sys_logininfor` VALUES ('313', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-26 20:32:59');
INSERT INTO `sys_logininfor` VALUES ('314', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 20:33:06');
INSERT INTO `sys_logininfor` VALUES ('315', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-26 20:34:50');
INSERT INTO `sys_logininfor` VALUES ('316', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 20:34:54');
INSERT INTO `sys_logininfor` VALUES ('317', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-26 20:37:14');
INSERT INTO `sys_logininfor` VALUES ('318', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-26 20:37:23');
INSERT INTO `sys_logininfor` VALUES ('319', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 20:37:26');
INSERT INTO `sys_logininfor` VALUES ('320', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-26 20:37:35');
INSERT INTO `sys_logininfor` VALUES ('321', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 20:37:45');
INSERT INTO `sys_logininfor` VALUES ('322', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-26 20:51:25');
INSERT INTO `sys_logininfor` VALUES ('323', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 20:51:29');
INSERT INTO `sys_logininfor` VALUES ('324', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-26 20:57:24');
INSERT INTO `sys_logininfor` VALUES ('325', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 20:57:31');
INSERT INTO `sys_logininfor` VALUES ('326', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-26 20:59:11');
INSERT INTO `sys_logininfor` VALUES ('327', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 20:59:20');
INSERT INTO `sys_logininfor` VALUES ('328', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-26 21:04:20');
INSERT INTO `sys_logininfor` VALUES ('329', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 21:04:27');
INSERT INTO `sys_logininfor` VALUES ('330', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-26 21:04:37');
INSERT INTO `sys_logininfor` VALUES ('331', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 21:04:41');
INSERT INTO `sys_logininfor` VALUES ('332', 'admin', '127.0.0.1', '内网IP', 'Microsoft Edge', 'Windows 10', '0', '登录成功', '2020-03-26 21:14:35');
INSERT INTO `sys_logininfor` VALUES ('333', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-26 21:50:08');
INSERT INTO `sys_logininfor` VALUES ('334', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-26 21:50:16');
INSERT INTO `sys_logininfor` VALUES ('335', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 21:50:20');
INSERT INTO `sys_logininfor` VALUES ('336', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-26 21:50:28');
INSERT INTO `sys_logininfor` VALUES ('337', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-26 21:50:39');
INSERT INTO `sys_logininfor` VALUES ('338', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 21:50:40');
INSERT INTO `sys_logininfor` VALUES ('339', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-26 21:55:56');
INSERT INTO `sys_logininfor` VALUES ('340', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-26 21:56:00');
INSERT INTO `sys_logininfor` VALUES ('341', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-26 21:56:30');
INSERT INTO `sys_logininfor` VALUES ('342', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-27 20:26:08');
INSERT INTO `sys_logininfor` VALUES ('343', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-27 20:53:47');
INSERT INTO `sys_logininfor` VALUES ('344', 'axianlu', '127.0.0.1', '内网IP', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2020-03-27 21:04:54');
INSERT INTO `sys_logininfor` VALUES ('345', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-27 21:56:01');
INSERT INTO `sys_logininfor` VALUES ('346', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-27 21:56:08');
INSERT INTO `sys_logininfor` VALUES ('347', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-27 21:56:11');
INSERT INTO `sys_logininfor` VALUES ('348', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-27 21:56:28');
INSERT INTO `sys_logininfor` VALUES ('349', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-27 21:56:36');
INSERT INTO `sys_logininfor` VALUES ('350', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-27 22:17:23');
INSERT INTO `sys_logininfor` VALUES ('351', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-27 22:17:28');
INSERT INTO `sys_logininfor` VALUES ('352', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-27 22:19:08');
INSERT INTO `sys_logininfor` VALUES ('353', 'axianlu', '127.0.0.1', '内网IP', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2020-03-27 22:24:03');
INSERT INTO `sys_logininfor` VALUES ('354', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-27 22:29:42');
INSERT INTO `sys_logininfor` VALUES ('355', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-27 22:29:51');
INSERT INTO `sys_logininfor` VALUES ('356', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-27 22:34:54');
INSERT INTO `sys_logininfor` VALUES ('357', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-27 22:34:58');
INSERT INTO `sys_logininfor` VALUES ('358', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-27 22:37:44');
INSERT INTO `sys_logininfor` VALUES ('359', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-27 22:42:08');
INSERT INTO `sys_logininfor` VALUES ('360', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-27 22:42:16');
INSERT INTO `sys_logininfor` VALUES ('361', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-27 22:42:56');
INSERT INTO `sys_logininfor` VALUES ('362', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-27 22:43:10');
INSERT INTO `sys_logininfor` VALUES ('363', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-27 22:46:33');
INSERT INTO `sys_logininfor` VALUES ('364', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-27 22:46:44');
INSERT INTO `sys_logininfor` VALUES ('365', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-27 22:48:09');
INSERT INTO `sys_logininfor` VALUES ('366', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-27 22:48:21');
INSERT INTO `sys_logininfor` VALUES ('367', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-27 22:49:04');
INSERT INTO `sys_logininfor` VALUES ('368', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-27 22:49:14');
INSERT INTO `sys_logininfor` VALUES ('369', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-27 22:54:37');
INSERT INTO `sys_logininfor` VALUES ('370', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-27 22:54:46');
INSERT INTO `sys_logininfor` VALUES ('371', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-27 22:57:42');
INSERT INTO `sys_logininfor` VALUES ('372', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-27 22:57:45');
INSERT INTO `sys_logininfor` VALUES ('373', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-27 22:59:06');
INSERT INTO `sys_logininfor` VALUES ('374', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-27 22:59:15');
INSERT INTO `sys_logininfor` VALUES ('375', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-27 23:00:12');
INSERT INTO `sys_logininfor` VALUES ('376', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-27 23:00:18');
INSERT INTO `sys_logininfor` VALUES ('377', 'admin', '192.168.0.100', '内网IP', 'Chrome Mobile', 'Android Mobile', '0', '登录成功', '2020-03-28 08:38:16');
INSERT INTO `sys_logininfor` VALUES ('378', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-28 08:58:29');
INSERT INTO `sys_logininfor` VALUES ('379', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-28 09:33:09');
INSERT INTO `sys_logininfor` VALUES ('380', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-28 09:33:13');
INSERT INTO `sys_logininfor` VALUES ('381', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-28 09:33:15');
INSERT INTO `sys_logininfor` VALUES ('382', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-28 09:33:24');
INSERT INTO `sys_logininfor` VALUES ('383', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-28 09:35:04');
INSERT INTO `sys_logininfor` VALUES ('384', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-28 09:35:12');
INSERT INTO `sys_logininfor` VALUES ('385', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-28 09:38:26');
INSERT INTO `sys_logininfor` VALUES ('386', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-28 09:38:37');
INSERT INTO `sys_logininfor` VALUES ('387', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-28 09:55:45');
INSERT INTO `sys_logininfor` VALUES ('388', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-28 09:55:50');
INSERT INTO `sys_logininfor` VALUES ('389', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-28 10:23:48');
INSERT INTO `sys_logininfor` VALUES ('390', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-28 10:34:39');
INSERT INTO `sys_logininfor` VALUES ('391', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-28 10:34:50');
INSERT INTO `sys_logininfor` VALUES ('392', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-28 10:36:30');
INSERT INTO `sys_logininfor` VALUES ('393', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-28 10:36:34');
INSERT INTO `sys_logininfor` VALUES ('394', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-28 10:36:37');
INSERT INTO `sys_logininfor` VALUES ('395', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-28 10:40:56');
INSERT INTO `sys_logininfor` VALUES ('396', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-28 10:41:09');
INSERT INTO `sys_logininfor` VALUES ('397', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-28 10:43:44');
INSERT INTO `sys_logininfor` VALUES ('398', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-28 10:43:58');
INSERT INTO `sys_logininfor` VALUES ('399', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-28 10:57:08');
INSERT INTO `sys_logininfor` VALUES ('400', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-28 10:57:15');
INSERT INTO `sys_logininfor` VALUES ('401', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-28 10:57:48');
INSERT INTO `sys_logininfor` VALUES ('402', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-28 10:57:56');
INSERT INTO `sys_logininfor` VALUES ('403', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-28 10:58:04');
INSERT INTO `sys_logininfor` VALUES ('404', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-28 10:58:14');
INSERT INTO `sys_logininfor` VALUES ('405', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-28 11:10:43');
INSERT INTO `sys_logininfor` VALUES ('406', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-28 11:10:46');
INSERT INTO `sys_logininfor` VALUES ('407', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-28 11:15:07');
INSERT INTO `sys_logininfor` VALUES ('408', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-28 11:15:19');
INSERT INTO `sys_logininfor` VALUES ('409', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-28 12:22:46');
INSERT INTO `sys_logininfor` VALUES ('410', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-28 14:53:52');
INSERT INTO `sys_logininfor` VALUES ('411', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-28 14:55:30');
INSERT INTO `sys_logininfor` VALUES ('412', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-28 14:55:38');
INSERT INTO `sys_logininfor` VALUES ('413', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-29 19:42:23');
INSERT INTO `sys_logininfor` VALUES ('414', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-29 23:52:41');
INSERT INTO `sys_logininfor` VALUES ('415', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-29 23:56:01');
INSERT INTO `sys_logininfor` VALUES ('416', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 17:18:06');
INSERT INTO `sys_logininfor` VALUES ('417', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-30 17:20:39');
INSERT INTO `sys_logininfor` VALUES ('418', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 17:20:48');
INSERT INTO `sys_logininfor` VALUES ('419', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 19:03:20');
INSERT INTO `sys_logininfor` VALUES ('420', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-30 19:10:50');
INSERT INTO `sys_logininfor` VALUES ('421', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 19:10:53');
INSERT INTO `sys_logininfor` VALUES ('422', 'admin', '127.0.0.1', '内网IP', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2020-03-30 19:11:48');
INSERT INTO `sys_logininfor` VALUES ('423', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 19:19:20');
INSERT INTO `sys_logininfor` VALUES ('424', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-30 19:20:01');
INSERT INTO `sys_logininfor` VALUES ('425', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-30 19:20:08');
INSERT INTO `sys_logininfor` VALUES ('426', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-30 19:20:13');
INSERT INTO `sys_logininfor` VALUES ('427', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 19:20:18');
INSERT INTO `sys_logininfor` VALUES ('428', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-30 19:50:43');
INSERT INTO `sys_logininfor` VALUES ('429', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 19:50:47');
INSERT INTO `sys_logininfor` VALUES ('430', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-30 19:55:38');
INSERT INTO `sys_logininfor` VALUES ('431', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 19:55:45');
INSERT INTO `sys_logininfor` VALUES ('432', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-30 20:01:18');
INSERT INTO `sys_logininfor` VALUES ('433', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 20:01:23');
INSERT INTO `sys_logininfor` VALUES ('434', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-30 20:02:35');
INSERT INTO `sys_logininfor` VALUES ('435', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 20:02:42');
INSERT INTO `sys_logininfor` VALUES ('436', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-30 20:03:18');
INSERT INTO `sys_logininfor` VALUES ('437', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 20:03:24');
INSERT INTO `sys_logininfor` VALUES ('438', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-30 20:03:37');
INSERT INTO `sys_logininfor` VALUES ('439', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 20:03:43');
INSERT INTO `sys_logininfor` VALUES ('440', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-30 20:03:59');
INSERT INTO `sys_logininfor` VALUES ('441', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 20:04:07');
INSERT INTO `sys_logininfor` VALUES ('442', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-30 20:05:14');
INSERT INTO `sys_logininfor` VALUES ('443', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 20:05:22');
INSERT INTO `sys_logininfor` VALUES ('444', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-30 20:05:29');
INSERT INTO `sys_logininfor` VALUES ('445', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 20:05:36');
INSERT INTO `sys_logininfor` VALUES ('446', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-30 20:07:37');
INSERT INTO `sys_logininfor` VALUES ('447', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 20:07:47');
INSERT INTO `sys_logininfor` VALUES ('448', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-30 20:08:04');
INSERT INTO `sys_logininfor` VALUES ('449', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-30 20:08:10');
INSERT INTO `sys_logininfor` VALUES ('450', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 20:08:12');
INSERT INTO `sys_logininfor` VALUES ('451', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-30 20:08:35');
INSERT INTO `sys_logininfor` VALUES ('452', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 20:08:41');
INSERT INTO `sys_logininfor` VALUES ('453', 'rensm', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-30 20:09:16');
INSERT INTO `sys_logininfor` VALUES ('454', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-03-30 20:09:23');
INSERT INTO `sys_logininfor` VALUES ('455', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 20:09:26');
INSERT INTO `sys_logininfor` VALUES ('456', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-30 20:34:23');
INSERT INTO `sys_logininfor` VALUES ('457', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 20:34:26');
INSERT INTO `sys_logininfor` VALUES ('458', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 23:25:14');
INSERT INTO `sys_logininfor` VALUES ('459', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-30 23:25:55');
INSERT INTO `sys_logininfor` VALUES ('460', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 23:26:08');
INSERT INTO `sys_logininfor` VALUES ('461', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-30 23:26:55');
INSERT INTO `sys_logininfor` VALUES ('462', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-30 23:27:05');
INSERT INTO `sys_logininfor` VALUES ('463', 'axianlu', '127.0.0.1', '内网IP', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2020-03-30 23:32:17');
INSERT INTO `sys_logininfor` VALUES ('464', 'axianlu', '127.0.0.1', '内网IP', 'Internet Explorer 11', 'Windows 10', '0', '退出成功', '2020-03-30 23:32:28');
INSERT INTO `sys_logininfor` VALUES ('465', 'rensm', '127.0.0.1', '内网IP', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2020-03-30 23:32:39');
INSERT INTO `sys_logininfor` VALUES ('466', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-31 20:10:47');
INSERT INTO `sys_logininfor` VALUES ('467', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-31 20:17:39');
INSERT INTO `sys_logininfor` VALUES ('468', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-31 20:17:50');
INSERT INTO `sys_logininfor` VALUES ('469', 'axianlu', '127.0.0.1', '内网IP', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2020-03-31 20:18:50');
INSERT INTO `sys_logininfor` VALUES ('470', 'rensm', '127.0.0.1', '内网IP', 'Microsoft Edge', 'Windows 10', '0', '登录成功', '2020-03-31 20:20:27');
INSERT INTO `sys_logininfor` VALUES ('471', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-31 20:26:28');
INSERT INTO `sys_logininfor` VALUES ('472', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-31 20:26:35');
INSERT INTO `sys_logininfor` VALUES ('473', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-03-31 20:36:47');
INSERT INTO `sys_logininfor` VALUES ('474', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-31 20:36:54');
INSERT INTO `sys_logininfor` VALUES ('475', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-03-31 22:57:21');
INSERT INTO `sys_logininfor` VALUES ('476', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-01 20:13:57');
INSERT INTO `sys_logininfor` VALUES ('477', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-02 19:53:45');
INSERT INTO `sys_logininfor` VALUES ('478', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-02 20:42:03');
INSERT INTO `sys_logininfor` VALUES ('479', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-03 16:55:04');
INSERT INTO `sys_logininfor` VALUES ('480', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-03 17:04:34');
INSERT INTO `sys_logininfor` VALUES ('481', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-03 17:04:44');
INSERT INTO `sys_logininfor` VALUES ('482', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-04 17:24:14');
INSERT INTO `sys_logininfor` VALUES ('483', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-04 17:30:33');
INSERT INTO `sys_logininfor` VALUES ('484', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-04 17:30:41');
INSERT INTO `sys_logininfor` VALUES ('485', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-04 17:34:06');
INSERT INTO `sys_logininfor` VALUES ('486', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-04-04 17:34:12');
INSERT INTO `sys_logininfor` VALUES ('487', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-04 17:34:15');
INSERT INTO `sys_logininfor` VALUES ('488', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-04 17:38:37');
INSERT INTO `sys_logininfor` VALUES ('489', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-04 17:38:41');
INSERT INTO `sys_logininfor` VALUES ('490', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-04 17:38:56');
INSERT INTO `sys_logininfor` VALUES ('491', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-04 17:39:05');
INSERT INTO `sys_logininfor` VALUES ('492', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-04 19:21:09');
INSERT INTO `sys_logininfor` VALUES ('493', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-04 19:22:46');
INSERT INTO `sys_logininfor` VALUES ('494', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-04-04 19:22:53');
INSERT INTO `sys_logininfor` VALUES ('495', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-04 19:22:56');
INSERT INTO `sys_logininfor` VALUES ('496', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 10:40:02');
INSERT INTO `sys_logininfor` VALUES ('497', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 10:40:16');
INSERT INTO `sys_logininfor` VALUES ('498', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 10:40:23');
INSERT INTO `sys_logininfor` VALUES ('499', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 12:20:08');
INSERT INTO `sys_logininfor` VALUES ('500', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 12:20:17');
INSERT INTO `sys_logininfor` VALUES ('501', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 16:23:25');
INSERT INTO `sys_logininfor` VALUES ('502', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 16:29:22');
INSERT INTO `sys_logininfor` VALUES ('503', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 16:29:31');
INSERT INTO `sys_logininfor` VALUES ('504', 'axianlu', '127.0.0.1', '内网IP', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2020-04-05 16:30:28');
INSERT INTO `sys_logininfor` VALUES ('505', 'axianlu', '127.0.0.1', '内网IP', 'Internet Explorer 11', 'Windows 10', '0', '退出成功', '2020-04-05 16:32:05');
INSERT INTO `sys_logininfor` VALUES ('506', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 16:48:54');
INSERT INTO `sys_logininfor` VALUES ('507', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 16:49:02');
INSERT INTO `sys_logininfor` VALUES ('508', 'chengxy', '127.0.0.1', '内网IP', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2020-04-05 16:51:28');
INSERT INTO `sys_logininfor` VALUES ('509', 'axianlu', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 16:57:03');
INSERT INTO `sys_logininfor` VALUES ('510', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 16:57:12');
INSERT INTO `sys_logininfor` VALUES ('511', 'chengxy', '127.0.0.1', '内网IP', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2020-04-05 16:58:17');
INSERT INTO `sys_logininfor` VALUES ('512', 'admin', '127.0.0.1', '内网IP', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2020-04-05 17:29:56');
INSERT INTO `sys_logininfor` VALUES ('513', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 17:32:36');
INSERT INTO `sys_logininfor` VALUES ('514', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 17:32:39');
INSERT INTO `sys_logininfor` VALUES ('515', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 17:34:42');
INSERT INTO `sys_logininfor` VALUES ('516', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 17:34:50');
INSERT INTO `sys_logininfor` VALUES ('517', 'admin', '127.0.0.1', '内网IP', 'Internet Explorer 11', 'Windows 10', '0', '登录成功', '2020-04-05 17:52:08');
INSERT INTO `sys_logininfor` VALUES ('518', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 17:54:01');
INSERT INTO `sys_logininfor` VALUES ('519', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误1次', '2020-04-05 17:54:09');
INSERT INTO `sys_logininfor` VALUES ('520', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 17:54:17');
INSERT INTO `sys_logininfor` VALUES ('521', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 17:54:24');
INSERT INTO `sys_logininfor` VALUES ('522', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 17:54:27');
INSERT INTO `sys_logininfor` VALUES ('523', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 17:56:06');
INSERT INTO `sys_logininfor` VALUES ('524', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 17:56:11');
INSERT INTO `sys_logininfor` VALUES ('525', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 17:57:34');
INSERT INTO `sys_logininfor` VALUES ('526', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 17:57:42');
INSERT INTO `sys_logininfor` VALUES ('527', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 18:02:35');
INSERT INTO `sys_logininfor` VALUES ('528', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 18:02:41');
INSERT INTO `sys_logininfor` VALUES ('529', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 18:06:13');
INSERT INTO `sys_logininfor` VALUES ('530', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 18:06:26');
INSERT INTO `sys_logininfor` VALUES ('531', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 18:07:01');
INSERT INTO `sys_logininfor` VALUES ('532', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 18:07:09');
INSERT INTO `sys_logininfor` VALUES ('533', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 18:08:02');
INSERT INTO `sys_logininfor` VALUES ('534', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 18:08:10');
INSERT INTO `sys_logininfor` VALUES ('535', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 18:11:04');
INSERT INTO `sys_logininfor` VALUES ('536', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 18:19:36');
INSERT INTO `sys_logininfor` VALUES ('537', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 18:19:42');
INSERT INTO `sys_logininfor` VALUES ('538', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 18:20:08');
INSERT INTO `sys_logininfor` VALUES ('539', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 18:20:13');
INSERT INTO `sys_logininfor` VALUES ('540', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 18:20:26');
INSERT INTO `sys_logininfor` VALUES ('541', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 18:20:46');
INSERT INTO `sys_logininfor` VALUES ('542', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 18:23:02');
INSERT INTO `sys_logininfor` VALUES ('543', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 18:23:08');
INSERT INTO `sys_logininfor` VALUES ('544', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 18:25:46');
INSERT INTO `sys_logininfor` VALUES ('545', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 18:25:51');
INSERT INTO `sys_logininfor` VALUES ('546', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 18:27:01');
INSERT INTO `sys_logininfor` VALUES ('547', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-04-05 18:27:08');
INSERT INTO `sys_logininfor` VALUES ('548', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 18:27:12');
INSERT INTO `sys_logininfor` VALUES ('549', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 18:30:51');
INSERT INTO `sys_logininfor` VALUES ('550', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误1次', '2020-04-05 18:30:59');
INSERT INTO `sys_logininfor` VALUES ('551', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误2次', '2020-04-05 18:31:03');
INSERT INTO `sys_logininfor` VALUES ('552', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误3次', '2020-04-05 18:31:05');
INSERT INTO `sys_logininfor` VALUES ('553', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 18:31:12');
INSERT INTO `sys_logininfor` VALUES ('554', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 18:31:33');
INSERT INTO `sys_logininfor` VALUES ('555', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 18:31:43');
INSERT INTO `sys_logininfor` VALUES ('556', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 18:31:52');
INSERT INTO `sys_logininfor` VALUES ('557', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 18:32:09');
INSERT INTO `sys_logininfor` VALUES ('558', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 18:32:30');
INSERT INTO `sys_logininfor` VALUES ('559', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 18:32:37');
INSERT INTO `sys_logininfor` VALUES ('560', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:29:39');
INSERT INTO `sys_logininfor` VALUES ('561', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:30:03');
INSERT INTO `sys_logininfor` VALUES ('562', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-04-05 19:30:12');
INSERT INTO `sys_logininfor` VALUES ('563', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:30:14');
INSERT INTO `sys_logininfor` VALUES ('564', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:30:40');
INSERT INTO `sys_logininfor` VALUES ('565', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:30:48');
INSERT INTO `sys_logininfor` VALUES ('566', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:31:06');
INSERT INTO `sys_logininfor` VALUES ('567', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:31:16');
INSERT INTO `sys_logininfor` VALUES ('568', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:32:12');
INSERT INTO `sys_logininfor` VALUES ('569', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:32:19');
INSERT INTO `sys_logininfor` VALUES ('570', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:32:48');
INSERT INTO `sys_logininfor` VALUES ('571', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:32:56');
INSERT INTO `sys_logininfor` VALUES ('572', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:33:05');
INSERT INTO `sys_logininfor` VALUES ('573', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:33:10');
INSERT INTO `sys_logininfor` VALUES ('574', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:33:48');
INSERT INTO `sys_logininfor` VALUES ('575', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:33:53');
INSERT INTO `sys_logininfor` VALUES ('576', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:34:05');
INSERT INTO `sys_logininfor` VALUES ('577', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:34:12');
INSERT INTO `sys_logininfor` VALUES ('578', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:34:24');
INSERT INTO `sys_logininfor` VALUES ('579', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-04-05 19:34:31');
INSERT INTO `sys_logininfor` VALUES ('580', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:34:35');
INSERT INTO `sys_logininfor` VALUES ('581', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:35:34');
INSERT INTO `sys_logininfor` VALUES ('582', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:35:42');
INSERT INTO `sys_logininfor` VALUES ('583', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:38:01');
INSERT INTO `sys_logininfor` VALUES ('584', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:38:08');
INSERT INTO `sys_logininfor` VALUES ('585', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:38:33');
INSERT INTO `sys_logininfor` VALUES ('586', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:38:42');
INSERT INTO `sys_logininfor` VALUES ('587', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:38:49');
INSERT INTO `sys_logininfor` VALUES ('588', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-04-05 19:38:58');
INSERT INTO `sys_logininfor` VALUES ('589', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:39:01');
INSERT INTO `sys_logininfor` VALUES ('590', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:39:37');
INSERT INTO `sys_logininfor` VALUES ('591', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:39:44');
INSERT INTO `sys_logininfor` VALUES ('592', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:40:06');
INSERT INTO `sys_logininfor` VALUES ('593', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:40:16');
INSERT INTO `sys_logininfor` VALUES ('594', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:40:27');
INSERT INTO `sys_logininfor` VALUES ('595', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:40:35');
INSERT INTO `sys_logininfor` VALUES ('596', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:40:50');
INSERT INTO `sys_logininfor` VALUES ('597', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-04-05 19:41:00');
INSERT INTO `sys_logininfor` VALUES ('598', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:41:02');
INSERT INTO `sys_logininfor` VALUES ('599', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:41:38');
INSERT INTO `sys_logininfor` VALUES ('600', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:41:52');
INSERT INTO `sys_logininfor` VALUES ('601', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:42:28');
INSERT INTO `sys_logininfor` VALUES ('602', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:42:36');
INSERT INTO `sys_logininfor` VALUES ('603', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:43:36');
INSERT INTO `sys_logininfor` VALUES ('604', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:43:45');
INSERT INTO `sys_logininfor` VALUES ('605', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:43:55');
INSERT INTO `sys_logininfor` VALUES ('606', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:44:01');
INSERT INTO `sys_logininfor` VALUES ('607', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:44:13');
INSERT INTO `sys_logininfor` VALUES ('608', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:44:22');
INSERT INTO `sys_logininfor` VALUES ('609', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:44:35');
INSERT INTO `sys_logininfor` VALUES ('610', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:44:45');
INSERT INTO `sys_logininfor` VALUES ('611', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:45:11');
INSERT INTO `sys_logininfor` VALUES ('612', '99', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', '2020-04-05 19:45:24');
INSERT INTO `sys_logininfor` VALUES ('613', 'dongzs', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-04-05 19:45:36');
INSERT INTO `sys_logininfor` VALUES ('614', 'dongzs', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', '2020-04-05 19:45:39');
INSERT INTO `sys_logininfor` VALUES ('615', 'dongsz', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-04-05 19:45:48');
INSERT INTO `sys_logininfor` VALUES ('616', 'dongsz', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误1次', '2020-04-05 19:45:51');
INSERT INTO `sys_logininfor` VALUES ('617', 'dongsz', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误2次', '2020-04-05 19:46:10');
INSERT INTO `sys_logininfor` VALUES ('618', 'dongsz', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误3次', '2020-04-05 19:46:45');
INSERT INTO `sys_logininfor` VALUES ('619', 'dongsz', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误4次', '2020-04-05 19:46:52');
INSERT INTO `sys_logininfor` VALUES ('620', 'dongsz', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误5次', '2020-04-05 19:47:29');
INSERT INTO `sys_logininfor` VALUES ('621', 'dongsz', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误5次，帐户锁定10分钟', '2020-04-05 19:48:27');
INSERT INTO `sys_logininfor` VALUES ('622', 'dongsz', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误5次，帐户锁定10分钟', '2020-04-05 19:50:15');
INSERT INTO `sys_logininfor` VALUES ('623', 'dongsz', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误5次，帐户锁定10分钟', '2020-04-05 19:50:44');
INSERT INTO `sys_logininfor` VALUES ('624', 'dongsz', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误1次', '2020-04-05 19:51:50');
INSERT INTO `sys_logininfor` VALUES ('625', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:51:57');
INSERT INTO `sys_logininfor` VALUES ('626', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:52:27');
INSERT INTO `sys_logininfor` VALUES ('627', 'dongsz', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:52:35');
INSERT INTO `sys_logininfor` VALUES ('628', 'dongsz', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:53:08');
INSERT INTO `sys_logininfor` VALUES ('629', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:53:14');
INSERT INTO `sys_logininfor` VALUES ('630', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:55:18');
INSERT INTO `sys_logininfor` VALUES ('631', 'chairman', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '对不起，您的账号已被删除', '2020-04-05 19:55:32');
INSERT INTO `sys_logininfor` VALUES ('632', 'chairman', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误1次', '2020-04-05 19:55:46');
INSERT INTO `sys_logininfor` VALUES ('633', 'chairman', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误2次', '2020-04-05 19:55:51');
INSERT INTO `sys_logininfor` VALUES ('634', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:56:04');
INSERT INTO `sys_logininfor` VALUES ('635', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 19:56:18');
INSERT INTO `sys_logininfor` VALUES ('636', 'chairman', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 19:56:29');
INSERT INTO `sys_logininfor` VALUES ('637', 'chairman', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 20:00:22');
INSERT INTO `sys_logininfor` VALUES ('638', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 20:00:30');
INSERT INTO `sys_logininfor` VALUES ('639', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 20:02:40');
INSERT INTO `sys_logininfor` VALUES ('640', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 20:02:44');
INSERT INTO `sys_logininfor` VALUES ('641', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 20:07:52');
INSERT INTO `sys_logininfor` VALUES ('642', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 20:08:04');
INSERT INTO `sys_logininfor` VALUES ('643', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 20:08:22');
INSERT INTO `sys_logininfor` VALUES ('644', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 20:08:32');
INSERT INTO `sys_logininfor` VALUES ('645', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 20:08:54');
INSERT INTO `sys_logininfor` VALUES ('646', 'chairman', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 20:09:09');
INSERT INTO `sys_logininfor` VALUES ('647', 'chairman', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 20:10:47');
INSERT INTO `sys_logininfor` VALUES ('648', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 20:10:54');
INSERT INTO `sys_logininfor` VALUES ('649', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 20:24:08');
INSERT INTO `sys_logininfor` VALUES ('650', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-04-05 20:24:12');
INSERT INTO `sys_logininfor` VALUES ('651', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-04-05 20:24:18');
INSERT INTO `sys_logininfor` VALUES ('652', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 20:24:21');
INSERT INTO `sys_logininfor` VALUES ('653', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 20:27:02');
INSERT INTO `sys_logininfor` VALUES ('654', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 20:27:09');
INSERT INTO `sys_logininfor` VALUES ('655', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 20:27:42');
INSERT INTO `sys_logininfor` VALUES ('656', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 20:27:50');
INSERT INTO `sys_logininfor` VALUES ('657', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 20:47:41');
INSERT INTO `sys_logininfor` VALUES ('658', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 20:47:44');
INSERT INTO `sys_logininfor` VALUES ('659', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 21:07:28');
INSERT INTO `sys_logininfor` VALUES ('660', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-04-05 21:07:35');
INSERT INTO `sys_logininfor` VALUES ('661', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 21:07:38');
INSERT INTO `sys_logininfor` VALUES ('662', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 21:09:04');
INSERT INTO `sys_logininfor` VALUES ('663', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 21:09:09');
INSERT INTO `sys_logininfor` VALUES ('664', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 21:09:27');
INSERT INTO `sys_logininfor` VALUES ('665', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-04-05 21:09:43');
INSERT INTO `sys_logininfor` VALUES ('666', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 21:09:47');
INSERT INTO `sys_logininfor` VALUES ('667', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 21:10:02');
INSERT INTO `sys_logininfor` VALUES ('668', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-04-05 21:10:08');
INSERT INTO `sys_logininfor` VALUES ('669', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 21:10:10');
INSERT INTO `sys_logininfor` VALUES ('670', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 21:10:51');
INSERT INTO `sys_logininfor` VALUES ('671', 'chairman', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 21:11:02');
INSERT INTO `sys_logininfor` VALUES ('672', 'chairman', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 21:12:52');
INSERT INTO `sys_logininfor` VALUES ('673', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 21:13:00');
INSERT INTO `sys_logininfor` VALUES ('674', 'chengxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 21:13:09');
INSERT INTO `sys_logininfor` VALUES ('675', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 21:13:17');
INSERT INTO `sys_logininfor` VALUES ('676', 'ry', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 21:13:28');
INSERT INTO `sys_logininfor` VALUES ('677', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 21:13:37');
INSERT INTO `sys_logininfor` VALUES ('678', '11', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 21:13:52');
INSERT INTO `sys_logininfor` VALUES ('679', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 21:14:01');
INSERT INTO `sys_logininfor` VALUES ('680', '22', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-05 21:14:11');
INSERT INTO `sys_logininfor` VALUES ('681', 'chairman', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误1次', '2020-04-05 21:14:19');
INSERT INTO `sys_logininfor` VALUES ('682', 'chairman', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', '2020-04-05 21:14:22');
INSERT INTO `sys_logininfor` VALUES ('683', 'chairman', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '密码输入错误2次', '2020-04-05 21:14:25');
INSERT INTO `sys_logininfor` VALUES ('684', 'chairman', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-05 21:14:31');
INSERT INTO `sys_logininfor` VALUES ('685', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-06 19:30:51');
INSERT INTO `sys_logininfor` VALUES ('686', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-06 20:21:47');
INSERT INTO `sys_logininfor` VALUES ('687', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-06 20:22:18');
INSERT INTO `sys_logininfor` VALUES ('688', 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-06 20:56:21');

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
) ENGINE=InnoDB AUTO_INCREMENT=2020 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

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
INSERT INTO `sys_menu` VALUES ('2000', '流程菜单', '0', '4', '#', 'menuItem', 'M', '0', null, 'fa fa-cogs', 'admin', '2020-02-25 21:00:54', '', null, '');
INSERT INTO `sys_menu` VALUES ('2001', '流程模型', '2000', '1', '/modeler/modelList', 'menuItem', 'C', '0', '', '#', 'admin', '2020-02-25 21:04:53', 'admin', '2020-02-25 21:40:37', '');
INSERT INTO `sys_menu` VALUES ('2002', '流程定义', '2000', '2', '/definition', 'menuItem', 'C', '0', null, '#', 'admin', '2020-02-26 22:00:43', '', null, '');
INSERT INTO `sys_menu` VALUES ('2003', '流程用户', '2000', '3', '/user', 'menuItem', 'C', '0', null, '#', 'admin', '2020-02-27 20:21:42', '', null, '');
INSERT INTO `sys_menu` VALUES ('2004', '流程用户组', '2000', '4', '/group', 'menuItem', 'C', '0', null, '#', 'admin', '2020-02-27 20:22:58', '', null, '');
INSERT INTO `sys_menu` VALUES ('2005', '请假流程', '0', '5', '#', 'menuItem', 'M', '0', '', 'fa fa-send', 'admin', '2020-02-28 20:34:58', 'admin', '2020-03-25 19:45:19', '');
INSERT INTO `sys_menu` VALUES ('2006', '请假列表', '2005', '1', '/leave', 'menuItem', 'C', '0', '', '#', 'admin', '2020-02-28 20:36:16', 'admin', '2020-03-27 21:47:22', '');
INSERT INTO `sys_menu` VALUES ('2007', '我的待办', '2005', '2', '/leave/leaveTodo', 'menuItem', 'C', '0', '', '#', 'admin', '2020-02-28 20:42:26', 'admin', '2020-02-28 21:50:48', '');
INSERT INTO `sys_menu` VALUES ('2008', '我的已办', '2005', '3', '/leave/leaveDone', 'menuItem', 'C', '0', '', '#', 'admin', '2020-02-28 20:43:18', 'admin', '2020-02-28 21:50:58', '');
INSERT INTO `sys_menu` VALUES ('2009', '待办事项', '0', '10', '#', 'menuItem', 'M', '0', '', 'fa fa-book', 'admin', '2020-03-25 20:11:55', 'admin', '2020-03-27 21:40:25', '');
INSERT INTO `sys_menu` VALUES ('2010', '我的待办', '2009', '1', '/todoitem', 'menuItem', 'C', '0', null, '#', 'admin', '2020-03-25 20:13:40', '', null, '');
INSERT INTO `sys_menu` VALUES ('2011', '我的已办', '2009', '2', '/todoitem/doneitemView', 'menuItem', 'C', '0', null, '#', 'admin', '2020-03-25 20:15:40', '', null, '');
INSERT INTO `sys_menu` VALUES ('2012', '请假会签', '0', '6', '#', 'menuItem', 'M', '0', null, 'fa fa-commenting', 'admin', '2020-03-27 21:41:00', '', null, '');
INSERT INTO `sys_menu` VALUES ('2013', '请假列表', '2012', '1', '/leaveCounterSign', 'menuItem', 'C', '0', '', '#', 'admin', '2020-03-27 21:41:30', 'admin', '2020-03-27 21:47:13', '');
INSERT INTO `sys_menu` VALUES ('2014', '我的待办', '2012', '2', '/leaveCounterSign/leaveTodo', 'menuItem', 'C', '0', null, '#', 'admin', '2020-03-27 21:42:38', '', null, '');
INSERT INTO `sys_menu` VALUES ('2015', '我的已办', '2012', '3', '/leaveCounterSign/leaveDone', 'menuItem', 'C', '0', null, '#', 'admin', '2020-03-27 21:43:01', '', null, '');
INSERT INTO `sys_menu` VALUES ('2016', '采购流程', '0', '7', '#', 'menuItem', 'M', '0', null, 'fa fa-cart-plus', 'admin', '2020-04-02 20:51:01', '', null, '');
INSERT INTO `sys_menu` VALUES ('2017', '采购列表', '2016', '1', '/purchase', 'menuItem', 'C', '0', null, '#', 'admin', '2020-04-02 20:51:45', '', null, '');
INSERT INTO `sys_menu` VALUES ('2018', '我的待办', '2016', '2', '/purchase/purchaseTodo', 'menuItem', 'C', '0', null, '#', 'admin', '2020-04-02 20:52:16', '', null, '');
INSERT INTO `sys_menu` VALUES ('2019', '我的已办', '2016', '3', '/purchase/purchaseDone', 'menuItem', 'C', '0', null, '#', 'admin', '2020-04-02 20:52:42', '', null, '');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='通知公告表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES ('1', '温馨提醒：2018-07-01 若依新版本发布啦', '2', '新版本内容', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');
INSERT INTO `sys_notice` VALUES ('2', '维护通知：2018-07-01 若依系统凌晨维护', '1', '维护内容', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');

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
) ENGINE=InnoDB AUTO_INCREMENT=346 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES ('100', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"流程菜单\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"fa fa-cogs\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-25 21:00:54');
INSERT INTO `sys_oper_log` VALUES ('101', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2000\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"流程模型\" ],\r\n  \"url\" : [ \"/modeler/model\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-25 21:04:53');
INSERT INTO `sys_oper_log` VALUES ('102', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2001\" ],\r\n  \"parentId\" : [ \"2000\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"流程模型\" ],\r\n  \"url\" : [ \"/modeler/modelList\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-25 21:40:37');
INSERT INTO `sys_oper_log` VALUES ('103', '流程模型', '3', 'com.ruoyi.activiti.modeler.ModelerController.remove()', 'POST', '1', 'admin', '研发部门', '/modeler/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"10006\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-26 19:53:56');
INSERT INTO `sys_oper_log` VALUES ('104', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2000\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"流程定义\" ],\r\n  \"url\" : [ \"/definition\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-26 22:00:43');
INSERT INTO `sys_oper_log` VALUES ('105', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"1\" ]\r\n}', '{\r\n  \"msg\" : \"操作失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-02-26 22:15:16');
INSERT INTO `sys_oper_log` VALUES ('106', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"1\" ]\r\n}', '{\r\n  \"msg\" : \"操作失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-02-26 22:15:49');
INSERT INTO `sys_oper_log` VALUES ('107', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"1\" ]\r\n}', '{\r\n  \"msg\" : \"操作失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-02-26 22:19:17');
INSERT INTO `sys_oper_log` VALUES ('108', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"1\" ]\r\n}', '{\r\n  \"msg\" : \"操作失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-02-26 22:19:46');
INSERT INTO `sys_oper_log` VALUES ('109', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"1\" ]\r\n}', '{\r\n  \"msg\" : \"操作失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-02-26 22:22:18');
INSERT INTO `sys_oper_log` VALUES ('110', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"1\" ]\r\n}', '{\r\n  \"msg\" : \"操作失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-02-26 22:23:12');
INSERT INTO `sys_oper_log` VALUES ('111', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"1\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-26 22:25:12');
INSERT INTO `sys_oper_log` VALUES ('112', '流程定义', '1', 'com.ruoyi.activiti.controller.ProcessDefinitionController.upload()', 'POST', '1', 'admin', '研发部门', '/definition/upload', '127.0.0.1', '内网IP', '{ }', '{\r\n  \"msg\" : \"filename : [leave.bpmn], extension : [bpmn], allowed extension : [[bmp, gif, jpg, jpeg, png, doc, docx, xls, xlsx, ppt, pptx, html, htm, txt, rar, zip, gz, bz2, pdf]]\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-02-26 22:27:37');
INSERT INTO `sys_oper_log` VALUES ('113', '流程定义', '1', 'com.ruoyi.activiti.controller.ProcessDefinitionController.upload()', 'POST', '1', 'admin', '研发部门', '/definition/upload', '127.0.0.1', '内网IP', '{ }', '{\r\n  \"msg\" : \"系统找不到指定的路径。\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-02-26 22:33:33');
INSERT INTO `sys_oper_log` VALUES ('114', '流程定义', '1', 'com.ruoyi.activiti.controller.ProcessDefinitionController.upload()', 'POST', '1', 'admin', '研发部门', '/definition/upload', '127.0.0.1', '内网IP', '{ }', '{\r\n  \"msg\" : \"系统找不到指定的路径。\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-02-26 22:37:34');
INSERT INTO `sys_oper_log` VALUES ('115', '流程定义', '1', 'com.ruoyi.activiti.controller.ProcessDefinitionController.upload()', 'POST', '1', 'admin', '研发部门', '/definition/upload', '127.0.0.1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-26 22:38:03');
INSERT INTO `sys_oper_log` VALUES ('116', '角色管理', '1', 'com.ruoyi.web.controller.system.SysRoleController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/role/add', '127.0.0.1', '内网IP', '{\r\n  \"roleName\" : [ \"部门领导\" ],\r\n  \"roleKey\" : [ \"deptLeader\" ],\r\n  \"roleSort\" : [ \"3\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"请假流程测试角色 -> 流程用户组\" ],\r\n  \"menuIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-27 19:37:37');
INSERT INTO `sys_oper_log` VALUES ('117', '角色管理', '1', 'com.ruoyi.web.controller.system.SysRoleController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/role/add', '127.0.0.1', '内网IP', '{\r\n  \"roleName\" : [ \"人事\" ],\r\n  \"roleKey\" : [ \"hr\" ],\r\n  \"roleSort\" : [ \"4\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"请假流程测试角色 -> 流程用户组\" ],\r\n  \"menuIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-27 19:38:41');
INSERT INTO `sys_oper_log` VALUES ('118', '用户管理', '1', 'com.ruoyi.web.controller.system.SysUserController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"105\" ],\r\n  \"userName\" : [ \"程序猿\" ],\r\n  \"deptName\" : [ \"测试部门\" ],\r\n  \"phonenumber\" : [ \"13000000000\" ],\r\n  \"email\" : [ \"110@gmail.com\" ],\r\n  \"loginName\" : [ \"chengxy\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"remark\" : [ \"注意该用户暂时没有分配角色（即没有分配到流程用户组）\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"\" ],\r\n  \"postIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-27 19:43:45');
INSERT INTO `sys_oper_log` VALUES ('119', '用户管理', '1', 'com.ruoyi.web.controller.system.SysUserController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"105\" ],\r\n  \"userName\" : [ \"一只闲鹿\" ],\r\n  \"deptName\" : [ \"测试部门\" ],\r\n  \"phonenumber\" : [ \"13000000001\" ],\r\n  \"email\" : [ \"114@gmail.com\" ],\r\n  \"loginName\" : [ \"axianlu\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"100\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"100\" ],\r\n  \"postIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-27 19:45:15');
INSERT INTO `sys_oper_log` VALUES ('120', '用户管理', '1', 'com.ruoyi.web.controller.system.SysUserController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"105\" ],\r\n  \"userName\" : [ \"人事喵\" ],\r\n  \"deptName\" : [ \"测试部门\" ],\r\n  \"phonenumber\" : [ \"13000000002\" ],\r\n  \"email\" : [ \"115@gmail.com\" ],\r\n  \"loginName\" : [ \"rensm\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"sex\" : [ \"1\" ],\r\n  \"role\" : [ \"101\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"101\" ],\r\n  \"postIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-27 19:46:20');
INSERT INTO `sys_oper_log` VALUES ('121', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2000\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"流程用户\" ],\r\n  \"url\" : [ \"/user\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-27 20:21:42');
INSERT INTO `sys_oper_log` VALUES ('122', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2000\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"流程用户组\" ],\r\n  \"url\" : [ \"/group\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-27 20:22:58');
INSERT INTO `sys_oper_log` VALUES ('123', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"业务菜单\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"fa fa-send\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-28 20:34:58');
INSERT INTO `sys_oper_log` VALUES ('124', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2005\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"请假流程\" ],\r\n  \"url\" : [ \"/leave\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-28 20:36:16');
INSERT INTO `sys_oper_log` VALUES ('125', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2005\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"我的待办\" ],\r\n  \"url\" : [ \"/leaveTodo\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-28 20:42:26');
INSERT INTO `sys_oper_log` VALUES ('126', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2005\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"我的已办\" ],\r\n  \"url\" : [ \"/leaveDone\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-28 20:43:18');
INSERT INTO `sys_oper_log` VALUES ('127', '字典类型', '1', 'com.ruoyi.web.controller.system.SysDictTypeController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/dict/add', '127.0.0.1', '内网IP', '{\r\n  \"dictName\" : [ \"请假类型\" ],\r\n  \"dictType\" : [ \"biz_leave_type\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-28 21:25:16');
INSERT INTO `sys_oper_log` VALUES ('128', '字典数据', '1', 'com.ruoyi.web.controller.system.SysDictDataController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/dict/data/add', '127.0.0.1', '内网IP', '{\r\n  \"dictLabel\" : [ \"事假\" ],\r\n  \"dictValue\" : [ \"1\" ],\r\n  \"dictType\" : [ \"biz_leave_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"1\" ],\r\n  \"listClass\" : [ \"success\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-28 21:27:30');
INSERT INTO `sys_oper_log` VALUES ('129', '字典数据', '1', 'com.ruoyi.web.controller.system.SysDictDataController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/dict/data/add', '127.0.0.1', '内网IP', '{\r\n  \"dictLabel\" : [ \"年假\" ],\r\n  \"dictValue\" : [ \"2\" ],\r\n  \"dictType\" : [ \"biz_leave_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"2\" ],\r\n  \"listClass\" : [ \"warning\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-28 21:27:58');
INSERT INTO `sys_oper_log` VALUES ('130', '角色管理', '1', 'com.ruoyi.web.controller.system.SysRoleController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/role/add', '127.0.0.1', '内网IP', '{\r\n  \"roleName\" : [ \"流程角色\" ],\r\n  \"roleKey\" : [ \"processRole\" ],\r\n  \"roleSort\" : [ \"5\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"menuIds\" : [ \"2005,2006,2007,2008\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-28 21:31:15');
INSERT INTO `sys_oper_log` VALUES ('131', '角色管理', '4', 'com.ruoyi.web.controller.system.SysRoleController.selectAuthUserAll()', 'POST', '1', 'admin', '研发部门', '/system/role/authUser/selectAll', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"102\" ],\r\n  \"userIds\" : [ \"102,101,100\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-28 21:32:37');
INSERT INTO `sys_oper_log` VALUES ('132', '请假业务', '1', 'com.ruoyi.activiti.controller.BizLeaveController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/leave/add', '127.0.0.1', '内网IP', '{\r\n  \"type\" : [ \"1\" ],\r\n  \"title\" : [ \"我想请假\" ],\r\n  \"reason\" : [ \"没想好理由\" ],\r\n  \"startTime\" : [ \"2020-02-28 22:00\" ],\r\n  \"endTime\" : [ \"2020-02-29 00:00\" ],\r\n  \"totalTime\" : [ \"7200\" ],\r\n  \"totalTimeText\" : [ \"0 天 2 时 0 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-28 21:41:59');
INSERT INTO `sys_oper_log` VALUES ('133', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.editSave()', 'POST', '1', 'chengxy', '测试部门', '/leave/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"20\" ],\r\n  \"applyUserName\" : [ \"\" ],\r\n  \"applyTime\" : [ \"\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"title\" : [ \"我想请假\" ],\r\n  \"reason\" : [ \"但我没想好理由\" ],\r\n  \"startTime\" : [ \"2020-02-28 22:00\" ],\r\n  \"endTime\" : [ \"2020-02-29 00:00\" ],\r\n  \"totalTime\" : [ \"7200\" ],\r\n  \"totalTimeText\" : [ \"0 天 2 时 0 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-28 21:46:15');
INSERT INTO `sys_oper_log` VALUES ('134', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"20\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-28 21:46:23');
INSERT INTO `sys_oper_log` VALUES ('135', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2007\" ],\r\n  \"parentId\" : [ \"2005\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"我的待办\" ],\r\n  \"url\" : [ \"/leave/leaveTodo\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-28 21:50:48');
INSERT INTO `sys_oper_log` VALUES ('136', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2008\" ],\r\n  \"parentId\" : [ \"2005\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"我的已办\" ],\r\n  \"url\" : [ \"/leave/leaveDone\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-28 21:50:58');
INSERT INTO `sys_oper_log` VALUES ('137', '请假业务', '1', 'com.ruoyi.activiti.controller.BizLeaveController.addSave()', 'POST', '1', 'admin', '研发部门', '/leave/add', '127.0.0.1', '内网IP', '{\r\n  \"type\" : [ \"1\" ],\r\n  \"title\" : [ \"我想请假\" ],\r\n  \"reason\" : [ \"想请就请\" ],\r\n  \"startTime\" : [ \"2020-02-29 20:30\" ],\r\n  \"endTime\" : [ \"2020-02-29 22:00\" ],\r\n  \"totalTime\" : [ \"5400\" ],\r\n  \"totalTimeText\" : [ \"0 天 1 时 30 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"提交申请失败：不允许管理员提交申请！\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-02-29 20:24:07');
INSERT INTO `sys_oper_log` VALUES ('138', '请假业务', '1', 'com.ruoyi.activiti.controller.BizLeaveController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/leave/add', '127.0.0.1', '内网IP', '{\r\n  \"type\" : [ \"1\" ],\r\n  \"title\" : [ \"我想请假\" ],\r\n  \"reason\" : [ \"想请就请\" ],\r\n  \"startTime\" : [ \"2020-02-29 20:30\" ],\r\n  \"endTime\" : [ \"2020-02-29 22:00\" ],\r\n  \"totalTime\" : [ \"5400\" ],\r\n  \"totalTimeText\" : [ \"0 天 1 时 30 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-29 20:24:49');
INSERT INTO `sys_oper_log` VALUES ('139', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"21\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-29 20:24:53');
INSERT INTO `sys_oper_log` VALUES ('140', '请假业务', '1', 'com.ruoyi.activiti.controller.BizLeaveController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/leave/add', '127.0.0.1', '内网IP', '{\r\n  \"type\" : [ \"1\" ],\r\n  \"title\" : [ \"我想请假\" ],\r\n  \"reason\" : [ \"又想请假了\" ],\r\n  \"startTime\" : [ \"2020-02-29 20:55\" ],\r\n  \"endTime\" : [ \"2020-02-29 22:00\" ],\r\n  \"totalTime\" : [ \"3900\" ],\r\n  \"totalTimeText\" : [ \"0 天 1 时 5 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-29 20:33:36');
INSERT INTO `sys_oper_log` VALUES ('141', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"22\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-02-29 20:33:39');
INSERT INTO `sys_oper_log` VALUES ('142', '请假业务', '1', 'com.ruoyi.activiti.controller.BizLeaveController.addSave()', 'POST', '1', 'admin', '研发部门', '/leave/add', '127.0.0.1', '内网IP', '{\r\n  \"type\" : [ \"1\" ],\r\n  \"title\" : [ \"1\" ],\r\n  \"reason\" : [ \"2\" ],\r\n  \"startTime\" : [ \"2020-03-19 14:30\" ],\r\n  \"endTime\" : [ \"2020-03-27 08:50\" ],\r\n  \"totalTime\" : [ \"670800\" ],\r\n  \"totalTimeText\" : [ \"7 天 18 时 20 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"提交申请失败：不允许管理员提交申请！\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-03-19 08:28:42');
INSERT INTO `sys_oper_log` VALUES ('143', '请假业务', '1', 'com.ruoyi.activiti.controller.BizLeaveController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/leave/add', '127.0.0.1', '内网IP', '{\r\n  \"type\" : [ \"1\" ],\r\n  \"title\" : [ \"1\" ],\r\n  \"reason\" : [ \"2\" ],\r\n  \"startTime\" : [ \"2020-03-19 10:50\" ],\r\n  \"endTime\" : [ \"2020-03-20 06:30\" ],\r\n  \"totalTime\" : [ \"70800\" ],\r\n  \"totalTimeText\" : [ \"0 天 19 时 40 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-19 08:29:20');
INSERT INTO `sys_oper_log` VALUES ('144', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"23\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-19 08:38:39');
INSERT INTO `sys_oper_log` VALUES ('145', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"24\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-19 08:38:41');
INSERT INTO `sys_oper_log` VALUES ('146', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"25\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-19 08:38:43');
INSERT INTO `sys_oper_log` VALUES ('147', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"26\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-19 08:38:45');
INSERT INTO `sys_oper_log` VALUES ('148', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"27\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-19 08:38:48');
INSERT INTO `sys_oper_log` VALUES ('149', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"28\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-19 08:38:50');
INSERT INTO `sys_oper_log` VALUES ('150', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"29\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-19 08:38:52');
INSERT INTO `sys_oper_log` VALUES ('151', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"30\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-19 08:38:56');
INSERT INTO `sys_oper_log` VALUES ('152', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"31\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-19 08:38:58');
INSERT INTO `sys_oper_log` VALUES ('153', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"32\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-19 08:39:00');
INSERT INTO `sys_oper_log` VALUES ('154', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"33\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-19 08:39:01');
INSERT INTO `sys_oper_log` VALUES ('155', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"67501\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 20:33:48');
INSERT INTO `sys_oper_log` VALUES ('156', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"55001\" ]\r\n}', '{\r\n  \"msg\" : \"操作失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-03-21 20:33:51');
INSERT INTO `sys_oper_log` VALUES ('157', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"47505\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 20:33:56');
INSERT INTO `sys_oper_log` VALUES ('158', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"55001\" ]\r\n}', '{\r\n  \"msg\" : \"操作失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-03-21 20:33:59');
INSERT INTO `sys_oper_log` VALUES ('159', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"55001\" ]\r\n}', '{\r\n  \"msg\" : \"操作失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-03-21 21:02:58');
INSERT INTO `sys_oper_log` VALUES ('160', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"55001\" ]\r\n}', '{\r\n  \"msg\" : \"操作失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-03-21 21:07:02');
INSERT INTO `sys_oper_log` VALUES ('161', '流程定义', '1', 'com.ruoyi.activiti.controller.ProcessDefinitionController.upload()', 'POST', '1', 'admin', '研发部门', '/definition/upload', '127.0.0.1', '内网IP', '{ }', '{\r\n  \"msg\" : \"filename : [leave.bpmn], extension : [bpmn], allowed extension : [[bmp, gif, jpg, jpeg, png, doc, docx, xls, xlsx, ppt, pptx, html, htm, txt, rar, zip, gz, bz2, pdf]]\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-03-21 21:23:31');
INSERT INTO `sys_oper_log` VALUES ('162', '流程定义', '1', 'com.ruoyi.activiti.controller.ProcessDefinitionController.upload()', 'POST', '1', 'admin', '研发部门', '/definition/upload', '127.0.0.1', '内网IP', '{ }', '{\r\n  \"msg\" : \"系统找不到指定的路径。\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-03-21 21:26:57');
INSERT INTO `sys_oper_log` VALUES ('163', '流程定义', '1', 'com.ruoyi.activiti.controller.ProcessDefinitionController.upload()', 'POST', '1', 'admin', '研发部门', '/definition/upload', '127.0.0.1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 21:28:19');
INSERT INTO `sys_oper_log` VALUES ('164', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"55001\" ]\r\n}', '{\r\n  \"msg\" : \"操作失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-03-21 21:33:05');
INSERT INTO `sys_oper_log` VALUES ('165', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"55001\" ]\r\n}', '{\r\n  \"msg\" : \"删除失败，存在运行中的流程实例\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-03-21 21:36:05');
INSERT INTO `sys_oper_log` VALUES ('166', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"70001\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 21:36:11');
INSERT INTO `sys_oper_log` VALUES ('167', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"75001\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 21:36:14');
INSERT INTO `sys_oper_log` VALUES ('168', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"55001\" ]\r\n}', '{\r\n  \"msg\" : \"删除失败，存在运行中的流程实例\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-03-21 21:36:17');
INSERT INTO `sys_oper_log` VALUES ('169', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"55001\" ]\r\n}', '{\r\n  \"msg\" : \"删除失败，存在运行中的流程实例\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-03-21 21:37:41');
INSERT INTO `sys_oper_log` VALUES ('170', '流程定义', '1', 'com.ruoyi.activiti.controller.ProcessDefinitionController.upload()', 'POST', '1', 'admin', '研发部门', '/definition/upload', '127.0.0.1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 21:39:43');
INSERT INTO `sys_oper_log` VALUES ('171', '流程定义', '1', 'com.ruoyi.activiti.controller.ProcessDefinitionController.upload()', 'POST', '1', 'admin', '研发部门', '/definition/upload', '127.0.0.1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 21:39:46');
INSERT INTO `sys_oper_log` VALUES ('172', '流程定义', '1', 'com.ruoyi.activiti.controller.ProcessDefinitionController.upload()', 'POST', '1', 'admin', '研发部门', '/definition/upload', '127.0.0.1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 21:42:07');
INSERT INTO `sys_oper_log` VALUES ('173', '流程定义', '1', 'com.ruoyi.activiti.controller.ProcessDefinitionController.upload()', 'POST', '1', 'admin', '研发部门', '/definition/upload', '127.0.0.1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 21:44:10');
INSERT INTO `sys_oper_log` VALUES ('174', '流程定义', '1', 'com.ruoyi.activiti.controller.ProcessDefinitionController.upload()', 'POST', '1', 'admin', '研发部门', '/definition/upload', '127.0.0.1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 21:45:20');
INSERT INTO `sys_oper_log` VALUES ('175', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"1\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 21:50:14');
INSERT INTO `sys_oper_log` VALUES ('176', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"5\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 21:50:17');
INSERT INTO `sys_oper_log` VALUES ('177', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"11\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 21:50:20');
INSERT INTO `sys_oper_log` VALUES ('178', '流程定义', '1', 'com.ruoyi.activiti.controller.ProcessDefinitionController.upload()', 'POST', '1', 'admin', '研发部门', '/definition/upload', '127.0.0.1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 21:51:08');
INSERT INTO `sys_oper_log` VALUES ('179', '角色管理', '1', 'com.ruoyi.web.controller.system.SysRoleController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/role/add', '127.0.0.1', '内网IP', '{\r\n  \"roleName\" : [ \"1\" ],\r\n  \"roleKey\" : [ \"1\" ],\r\n  \"roleSort\" : [ \"6\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"menuIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 22:10:35');
INSERT INTO `sys_oper_log` VALUES ('180', '角色管理', '1', 'com.ruoyi.web.controller.system.SysRoleController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/role/add', '127.0.0.1', '内网IP', '{\r\n  \"roleName\" : [ \"2\" ],\r\n  \"roleKey\" : [ \"2\" ],\r\n  \"roleSort\" : [ \"7\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"menuIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 22:10:47');
INSERT INTO `sys_oper_log` VALUES ('181', '角色管理', '1', 'com.ruoyi.web.controller.system.SysRoleController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/role/add', '127.0.0.1', '内网IP', '{\r\n  \"roleName\" : [ \"8\" ],\r\n  \"roleKey\" : [ \"8\" ],\r\n  \"roleSort\" : [ \"8\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"menuIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 22:11:08');
INSERT INTO `sys_oper_log` VALUES ('182', '角色管理', '1', 'com.ruoyi.web.controller.system.SysRoleController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/role/add', '127.0.0.1', '内网IP', '{\r\n  \"roleName\" : [ \"9\" ],\r\n  \"roleKey\" : [ \"9\" ],\r\n  \"roleSort\" : [ \"9\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"menuIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 22:11:14');
INSERT INTO `sys_oper_log` VALUES ('183', '角色管理', '1', 'com.ruoyi.web.controller.system.SysRoleController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/role/add', '127.0.0.1', '内网IP', '{\r\n  \"roleName\" : [ \"10\" ],\r\n  \"roleKey\" : [ \"10\" ],\r\n  \"roleSort\" : [ \"10\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"menuIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 22:11:21');
INSERT INTO `sys_oper_log` VALUES ('184', '角色管理', '1', 'com.ruoyi.web.controller.system.SysRoleController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/role/add', '127.0.0.1', '内网IP', '{\r\n  \"roleName\" : [ \"11\" ],\r\n  \"roleKey\" : [ \"11\" ],\r\n  \"roleSort\" : [ \"11\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"menuIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 22:11:29');
INSERT INTO `sys_oper_log` VALUES ('185', '请假业务', '1', 'com.ruoyi.activiti.controller.BizLeaveController.addSave()', 'POST', '1', 'admin', '研发部门', '/leave/add', '127.0.0.1', '内网IP', '{\r\n  \"type\" : [ \"2\" ],\r\n  \"title\" : [ \"我想请假\" ],\r\n  \"reason\" : [ \"没有理由\" ],\r\n  \"startTime\" : [ \"2020-03-21 23:25\" ],\r\n  \"endTime\" : [ \"2020-03-21 23:55\" ],\r\n  \"totalTime\" : [ \"1800\" ],\r\n  \"totalTimeText\" : [ \"0 天 0 时 30 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"提交申请失败：不允许管理员提交申请！\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-03-21 23:05:28');
INSERT INTO `sys_oper_log` VALUES ('186', '请假业务', '1', 'com.ruoyi.activiti.controller.BizLeaveController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/leave/add', '127.0.0.1', '内网IP', '{\r\n  \"type\" : [ \"1\" ],\r\n  \"title\" : [ \"我想请假\" ],\r\n  \"reason\" : [ \"12345\" ],\r\n  \"startTime\" : [ \"2020-03-21 23:25\" ],\r\n  \"endTime\" : [ \"2020-03-21 23:55\" ],\r\n  \"totalTime\" : [ \"1800\" ],\r\n  \"totalTimeText\" : [ \"0 天 0 时 30 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 23:06:07');
INSERT INTO `sys_oper_log` VALUES ('187', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"34\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-21 23:06:18');
INSERT INTO `sys_oper_log` VALUES ('188', '角色管理', '3', 'com.ruoyi.web.controller.system.SysRoleController.remove()', 'POST', '1', 'admin', '研发部门', '/system/role/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"103,104,105,106,107\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-23 19:02:03');
INSERT INTO `sys_oper_log` VALUES ('189', '角色管理', '3', 'com.ruoyi.web.controller.system.SysRoleController.remove()', 'POST', '1', 'admin', '研发部门', '/system/role/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"108\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-23 19:02:08');
INSERT INTO `sys_oper_log` VALUES ('190', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'axianlu', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"53\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-23 20:20:42');
INSERT INTO `sys_oper_log` VALUES ('191', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"35\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-23 20:22:02');
INSERT INTO `sys_oper_log` VALUES ('192', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"36\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-23 20:22:37');
INSERT INTO `sys_oper_log` VALUES ('193', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"37\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-23 20:22:40');
INSERT INTO `sys_oper_log` VALUES ('194', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"38\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-23 20:22:43');
INSERT INTO `sys_oper_log` VALUES ('195', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"39\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-23 20:22:53');
INSERT INTO `sys_oper_log` VALUES ('196', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"40\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-23 20:22:56');
INSERT INTO `sys_oper_log` VALUES ('197', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"41\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-23 20:22:58');
INSERT INTO `sys_oper_log` VALUES ('198', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"42\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-23 20:23:01');
INSERT INTO `sys_oper_log` VALUES ('199', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"43\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-23 20:23:03');
INSERT INTO `sys_oper_log` VALUES ('200', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"44\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-23 20:23:16');
INSERT INTO `sys_oper_log` VALUES ('201', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"45\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-23 20:23:23');
INSERT INTO `sys_oper_log` VALUES ('202', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"46\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-23 20:23:26');
INSERT INTO `sys_oper_log` VALUES ('203', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.cancelApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/cancelApply', '127.0.0.1', '内网IP', '{\r\n  \"instanceId\" : [ \"22560\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-24 20:06:20');
INSERT INTO `sys_oper_log` VALUES ('204', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.suspendApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/suspendApply', '127.0.0.1', '内网IP', '{\r\n  \"instanceId\" : [ \"22544\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-24 20:26:02');
INSERT INTO `sys_oper_log` VALUES ('205', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.suspendOrActiveApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/suspendOrActiveApply', '127.0.0.1', '内网IP', '{\r\n  \"instanceId\" : [ \"22544\" ],\r\n  \"suspendState\" : [ \"2\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-24 21:02:21');
INSERT INTO `sys_oper_log` VALUES ('206', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.suspendOrActiveApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/suspendOrActiveApply', '127.0.0.1', '内网IP', '{\r\n  \"instanceId\" : [ \"22544\" ],\r\n  \"suspendState\" : [ \"1\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-24 21:03:35');
INSERT INTO `sys_oper_log` VALUES ('207', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.cancelApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/cancelApply', '127.0.0.1', '内网IP', '{\r\n  \"instanceId\" : [ \"22544\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-24 21:03:55');
INSERT INTO `sys_oper_log` VALUES ('208', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2005\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"请假流程\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"fa fa-send\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-25 19:45:19');
INSERT INTO `sys_oper_log` VALUES ('209', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2006\" ],\r\n  \"parentId\" : [ \"2005\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"请假列表\" ],\r\n  \"url\" : [ \"/leave\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-25 19:45:35');
INSERT INTO `sys_oper_log` VALUES ('210', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"待办事项\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"6\" ],\r\n  \"icon\" : [ \"fa fa-book\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-25 20:11:55');
INSERT INTO `sys_oper_log` VALUES ('211', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2009\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"我的待办\" ],\r\n  \"url\" : [ \"/todoitem\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-25 20:13:40');
INSERT INTO `sys_oper_log` VALUES ('212', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2009\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"我的已办\" ],\r\n  \"url\" : [ \"/todoitem/doneitemView\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-25 20:15:40');
INSERT INTO `sys_oper_log` VALUES ('213', '角色管理', '2', 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"102\" ],\r\n  \"roleName\" : [ \"流程角色\" ],\r\n  \"roleKey\" : [ \"processRole\" ],\r\n  \"roleSort\" : [ \"5\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"menuIds\" : [ \"2005,2006,2007,2008,2009,2010,2011\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-25 20:23:53');
INSERT INTO `sys_oper_log` VALUES ('214', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"47\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-25 20:24:19');
INSERT INTO `sys_oper_log` VALUES ('215', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"48\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-25 20:34:03');
INSERT INTO `sys_oper_log` VALUES ('216', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"49\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-26 19:56:34');
INSERT INTO `sys_oper_log` VALUES ('217', '流程模型', '3', 'com.ruoyi.activiti.modeler.ModelerController.remove()', 'POST', '1', 'admin', '研发部门', '/modeler/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"40001\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 20:40:02');
INSERT INTO `sys_oper_log` VALUES ('218', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"50\" ]\r\n}', 'null', '1', 'Cannot start process instance. Process definition 请假流程-普通表单 (id = leave:1:18) is suspended', '2020-03-27 21:03:42');
INSERT INTO `sys_oper_log` VALUES ('219', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"50\" ]\r\n}', 'null', '1', 'Cannot start process instance. Process definition 请假流程-普通表单 (id = leave:1:18) is suspended', '2020-03-27 21:06:20');
INSERT INTO `sys_oper_log` VALUES ('220', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"50\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 21:06:37');
INSERT INTO `sys_oper_log` VALUES ('221', '字典数据', '2', 'com.ruoyi.web.controller.system.SysDictDataController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/dict/data/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictCode\" : [ \"101\" ],\r\n  \"dictLabel\" : [ \"会签\" ],\r\n  \"dictValue\" : [ \"2\" ],\r\n  \"dictType\" : [ \"biz_leave_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"2\" ],\r\n  \"listClass\" : [ \"warning\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 21:28:13');
INSERT INTO `sys_oper_log` VALUES ('222', '字典数据', '2', 'com.ruoyi.web.controller.system.SysDictDataController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/dict/data/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictCode\" : [ \"100\" ],\r\n  \"dictLabel\" : [ \"普通\" ],\r\n  \"dictValue\" : [ \"1\" ],\r\n  \"dictType\" : [ \"biz_leave_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"1\" ],\r\n  \"listClass\" : [ \"success\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 21:28:20');
INSERT INTO `sys_oper_log` VALUES ('223', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2009\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"待办事项\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"10\" ],\r\n  \"icon\" : [ \"fa fa-book\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 21:40:25');
INSERT INTO `sys_oper_log` VALUES ('224', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"请假会签\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"6\" ],\r\n  \"icon\" : [ \"fa fa-commenting\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 21:41:00');
INSERT INTO `sys_oper_log` VALUES ('225', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2012\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"请假列表\" ],\r\n  \"url\" : [ \"/leave\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 21:41:30');
INSERT INTO `sys_oper_log` VALUES ('226', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2006\" ],\r\n  \"parentId\" : [ \"2005\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"请假列表\" ],\r\n  \"url\" : [ \"/leave?type=1\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 21:41:46');
INSERT INTO `sys_oper_log` VALUES ('227', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2013\" ],\r\n  \"parentId\" : [ \"2012\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"请假列表\" ],\r\n  \"url\" : [ \"/leave?type=2\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 21:42:02');
INSERT INTO `sys_oper_log` VALUES ('228', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2013\" ],\r\n  \"parentId\" : [ \"2012\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"请假列表\" ],\r\n  \"url\" : [ \"/leaveCounterSign?type=2\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 21:42:18');
INSERT INTO `sys_oper_log` VALUES ('229', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2012\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"我的待办\" ],\r\n  \"url\" : [ \"/leaveCounterSign/leaveTodo\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 21:42:38');
INSERT INTO `sys_oper_log` VALUES ('230', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2012\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"我的已办\" ],\r\n  \"url\" : [ \"/leaveCounterSign/leaveDone\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 21:43:01');
INSERT INTO `sys_oper_log` VALUES ('231', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2013\" ],\r\n  \"parentId\" : [ \"2012\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"请假列表\" ],\r\n  \"url\" : [ \"/leaveCounterSign\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 21:47:13');
INSERT INTO `sys_oper_log` VALUES ('232', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2006\" ],\r\n  \"parentId\" : [ \"2005\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"请假列表\" ],\r\n  \"url\" : [ \"/leave\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 21:47:22');
INSERT INTO `sys_oper_log` VALUES ('233', '请假业务', '1', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.addSave()', 'POST', '1', 'admin', '研发部门', '/leaveCounterSign/add', '127.0.0.1', '内网IP', '{\r\n  \"title\" : [ \"会签版\" ],\r\n  \"reason\" : [ \"老子请假从来不用原因\" ],\r\n  \"startTime\" : [ \"2020-03-27 23:00\" ],\r\n  \"endTime\" : [ \"2020-03-28 00:00\" ],\r\n  \"totalTime\" : [ \"3600\" ],\r\n  \"totalTimeText\" : [ \"0 天 1 时 0 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"提交申请失败：不允许管理员提交申请！\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-03-27 21:55:56');
INSERT INTO `sys_oper_log` VALUES ('234', '角色管理', '2', 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"102\" ],\r\n  \"roleName\" : [ \"流程角色\" ],\r\n  \"roleKey\" : [ \"processRole\" ],\r\n  \"roleSort\" : [ \"5\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"menuIds\" : [ \"2005,2006,2007,2008,2012,2013,2014,2015,2009,2010,2011\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 21:56:26');
INSERT INTO `sys_oper_log` VALUES ('235', '请假业务', '1', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/leaveCounterSign/add', '127.0.0.1', '内网IP', '{\r\n  \"title\" : [ \"会签版\" ],\r\n  \"reason\" : [ \"喵喵喵？\" ],\r\n  \"startTime\" : [ \"2020-03-27 22:05\" ],\r\n  \"endTime\" : [ \"2020-03-28 23:30\" ],\r\n  \"totalTime\" : [ \"91500\" ],\r\n  \"totalTimeText\" : [ \"1 天 1 时 25 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:01:02');
INSERT INTO `sys_oper_log` VALUES ('236', '流程定义', '1', 'com.ruoyi.activiti.controller.ProcessDefinitionController.upload()', 'POST', '1', 'admin', '研发部门', '/definition/upload', '127.0.0.1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:17:43');
INSERT INTO `sys_oper_log` VALUES ('237', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leaveCounterSign/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"54\" ],\r\n  \"users[]\" : [ \"rensm\", \"axianlu\" ]\r\n}', '{\r\n  \"msg\" : \"提交申请失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-03-27 22:20:44');
INSERT INTO `sys_oper_log` VALUES ('238', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leaveCounterSign/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"54\" ],\r\n  \"users[]\" : [ \"rensm\", \"axianlu\" ]\r\n}', '{\r\n  \"msg\" : \"提交申请失败\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-03-27 22:22:33');
INSERT INTO `sys_oper_log` VALUES ('239', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leaveCounterSign/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"54\" ],\r\n  \"users[]\" : [ \"rensm\", \"axianlu\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:23:25');
INSERT INTO `sys_oper_log` VALUES ('240', '流程定义', '1', 'com.ruoyi.activiti.controller.ProcessDefinitionController.upload()', 'POST', '1', 'admin', '研发部门', '/definition/upload', '127.0.0.1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:35:55');
INSERT INTO `sys_oper_log` VALUES ('241', '流程定义', '1', 'com.ruoyi.activiti.controller.ProcessDefinitionController.upload()', 'POST', '1', 'admin', '研发部门', '/definition/upload', '127.0.0.1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:36:50');
INSERT INTO `sys_oper_log` VALUES ('242', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"47525\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:37:03');
INSERT INTO `sys_oper_log` VALUES ('243', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"45001\" ]\r\n}', '{\r\n  \"msg\" : \"删除失败，存在运行中的流程实例\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-03-27 22:37:07');
INSERT INTO `sys_oper_log` VALUES ('244', '字典数据', '2', 'com.ruoyi.web.controller.system.SysDictDataController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/dict/data/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictCode\" : [ \"101\" ],\r\n  \"dictLabel\" : [ \"会签\" ],\r\n  \"dictValue\" : [ \"leaveCounterSign\" ],\r\n  \"dictType\" : [ \"biz_leave_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"2\" ],\r\n  \"listClass\" : [ \"warning\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:38:10');
INSERT INTO `sys_oper_log` VALUES ('245', '字典数据', '2', 'com.ruoyi.web.controller.system.SysDictDataController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/dict/data/edit', '127.0.0.1', '内网IP', '{\r\n  \"dictCode\" : [ \"100\" ],\r\n  \"dictLabel\" : [ \"普通\" ],\r\n  \"dictValue\" : [ \"leave\" ],\r\n  \"dictType\" : [ \"biz_leave_type\" ],\r\n  \"cssClass\" : [ \"\" ],\r\n  \"dictSort\" : [ \"1\" ],\r\n  \"listClass\" : [ \"success\" ],\r\n  \"isDefault\" : [ \"Y\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:38:19');
INSERT INTO `sys_oper_log` VALUES ('246', '请假业务', '1', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/leaveCounterSign/add', '127.0.0.1', '内网IP', '{\r\n  \"title\" : [ \"会签版2\" ],\r\n  \"reason\" : [ \"www\" ],\r\n  \"startTime\" : [ \"2020-03-28 00:00\" ],\r\n  \"endTime\" : [ \"2020-04-04 01:05\" ],\r\n  \"totalTime\" : [ \"608700\" ],\r\n  \"totalTimeText\" : [ \"7 天 1 时 5 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:42:44');
INSERT INTO `sys_oper_log` VALUES ('247', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leaveCounterSign/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"55\" ],\r\n  \"users[]\" : [ \"rensm\", \"axianlu\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:42:53');
INSERT INTO `sys_oper_log` VALUES ('248', '请假业务', '1', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/leaveCounterSign/add', '127.0.0.1', '内网IP', '{\r\n  \"title\" : [ \"会签版3\" ],\r\n  \"reason\" : [ \"111\" ],\r\n  \"startTime\" : [ \"2020-03-28 02:10\" ],\r\n  \"endTime\" : [ \"2020-04-03 06:05\" ],\r\n  \"totalTime\" : [ \"532500\" ],\r\n  \"totalTimeText\" : [ \"6 天 3 时 55 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:48:54');
INSERT INTO `sys_oper_log` VALUES ('249', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leaveCounterSign/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"56\" ],\r\n  \"users[]\" : [ \"rensm\", \"axianlu\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:49:02');
INSERT INTO `sys_oper_log` VALUES ('250', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.editSave()', 'POST', '1', 'axianlu', '测试部门', '/leaveCounterSign/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"56\" ],\r\n  \"applyUserName\" : [ \"程序猿\" ],\r\n  \"applyTime\" : [ \"2020-03-27 22:49\" ],\r\n  \"title\" : [ \"会签版3\" ],\r\n  \"reason\" : [ \"111\" ],\r\n  \"startTime\" : [ \"2020-03-28 02:10\" ],\r\n  \"endTime\" : [ \"2020-04-03 06:05\" ],\r\n  \"totalTime\" : [ \"532500\" ],\r\n  \"totalTimeText\" : [ \"6 天 3 时 55 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:52:55');
INSERT INTO `sys_oper_log` VALUES ('251', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.editSave()', 'POST', '1', 'axianlu', '测试部门', '/leaveCounterSign/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"56\" ],\r\n  \"applyUserName\" : [ \"程序猿\" ],\r\n  \"applyTime\" : [ \"2020-03-27 22:49\" ],\r\n  \"title\" : [ \"会签版3\" ],\r\n  \"reason\" : [ \"111\" ],\r\n  \"startTime\" : [ \"2020-03-28 02:10\" ],\r\n  \"endTime\" : [ \"2020-04-03 06:05\" ],\r\n  \"totalTime\" : [ \"532500\" ],\r\n  \"totalTimeText\" : [ \"6 天 3 时 55 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:53:01');
INSERT INTO `sys_oper_log` VALUES ('252', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.editSave()', 'POST', '1', 'axianlu', '测试部门', '/leaveCounterSign/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"56\" ],\r\n  \"applyUserName\" : [ \"程序猿\" ],\r\n  \"applyTime\" : [ \"2020-03-27 22:49\" ],\r\n  \"title\" : [ \"会签版3\" ],\r\n  \"reason\" : [ \"111\" ],\r\n  \"startTime\" : [ \"2020-03-28 02:10\" ],\r\n  \"endTime\" : [ \"2020-04-03 06:05\" ],\r\n  \"totalTime\" : [ \"532500\" ],\r\n  \"totalTimeText\" : [ \"6 天 3 时 55 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:53:10');
INSERT INTO `sys_oper_log` VALUES ('253', '请假业务', '1', 'com.ruoyi.activiti.controller.BizLeaveController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/leave/add', '127.0.0.1', '内网IP', '{\r\n  \"title\" : [ \"我想请假\" ],\r\n  \"reason\" : [ \"喵喵喵？\" ],\r\n  \"startTime\" : [ \"2020-03-27 23:00\" ],\r\n  \"endTime\" : [ \"2020-03-28 10:30\" ],\r\n  \"totalTime\" : [ \"41400\" ],\r\n  \"totalTimeText\" : [ \"0 天 11 时 30 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:55:32');
INSERT INTO `sys_oper_log` VALUES ('254', '请假业务', '1', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/leaveCounterSign/add', '127.0.0.1', '内网IP', '{\r\n  \"title\" : [ \"会签版\" ],\r\n  \"reason\" : [ \"123\" ],\r\n  \"startTime\" : [ \"2020-03-27 23:55\" ],\r\n  \"endTime\" : [ \"2020-03-31 06:05\" ],\r\n  \"totalTime\" : [ \"281400\" ],\r\n  \"totalTimeText\" : [ \"3 天 6 时 10 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:55:57');
INSERT INTO `sys_oper_log` VALUES ('255', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"57\" ]\r\n}', 'null', '1', 'no processes deployed with key \'leave\'', '2020-03-27 22:57:16');
INSERT INTO `sys_oper_log` VALUES ('256', '流程定义', '1', 'com.ruoyi.activiti.controller.ProcessDefinitionController.upload()', 'POST', '1', 'admin', '研发部门', '/definition/upload', '127.0.0.1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:58:00');
INSERT INTO `sys_oper_log` VALUES ('257', '流程定义', '1', 'com.ruoyi.activiti.controller.ProcessDefinitionController.upload()', 'POST', '1', 'admin', '研发部门', '/definition/upload', '127.0.0.1', '内网IP', '{ }', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:58:11');
INSERT INTO `sys_oper_log` VALUES ('258', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"57\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:58:51');
INSERT INTO `sys_oper_log` VALUES ('259', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leaveCounterSign/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"58\" ],\r\n  \"users[]\" : [ \"rensm\", \"axianlu\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 22:59:03');
INSERT INTO `sys_oper_log` VALUES ('260', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.editSave()', 'POST', '1', 'rensm', '测试部门', '/leaveCounterSign/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"58\" ],\r\n  \"applyUserName\" : [ \"程序猿\" ],\r\n  \"applyTime\" : [ \"2020-03-27 22:59\" ],\r\n  \"title\" : [ \"会签版\" ],\r\n  \"reason\" : [ \"123\" ],\r\n  \"startTime\" : [ \"2020-03-27 23:55\" ],\r\n  \"endTime\" : [ \"2020-03-31 06:05\" ],\r\n  \"totalTime\" : [ \"281400\" ],\r\n  \"totalTimeText\" : [ \"3 天 6 时 10 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-27 23:02:08');
INSERT INTO `sys_oper_log` VALUES ('261', '请假业务', '1', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/leaveCounterSign/add', '127.0.0.1', '内网IP', '{\r\n  \"title\" : [ \"会签版2.0\" ],\r\n  \"reason\" : [ \"。。。\" ],\r\n  \"startTime\" : [ \"2020-03-28 10:50\" ],\r\n  \"endTime\" : [ \"2020-04-03 06:30\" ],\r\n  \"totalTime\" : [ \"502800\" ],\r\n  \"totalTimeText\" : [ \"5 天 19 时 40 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-28 10:35:23');
INSERT INTO `sys_oper_log` VALUES ('262', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leaveCounterSign/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"59\" ],\r\n  \"users[]\" : [ \"rensm\", \"axianlu\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-28 10:35:39');
INSERT INTO `sys_oper_log` VALUES ('263', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"5006\" ]\r\n}', '{\r\n  \"msg\" : \"删除失败，存在运行中的流程实例\",\r\n  \"code\" : 500\r\n}', '0', null, '2020-03-28 10:39:06');
INSERT INTO `sys_oper_log` VALUES ('264', '请假业务', '1', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/leaveCounterSign/add', '127.0.0.1', '内网IP', '{\r\n  \"title\" : [ \"会签版3.0\" ],\r\n  \"reason\" : [ \"嘤嘤嘤\" ],\r\n  \"startTime\" : [ \"2020-03-28 15:00\" ],\r\n  \"endTime\" : [ \"2020-03-30 05:00\" ],\r\n  \"totalTime\" : [ \"136800\" ],\r\n  \"totalTimeText\" : [ \"1 天 14 时 0 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-28 10:41:36');
INSERT INTO `sys_oper_log` VALUES ('265', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leaveCounterSign/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"60\" ],\r\n  \"users[]\" : [ \"rensm\", \"axianlu\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-28 10:41:50');
INSERT INTO `sys_oper_log` VALUES ('266', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.editSave()', 'POST', '1', 'axianlu', '测试部门', '/leaveCounterSign/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"58\" ],\r\n  \"applyUserName\" : [ \"程序猿\" ],\r\n  \"applyTime\" : [ \"2020-03-27 22:59\" ],\r\n  \"title\" : [ \"会签版\" ],\r\n  \"reason\" : [ \"123\" ],\r\n  \"startTime\" : [ \"2020-03-27 23:55\" ],\r\n  \"endTime\" : [ \"2020-03-31 06:05\" ],\r\n  \"totalTime\" : [ \"281400\" ],\r\n  \"totalTimeText\" : [ \"3 天 6 时 10 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-28 10:58:45');
INSERT INTO `sys_oper_log` VALUES ('267', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.editSave()', 'POST', '1', 'axianlu', '测试部门', '/leave/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"57\" ],\r\n  \"applyUserName\" : [ \"程序猿\" ],\r\n  \"applyTime\" : [ \"2020-03-27 22:58\" ],\r\n  \"title\" : [ \"我想请假\" ],\r\n  \"reason\" : [ \"喵喵喵？\" ],\r\n  \"startTime\" : [ \"2020-03-27 23:00\" ],\r\n  \"endTime\" : [ \"2020-03-28 10:30\" ],\r\n  \"totalTime\" : [ \"41400\" ],\r\n  \"totalTimeText\" : [ \"0 天 11 时 30 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-28 11:01:00');
INSERT INTO `sys_oper_log` VALUES ('268', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.editSave()', 'POST', '1', 'axianlu', '测试部门', '/leave/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"57\" ],\r\n  \"applyUserName\" : [ \"程序猿\" ],\r\n  \"applyTime\" : [ \"2020-03-27 22:58\" ],\r\n  \"title\" : [ \"我想请假\" ],\r\n  \"reason\" : [ \"喵喵喵？\" ],\r\n  \"startTime\" : [ \"2020-03-27 23:00\" ],\r\n  \"endTime\" : [ \"2020-03-28 10:30\" ],\r\n  \"totalTime\" : [ \"41400\" ],\r\n  \"totalTimeText\" : [ \"0 天 11 时 30 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-28 11:10:37');
INSERT INTO `sys_oper_log` VALUES ('269', '请假业务', '1', 'com.ruoyi.activiti.controller.BizLeaveController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/leave/add', '127.0.0.1', '内网IP', '{\r\n  \"title\" : [ \"请假嘤嘤嘤\" ],\r\n  \"reason\" : [ \"嘤嘤嘤\" ],\r\n  \"startTime\" : [ \"2020-03-31 20:25\" ],\r\n  \"endTime\" : [ \"2020-04-03 01:05\" ],\r\n  \"totalTime\" : [ \"189600\" ],\r\n  \"totalTimeText\" : [ \"2 天 4 时 40 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-31 20:18:20');
INSERT INTO `sys_oper_log` VALUES ('270', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"61\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-31 20:18:24');
INSERT INTO `sys_oper_log` VALUES ('271', '用户管理', '1', 'com.ruoyi.web.controller.system.SysUserController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"105\" ],\r\n  \"userName\" : [ \"1\" ],\r\n  \"deptName\" : [ \"测试部门\" ],\r\n  \"phonenumber\" : [ \"13111111111\" ],\r\n  \"email\" : [ \"110@qq.com\" ],\r\n  \"loginName\" : [ \"11\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"\" ],\r\n  \"postIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-31 22:58:21');
INSERT INTO `sys_oper_log` VALUES ('272', '用户管理', '1', 'com.ruoyi.web.controller.system.SysUserController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"105\" ],\r\n  \"userName\" : [ \"22\" ],\r\n  \"deptName\" : [ \"测试部门\" ],\r\n  \"phonenumber\" : [ \"13111111112\" ],\r\n  \"email\" : [ \"111@qq.com\" ],\r\n  \"loginName\" : [ \"22\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"\" ],\r\n  \"postIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-31 22:59:00');
INSERT INTO `sys_oper_log` VALUES ('273', '用户管理', '1', 'com.ruoyi.web.controller.system.SysUserController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"105\" ],\r\n  \"userName\" : [ \"33\" ],\r\n  \"deptName\" : [ \"测试部门\" ],\r\n  \"phonenumber\" : [ \"13111111113\" ],\r\n  \"email\" : [ \"113@qq.com\" ],\r\n  \"loginName\" : [ \"33\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"\" ],\r\n  \"postIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-31 22:59:31');
INSERT INTO `sys_oper_log` VALUES ('274', '用户管理', '1', 'com.ruoyi.web.controller.system.SysUserController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"105\" ],\r\n  \"userName\" : [ \"44\" ],\r\n  \"deptName\" : [ \"测试部门\" ],\r\n  \"phonenumber\" : [ \"13111111114\" ],\r\n  \"email\" : [ \"114@qq.com\" ],\r\n  \"loginName\" : [ \"44\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"\" ],\r\n  \"postIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-31 22:59:56');
INSERT INTO `sys_oper_log` VALUES ('275', '用户管理', '1', 'com.ruoyi.web.controller.system.SysUserController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"105\" ],\r\n  \"userName\" : [ \"55\" ],\r\n  \"deptName\" : [ \"测试部门\" ],\r\n  \"phonenumber\" : [ \"13111111115\" ],\r\n  \"email\" : [ \"115@qq.com\" ],\r\n  \"loginName\" : [ \"55\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"\" ],\r\n  \"postIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-31 23:00:20');
INSERT INTO `sys_oper_log` VALUES ('276', '用户管理', '1', 'com.ruoyi.web.controller.system.SysUserController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"105\" ],\r\n  \"userName\" : [ \"66\" ],\r\n  \"deptName\" : [ \"测试部门\" ],\r\n  \"phonenumber\" : [ \"13111111116\" ],\r\n  \"email\" : [ \"116@qq.com\" ],\r\n  \"loginName\" : [ \"66\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"\" ],\r\n  \"postIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-03-31 23:00:45');
INSERT INTO `sys_oper_log` VALUES ('277', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"17506\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-01 20:51:23');
INSERT INTO `sys_oper_log` VALUES ('278', '代码生成', '6', 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', '1', 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\r\n  \"tables\" : [ \"biz_purchase\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-02 19:56:11');
INSERT INTO `sys_oper_log` VALUES ('279', '代码生成', '8', 'com.ruoyi.generator.controller.GenController.genCode()', 'GET', '1', 'admin', '研发部门', '/tool/gen/genCode/biz_purchase', '127.0.0.1', '内网IP', '{ }', 'null', '0', null, '2020-04-02 20:42:12');
INSERT INTO `sys_oper_log` VALUES ('280', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"采购流程\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"7\" ],\r\n  \"icon\" : [ \"fa fa-cart-plus\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-02 20:51:01');
INSERT INTO `sys_oper_log` VALUES ('281', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2016\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"采购列表\" ],\r\n  \"url\" : [ \"/purchase\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-02 20:51:45');
INSERT INTO `sys_oper_log` VALUES ('282', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2016\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"我的待办\" ],\r\n  \"url\" : [ \"/purchase/purchaseTodo\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-02 20:52:16');
INSERT INTO `sys_oper_log` VALUES ('283', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2016\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"我的已办\" ],\r\n  \"url\" : [ \"/purchase/purchaseDone\" ],\r\n  \"target\" : [ \"menuItem\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-02 20:52:42');
INSERT INTO `sys_oper_log` VALUES ('284', '【请填写功能名称】', '1', 'com.ruoyi.activiti.controller.BizPurchaseController.addSave()', 'POST', '1', 'admin', '研发部门', '/purchase/add', '127.0.0.1', '内网IP', '{\r\n  \"title\" : [ \"路边的野花不要采\" ],\r\n  \"amount\" : [ \"19999\" ],\r\n  \"applyUser\" : [ \"\" ],\r\n  \"applyTime\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-02 21:04:56');
INSERT INTO `sys_oper_log` VALUES ('285', '【请填写功能名称】', '2', 'com.ruoyi.activiti.controller.BizPurchaseController.editSave()', 'POST', '1', 'admin', '研发部门', '/purchase/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"title\" : [ \"路边的野花不要采\" ],\r\n  \"amount\" : [ \"19999999.0\" ],\r\n  \"applyUser\" : [ \"\" ],\r\n  \"applyTime\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-02 21:12:32');
INSERT INTO `sys_oper_log` VALUES ('286', '请假业务', '2', 'com.ruoyi.activiti.controller.BizPurchaseController.submitApply()', 'POST', '1', 'admin', '研发部门', '/purchase/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"1\" ]\r\n}', 'null', '1', 'Unknown property used in expression: ${applyUserId}', '2020-04-02 21:13:57');
INSERT INTO `sys_oper_log` VALUES ('287', '请假业务', '2', 'com.ruoyi.activiti.controller.BizPurchaseController.submitApply()', 'POST', '1', 'admin', '研发部门', '/purchase/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"1\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-02 21:16:03');
INSERT INTO `sys_oper_log` VALUES ('288', '请假业务', '1', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/leaveCounterSign/add', '127.0.0.1', '内网IP', '{\r\n  \"title\" : [ \"会签版\" ],\r\n  \"reason\" : [ \"2222\" ],\r\n  \"startTime\" : [ \"2020-04-16 10:50\" ],\r\n  \"endTime\" : [ \"2020-04-24 10:30\" ],\r\n  \"totalTime\" : [ \"690000\" ],\r\n  \"totalTimeText\" : [ \"7 天 23 时 40 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-03 17:05:03');
INSERT INTO `sys_oper_log` VALUES ('289', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveCounterSignController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leaveCounterSign/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"62\" ],\r\n  \"users[]\" : [ \"rensm\", \"axianlu\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-04 17:33:42');
INSERT INTO `sys_oper_log` VALUES ('290', '角色管理', '2', 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"102\" ],\r\n  \"roleName\" : [ \"流程角色\" ],\r\n  \"roleKey\" : [ \"processRole\" ],\r\n  \"roleSort\" : [ \"5\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"menuIds\" : [ \"2005,2006,2007,2008,2012,2013,2014,2015,2016,2017,2018,2019,2009,2010,2011\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-04 17:38:54');
INSERT INTO `sys_oper_log` VALUES ('291', '流程定义', '3', 'com.ruoyi.activiti.controller.ProcessDefinitionController.remove()', 'POST', '1', 'admin', '研发部门', '/definition/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"27508\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-04 19:21:53');
INSERT INTO `sys_oper_log` VALUES ('292', '采购流程', '1', 'com.ruoyi.activiti.controller.BizPurchaseController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/add', '127.0.0.1', '内网IP', '{\r\n  \"title\" : [ \"路边的野花不要采\" ],\r\n  \"amount\" : [ \"999\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-04 19:27:49');
INSERT INTO `sys_oper_log` VALUES ('293', '采购流程', '1', 'com.ruoyi.activiti.controller.BizPurchaseController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/add', '127.0.0.1', '内网IP', '{\r\n  \"productIds\" : [ \"7,6,5,4,3,2,1\" ],\r\n  \"title\" : [ \"\" ],\r\n  \"amount\" : [ \"\" ],\r\n  \"btSelectAll\" : [ \"on\", \"on\" ],\r\n  \"btSelectItem\" : [ \"on\", \"on\", \"on\", \"on\", \"on\", \"on\", \"on\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-04 20:27:32');
INSERT INTO `sys_oper_log` VALUES ('294', '采购流程', '1', 'com.ruoyi.activiti.controller.BizPurchaseController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/add', '127.0.0.1', '内网IP', '{\r\n  \"productIds\" : [ \"7,6,5,4,3,2,1\" ],\r\n  \"title\" : [ \"\" ],\r\n  \"amount\" : [ \"\" ],\r\n  \"btSelectAll\" : [ \"on\", \"on\" ],\r\n  \"btSelectItem\" : [ \"on\", \"on\", \"on\", \"on\", \"on\", \"on\", \"on\" ]\r\n}', 'null', '1', 'nested exception is org.apache.ibatis.executor.ExecutorException: Error selecting key or setting result to parameter object. Cause: org.apache.ibatis.reflection.ReflectionException: Could not set property \'id\' of \'class com.ruoyi.activiti.domain.BizPurchase\' with value \'4\' Cause: java.lang.IllegalArgumentException: argument type mismatch', '2020-04-04 20:39:14');
INSERT INTO `sys_oper_log` VALUES ('295', '采购流程', '3', 'com.ruoyi.activiti.controller.BizPurchaseController.remove()', 'POST', '1', 'chengxy', '测试部门', '/purchase/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"3\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-04 20:40:15');
INSERT INTO `sys_oper_log` VALUES ('296', '采购流程', '3', 'com.ruoyi.activiti.controller.BizPurchaseController.remove()', 'POST', '1', 'chengxy', '测试部门', '/purchase/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"4\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-04 20:40:18');
INSERT INTO `sys_oper_log` VALUES ('297', '采购流程', '1', 'com.ruoyi.activiti.controller.BizPurchaseController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/add', '127.0.0.1', '内网IP', '{\r\n  \"productIds\" : [ \"7,6,5,4,3,2,1\" ],\r\n  \"title\" : [ \"\" ],\r\n  \"amount\" : [ \"\" ],\r\n  \"btSelectAll\" : [ \"on\", \"on\" ],\r\n  \"btSelectItem\" : [ \"on\", \"on\", \"on\", \"on\", \"on\", \"on\", \"on\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-04 20:41:14');
INSERT INTO `sys_oper_log` VALUES ('298', '采购流程', '3', 'com.ruoyi.activiti.controller.BizPurchaseController.remove()', 'POST', '1', 'chengxy', '测试部门', '/purchase/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"2\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 10:40:28');
INSERT INTO `sys_oper_log` VALUES ('299', '采购流程', '3', 'com.ruoyi.activiti.controller.BizPurchaseController.remove()', 'POST', '1', 'chengxy', '测试部门', '/purchase/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"5\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 10:40:31');
INSERT INTO `sys_oper_log` VALUES ('300', '采购流程', '1', 'com.ruoyi.activiti.controller.BizPurchaseController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/add', '127.0.0.1', '内网IP', '{\r\n  \"productIds\" : [ \"2,1\" ],\r\n  \"title\" : [ \"路边的野花不要采1\" ],\r\n  \"amount\" : [ \"999\" ],\r\n  \"btSelectAll\" : [ \"on\", \"on\" ],\r\n  \"btSelectItem\" : [ \"on\", \"on\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 10:42:42');
INSERT INTO `sys_oper_log` VALUES ('301', '采购流程', '3', 'com.ruoyi.activiti.controller.BizPurchaseController.remove()', 'POST', '1', 'chengxy', '测试部门', '/purchase/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"6\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 10:44:26');
INSERT INTO `sys_oper_log` VALUES ('302', '采购流程', '1', 'com.ruoyi.activiti.controller.BizPurchaseController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/add', '127.0.0.1', '内网IP', '{\r\n  \"productIds\" : [ \"2,1\" ],\r\n  \"title\" : [ \"路边的野花不要采1\" ],\r\n  \"amount\" : [ \"999\" ],\r\n  \"btSelectAll\" : [ \"on\", \"on\" ],\r\n  \"btSelectItem\" : [ \"on\", \"on\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 10:44:52');
INSERT INTO `sys_oper_log` VALUES ('303', '采购流程', '3', 'com.ruoyi.activiti.controller.BizPurchaseController.remove()', 'POST', '1', 'chengxy', '测试部门', '/purchase/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"7\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 11:00:00');
INSERT INTO `sys_oper_log` VALUES ('304', '采购流程', '1', 'com.ruoyi.activiti.controller.BizPurchaseController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/add', '127.0.0.1', '内网IP', '{\r\n  \"productIds\" : [ \"2,1\" ],\r\n  \"title\" : [ \"路边的野花不要采\" ],\r\n  \"amount\" : [ \"999\" ],\r\n  \"btSelectAll\" : [ \"on\", \"on\" ],\r\n  \"btSelectItem\" : [ \"on\", \"on\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 11:00:35');
INSERT INTO `sys_oper_log` VALUES ('305', '采购流程', '3', 'com.ruoyi.activiti.controller.BizPurchaseController.remove()', 'POST', '1', 'chengxy', '测试部门', '/purchase/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"8\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 11:01:31');
INSERT INTO `sys_oper_log` VALUES ('306', '采购流程', '1', 'com.ruoyi.activiti.controller.BizPurchaseController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/add', '127.0.0.1', '内网IP', '{\r\n  \"productIds\" : [ \"2,1\" ],\r\n  \"title\" : [ \"路边的野花不要采\" ],\r\n  \"amount\" : [ \"999\" ],\r\n  \"btSelectAll\" : [ \"on\", \"on\" ],\r\n  \"btSelectItem\" : [ \"on\", \"on\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 11:01:55');
INSERT INTO `sys_oper_log` VALUES ('307', '采购流程', '2', 'com.ruoyi.activiti.controller.BizPurchaseController.editSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"9\" ],\r\n  \"title\" : [ \"路边的野花不要采\" ],\r\n  \"amount\" : [ \"999.0\" ],\r\n  \"btSelectItem\" : [ \"on\" ]\r\n}', 'null', '1', '', '2020-04-05 11:28:53');
INSERT INTO `sys_oper_log` VALUES ('308', '采购流程', '2', 'com.ruoyi.activiti.controller.BizPurchaseController.editSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"9\" ],\r\n  \"productIds\" : [ \"\" ],\r\n  \"title\" : [ \"路边的野花不要采\" ],\r\n  \"amount\" : [ \"999.0\" ],\r\n  \"btSelectAll\" : [ \"on\", \"on\" ],\r\n  \"btSelectItem\" : [ \"on\", \"on\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 11:31:43');
INSERT INTO `sys_oper_log` VALUES ('309', '采购流程', '2', 'com.ruoyi.activiti.controller.BizPurchaseController.editSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"9\" ],\r\n  \"productIds\" : [ \"\" ],\r\n  \"title\" : [ \"路边的野花不要采\" ],\r\n  \"amount\" : [ \"999.0\" ],\r\n  \"btSelectAll\" : [ \"on\", \"on\" ],\r\n  \"btSelectItem\" : [ \"on\", \"on\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 11:32:23');
INSERT INTO `sys_oper_log` VALUES ('310', '采购流程', '2', 'com.ruoyi.activiti.controller.BizPurchaseController.editSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"9\" ],\r\n  \"productIds\" : [ \"2,1\" ],\r\n  \"title\" : [ \"路边的野花不要采\" ],\r\n  \"amount\" : [ \"999.0\" ],\r\n  \"btSelectAll\" : [ \"on\", \"on\" ],\r\n  \"btSelectItem\" : [ \"on\", \"on\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 11:33:06');
INSERT INTO `sys_oper_log` VALUES ('311', '采购流程', '2', 'com.ruoyi.activiti.controller.BizPurchaseController.editSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"9\" ],\r\n  \"productIds\" : [ \"3\" ],\r\n  \"title\" : [ \"路边的野花不要采\" ],\r\n  \"amount\" : [ \"999.0\" ],\r\n  \"btSelectItem\" : [ \"on\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 11:33:20');
INSERT INTO `sys_oper_log` VALUES ('312', '采购流程', '1', 'com.ruoyi.activiti.controller.BizPurchaseController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/add', '127.0.0.1', '内网IP', '{\r\n  \"productIds\" : [ \"\" ],\r\n  \"title\" : [ \"\" ],\r\n  \"amount\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 11:35:45');
INSERT INTO `sys_oper_log` VALUES ('313', '采购流程', '3', 'com.ruoyi.activiti.controller.BizPurchaseController.remove()', 'POST', '1', 'chengxy', '测试部门', '/purchase/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"10\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 11:36:42');
INSERT INTO `sys_oper_log` VALUES ('314', '采购流程', '2', 'com.ruoyi.activiti.controller.BizPurchaseController.editSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"9\" ],\r\n  \"productIds\" : [ \"1,3\" ],\r\n  \"title\" : [ \"路边的野花不要采\" ],\r\n  \"amount\" : [ \"999.0\" ],\r\n  \"btSelectItem\" : [ \"on\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 11:49:29');
INSERT INTO `sys_oper_log` VALUES ('315', '采购流程', '2', 'com.ruoyi.activiti.controller.BizPurchaseController.editSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"9\" ],\r\n  \"productIds\" : [ \"\" ],\r\n  \"title\" : [ \"路边的野花不要采\" ],\r\n  \"amount\" : [ \"999.0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 11:49:48');
INSERT INTO `sys_oper_log` VALUES ('316', '采购流程', '2', 'com.ruoyi.activiti.controller.BizPurchaseController.editSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"9\" ],\r\n  \"productIds\" : [ \"7,6,5,4,3,2,1\" ],\r\n  \"title\" : [ \"路边的野花不要采\" ],\r\n  \"amount\" : [ \"999.0\" ],\r\n  \"btSelectAll\" : [ \"on\", \"on\" ],\r\n  \"btSelectItem\" : [ \"on\", \"on\", \"on\", \"on\", \"on\", \"on\", \"on\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 11:50:07');
INSERT INTO `sys_oper_log` VALUES ('317', '采购流程', '1', 'com.ruoyi.activiti.controller.BizPurchaseController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/add', '127.0.0.1', '内网IP', '{\r\n  \"productIds\" : [ \"7,6,5,4,3\" ],\r\n  \"title\" : [ \"路边的野花不要采1\" ],\r\n  \"amount\" : [ \"888\" ],\r\n  \"btSelectAll\" : [ \"on\", \"on\" ],\r\n  \"btSelectItem\" : [ \"on\", \"on\", \"on\", \"on\", \"on\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 11:50:51');
INSERT INTO `sys_oper_log` VALUES ('318', '采购流程', '2', 'com.ruoyi.activiti.controller.BizPurchaseController.editSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"11\" ],\r\n  \"productIds\" : [ \"1,3,4,5,7\" ],\r\n  \"title\" : [ \"路边的野花不要采1\" ],\r\n  \"amount\" : [ \"888.0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 11:51:12');
INSERT INTO `sys_oper_log` VALUES ('319', '请假业务', '2', 'com.ruoyi.activiti.controller.BizPurchaseController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/purchase/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"9\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 11:51:30');
INSERT INTO `sys_oper_log` VALUES ('320', '请假业务', '1', 'com.ruoyi.activiti.controller.BizLeaveController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/leave/add', '127.0.0.1', '内网IP', '{\r\n  \"title\" : [ \"老子要请假\" ],\r\n  \"reason\" : [ \"请假\" ],\r\n  \"startTime\" : [ \"2020-04-05 12:30\" ],\r\n  \"endTime\" : [ \"2020-04-07 00:00\" ],\r\n  \"totalTime\" : [ \"127800\" ],\r\n  \"totalTimeText\" : [ \"1 天 11 时 30 分 0 秒\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 12:19:54');
INSERT INTO `sys_oper_log` VALUES ('321', '请假业务', '2', 'com.ruoyi.activiti.controller.BizLeaveController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/leave/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"63\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 12:19:58');
INSERT INTO `sys_oper_log` VALUES ('322', '请假业务', '2', 'com.ruoyi.activiti.controller.BizPurchaseController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/purchase/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"11\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 17:44:06');
INSERT INTO `sys_oper_log` VALUES ('323', '角色管理', '1', 'com.ruoyi.web.controller.system.SysRoleController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/role/add', '127.0.0.1', '内网IP', '{\r\n  \"roleName\" : [ \"财务\" ],\r\n  \"roleKey\" : [ \"financial\" ],\r\n  \"roleSort\" : [ \"6\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"menuIds\" : [ \"2016,2017,2018,2019,2009,2010,2011\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 17:51:04');
INSERT INTO `sys_oper_log` VALUES ('324', '用户管理', '2', 'com.ruoyi.web.controller.system.SysUserController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"103\" ],\r\n  \"deptId\" : [ \"105\" ],\r\n  \"userName\" : [ \"caiwu\" ],\r\n  \"dept.deptName\" : [ \"测试部门\" ],\r\n  \"phonenumber\" : [ \"13111111111\" ],\r\n  \"email\" : [ \"110@qq.com\" ],\r\n  \"loginName\" : [ \"11\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"109\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"109\" ],\r\n  \"postIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 17:53:02');
INSERT INTO `sys_oper_log` VALUES ('325', '角色管理', '2', 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"109\" ],\r\n  \"roleName\" : [ \"财务\" ],\r\n  \"roleKey\" : [ \"financial\" ],\r\n  \"roleSort\" : [ \"6\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"menuIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 17:55:45');
INSERT INTO `sys_oper_log` VALUES ('326', '角色管理', '4', 'com.ruoyi.web.controller.system.SysRoleController.selectAuthUserAll()', 'POST', '1', 'admin', '研发部门', '/system/role/authUser/selectAll', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"102\" ],\r\n  \"userIds\" : [ \"103,2\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 17:56:03');
INSERT INTO `sys_oper_log` VALUES ('327', '角色管理', '1', 'com.ruoyi.web.controller.system.SysRoleController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/role/add', '127.0.0.1', '内网IP', '{\r\n  \"roleName\" : [ \"总经理\" ],\r\n  \"roleKey\" : [ \"generalManager\" ],\r\n  \"roleSort\" : [ \"7\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"menuIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 18:10:13');
INSERT INTO `sys_oper_log` VALUES ('328', '用户管理', '2', 'com.ruoyi.web.controller.system.SysUserController.editSave()', 'POST', '1', 'admin', '研发部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"104\" ],\r\n  \"deptId\" : [ \"105\" ],\r\n  \"userName\" : [ \"zongjingli\" ],\r\n  \"dept.deptName\" : [ \"测试部门\" ],\r\n  \"phonenumber\" : [ \"13111111112\" ],\r\n  \"email\" : [ \"111@qq.com\" ],\r\n  \"loginName\" : [ \"22\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"102\", \"110\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"102,110\" ],\r\n  \"postIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 18:12:35');
INSERT INTO `sys_oper_log` VALUES ('329', '用户管理', '1', 'com.ruoyi.web.controller.system.SysUserController.addSave()', 'POST', '1', 'admin', '研发部门', '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"105\" ],\r\n  \"userName\" : [ \"dongshizhang\" ],\r\n  \"deptName\" : [ \"测试部门\" ],\r\n  \"phonenumber\" : [ \"13111111119\" ],\r\n  \"email\" : [ \"119@qq.com\" ],\r\n  \"loginName\" : [ \"99\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"102\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"102\" ],\r\n  \"postIds\" : [ \"\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 18:14:12');
INSERT INTO `sys_oper_log` VALUES ('330', '用户管理', '3', 'com.ruoyi.web.controller.system.SysUserController.remove()', 'POST', '1', 'admin', '研发部门', '/system/user/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"109\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 18:15:35');
INSERT INTO `sys_oper_log` VALUES ('331', '采购流程', '1', 'com.ruoyi.activiti.controller.BizPurchaseController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/add', '127.0.0.1', '内网IP', '{\r\n  \"productIds\" : [ \"5,3,1\" ],\r\n  \"title\" : [ \"路边的野花不要踩\" ],\r\n  \"amount\" : [ \"998\" ],\r\n  \"btSelectAll\" : [ \"on\", \"on\" ],\r\n  \"btSelectItem\" : [ \"on\", \"on\", \"on\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 18:29:23');
INSERT INTO `sys_oper_log` VALUES ('332', '请假业务', '2', 'com.ruoyi.activiti.controller.BizPurchaseController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/purchase/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"12\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 18:29:30');
INSERT INTO `sys_oper_log` VALUES ('333', '采购流程', '1', 'com.ruoyi.activiti.controller.BizPurchaseController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/add', '127.0.0.1', '内网IP', '{\r\n  \"productIds\" : [ \"7,6,5,4,3,2,1\" ],\r\n  \"title\" : [ \"路边的野花不要踩\" ],\r\n  \"amount\" : [ \"999999\" ],\r\n  \"btSelectAll\" : [ \"on\", \"on\" ],\r\n  \"btSelectItem\" : [ \"on\", \"on\", \"on\", \"on\", \"on\", \"on\", \"on\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 19:36:53');
INSERT INTO `sys_oper_log` VALUES ('334', '采购流程', '2', 'com.ruoyi.activiti.controller.BizPurchaseController.editSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"13\" ],\r\n  \"productIds\" : [ \"1,2,3,4,5,6,7\" ],\r\n  \"title\" : [ \"路边的野花不要踩\" ],\r\n  \"amount\" : [ \"99999.0\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 19:37:05');
INSERT INTO `sys_oper_log` VALUES ('335', '请假业务', '2', 'com.ruoyi.activiti.controller.BizPurchaseController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/purchase/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"13\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 19:37:31');
INSERT INTO `sys_oper_log` VALUES ('336', '重置密码', '2', 'com.ruoyi.web.controller.system.SysUserController.resetPwd()', 'GET', '1', 'admin', '研发部门', '/system/user/resetPwd/109', '127.0.0.1', '内网IP', '{ }', '\"system/user/resetPwd\"', '0', null, '2020-04-05 19:52:18');
INSERT INTO `sys_oper_log` VALUES ('337', '重置密码', '2', 'com.ruoyi.web.controller.system.SysUserController.resetPwdSave()', 'POST', '1', 'admin', '研发部门', '/system/user/resetPwd', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"109\" ],\r\n  \"loginName\" : [ \"dongsz\" ],\r\n  \"password\" : [ \"123456\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 19:52:25');
INSERT INTO `sys_oper_log` VALUES ('338', '重置密码', '2', 'com.ruoyi.web.controller.system.SysUserController.resetPwd()', 'GET', '1', 'admin', '研发部门', '/system/user/resetPwd/109', '127.0.0.1', '内网IP', '{ }', '\"system/user/resetPwd\"', '0', null, '2020-04-05 19:54:08');
INSERT INTO `sys_oper_log` VALUES ('339', '用户管理', '3', 'com.ruoyi.web.controller.system.SysUserController.remove()', 'POST', '1', 'admin', '研发部门', '/system/user/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"109\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 19:54:18');
INSERT INTO `sys_oper_log` VALUES ('340', '重置密码', '2', 'com.ruoyi.web.controller.system.SysUserController.resetPwd()', 'GET', '1', 'admin', '研发部门', '/system/user/resetPwd/109', '127.0.0.1', '内网IP', '{ }', '\"system/user/resetPwd\"', '0', null, '2020-04-05 19:56:10');
INSERT INTO `sys_oper_log` VALUES ('341', '重置密码', '2', 'com.ruoyi.web.controller.system.SysUserController.resetPwdSave()', 'POST', '1', 'admin', '研发部门', '/system/user/resetPwd', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"109\" ],\r\n  \"loginName\" : [ \"chairman\" ],\r\n  \"password\" : [ \"123456\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 19:56:15');
INSERT INTO `sys_oper_log` VALUES ('342', '采购流程', '1', 'com.ruoyi.activiti.controller.BizPurchaseController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/add', '127.0.0.1', '内网IP', '{\r\n  \"productIds\" : [ \"1\" ],\r\n  \"title\" : [ \"测试采购流程\" ],\r\n  \"amount\" : [ \"50000\" ],\r\n  \"btSelectAll\" : [ \"on\", \"on\" ],\r\n  \"btSelectItem\" : [ \"on\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 20:22:15');
INSERT INTO `sys_oper_log` VALUES ('343', '请假业务', '2', 'com.ruoyi.activiti.controller.BizPurchaseController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/purchase/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"14\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 20:22:43');
INSERT INTO `sys_oper_log` VALUES ('344', '采购流程', '1', 'com.ruoyi.activiti.controller.BizPurchaseController.addSave()', 'POST', '1', 'chengxy', '测试部门', '/purchase/add', '127.0.0.1', '内网IP', '{\r\n  \"productIds\" : [ \"5\" ],\r\n  \"title\" : [ \"菜狗\" ],\r\n  \"amount\" : [ \"60000\" ],\r\n  \"btSelectAll\" : [ \"on\", \"on\" ],\r\n  \"btSelectItem\" : [ \"on\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 21:08:12');
INSERT INTO `sys_oper_log` VALUES ('345', '请假业务', '2', 'com.ruoyi.activiti.controller.BizPurchaseController.submitApply()', 'POST', '1', 'chengxy', '测试部门', '/purchase/submitApply', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"15\" ]\r\n}', '{\r\n  \"msg\" : \"操作成功\",\r\n  \"code\" : 0\r\n}', '0', null, '2020-04-05 21:08:21');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='岗位信息表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('1', 'ceo', '董事长', '1', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('2', 'se', '项目经理', '2', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('3', 'hr', '人力资源', '3', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('4', 'user', '普通员工', '4', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');

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
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'admin', '1', '1', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');
INSERT INTO `sys_role` VALUES ('2', '普通角色', 'common', '2', '2', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '普通角色');
INSERT INTO `sys_role` VALUES ('100', '部门领导', 'deptLeader', '3', '1', '0', '0', 'admin', '2020-02-27 19:37:37', '', null, '请假流程测试角色 -> 流程用户组');
INSERT INTO `sys_role` VALUES ('101', '人事', 'hr', '4', '1', '0', '0', 'admin', '2020-02-27 19:38:41', '', null, '请假流程测试角色 -> 流程用户组');
INSERT INTO `sys_role` VALUES ('102', '流程角色', 'processRole', '5', '1', '0', '0', 'admin', '2020-02-28 21:31:15', 'admin', '2020-04-04 17:38:54', '');
INSERT INTO `sys_role` VALUES ('109', '财务', 'financial', '6', '1', '0', '0', 'admin', '2020-04-05 17:51:04', 'admin', '2020-04-05 17:55:45', '');
INSERT INTO `sys_role` VALUES ('110', '总经理', 'generalManager', '7', '1', '0', '0', 'admin', '2020-04-05 18:10:13', '', null, null);

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
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '2');
INSERT INTO `sys_role_menu` VALUES ('2', '3');
INSERT INTO `sys_role_menu` VALUES ('2', '100');
INSERT INTO `sys_role_menu` VALUES ('2', '101');
INSERT INTO `sys_role_menu` VALUES ('2', '102');
INSERT INTO `sys_role_menu` VALUES ('2', '103');
INSERT INTO `sys_role_menu` VALUES ('2', '104');
INSERT INTO `sys_role_menu` VALUES ('2', '105');
INSERT INTO `sys_role_menu` VALUES ('2', '106');
INSERT INTO `sys_role_menu` VALUES ('2', '107');
INSERT INTO `sys_role_menu` VALUES ('2', '108');
INSERT INTO `sys_role_menu` VALUES ('2', '109');
INSERT INTO `sys_role_menu` VALUES ('2', '110');
INSERT INTO `sys_role_menu` VALUES ('2', '111');
INSERT INTO `sys_role_menu` VALUES ('2', '112');
INSERT INTO `sys_role_menu` VALUES ('2', '113');
INSERT INTO `sys_role_menu` VALUES ('2', '114');
INSERT INTO `sys_role_menu` VALUES ('2', '115');
INSERT INTO `sys_role_menu` VALUES ('2', '500');
INSERT INTO `sys_role_menu` VALUES ('2', '501');
INSERT INTO `sys_role_menu` VALUES ('2', '1000');
INSERT INTO `sys_role_menu` VALUES ('2', '1001');
INSERT INTO `sys_role_menu` VALUES ('2', '1002');
INSERT INTO `sys_role_menu` VALUES ('2', '1003');
INSERT INTO `sys_role_menu` VALUES ('2', '1004');
INSERT INTO `sys_role_menu` VALUES ('2', '1005');
INSERT INTO `sys_role_menu` VALUES ('2', '1006');
INSERT INTO `sys_role_menu` VALUES ('2', '1007');
INSERT INTO `sys_role_menu` VALUES ('2', '1008');
INSERT INTO `sys_role_menu` VALUES ('2', '1009');
INSERT INTO `sys_role_menu` VALUES ('2', '1010');
INSERT INTO `sys_role_menu` VALUES ('2', '1011');
INSERT INTO `sys_role_menu` VALUES ('2', '1012');
INSERT INTO `sys_role_menu` VALUES ('2', '1013');
INSERT INTO `sys_role_menu` VALUES ('2', '1014');
INSERT INTO `sys_role_menu` VALUES ('2', '1015');
INSERT INTO `sys_role_menu` VALUES ('2', '1016');
INSERT INTO `sys_role_menu` VALUES ('2', '1017');
INSERT INTO `sys_role_menu` VALUES ('2', '1018');
INSERT INTO `sys_role_menu` VALUES ('2', '1019');
INSERT INTO `sys_role_menu` VALUES ('2', '1020');
INSERT INTO `sys_role_menu` VALUES ('2', '1021');
INSERT INTO `sys_role_menu` VALUES ('2', '1022');
INSERT INTO `sys_role_menu` VALUES ('2', '1023');
INSERT INTO `sys_role_menu` VALUES ('2', '1024');
INSERT INTO `sys_role_menu` VALUES ('2', '1025');
INSERT INTO `sys_role_menu` VALUES ('2', '1026');
INSERT INTO `sys_role_menu` VALUES ('2', '1027');
INSERT INTO `sys_role_menu` VALUES ('2', '1028');
INSERT INTO `sys_role_menu` VALUES ('2', '1029');
INSERT INTO `sys_role_menu` VALUES ('2', '1030');
INSERT INTO `sys_role_menu` VALUES ('2', '1031');
INSERT INTO `sys_role_menu` VALUES ('2', '1032');
INSERT INTO `sys_role_menu` VALUES ('2', '1033');
INSERT INTO `sys_role_menu` VALUES ('2', '1034');
INSERT INTO `sys_role_menu` VALUES ('2', '1035');
INSERT INTO `sys_role_menu` VALUES ('2', '1036');
INSERT INTO `sys_role_menu` VALUES ('2', '1037');
INSERT INTO `sys_role_menu` VALUES ('2', '1038');
INSERT INTO `sys_role_menu` VALUES ('2', '1039');
INSERT INTO `sys_role_menu` VALUES ('2', '1040');
INSERT INTO `sys_role_menu` VALUES ('2', '1041');
INSERT INTO `sys_role_menu` VALUES ('2', '1042');
INSERT INTO `sys_role_menu` VALUES ('2', '1043');
INSERT INTO `sys_role_menu` VALUES ('2', '1044');
INSERT INTO `sys_role_menu` VALUES ('2', '1045');
INSERT INTO `sys_role_menu` VALUES ('2', '1046');
INSERT INTO `sys_role_menu` VALUES ('2', '1047');
INSERT INTO `sys_role_menu` VALUES ('2', '1048');
INSERT INTO `sys_role_menu` VALUES ('2', '1049');
INSERT INTO `sys_role_menu` VALUES ('2', '1050');
INSERT INTO `sys_role_menu` VALUES ('2', '1051');
INSERT INTO `sys_role_menu` VALUES ('2', '1052');
INSERT INTO `sys_role_menu` VALUES ('2', '1053');
INSERT INTO `sys_role_menu` VALUES ('2', '1054');
INSERT INTO `sys_role_menu` VALUES ('2', '1055');
INSERT INTO `sys_role_menu` VALUES ('2', '1056');
INSERT INTO `sys_role_menu` VALUES ('2', '1057');
INSERT INTO `sys_role_menu` VALUES ('2', '1058');
INSERT INTO `sys_role_menu` VALUES ('2', '1059');
INSERT INTO `sys_role_menu` VALUES ('2', '1060');
INSERT INTO `sys_role_menu` VALUES ('2', '1061');
INSERT INTO `sys_role_menu` VALUES ('102', '2005');
INSERT INTO `sys_role_menu` VALUES ('102', '2006');
INSERT INTO `sys_role_menu` VALUES ('102', '2007');
INSERT INTO `sys_role_menu` VALUES ('102', '2008');
INSERT INTO `sys_role_menu` VALUES ('102', '2009');
INSERT INTO `sys_role_menu` VALUES ('102', '2010');
INSERT INTO `sys_role_menu` VALUES ('102', '2011');
INSERT INTO `sys_role_menu` VALUES ('102', '2012');
INSERT INTO `sys_role_menu` VALUES ('102', '2013');
INSERT INTO `sys_role_menu` VALUES ('102', '2014');
INSERT INTO `sys_role_menu` VALUES ('102', '2015');
INSERT INTO `sys_role_menu` VALUES ('102', '2016');
INSERT INTO `sys_role_menu` VALUES ('102', '2017');
INSERT INTO `sys_role_menu` VALUES ('102', '2018');
INSERT INTO `sys_role_menu` VALUES ('102', '2019');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `salt` varchar(20) DEFAULT '' COMMENT '盐加密',
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
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '103', 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '', '29c67a30398638269fe600f73a054934', '111111', '0', '0', '127.0.0.1', '2020-04-06 20:56:21', 'admin', '2018-03-16 11:33:00', 'ry', '2020-04-06 20:56:21', '管理员');
INSERT INTO `sys_user` VALUES ('2', '105', 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '', '8e6d98b90472783cc73c17047ddccf36', '222222', '0', '0', '127.0.0.1', '2020-04-05 21:13:17', 'admin', '2018-03-16 11:33:00', 'ry', '2020-04-05 21:13:17', '测试员');
INSERT INTO `sys_user` VALUES ('100', '105', 'chengxy', '程序猿', '00', '110@gmail.com', '13000000000', '0', '', '83eaa9a017db80e771b9b78ee8319aab', '0093f2', '0', '0', '127.0.0.1', '2020-04-05 21:13:00', 'admin', '2020-02-27 19:43:45', '', '2020-04-05 21:13:00', '注意该用户暂时没有分配角色（即没有分配到流程用户组）');
INSERT INTO `sys_user` VALUES ('101', '105', 'axianlu', '一只闲鹿', '00', '114@gmail.com', '13000000001', '0', '', '0420ce8032acfdc7ba5d59f1bc3f01e5', '309e5c', '0', '0', '127.0.0.1', '2020-04-05 16:49:02', 'admin', '2020-02-27 19:45:15', '', '2020-04-05 16:49:02', null);
INSERT INTO `sys_user` VALUES ('102', '105', 'rensm', '人事喵', '00', '115@gmail.com', '13000000002', '1', '', '63e586cd5426e2fd30c48905cdf7bcfe', 'e12d1b', '0', '0', '127.0.0.1', '2020-03-31 20:20:27', 'admin', '2020-02-27 19:46:20', '', '2020-03-31 20:20:27', null);
INSERT INTO `sys_user` VALUES ('103', '105', '11', 'caiwu', '00', '110@qq.com', '13111111111', '0', '', '9a07736daa48f0586dad267ea360ff7c', '2d7a22', '0', '0', '127.0.0.1', '2020-04-05 21:13:37', 'admin', '2020-03-31 22:58:21', 'admin', '2020-04-05 21:13:37', '');
INSERT INTO `sys_user` VALUES ('104', '105', '22', 'zongjingli', '00', '111@qq.com', '13111111112', '0', '', '6c8017bb59ac49b861067437d88de4e9', '727312', '0', '0', '127.0.0.1', '2020-04-05 21:14:01', 'admin', '2020-03-31 22:59:00', 'admin', '2020-04-05 21:14:01', '');
INSERT INTO `sys_user` VALUES ('105', '105', '33', '33', '00', '113@qq.com', '13111111113', '0', '', '8cff2807327e928e05f206497eec5a3d', 'da2c2e', '0', '0', '', null, 'admin', '2020-03-31 22:59:31', '', null, null);
INSERT INTO `sys_user` VALUES ('106', '105', '44', '44', '00', '114@qq.com', '13111111114', '0', '', '341fc5512c7c010fa8f151b2e0598e11', '4d3ad4', '0', '0', '', null, 'admin', '2020-03-31 22:59:56', '', null, null);
INSERT INTO `sys_user` VALUES ('107', '105', '55', '55', '00', '115@qq.com', '13111111115', '0', '', 'd0e407615613a3281c66edbdcae2f205', '0d3114', '0', '0', '', null, 'admin', '2020-03-31 23:00:20', '', null, null);
INSERT INTO `sys_user` VALUES ('108', '105', '66', '66', '00', '116@qq.com', '13111111116', '0', '', '89f3963f73ad41d4103e969d8dbf1377', '5c6554', '0', '0', '', null, 'admin', '2020-03-31 23:00:45', '', null, null);
INSERT INTO `sys_user` VALUES ('109', '105', 'chairman', 'chairman', '00', '119@qq.com', '13111111119', '0', '', '2dc7933681b07e3bcca0e1ce6e8554ec', 'e4c935', '0', '0', '127.0.0.1', '2020-04-05 21:14:31', 'admin', '2020-04-05 18:14:12', '', '2020-04-05 21:14:31', null);

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
INSERT INTO `sys_user_online` VALUES ('82a70308-473a-40fa-a034-c3620a6eb798', 'admin', '研发部门', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', 'on_line', '2020-04-06 20:56:17', '2020-04-06 20:56:21', '1800000');

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
INSERT INTO `sys_user_role` VALUES ('2', '102');
INSERT INTO `sys_user_role` VALUES ('100', '102');
INSERT INTO `sys_user_role` VALUES ('101', '100');
INSERT INTO `sys_user_role` VALUES ('101', '102');
INSERT INTO `sys_user_role` VALUES ('102', '101');
INSERT INTO `sys_user_role` VALUES ('102', '102');
INSERT INTO `sys_user_role` VALUES ('103', '102');
INSERT INTO `sys_user_role` VALUES ('103', '109');
INSERT INTO `sys_user_role` VALUES ('104', '102');
INSERT INTO `sys_user_role` VALUES ('104', '110');
INSERT INTO `sys_user_role` VALUES ('109', '102');

-- ----------------------------
-- View structure for act_id_group
-- ----------------------------
DROP VIEW IF EXISTS `ACT_ID_GROUP`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ACT_ID_GROUP` AS select `r`.`role_key` AS `ID_`,NULL AS `REV_`,`r`.`role_name` AS `NAME_`,'assignment' AS `TYPE_` from `sys_role` `r` ;

-- ----------------------------
-- View structure for act_id_membership
-- ----------------------------
DROP VIEW IF EXISTS `ACT_ID_MEMBERSHIP`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ACT_ID_MEMBERSHIP` AS select (select `u`.`login_name` from `sys_user` `u` where (`u`.`user_id` = `ur`.`user_id`)) AS `USER_ID_`,(select `r`.`role_key` from `sys_role` `r` where (`r`.`role_id` = `ur`.`role_id`)) AS `GROUP_ID_` from `sys_user_role` `ur` ;

-- ----------------------------
-- View structure for act_id_user
-- ----------------------------
DROP VIEW IF EXISTS `ACT_ID_USER`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ACT_ID_USER` AS select `u`.`login_name` AS `ID_`,0 AS `REV_`,`u`.`user_name` AS `FIRST_`,'' AS `LAST_`,`u`.`email` AS `EMAIL_`,`u`.`password` AS `PWD_`,'' AS `PICTURE_ID_` from `sys_user` `u` ;
