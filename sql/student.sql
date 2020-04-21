/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50162
Source Host           : localhost:3306
Source Database       : cloud-alibaba

Target Server Type    : MYSQL
Target Server Version : 50162
File Encoding         : 65001

Date: 2020-04-21 12:58:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `score` decimal(10,2) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '曹操', '98.82', 'caocao@qq.com');
INSERT INTO `student` VALUES ('3', '刘备', '60.34', 'liubei@qq.com');
INSERT INTO `student` VALUES ('4', '孙权', '75.83', '孙权@qq.com');
