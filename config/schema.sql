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


