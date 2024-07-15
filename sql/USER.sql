/*
 Navicat Premium Data Transfer

 Source Server         : 淖毛湖
 Source Server Type    : MySQL
 Source Server Version : 80023 (8.0.23)
 Source Host           : 172.20.1.42:3306
 Source Schema         : web-test

 Target Server Type    : MySQL
 Target Server Version : 80023 (8.0.23)
 File Encoding         : 65001

 Date: 24/07/2024 17:12:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for USER
-- ----------------------------
DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER`  (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USERNAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `PASSWORD` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `SALT` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '盐',
  `NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `GENDER` int NOT NULL DEFAULT 2 COMMENT '性别;0：男 1：女 2：未知',
  `IS_LOCK` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否锁号',
  `IS_DELETED` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `UNIQUE_LOCK` bigint NOT NULL DEFAULT 0 COMMENT '唯一性锁',
  `REVISION` int NULL DEFAULT 1 COMMENT '乐观锁',
  `CREATED_BY` bigint NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` bigint NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `UNIQUE_USERNAME_UNIQUE_LOCK`(`USERNAME` ASC, `UNIQUE_LOCK` ASC) USING BTREE,
  INDEX `NAME_INDEX`(`NAME` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of USER
-- ----------------------------
INSERT INTO `USER` VALUES (1, 'techking@techking.com', 'c94fceab24e312a9a9e3efa27c070377', '02e16828a7bf433f80a3cb8b0f0f4402', 'techking', 2, 0, 0, 0, 1, NULL, '2024-07-24 16:27:28', NULL, '2024-07-24 16:27:28');
INSERT INTO `USER` VALUES (2, 'user@techking.com', 'da9f625bd82e3b2adac4596a4f4e650d', '80824910d9284e4dbb107999eef822c2', 'user', 2, 0, 0, 0, 1, NULL, '2024-07-24 16:43:56', NULL, '2024-07-24 16:43:56');

SET FOREIGN_KEY_CHECKS = 1;
