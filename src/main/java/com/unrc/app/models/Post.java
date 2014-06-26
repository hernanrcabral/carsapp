package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Post extends Model {
	static{
		validatePresenceOf("description");
	}

	public String id (){
  		return String.valueOf(this.getInteger("id"));
	}

  	public String user_id (){
  		return this.getString("user_id");
 	}

  	public String vehicle_id (){
  		return this.getString("vehicle_id");
  	}
	
	public String description (){
  		return this.getString("description");
  	}

}

