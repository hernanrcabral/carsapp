use CVVdatabase;
-- Integrantes: W - X - Y - Z

DROP TABLE IF EXISTS users; -- Usuarios
CREATE TABLE users(
    id INT(11) NOT NULL AUTO_INCREMENT,
    email VARCHAR(60) UNIQUE,
    first_name VARCHAR(56),
    last_name VARCHAR(56),
  CONSTRAINT users_pk PRIMARY KEY (id)
);

-- CREO LA BASE DE DATOS DE LAS CIUDADES QUE POSEEN EL VEHICULO 
drop table if exists cities;
create table cities(
	id int(11) not null auto_increment,
	postal_code integer(10) not null,	
	name varchar(40) not null,
	constraint pk_city primary key (id)
);

DROP TABLE IF EXISTS vehicles; 	-- Vehiculos
CREATE TABLE vehicles(
	patent VARCHAR (11) not null, 
	model VARCHAR(11) not null,
	mark  VARCHAR(11) not null,
    descripcion VARCHAR (40),
    type enum('Car','Truck','Motocycle','Others') not null,
   	price FLOAT not null,
	status enum('Sell','Rent') not null,
	user_id integer(11) not null references users(id),
	city_id int(11) not null references cities(id),
	constraint pk_vehicle primary key (patent)
);	
drop table if exists buildings;
create table buildings(
	id integer(100) not null auto_increment,
	type enum('Land','Farm','House','Departament','Garage') not null,	
	owner_id integer(100) not null references owners(id),
	real_estate_id integer(100) not null references real_estates(id),
	description varchar(400),
	price float not null,
	status enum('Sell','Rent') not null,
	street varchar(45) not null,
	neighborhood varchar(45) not null,
	city_id int(11) not null references cities(id),
	constraint pk_buildings primary key (id)
