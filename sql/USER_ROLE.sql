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

 Date: 24/07/2024 17:12:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for USER_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `USER_ROLE`;
CREATE TABLE `USER_ROLE`  (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_ID` bigint NOT NULL COMMENT '用户ID',
  `ROLE_ID` bigint NOT NULL COMMENT '角色ID',
  `IS_DELETED` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `UNIQUE_LOCK` bigint NOT NULL DEFAULT 0 COMMENT '唯一性锁',
  `REVISION` int NULL DEFAULT 1 COMMENT '乐观锁',
  `CREATED_BY` bigint NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` bigint NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `UNIQUE_USER_ID_ROLE_ID_UNIQUE_LOCK`(`USER_ID` ASC, `ROLE_ID` ASC, `UNIQUE_LOCK` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of USER_ROLE
-- ----------------------------
INSERT INTO `USER_ROLE` VALUES (1, 1, 1, 0, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `USER_ROLE` VALUES (2, 2, 2, 0, 0, 1, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
