/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : task-manager

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-07-18 15:52:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` varchar(36) NOT NULL COMMENT '主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '配置名称',
  `value` varchar(100) DEFAULT NULL COMMENT '配置值',
  `group` tinyint(4) DEFAULT '0' COMMENT '配置分组',
  `need_auth` tinyint(4) DEFAULT '1' COMMENT '1需要登录后才能获取，0不需要登录即可获取',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'SYSTEM_NAME', 'cloud-vue通用后台登录', '0', '1');
INSERT INTO `sys_config` VALUES ('2', 'SYSTEM_LOGO', 'uploads\\20170219\\d9fe7b784e1b1f406234b7b301e627e8.png', '0', '1');
INSERT INTO `sys_config` VALUES ('3', 'LOGIN_SESSION_VALID', '1644', '0', '1');
INSERT INTO `sys_config` VALUES ('4', 'IDENTIFYING_CODE', '0', '0', '1');

-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group` (
  `id` varchar(36) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `rules` varchar(4000) DEFAULT NULL,
  `pid` varchar(36) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `status` tinyint(3) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_group
-- ----------------------------
INSERT INTO `sys_group` VALUES ('15', '普通会员', '1,2,3,4,5,6,7,8,9,10,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,59,61,62,63,28,29', '0', '最厉害的组别', '1');
INSERT INTO `sys_group` VALUES ('24', 'bbb', '10', '0', 'sss', '1');
INSERT INTO `sys_group` VALUES ('25', 'ccc', '61,62', '24', 'xxxx', '1');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(36) NOT NULL COMMENT '菜单ID',
  `pid` varchar(36) DEFAULT '0' COMMENT '上级菜单ID',
  `title` varchar(32) DEFAULT '' COMMENT '菜单名称',
  `url` varchar(127) DEFAULT '' COMMENT '链接地址',
  `icon` varchar(64) DEFAULT '' COMMENT '图标',
  `menu_type` tinyint(4) DEFAULT NULL COMMENT '菜单类型',
  `sort` tinyint(4) unsigned DEFAULT '0' COMMENT '排序（同级有效）',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态',
  `rule_id` varchar(36) DEFAULT NULL COMMENT '权限id',
  `module` varchar(50) DEFAULT NULL,
  `menu` varchar(50) DEFAULT NULL COMMENT '三级菜单吗',
  `rule_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='【配置】后台菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('52', '0', '管理', '', '', '1', '0', '1', '59', 'Administrative', '', '管理菜单');
INSERT INTO `sys_menu` VALUES ('53', '52', '系统配置', '', '', '1', '0', '1', '61', 'Administrative', '', '系统管理二级菜单');
INSERT INTO `sys_menu` VALUES ('54', '53', '菜单管理', '/home/menu/list', '', '1', '0', '1', '21', 'Administrative', 'menu', '菜单列表');
INSERT INTO `sys_menu` VALUES ('55', '53', '系统参数', '/home/config/add', '', '1', '0', '1', '29', 'Administrative', 'systemConfig', '修改系统配置');
INSERT INTO `sys_menu` VALUES ('56', '53', '权限规则', '/home/rule/list', '', '1', '0', '1', '13', 'Administrative', 'rule', '规则列表');
INSERT INTO `sys_menu` VALUES ('57', '52', '组织架构', '', '', '1', '0', '1', '63', 'Administrative', '', '组织架构二级菜单');
INSERT INTO `sys_menu` VALUES ('58', '57', '岗位管理', '/home/position/list', '', '1', '0', '1', '31', 'Administrative', 'position', '岗位列表');
INSERT INTO `sys_menu` VALUES ('59', '57', '部门管理', '/home/structures/list', '', '1', '0', '1', '39', 'Administrative', 'structures', '部门列表');
INSERT INTO `sys_menu` VALUES ('60', '57', '用户组管理', '/home/groups/list', '', '1', '0', '1', '47', 'Administrative', 'groups', '用户组列表');
INSERT INTO `sys_menu` VALUES ('61', '52', '账户管理', '', '', '1', '0', '1', '62', 'Administrative', '', '账户管理二级菜单');
INSERT INTO `sys_menu` VALUES ('62', '61', '账户列表', '/home/users/list', '', '1', '0', '1', '55', 'Administrative', 'users', '成员列表');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `id` varchar(36) NOT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '岗位名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '岗位备注',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `status` tinyint(5) DEFAULT NULL COMMENT '状态1启用,0禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('11', '文案策划', null, '2018-07-18 14:08:01', '1');
INSERT INTO `sys_post` VALUES ('12', '产品助理', null, '2018-07-18 14:08:04', '1');
INSERT INTO `sys_post` VALUES ('15', '总经理', null, '2018-07-18 14:08:07', '1');
INSERT INTO `sys_post` VALUES ('20', '项目经理', null, '2018-07-18 14:08:09', '1');
INSERT INTO `sys_post` VALUES ('25', '职能', null, '2018-07-18 14:08:12', '1');
INSERT INTO `sys_post` VALUES ('26', '项目助理', null, '2018-07-18 14:08:15', '1');
INSERT INTO `sys_post` VALUES ('27', '测试工程师', null, '2018-07-18 14:08:19', '1');
INSERT INTO `sys_post` VALUES ('28', '人事经理', null, '2018-07-18 14:08:22', '1');
INSERT INTO `sys_post` VALUES ('29', 'CEO', null, '2018-07-18 14:08:26', '1');
INSERT INTO `sys_post` VALUES ('30', '品牌策划', null, '2018-07-18 14:08:29', '1');
INSERT INTO `sys_post` VALUES ('31', '前端研发工程师', null, '2018-07-18 14:08:31', '1');
INSERT INTO `sys_post` VALUES ('32', '后端研发工程师', null, '2018-07-18 14:08:34', '1');
INSERT INTO `sys_post` VALUES ('6', '前端开发工程师', null, '2018-07-18 14:08:37', '1');
INSERT INTO `sys_post` VALUES ('7', '设计师', null, '2018-07-18 14:08:40', '1');

-- ----------------------------
-- Table structure for sys_rule
-- ----------------------------
DROP TABLE IF EXISTS `sys_rule`;
CREATE TABLE `sys_rule` (
  `id` varchar(36) NOT NULL,
  `title` varchar(100) DEFAULT '' COMMENT '名称',
  `rule_name` varchar(100) DEFAULT '' COMMENT '定义',
  `level` tinyint(5) DEFAULT NULL COMMENT '级别。1模块,2控制器,3操作',
  `pid` varchar(36) DEFAULT '0' COMMENT '父id，默认0',
  `status` tinyint(3) DEFAULT '1' COMMENT '状态，1启用，0禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_rule
-- ----------------------------
INSERT INTO `sys_rule` VALUES ('11', '权限规则', 'rules', '2', '10', '2');
INSERT INTO `sys_rule` VALUES ('13', '规则列表', 'index', '3', '11', '3');
INSERT INTO `sys_rule` VALUES ('14', '权限详情', 'read', '3', '11', '3');
INSERT INTO `sys_rule` VALUES ('15', '编辑权限', 'update', '3', '11', '3');
INSERT INTO `sys_rule` VALUES ('16', '删除权限', 'delete', '3', '11', '3');
INSERT INTO `sys_rule` VALUES ('17', '添加权限', 'save', '3', '11', '3');
INSERT INTO `sys_rule` VALUES ('18', '批量删除权限', 'deletes', '3', '11', '3');
INSERT INTO `sys_rule` VALUES ('19', '批量启用/禁用权限', 'enables', '3', '11', '3');
INSERT INTO `sys_rule` VALUES ('20', '菜单管理', 'menus', '2', '10', '2');
INSERT INTO `sys_rule` VALUES ('21', '菜单列表', 'index', '3', '20', '3');
INSERT INTO `sys_rule` VALUES ('22', '添加菜单', 'save', '3', '20', '3');
INSERT INTO `sys_rule` VALUES ('23', '菜单详情', 'read', '3', '20', '3');
INSERT INTO `sys_rule` VALUES ('24', '编辑菜单', 'update', '3', '20', '3');
INSERT INTO `sys_rule` VALUES ('25', '删除菜单', 'delete', '3', '20', '3');
INSERT INTO `sys_rule` VALUES ('26', '批量删除菜单', 'deletes', '3', '20', '3');
INSERT INTO `sys_rule` VALUES ('27', '批量启用/禁用菜单', 'enables', '3', '20', '3');
INSERT INTO `sys_rule` VALUES ('28', '系统管理', 'systemConfigs', '2', '10', '2');
INSERT INTO `sys_rule` VALUES ('29', '修改系统配置', 'save', '3', '28', '3');
INSERT INTO `sys_rule` VALUES ('30', '岗位管理', 'posts', '2', '10', '2');
INSERT INTO `sys_rule` VALUES ('31', '岗位列表', 'index', '3', '30', '3');
INSERT INTO `sys_rule` VALUES ('32', '岗位详情', 'read', '3', '30', '3');
INSERT INTO `sys_rule` VALUES ('33', '编辑岗位', 'update', '3', '30', '3');
INSERT INTO `sys_rule` VALUES ('34', '删除岗位', 'delete', '3', '30', '3');
INSERT INTO `sys_rule` VALUES ('35', '添加岗位', 'save', '3', '30', '3');
INSERT INTO `sys_rule` VALUES ('36', '批量删除岗位', 'deletes', '3', '30', '3');
INSERT INTO `sys_rule` VALUES ('37', '批量启用/禁用岗位', 'enables', '3', '30', '3');
INSERT INTO `sys_rule` VALUES ('38', '部门管理', 'structures', '2', '10', '2');
INSERT INTO `sys_rule` VALUES ('39', '部门列表', 'index', '3', '38', '3');
INSERT INTO `sys_rule` VALUES ('40', '部门详情', 'read', '3', '38', '3');
INSERT INTO `sys_rule` VALUES ('41', '编辑部门', 'update', '3', '38', '3');
INSERT INTO `sys_rule` VALUES ('42', '删除部门', 'delete', '3', '38', '3');
INSERT INTO `sys_rule` VALUES ('43', '添加部门', 'save', '3', '38', '3');
INSERT INTO `sys_rule` VALUES ('44', '批量删除部门', 'deletes', '3', '38', '3');
INSERT INTO `sys_rule` VALUES ('45', '批量启用/禁用部门', 'enables', '3', '38', '3');
INSERT INTO `sys_rule` VALUES ('46', '用户组管理', 'groups', '2', '10', '2');
INSERT INTO `sys_rule` VALUES ('47', '用户组列表', 'index', '3', '46', '3');
INSERT INTO `sys_rule` VALUES ('48', '用户组详情', 'read', '3', '46', '3');
INSERT INTO `sys_rule` VALUES ('49', '编辑用户组', 'update', '3', '46', '3');
INSERT INTO `sys_rule` VALUES ('50', '删除用户组', 'delete', '3', '46', '3');
INSERT INTO `sys_rule` VALUES ('51', '添加用户组', 'save', '3', '46', '3');
INSERT INTO `sys_rule` VALUES ('52', '批量删除用户组', 'deletes', '3', '46', '3');
INSERT INTO `sys_rule` VALUES ('53', '批量启用/禁用用户组', 'enables', '3', '46', '3');
INSERT INTO `sys_rule` VALUES ('54', '成员管理', 'users', '2', '10', '2');
INSERT INTO `sys_rule` VALUES ('55', '成员列表', 'index', '3', '54', '3');
INSERT INTO `sys_rule` VALUES ('56', '成员详情', 'read', '3', '54', '3');
INSERT INTO `sys_rule` VALUES ('57', '删除成员', 'delete', '3', '54', '3');
INSERT INTO `sys_rule` VALUES ('59', '管理菜单', 'Adminstrative', '2', '10', '2');
INSERT INTO `sys_rule` VALUES ('61', '系统管理二级菜单', 'systemConfig', '1', '59', '1');
INSERT INTO `sys_rule` VALUES ('62', '账户管理二级菜单', 'personnel', '3', '59', '3');
INSERT INTO `sys_rule` VALUES ('63', '组织架构二级菜单', 'structures', '3', '59', '3');
INSERT INTO `sys_rule` VALUES ('66', 'bb', 'bbb', '1', '59', '1');

-- ----------------------------
-- Table structure for sys_structure
-- ----------------------------
DROP TABLE IF EXISTS `sys_structure`;
CREATE TABLE `sys_structure` (
  `id` varchar(36) NOT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '组织名称',
  `pid` varchar(36) DEFAULT NULL COMMENT '父节点id',
  `status` tinyint(3) DEFAULT '1' COMMENT '组织的状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_structure
-- ----------------------------
INSERT INTO `sys_structure` VALUES ('37', '总经办', '1', '37');
INSERT INTO `sys_structure` VALUES ('5', '设计部', '1', '5');
INSERT INTO `sys_structure` VALUES ('52', '项目部', '1', '52');
INSERT INTO `sys_structure` VALUES ('53', '测试部', '1', '53');
INSERT INTO `sys_structure` VALUES ('54', '开发部', '1', '54');
INSERT INTO `sys_structure` VALUES ('55', '市场部', '1', '55');
INSERT INTO `sys_structure` VALUES ('56', '研发部', '1', '56');
INSERT INTO `sys_structure` VALUES ('57', '企业微信', '0', '57');
INSERT INTO `sys_structure` VALUES ('6', '职能部', '1', '6');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(36) NOT NULL COMMENT '用户ID',
  `user_name` varchar(255) NOT NULL COMMENT '管理后台账号',
  `password` varchar(255) NOT NULL COMMENT '管理后台密码',
  `remark` varchar(255) DEFAULT NULL COMMENT '用户备注',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `real_name` varchar(100) NOT NULL COMMENT '真实姓名',
  `structure_id` varchar(36) NOT NULL COMMENT '部门',
  `post_id` varchar(36) NOT NULL COMMENT '岗位',
  `status` tinyint(3) DEFAULT NULL COMMENT '状态,1启用0禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '', '2018-07-17 17:54:27', '超级管理员', '1', '5', '1');
INSERT INTO `sys_user` VALUES ('3', '用户AAAA', 'c78b6663d47cfbdb4d65ea51c104044e', '', '2018-07-17 17:54:48', '用户A', '5', '20', '1');
