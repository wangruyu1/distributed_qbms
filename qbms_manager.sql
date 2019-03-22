/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : qbms_manager

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 03/22/2019 20:56:23 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `make_paper`
-- ----------------------------
DROP TABLE IF EXISTS `make_paper`;
CREATE TABLE `make_paper` (
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `paper_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `total_time` int(11) DEFAULT NULL COMMENT '单位:分钟',
  `start_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `make_paper`
-- ----------------------------
BEGIN;
INSERT INTO `make_paper` VALUES ('011a7e4f-05c5-46b9-9d16-c329972b2897', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-21 21:13:56', '120', '2017-04-04 05:25:00'), ('239eff36-439a-4498-94d6-c674df36721e', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-24 16:17:39', '200', '2017-04-24 16:20:00'), ('2767c261-a4a5-46c6-bfc3-ac5fbaf96a95', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-23 16:56:05', '22', '2017-04-05 02:10:00'), ('2e2fa850-2fed-47bc-bf5e-054965cdff72', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-21 22:20:20', '13', '2017-04-05 06:25:00'), ('3625ed6c-6e86-44b1-887d-e0eec4c52c1f', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-21 14:56:57', '12', '2017-03-28 05:25:00'), ('39602247-4bba-4314-bfd2-64fe7db2bbbc', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-21 21:08:49', '120', '2017-04-04 05:25:00'), ('5220b0ee-dff8-45f8-abde-9d6adb831c91', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-21 21:52:36', '12', '2017-04-05 05:25:00'), ('558645f0-85f9-45ee-bd74-68e0deece1a9', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-23 16:15:55', '6', '2017-04-04 05:10:00'), ('5c6b88bd-f3e3-4413-87ba-78da2cbdd028', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-22 12:31:53', '12', '2017-03-29 05:25:00'), ('6413781b-b54c-4f61-8f97-6d764c238dd5', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-21 21:21:51', '120', '2017-04-04 01:25:00'), ('710baac5-ec18-448d-a12f-d3ca9540b5d4', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-23 17:03:06', '12', '2017-04-12 02:10:00'), ('7498421c-a124-4132-960f-3046405a2f3f', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-23 16:22:28', '22', '2017-04-05 02:10:00'), ('8e408c9d-f374-49c5-84a8-d0276c6423b1', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-21 22:29:22', '12', '2017-04-05 01:05:00'), ('985d63c1-e345-4035-bc6b-9ae738d29988', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-22 14:12:18', '120', '2017-04-04 00:05:00'), ('9959bf14-1c4e-408e-9c89-685c9727d6ed', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-21 22:27:35', '120', '2017-04-05 01:05:00'), ('a7c133c8-76da-4db5-b0d0-b92187277eee', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-23 16:19:12', '6', '2017-04-04 05:10:00'), ('bfcf3555-cad9-4269-ab91-91e21d0cac0c', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-23 16:51:34', '22', '2017-04-05 02:10:00'), ('cb50838a-bce8-4bf5-ad5c-b4895d2107eb', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-22 14:08:31', '333', '2017-04-04 01:05:00'), ('ce7f32f3-33dc-4147-9931-c7fb9f61ad15', 'cecc14e0-e4c6-4528-9b88-9f06dbf49081', '1', '2017-04-23 15:55:07', '12', '2017-04-05 05:05:00'), ('d98ae41c-3789-43b9-ac3d-4a407bc98bd6', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-23 15:54:10', '12', '2017-04-04 01:05:00'), ('e6494319-b74d-41c2-99a9-a3520f466e9a', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-23 16:51:17', '22', '2017-04-05 02:10:00'), ('e650f4dd-498a-4828-9b1c-679d7d798782', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-24 15:22:46', '120', '2017-04-24 15:25:00'), ('eb070229-93d6-4efb-87a2-edba15d4641b', 'cecc14e0-e4c6-4528-9b88-9f06dbf49081', '1', '2017-04-19 16:41:01', '12', '2017-04-04 09:25:00'), ('ee55af85-91f1-4b68-b0f7-4ccdcd8c7a4c', 'a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '2017-04-21 22:18:37', '120', '2017-03-28 05:05:00'), ('d7cc162d-532a-477d-a2de-96b7a44ea4f8', '36450c59-8d48-435f-aa59-c9bac38a65ae', '1', '2017-05-15 13:10:10', '120', '2017-05-16 10:30:00'), ('59b3545c-f46f-4d3a-946c-7eb6db218062', '0c0c90e5-b1d7-4c70-b35e-bfaec8b8bb88', '1', '2017-05-17 16:45:31', '120', '2017-05-18 10:00:00'), ('4b0eb856-d805-4ecf-9ba6-2843d48b0a6a', 'd8c1c65a-d48b-44b4-819f-13de0c5d42db', '1', '2017-06-12 18:08:47', '200', '2017-06-12 18:20:00');
COMMIT;

-- ----------------------------
--  Table structure for `manager_user_map`
-- ----------------------------
DROP TABLE IF EXISTS `manager_user_map`;
CREATE TABLE `manager_user_map` (
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `manager_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `manager_user_map`
-- ----------------------------
BEGIN;
INSERT INTO `manager_user_map` VALUES ('asdasd', '1', 'asdqw');
COMMIT;

-- ----------------------------
--  Table structure for `paper`
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `paper_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `paper_category_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `paper_difficulty_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '保存subject_id,小题用逗号隔开,大题用|隔开.',
  `create_time` datetime DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `paper`
-- ----------------------------
BEGIN;
INSERT INTO `paper` VALUES ('0c0c90e5-b1d7-4c70-b35e-bfaec8b8bb88', '1', '7b973dda-dbff-4c15-adf2-228911173ee7', '52c860c0-c436-415c-80f2-e1b926140bec', 'null|12#50e29c95-95ba-4397-a645-da5a69fc414d,5d10da4e-f71e-4826-8f2b-4be16ae9ca57,60ee21de-16e5-4ddd-a022-64968c394d0c,81a476ff-ed93-4fdf-a5d4-b6d5e4ef13e5|5#40ef83af-c476-4fbd-82c1-5cec3d6f53d8,dac476db-2511-4fd7-b023-868222385b0e|5#30cb4d2d-7ea3-4609-9b98-ad8784876d0d,6f070a57-559b-480e-838f-b419be037d0b', '2017-04-28 15:46:30', '年级: 班级：姓名:', '高三期末考试离散下'), ('36450c59-8d48-435f-aa59-c9bac38a65ae', '1', '08f0d2fe-d5d0-4694-9076-4585c1e4523e', '52c860c0-c436-415c-80f2-e1b926140bec', '|15#ce2e1ca3-1ab2-457e-a0c1-2a3f0db78702', '2017-05-04 14:57:42', '年级: 班级：姓名:', '大二下java期末考试'), ('a6d043c8-6034-4cb2-b0e4-5749b43ff363', '1', '7b973dda-dbff-4c15-adf2-228911173ee7', 'eb2c28ad-c9ea-4899-a021-840e1d0959b1', 'null|3#5ae61710-1c31-4af5-9d16-33c4cd8f932d,5d10da4e-f71e-4826-8f2b-4be16ae9ca57,81a476ff-ed93-4fdf-a5d4-b6d5e4ef13e5|2#9af7037a-d975-49eb-bcbd-20f9f91c915b', '2017-04-05 15:43:31', '年级:133  班级：133 姓名:王如玉', 'as'), ('a9cc34aa-09a0-48b2-b512-7769054cf12e', '1', 'a04e0836-0332-4ed9-a728-3792b440672b', '52c860c0-c436-415c-80f2-e1b926140bec', 'null|22#1c1c996e-1136-425f-82b9-91a01dc5f13c,ce2e1ca3-1ab2-457e-a0c1-2a3f0db78702', '2017-05-04 14:59:40', '年级: 班级：姓名:', '大三java期末'), ('cecc14e0-e4c6-4528-9b88-9f06dbf49081', '1', '08f0d2fe-d5d0-4694-9076-4585c1e4523e', 'eb2c28ad-c9ea-4899-a021-840e1d0959b1', 'null|3#5ae61710-1c31-4af5-9d16-33c4cd8f932d,5d10da4e-f71e-4826-8f2b-4be16ae9ca57,81a476ff-ed93-4fdf-a5d4-b6d5e4ef13e5,e8b391a8-3e13-4c24-8e2f-81dac6f46ee3', '2017-04-05 15:35:55', '年级:133  班级：133 姓名:王如玉', 'as'), ('a98225bb-7389-4e48-bc27-86ff44a63e7f', '1', '08f0d2fe-d5d0-4694-9076-4585c1e4523e', '52c860c0-c436-415c-80f2-e1b926140bec', '|2#b83f6382-06d1-4e03-9946-802c8aeaf4ac,49d985ed-491b-464d-90cb-b0f934f25ec7|3#0056427b-4ed7-4723-b952-03ecf1341039,69abfa4b-add0-44e8-9446-f543c91b3085', '2017-05-17 16:42:59', '年级: 班级：姓名:', '大二下离散数学'), ('31f3ec91-c670-4670-bd47-eac6a0630763', '1', '08f0d2fe-d5d0-4694-9076-4585c1e4523e', '52c860c0-c436-415c-80f2-e1b926140bec', null, '2017-06-01 10:58:10', '年级: 班级：姓名:', 'asasd'), ('f668fd48-0591-4b45-bbdb-714b1a28870c', '1', '08f0d2fe-d5d0-4694-9076-4585c1e4523e', '52c860c0-c436-415c-80f2-e1b926140bec', 'null|2#bbc81b70-e950-487a-bcf7-a0b61a803370,b336468f-2a51-4776-866a-89b40e84022f|2#1c1c996e-1136-425f-82b9-91a01dc5f13c', '2017-06-01 11:01:06', '年级: 班级：姓名:', 'AS'), ('d8c1c65a-d48b-44b4-819f-13de0c5d42db', '1', 'da00f007-0bec-408b-9876-bcb7842ebd17', '52c860c0-c436-415c-80f2-e1b926140bec', '|2#99b5f1e8-e438-4f19-a0c2-4aeb146ae765,c3e66cb6-57c6-4fe8-af48-0c51b9bbb691|5#29ca7643-b29f-46d2-8ec8-bfbc69bd83a3', '2017-06-12 18:06:28', '年级: 班级：姓名:', '大二期末下高数');
COMMIT;

-- ----------------------------
--  Table structure for `paper_category`
-- ----------------------------
DROP TABLE IF EXISTS `paper_category`;
CREATE TABLE `paper_category` (
  `category_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '分类名称',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `paper_category`
-- ----------------------------
BEGIN;
INSERT INTO `paper_category` VALUES ('08f0d2fe-d5d0-4694-9076-4585c1e4523e', null, 'java并发与多线程', '胶较难'), ('7b973dda-dbff-4c15-adf2-228911173ee7', null, 'c#', 'c#试题w'), ('a04e0836-0332-4ed9-a728-3792b440672b', null, 'java', 'java试题'), ('f655f648-46fe-48b4-930b-d224bb1980e2', null, '数据结构', 'hahahaha'), ('da00f007-0bec-408b-9876-bcb7842ebd17', null, '网络基础', 'network');
COMMIT;

-- ----------------------------
--  Table structure for `subject`
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `subject_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `subject_category_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_bin,
  `answer` text CHARACTER SET utf8 COLLATE utf8_bin,
  `user_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `subject_difficulty_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `subject`
-- ----------------------------
BEGIN;
INSERT INTO `subject` VALUES ('0425afe3-9f48-4caa-958a-cbc23dd3e8eb', 'as', null, '320f72b3-9382-4d0f-b7ab-509e8e5ddec8', '1+1=?\nA:1 B:2 C:3 D:4', 'B', '1', 'ff12f723-77c4-4817-8eec-1ceb1dd249b6'), ('1c1c996e-1136-425f-82b9-91a01dc5f13c', 'java填空题', null, 'b6286497-15d2-4467-a061-1ff4442e5c9f', 'String 是最基本的数据类型吗？', '不是。Java中的基本数据类型只有8个：byte、short、int、long、float、double、char、boolean；除了基本类型（primitive type）和枚举类型（enumeration type），剩下的都是引用类型（reference type）。', '1', '52c860c0-c436-415c-80f2-e1b926140bec'), ('40ef83af-c476-4fbd-82c1-5cec3d6f53d8', 'asd', null, '618e8ca1-aca5-431d-8e2f-c74aa9ce7757', '最后，给大家出个思考题：下面程序的运行结果是什么？\n\npublic class Dervied extends Base {\n\n    private String name = \"dervied\";\n\n    public Dervied() {\n        tellName();\n        printName();\n    }\n    \n    public void tellName() {\n        System.out.println(\"Dervied tell name: \" + name);\n    }\n    \n    public void printName() {\n        System.out.println(\"Dervied print name: \" + name);\n    }\n\n    public static void main(String[] args){\n        \n        new Dervied();    \n    }\n}\n\nclass Base {\n    \n    private String name = \"base\";\n\n    public Base() {\n        tellName();\n        printName();\n    }\n    \n    public void tellName() {\n        System.out.println(\"Base tell name: \" + name);\n    }\n    \n    public void printName() {\n        System.out.println(\"Base print name: \" + name);\n    }\n}', 'CS', '1', 'eb2c28ad-c9ea-4899-a021-840e1d0959b1'), ('50e29c95-95ba-4397-a645-da5a69fc414d', 'asss', null, '320f72b3-9382-4d0f-b7ab-509e8e5ddec8', '1+1=?\nA:1 B:2 C:3 D:4', 'B', '1', 'eb2c28ad-c9ea-4899-a021-840e1d0959b1'), ('5d10da4e-f71e-4826-8f2b-4be16ae9ca57', '123sdsd', null, '320f72b3-9382-4d0f-b7ab-509e8e5ddec8', '下列说法正确的有（）\n\nA． class中的constructor不可省略\n\nB． constructor必须与class同名，但方法不能与class同名\n\nC． constructor在一个对象被new时执行\n\nD．一个class只能定义一个constructor', 'AC', '1', 'eb2c28ad-c9ea-4899-a021-840e1d0959b1'), ('60ee21de-16e5-4ddd-a022-64968c394d0c', '123sdsd', null, '320f72b3-9382-4d0f-b7ab-509e8e5ddec8', '下列说法正确的有（）\n\nA． class中的constructor不可省略\n\nB． constructor必须与class同名，但方法不能与class同名\n\nC． constructor在一个对象被new时执行\n\nD．一个class只能定义一个constructor', 'AC', '1', 'eb2c28ad-c9ea-4899-a021-840e1d0959b1'), ('81a476ff-ed93-4fdf-a5d4-b6d5e4ef13e5', 'asss', null, '320f72b3-9382-4d0f-b7ab-509e8e5ddec8', '1+1=?\nA:1 B:2 C:3 D:4', 'B', '1', 'eb2c28ad-c9ea-4899-a021-840e1d0959b1'), ('9af7037a-d975-49eb-bcbd-20f9f91c915b', 'as', null, '320f72b3-9382-4d0f-b7ab-509e8e5ddec8', '1+1=?\nA:1 B:2 C:3 D:4', 'B', '1', 'ff12f723-77c4-4817-8eec-1ceb1dd249b6'), ('a5e43c6e-f4c6-4d34-b22c-938d31db3f8b', 'asss', null, '320f72b3-9382-4d0f-b7ab-509e8e5ddec8', '1+1=?\nA:1 B:2 C:3 D:4', 'B', '1', 'eb2c28ad-c9ea-4899-a021-840e1d0959b1'), ('bbc81b70-e950-487a-bcf7-a0b61a803370', '阿萨德', null, '320f72b3-9382-4d0f-b7ab-509e8e5ddec8', '奥术大师多', 's大声道', '1', '52c860c0-c436-415c-80f2-e1b926140bec'), ('ce2e1ca3-1ab2-457e-a0c1-2a3f0db78702', 'java填空题', null, 'b6286497-15d2-4467-a061-1ff4442e5c9f', 'String 是最基本的数据类型吗？', '不是。Java中的基本数据类型只有8个：byte、short、int、long、float、double、char、boolean；除了基本类型（primitive type）和枚举类型（enumeration type），剩下的都是引用类型（reference type）。', '1', '52c860c0-c436-415c-80f2-e1b926140bec'), ('dac476db-2511-4fd7-b023-868222385b0e', 'asd', null, '618e8ca1-aca5-431d-8e2f-c74aa9ce7757', '最后，给大家出个思考题：下面程序的运行结果是什么？\n\npublic class Dervied extends Base {\n\n    private String name = \"dervied\";\n\n    public Dervied() {\n        tellName();\n        printName();\n    }\n    \n    public void tellName() {\n        System.out.println(\"Dervied tell name: \" + name);\n    }\n    \n    public void printName() {\n        System.out.println(\"Dervied print name: \" + name);\n    }\n\n    public static void main(String[] args){\n        \n        new Dervied();    \n    }\n}\n\nclass Base {\n    \n    private String name = \"base\";\n\n    public Base() {\n        tellName();\n        printName();\n    }\n    \n    public void tellName() {\n        System.out.println(\"Base tell name: \" + name);\n    }\n    \n    public void printName() {\n        System.out.println(\"Base print name: \" + name);\n    }\n}', 'CS', '1', 'eb2c28ad-c9ea-4899-a021-840e1d0959b1'), ('de956d6c-4e52-4751-b032-3edf50143a1b', 'dssd', null, '618e8ca1-aca5-431d-8e2f-c74aa9ce7757', '12+23=?\nA:22 B:35 C:12 D:12', 'C', '1', 'ff12f723-77c4-4817-8eec-1ceb1dd249b6'), ('e6338655-439a-4f10-ba35-2aa6ab398007', 'as', null, '618e8ca1-aca5-431d-8e2f-c74aa9ce7757', '1+1=?\nA:1 B:2 C:3 D:4', 'B', '1', 'ff12f723-77c4-4817-8eec-1ceb1dd249b6'), ('b83f6382-06d1-4e03-9946-802c8aeaf4ac', 'java填空题', null, 'b6286497-15d2-4467-a061-1ff4442e5c9f', 'String 是最基本的数据类型吗？', '不是。Java中的基本数据类型只有8个：byte、short、int、long、float、double、char、boolean；除了基本类型（primitive type）和枚举类型（enumeration type），剩下的都是引用类型（reference type）。', '1', '52c860c0-c436-415c-80f2-e1b926140bec'), ('49d985ed-491b-464d-90cb-b0f934f25ec7', 'java填空题', null, 'b6286497-15d2-4467-a061-1ff4442e5c9f', 'String 是最基本的数据类型吗？', '不是。Java中的基本数据类型只有8个：byte、short、int、long、float、double、char、boolean；除了基本类型（primitive type）和枚举类型（enumeration type），剩下的都是引用类型（reference type）。', '1', '52c860c0-c436-415c-80f2-e1b926140bec'), ('0056427b-4ed7-4723-b952-03ecf1341039', 'asd', null, '618e8ca1-aca5-431d-8e2f-c74aa9ce7757', '最后，给大家出个思考题：下面程序的运行结果是什么？\n\npublic class Dervied extends Base {\n\n    private String name = \"dervied\";\n\n    public Dervied() {\n        tellName();\n        printName();\n    }\n    \n    public void tellName() {\n        System.out.println(\"Dervied tell name: \" + name);\n    }\n    \n    public void printName() {\n        System.out.println(\"Dervied print name: \" + name);\n    }\n\n    public static void main(String[] args){\n        \n        new Dervied();    \n    }\n}\n\nclass Base {\n    \n    private String name = \"base\";\n\n    public Base() {\n        tellName();\n        printName();\n    }\n    \n    public void tellName() {\n        System.out.println(\"Base tell name: \" + name);\n    }\n    \n    public void printName() {\n        System.out.println(\"Base print name: \" + name);\n    }\n}', 'CS', '1', 'eb2c28ad-c9ea-4899-a021-840e1d0959b1'), ('69abfa4b-add0-44e8-9446-f543c91b3085', 'asd', null, '618e8ca1-aca5-431d-8e2f-c74aa9ce7757', '最后，给大家出个思考题：下面程序的运行结果是什么？\n\npublic class Dervied extends Base {\n\n    private String name = \"dervied\";\n\n    public Dervied() {\n        tellName();\n        printName();\n    }\n    \n    public void tellName() {\n        System.out.println(\"Dervied tell name: \" + name);\n    }\n    \n    public void printName() {\n        System.out.println(\"Dervied print name: \" + name);\n    }\n\n    public static void main(String[] args){\n        \n        new Dervied();    \n    }\n}\n\nclass Base {\n    \n    private String name = \"base\";\n\n    public Base() {\n        tellName();\n        printName();\n    }\n    \n    public void tellName() {\n        System.out.println(\"Base tell name: \" + name);\n    }\n    \n    public void printName() {\n        System.out.println(\"Base print name: \" + name);\n    }\n}', 'CS', '1', 'eb2c28ad-c9ea-4899-a021-840e1d0959b1'), ('b336468f-2a51-4776-866a-89b40e84022f', 'wqqwe', null, '320f72b3-9382-4d0f-b7ab-509e8e5ddec8', 'afasdsad', 'as', '1', '52c860c0-c436-415c-80f2-e1b926140bec'), ('99b5f1e8-e438-4f19-a0c2-4aeb146ae765', 'as', null, '320f72b3-9382-4d0f-b7ab-509e8e5ddec8', '1+1=?\nA:1 B:2 C:3 D:4', 'B', '1', 'ff12f723-77c4-4817-8eec-1ceb1dd249b6'), ('c3e66cb6-57c6-4fe8-af48-0c51b9bbb691', '123sdsd', null, '320f72b3-9382-4d0f-b7ab-509e8e5ddec8', '下列说法正确的有（）\n\nA． class中的constructor不可省略\n\nB． constructor必须与class同名，但方法不能与class同名\n\nC． constructor在一个对象被new时执行\n\nD．一个class只能定义一个constructor', 'AC', '1', 'eb2c28ad-c9ea-4899-a021-840e1d0959b1'), ('29ca7643-b29f-46d2-8ec8-bfbc69bd83a3', 'asss', null, '320f72b3-9382-4d0f-b7ab-509e8e5ddec8', '1+1=?\nA:1 B:2 C:3 D:4', 'B', '1', 'eb2c28ad-c9ea-4899-a021-840e1d0959b1');
COMMIT;

-- ----------------------------
--  Table structure for `subject_category`
-- ----------------------------
DROP TABLE IF EXISTS `subject_category`;
CREATE TABLE `subject_category` (
  `subject_category_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '类型',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '题目类型名称',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `subject_category`
-- ----------------------------
BEGIN;
INSERT INTO `subject_category` VALUES ('320f72b3-9382-4d0f-b7ab-509e8e5ddec8', '1', '单选题', '一个选择答案.a'), ('618e8ca1-aca5-431d-8e2f-c74aa9ce7757', '2', '多选题', '多个选择答案.'), ('b6286497-15d2-4467-a061-1ff4442e5c9f', '5', '填空题', '填空'), ('b8e420fc-17be-4f7e-96a7-d331c9b6f35d', '4', '大题', '很大的题');
COMMIT;

-- ----------------------------
--  Table structure for `subject_difficulty`
-- ----------------------------
DROP TABLE IF EXISTS `subject_difficulty`;
CREATE TABLE `subject_difficulty` (
  `subject_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `type` int(11) DEFAULT '0',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `subject_difficulty`
-- ----------------------------
BEGIN;
INSERT INTO `subject_difficulty` VALUES ('52c860c0-c436-415c-80f2-e1b926140bec', '4', '专家难度', '很难很难.a'), ('eb2c28ad-c9ea-4899-a021-840e1d0959b1', '2', '一般', '不算难，还是难'), ('ff12f723-77c4-4817-8eec-1ceb1dd249b6', '3', '容易', '很简单.'), ('489cc359-2182-4cc3-b6df-e22fd4cd7b63', null, 'easy', '简单.');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
