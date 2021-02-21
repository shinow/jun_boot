/*panda 增补sql脚本*/
-- 测试表
DROP TABLE IF EXISTS `sys_test`;

CREATE TABLE `sys_test` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(11) DEFAULT NULL COMMENT '名字',
  `real_name` varchar(11) DEFAULT NULL COMMENT '真名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测试表';
