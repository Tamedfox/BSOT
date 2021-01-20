/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.62 : Database - bsot
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bsot` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bsot`;

/*Table structure for table `p_bsot_menu` */

DROP TABLE IF EXISTS `p_bsot_menu`;

CREATE TABLE `p_bsot_menu` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '资源ID',
  `parent_id` bigint(32) DEFAULT NULL COMMENT '父级资源ID',
  `name` varchar(64) DEFAULT NULL COMMENT '资源名称',
  `description` varchar(128) DEFAULT NULL COMMENT '资源描述',
  `path` varchar(64) DEFAULT NULL COMMENT '路径',
  `method` varchar(10) DEFAULT NULL COMMENT '请求方法',
  `type` varchar(1) DEFAULT NULL COMMENT '权限类型:0-菜单 1-菜单项 2-请求',
  `component` varchar(200) DEFAULT NULL COMMENT '组件路径',
  `redirect` varchar(64) DEFAULT NULL COMMENT '重定向路径',
  `affix` tinyint(1) DEFAULT NULL COMMENT '是否固定标签',
  `no_cache` tinyint(1) DEFAULT NULL COMMENT '是否缓存页面',
  `title` varchar(64) DEFAULT NULL COMMENT '展示名称',
  `icon` varchar(64) DEFAULT NULL COMMENT '图标名',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 0-禁用 1-启用',
  `order_seq` int(8) DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='菜单资源表';

/*Data for the table `p_bsot_menu` */

insert  into `p_bsot_menu`(`id`,`parent_id`,`name`,`description`,`path`,`method`,`type`,`component`,`redirect`,`affix`,`no_cache`,`title`,`icon`,`status`,`order_seq`) values (1,0,'首页','首页','/',NULL,'0','Layout','/dashboard',0,NULL,'','',1,1),(2,0,'权限管理','用户权限管理','/permission',NULL,'0','Layout','/permission/userIndex',NULL,NULL,'权限管理','permission',1,2),(3,2,'用户管理','用户相关信息管理','/permission/userIndex','','1','permission/user/index',NULL,NULL,NULL,'用户管理','user',1,1),(4,2,'角色管理','用户角色相关信息管理','/permission/roleIndex',NULL,'1','permission/role/index',NULL,NULL,NULL,'角色管理','role',1,2),(5,2,'菜单管理','用户菜单相关信息管理','/permission/permissionIndex',NULL,'1','permission/permission/index',NULL,NULL,NULL,'菜单管理','permission-manage',1,3),(6,3,'用户信息查询','用户信息查询','/user/*','GET','2',NULL,NULL,NULL,NULL,NULL,NULL,1,1),(7,3,'用户信息删除','用户信息删除','/user/*','DELETE','2',NULL,NULL,NULL,NULL,NULL,NULL,1,2),(8,3,'用户信息添加','用户信息添加','/user','POST','2',NULL,NULL,NULL,NULL,NULL,NULL,1,3),(9,3,'用户信息更新','用户信息更细','/user','PUT','2',NULL,NULL,NULL,NULL,NULL,NULL,1,4),(10,3,'获取指定用户的角色信息','获取指定用户的角色信息','/user/roles/*','GET','2',NULL,NULL,NULL,NULL,NULL,NULL,1,5),(11,3,'获取指定用户的权限信息','获取指定用户的权限信息','/user/menus/*','GET','2',NULL,NULL,NULL,NULL,NULL,NULL,1,6),(12,3,'分配指定用户的角色信息','分配指定用户的角色信息','/user/role/update','PUT','2',NULL,NULL,NULL,NULL,NULL,NULL,1,7),(13,3,'分页获取用户信息','分页获取用户信息','/user/list/*/*','GET','2',NULL,NULL,NULL,NULL,NULL,NULL,1,8),(14,3,'获取指定用户的导航菜单','获取指定用户的导航菜单','/user/list/nav','GET','2',NULL,NULL,NULL,NULL,NULL,NULL,1,9),(15,1,'获取当前在线的活跃用户','获取当前在线的活跃用户','/dashboard/online','GET','2',NULL,NULL,NULL,NULL,NULL,NULL,1,1),(17,1,'首页','首页','dashboard',NULL,'1','dashboard/index','',1,NULL,'首页','dashboard',1,2),(18,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `p_bsot_role` */

DROP TABLE IF EXISTS `p_bsot_role`;

CREATE TABLE `p_bsot_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(128) DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '角色状态 0-禁用 1-启用',
  `order_seq` int(10) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户角色信息表';

/*Data for the table `p_bsot_role` */

insert  into `p_bsot_role`(`id`,`name`,`description`,`create_time`,`update_time`,`status`,`order_seq`) values (1,'管理员','管理员','2020-09-20 18:58:52',NULL,1,1);

/*Table structure for table `p_bsot_role_menu_rel` */

DROP TABLE IF EXISTS `p_bsot_role_menu_rel`;

CREATE TABLE `p_bsot_role_menu_rel` (
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(32) DEFAULT NULL COMMENT '权限id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色权限关联关系表';

/*Data for the table `p_bsot_role_menu_rel` */

insert  into `p_bsot_role_menu_rel`(`role_id`,`menu_id`) values (1,1),(1,6),(1,11),(1,13),(1,2),(1,3),(1,4),(1,5),(1,7),(1,8),(1,9),(1,10),(1,12),(1,14),(1,15),(1,17);

/*Table structure for table `p_bsot_user` */

DROP TABLE IF EXISTS `p_bsot_user`;

CREATE TABLE `p_bsot_user` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `icon` varchar(500) DEFAULT NULL COMMENT '头像地址',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最近一次登录时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '账户状态：0-禁用 1-启动',
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `p_bsot_user` */

insert  into `p_bsot_user`(`id`,`username`,`password`,`icon`,`email`,`create_time`,`last_login_time`,`status`) values (1,'admin','$2a$10$Xn9FwFE3s/zgXtY/MrdF5.hqv5lvJAZyWIOt2cx2i4GSF25ikLRSm','https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202002%2F23%2F20200223201121_ygnrh.thumb.400_0.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1612877339&t=468d7f9b313b1f70da3f8fb006025044','191089192@qq.com','2020-09-20 17:39:42','2021-01-17 19:27:48',1);

/*Table structure for table `p_bsot_user_role_rel` */

DROP TABLE IF EXISTS `p_bsot_user_role_rel`;

CREATE TABLE `p_bsot_user_role_rel` (
  `user_id` bigint(32) DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联关系表';

/*Data for the table `p_bsot_user_role_rel` */

insert  into `p_bsot_user_role_rel`(`user_id`,`role_id`) values (1,1);

/*Table structure for table `s_bsot_quartz_job` */

DROP TABLE IF EXISTS `s_bsot_quartz_job`;

CREATE TABLE `s_bsot_quartz_job` (
  `job_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bean_name` varchar(255) DEFAULT NULL COMMENT 'Spring Bean名称',
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron 表达式',
  `is_stop` tinyint(1) DEFAULT NULL COMMENT '状态：1暂停、0启用',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
  `method_name` varchar(255) DEFAULT NULL COMMENT '方法名称',
  `params` varchar(255) DEFAULT NULL COMMENT '参数',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  `person_in_charge` varchar(100) DEFAULT NULL COMMENT '负责人',
  `email` varchar(100) DEFAULT NULL COMMENT '报警邮箱',
  `sub_task` varchar(100) DEFAULT NULL COMMENT '子任务ID',
  `pause_after_failure` tinyint(1) DEFAULT NULL COMMENT '任务失败后是否暂停',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='定时任务';

/*Data for the table `s_bsot_quartz_job` */

insert  into `s_bsot_quartz_job`(`job_id`,`bean_name`,`cron_expression`,`is_stop`,`job_name`,`method_name`,`params`,`description`,`person_in_charge`,`email`,`sub_task`,`pause_after_failure`,`create_by`,`update_by`,`create_time`,`update_time`) values (1,'testJob','/10 * * * * ?',0,'测试','test',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL),(2,'testJob','/20 * * * * ?',0,'测试参数','testParams','hello,world',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
