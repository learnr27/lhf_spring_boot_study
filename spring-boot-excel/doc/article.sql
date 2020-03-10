/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : lhf_springboot1

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-10-08 17:18:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `author` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '作者',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '内容',
  `press` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '出版社',
  `pub_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '出版时间',
  `status` tinyint(4) DEFAULT '1' COMMENT '是否删除 1：否 -1：是',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标题',
  `type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文章类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '罗贯中', '滚滚长江东逝水，浪花淘尽英雄', '铁道出版社', '2019-09-10 12:01:35', '1', '三国演义', '名著');
INSERT INTO `article` VALUES ('2', '吴承恩', '俺老孙来也', '人民邮电出版社', '2015-06-19 12:20:48', '1', '西游记', '名著');
INSERT INTO `article` VALUES ('3', '施耐庵', '108位梁山好汉', '清华出版社', '2019-09-09 18:49:03', '1', '水浒传', '名著');
