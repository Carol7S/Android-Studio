/*
 Navicat Premium Data Transfer

 Source Server         : test1
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 23/11/2021 12:08:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Book
-- ----------------------------
DROP TABLE IF EXISTS `Book`;
CREATE TABLE `Book` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `pages` int(10) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Book
-- ----------------------------
BEGIN;
INSERT INTO `Book` VALUES (1, '史记', '司马迁', 800, 100.00);
INSERT INTO `Book` VALUES (2, '三国志', '陈寿', 600, 80.00);
INSERT INTO `Book` VALUES (5, '晋书', '房玄龄', 1200, 185.00);
INSERT INTO `Book` VALUES (6, '晋书', '房玄龄', 1200, 185.00);
INSERT INTO `Book` VALUES (7, '晋书', '房玄龄', 1200, 185.00);
INSERT INTO `Book` VALUES (8, 'name', 'author', 1234, 152.40);
INSERT INTO `Book` VALUES (9, '新唐书', '欧阳修', 1234, 152.40);
INSERT INTO `Book` VALUES (10, '新唐书', '欧阳修', 1234, 152.40);
INSERT INTO `Book` VALUES (11, '新唐书', '欧阳修', 1234, 152.40);
INSERT INTO `Book` VALUES (12, '晋书', '房玄龄', 1200, 185.00);
COMMIT;

-- ----------------------------
-- Table structure for Fund
-- ----------------------------
DROP TABLE IF EXISTS `Fund`;
CREATE TABLE `Fund` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `DailyIncrease` varchar(255) DEFAULT NULL,
  `WeeklyIncrease` varchar(255) DEFAULT NULL,
  `MonthlyIncrease` varchar(255) DEFAULT NULL,
  `YearlyIncrease` varchar(255) DEFAULT NULL,
  `holdNum` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Fund
-- ----------------------------
BEGIN;
INSERT INTO `Fund` VALUES (1, '中欧盛世成长混合', '3.0316', '5.87亿', '9年237天', '2.29%', '3.46%', '18.35%', '14.41%', 200);
INSERT INTO `Fund` VALUES (5, '金信民长混合A', '2.3682', '1.00亿', '1年184天', '0.09%', '3.50%', '18.30%', '104.65%', 100);
INSERT INTO `Fund` VALUES (6, '中欧永裕混合A', '2.1550', '5.11亿', '6年170天', '2.28%', '3.44%', '18.27%', '13.50%', 300);
COMMIT;

-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of User
-- ----------------------------
BEGIN;
INSERT INTO `User` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin');
INSERT INTO `User` VALUES (2, 'carol', 'e10adc3949ba59abbe56e057f20f883e', 'user');
INSERT INTO `User` VALUES (3, 'alice', '93279e3308bdbbeed946fc965017f67a', 'user');
COMMIT;

-- ----------------------------
-- Table structure for UserInfo
-- ----------------------------
DROP TABLE IF EXISTS `UserInfo`;
CREATE TABLE `UserInfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `money` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of UserInfo
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sds_user
-- ----------------------------
DROP TABLE IF EXISTS `sds_user`;
CREATE TABLE `sds_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sds_username` varchar(255) DEFAULT NULL,
  `sds_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sds_user
-- ----------------------------
BEGIN;
INSERT INTO `sds_user` VALUES (1, 'admin', '123456');
INSERT INTO `sds_user` VALUES (2, 'carol', 'root');
INSERT INTO `sds_user` VALUES (8, 'dorothy', '123');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
