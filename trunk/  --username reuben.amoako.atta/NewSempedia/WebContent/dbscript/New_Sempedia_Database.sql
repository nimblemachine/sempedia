CREATE TABLE `public`.`class_description`  ( 
	`class_text_id`	int4 NOT NULL,
	`para_style`   	varchar(255) NULL,
	`class_id`     	int4 NULL,
	`para_text`    	varchar(10000) NULL,
	PRIMARY KEY(`class_text_id`)
)

CREATE TABLE `class`  ( 
	`class_id`  	int4 NOT NULL,
	`class_name`	varchar(255) NULL,
	PRIMARY KEY(`class_id`)
)

CREATE TABLE `inheritance`  ( 
	`inheritance_id`	int4 NOT NULL,
	`subclass`      	int4 NULL,
	`superclass`    	int4 NULL,
	PRIMARY KEY(`inheritance_id`)
)

CREATE TABLE `object_description`  ( 
	`object_description_id`	int4 NOT NULL,
	`object_image`         	bytea NULL,
	`object_id`            	int4 NULL,
	`object_desc`          	varchar(10000) NULL,
	PRIMARY KEY(`object_description_id`)
)

CREATE TABLE `object`  ( 
	`object_id`  	int4 NOT NULL,
	`object_name`	varchar(255) NULL,
	`class_id`   	int4 NULL,
	PRIMARY KEY(`object_id`)
)

CREATE TABLE `predicate`  ( 
	`predicate_id`  	int4 NOT NULL,
	`predicate_name`	varchar(255) NULL,
	`class_id`      	int4 NULL,
	PRIMARY KEY(`predicate_id`)
)

CREATE TABLE `triple`  ( 
	`triple_id`   	int4 NOT NULL,
	`predicate_id`	int4 NULL,
	`subject`     	int4 NULL,
	`object`      	int4 NULL,
	PRIMARY KEY(`triple_id`)
)

CREATE UNIQUE INDEX `class_description_pkey`
	ON `class_description`(`class_text_id`)

CREATE UNIQUE INDEX `class_pkey`
	ON `class`(`class_id`)

CREATE UNIQUE INDEX `inheritance_pkey`
	ON `inheritance`(`inheritance_id`)

CREATE UNIQUE INDEX `object_description_pkey`
	ON `object_description`(`object_description_id`)

CREATE UNIQUE INDEX `object_pkey`
	ON `object`(`object_id`)

CREATE UNIQUE INDEX `predicate_pkey`
	ON `predicate`(`predicate_id`)

CREATE UNIQUE INDEX `triple_pkey`
	ON `triple`(`triple_id`)


ALTER TABLE `class_description`
	ADD CONSTRAINT `class_id`
	FOREIGN KEY(`class_id`)
	REFERENCES `class`(`class_id`)
	MATCH SIMPLE
	ON DELETE NO ACTION
	ON UPDATE NO ACTION 

ALTER TABLE `inheritance`
	ADD CONSTRAINT `class_id`
	FOREIGN KEY(`superclass`)
	REFERENCES `class`(`class_id`)
	MATCH SIMPLE
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 

ALTER TABLE `object_description`
	ADD CONSTRAINT `object_id`
	FOREIGN KEY(`object_id`)
	REFERENCES `object`(`object_id`)
	MATCH SIMPLE
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 

ALTER TABLE `object`
	ADD CONSTRAINT `fkc300a33ffd1b4d3d`
	FOREIGN KEY(`class_id`)
	REFERENCES `class`(`class_id`)
	MATCH SIMPLE
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 

ALTER TABLE `predicate`
	ADD CONSTRAINT `fkafa6a9b7fd1b4d3d`
	FOREIGN KEY(`class_id`)
	REFERENCES `class`(`class_id`)
	MATCH SIMPLE
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 

ALTER TABLE `triple`
	ADD CONSTRAINT `object_id`
	FOREIGN KEY(`object`)
	REFERENCES `object`(`object_id`)
	MATCH SIMPLE
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 

ALTER TABLE `triple`
	ADD CONSTRAINT `fkcc6a0c5e37d36139`
	FOREIGN KEY(`predicate_id`)
	REFERENCES `predicate`(`predicate_id`)
	MATCH SIMPLE
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 


