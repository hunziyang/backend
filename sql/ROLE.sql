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

 Date: 24/07/2024 17:12:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ROLE
-- ----------------------------
DROP TABLE IF EXISTS `ROLE`;
CREATE TABLE `ROLE`  (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编码',
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `IS_DELETED` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `UNIQUE_LOCK` bigint NOT NULL DEFAULT 0 COMMENT '唯一性锁',
  `REVISION` int NULL DEFAULT 1 COMMENT '乐观锁',
  `CREATED_BY` bigint NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` bigint NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `UNIQUE_CODE_UNIQUE_LOCK`(`CODE` ASC, `UNIQUE_LOCK` ASC) USING BTREE,
  INDEX `NAME_INDEX`(`NAME` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ROLE
-- ----------------------------
INSERT INTO `ROLE` VALUES (1, '超级管理员', 'ROLE_ADMIN', NULL, 0, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `ROLE` VALUES (2, '普通用户', 'ROLE_USER', NULL, 0, 0, 1, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
