CREATE TABLE user (
	user_id int NOT NULL auto_increment,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	email varchar(255) NOT NULL UNIQUE,
	password varchar(255) NOT NULL,
	active bit,
	activation_code varchar(255),
	primary key (user_id)
) engine = InnoDB;

CREATE TABLE access_level (
	user_id int NOT NULL,
	access_levels varchar(255)
) engine = InnoDB;


CREATE TABLE harvesting_date (
  date_id int NOT NULL AUTO_INCREMENT,
  date date,
  PRIMARY KEY (date_id)
)
ENGINE = InnoDB;


CREATE TABLE tomatoes_gardenbed (
	gardenbed_id int NOT NULL,
	comment varchar(255),
	place varchar(1) NOT NULL,
	number_of_plants int NOT NULL,
	width double NOT NULL,
	length double NOT NULL,	
	variety_specification_id int,
	PRIMARY KEY (gardenbed_id)
)
ENGINE = InnoDB;

CREATE TABLE tomatoes_variety_specification (
  variety_specification_id int NOT NULL,
  custom_id varchar(11) NOT NULL,
  variety_id int,
  PRIMARY KEY (variety_specification_id)
)
ENGINE = InnoDB;

CREATE TABLE tomatoes_season_varieties (
	season_id int NOT NULL,
	variety_specification_id int NOT NULL,
	variety_id int NOT NULL,
	PRIMARY KEY (season_id, variety_id)
)
ENGINE = InnoDB;

CREATE TABLE tomatoes_season (
	season_id int NOT NULL,
	year int NOT NULL,
	PRIMARY KEY (season_id)
)
ENGINE = InnoDB;

CREATE TABLE tomatoes_variety (
  variety_id int NOT NULL,
  name varchar(255) NOT NULL,
  season_id int,
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE tomatoes (
  id int NOT NULL,
  date_id int NOT NULL,
  variety_id int NOT NULL,
  quantity int,
  weight int,
  PRIMARY KEY (id)
)
ENGINE = InnoDB;


CREATE TABLE cucumbers_gardenbed (
	gardenbed_id int NOT NULL,
	comment varchar(255),
	place varchar(1) NOT NULL,
	number_of_plants int NOT NULL,
	width double NOT NULL,
	length double NOT NULL,	
	variety_specification_id int,
	PRIMARY KEY (gardenbed_id)
)
ENGINE = InnoDB;

CREATE TABLE cucumbers_variety_specification (
  variety_specification_id int NOT NULL,
  custom_id varchar(11) NOT NULL,
  variety_id int,
  PRIMARY KEY (variety_specification_id)
)
ENGINE = InnoDB;

CREATE TABLE cucumbers_season_varieties (
	season_id int NOT NULL,
	variety_specification_id int NOT NULL,
	variety_id int NOT NULL,
	PRIMARY KEY (season_id, variety_id)
)
ENGINE = InnoDB;

CREATE TABLE cucumbers_season (
	season_id int NOT NULL,
	year int NOT NULL,
	PRIMARY KEY (season_id)
)
ENGINE = InnoDB;

CREATE TABLE cucumbers_variety (
  variety_id int NOT NULL,
  name varchar(255) NOT NULL,
  season_id int,
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE cucumbers (
  id int NOT NULL,
  date_id int NOT NULL,
  variety_id int NOT NULL,
  quantity int,
  weight int,
  PRIMARY KEY (id)
)
ENGINE = InnoDB;


CREATE TABLE peppers_gardenbed (
	gardenbed_id int NOT NULL,
	comment varchar(255),
	place varchar(1) NOT NULL,
	number_of_plants int NOT NULL,
	width double NOT NULL,
	length double NOT NULL,	
	variety_specification_id int,
	PRIMARY KEY (gardenbed_id)
)
ENGINE = InnoDB;

CREATE TABLE peppers_variety_specification (
  variety_specification_id int NOT NULL,
  custom_id varchar(11) NOT NULL,
  variety_id int,
  PRIMARY KEY (variety_specification_id)
)
ENGINE = InnoDB;

CREATE TABLE peppers_season_varieties (
	season_id int NOT NULL,
	variety_specification_id int NOT NULL,
	variety_id int NOT NULL,
	PRIMARY KEY (season_id, variety_id)
)
ENGINE = InnoDB;

CREATE TABLE peppers_season (
	season_id int NOT NULL,
	year int NOT NULL,
	PRIMARY KEY (season_id)
)
ENGINE = InnoDB;

CREATE TABLE peppers_variety (
  variety_id int NOT NULL,
  name varchar(255) NOT NULL,
  season_id int,
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE peppers (
  id int NOT NULL,
  date_id int NOT NULL,
  variety_id int NOT NULL,
  quantity int,
  weight int,
  PRIMARY KEY (id)
)
ENGINE = InnoDB;


CREATE TABLE carrots_gardenbed (
	gardenbed_id int NOT NULL,
	comment varchar(255),
	place varchar(1) NOT NULL,
	number_of_plants int NOT NULL,
	width double NOT NULL,
	length double NOT NULL,	
	variety_specification_id int,
	PRIMARY KEY (gardenbed_id)
)
ENGINE = InnoDB;

CREATE TABLE carrots_variety_specification (
  variety_specification_id int NOT NULL,
  custom_id varchar(11) NOT NULL,
  variety_id int,
  PRIMARY KEY (variety_specification_id)
)
ENGINE = InnoDB;

CREATE TABLE carrots_season_varieties (
	season_id int NOT NULL,
	variety_specification_id int NOT NULL,
	variety_id int NOT NULL,
	PRIMARY KEY (season_id, variety_id)
)
ENGINE = InnoDB;

CREATE TABLE carrots_season (
	season_id int NOT NULL,
	year int NOT NULL,
	PRIMARY KEY (season_id)
)
ENGINE = InnoDB;

CREATE TABLE carrots_variety (
  variety_id int NOT NULL,
  name varchar(255) NOT NULL,
  season_id int,
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE carrots (
  id int NOT NULL,
  date_id int NOT NULL,
  variety_id int NOT NULL,
  quantity int,
  weight int,
  size varchar(255),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;


CREATE TABLE beetroots_gardenbed (
	gardenbed_id int NOT NULL,
	comment varchar(255),
	place varchar(1) NOT NULL,
	number_of_plants int NOT NULL,
	width double NOT NULL,
	length double NOT NULL,	
	variety_specification_id int,
	PRIMARY KEY (gardenbed_id)
)
ENGINE = InnoDB;

CREATE TABLE beetroots_variety_specification (
  variety_specification_id int NOT NULL,
  custom_id varchar(11) NOT NULL,
  variety_id int,
  PRIMARY KEY (variety_specification_id)
)
ENGINE = InnoDB;

CREATE TABLE beetroots_season_varieties (
	season_id int NOT NULL,
	variety_specification_id int NOT NULL,
	variety_id int NOT NULL,
	PRIMARY KEY (season_id, variety_id)
)
ENGINE = InnoDB;

CREATE TABLE beetroots_season (
	season_id int NOT NULL,
	year int NOT NULL,
	PRIMARY KEY (season_id)
)
ENGINE = InnoDB;

CREATE TABLE beetroots_variety (
  variety_id int NOT NULL,
  name varchar(255) NOT NULL,
  season_id int,
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE beetroots (
  id int NOT NULL,
  date_id int NOT NULL,
  variety_id int NOT NULL,
  quantity int,
  weight int,
  size varchar(255),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;


CREATE TABLE potatoes_gardenbed (
	gardenbed_id int NOT NULL,
	comment varchar(255),
	place varchar(1) NOT NULL,
	number_of_plants int NOT NULL,
	width double NOT NULL,
	length double NOT NULL,	
	variety_specification_id int,
	PRIMARY KEY (gardenbed_id)
)
ENGINE = InnoDB;

CREATE TABLE potatoes_variety_specification (
  variety_specification_id int NOT NULL,
  custom_id varchar(11) NOT NULL,
  variety_id int,
  PRIMARY KEY (variety_specification_id)
)
ENGINE = InnoDB;

CREATE TABLE potatoes_season_varieties (
	season_id int NOT NULL,
	variety_specification_id int NOT NULL,
	variety_id int NOT NULL,
	PRIMARY KEY (season_id, variety_id)
)
ENGINE = InnoDB;

CREATE TABLE potatoes_season (
	season_id int NOT NULL,
	year int NOT NULL,
	PRIMARY KEY (season_id)
)
ENGINE = InnoDB;

CREATE TABLE potatoes_variety (
  variety_id int NOT NULL,
  name varchar(255) NOT NULL,
  season_id int,
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE potatoes (
  id int NOT NULL,
  date_id int NOT NULL,
  variety_id int NOT NULL,
  quantity int,
  weight int,
  size varchar(255),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;


CREATE TABLE beans_gardenbed (
	gardenbed_id int NOT NULL,
	comment varchar(255),
	place varchar(1) NOT NULL,
	number_of_plants int NOT NULL,
	width double NOT NULL,
	length double NOT NULL,	
	variety_specification_id int,
	PRIMARY KEY (gardenbed_id)
)
ENGINE = InnoDB;

CREATE TABLE beans_variety_specification (
  variety_specification_id int NOT NULL,
  custom_id varchar(11) NOT NULL,
  variety_id int,
  PRIMARY KEY (variety_specification_id)
)
ENGINE = InnoDB;

CREATE TABLE beans_season_varieties (
	season_id int NOT NULL,
	variety_specification_id int NOT NULL,
	variety_id int NOT NULL,
	PRIMARY KEY (season_id, variety_id)
)
ENGINE = InnoDB;

CREATE TABLE beans_season (
	season_id int NOT NULL,
	year int NOT NULL,
	PRIMARY KEY (season_id)
)
ENGINE = InnoDB;

CREATE TABLE beans_variety (
  variety_id int NOT NULL,
  name varchar(255) NOT NULL,
  season_id int,
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE beans (
  id int NOT NULL,
  date_id int NOT NULL,
  variety_id int NOT NULL,
  quantity int,
  weight int,
  PRIMARY KEY (id)
)
ENGINE = InnoDB;


CREATE TABLE cabbage_gardenbed (
	gardenbed_id int NOT NULL,
	comment varchar(255),
	place varchar(1) NOT NULL,
	number_of_plants int NOT NULL,
	width double NOT NULL,
	length double NOT NULL,	
	variety_specification_id int,
	PRIMARY KEY (gardenbed_id)
)
ENGINE = InnoDB;

CREATE TABLE cabbage_variety_specification (
  variety_specification_id int NOT NULL,
  custom_id varchar(11) NOT NULL,
  variety_id int,
  PRIMARY KEY (variety_specification_id)
)
ENGINE = InnoDB;

CREATE TABLE cabbage_season_varieties (
	season_id int NOT NULL,
	variety_specification_id int NOT NULL,
	variety_id int NOT NULL,
	PRIMARY KEY (season_id, variety_id)
)
ENGINE = InnoDB;

CREATE TABLE cabbage_season (
	season_id int NOT NULL,
	year int NOT NULL,
	PRIMARY KEY (season_id)
)
ENGINE = InnoDB;

CREATE TABLE cabbage_variety (
  variety_id int NOT NULL,
  name varchar(255) NOT NULL,
  season_id int,
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE cabbage (
  id int NOT NULL,
  date_id int NOT NULL,
  variety_id int NOT NULL,
  quantity int,
  weight int,
  PRIMARY KEY (id)
)
ENGINE = InnoDB;


CREATE TABLE corn_gardenbed (
	gardenbed_id int NOT NULL,
	comment varchar(255),
	place varchar(1) NOT NULL,
	number_of_plants int NOT NULL,
	width double NOT NULL,
	length double NOT NULL,	
	variety_specification_id int,
	PRIMARY KEY (gardenbed_id)
)
ENGINE = InnoDB;

CREATE TABLE corn_variety_specification (
  variety_specification_id int NOT NULL,
  custom_id varchar(11) NOT NULL,
  variety_id int,
  PRIMARY KEY (variety_specification_id)
)
ENGINE = InnoDB;

CREATE TABLE corn_season_varieties (
	season_id int NOT NULL,
	variety_specification_id int NOT NULL,
	variety_id int NOT NULL,
	PRIMARY KEY (season_id, variety_id)
)
ENGINE = InnoDB;

CREATE TABLE corn_season (
	season_id int NOT NULL,
	year int NOT NULL,
	PRIMARY KEY (season_id)
)
ENGINE = InnoDB;

CREATE TABLE corn_variety (
  variety_id int NOT NULL,
  name varchar(255) NOT NULL,
  season_id int,
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE corn (
  id int NOT NULL,
  date_id int NOT NULL,
  variety_id int NOT NULL,
  quantity int,
  weight int,
  PRIMARY KEY (id)
)
ENGINE = InnoDB;


CREATE TABLE squash_gardenbed (
	gardenbed_id int NOT NULL,
	comment varchar(255),
	place varchar(1) NOT NULL,
	number_of_plants int NOT NULL,
	width double NOT NULL,
	length double NOT NULL,	
	variety_specification_id int,
	PRIMARY KEY (gardenbed_id)
)
ENGINE = InnoDB;

CREATE TABLE squash_variety_specification (
  variety_specification_id int NOT NULL,
  custom_id varchar(11) NOT NULL,
  variety_id int,
  PRIMARY KEY (variety_specification_id)
)
ENGINE = InnoDB;

CREATE TABLE squash_season_varieties (
	season_id int NOT NULL,
	variety_specification_id int NOT NULL,
	variety_id int NOT NULL,
	PRIMARY KEY (season_id, variety_id)
)
ENGINE = InnoDB;

CREATE TABLE squash_season (
	season_id int NOT NULL,
	year int NOT NULL,
	PRIMARY KEY (season_id)
)
ENGINE = InnoDB;

CREATE TABLE squash_variety (
  variety_id int NOT NULL,
  name varchar(255) NOT NULL,
  season_id int,
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE squash (
  id int NOT NULL,
  date_id int NOT NULL,
  variety_id int NOT NULL,
  weight int,
  PRIMARY KEY (id)
)
ENGINE = InnoDB;


CREATE TABLE pumpkins_gardenbed (
	gardenbed_id int NOT NULL,
	comment varchar(255),
	place varchar(1) NOT NULL,
	number_of_plants int NOT NULL,
	width double NOT NULL,
	length double NOT NULL,	
	variety_specification_id int,
	PRIMARY KEY (gardenbed_id)
)
ENGINE = InnoDB;

CREATE TABLE pumpkins_variety_specification (
  variety_specification_id int NOT NULL,
  custom_id varchar(11) NOT NULL,
  variety_id int,
  PRIMARY KEY (variety_specification_id)
)
ENGINE = InnoDB;

CREATE TABLE pumpkins_season_varieties (
	season_id int NOT NULL,
	variety_specification_id int NOT NULL,
	variety_id int NOT NULL,
	PRIMARY KEY (season_id, variety_id)
)
ENGINE = InnoDB;

CREATE TABLE pumpkins_season (
	season_id int NOT NULL,
	year int NOT NULL,
	PRIMARY KEY (season_id)
)
ENGINE = InnoDB;

CREATE TABLE pumpkins_variety (
  variety_id int NOT NULL,
  name varchar(255) NOT NULL,
  season_id int,
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE pumpkins (
  id int NOT NULL,
  date_id int NOT NULL,
  variety_id int NOT NULL,
  weight int,
  PRIMARY KEY (id)
)
ENGINE = InnoDB;


CREATE TABLE garlic_gardenbed (
	gardenbed_id int NOT NULL,
	comment varchar(255),
	place varchar(1) NOT NULL,
	number_of_plants int NOT NULL,
	width double NOT NULL,
	length double NOT NULL,	
	variety_specification_id int,
	PRIMARY KEY (gardenbed_id)
)
ENGINE = InnoDB;

CREATE TABLE garlic_variety_specification (
  variety_specification_id int NOT NULL,
  custom_id varchar(11) NOT NULL,
  variety_id int,
  PRIMARY KEY (variety_specification_id)
)
ENGINE = InnoDB;

CREATE TABLE garlic_season_varieties (
	season_id int NOT NULL,
	variety_specification_id int NOT NULL,
	variety_id int NOT NULL,
	PRIMARY KEY (season_id, variety_id)
)
ENGINE = InnoDB;

CREATE TABLE garlic_season (
	season_id int NOT NULL,
	year int NOT NULL,
	PRIMARY KEY (season_id)
)
ENGINE = InnoDB;

CREATE TABLE garlic_variety (
  variety_id int NOT NULL,
  name varchar(255) NOT NULL,
  season_id int,
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE garlic (
  id int NOT NULL,
  date_id int NOT NULL,
  variety_id int NOT NULL,
  quantity int,
  weight int,
  PRIMARY KEY (id)
)
ENGINE = InnoDB;


CREATE TABLE roots_gardenbed (
	gardenbed_id int NOT NULL,
	comment varchar(255),
	place varchar(1) NOT NULL,
	number_of_plants int NOT NULL,
	width double NOT NULL,
	length double NOT NULL,	
	variety_specification_id int,
	PRIMARY KEY (gardenbed_id)
)
ENGINE = InnoDB;

CREATE TABLE roots_variety_specification (
  variety_specification_id int NOT NULL,
  custom_id varchar(11) NOT NULL,
  variety_id int,
  PRIMARY KEY (variety_specification_id)
)
ENGINE = InnoDB;

CREATE TABLE roots_season_varieties (
	season_id int NOT NULL,
	variety_specification_id int NOT NULL,
	variety_id int NOT NULL,
	PRIMARY KEY (season_id, variety_id)
)
ENGINE = InnoDB;

CREATE TABLE roots_season (
	season_id int NOT NULL,
	year int NOT NULL,
	PRIMARY KEY (season_id)
)
ENGINE = InnoDB;

CREATE TABLE roots_variety (
  variety_id int NOT NULL,
  name varchar(255) NOT NULL,
  season_id int,
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE roots (
  id int NOT NULL,
  date_id int NOT NULL,
  variety_id int NOT NULL,
  quantity int,
  weight int,
  size varchar(255),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;


CREATE TABLE berries_gardenbed (
	gardenbed_id int NOT NULL,
	comment varchar(255),
	place varchar(1) NOT NULL,
	number_of_plants int NOT NULL,
	width double NOT NULL,
	length double NOT NULL,	
	variety_specification_id int,
	PRIMARY KEY (gardenbed_id)
)
ENGINE = InnoDB;

CREATE TABLE berries_variety_specification (
  variety_specification_id int NOT NULL,
  custom_id varchar(11) NOT NULL,
  variety_id int,
  PRIMARY KEY (variety_specification_id)
)
ENGINE = InnoDB;

CREATE TABLE berries_season_varieties (
	season_id int NOT NULL,
	variety_specification_id int NOT NULL,
	variety_id int NOT NULL,
	PRIMARY KEY (season_id, variety_id)
)
ENGINE = InnoDB;

CREATE TABLE berries_season (
	season_id int NOT NULL,
	year int NOT NULL,
	PRIMARY KEY (season_id)
)
ENGINE = InnoDB;

CREATE TABLE berries_variety (
  variety_id int NOT NULL,
  name varchar(255) NOT NULL,
  season_id int,
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE berries (
  id int NOT NULL,
  date_id int NOT NULL,
  variety_id int NOT NULL,
  weight int,
  PRIMARY KEY (id)
)
ENGINE = InnoDB;


CREATE TABLE fruits_gardenbed (
	gardenbed_id int NOT NULL,
	comment varchar(255),
	place varchar(1) NOT NULL,
	number_of_plants int NOT NULL,
	width double NOT NULL,
	length double NOT NULL,	
	variety_specification_id int,
	PRIMARY KEY (gardenbed_id)
)
ENGINE = InnoDB;

CREATE TABLE fruits_variety_specification (
  variety_specification_id int NOT NULL,
  custom_id varchar(11) NOT NULL,
  variety_id int,
  PRIMARY KEY (variety_specification_id)
)
ENGINE = InnoDB;

CREATE TABLE fruits_season_varieties (
	season_id int NOT NULL,
	variety_specification_id int NOT NULL,
	variety_id int NOT NULL,
	PRIMARY KEY (season_id, variety_id)
)
ENGINE = InnoDB;

CREATE TABLE fruits_season (
	season_id int NOT NULL,
	year int NOT NULL,
	PRIMARY KEY (season_id)
)
ENGINE = InnoDB;

CREATE TABLE fruits_variety (
  variety_id int NOT NULL,
  name varchar(255) NOT NULL,
  season_id int,
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE fruits (
  id int NOT NULL,
  date_id int NOT NULL,
  variety_id int NOT NULL,
  weight int,
  PRIMARY KEY (id)
)
ENGINE = InnoDB;


CREATE TABLE hibernate_sequence (
  next_val bigint
)
ENGINE = InnoDB;


CREATE TABLE sequence_gardenbed (
	next_val bigint
)
ENGINE = InnoDB;

INSERT INTO sequence_gardenbed
	VALUES (1);


CREATE TABLE sequence_season (
	next_val bigint
)
ENGINE = InnoDB;

INSERT INTO sequence_season
	VALUES (1);


CREATE TABLE sequence_variety (
	next_val bigint
)
ENGINE = InnoDB;

INSERT INTO sequence_variety
	VALUES (1);


CREATE TABLE sequence_variety_specification (
	next_val bigint
)
ENGINE = InnoDB;

INSERT INTO sequence_variety_specification
	VALUES (1);


ALTER TABLE access_level
	ADD CONSTRAINT access_level__user__fk
	FOREIGN KEY (user_id) REFERENCES user (user_id);


ALTER TABLE tomatoes_gardenbed
	ADD CONSTRAINT tomatoes_gardenbed__tomatoes_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES tomatoes_variety_specification (variety_specification_id);

ALTER TABLE tomatoes_variety_specification
	ADD CONSTRAINT tomatoes_variety_specification__tomatoes_variety__fk
	FOREIGN KEY (variety_id) REFERENCES tomatoes_variety (variety_id);

ALTER TABLE tomatoes_season_varieties
	ADD CONSTRAINT tomatoes_season_varieties__variety_specification__fk
	UNIQUE (variety_specification_id);

ALTER TABLE tomatoes_season_varieties
	ADD CONSTRAINT tomatoes_season_varieties__tomatoes_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES tomatoes_variety_specification (variety_specification_id);

ALTER TABLE tomatoes_season_varieties
	ADD CONSTRAINT tomatoes_season_varieties__tomatoes_season__fk
	FOREIGN KEY (season_id) REFERENCES tomatoes_season (season_id);

ALTER TABLE tomatoes_season_varieties
	ADD CONSTRAINT tomatoes_season_varieties__tomatoes_variety__fk
	FOREIGN KEY (variety_id) REFERENCES tomatoes_variety (variety_id);

ALTER TABLE tomatoes_variety
	ADD CONSTRAINT tomatoes_variety__tomatoes_season__fk
	FOREIGN KEY (season_id) REFERENCES tomatoes_season (season_id);

ALTER TABLE tomatoes
	ADD CONSTRAINT tomatoes__tomatoes_variety__fk
	FOREIGN KEY (variety_id) REFERENCES tomatoes_variety (variety_id);

ALTER TABLE tomatoes
	ADD CONSTRAINT tomatoes__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);


ALTER TABLE cucumbers_gardenbed
	ADD CONSTRAINT cucumbers_gardenbed__cucumbers_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES cucumbers_variety_specification (variety_specification_id);

ALTER TABLE cucumbers_variety_specification
	ADD CONSTRAINT cucumbers_variety_specification__cucumbers_variety__fk
	FOREIGN KEY (variety_id) REFERENCES cucumbers_variety (variety_id);

ALTER TABLE cucumbers_season_varieties
	ADD CONSTRAINT cucumbers_season_varieties__variety_specification__fk
	UNIQUE (variety_specification_id);

ALTER TABLE cucumbers_season_varieties
	ADD CONSTRAINT cucumbers_season_varieties__cucumbers_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES cucumbers_variety_specification (variety_specification_id);

ALTER TABLE cucumbers_season_varieties
	ADD CONSTRAINT cucumbers_season_varieties__cucumbers_season__fk
	FOREIGN KEY (season_id) REFERENCES cucumbers_season (season_id);

ALTER TABLE cucumbers_season_varieties
	ADD CONSTRAINT cucumbers_season_varieties__cucumbers_variety__fk
	FOREIGN KEY (variety_id) REFERENCES cucumbers_variety (variety_id);

ALTER TABLE cucumbers_variety
	ADD CONSTRAINT cucumbers_variety__cucumbers_season__fk
	FOREIGN KEY (season_id) REFERENCES cucumbers_season (season_id);

ALTER TABLE cucumbers
	ADD CONSTRAINT cucumbers__cucumbers_variety__fk
	FOREIGN KEY (variety_id) REFERENCES cucumbers_variety (variety_id);

ALTER TABLE cucumbers
	ADD CONSTRAINT cucumbers__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);


ALTER TABLE peppers_gardenbed
	ADD CONSTRAINT peppers_gardenbed__peppers_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES peppers_variety_specification (variety_specification_id);

ALTER TABLE peppers_variety_specification
	ADD CONSTRAINT peppers_variety_specification__peppers_variety__fk
	FOREIGN KEY (variety_id) REFERENCES peppers_variety (variety_id);

ALTER TABLE peppers_season_varieties
	ADD CONSTRAINT peppers_season_varieties__variety_specification__fk
	UNIQUE (variety_specification_id);

ALTER TABLE peppers_season_varieties
	ADD CONSTRAINT peppers_season_varieties__peppers_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES peppers_variety_specification (variety_specification_id);

ALTER TABLE peppers_season_varieties
	ADD CONSTRAINT peppers_season_varieties__peppers_season__fk
	FOREIGN KEY (season_id) REFERENCES peppers_season (season_id);

ALTER TABLE peppers_season_varieties
	ADD CONSTRAINT peppers_season_varieties__peppers_variety__fk
	FOREIGN KEY (variety_id) REFERENCES peppers_variety (variety_id);

ALTER TABLE peppers_variety
	ADD CONSTRAINT peppers_variety__peppers_season__fk
	FOREIGN KEY (season_id) REFERENCES peppers_season (season_id);

ALTER TABLE peppers
	ADD CONSTRAINT peppers__peppers_variety__fk
	FOREIGN KEY (variety_id) REFERENCES peppers_variety (variety_id);

ALTER TABLE peppers
	ADD CONSTRAINT peppers__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);


ALTER TABLE carrots_gardenbed
	ADD CONSTRAINT carrots_gardenbed__carrots_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES carrots_variety_specification (variety_specification_id);

ALTER TABLE carrots_variety_specification
	ADD CONSTRAINT carrots_variety_specification__carrots_variety__fk
	FOREIGN KEY (variety_id) REFERENCES carrots_variety (variety_id);

ALTER TABLE carrots_season_varieties
	ADD CONSTRAINT carrots_season_varieties__variety_specification__fk
	UNIQUE (variety_specification_id);

ALTER TABLE carrots_season_varieties
	ADD CONSTRAINT carrots_season_varieties__carrots_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES carrots_variety_specification (variety_specification_id);

ALTER TABLE carrots_season_varieties
	ADD CONSTRAINT carrots_season_varieties__carrots_season__fk
	FOREIGN KEY (season_id) REFERENCES carrots_season (season_id);

ALTER TABLE carrots_season_varieties
	ADD CONSTRAINT carrots_season_varieties__carrots_variety__fk
	FOREIGN KEY (variety_id) REFERENCES carrots_variety (variety_id);

ALTER TABLE carrots_variety
	ADD CONSTRAINT carrots_variety__carrots_season__fk
	FOREIGN KEY (season_id) REFERENCES carrots_season (season_id);

ALTER TABLE carrots
	ADD CONSTRAINT carrots__carrots_variety__fk
	FOREIGN KEY (variety_id) REFERENCES carrots_variety (variety_id);

ALTER TABLE carrots
	ADD CONSTRAINT carrots__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);


ALTER TABLE beetroots_gardenbed
	ADD CONSTRAINT beetroots_gardenbed__beetroots_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES beetroots_variety_specification (variety_specification_id);

ALTER TABLE beetroots_variety_specification
	ADD CONSTRAINT beetroots_variety_specification__beetroots_variety__fk
	FOREIGN KEY (variety_id) REFERENCES beetroots_variety (variety_id);

ALTER TABLE beetroots_season_varieties
	ADD CONSTRAINT beetroots_season_varieties__variety_specification__fk
	UNIQUE (variety_specification_id);

ALTER TABLE beetroots_season_varieties
	ADD CONSTRAINT beetroots_season_varieties__beetroots_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES beetroots_variety_specification (variety_specification_id);

ALTER TABLE beetroots_season_varieties
	ADD CONSTRAINT beetroots_season_varieties__beetroots_season__fk
	FOREIGN KEY (season_id) REFERENCES beetroots_season (season_id);

ALTER TABLE beetroots_season_varieties
	ADD CONSTRAINT beetroots_season_varieties__beetroots_variety__fk
	FOREIGN KEY (variety_id) REFERENCES beetroots_variety (variety_id);

ALTER TABLE beetroots_variety
	ADD CONSTRAINT beetroots_variety__beetroots_season__fk
	FOREIGN KEY (season_id) REFERENCES beetroots_season (season_id);

ALTER TABLE beetroots
	ADD CONSTRAINT beetroots__beetroots_variety__fk
	FOREIGN KEY (variety_id) REFERENCES beetroots_variety (variety_id);

ALTER TABLE beetroots
	ADD CONSTRAINT beetroots__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);


ALTER TABLE potatoes_gardenbed
	ADD CONSTRAINT potatoes_gardenbed__potatoes_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES potatoes_variety_specification (variety_specification_id);

ALTER TABLE potatoes_variety_specification
	ADD CONSTRAINT potatoes_variety_specification__potatoes_variety__fk
	FOREIGN KEY (variety_id) REFERENCES potatoes_variety (variety_id);

ALTER TABLE potatoes_season_varieties
	ADD CONSTRAINT potatoes_season_varieties__variety_specification__fk
	UNIQUE (variety_specification_id);

ALTER TABLE potatoes_season_varieties
	ADD CONSTRAINT potatoes_season_varieties__potatoes_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES potatoes_variety_specification (variety_specification_id);

ALTER TABLE potatoes_season_varieties
	ADD CONSTRAINT potatoes_season_varieties__potatoes_season__fk
	FOREIGN KEY (season_id) REFERENCES potatoes_season (season_id);

ALTER TABLE potatoes_season_varieties
	ADD CONSTRAINT potatoes_season_varieties__potatoes_variety__fk
	FOREIGN KEY (variety_id) REFERENCES potatoes_variety (variety_id);

ALTER TABLE potatoes_variety
	ADD CONSTRAINT potatoes_variety__potatoes_season__fk
	FOREIGN KEY (season_id) REFERENCES potatoes_season (season_id);

ALTER TABLE potatoes
	ADD CONSTRAINT potatoes__potatoes_variety__fk
	FOREIGN KEY (variety_id) REFERENCES potatoes_variety (variety_id);

ALTER TABLE potatoes
	ADD CONSTRAINT potatoes__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);


ALTER TABLE beans_gardenbed
	ADD CONSTRAINT beans_gardenbed__beans_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES beans_variety_specification (variety_specification_id);

ALTER TABLE beans_variety_specification
	ADD CONSTRAINT beans_variety_specification__beans_variety__fk
	FOREIGN KEY (variety_id) REFERENCES beans_variety (variety_id);

ALTER TABLE beans_season_varieties
	ADD CONSTRAINT beans_season_varieties__variety_specification__fk
	UNIQUE (variety_specification_id);

ALTER TABLE beans_season_varieties
	ADD CONSTRAINT beans_season_varieties__beans_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES beans_variety_specification (variety_specification_id);

ALTER TABLE beans_season_varieties
	ADD CONSTRAINT beans_season_varieties__beans_season__fk
	FOREIGN KEY (season_id) REFERENCES beans_season (season_id);

ALTER TABLE beans_season_varieties
	ADD CONSTRAINT beans_season_varieties__beans_variety__fk
	FOREIGN KEY (variety_id) REFERENCES beans_variety (variety_id);

ALTER TABLE beans_variety
	ADD CONSTRAINT beans_variety__beans_season__fk
	FOREIGN KEY (season_id) REFERENCES beans_season (season_id);

ALTER TABLE beans
	ADD CONSTRAINT beans__beans_variety__fk
	FOREIGN KEY (variety_id) REFERENCES beans_variety (variety_id);

ALTER TABLE beans
	ADD CONSTRAINT beans__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);


ALTER TABLE cabbage_gardenbed
	ADD CONSTRAINT cabbage_gardenbed__cabbage_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES cabbage_variety_specification (variety_specification_id);

ALTER TABLE cabbage_variety_specification
	ADD CONSTRAINT cabbage_variety_specification__cabbage_variety__fk
	FOREIGN KEY (variety_id) REFERENCES cabbage_variety (variety_id);

ALTER TABLE cabbage_season_varieties
	ADD CONSTRAINT cabbage_season_varieties__variety_specification__fk
	UNIQUE (variety_specification_id);

ALTER TABLE cabbage_season_varieties
	ADD CONSTRAINT cabbage_season_varieties__cabbage_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES cabbage_variety_specification (variety_specification_id);

ALTER TABLE cabbage_season_varieties
	ADD CONSTRAINT cabbage_season_varieties__cabbage_season__fk
	FOREIGN KEY (season_id) REFERENCES cabbage_season (season_id);

ALTER TABLE cabbage_season_varieties
	ADD CONSTRAINT cabbage_season_varieties__cabbage_variety__fk
	FOREIGN KEY (variety_id) REFERENCES cabbage_variety (variety_id);

ALTER TABLE cabbage_variety
	ADD CONSTRAINT cabbage_variety__cabbage_season__fk
	FOREIGN KEY (season_id) REFERENCES cabbage_season (season_id);

ALTER TABLE cabbage
	ADD CONSTRAINT cabbage__cabbage_variety__fk
	FOREIGN KEY (variety_id) REFERENCES cabbage_variety (variety_id);

ALTER TABLE cabbage
	ADD CONSTRAINT cabbage__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);


ALTER TABLE corn_gardenbed
	ADD CONSTRAINT corn_gardenbed__corn_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES corn_variety_specification (variety_specification_id);

ALTER TABLE corn_variety_specification
	ADD CONSTRAINT corn_variety_specification__corn_variety__fk
	FOREIGN KEY (variety_id) REFERENCES corn_variety (variety_id);

ALTER TABLE corn_season_varieties
	ADD CONSTRAINT corn_season_varieties__variety_specification__fk
	UNIQUE (variety_specification_id);

ALTER TABLE corn_season_varieties
	ADD CONSTRAINT corn_season_varieties__corn_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES corn_variety_specification (variety_specification_id);

ALTER TABLE corn_season_varieties
	ADD CONSTRAINT corn_season_varieties__corn_season__fk
	FOREIGN KEY (season_id) REFERENCES corn_season (season_id);

ALTER TABLE corn_season_varieties
	ADD CONSTRAINT corn_season_varieties__corn_variety__fk
	FOREIGN KEY (variety_id) REFERENCES corn_variety (variety_id);

ALTER TABLE corn_variety
	ADD CONSTRAINT corn_variety__corn_season__fk
	FOREIGN KEY (season_id) REFERENCES corn_season (season_id);

ALTER TABLE corn
	ADD CONSTRAINT corn__corn_variety__fk
	FOREIGN KEY (variety_id) REFERENCES corn_variety (variety_id);

ALTER TABLE corn
	ADD CONSTRAINT corn__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);


ALTER TABLE squash_gardenbed
	ADD CONSTRAINT squash_gardenbed__squash_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES squash_variety_specification (variety_specification_id);

ALTER TABLE squash_variety_specification
	ADD CONSTRAINT squash_variety_specification__squash_variety__fk
	FOREIGN KEY (variety_id) REFERENCES squash_variety (variety_id);

ALTER TABLE squash_season_varieties
	ADD CONSTRAINT squash_season_varieties__variety_specification__fk
	UNIQUE (variety_specification_id);

ALTER TABLE squash_season_varieties
	ADD CONSTRAINT squash_season_varieties__squash_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES squash_variety_specification (variety_specification_id);

ALTER TABLE squash_season_varieties
	ADD CONSTRAINT squash_season_varieties__squash_season__fk
	FOREIGN KEY (season_id) REFERENCES squash_season (season_id);

ALTER TABLE squash_season_varieties
	ADD CONSTRAINT squash_season_varieties__squash_variety__fk
	FOREIGN KEY (variety_id) REFERENCES squash_variety (variety_id);

ALTER TABLE squash_variety
	ADD CONSTRAINT squash_variety__squash_season__fk
	FOREIGN KEY (season_id) REFERENCES squash_season (season_id);

ALTER TABLE squash
	ADD CONSTRAINT squash__squash_variety__fk
	FOREIGN KEY (variety_id) REFERENCES squash_variety (variety_id);

ALTER TABLE squash
	ADD CONSTRAINT squash__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);


ALTER TABLE pumpkins_gardenbed
	ADD CONSTRAINT pumpkins_gardenbed__pumpkins_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES pumpkins_variety_specification (variety_specification_id);

ALTER TABLE pumpkins_variety_specification
	ADD CONSTRAINT pumpkins_variety_specification__pumpkins_variety__fk
	FOREIGN KEY (variety_id) REFERENCES pumpkins_variety (variety_id);

ALTER TABLE pumpkins_season_varieties
	ADD CONSTRAINT pumpkins_season_varieties__variety_specification__fk
	UNIQUE (variety_specification_id);

ALTER TABLE pumpkins_season_varieties
	ADD CONSTRAINT pumpkins_season_varieties__pumpkins_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES pumpkins_variety_specification (variety_specification_id);

ALTER TABLE pumpkins_season_varieties
	ADD CONSTRAINT pumpkins_season_varieties__pumpkins_season__fk
	FOREIGN KEY (season_id) REFERENCES pumpkins_season (season_id);

ALTER TABLE pumpkins_season_varieties
	ADD CONSTRAINT pumpkins_season_varieties__pumpkins_variety__fk
	FOREIGN KEY (variety_id) REFERENCES pumpkins_variety (variety_id);

ALTER TABLE pumpkins_variety
	ADD CONSTRAINT pumpkins_variety__pumpkins_season__fk
	FOREIGN KEY (season_id) REFERENCES pumpkins_season (season_id);

ALTER TABLE pumpkins
	ADD CONSTRAINT pumpkins__pumpkins_variety__fk
	FOREIGN KEY (variety_id) REFERENCES pumpkins_variety (variety_id);

ALTER TABLE pumpkins
	ADD CONSTRAINT pumpkins__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);


ALTER TABLE garlic_gardenbed
	ADD CONSTRAINT garlic_gardenbed__garlic_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES garlic_variety_specification (variety_specification_id);

ALTER TABLE garlic_variety_specification
	ADD CONSTRAINT garlic_variety_specification__garlic_variety__fk
	FOREIGN KEY (variety_id) REFERENCES garlic_variety (variety_id);

ALTER TABLE garlic_season_varieties
	ADD CONSTRAINT garlic_season_varieties__variety_specification__fk
	UNIQUE (variety_specification_id);

ALTER TABLE garlic_season_varieties
	ADD CONSTRAINT garlic_season_varieties__garlic_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES garlic_variety_specification (variety_specification_id);

ALTER TABLE garlic_season_varieties
	ADD CONSTRAINT garlic_season_varieties__garlic_season__fk
	FOREIGN KEY (season_id) REFERENCES garlic_season (season_id);

ALTER TABLE garlic_season_varieties
	ADD CONSTRAINT garlic_season_varieties__garlic_variety__fk
	FOREIGN KEY (variety_id) REFERENCES garlic_variety (variety_id);

ALTER TABLE garlic_variety
	ADD CONSTRAINT garlic_variety__garlic_season__fk
	FOREIGN KEY (season_id) REFERENCES garlic_season (season_id);

ALTER TABLE garlic
	ADD CONSTRAINT garlic__garlic_variety__fk
	FOREIGN KEY (variety_id) REFERENCES garlic_variety (variety_id);

ALTER TABLE garlic
	ADD CONSTRAINT garlic__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);


ALTER TABLE roots_gardenbed
	ADD CONSTRAINT roots_gardenbed__roots_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES roots_variety_specification (variety_specification_id);

ALTER TABLE roots_variety_specification
	ADD CONSTRAINT roots_variety_specification__roots_variety__fk
	FOREIGN KEY (variety_id) REFERENCES roots_variety (variety_id);

ALTER TABLE roots_season_varieties
	ADD CONSTRAINT roots_season_varieties__variety_specification__fk
	UNIQUE (variety_specification_id);

ALTER TABLE roots_season_varieties
	ADD CONSTRAINT roots_season_varieties__roots_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES roots_variety_specification (variety_specification_id);

ALTER TABLE roots_season_varieties
	ADD CONSTRAINT roots_season_varieties__roots_season__fk
	FOREIGN KEY (season_id) REFERENCES roots_season (season_id);

ALTER TABLE roots_season_varieties
	ADD CONSTRAINT roots_season_varieties__roots_variety__fk
	FOREIGN KEY (variety_id) REFERENCES roots_variety (variety_id);

ALTER TABLE roots_variety
	ADD CONSTRAINT roots_variety__roots_season__fk
	FOREIGN KEY (season_id) REFERENCES roots_season (season_id);

ALTER TABLE roots
	ADD CONSTRAINT roots__roots_variety__fk
	FOREIGN KEY (variety_id) REFERENCES roots_variety (variety_id);

ALTER TABLE roots
	ADD CONSTRAINT roots__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);


ALTER TABLE berries_gardenbed
	ADD CONSTRAINT berries_gardenbed__berries_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES berries_variety_specification (variety_specification_id);

ALTER TABLE berries_variety_specification
	ADD CONSTRAINT berries_variety_specification__berries_variety__fk
	FOREIGN KEY (variety_id) REFERENCES berries_variety (variety_id);

ALTER TABLE berries_season_varieties
	ADD CONSTRAINT berries_season_varieties__variety_specification__fk
	UNIQUE (variety_specification_id);

ALTER TABLE berries_season_varieties
	ADD CONSTRAINT berries_season_varieties__berries_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES berries_variety_specification (variety_specification_id);

ALTER TABLE berries_season_varieties
	ADD CONSTRAINT berries_season_varieties__berries_season__fk
	FOREIGN KEY (season_id) REFERENCES berries_season (season_id);

ALTER TABLE berries_season_varieties
	ADD CONSTRAINT berries_season_varieties__berries_variety__fk
	FOREIGN KEY (variety_id) REFERENCES berries_variety (variety_id);

ALTER TABLE berries_variety
	ADD CONSTRAINT berries_variety__berries_season__fk
	FOREIGN KEY (season_id) REFERENCES berries_season (season_id);

ALTER TABLE berries
	ADD CONSTRAINT berries__berries_variety__fk
	FOREIGN KEY (variety_id) REFERENCES berries_variety (variety_id);

ALTER TABLE berries
	ADD CONSTRAINT berries__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);


ALTER TABLE fruits_gardenbed
	ADD CONSTRAINT fruits_gardenbed__fruits_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES fruits_variety_specification (variety_specification_id);

ALTER TABLE fruits_variety_specification
	ADD CONSTRAINT fruits_variety_specification__fruits_variety__fk
	FOREIGN KEY (variety_id) REFERENCES fruits_variety (variety_id);

ALTER TABLE fruits_season_varieties
	ADD CONSTRAINT fruits_season_varieties__variety_specification__fk
	UNIQUE (variety_specification_id);

ALTER TABLE fruits_season_varieties
	ADD CONSTRAINT fruits_season_varieties__fruits_variety_specification__fk
	FOREIGN KEY (variety_specification_id) REFERENCES fruits_variety_specification (variety_specification_id);

ALTER TABLE fruits_season_varieties
	ADD CONSTRAINT fruits_season_varieties__fruits_season__fk
	FOREIGN KEY (season_id) REFERENCES fruits_season (season_id);

ALTER TABLE fruits_season_varieties
	ADD CONSTRAINT fruits_season_varieties__fruits_variety__fk
	FOREIGN KEY (variety_id) REFERENCES fruits_variety (variety_id);

ALTER TABLE fruits_variety
	ADD CONSTRAINT fruits_variety__fruits_season__fk
	FOREIGN KEY (season_id) REFERENCES fruits_season (season_id);

ALTER TABLE fruits
	ADD CONSTRAINT fruits__fruits_variety__fk
	FOREIGN KEY (variety_id) REFERENCES fruits_variety (variety_id);

ALTER TABLE fruits
	ADD CONSTRAINT fruits__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);
