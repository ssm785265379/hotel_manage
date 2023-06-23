/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50148
Source Host           : localhost:3306
Source Database       : hotel

Target Server Type    : MYSQL
Target Server Version : 50148
File Encoding         : 65001

Date: 2022-05-17 23:27:45
啊啊
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for in_room_info
-- ----------------------------
DROP TABLE IF EXISTS `in_room_info`;
CREATE TABLE `in_room_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `room_num` bigint(255) NOT NULL,
  `room_type_name` varchar(255) NOT NULL,
  `room_price` bigint(20) NOT NULL COMMENT '房间表主键',
  `customer_name` varchar(40) NOT NULL COMMENT '客人姓名',
  `gender` varchar(2) NOT NULL DEFAULT '1' COMMENT '性别(1男 0女)',
  `idcard` varchar(20) NOT NULL COMMENT '身份证号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `money` float(10,2) NOT NULL COMMENT '押金',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '入住时间',
  `esout_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `out_room_status` varchar(2) DEFAULT '0' COMMENT '退房状态：0未退房 1已经退房',
  PRIMARY KEY (`id`,`idcard`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of in_room_info
-- ----------------------------
INSERT INTO `in_room_info` VALUES ('70', '8201', '单人间', '20', '沈世贸', '1', '330326200007302232', '13757880647', '10.00', '2022-05-17 14:57:56', '2022-05-22 00:00:00', '1');
INSERT INTO `in_room_info` VALUES ('71', '8201', '单人间', '20', '刘备', '1', '330326200007302232', '15381198768', '10.00', '2022-05-17 16:31:31', '2022-05-18 00:00:00', '1');
INSERT INTO `in_room_info` VALUES ('73', '8202', '单人间', '20', 'ssm', '1', '330326200007302231', '13757880647', '60.00', '2022-05-17 16:46:41', '2022-05-20 00:00:00', '1');
INSERT INTO `in_room_info` VALUES ('73', '8202', '单人间', '20', '沈世贸', '1', '330326200007302232', '13757880647', '60.00', '2022-05-17 16:46:41', '2022-05-20 00:00:00', '1');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_num` varchar(50) NOT NULL COMMENT '订单编号',
  `order_money` float(10,2) NOT NULL COMMENT '订单总价',
  `remark` varchar(100) DEFAULT NULL COMMENT '订单备注',
  `order_status` varchar(2) NOT NULL DEFAULT '0' COMMENT '0未结算，1已结算',
  `iri_id` bigint(20) NOT NULL COMMENT '入住信息主键',
  `create_date` datetime NOT NULL COMMENT '下单时间',
  `room_num` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `iri_id` (`iri_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`iri_id`) REFERENCES `in_room_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('27', '1', '20.00', null, '1', '70', '2022-05-17 14:57:30', '8201');
INSERT INTO `orders` VALUES ('28', '2', '100.00', null, '1', '73', '2022-05-17 16:45:14', '8202');

-- ----------------------------
-- Table structure for out_room_info
-- ----------------------------
DROP TABLE IF EXISTS `out_room_info`;
CREATE TABLE `out_room_info` (
  `id` bigint(255) NOT NULL,
  `room_num` varchar(255) NOT NULL,
  `room_type` varchar(255) NOT NULL,
  `room_price` decimal(10,2) NOT NULL,
  `customer_name` varchar(255) NOT NULL,
  `idcard` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `money` float(10,2) NOT NULL,
  `order_money` float(10,2) NOT NULL,
  `cost` float(10,2) NOT NULL,
  `in_date` datetime NOT NULL,
  `out_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of out_room_info
-- ----------------------------
INSERT INTO `out_room_info` VALUES ('70', '8201', '单人间', '20.00', '沈世贸', '330326200007302232', '13757880647', '10.00', '20.00', '36.00', '2022-05-17 14:57:04', '2022-05-17 14:57:46');
INSERT INTO `out_room_info` VALUES ('71', '8201', '单人间', '20.00', '刘备', '330326200007302232', '15381198768', '10.00', '0.00', '16.00', '2022-05-17 16:29:13', '2022-05-17 16:31:11');
INSERT INTO `out_room_info` VALUES ('73', '8202', '单人间', '20.00', 'ssm', '330326200007302231', '13757880647', '60.00', '100.00', '120.00', '2022-05-17 16:35:38', '2022-05-17 16:45:34');

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `position` varchar(255) NOT NULL,
  `salary` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `position` (`position`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('1', '管理员', '0.00');
INSERT INTO `position` VALUES ('2', '前台', '3000.00');
INSERT INTO `position` VALUES ('3', '保洁', '2500.00');

-- ----------------------------
-- Table structure for rooms
-- ----------------------------
DROP TABLE IF EXISTS `rooms`;
CREATE TABLE `rooms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `room_num` varchar(10) DEFAULT NULL COMMENT '房间编号',
  `room_status` varchar(2) DEFAULT '0' COMMENT '房间的状态(0空闲，1已入住，2打扫)',
  `room_type_id` bigint(20) DEFAULT NULL COMMENT '房间类型主键',
  PRIMARY KEY (`id`),
  UNIQUE KEY `room_num` (`room_num`),
  KEY `room_type` (`room_type_id`),
  CONSTRAINT `room_type` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rooms
-- ----------------------------
INSERT INTO `rooms` VALUES ('1', '8201', '2', '1');
INSERT INTO `rooms` VALUES ('2', '8202', '2', '1');
INSERT INTO `rooms` VALUES ('3', '8203', '0', '1');
INSERT INTO `rooms` VALUES ('6', '8205', '0', '3');
INSERT INTO `rooms` VALUES ('7', '8206', '0', '3');
INSERT INTO `rooms` VALUES ('8', '8207', '0', '2');
INSERT INTO `rooms` VALUES ('11', '8208', '0', '5');
INSERT INTO `rooms` VALUES ('12', '8209', '0', '3');
INSERT INTO `rooms` VALUES ('13', '8210', '0', '5');
INSERT INTO `rooms` VALUES ('14', '8211', '0', '1');
INSERT INTO `rooms` VALUES ('15', '8212', '0', '3');
INSERT INTO `rooms` VALUES ('16', '8213', '0', '5');
INSERT INTO `rooms` VALUES ('17', '8214', '0', '2');
INSERT INTO `rooms` VALUES ('18', '101', '0', '1');

-- ----------------------------
-- Table structure for room_type
-- ----------------------------
DROP TABLE IF EXISTS `room_type`;
CREATE TABLE `room_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `room_type_name` varchar(20) NOT NULL COMMENT '房间类型名',
  `room_price` float(10,2) NOT NULL COMMENT '房间的单价',
  PRIMARY KEY (`id`),
  UNIQUE KEY `room_type_name` (`room_type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room_type
-- ----------------------------
INSERT INTO `room_type` VALUES ('1', '单人间', '20.00');
INSERT INTO `room_type` VALUES ('2', '双人间', '180.00');
INSERT INTO `room_type` VALUES ('3', '豪华间', '280.00');
INSERT INTO `room_type` VALUES ('5', '总统套房', '500.00');
INSERT INTO `room_type` VALUES ('6', '钟点房', '100.00');
INSERT INTO `room_type` VALUES ('7', '情侣套房', '599.00');
INSERT INTO `room_type` VALUES ('8', '单人间带窗户', '200.00');
INSERT INTO `room_type` VALUES ('9', '双刃剑带窗户', '249.00');
INSERT INTO `room_type` VALUES ('10', '总统房带窗户', '999.00');
INSERT INTO `room_type` VALUES ('11', '闺蜜房', '99.00');
INSERT INTO `room_type` VALUES ('12', '三人间', '300.00');

-- ----------------------------
-- Table structure for system_authority
-- ----------------------------
DROP TABLE IF EXISTS `system_authority`;
CREATE TABLE `system_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `authority_name` varchar(20) DEFAULT NULL COMMENT '权限名',
  `authority_url` varchar(200) DEFAULT '#' COMMENT '权限跳转地址',
  `parent` bigint(20) DEFAULT '0' COMMENT '记住上级的主键，0为一级节点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_authority
-- ----------------------------
INSERT INTO `system_authority` VALUES ('1', '入住管理', '#', '0');
INSERT INTO `system_authority` VALUES ('2', '消费管理', '#', '0');
INSERT INTO `system_authority` VALUES ('3', '会员管理', '#', '0');
INSERT INTO `system_authority` VALUES ('4', '客房管理', '#', '0');
INSERT INTO `system_authority` VALUES ('5', '系统管理', '#', '0');
INSERT INTO `system_authority` VALUES ('6', '客人意见', '#', '0');
INSERT INTO `system_authority` VALUES ('7', '预约管理', '#', '0');
INSERT INTO `system_authority` VALUES ('10', '入住信息添加', '/pages/bill/checkin.html', '1');
INSERT INTO `system_authority` VALUES ('11', '入住信息查询', '/pages/bill/inRoomManage.html', '1');
INSERT INTO `system_authority` VALUES ('12', '结账退房', '/pages/bill/out.html', '1');
INSERT INTO `system_authority` VALUES ('13', '退房信息查询', '/pages/bill/outRoomManage.html', '1');
INSERT INTO `system_authority` VALUES ('20', '消费记录', '/pages/bill/showCost.html', '2');
INSERT INTO `system_authority` VALUES ('30', '添加会员', '/pages/vip/addVip.html', '3');
INSERT INTO `system_authority` VALUES ('31', '会员信息查询', '/pages/vip/vipManage.html', '3');
INSERT INTO `system_authority` VALUES ('41', '客房信息查询', '/pages/room/showRoom.html', '4');
INSERT INTO `system_authority` VALUES ('42', '客房信息管理', '/pages/room/roomManage.html', '4');
INSERT INTO `system_authority` VALUES ('43', '客房类型管理', '/pages/room/showRoomType.html', '4');
INSERT INTO `system_authority` VALUES ('51', '用户信息管理', '/pages/user/systemUserManage.html', '5');
INSERT INTO `system_authority` VALUES ('52', '职位信息管理', '/pages/user/positionManage.html', '5');
INSERT INTO `system_authority` VALUES ('53', '添加用户', '/toAddUser.do', '5');
INSERT INTO `system_authority` VALUES ('54', '修改用户', '/toChangeUser.do', '5');
INSERT INTO `system_authority` VALUES ('55', '修改密码', '/pages/user/changePassword.html', '5');
INSERT INTO `system_authority` VALUES ('71', '订单添加', '/pages/order/addOrder.html', '7');

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(40) NOT NULL COMMENT '账号',
  `pwd` varchar(40) NOT NULL COMMENT '密码',
  `phone` varchar(255) NOT NULL,
  `idcard` varchar(255) NOT NULL,
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `use_status` varchar(2) NOT NULL DEFAULT '1' COMMENT '启用状态：1启用，0禁用',
  `is_admin` varchar(2) NOT NULL DEFAULT '0' COMMENT '1超级管理员，0普通管理员',
  `position_id` int(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pid` (`position_id`),
  CONSTRAINT `pid` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('1', 'admin', 'f67bdadd7d426a4e7210dd04390bf65b', '1', '1', '2018-10-12 14:20:27', '1', '1', '1');
INSERT INTO `system_user` VALUES ('24', '小五', 'f67bdadd7d426a4e7210dd04390bf65b', '13757880647', '330326200007302232', '2022-05-17 22:27:39', '1', '0', '2');

-- ----------------------------
-- Table structure for user_authority
-- ----------------------------
DROP TABLE IF EXISTS `user_authority`;
CREATE TABLE `user_authority` (
  `user_id` bigint(20) DEFAULT NULL COMMENT '系统用户主键',
  `authority_id` bigint(20) DEFAULT NULL COMMENT '权限主键',
  KEY `uid` (`user_id`),
  KEY `aid` (`authority_id`),
  CONSTRAINT `aid` FOREIGN KEY (`authority_id`) REFERENCES `system_authority` (`id`),
  CONSTRAINT `uid` FOREIGN KEY (`user_id`) REFERENCES `system_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_authority
-- ----------------------------
INSERT INTO `user_authority` VALUES ('1', '2');
INSERT INTO `user_authority` VALUES ('1', '3');
INSERT INTO `user_authority` VALUES ('1', '4');
INSERT INTO `user_authority` VALUES ('1', '5');
INSERT INTO `user_authority` VALUES ('1', '6');
INSERT INTO `user_authority` VALUES ('1', '7');
INSERT INTO `user_authority` VALUES ('1', '10');
INSERT INTO `user_authority` VALUES ('1', '11');
INSERT INTO `user_authority` VALUES ('1', '12');
INSERT INTO `user_authority` VALUES ('1', '20');
INSERT INTO `user_authority` VALUES ('1', '30');
INSERT INTO `user_authority` VALUES ('1', '31');
INSERT INTO `user_authority` VALUES ('1', '41');
INSERT INTO `user_authority` VALUES ('1', '42');
INSERT INTO `user_authority` VALUES ('1', '43');
INSERT INTO `user_authority` VALUES ('1', '51');
INSERT INTO `user_authority` VALUES ('1', '52');
INSERT INTO `user_authority` VALUES ('1', '53');
INSERT INTO `user_authority` VALUES ('1', '54');
INSERT INTO `user_authority` VALUES ('1', '55');
INSERT INTO `user_authority` VALUES ('1', '71');
INSERT INTO `user_authority` VALUES ('1', '1');
INSERT INTO `user_authority` VALUES ('1', '11');
INSERT INTO `user_authority` VALUES ('1', '13');
INSERT INTO `user_authority` VALUES ('24', '1');
INSERT INTO `user_authority` VALUES ('24', '2');
INSERT INTO `user_authority` VALUES ('24', '10');
INSERT INTO `user_authority` VALUES ('24', '11');
INSERT INTO `user_authority` VALUES ('24', '12');
INSERT INTO `user_authority` VALUES ('24', '13');
INSERT INTO `user_authority` VALUES ('24', '20');

-- ----------------------------
-- Table structure for vip
-- ----------------------------
DROP TABLE IF EXISTS `vip`;
CREATE TABLE `vip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vip_num` varchar(50) NOT NULL COMMENT '会员卡编号',
  `vip_rate` float(3,2) NOT NULL DEFAULT '0.90' COMMENT '1~9折',
  `customer_name` varchar(40) NOT NULL COMMENT '会员姓名',
  `gender` varchar(2) NOT NULL DEFAULT '1' COMMENT '性别：1男 0女',
  `idcard` varchar(20) NOT NULL COMMENT '会员身份证',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `create_date` datetime NOT NULL COMMENT '会员办理日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`idcard`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vip
-- ----------------------------
INSERT INTO `vip` VALUES ('28', '000001', '0.80', '沈世贸', 'on', '330326200007302232', '13757880647', '2022-05-17 14:56:17');
