package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class City extends Model{
	static{
		validatePresenceOf("postal_code", "name");
	}

  	public String name (){
  		return this.getString("name");
 	}

	public String id (){
  		return String.valueOf(this.getInteger("id"));
  	}

  	public String postal_code (){
  		return String.valueOf(this.getInteger("postal_code"));
  	}


}

