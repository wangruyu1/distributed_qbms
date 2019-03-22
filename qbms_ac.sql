/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : qbms_ac

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 03/22/2019 20:56:46 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `icon` varchar(255) NOT NULL,
  `router` varchar(255) DEFAULT NULL,
  `children` varchar(255) DEFAULT NULL,
  `parentId` varchar(255) DEFAULT NULL,
  `order` int(11) NOT NULL DEFAULT '0' COMMENT '值越小菜单位置越往下'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `menu`
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES ('1', '试题管理', 'fa fa-cog', '', '4,5', null, '0'), ('10', '难度等级管理', 'fa  fa-sun-o', '', '3', null, '0'), ('11', '试卷管理', 'fa fa-map', null, '2,7,8,9', null, '0'), ('12', '试卷管理', 'fa fa-map', null, '13,14', null, '0'), ('13', '试卷历史', 'fa fa-history', 'userpaper/history', null, '12', '0'), ('14', '即将开始', 'fa fa-hourglass-1', 'userpaper/toStart', null, '12', '0'), ('15', '考试进行中', 'fa fa-hourglass-2', 'userpaper/starting', null, '12', '0'), ('16', '批改试卷', 'fa  fa-check-square-o', 'userpaper/revise', null, '11', '0'), ('2', '试卷分类', 'fa fa-building-o', 'category', null, '11', '0'), ('3', '难度等级', 'fa fa-balance-scale', 'subjectDifficulty', null, '10', '0'), ('4', '试题分类', 'glyphicon glyphicon-th', 'subjectCategory', null, '1', '0'), ('5', '试题', 'fa fa-book', 'subject', null, '1', '0'), ('6', '屏幕锁定', 'fa fa-lock', 'lockScreen', null, null, '0'), ('7', '试卷', 'fa fa-map-o', 'paper', null, '11', '0'), ('8', '手动添加试卷', 'fa fa-hand-paper-o', 'paper/manuals', null, '11', '0'), ('9', '自动生成试卷', 'fa fa-stack-overflow', 'paper/automation', null, '11', '0');
COMMIT;

-- ----------------------------
--  Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `role`
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES ('ROLE_ADMIN', '管理员', '管理所有权限'), ('ROLE_MANAGER', '出题人', '出题和相关用户管理'), ('ROLE_USER', '普通用户', '进行试卷考试和个人信息管理');
COMMIT;

-- ----------------------------
--  Table structure for `role_menu_map`
-- ----------------------------
DROP TABLE IF EXISTS `role_menu_map`;
CREATE TABLE `role_menu_map` (
  `id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  `menu_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `role_menu_map`
-- ----------------------------
BEGIN;
INSERT INTO `role_menu_map` VALUES ('1', 'ROLE_ADMIN', '1'), ('10', 'ROLE_MANAGER', '5'), ('12', 'ROLE_MANAGER', '7'), ('13', 'ROLE_MANAGER', '8'), ('14', 'ROLE_MANAGER', '9'), ('15', 'ROLE_MANAGER', '10'), ('16', 'ROLE_MANAGER', '11'), ('17', 'ROLE_USER', '12'), ('18', 'ROLE_USER', '13'), ('19', 'ROLE_USER', '14'), ('2', 'ROLE_MANAGER', '1'), ('20', 'ROLE_USER', '15'), ('21', 'ROLE_MANAGER', '16'), ('3', 'ROLE_MANAGER', '2'), ('4', 'ROLE_ADMIN', '2'), ('5', 'ROLE_ADMIN', '3'), ('6', 'ROLE_MANAGER', '3'), ('7', 'ROLE_ADMIN', '4'), ('8', 'ROLE_MANAGER', '4');
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `name` varchar(32) NOT NULL,
  `display_name` varchar(32) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '1=可用,2=锁定,3=删除',
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT '-1' COMMENT '1=出题人,2=考试者,3=管理员'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'test', 'test_display_name', '123123', '1', null, null, '1'), ('1wqe', 'wangruyu', '管理员', '123123', '1', null, null, '3'), ('asdqw', 'user', '用户1', '123123', '1', null, null, '2');
COMMIT;

-- ----------------------------
--  Table structure for `user_role_map`
-- ----------------------------
DROP TABLE IF EXISTS `user_role_map`;
CREATE TABLE `user_role_map` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_role_map`
-- ----------------------------
BEGIN;
INSERT INTO `user_role_map` VALUES ('asdas12', '1wqe', 'ROLE_ADMIN'), ('qwasd12', '1', 'ROLE_MANAGER'), ('qweadasd', 'asdqw', 'ROLE_USER');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
