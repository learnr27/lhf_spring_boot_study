/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : lhf_springboot2

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-11-06 14:25:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `publish` varchar(20) DEFAULT NULL,
  `pages` int(10) DEFAULT NULL,
  `price` float(10,2) DEFAULT NULL,
  `bookcaseid` int(10) DEFAULT NULL,
  `abled` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ieh6qsxp6q7oydadktc9oc8t2` (`bookcaseid`),
  CONSTRAINT `FK_ieh6qsxp6q7oydadktc9oc8t2` FOREIGN KEY (`bookcaseid`) REFERENCES `bookcase` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '解忧杂货店', '东野圭吾', '电子工业出版社', '102', '27.30', '9', '0');
INSERT INTO `book` VALUES ('2', '追风筝的人', '卡勒德·胡赛尼', '上海人民出版社', '230', '33.50', '3', '0');
INSERT INTO `book` VALUES ('3', '人间失格', '太宰治', '作家出版社', '150', '17.30', '1', '1');
INSERT INTO `book` VALUES ('4', '这就是二十四节气', '高春香', '电子工业出版社', '220', '59.00', '3', '1');
INSERT INTO `book` VALUES ('5', '白夜行', '东野圭吾', '南海出版公司', '300', '27.30', '4', '1');
INSERT INTO `book` VALUES ('6', '摆渡人', '克莱儿·麦克福尔', '百花洲文艺出版社', '225', '22.80', '1', '1');
INSERT INTO `book` VALUES ('7', '暖暖心绘本', '米拦弗特毕', '湖南少儿出版社', '168', '131.60', '5', '1');
INSERT INTO `book` VALUES ('8', '天才在左疯子在右', '高铭', '北京联合出版公司', '330', '27.50', '6', '1');
INSERT INTO `book` VALUES ('9', '我们仨', '杨绛', '生活.读书.新知三联书店', '89', '17.20', '7', '1');
INSERT INTO `book` VALUES ('11', '水浒传', '施耐庵', '三联出版社', '300', '50.00', '1', '1');
INSERT INTO `book` VALUES ('12', '三国演义', '罗贯中', '三联出版社', '300', '50.00', '2', '1');
INSERT INTO `book` VALUES ('13', '红楼梦', '曹雪芹', '三联出版社', '300', '50.00', '5', '1');
INSERT INTO `book` VALUES ('14', '西游记', '吴承恩', '三联出版社', '300', '60.00', '3', '1');
INSERT INTO `book` VALUES ('74', '从零开始学Redis', '刘河飞', '中国工信出版集团', '420', '67.00', '9', null);
INSERT INTO `book` VALUES ('75', '天才在左疯子在右', '高铭', '武汉大学出版社', '258', '88.00', '6', null);
INSERT INTO `book` VALUES ('76', '追风筝的人', '卡勒德·胡赛尼', '上海人民出版社', '362', '88.00', '10', null);
INSERT INTO `book` VALUES ('77', '追风筝的人', '卡勒德·胡赛尼', '上海人民出版社', '362', '88.00', '10', null);
