--
-- Integrantes: Garcia Silvia - Bueno Maria Victoria - Zabala Ana Carolina - Gomez Seyla Damaris
--
drop database if exists carsapp_development;
create database carsapp_development;

DROP TABLE IF EXISTS carsapp_development.users; -- Usuarios
CREATE TABLE carsapp_development.users(
    id INT(11) NOT NULL AUTO_INCREMENT,
    email VARCHAR(60) UNIQUE,
    first_name VARCHAR(56),
    last_name VARCHAR(56),
  CONSTRAINT users_pk PRIMARY KEY (id)
);
ALTER TABLE carsapp_development.users ADD role VARCHAR(20) NOT NULL;
ALTER TABLE carsapp_development.users ADD password VARCHAR(20) NOT NULL;

drop table if exists carsapp_development.cities; -- Ciudad
create table carsapp_development.cities(
    id int(11) not null auto_increment,
    postal_code int(11) UNIQUE,   
    name varchar(60) not null,
    constraint pk_city primary key (id)
);

DROP TABLE IF EXISTS carsapp_development.vehicles;  -- Vehiculos
CREATE TABLE carsapp_development.vehicles(
    patent VARCHAR (11) not null, 
    kind VARCHAR(11) not null,
    mark  VARCHAR(11) not null,
    user_id VARCHAR(60) not null references users(email),
    city_id VARCHAR(60) not null references cities(name),
    constraint pk_vehicle primary key (patent)
);  

DROP TABLE IF EXISTS carsapp_development.questions;  -- Crea Tabla de Respuesta
CREATE TABLE carsapp_development.questions
(
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    question VARCHAR(200) not null,
    post_id VARCHAR(400) NOT NULL REFERENCES posts(description),
    user_id VARCHAR(60) NOT NULL REFERENCES users(email) 
);


DROP TABLE IF EXISTS carsapp_development.answers;  -- Crea Tabla de Respuesta
CREATE TABLE carsapp_development.answers
(
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    answer VARCHAR(200),
    question_id  VARCHAR(200) NOT NULL REFERENCES questions(question),
    user_id VARCHAR(60) NOT NULL REFERENCES users(email) 
);

DROP TABLE IF EXISTS carsapp_development.posts;     -- Crea Tabla de Publicaciones
CREATE TABLE carsapp_development.posts(
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    description VARCHAR(400) NOT NULL,
    user_id VARCHAR(60) NOT NULL REFERENCES users(email),
    vehicle_id VARCHAR (11) NOT NULL REFERENCES vehicles(patent)     
);


DROP TABLE IF EXISTS carssapp_development.cars; -- Crea Tabla Automovil
CREATE TABLE carsapp_development.cars(
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    count_doors VARCHAR(11),   
    id_vehicle VARCHAR(11) NOT NULL REFERENCES vehicles(patent)
    
);

DROP TABLE IF EXISTS carsapp_development.trucks; -- Crea Tabla Camionetas
CREATE TABLE carsapp_development.trucks(
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    count_belt VARCHAR(11),
    id_vehicle VARCHAR(11) NOT NULL REFERENCES vehicles(patent)
);

DROP TABLE IF EXISTS carsapp_development.motocicles; -- Crea Tabla Motocicletas
CREATE TABLE carsapp_development.motocicles(
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    cylinder VARCHAR(11),
    id_vehicle VARCHAR(11) NOT NULL REFERENCES vehicles(patent)
);

DROP TABLE IF EXISTS carsapp_development.others; -- Crea Tabla De Otros vehiculos 
CREATE TABLE carsapp_development.others(
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    c_other VARCHAR(200),
    id_vehicle VARCHAR(11) NOT NULL REFERENCES vehicles(patent)
);

-- 
--          DATABASE CARSAPP_TEST
--
--
drop database if exists carsapp_test;
create database carsapp_test;

DROP TABLE IF EXISTS carsapp_test.users; -- Usuarios
CREATE TABLE carsapp_test.users(
    id INT(11) NOT NULL AUTO_INCREMENT,
    email VARCHAR(60) UNIQUE,
    first_name VARCHAR(56),
    last_name VARCHAR(56),
  CONSTRAINT users_pk PRIMARY KEY (id)
);
ALTER TABLE carsapp_test.users ADD role VARCHAR(20) NOT NULL; 
ALTER TABLE carsapp_test.users ADD password VARCHAR(20) NOT NULL;

-- CREO LA BASE DE DATOS DE LAS CIUDADES QUE POSEEN EL VEHICULO 
drop table if exists carsapp_test.cities;
create table carsapp_test.cities(
    id int(11) not null auto_increment,
    postal_code int(11) not null,   
    name varchar(60) not null,
    constraint pk_city primary key (id)
);

DROP TABLE IF EXISTS carsapp_test.vehicles;     -- Vehiculos
CREATE TABLE carsapp_test.vehicles(
    patent VARCHAR (11) not null, 
    kind VARCHAR(11) not null,
    mark  VARCHAR(11) not null,
    user_id VARCHAR(60) not null references users(email),
    city_id VARCHAR(60) not null references cities(name),
    constraint pk_vehicle primary key (patent)
);  


DROP TABLE IF EXISTS carsapp_test.answers;  -- Crea Tabla de Respuesta
CREATE TABLE carsapp_test.answers(
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    answer VARCHAR(200),
    question_id  VARCHAR(200) REFERENCES questions(question),
    user_id VARCHAR(60) NOT NULL REFERENCES users(email) 
);
DROP TABLE IF EXISTS carsapp_test.questions; -- Crea Tabla de Pregunta
CREATE TABLE carsapp_test.questions(
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    question VARCHAR(200) NOT NULL,
    user_id VARCHAR(60) NOT NULL REFERENCES users(email),
    post_id VARCHAR(400) NOT NULL REFERENCES posts(description)   
);

DROP TABLE IF EXISTS carsapp_test.posts;-- Crea Tabla de Publicaciones
CREATE TABLE carsapp_test.posts(
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    description VARCHAR(400) NOT NULL,
    user_id VARCHAR(60) NOT NULL REFERENCES users(email),
    vehicle_id VARCHAR(11) NOT NULL REFERENCES vehicles(patent)     
);


DROP TABLE IF EXISTS carssapp_test.cars; -- Crea Tabla Automovil
CREATE TABLE carsapp_test.cars(
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    count_doors VARCHAR(11),   
    id_vehicle VARCHAR(11) NOT NULL REFERENCES vehicles(patent)
    
);

DROP TABLE IF EXISTS carsapp_test.trucks; -- Crea Tabla Camionetas
CREATE TABLE carsapp_test.trucks(
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    count_belt VARCHAR(11),
    id_vehicle VARCHAR(11) NOT NULL REFERENCES vehicles(patent)
);

DROP TABLE IF EXISTS carsapp_test.motocicles; -- Crea Tabla Motocicletas
CREATE TABLE carsapp_test.motocicles(
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    cylinder INT(11),
    id_vehicle VARCHAR(11) NOT NULL REFERENCES vehicles(patent)
);

DROP TABLE IF EXISTS carsapp_test.others; -- Crea Tabla de otro vehiculo
CREATE TABLE carsapp_test.others(
    id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    c_other VARCHAR(50),
    id_vehicle VARCHAR(11) NOT NULL REFERENCES vehicles(patent)
);
