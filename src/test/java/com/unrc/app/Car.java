package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Car extends Vehicle {
	 public void save(){
		this.type = "Car";
		super();
	}
}

