package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Vehicle extends Model {
	static{
		validatePresenceOf("user_id", "city_id","status","type","patent","kind","mark","description","price");
	}
}

