/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : zigbee

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2017-06-13 22:03:45
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zigbee_receive_data
-- ----------------------------
INSERT INTO `zigbee_receive_data` VALUES ('1', '30', '15', '2017-04-13 21:27:06', '2017-04-13 21:27:06');
INSERT INTO `zigbee_receive_data` VALUES ('2', '25', '10', '2017-04-13 21:27:06', '2017-04-13 21:27:06');
INSERT INTO `zigbee_receive_data` VALUES ('3', '111', '222', '2017-06-12 22:03:02', '2017-06-12 22:03:02');
INSERT INTO `zigbee_receive_data` VALUES ('4', '222', '33', '2017-06-12 22:16:46', '2017-06-12 22:16:46');
INSERT INTO `zigbee_receive_data` VALUES ('5', '33', '33', '2017-06-13 16:11:15', '2017-06-13 16:11:15');
INSERT INTO `zigbee_receive_data` VALUES ('6', '22', '34', '2017-06-13 16:11:57', '2017-06-13 16:11:57');
INSERT INTO `zigbee_receive_data` VALUES ('7', '22', '3', '2017-06-13 16:14:38', '2017-06-13 16:14:38');
INSERT INTO `zigbee_receive_data` VALUES ('8', '1', '20', '2017-06-13 17:55:30', '2017-06-13 17:55:30');
INSERT INTO `zigbee_receive_data` VALUES ('9', '30', '20', '2017-06-13 18:02:31', '2017-06-13 18:02:31');
INSERT INTO `zigbee_receive_data` VALUES ('10', '13', '33', '2017-06-13 19:11:54', '2017-06-13 19:11:54');
INSERT INTO `zigbee_receive_data` VALUES ('11', '14', '14', '2017-06-14 21:09:30', '2017-06-14 21:09:38');
INSERT INTO `zigbee_receive_data` VALUES ('12', '17', '17', '2017-07-13 21:09:54', '2017-07-13 21:10:02');
