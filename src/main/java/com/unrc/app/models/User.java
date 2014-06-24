package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class User extends Model {
 static {
      validatePresenceOf("first_name", "last_name","email");
  }
  
  	public String email (){
  		return this.getString("email");
 	}

	public String id (){
  		return String.valueOf(this.getInteger("id"));
  	}

  	public String first_name (){
  		return this.getString("first_name");
  	}

}

