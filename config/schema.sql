--
-- Integrantes: Garcia Silvia - Bueno Maria Victoria - Zabala Ana Carolina - Gomez Seyla Damaris
--
drop database if exists carsapp_development;
create database if not exists carsapp_development;

DROP TABLE IF EXISTS carsapp_development.users; -- Usuarios
CREATE TABLE carsapp_development.users(
    id INT(11) NOT NULL AUTO_INCREMENT,
    email VARCHAR(60) UNIQUE,
    first_name VARCHAR(56),
    last_name VARCHAR(56),
	pass VARCHAR(11),
  CONSTRAINT users_pk PRIMARY KEY (id)
);


drop table if exists carsapp_development.cities; -- Ciudad
create table carsapp_development.cities(
	id int(11) not null auto_increment,
	postal_code int(11) not null,	
	name varchar(60) not null,
	constraint pk_city primary key (id)
);

DROP TABLE IF EXISTS carsapp_development.vehicles; 	-- Vehiculos
CREATE TABLE carsapp_development.vehicles(
	patent VARCHAR (11) not null, 
	kind VARCHAR(11) not null,
	mark  VARCHAR(11) not null,
    description VARCHAR (400),
    type VARCHAR(40) not null,
   	price DECIMAL not null,
	status VARCHAR(40) not null,
	user_id int (11) not null references users(id),
	city_id int(11) not null references cities(id),
	constraint pk_vehicle primary key (patent)
);	

DROP TABLE IF EXISTS carsapp_development.answers;  -- Crea Tabla de Respuesta
CREATE TABLE carsapp_development.answers
(
	id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
	description VARCHAR(200),
	user_id INT(11) NOT NULL REFERENCES users(id) 
);

DROP TABLE IF EXISTS carsapp_development.questions; -- Crea Tabla de Pregunta
CREATE TABLE carsapp_development.questions
(
	id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
	description VARCHAR(200) NOT NULL,
	user_id INT(11) NOT NULL REFERENCES users(id),
	answer_id INT(11) NOT NULL REFERENCES answers(id)
);

DROP TABLE IF EXISTS carsapp_development.posts;		-- Crea Tabla de Publicaciones
CREATE TABLE carsapp_development.posts(
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    description VARCHAR(400) NOT NULL,
    user_id INT(11) NOT NULL REFERENCES users(id),
    question_id  INT(11) NOT NULL REFERENCES questions(id),
	vehicle_id INT(11) NOT NULL REFERENCES vehicles(patent)   	
);

DROP TABLE IF EXISTS carsapp_development.rates; -- Crea Tabla de Calificación de las publicaciones
CREATE TABLE carsapp_development.rates( 
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    points INT(11) NOT NULL,
    post_id INT(11) NOT NULL REFERENCES posts(id),
    user_id INT(11) NOT NULL REFERENCES users(id)	
);

-- 
--			DATABASE CARSAPP_TEST
--
--
drop database if exists carsapp_test;
create database if not exists carsapp_test;

DROP TABLE IF EXISTS carsapp_test.users; -- Usuarios
CREATE TABLE carsapp_test.users(
    id INT(11) NOT NULL AUTO_INCREMENT,
    email VARCHAR(60) UNIQUE,
    first_name VARCHAR(56),
    last_name VARCHAR(56),
	pass VARCHAR(11),
  CONSTRAINT users_pk PRIMARY KEY (id)
);

-- CREO LA BASE DE DATOS DE LAS CIUDADES QUE POSEEN EL VEHICULO 
drop table if exists carsapp_test.cities;
create table carsapp_test.cities(
	id int(11) not null auto_increment,
	postal_code int(11) not null,	
	name varchar(60) not null,
	constraint pk_city primary key (id)
);

DROP TABLE IF EXISTS carsapp_test.vehicles; 	-- Vehiculos
CREATE TABLE carsapp_test.vehicles(
	patent VARCHAR (11) not null, 
	kind VARCHAR(11) not null,
	mark  VARCHAR(11) not null,
    description VARCHAR (400),
    type VARCHAR(40) not null,
   	price DECIMAL not null,
	status VARCHAR(40) not null,
	user_id int (11) not null references users(id),
	city_id int(11) not null references cities(id),
	constraint pk_vehicle primary key (patent)
);	

DROP TABLE IF EXISTS carsapp_test.answers;-- Crea Tabla de Respuesta
CREATE TABLE carsapp_test.answers(
	id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
	description VARCHAR(200),
	user_id INT(11) NOT NULL REFERENCES users(id) 
);

DROP TABLE IF EXISTS carsapp_test.questions; -- Crea Tabla de Pregunta
CREATE TABLE carsapp_test.questions(
	id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
	description VARCHAR(200) NOT NULL,
	user_id INT(11) NOT NULL REFERENCES users(id),
	answer_id INT(11) NOT NULL REFERENCES answers(id)
);

DROP TABLE IF EXISTS carsapp_test.posts;-- Crea Tabla de Publicaciones
CREATE TABLE carsapp_test.posts(
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    description VARCHAR(400) NOT NULL,
    user_id INT(11) NOT NULL REFERENCES users(id),
    question_id  INT(11) NOT NULL REFERENCES questions(id),
	vehicle_id INT(11) NOT NULL REFERENCES vehicles(patent)   	
);

DROP TABLE IF EXISTS carsapp_test.rates; -- Crea Tabla de Calificación de las publicaciones
CREATE TABLE carsapp_test.rates( 
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    points INT(11) NOT NULL,
    post_id INT(11) NOT NULL REFERENCES posts(id),
    user_id INT(11) NOT NULL REFERENCES users(id)	
);

