use CVVdatabase;
-- Integrantes: Garcia Silvia - Bueno Maria Victoria - Zabala Ana Carolina - Gomez Seyla Damaris

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
	status enum('Sell','Buy') not null,
	user_id integer(11) not null references users(id),
	city_id int(11) not null references cities(id),
	constraint pk_vehicle primary key (patent)
);	

