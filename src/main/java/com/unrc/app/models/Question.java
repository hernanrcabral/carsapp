package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Question extends Model {
	static{
		validatePresenceOf("question");
	}
	
	public String id (){
  		return String.valueOf(this.getInteger("id"));
	}

  	public String user_id (){
  		return this.getString("user_id");
 	}

  	public String question (){
  		return this.getString("question");
  	}
	
	public String post_id (){
  		return this.getString("post_id");
  	}
}

