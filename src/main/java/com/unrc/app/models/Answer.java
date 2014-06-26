package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Answer extends Model {
	static{
		validatePresenceOf("user_id","answer","question_id");
	}

	public String id (){
  		return String.valueOf(this.getInteger("id"));
	}

  	public String user_id (){
  		return this.getString("user_id");
 	}

  	public String answer (){
  		return this.getString("answer");
  	}
	
	public String question_id (){
  		return this.getString("question_id");
  	}

}

