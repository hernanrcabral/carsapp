package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Vehicle extends Model {
	static{
		validatePresenceOf("city_id","user_id","patent","kind","mark");
	}
}



