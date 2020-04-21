/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50162
Source Host           : localhost:3306
Source Database       : cloud-alibaba

Target Server Type    : MYSQL
Target Server Version : 50162
File Encoding         : 65001

Date: 2020-04-21 12:57:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for adress
-- ----------------------------
DROP TABLE IF EXISTS `adress`;
CREATE TABLE `adress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adress` varchar(255) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adress
-- ----------------------------
INSERT INTO `adress` VALUES ('1', '兖州', '1');
INSERT INTO `adress` VALUES ('2', '徐州', '1');
INSERT INTO `adress` VALUES ('3', '荆州', '2');
INSERT INTO `adress` VALUES ('4', '西川', '2');
INSERT INTO `adress` VALUES ('5', '江东', '3');
INSERT INTO `adress` VALUES ('6', '建康', '3');
INSERT INTO `adress` VALUES ('7', '西凉', '1');
