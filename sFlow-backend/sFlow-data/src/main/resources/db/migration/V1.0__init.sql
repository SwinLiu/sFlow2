-- Delete table sf_sys_sequence
Drop table  IF EXISTS sf_sys_sequence ;

-- Create table sf_sys_sequence
CREATE TABLE `sf_sys_sequence` (
  `sequence_name` varchar(20) NOT NULL,
  `curr_value` bigint(20) NOT NULL,
  `increment` int(2) DEFAULT NULL,
  `lpad_char` char(1) DEFAULT NULL,
  `lpad_length` int(2) DEFAULT NULL,
  `prefix` varchar(10) DEFAULT NULL,
  `suffix` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `sf_sys_sequence` WRITE;

INSERT INTO `sf_sys_sequence` (`sequence_name`, `curr_value`, `increment`, `lpad_char`, `lpad_length`, `prefix`, `suffix`)
VALUES ('SEQ_USER_ID',1,1,'0',5,'U',NULL);
INSERT INTO `sf_sys_sequence` (`sequence_name`, `curr_value`, `increment`, `lpad_char`, `lpad_length`, `prefix`, `suffix`)
VALUES ('SEQ_COMP_ID',1,1,'0',5,'C',NULL);
INSERT INTO `sf_sys_sequence` (`sequence_name`, `curr_value`, `increment`, `lpad_char`, `lpad_length`, `prefix`, `suffix`)
VALUES ('SEQ_EMP_ID',1,1,'0',5,'E',NULL);
commit;
UNLOCK TABLES;



/* Create Sequence Procedure */
DROP procedure IF EXISTS `nextval`;

DELIMITER $$
CREATE PROCEDURE nextval(
  IN in_sequence_name varchar(20),
  OUT out_prefix varchar(10),
  OUT out_curr_value bigint(20),
  OUT out_lpad_char char(1),
  OUT out_lpad_length int(2),
  OUT out_suffix varchar(10)
 )
BEGIN

	-- get current sequence value
 	select prefix,curr_value,lpad_char, lpad_length, suffix
      INTO out_prefix,out_curr_value,out_lpad_char, out_lpad_length, out_suffix
      from sf_sys_sequence
	 WHERE sequence_name = in_sequence_name for update;

	-- update current sequence value
	UPDATE sf_sys_sequence t
	   SET t.curr_value = t.curr_value + t.increment
	 WHERE t.sequence_name = in_sequence_name;

END$$

DELIMITER ;


/*
Test function

CALL nextval('SEQ_USER_ID',@prefix,@curr_value,@lpad_char, @lpad_length, @suffix);
SELECT @prefix,@curr_value,@lpad_char, @lpad_length, @suffix;
*/



-- Delete table sf_usr_account
Drop table  IF EXISTS sf_usr_account ;

CREATE TABLE `sf_usr_account` (
  `uid` varchar(20) NOT NULL,
  `user_name` varchar(40) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `status` int(1) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `changer` varchar(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `sf_usr_account`
ADD PRIMARY KEY (`uid`),
ADD UNIQUE INDEX `email_UNIQUE` (`email` ASC),
ADD UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC),
ADD UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC);

/*
DELIMITER $$
CREATE TRIGGER `sf_usr_account_BEFORE_INSERT` BEFORE INSERT ON `sf_usr_account` FOR EACH ROW
BEGIN
	DECLARE new_uid varchar(20);
    DECLARE dt CHAR(8);
	DECLARE in_sequence_name varchar(20);
	DECLARE prefix varchar(10);
	DECLARE curr_value bigint(20);
	DECLARE lpad_char char(1);
	DECLARE lpad_length int(2);
	DECLARE suffix varchar(10);

	SET dt = DATE_FORMAT(CURDATE(),'%Y%m%d');
    SET in_sequence_name = 'SEQ_USER_ID';

	CALL nextval(in_sequence_name,@prefix,@curr_value,@lpad_char, @lpad_length, @suffix);

    SET new_uid =  CONCAT(@prefix, dt, RIGHT(CONCAT('00000000000', @curr_value), @lpad_length));

	SET NEW.uid = new_uid;

END$$

DELIMITER ;
*/


LOCK TABLES `sf_usr_account` WRITE;
INSERT INTO `sf_usr_account` (`uid`, `user_name`, `email`, `phone_number`, `status`, `create_time`, `changer`, `update_time`)
VALUES ('U00001', 'admin', 'i@lyplay.com', '13112341234', 1, unix_timestamp(NOW(3))* 1000, 'SYSTEM', unix_timestamp(NOW(3))* 1000);
commit;
UNLOCK TABLES;



-- Delete table sf_usr_password
Drop table  IF EXISTS sf_usr_password ;

CREATE TABLE `sf_usr_password` (
  `uid` varchar(20) NOT NULL,
  `password` varchar(40) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `changer` varchar(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `sf_usr_password` (`uid`, `password`, `create_time`, `changer`, `update_time`)
select uid, '7b902e6ff1db9f560443f2048974fd7d386975b0', unix_timestamp(NOW(3))* 1000, 'SYSTEM', unix_timestamp(NOW(3))* 1000
  from sf_usr_account
 where user_name = 'admin';
commit;


-- Delete table sf_usr_log
Drop table  IF EXISTS sf_usr_log ;

CREATE TABLE `sf_usr_log` (
  `uid` varchar(20) NOT NULL,
  `login_time` bigint(20) NOT NULL,
  `logout_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`,`login_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Delete table sf_com_company
Drop table  IF EXISTS sf_com_company ;

CREATE TABLE `sf_com_company` (
  `comp_id` varchar(20) NOT NULL,
  `comp_name` varchar(100) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `changer` varchar(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`comp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- Delete table sf_com_emp
Drop table  IF EXISTS sf_com_emp ;

CREATE TABLE `sf_com_emp` (
  `emp_id` varchar(20) NOT NULL,
  `employee_id` varchar(50) NOT NULL,
  `sur_name` varchar(100) DEFAULT NULL,
  `given_name` varchar(100) DEFAULT NULL,
  `gender` int(1) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `work_email` varchar(100) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `test_flag` bit(1) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `changer` varchar(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Delete table sf_com_user_emp
Drop table  IF EXISTS sf_com_user_emp ;

CREATE TABLE `sf_com_user_emp` (
  `id` varchar(20) NOT NULL,
  `comp_id` varchar(20) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `emp_id` varchar(20) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `changer` varchar(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Delete table sf_com_org
Drop table  IF EXISTS sf_com_org ;

CREATE TABLE `sf_com_org` (
  `id` varchar(20) NOT NULL,
  `comp_id` varchar(20) NOT NULL,
  `org_id` varchar(20) NOT NULL,
  `org_name` varchar(200) NOT NULL,
  `org_type_id` varchar(20) NOT NULL,
  `order_number` int(5) NOT NULL,
  `parent_id` varchar(20) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `changer` varchar(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Delete table sf_com_org_type
Drop table  IF EXISTS sf_com_org_type ;

CREATE TABLE `sf_com_org_type` (
  `id` varchar(20) NOT NULL,
  `comp_id` varchar(20) NOT NULL,
  `org_type_name` varchar(200) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `changer` varchar(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Delete table sf_com_pos
Drop table  IF EXISTS sf_com_pos ;

CREATE TABLE `sf_com_pos` (
  `id` varchar(20) NOT NULL,
  `comp_id` varchar(20) NOT NULL,
  `pos_name` varchar(200) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `changer` varchar(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Delete table sf_com_usr_org_pos
Drop table  IF EXISTS sf_com_usr_org_pos ;

CREATE TABLE `sf_com_usr_org_pos` (
  `id` varchar(20) NOT NULL,
  `comp_id` varchar(20) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `org_id` varchar(20) NOT NULL,
  `pos_id` varchar(20) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `changer` varchar(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Delete table sf_com_grade
Drop table  IF EXISTS sf_com_grade ;

CREATE TABLE `sf_com_grade` (
  `id` varchar(20) NOT NULL,
  `comp_id` varchar(20) NOT NULL,
  `grade_id` varchar(20) NOT NULL,
  `grade_name` varchar(200) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `changer` varchar(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Delete table sf_com_emp_grade
Drop table  IF EXISTS sf_com_emp_grade ;

CREATE TABLE `sf_com_emp_grade` (
  `id` varchar(20) NOT NULL,
  `emp_id` varchar(20) NOT NULL,
  `grade_id` varchar(20) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `changer` varchar(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Delete table sf_com_role
Drop table  IF EXISTS sf_com_role ;

CREATE TABLE `sf_com_role` (
  `id` varchar(20) NOT NULL,
  `comp_id` varchar(20) NOT NULL,
  `role_name` varchar(200) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `changer` varchar(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- Delete table sf_com_access_grp
Drop table  IF EXISTS sf_com_access_grp ;

CREATE TABLE `sf_com_access_grp` (
  `id` varchar(20) NOT NULL,
  `comp_id` varchar(20) NOT NULL,
  `group_name` varchar(20) NOT NULL,
  `order_number` int(5) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `changer` varchar(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Delete table sf_com_role_access_grp
Drop table  IF EXISTS sf_com_role_access_grp ;

CREATE TABLE `sf_com_role_access_grp` (
  `id` varchar(20) NOT NULL,
  `comp_id` varchar(20) NOT NULL,
  `role_id` varchar(20) NOT NULL,
  `group_id` varchar(20) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `changer` varchar(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Delete table sf_com_user_access_grp
Drop table  IF EXISTS sf_com_user_access_grp ;

CREATE TABLE `sf_com_user_access_grp` (
  `id` varchar(20) NOT NULL,
  `comp_id` varchar(20) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `group_id` varchar(20) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `changer` varchar(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Delete table sf_com_acl
Drop table  IF EXISTS sf_com_acl ;

CREATE TABLE `sf_com_acl` (
  `id` varchar(20) NOT NULL,
  `comp_id` varchar(20) NOT NULL,
  `p_id` varchar(20) NOT NULL,
  `p_type` varchar(20) NOT NULL,
  `r_id` varchar(20) NOT NULL,
  `r_type` varchar(20) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `changer` varchar(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;













