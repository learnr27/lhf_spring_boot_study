/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : lhf_springboot1

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-08-02 10:33:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for girl
-- ----------------------------
DROP TABLE IF EXISTS `girl`;
CREATE TABLE `girl` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姓名',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `height` int(3) DEFAULT NULL COMMENT '身高',
  `weight` int(3) DEFAULT NULL COMMENT '体重',
  `cup_size` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '罩杯',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of girl
-- ----------------------------
