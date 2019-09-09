-- 用户信息表主键自增
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(10) NOT NULL COMMENT '姓名',
  `age` tinyint(3) NOT NULL DEFAULT '18' COMMENT '年龄',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';

-- 地址信息表主键使用自定义生成策略-基于snowflake算法
-- 一个用户可用于多个地址，即用户对地址是一对多关系
CREATE TABLE `address` (
  `id` varchar(18) NOT NULL COMMENT '主键',
  `city` varchar(15) NOT NULL COMMENT '城市名称',
  `address` varchar(30) NOT NULL COMMENT '详细地址',
  `user_id` bigint(20) NOT NULL COMMENT '用户信息ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地址信息';

