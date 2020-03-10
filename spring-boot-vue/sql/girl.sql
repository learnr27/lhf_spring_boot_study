/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : lhf_springboot2

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-11-06 14:25:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for girl
-- ----------------------------
DROP TABLE IF EXISTS `girl`;
CREATE TABLE `girl` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姓名',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `height` int(3) DEFAULT NULL COMMENT '身高',
  `weight` int(3) DEFAULT NULL COMMENT '体重',
  `cup_size` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '罩杯',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of girl
-- ----------------------------
INSERT INTO `girl` VALUES ('1', '梦梦', '21', '170', '110', 'B罩杯');
INSERT INTO `girl` VALUES ('2', '珊珊', '21', '160', '54', 'D罩杯');
INSERT INTO `girl` VALUES ('3', '梦月', '23', '170', '55', 'F罩杯');
INSERT INTO `girl` VALUES ('4', '珊蓝', '24', '171', '120', 'C罩杯');
INSERT INTO `girl` VALUES ('5', '月月', '25', '168', '110', 'D罩杯');
INSERT INTO `girl` VALUES ('6', '蓝月', '23', '171', '120', 'C罩杯');
INSERT INTO `girl` VALUES ('7', '文文', '23', '170', '110', 'B罩杯');
INSERT INTO `girl` VALUES ('8', '冬梅', '24', '171', '120', 'C罩杯');
INSERT INTO `girl` VALUES ('9', '秋菊', '25', '168', '110', 'D罩杯');
INSERT INTO `girl` VALUES ('10', '夏荷', '23', '171', '120', 'C罩杯');
INSERT INTO `girl` VALUES ('11', '蝶恋花', '24', '173', '66', 'F罩杯');
INSERT INTO `girl` VALUES ('12', '柳如月', '25', '170', '56', 'C罩杯');
INSERT INTO `girl` VALUES ('13', '月如钩', '24', '169', '57', 'C罩杯');
INSERT INTO `girl` VALUES ('14', '沉鱼落雁', '24', '179', '57', 'C罩杯');
INSERT INTO `girl` VALUES ('15', '鱼菲儿', '24', '170', '55', 'D罩杯');
INSERT INTO `girl` VALUES ('16', '鱼菲儿', '24', '170', '55', 'D罩杯');
