CREATE TABLE user (
	user_id int(11) NOT NULL auto_increment,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	email varchar(255) NOT NULL UNIQUE,
	password varchar(255) NOT NULL,
	active bit,
	activation_code varchar(255),
	primary key (user_id)
) engine = InnoDB;

CREATE TABLE access_level (
	user_id int(11) NOT NULL,
	access_levels varchar(255)
) engine = InnoDB;

CREATE TABLE harvesting_date (
  date_id int(11) NOT NULL AUTO_INCREMENT,
  date date,
  PRIMARY KEY (date_id)
)
ENGINE = InnoDB;

CREATE TABLE place (
  id int(11) NOT NULL,
  place varchar(255)
)
ENGINE = InnoDB;

CREATE TABLE tomatoes_variety (
  variety_id int(11) NOT NULL,
  name varchar(255),
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE tomatoes (
  id int(11) NOT NULL,
  date_id int(11) NOT NULL,
  variety_id int(11) NOT NULL,
  quantity int(11),
  weight int(11),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;

CREATE TABLE cucumbers_variety (
  variety_id int(11) NOT NULL,
  name varchar(255),
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE cucumbers (
  id int(11) NOT NULL,
  date_id int(11) NOT NULL,
  variety_id int(11) NOT NULL,
  quantity int(11),
  weight int(11),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;

CREATE TABLE peppers_variety (
  variety_id int(11) NOT NULL,
  name varchar(255),
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE peppers (
  id int(11) NOT NULL,
  date_id int(11) NOT NULL,
  variety_id int(11) NOT NULL,
  quantity int(11),
  weight int(11),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;

CREATE TABLE carrots_variety (
  variety_id int(11) NOT NULL,
  name varchar(255),
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE carrots (
  id int(11) NOT NULL,
  date_id int(11) NOT NULL,
  variety_id int(11) NOT NULL,
  quantity int(11),
  weight int(11),
  size varchar(255),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;

CREATE TABLE beetroots_variety (
  variety_id int(11) NOT NULL,
  name varchar(255),
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE beetroots (
  id int(11) NOT NULL,
  date_id int(11) NOT NULL,
  variety_id int(11) NOT NULL,
  quantity int(11),
  weight int(11),
  size varchar(255),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;

CREATE TABLE potatoes_variety (
  variety_id int(11) NOT NULL,
  name varchar(255),
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE potatoes (
  id int(11) NOT NULL,
  date_id int(11) NOT NULL,
  variety_id int(11) NOT NULL,
  quantity int(11),
  weight int(11),
  size varchar(255),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;

CREATE TABLE beans_variety (
  variety_id int(11) NOT NULL,
  name varchar(255),
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE beans (
  id int(11) NOT NULL,
  date_id int(11) NOT NULL,
  variety_id int(11) NOT NULL,
  quantity int(11),
  weight int(11),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;

CREATE TABLE cabbage_variety (
  variety_id int(11) NOT NULL,
  name varchar(255),
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE cabbage (
  id int(11) NOT NULL,
  date_id int(11) NOT NULL,
  variety_id int(11) NOT NULL,
  quantity int(11),
  weight int(11),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;

CREATE TABLE corn_variety (
  variety_id int(11) NOT NULL,
  name varchar(255),
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE corn (
  id int(11) NOT NULL,
  date_id int(11) NOT NULL,
  variety_id int(11) NOT NULL,
  quantity int(11),
  weight int(11),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;

CREATE TABLE squash_variety (
  variety_id int(11) NOT NULL,
  name varchar(255),
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE squash (
  id int(11) NOT NULL,
  date_id int(11) NOT NULL,
  variety_id int(11) NOT NULL,
  weight int(11),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;

CREATE TABLE pumpkins_variety (
  variety_id int(11) NOT NULL,
  name varchar(255),
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE pumpkins (
  id int(11) NOT NULL,
  date_id int(11) NOT NULL,
  variety_id int(11) NOT NULL,
  weight int(11),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;

CREATE TABLE garlic_variety (
  variety_id int(11) NOT NULL,
  name varchar(255),
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE garlic (
  id int(11) NOT NULL,
  date_id int(11) NOT NULL,
  variety_id int(11) NOT NULL,
  quantity int(11),
  weight int(11),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;

CREATE TABLE roots_variety (
  variety_id int(11) NOT NULL,
  name varchar(255),
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE roots (
  id int(11) NOT NULL,
  date_id int(11) NOT NULL,
  variety_id int(11) NOT NULL,
  quantity int(11),
  weight int(11),
  size varchar(255),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;

CREATE TABLE berries_variety (
  variety_id int(11) NOT NULL,
  name varchar(255),
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE berries (
  id int(11) NOT NULL,
  date_id int(11) NOT NULL,
  variety_id int(11) NOT NULL,
  weight int(11),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;

CREATE TABLE fruits_variety (
  variety_id int(11) NOT NULL,
  name varchar(255),
  PRIMARY KEY (variety_id)
)
ENGINE = InnoDB;

CREATE TABLE fruits (
  id int(11) NOT NULL,
  date_id int(11) NOT NULL,
  variety_id int(11) NOT NULL,
  weight int(11),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;

CREATE TABLE hibernate_sequence (
  next_val bigint(20) DEFAULT NULL
)
ENGINE = InnoDB;

ALTER TABLE access_level
	ADD CONSTRAINT access_level__user__fk
	FOREIGN KEY (user_id) REFERENCES user (user_id);

ALTER TABLE tomatoes
	ADD CONSTRAINT tomatoes__tomatoes_variety__fk
	FOREIGN KEY (variety_id) REFERENCES tomatoes_variety (variety_id);

ALTER TABLE tomatoes
	ADD CONSTRAINT tomatoes__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);

ALTER TABLE cucumbers
	ADD CONSTRAINT cucumbers__cucumbers_variety__fk
	FOREIGN KEY (variety_id) REFERENCES cucumbers_variety (variety_id);

ALTER TABLE cucumbers
	ADD CONSTRAINT cucumbers__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);

ALTER TABLE peppers
	ADD CONSTRAINT peppers__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);

ALTER TABLE peppers
	ADD CONSTRAINT peppers__peppers_variety__fk
	FOREIGN KEY (variety_id) REFERENCES peppers_variety (variety_id);

ALTER TABLE carrots
	ADD CONSTRAINT carrots__carrots_variety__fk
	FOREIGN KEY (variety_id) REFERENCES carrots_variety (variety_id);

ALTER TABLE carrots
	ADD CONSTRAINT carrots__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);
	
ALTER TABLE beetroots
	ADD CONSTRAINT beetroots__beetroots_variety__fk
	FOREIGN KEY (variety_id) REFERENCES beetroots_variety (variety_id);

ALTER TABLE beetroots
	ADD CONSTRAINT beetroots__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);
	
ALTER TABLE potatoes
	ADD CONSTRAINT potatoes__potatoes_variety__fk
	FOREIGN KEY (variety_id) REFERENCES potatoes_variety (variety_id);

ALTER TABLE potatoes
	ADD CONSTRAINT potatoes__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);

ALTER TABLE beans
	ADD CONSTRAINT beans__beans_variety__fk
	FOREIGN KEY (variety_id) REFERENCES beans_variety (variety_id);

ALTER TABLE beans
	ADD CONSTRAINT beans__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);

ALTER TABLE cabbage
	ADD CONSTRAINT cabbage__cabbage_variety__fk
	FOREIGN KEY (variety_id) REFERENCES cabbage_variety (variety_id);

ALTER TABLE cabbage
	ADD CONSTRAINT cabbage__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);

ALTER TABLE corn
	ADD CONSTRAINT corn__corn_variety__fk
	FOREIGN KEY (variety_id) REFERENCES corn_variety (variety_id);

ALTER TABLE corn
	ADD CONSTRAINT corn__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);

ALTER TABLE squash
	ADD CONSTRAINT squash__squash_variety__fk
	FOREIGN KEY (variety_id) REFERENCES squash_variety (variety_id);

ALTER TABLE squash
	ADD CONSTRAINT squash__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);

ALTER TABLE pumpkins
	ADD CONSTRAINT pumpkins__pumpkins_variety__fk
	FOREIGN KEY (variety_id) REFERENCES pumpkins_variety (variety_id);
	
ALTER TABLE pumpkins
	ADD CONSTRAINT pumpkins__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);

ALTER TABLE garlic
	ADD CONSTRAINT garlic__garlic_variety__fk
	FOREIGN KEY (variety_id) REFERENCES garlic_variety (variety_id);

ALTER TABLE garlic
	ADD CONSTRAINT garlic__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);

ALTER TABLE roots
	ADD CONSTRAINT roots__roots_variety__fk
	FOREIGN KEY (variety_id) REFERENCES roots_variety (variety_id);

ALTER TABLE roots
	ADD CONSTRAINT roots__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);

ALTER TABLE berries
	ADD CONSTRAINT berries__berries_variety__fk
	FOREIGN KEY (variety_id) REFERENCES berries_variety (variety_id);

ALTER TABLE berries
	ADD CONSTRAINT berries__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);

ALTER TABLE fruits
	ADD CONSTRAINT fruits__fruits_variety__fk
	FOREIGN KEY (variety_id) REFERENCES fruits_variety (variety_id);

ALTER TABLE fruits
	ADD CONSTRAINT fruits__harvesting_date__fk
	FOREIGN KEY (date_id) REFERENCES harvesting_date (date_id);
