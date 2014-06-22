package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Answer extends Model {
	static{
		validatePresenceOf("user_id","description","id","question_id");
	}
}

