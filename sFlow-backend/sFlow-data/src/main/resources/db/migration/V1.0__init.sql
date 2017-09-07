-- Delete table sf_sys_sequence
Drop table  IF EXISTS sf_sys_sequence ;

-- Create table sf_sys_sequence 
CREATE TABLE `sf_sys_sequence` (
  `sequence_name` varchar(20) NOT NULL,
  `curr_value` bigint(20) NOT NULL,
  `increment` int(11) DEFAULT NULL,
  `lpad_char` char(1) DEFAULT NULL,
  `lpad_length` int(11) DEFAULT NULL,
  `prefix` varchar(10) DEFAULT NULL,
  `suffix` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `sf_sys_sequence` WRITE;

INSERT INTO `sf_sys_sequence` (`sequence_name`, `curr_value`, `increment`, `lpad_char`, `lpad_length`, `prefix`, `suffix`)
VALUES ('SEQ_USER',0,1,'0',5,'U',NULL);

commit;
UNLOCK TABLES;












