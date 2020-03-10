/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : lhf_springboot2

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-11-06 14:25:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bookcase
-- ----------------------------
DROP TABLE IF EXISTS `bookcase`;
CREATE TABLE `bookcase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookcase
-- ----------------------------
INSERT INTO `bookcase` VALUES ('1', '社会');
INSERT INTO `bookcase` VALUES ('2', '情感');
INSERT INTO `bookcase` VALUES ('3', '国学');
INSERT INTO `bookcase` VALUES ('4', '推理');
INSERT INTO `bookcase` VALUES ('5', '绘画');
INSERT INTO `bookcase` VALUES ('6', '心理学');
INSERT INTO `bookcase` VALUES ('7', '传记');
INSERT INTO `bookcase` VALUES ('8', '科技');
INSERT INTO `bookcase` VALUES ('9', '计算机');
INSERT INTO `bookcase` VALUES ('10', '小说');
