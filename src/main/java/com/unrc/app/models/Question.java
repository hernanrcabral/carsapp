package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Question extends Model {
	static{
<<<<<<< HEAD
		validatePresenceOf("user_id", "id","description","answer_id");
=======
		validatePresenceOf("user_id", "id_question","description","id_answer");
>>>>>>> 5688ed90c88ec60d4b3612724681777ea21ecbe3
	}
}

