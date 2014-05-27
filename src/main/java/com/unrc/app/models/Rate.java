package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Rate extends Model {
	static{
		validatePresenceOf("points","user_id","post_id");
	}
}

