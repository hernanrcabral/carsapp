package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Motocicle extends Model {
	static{
		validatePresenceOf("id","cylinder","id_vehicle");
	}
}

