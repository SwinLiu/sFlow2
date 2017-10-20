DROP TABLE IF EXISTS `sf_sys_menu`;
CREATE TABLE `sf_sys_menu` (
  `menu_id` varchar(20) NOT NULL,
  `text` varchar(50) NOT NULL,
  `translate` varchar(100) DEFAULT NULL,
  `group` bit(1) DEFAULT NULL,
  `link` varchar(50) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `parent_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sf_sys_menu
-- ----------------------------
INSERT INTO `sf_sys_menu` VALUES ('M0000', '主导航', 'main_navigation', '', null, null, null);
INSERT INTO `sf_sys_menu` VALUES ('M0010', '仪表盘', 'dashboard', '\0', '/dashboard', 'icon-speedometer', 'M0000');
INSERT INTO `sf_sys_menu` VALUES ('M0020', 'User Profile', 'profile', '\0', '/user/profile', 'icon-speedometer', 'M0000');
INSERT INTO `sf_sys_menu` VALUES ('M0030', 'User Management', 'user-management', '\0', '/user/management', 'icon-user', 'M0000');
INSERT INTO `sf_sys_menu` VALUES ('M0040', 'Company Management', 'company-management', '\0', '/company/management', 'icon-speedometer', 'M0000');
INSERT INTO `sf_sys_menu` VALUES ('M0050', 'Employee Management', 'employee-management', '\0', '/employee/management', 'icon-speedometer', 'M0000');
INSERT INTO `sf_sys_menu` VALUES ('M1000', 'More', 'more', '', null, null, null);
INSERT INTO `sf_sys_menu` VALUES ('M1010', 'Common Logics', 'logics', '\0', '/logics', 'icon-compass', 'M1000');
INSERT INTO `sf_sys_menu` VALUES ('M1020', 'ACL', 'acl', '\0', '/logics/acl', null, 'M1010');
INSERT INTO `sf_sys_menu` VALUES ('M1030', 'Route Guard', 'guard', '\0', '/logics/guard', null, 'M1010');
INSERT INTO `sf_sys_menu` VALUES ('M1040', 'Down File', 'downfile', '\0', '/logics/downfile', null, 'M1010');
commit;

