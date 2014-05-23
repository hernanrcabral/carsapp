package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Vehicle extends Model {
	static{
		validatePresenceOf("user_id", "id_question""description","id_answer");
	}
}

