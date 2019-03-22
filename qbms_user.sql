/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : qbms_user

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 03/22/2019 20:56:04 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `user_paper`
-- ----------------------------
DROP TABLE IF EXISTS `user_paper`;
CREATE TABLE `user_paper` (
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_bin,
  `status` int(11) NOT NULL DEFAULT '-1' COMMENT '-1=未知,1=即将开始,2=正在进行,3=完成提交.4=批改完成',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `total_time` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `paper_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '原试卷id',
  `manager_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `answer` text CHARACTER SET utf8 COLLATE utf8_bin
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_paper`
-- ----------------------------
BEGIN;
INSERT INTO `user_paper` VALUES ('022c79fd-38cb-4a59-b88f-0245966f3592', 'asdqw', '第一题:单选题(每题3分)\r\n1.123sdsd\r\n下列说法正确的有（）\n\nA． class中的constructor不可省略\n\nB． constructor必须与class同名，但方法不能与class同名\n\nC． constructor在一个对象被new时执行\n\nD．一个class只能定义一个constructor\r\n2.asss\r\n1+1=?\nA:1 B:2 C:3 D:4\r\n第二题:单选题(每题2分)\r\n1.as\r\n1+1=?\nA:1 B:2 C:3 D:4', '4', 'as', '年级:133  班级：133 姓名:王如玉', '2017-04-24 17:36:00', '60', '11', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', null), ('0b5ac8d4-6bf0-42e3-ac61-5c26873d7fc5', 'asdqw', '第一题:单选题(每题3分)\r\n1.123sdsd\r\n下列说法正确的有（）\n\nA． class中的constructor不可省略\n\nB． constructor必须与class同名，但方法不能与class同名\n\nC． constructor在一个对象被new时执行\n\nD．一个class只能定义一个constructor\r\n2.asss\r\n1+1=?\nA:1 B:2 C:3 D:4\r\n第二题:单选题(每题2分)\r\n1.as\r\n1+1=?\nA:1 B:2 C:3 D:4', '4', 'as', '年级:2013  班级：133 姓名:王如玉', '2017-04-24 15:25:00', '120', '11', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', null), ('f1a8fb40-68ed-43b8-b4f0-0ba09621f538', 'asdqw', '第一题:填空题(每题15分)\r\n1.java填空题\r\nString 是最基本的数据类型吗？', '4', '大二下java期末考试', '年级: 班级：姓名:', '2017-05-16 10:30:00', '120', '-1', '36450c59-8d48-435f-aa59-c9bac38a65ae', '1', null), ('344c7cf9-a554-47b3-9f91-93240de1fd71', 'asdqw', '第一题:单选题(每题12分)\r\n1.asss\r\n1+1=?\nA:1 B:2 C:3 D:4\r\n2.123sdsd\r\n下列说法正确的有（）\n\nA． class中的constructor不可省略\n\nB． constructor必须与class同名，但方法不能与class同名\n\nC． constructor在一个对象被new时执行\n\nD．一个class只能定义一个constructor\r\n3.123sdsd\r\n下列说法正确的有（）\n\nA． class中的constructor不可省略\n\nB． constructor必须与class同名，但方法不能与class同名\n\nC． constructor在一个对象被new时执行\n\nD．一个class只能定义一个constructor\r\n4.asss\r\n1+1=?\nA:1 B:2 C:3 D:4\r\n第二题:多选题(每题5分)\r\n1.asd\r\n最后，给大家出个思考题：下面程序的运行结果是什么？\n\npublic class Dervied extends Base {\n\n    private String name = \"dervied\";\n\n    public Dervied() {\n        tellName();\n        printName();\n    }\n    \n    public void tellName() {\n        System.out.println(\"Dervied tell name: \" + name);\n    }\n    \n    public void printName() {\n        System.out.println(\"Dervied print name: \" + name);\n    }\n\n    public static void main(String[] args){\n        \n        new Dervied();    \n    }\n}\n\nclass Base {\n    \n    private String name = \"base\";\n\n    public Base() {\n        tellName();\n        printName();\n    }\n    \n    public void tellName() {\n        System.out.println(\"Base tell name: \" + name);\n    }\n    \n    public void printName() {\n        System.out.println(\"Base print name: \" + name);\n    }\n}\r\n2.asd\r\n最后，给大家出个思考题：下面程序的运行结果是什么？\n\npublic class Dervied extends Base {\n\n    private String name = \"dervied\";\n\n    public Dervied() {\n        tellName();\n        printName();\n    }\n    \n    public void tellName() {\n        System.out.println(\"Dervied tell name: \" + name);\n    }\n    \n    public void printName() {\n        System.out.println(\"Dervied print name: \" + name);\n    }\n\n    public static void main(String[] args){\n        \n        new Dervied();    \n    }\n}\n\nclass Base {\n    \n    private String name = \"base\";\n\n    public Base() {\n        tellName();\n        printName();\n    }\n    \n    public void tellName() {\n        System.out.println(\"Base tell name: \" + name);\n    }\n    \n    public void printName() {\n        System.out.println(\"Base print name: \" + name);\n    }\n}\r\n第三题:(每题5分)', '4', '高三期末考试离散下', '年级: 班级：姓名:', '2017-05-18 10:00:00', '120', '-1', '0c0c90e5-b1d7-4c70-b35e-bfaec8b8bb88', '1', null), ('0da8bf9a-3bbe-4c26-9b5f-8952bedd97b9', 'asdqw', '第一题:单选题(每题2分)\r\n1.as\r\n1+1=?\nA:1 B:2 C:3 D:4\r\n2.123sdsd\r\n下列说法正确的有（）\n\nA． class中的constructor不可省略\n\nB． constructor必须与class同名，但方法不能与class同名\n\nC． constructor在一个对象被new时执行\n\nD．一个class只能定义一个constructor\r\n第二题:单选题(每题5分)\r\n1.asss\r\n1+1=?\nA:1 B:2 C:3 D:4', '4', '大二期末下高数', '年级: 班级：姓名:', '2017-06-12 18:20:00', '200', '99', 'd8c1c65a-d48b-44b4-819f-13de0c5d42db', '1', '一:1.A 2.B\n二:1.D');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
