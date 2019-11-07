/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(225) NOT NULL,
  `first_name` varchar(40),
  `last_name` varchar(40),
  `contact_number` varchar(11),
  `password` varchar(255),
  `create_date` datetime,
  `update_date` timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `reg_code` varchar(255),
  `status` varchar(40) COMMENT '0 Inactive,1 Active,3 Delete',
  `roles` varchar(255) COMMENT 'USER,MENTOR,ADMIN',
  `linkedin_url` varchar(512),
  PRIMARY KEY (`id`)
);

/*Table structure for table `mentor_skill` */

DROP TABLE IF EXISTS `mentor_skill`;

CREATE TABLE `mentor_skill` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `mentor_id` int(11),
  `skill_id` int(11),
  `self_rating` float(3,1),
  `years_of_exp` float(3,1),
  `price` DECIMAL(9,2),
  `remarks` varchar(512),
  `create_date` datetime,
  `update_date` timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `status` varchar(40) COMMENT '0 Inactive,1 Active,3 Delete',
  PRIMARY KEY (`id`)
);


/*Table structure for table `skill` */

DROP TABLE IF EXISTS `skill`;

CREATE TABLE `skill` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(40),
  `toc` varchar(40),
  `prerequites` varchar(512),
  `create_date` datetime,
  `update_date` timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `status` varchar(40) COMMENT '0 Inactive,1 Active,3 Delete',
  PRIMARY KEY (`id`)
);

/*Table structure for table `training` */

DROP TABLE IF EXISTS `training`;

CREATE TABLE `training` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11),
  `mentor_id` int(11),
  `skill_id` int(11),
  `rating` varchar(10),
  `start_date` datetime, 
  `end_date` datetime,
  `create_date` datetime,
  `update_date` timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `status` varchar(40) COMMENT '0 Inactive,1 Active,2 Proposed,4 Confirmed,5 Trainings Started,6 Not Completed,7 Withdraw,8 Reject,3 Delete',
  PRIMARY KEY (`id`)
);
