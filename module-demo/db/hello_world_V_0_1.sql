
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hello_world
-- ----------------------------
DROP TABLE IF EXISTS `hello_world`;
CREATE TABLE `hello_world` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone_num` varchar(255) DEFAULT NULL,
  `type` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hello_world
-- ----------------------------
INSERT INTO `hello_world` VALUES ('1', '哈哈1', '15889994809', '0');
INSERT INTO `hello_world` VALUES ('2', '姓名', '13900138000', '0');
INSERT INTO `hello_world` VALUES ('3', '测试', '13800138000', '0');
INSERT INTO `hello_world` VALUES ('4', '测试', '13800138000', '0');
INSERT INTO `hello_world` VALUES ('5', '测试', '13800138000', '0');
INSERT INTO `hello_world` VALUES ('6', '测试', '13800138000', '0');
INSERT INTO `hello_world` VALUES ('7', '测试', '13800138000', '0');
INSERT INTO `hello_world` VALUES ('8', '测试', '13800138000', '0');
INSERT INTO `hello_world` VALUES ('9', '测试', '13800138000', '0');
INSERT INTO `hello_world` VALUES ('10', '测试', '13800138000', '0');
INSERT INTO `hello_world` VALUES ('11', '姓名', '13800138000', '0');
INSERT INTO `hello_world` VALUES ('12', '123123', '15880004808', '0');
INSERT INTO `hello_world` VALUES ('13', '123123', '15880004808', '0');
INSERT INTO `hello_world` VALUES ('14', '123123', '15880004808', '0');
