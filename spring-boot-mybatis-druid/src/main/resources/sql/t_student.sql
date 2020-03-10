/*
Navicat MySQL Data Transfer

Source Server         : Server1
Source Server Version : 50717
Source Host           : 10.12.67.46:3306
Source Database       : lhf_springboot

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-07-19 17:38:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `sid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sno` varchar(10) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `sex` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('1', '001', '张三', '男');
INSERT INTO `t_student` VALUES ('2', '002', '李四', '男');
INSERT INTO `t_student` VALUES ('3', '003', '王五', '女');
