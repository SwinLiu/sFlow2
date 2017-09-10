-- Delete table sf_sys_sequence
Drop table  IF EXISTS sf_sys_sequence ;

-- Create table sf_sys_sequence 
CREATE TABLE `sf_sys_sequence` (
  `sequence_name` varchar(20) NOT NULL,
  `curr_value` bigint(20) NOT NULL,
  `increment` int(11) DEFAULT NULL,
  `lpad_char` char(1) DEFAULT NULL,
  `lpad_length` int(2) DEFAULT NULL,
  `prefix` varchar(10) DEFAULT NULL,
  `suffix` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `sf_sys_sequence` WRITE;

INSERT INTO `sf_sys_sequence` (`sequence_name`, `curr_value`, `increment`, `lpad_char`, `lpad_length`, `prefix`, `suffix`)
VALUES ('SEQ_USER',0,1,'0',5,'U',NULL);

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
	
	start transaction;
	
	-- get current sequence value
 	select prefix,curr_value,lpad_char, lpad_length, suffix 
      INTO out_prefix,out_curr_value,out_lpad_char, out_lpad_length, out_suffix
      from sf_sys_sequence 
	 WHERE sequence_name = in_sequence_name;
     
	-- update current sequence value
	UPDATE sf_sys_sequence t
	   SET t.curr_value = t.curr_value + t.increment  
	 WHERE t.sequence_name = in_sequence_name;
	
    commit;
END$$

DELIMITER ;


/*
Test function

CALL nextval('SEQ_USER',@prefix,@curr_value,@lpad_char, @lpad_length, @suffix);
SELECT @prefix,@curr_value,@lpad_char, @lpad_length, @suffix;
*/



-- Delete table sf_usr_account
Drop table  IF EXISTS sf_usr_account ;

-- Create table sf_usr_account 
CREATE TABLE `sf_usr_account` (
  `uid` varchar(20) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `user_name` varchar(40) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/* Create Sequence Procedure */
DROP trigger IF EXISTS `tri_sf_usr_account_bi`;

DELIMITER $$
CREATE TRIGGER `tri_sf_usr_account_bi` BEFORE INSERT ON `sf_usr_account` FOR EACH ROW
BEGIN

DECLARE dt CHAR(8);
DECLARE prefix CHAR(10);
DECLARE curr_value bigint(20);
DECLARE lpad_char CHAR(1);
DECLARE lpad_length INT(2);
DECLARE suffix CHAR(10);

set dt = DATE_FORMAT(NOW(), '%Y%m%d');
CALL nextval('SEQ_USER',prefix,curr_value,lpad_char, lpad_length, suffix);

set NEW.uid = concat(prefix,dt,curr_value);

END$$

DELIMITER ;


LOCK TABLES `sf_usr_account` WRITE;

INSERT INTO `sf_usr_account` (`email`, `phone_number`, `user_name`, `create_time`, `changer`, `update_time`)
VALUES ('i@lyplay.com','12345678','Swin.Liu',UNIX_TIMESTAMP(),'SYSTEM',UNIX_TIMESTAMP());

commit;
UNLOCK TABLES;

