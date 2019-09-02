CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(10) NOT NULL COMMENT '姓名',
  `age` tinyint(3) NOT NULL DEFAULT '18' COMMENT '年龄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';

CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `city` varchar(15) NOT NULL COMMENT '城市名称',
  `address` varchar(30) NOT NULL COMMENT '详细地址',
  `user_id` bigint(20) NOT NULL COMMENT '用户信息ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地址信息';

