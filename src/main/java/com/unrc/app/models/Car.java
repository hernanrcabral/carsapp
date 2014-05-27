package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Car extends Model {
	static{
		validatePresenceOf("id_car","count_doors","id_vehicle");
	}
}
