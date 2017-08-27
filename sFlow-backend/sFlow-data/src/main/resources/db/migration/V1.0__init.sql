-- Delete table sflow_test
Drop table  IF EXISTS sflow_test ;

-- Create table sflow_test 
CREATE TABLE sflow_test ( 
	id	varchar(20) ,
	user_id	varchar(20) NOT NULL ,
	org_id	varchar(20) NOT NULL ,
	pos_id	varchar(20) NOT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create Primary Key for Table sflow_test 
ALTER TABLE sflow_test ADD CONSTRAINT pk_sflow_test PRIMARY KEY (id); 