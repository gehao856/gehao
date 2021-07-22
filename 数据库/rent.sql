/*
Navicat MySQL Data Transfer

Source Server         : db
Source Server Version : 50623
Source Host           : localhost:3306
Source Database       : rent

Target Server Type    : MYSQL
Target Server Version : 50623
File Encoding         : 65001

Date: 2020-11-12 10:19:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for contract
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `customer_id` int(10) DEFAULT NULL,
  `houses_id` int(10) DEFAULT NULL,
  `qzrq` datetime DEFAULT NULL COMMENT '起租日期',
  `kfrq` datetime DEFAULT NULL COMMENT '退租日期',
  `tzrq` datetime DEFAULT NULL,
  `money` double(5,2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `djr` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `xgr` varchar(20) DEFAULT NULL,
  `rent_id` char(10) DEFAULT NULL,
  `payrent` varchar(20) DEFAULT NULL COMMENT '付款日',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='合同信息表';

-- ----------------------------
-- Records of contract
-- ----------------------------
INSERT INTO `contract` VALUES ('10', '1001', '8', '2020-09-30 16:00:00', null, '2020-10-30 16:00:00', '100.00', null, null, null, null, '2001', '12');
INSERT INTO `contract` VALUES ('16', '1001', '12', '2020-10-31 16:00:00', null, '2020-10-30 16:00:00', '100.00', null, null, null, null, '2006', '1');
INSERT INTO `contract` VALUES ('14', '1001', '12', '2020-10-31 16:00:00', null, '2020-10-30 16:00:00', '100.00', null, null, null, null, '2006', '1');
INSERT INTO `contract` VALUES ('17', '1001', '12', '2020-09-30 16:00:00', null, '2020-10-30 16:00:00', '100.00', null, null, null, null, '2006', '1');
INSERT INTO `contract` VALUES ('18', '1001', '12', '2020-09-30 16:00:00', null, '2020-10-30 16:00:00', '110.00', null, null, null, null, '2006', '2');
INSERT INTO `contract` VALUES ('19', '1001', '12', '2020-08-30 16:00:00', null, '2020-10-15 16:00:00', '230.00', null, null, null, null, '2006', '5');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identity` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `custname` varchar(20) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL COMMENT '0 代表女\r\n            1 代表男',
  `address` varchar(120) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `career` varchar(20) DEFAULT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `djr` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `xgr` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1007 DEFAULT CHARSET=utf8 COMMENT='租户信息表';

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1001', '123456', '123456', 'lily', '女', '北京', '15612345678', '程序员', '无', null, null, null, null);
INSERT INTO `customer` VALUES ('1002', '4212334757676', '123456', '刘晓1', '男', '233334343', '13134566777', 'ui', '1111111111', null, null, null, null);
INSERT INTO `customer` VALUES ('1006', '321123199012011234', '123456', 'zhangsan', '男', '文化路002号', '13210011234', '自由职业', '', null, null, null, null);

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `type` int(10) DEFAULT NULL,
  `sort` int(10) DEFAULT NULL ,
  `status` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='部门信息表';

-- ----------------------------
-- Records of dept
-- ----------------------------

-- ----------------------------
-- Table structure for houses
-- ----------------------------
DROP TABLE IF EXISTS `houses`;
CREATE TABLE `houses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numbers` varchar(20) DEFAULT NULL,
  `owner_id` int(10) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `type` double(5,2) DEFAULT NULL,
  `imgs` varchar(200) DEFAULT NULL,
  `province` varchar(100) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `county` varchar(20) DEFAULT NULL,
  `sort` double(5,2) DEFAULT NULL,
  `status` int(10) DEFAULT NULL COMMENT '0 未出租\r\n            1 出租中\r\n            2 已退租',
  `remarks` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `area` double(10,2) DEFAULT NULL COMMENT '面积',
  `price` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='房屋信息管理';

-- ----------------------------
-- Records of houses
-- ----------------------------
INSERT INTO `houses` VALUES ('8', 'T001', '1', '大学路002号', null, 'images/353e2b05-bef1-42e1-8550-8a9f50a87e5e.png', '湖北省', '武汉市', '江岸区', null, '1', null, null, null, null, '89.00', '1200.00');
INSERT INTO `houses` VALUES ('11', 'T002', '2', '建设西路', null, 'images/01d34f95-2973-4263-8be1-bf7da61b60b6.png', '福建省', '福州市', '鼓楼区', null, '0', '快速下手', null, null, null, '100.00', '1500.00');
INSERT INTO `houses` VALUES ('12', 'T003', '2', '建设西路001', null, 'images/380795f1-02c5-4752-ae7c-6a348d72c3de.png', '福建省', '福州市', '鼓楼区', null, '1', null, null, null, null, '88.00', '1300.00');

-- ----------------------------
-- Table structure for lookhouse
-- ----------------------------
DROP TABLE IF EXISTS `lookhouse`;
CREATE TABLE `lookhouse` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `houses_id` int(10) DEFAULT NULL,
  `customer_id` int(10) DEFAULT NULL,
  `look_date` datetime DEFAULT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `djr` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `xgr` varchar(20) DEFAULT NULL,
  `stutas` int(2) DEFAULT NULL COMMENT '0 未看房           1 看房拒绝租赁',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2007 DEFAULT CHARSET=utf8 COMMENT='看房信息表';

-- ----------------------------
-- Records of lookhouse
-- ----------------------------
INSERT INTO `lookhouse` VALUES ('2001', '8', '1001', '2020-10-07 14:57:31', '看房', null, null, null, null, '0');
INSERT INTO `lookhouse` VALUES ('2002', '11', '1001', '2020-10-10 08:31:52', '看房', '2020-10-07 11:08:26', '', '2020-10-27 11:08:31', '', '1');
INSERT INTO `lookhouse` VALUES ('2006', '12', '1001', '2020-10-19 16:00:00', '阳光房', '2020-10-19 08:28:40', null, null, null, '1');
INSERT INTO `lookhouse` VALUES ('2005', '11', '1001', '2020-10-17 16:00:00', '123455', '2020-10-18 12:21:19', null, null, null, '1');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `type` int(10) DEFAULT NULL,
  `sort` int(10) DEFAULT NULL COMMENT '0 代表女\r\n            1 代表男',
  `status` int(10) DEFAULT NULL,
  `url` varchar(20) DEFAULT NULL,
  `permission` varchar(20) DEFAULT NULL,
  `icon` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='菜单信息表';

-- ----------------------------
-- Records of menu
-- ----------------------------

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `content` varchar(200) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `djr` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `xgr` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='公告信息表 公布最新优质房源';

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '10月19日，成都大学召开干部大会，宣布市委关于成都大学党委书记的任职决定：市委教育工委书记、市教育局党组书记、局长刘强任成都大学党委书记', '2020-09-21 15:59:58', 'admin', null, null);
INSERT INTO `notice` VALUES ('2', '成都大学党委书记毛洪涛，于15日在个人微信朋友圈留下“绝笔信”后失联。事发后，该事件迅速引发全国关注。成都警方通报称，今晨6时许，毛洪涛的遗体在江安河温江段一河道内被发现，初步判断为溺水身亡，排除刑事案件。在确认溺亡后，多名学生发文追忆毛洪涛。\r\n\r\n成都大学党委书记毛洪涛，于15日在个人微信朋友圈留下“绝笔信”后失联。事发后，该事件迅速引发全国关注。成都警方通报称', '2020-10-19 16:13:53', 'kappy', null, null);
INSERT INTO `notice` VALUES ('3', '为更好的服务新农村建设，有效发挥农村客运站功能作用,推动农村客运、物流和旅游发展。南芬区交通运输局将位于思山岭街道财神庙村农村客运站（面积约146平），对外采取“交通+旅游”合作的形式进行租赁。相关要求如下', '2020-10-19 16:23:39', 'admin', null, null);

-- ----------------------------
-- Table structure for owner
-- ----------------------------
DROP TABLE IF EXISTS `owner`;
CREATE TABLE `owner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identity` varchar(20) DEFAULT NULL,
  `custname` varchar(20) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL COMMENT '0 代表女\r\n            1 代表男',
  `address` varchar(120) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `career` varchar(20) DEFAULT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `djr` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='房东信息表';

-- ----------------------------
-- Records of owner
-- ----------------------------
INSERT INTO `owner` VALUES ('1', '233232', 'kappy1', '女', '西安', '13523454321', '程序员', '飒飒1', null, null);
INSERT INTO `owner` VALUES ('2', '211590199901021234', 'kevin', '男', '大学路001号', '13212345678', '程序员', '无', null, null);
INSERT INTO `owner` VALUES ('18', '231234198810021324', 'admin', '男', '雁塔路光明街', '13423783425', 'IT运维', null, null, null);

-- ----------------------------
-- Table structure for rentals
-- ----------------------------
DROP TABLE IF EXISTS `rentals`;
CREATE TABLE `rentals` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `houses_id` int(10) DEFAULT NULL,
  `customer_id` int(10) DEFAULT NULL,
  `money` int(5) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `jzrq` datetime DEFAULT NULL COMMENT '截至日期',
  `jfr` varchar(200) DEFAULT NULL COMMENT '交租人',
  `remarks` varchar(200) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `djr` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `xgr` varchar(20) DEFAULT NULL,
  `ksrq` datetime DEFAULT NULL COMMENT '开始日期',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10011 DEFAULT CHARSET=utf8 COMMENT='收租信息表';

-- ----------------------------
-- Records of rentals
-- ----------------------------
INSERT INTO `rentals` VALUES ('10000', '8', '1002', '400', '郑州', '2020-09-30 14:24:00', '张三', '没交完', '2020-08-07 10:39:34', null, null, null, '2020-09-01 14:23:56');
INSERT INTO `rentals` VALUES ('10002', '8', '1002', '800', '郑州', '2020-10-12 00:00:00', '张三', '没交完', '2020-04-01 14:37:44', '', '2020-10-20 14:37:54', '', '2020-10-01 14:23:56');
INSERT INTO `rentals` VALUES ('10009', '11', '1001', '1000', '北京', '2020-11-29 16:00:00', 'zhangwu', '有人帮忙缴费了', '2020-10-10 16:19:45', null, null, null, '2020-10-31 16:00:00');
INSERT INTO `rentals` VALUES ('10010', '11', '1001', '1590', '上海', '2020-11-29 16:00:00', 'zhangwu', '有人帮忙缴费了', '2020-11-10 16:19:45', '', '2020-10-22 22:35:25', '', '2020-10-31 16:00:00');

-- ----------------------------
-- Table structure for rentinfo
-- ----------------------------
DROP TABLE IF EXISTS `rentinfo`;
CREATE TABLE `rentinfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `houses_id` int(10) DEFAULT NULL,
  `customer_id` int(10) DEFAULT NULL,
  `contract_id` int(10) DEFAULT NULL,
  `address` char(10) DEFAULT NULL,
  `remarks` char(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `djr` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `xgr` varchar(20) DEFAULT NULL,
  `status` int(10) DEFAULT NULL COMMENT '0未退租\r\n1 退租\r\n',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2011 DEFAULT CHARSET=utf8 COMMENT='租赁信息表';

-- ----------------------------
-- Records of rentinfo
-- ----------------------------
INSERT INTO `rentinfo` VALUES ('2001', '8', '1001', '10', '12221', null, '2020-10-07 05:13:31', null, null, null, '1');
INSERT INTO `rentinfo` VALUES ('2005', '11', '1002', '14', null, null, '2020-10-10 05:02:12', null, null, null, '1');
INSERT INTO `rentinfo` VALUES ('2010', '12', '1001', '19', null, null, '2020-10-19 09:17:19', null, null, null, '0');

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `houses_id` int(10) DEFAULT NULL,
  `customer_id` int(10) DEFAULT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `djr` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `xgr` varchar(20) DEFAULT NULL,
  `status` int(2) DEFAULT NULL COMMENT '0 未修复\r\n            1 已经修',
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=214 DEFAULT CHARSET=utf8 COMMENT='报修信息表';

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES ('205', '8', '1001', '在干嘛', '2020-10-07 03:32:08', null, null, null, '0', null);
INSERT INTO `repair` VALUES ('206', '8', '1001', '在干嘛111\n修好啦', '2020-05-07 03:32:08', '21', '2020-10-15 11:27:21', 'ww', '1', '');
INSERT INTO `repair` VALUES ('209', '11', '1002', '水管问题太多了', '2020-10-10 17:34:49', null, null, null, '0', null);
INSERT INTO `repair` VALUES ('210', '11', '1002', '水管问题太多了', '2020-04-10 17:34:49', '', '2020-10-05 22:24:07', '', '0', '');
INSERT INTO `repair` VALUES ('211', '11', '1002', '水管问题太多了', '2020-05-10 17:34:49', '', '2020-10-05 22:24:07', '', '0', '');
INSERT INTO `repair` VALUES ('212', '11', '1002', '水管问题太多了', '2020-01-10 17:34:49', '', '2020-10-05 22:24:07', '', '0', '');
INSERT INTO `repair` VALUES ('213', '11', '1002', '水管问题太多了', '2020-05-10 17:34:49', '', '2020-10-05 22:24:07', '', '0', '');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `type` int(10) DEFAULT NULL,
  `sort` int(10) DEFAULT NULL COMMENT '0 代表女\r\n            1 代表男',
  `status` int(10) DEFAULT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(10) DEFAULT NULL,
  `role_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_id` int(10) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL COMMENT '0 代表女\r\n            1 代表男',
  `realname` varchar(120) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `avatar` varchar(20) DEFAULT NULL,
  `job_title` varchar(20) DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  `sort` int(10) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `create_by` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', null, 'kappy', '123', 'zhangsan', '女', '110', 'kappy', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('2', null, 'kevin', '123456', 'lisi', '男', '120', 'lisi@163.com', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `role_id` int(10) DEFAULT NULL,
  `create_by` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
