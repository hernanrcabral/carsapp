package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Post extends Model {
	static{
		validatePresenceOf("description","vehicle_id", "user_id");
	}
}

