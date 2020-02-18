DROP schema if exists db_schema;
create schema db_schema;
use db_schema;


--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS messages;
CREATE TABLE messages (
  id int(11) NOT NULL AUTO_INCREMENT,
  user_id int(11) NOT NULL,
  text varchar(200) NOT NULL,
  date_message datetime NOT NULL,
  PRIMARY KEY (id),
  KEY fk_messages_1_idx (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Table structure for table `speciality`
--

DROP TABLE IF EXISTS speciality;
CREATE TABLE speciality (
  id_speciality int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  description varchar(400) NOT NULL,
  faculty varchar(45) NOT NULL,
  PRIMARY KEY (id_speciality)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id int(11) NOT NULL AUTO_INCREMENT,
  login varchar(45) NOT NULL,
  password varchar(45) NOT NULL,
  full_name varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  id_speciality varchar(45) DEFAULT NULL,
  test_score int(11) DEFAULT NULL,
  accepted varchar(45) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
