package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Other extends Model {
	static{
		validatePresenceOf("c_other");
	}

	public String id (){
  		return String.valueOf(this.getInteger("id"));
  	}

	public String id_vehicle (){
  		return this.getString("id_vehicle");
  	}

  	public String c_other (){
  		return this.getString("c_other");
  	}
}

