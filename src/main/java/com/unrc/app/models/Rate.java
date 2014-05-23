package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Rate extends Model {
	static{
		validatePresenceOf("id_rate", "point","user_id","id_post");
	}
}

