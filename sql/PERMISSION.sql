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

 Date: 24/07/2024 17:11:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for PERMISSION
-- ----------------------------
DROP TABLE IF EXISTS `PERMISSION`;
CREATE TABLE `PERMISSION`  (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
  `CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `TYPE` int NOT NULL COMMENT '权限类型;0：目录 1：菜单 2：按钮',
  `PARENT_ID` bigint NOT NULL COMMENT '父节点',
  `ORDER` int NOT NULL COMMENT '排序',
  `ICON` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图像',
  `PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路径',
  `COMPONENT` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件',
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `IS_DELETED` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `UNIQUE_LOCK` bigint NOT NULL DEFAULT 0 COMMENT '唯一性锁',
  `REVISION` int NULL DEFAULT 1 COMMENT '乐观锁',
  `CREATED_BY` bigint NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` bigint NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `UNIQUE_CODE_UNIQUE_LOCK`(`CODE` ASC, `UNIQUE_LOCK` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of PERMISSION
-- ----------------------------
INSERT INTO `PERMISSION` VALUES (1, '系统管理', NULL, 0, 0, 999, NULL, NULL, NULL, NULL, 0, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `PERMISSION` VALUES (2, '用户管理', 'user:select', 1, 1, 1, NULL, NULL, NULL, NULL, 0, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `PERMISSION` VALUES (3, '更新用户锁状态', 'user:updateLock', 2, 2, 1, NULL, NULL, NULL, NULL, 0, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `PERMISSION` VALUES (5, '用户删除', 'user:delete', 2, 2, 1, NULL, NULL, NULL, NULL, 0, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `PERMISSION` VALUES (6, '重置密码', 'user:resetPassword', 2, 2, 1, NULL, NULL, NULL, NULL, 0, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `PERMISSION` VALUES (7, '用户详情', 'user:detail', 2, 2, 1, NULL, NULL, NULL, NULL, 0, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `PERMISSION` VALUES (8, '更新用户信息', 'user:updateDetailIncludeRoles', 2, 2, 1, NULL, NULL, NULL, NULL, 0, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `PERMISSION` VALUES (9, '角色管理', 'role:select', 1, 1, 2, NULL, NULL, NULL, NULL, 0, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `PERMISSION` VALUES (10, '角色创建', 'role:insert', 2, 9, 1, NULL, NULL, NULL, NULL, 0, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `PERMISSION` VALUES (11, '角色更新', 'role:update', 2, 9, 1, NULL, NULL, NULL, NULL, 0, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `PERMISSION` VALUES (12, '角色删除', 'role:delete', 2, 9, 1, NULL, NULL, NULL, NULL, 0, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `PERMISSION` VALUES (13, '角色详情', 'role:detail', 2, 9, 1, NULL, NULL, NULL, NULL, 0, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `PERMISSION` VALUES (14, '全部角色', 'role:selectAll', 2, 9, 1, NULL, NULL, NULL, NULL, 0, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `PERMISSION` VALUES (15, '权限管理', 'permission:select', 1, 1, 3, NULL, NULL, NULL, NULL, 0, 0, 1, NULL, NULL, NULL, NULL);
INSERT INTO `PERMISSION` VALUES (16, '权限树', 'permission:tree', 2, 15, 1, NULL, NULL, NULL, NULL, 0, 0, 1, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
