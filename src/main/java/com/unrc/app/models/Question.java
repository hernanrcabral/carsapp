package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Question extends Model {
	static{
		validatePresenceOf("user_id", "id","description","post_id");
	}
}

