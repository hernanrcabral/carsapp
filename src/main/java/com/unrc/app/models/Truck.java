package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Truck extends Model {
	static{
		validatePresenceOf("id_truck","count_belt","id_vehicle");
	}
}
