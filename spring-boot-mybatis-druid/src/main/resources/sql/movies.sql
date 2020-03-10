/*
Navicat MySQL Data Transfer

Source Server         : Server1
Source Server Version : 50717
Source Host           : 10.12.67.46:3306
Source Database       : lhf_springboot

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-07-19 11:53:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for movies
-- ----------------------------
DROP TABLE IF EXISTS `movies`;
CREATE TABLE `movies` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `movie_name` varchar(50) DEFAULT NULL COMMENT '电影名称',
  `movie_actor` varchar(100) DEFAULT NULL COMMENT '电影主演',
  `long_time` int(11) DEFAULT NULL COMMENT '时长/分钟',
  `movie_desc` varchar(255) DEFAULT NULL COMMENT '电影描述',
  `show_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上映时间',
  `box_office` decimal(10,4) DEFAULT NULL COMMENT '票房/亿元',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of movies
-- ----------------------------
INSERT INTO `movies` VALUES ('1', '《战狼》', '吴京', '98', '动作，军事，爱国片', '2016-05-01 11:49:46', '9.6789');
INSERT INTO `movies` VALUES ('2', '《战狼2》', '吴京', '125', '动作，战争，军事，爱国，救援', '2017-10-01 11:52:51', '56.8976');
