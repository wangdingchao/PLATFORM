/*
 Navicat Premium Data Transfer

 Source Server         : Mu
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : localhost:3306
 Source Schema         : platform

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 13/10/2023 20:34:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for athlete
-- ----------------------------
DROP TABLE IF EXISTS `athlete`;
CREATE TABLE `athlete` (
  `aid` int NOT NULL,
  `age` int DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `id_number` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  `registration_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of athlete
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `pid` int NOT NULL AUTO_INCREMENT,
  `permissionName` varchar(50) DEFAULT NULL,
  `permissionDesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `rid` varchar(255) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `role_key` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  `is_alive` varchar(255) DEFAULT NULL,
  `creator_id` varchar(255) DEFAULT NULL,
  `last_update_by` varchar(255) DEFAULT NULL,
  `employee_num` int DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`rid`, `name`, `role_key`, `desc`, `is_alive`, `creator_id`, `last_update_by`, `employee_num`) VALUES ('1', '管理员', '管理员', '管理人员', '1', 'fslee123', 'fslee123', 0);
COMMIT;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `rid` varchar(32) DEFAULT NULL,
  `pid` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` varchar(255) NOT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `wechat_id` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `last_login_time` timestamp NULL DEFAULT NULL,
  `valid` tinyint(1) DEFAULT NULL,
  `last_change_password_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`uid`, `mobile`, `imageUrl`, `wechat_id`, `password`, `last_login_time`, `valid`, `last_change_password_time`) VALUES ('1', '15600950315', 'www.123.com', '123', '123456', '2023-10-13 16:03:07', 1, '2023-10-13 16:03:11');
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `uid` int DEFAULT NULL,
  `rid` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` (`uid`, `rid`) VALUES (1, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
