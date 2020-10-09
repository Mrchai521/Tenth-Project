/*
SQLyog v10.2 
MySQL - 5.5.49 : Database - tensquare_article
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tensquare_article` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tensquare_article`;

/*Table structure for table `tb_article` */

DROP TABLE IF EXISTS `tb_article`;

CREATE TABLE `tb_article` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `columnid` varchar(20) DEFAULT NULL COMMENT '专栏ID',
  `userid` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '文章正文',
  `image` varchar(100) DEFAULT NULL COMMENT '文章封面',
  `createtime` datetime DEFAULT NULL COMMENT '发表日期',
  `updatetime` datetime DEFAULT NULL COMMENT '修改日期',
  `ispublic` varchar(1) DEFAULT NULL COMMENT '是否公开',
  `istop` varchar(1) DEFAULT NULL COMMENT '是否置顶',
  `visits` int(20) DEFAULT NULL COMMENT '浏览量',
  `thumbup` int(20) DEFAULT NULL COMMENT '点赞数',
  `comment` int(20) DEFAULT NULL COMMENT '评论数',
  `state` varchar(1) DEFAULT NULL COMMENT '审核状态',
  `channelid` varchar(20) DEFAULT NULL COMMENT '所属频道',
  `url` varchar(100) DEFAULT NULL COMMENT 'URL',
  `type` varchar(1) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章';

/*Data for the table `tb_article` */


/*Table structure for table `tb_channel` */

DROP TABLE IF EXISTS `tb_channel`;

CREATE TABLE `tb_channel` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `name` varchar(100) DEFAULT NULL COMMENT '频道名称',
  `state` varchar(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='频道';

/*Data for the table `tb_channel` */

/*Table structure for table `tb_column` */

DROP TABLE IF EXISTS `tb_column`;

CREATE TABLE `tb_column` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `name` varchar(100) DEFAULT NULL COMMENT '专栏名称',
  `summary` varchar(1000) DEFAULT NULL COMMENT '专栏简介',
  `userid` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `createtime` datetime DEFAULT NULL COMMENT '申请日期',
  `checktime` datetime DEFAULT NULL COMMENT '审核日期',
  `state` varchar(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专栏';

/*Data for the table `tb_column` */


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.5.49 : Database - tensquare_base
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tensquare_base` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tensquare_base`;

/*Table structure for table `tb_city` */

DROP TABLE IF EXISTS `tb_city`;

CREATE TABLE `tb_city` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `name` varchar(20) DEFAULT NULL COMMENT '城市名称',
  `ishot` varchar(1) DEFAULT NULL COMMENT '是否热门',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='城市';

/*Data for the table `tb_city` */

insert  into `tb_city`(`id`,`name`,`ishot`) values 

('1','北京','1'),

('2','上海','1'),

('3','广州','1'),

('4','深圳','1'),

('5','天津','0'),

('6','重庆','0'),

('7','西安','0');

/*Table structure for table `tb_label` */

DROP TABLE IF EXISTS `tb_label`;

CREATE TABLE `tb_label` (
  `id` varchar(20) NOT NULL COMMENT '标签ID',
  `labelname` varchar(100) DEFAULT NULL COMMENT '标签名称',
  `state` varchar(1) DEFAULT NULL COMMENT '状态',
  `count` bigint(20) DEFAULT NULL COMMENT '使用数量',
  `recommend` varchar(1) DEFAULT NULL COMMENT '是否推荐',
  `fans` bigint(20) DEFAULT NULL COMMENT '粉丝数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签';

/*Data for the table `tb_label` */

insert  into `tb_label`(`id`,`labelname`,`state`,`count`,`recommend`,`fans`) values 

('1','java','1',NULL,NULL,NULL),

('2','PHP','1',NULL,NULL,NULL),

('3','C++','1',NULL,NULL,NULL),

('4','python','1',NULL,NULL,NULL);

/*Table structure for table `tb_ul` */

DROP TABLE IF EXISTS `tb_ul`;

CREATE TABLE `tb_ul` (
  `userid` varchar(20) NOT NULL,
  `labelid` varchar(20) NOT NULL,
  PRIMARY KEY (`userid`,`labelid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_ul` */

insert  into `tb_ul`(`userid`,`labelid`) values 

('1','1'),

('1','2'),

('1','3');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

/*
SQLyog v10.2 
MySQL - 5.5.49 : Database - tensquare_friend
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tensquare_friend` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tensquare_friend`;

/*Table structure for table `tb_friend` */

DROP TABLE IF EXISTS `tb_friend`;

CREATE TABLE `tb_friend` (
  `userid` varchar(20) NOT NULL COMMENT '用户ID',
  `friendid` varchar(20) NOT NULL COMMENT '好友ID',
  `islike` varchar(1) DEFAULT NULL COMMENT '是否互相喜欢',
  PRIMARY KEY (`userid`,`friendid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_friend` */

insert  into `tb_friend`(`userid`,`friendid`,`islike`) values ('1','100','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

/*
SQLyog v10.2 
MySQL - 5.5.49 : Database - tensquare_gathering
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tensquare_gathering` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tensquare_gathering`;

/*Table structure for table `tb_gathering` */

DROP TABLE IF EXISTS `tb_gathering`;

CREATE TABLE `tb_gathering` (
  `id` varchar(20) NOT NULL COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '活动名称',
  `summary` text COMMENT '大会简介',
  `detail` text COMMENT '详细说明',
  `sponsor` varchar(100) DEFAULT NULL COMMENT '主办方',
  `image` varchar(100) DEFAULT NULL COMMENT '活动图片',
  `starttime` datetime DEFAULT NULL COMMENT '开始时间',
  `endtime` datetime DEFAULT NULL COMMENT '截止时间',
  `address` varchar(100) DEFAULT NULL COMMENT '举办地点',
  `enrolltime` datetime DEFAULT NULL COMMENT '报名截止',
  `state` varchar(1) DEFAULT NULL COMMENT '是否可见',
  `city` varchar(20) DEFAULT NULL COMMENT '城市',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动';

/*Data for the table `tb_gathering` */

insert  into `tb_gathering`(`id`,`name`,`summary`,`detail`,`sponsor`,`image`,`starttime`,`endtime`,`address`,`enrolltime`,`state`,`city`) values ('1','测试活动','喝茶看电影，不亦乐乎','喝茶看电影，不亦乐乎','黑马程序员',NULL,'2017-12-14 15:39:32','2017-12-21 15:39:36',NULL,NULL,'1','1');
insert  into `tb_gathering`(`id`,`name`,`summary`,`detail`,`sponsor`,`image`,`starttime`,`endtime`,`address`,`enrolltime`,`state`,`city`) values ('94377594140','aaaa',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','1');
insert  into `tb_gathering`(`id`,`name`,`summary`,`detail`,`sponsor`,`image`,`starttime`,`endtime`,`address`,`enrolltime`,`state`,`city`) values ('943776146707845','aaaa',NULL,NULL,'ssss',NULL,NULL,NULL,'cccc',NULL,'1','1');
insert  into `tb_gathering`(`id`,`name`,`summary`,`detail`,`sponsor`,`image`,`starttime`,`endtime`,`address`,`enrolltime`,`state`,`city`) values ('943776663576121344','aaaa',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','2');
insert  into `tb_gathering`(`id`,`name`,`summary`,`detail`,`sponsor`,`image`,`starttime`,`endtime`,`address`,`enrolltime`,`state`,`city`) values ('943783521749700608','2342423',NULL,NULL,'23454534',NULL,NULL,NULL,'545435435',NULL,'1','2');
insert  into `tb_gathering`(`id`,`name`,`summary`,`detail`,`sponsor`,`image`,`starttime`,`endtime`,`address`,`enrolltime`,`state`,`city`) values ('944085821768732672','JAVAEE茶话会',NULL,NULL,'传智',NULL,NULL,NULL,'金燕龙',NULL,'1','2');
insert  into `tb_gathering`(`id`,`name`,`summary`,`detail`,`sponsor`,`image`,`starttime`,`endtime`,`address`,`enrolltime`,`state`,`city`) values ('944086086991351808','是',NULL,NULL,'11',NULL,NULL,NULL,'11',NULL,'1','3');
insert  into `tb_gathering`(`id`,`name`,`summary`,`detail`,`sponsor`,`image`,`starttime`,`endtime`,`address`,`enrolltime`,`state`,`city`) values ('944090372710207488','大讨论',NULL,NULL,'小马',NULL,NULL,NULL,'消息',NULL,'1','3');
insert  into `tb_gathering`(`id`,`name`,`summary`,`detail`,`sponsor`,`image`,`starttime`,`endtime`,`address`,`enrolltime`,`state`,`city`) values ('944105652622594048','测试测试',NULL,NULL,'测试者',NULL,NULL,NULL,'测试地址',NULL,'1','4');
insert  into `tb_gathering`(`id`,`name`,`summary`,`detail`,`sponsor`,`image`,`starttime`,`endtime`,`address`,`enrolltime`,`state`,`city`) values ('945227340642914304','111',NULL,NULL,'222',NULL,NULL,NULL,'333',NULL,'1','5');
insert  into `tb_gathering`(`id`,`name`,`summary`,`detail`,`sponsor`,`image`,`starttime`,`endtime`,`address`,`enrolltime`,`state`,`city`) values ('945227678527655936','111',NULL,NULL,'222',NULL,NULL,NULL,'333',NULL,'1','5');
insert  into `tb_gathering`(`id`,`name`,`summary`,`detail`,`sponsor`,`image`,`starttime`,`endtime`,`address`,`enrolltime`,`state`,`city`) values ('945235087564345344','啊啊',NULL,NULL,'1',NULL,NULL,NULL,'1',NULL,'1','1');
insert  into `tb_gathering`(`id`,`name`,`summary`,`detail`,`sponsor`,`image`,`starttime`,`endtime`,`address`,`enrolltime`,`state`,`city`) values ('945235534329024512','1',NULL,NULL,'1',NULL,NULL,NULL,'1',NULL,'1','2');
insert  into `tb_gathering`(`id`,`name`,`summary`,`detail`,`sponsor`,`image`,`starttime`,`endtime`,`address`,`enrolltime`,`state`,`city`) values ('945235859786043392','1',NULL,NULL,'1',NULL,NULL,NULL,'1',NULL,'1','3');
insert  into `tb_gathering`(`id`,`name`,`summary`,`detail`,`sponsor`,`image`,`starttime`,`endtime`,`address`,`enrolltime`,`state`,`city`) values ('951384896167874560','??',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `tb_usergath` */

DROP TABLE IF EXISTS `tb_usergath`;

CREATE TABLE `tb_usergath` (
  `userid` varchar(20) NOT NULL COMMENT '用户ID',
  `gathid` varchar(20) NOT NULL COMMENT '活动ID',
  `exetime` datetime DEFAULT NULL COMMENT '点击时间',
  PRIMARY KEY (`userid`,`gathid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户关注活动';

/*Data for the table `tb_usergath` */

insert  into `tb_usergath`(`userid`,`gathid`,`exetime`) values ('1','200','2018-01-06 15:44:04');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;



/*
SQLyog v10.2 
MySQL - 5.5.49 : Database - tensquare_qa
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tensquare_qa` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tensquare_qa`;

/*Table structure for table `tb_pl` */

DROP TABLE IF EXISTS `tb_pl`;

CREATE TABLE `tb_pl` (
  `problemid` varchar(20) NOT NULL COMMENT '问题ID',
  `labelid` varchar(20) NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`problemid`,`labelid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_pl` */

insert  into `tb_pl`(`problemid`,`labelid`) values ('1','1');

/*Table structure for table `tb_problem` */

DROP TABLE IF EXISTS `tb_problem`;

CREATE TABLE `tb_problem` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `createtime` datetime DEFAULT NULL COMMENT '创建日期',
  `updatetime` datetime DEFAULT NULL COMMENT '修改日期',
  `userid` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `nickname` varchar(100) DEFAULT NULL COMMENT '昵称',
  `visits` bigint(20) DEFAULT NULL COMMENT '浏览量',
  `thumbup` bigint(20) DEFAULT NULL COMMENT '点赞数',
  `reply` bigint(20) DEFAULT NULL COMMENT '回复数',
  `solve` varchar(1) DEFAULT NULL COMMENT '是否解决',
  `replyname` varchar(100) DEFAULT NULL COMMENT '回复人昵称',
  `replytime` datetime DEFAULT NULL COMMENT '回复日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问题';

/*Data for the table `tb_problem` */

insert  into `tb_problem`(`id`,`title`,`content`,`createtime`,`updatetime`,`userid`,`nickname`,`visits`,`thumbup`,`reply`,`solve`) values ('1','这是个问题','代码调试不通咋办？','2018-01-08 11:50:50','2018-01-09 11:50:54','2',NULL,101,NULL,NULL,NULL);

/*Table structure for table `tb_reply` */

DROP TABLE IF EXISTS `tb_reply`;

CREATE TABLE `tb_reply` (
  `id` varchar(20) NOT NULL COMMENT '编号',
  `problemid` varchar(20) DEFAULT NULL COMMENT '问题ID',
  `content` text COMMENT '回答内容',
  `createtime` datetime DEFAULT NULL COMMENT '创建日期',
  `updatetime` datetime DEFAULT NULL COMMENT '更新日期',
  `userid` varchar(20) DEFAULT NULL COMMENT '回答人ID',
  `nickname` varchar(100) DEFAULT NULL COMMENT '回答人昵称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回答';

/*Data for the table `tb_reply` */

insert  into `tb_reply`(`id`,`problemid`,`content`,`createtime`,`updatetime`,`userid`,`nickname`) values ('','1',NULL,NULL,NULL,NULL,NULL);
insert  into `tb_reply`(`id`,`problemid`,`content`,`createtime`,`updatetime`,`userid`,`nickname`) values ('2','1','问老师呗','2018-01-10 14:14:06',NULL,'1',NULL);
insert  into `tb_reply`(`id`,`problemid`,`content`,`createtime`,`updatetime`,`userid`,`nickname`) values ('3','2','明天再调','2018-01-07 14:14:13',NULL,'1',NULL);

/*Table structure for table `tb_ul` */

DROP TABLE IF EXISTS `tb_ul`;

CREATE TABLE `tb_ul` (
  `uid` varchar(20) NOT NULL COMMENT '用户ID',
  `lid` varchar(20) NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`uid`,`lid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_ul` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;



/*
SQLyog v10.2 
MySQL - 5.5.49 : Database - tensquare_recruit
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tensquare_recruit` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tensquare_recruit`;

/*Table structure for table `tb_enterprise` */

DROP TABLE IF EXISTS `tb_enterprise`;

CREATE TABLE `tb_enterprise` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `name` varchar(100) DEFAULT NULL COMMENT '企业名称',
  `summary` varchar(1000) DEFAULT NULL COMMENT '企业简介',
  `address` varchar(100) DEFAULT NULL COMMENT '企业地址',
  `labels` varchar(100) DEFAULT NULL COMMENT '标签列表',
  `coordinate` varchar(100) DEFAULT NULL COMMENT '坐标',
  `ishot` varchar(1) DEFAULT NULL COMMENT '是否热门',
  `logo` varchar(100) DEFAULT NULL COMMENT 'LOGO',
  `jobcount` int DEFAULT NULL COMMENT '职位数',
  `url` varchar(100) DEFAULT NULL COMMENT 'URL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业';

/*Data for the table `tb_enterprise` */

insert  into `tb_enterprise`(`id`,`name`,`summary`,`address`,`labels`,`coordinate`,`ishot`) values ('','传智播客','国内著名IT教育机构','金燕龙办公楼','IT 培训','1019,2223','1');
insert  into `tb_enterprise`(`id`,`name`,`summary`,`address`,`labels`,`coordinate`,`ishot`) values ('2','小米','手机厂商','中关村软件园','手机','0211,3333','0');

/*Table structure for table `tb_recruit` */

DROP TABLE IF EXISTS `tb_recruit`;

CREATE TABLE `tb_recruit` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `jobname` varchar(100) DEFAULT NULL COMMENT '职位名称',
  `salary` varchar(100) DEFAULT NULL COMMENT '薪资范围',
  `condition` varchar(100) DEFAULT NULL COMMENT '经验要求',
  `education` varchar(100) DEFAULT NULL COMMENT '学历要求',
  `type` varchar(1) DEFAULT NULL COMMENT '任职方式',
  `address` varchar(100) DEFAULT NULL COMMENT '办公地址',
  `eid` varchar(20) DEFAULT NULL COMMENT '企业ID',
  `createtime` datetime DEFAULT NULL COMMENT '创建日期',
  `state` varchar(1) DEFAULT NULL COMMENT '状态',
  `url` varchar(100) DEFAULT NULL COMMENT '网址',
  `label` varchar(100) DEFAULT NULL COMMENT '标签',
  `content1` varchar(100) DEFAULT NULL COMMENT '职位描述',
  `content2` varchar(100) DEFAULT NULL COMMENT '职位要求',  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职位';

/*Data for the table `tb_recruit` */

insert  into `tb_recruit`(`id`,`jobname`,`salary`,`condition`,`education`,`type`,`address`,`eid`,`createtime`,`state`,`url`) values ('','大数据工程师','20000-30000','八年以上开发经验','本科','1','国贸','1','2018-01-06 16:21:12','1',NULL);
insert  into `tb_recruit`(`id`,`jobname`,`salary`,`condition`,`education`,`type`,`address`,`eid`,`createtime`,`state`,`url`) values ('1','java开发工程师','15000-20000','五年以上开发经验','本科','1','中关村软件园','1','2018-01-05 15:38:05','1','http://www.baidu.com');
insert  into `tb_recruit`(`id`,`jobname`,`salary`,`condition`,`education`,`type`,`address`,`eid`,`createtime`,`state`,`url`) values ('2','php开发工程师','4000-6000','一年以上开发经验','专科','1','王府街宏福创业园','1','2018-01-07 16:10:20','1','http://www.baidu.com');
insert  into `tb_recruit`(`id`,`jobname`,`salary`,`condition`,`education`,`type`,`address`,`eid`,`createtime`,`state`,`url`) values ('3','.net开发工程师','2000-3000','一年以上开发经验','专科','1','大望路','1','2018-01-06 16:20:27','2',NULL);
insert  into `tb_recruit`(`id`,`jobname`,`salary`,`condition`,`education`,`type`,`address`,`eid`,`createtime`,`state`,`url`) values ('5','前端开发工程师','8000-12000','三年以上开发经验','本科','1','上地','1','2018-01-18 16:22:11','2',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;



/*
SQLyog v10.2 
MySQL - 5.5.49 : Database - tensquare_user
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tensquare_user` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tensquare_user`;

/*Table structure for table `tb_admin` */

DROP TABLE IF EXISTS `tb_admin`;

CREATE TABLE `tb_admin` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `loginname` varchar(100) DEFAULT NULL COMMENT '登陆名称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `state` varchar(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员';

/*Data for the table `tb_admin` */

/*Table structure for table `tb_follow` */

DROP TABLE IF EXISTS `tb_follow`;

CREATE TABLE `tb_follow` (
  `userid` varchar(20) NOT NULL COMMENT '用户ID',
  `targetuser` varchar(20) NOT NULL COMMENT '被关注用户ID',
  PRIMARY KEY (`userid`,`targetuser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_follow` */

insert  into `tb_follow`(`userid`,`targetuser`) values ('1','1');
insert  into `tb_follow`(`userid`,`targetuser`) values ('1','10');

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号码',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(100) DEFAULT NULL COMMENT '昵称',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '出生年月日',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT 'E-Mail',
  `regdate` datetime DEFAULT NULL COMMENT '注册日期',
  `updatedate` datetime DEFAULT NULL COMMENT '修改日期',
  `lastdate` datetime DEFAULT NULL COMMENT '最后登陆日期',
  `online` bigint(20) DEFAULT NULL COMMENT '在线时长（分钟）',
  `interest` varchar(100) DEFAULT NULL COMMENT '兴趣',
  `personality` varchar(100) DEFAULT NULL COMMENT '个性',
  `fanscount` int(20) DEFAULT NULL COMMENT '粉丝数',
  `followcount` int(20) DEFAULT NULL COMMENT '关注数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`password`,`nickname`,`sex`,`birthday`,`mobile`,`email`,`regdate`,`updatedate`,`lastdate`,`online`,`interest`,`personality`,`fanscount`,`followcount`) values ('1','111111','小白','男','2018-01-08 15:39:19',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


