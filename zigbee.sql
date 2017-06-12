/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : zigbee

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2017-06-12 22:05:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `zigbee_receive_data`
-- ----------------------------
DROP TABLE IF EXISTS `zigbee_receive_data`;
CREATE TABLE `zigbee_receive_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `temperature` varchar(50) NOT NULL COMMENT '温度',
  `humidity` varchar(50) NOT NULL COMMENT '湿度',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zigbee_receive_data
-- ----------------------------
INSERT INTO `zigbee_receive_data` VALUES ('1', '30', '15', '2017-04-13 21:27:06', '2017-04-13 21:27:06');
INSERT INTO `zigbee_receive_data` VALUES ('2', '25', '10', '2017-04-13 21:27:06', '2017-04-13 21:27:06');
INSERT INTO `zigbee_receive_data` VALUES ('3', '111', '222', '2017-06-12 22:03:02', '2017-06-12 22:03:02');
