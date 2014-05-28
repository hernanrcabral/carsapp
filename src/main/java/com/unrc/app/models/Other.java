package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Other extends Model {
	static{
		validatePresenceOf("id","c_other","id_vehicle");
	}
}

