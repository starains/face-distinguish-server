/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50157
Source Host           : localhost:3306
Source Database       : volunteer

Target Server Type    : MYSQL
Target Server Version : 50157
File Encoding         : 65001

Date: 2019-05-20 09:39:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tf_f_user
-- ----------------------------
DROP TABLE IF EXISTS `tf_f_user`;
CREATE TABLE `tf_f_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `username` varchar(255) NOT NULL COMMENT '用户昵称',
  `imgbase64` mediumblob,
  `registtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
