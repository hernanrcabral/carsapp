package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Motocicle extends Model {
	static{
		validatePresenceOf("cylinder");
	}

	public String cylinder (){
		return String.valueOf(this.getInteger("cylinder"));

  	}
	public String id (){
  		return String.valueOf(this.getInteger("id"));
  	}

	public String id_vehicle (){
  		return this.getString("id_vehicle");
  	}
}

