package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Truck extends Model {
	static{
		validatePresenceOf("count_belt");
	}

		public String count_belt (){
		String cant = "No Ingreso la cantidad de cinturones.";

		if(this.getString("count_belt").charAt(0)=='1'){
            cant="1";
        }
        if(this.getString("count_belt").charAt(0)=='2'){
           	cant="2";
        } 
        if(this.getString("count_belt").charAt(0)=='3'){
       		cant="3";
        }
       	if(this.getString("count_belt").charAt(0)=='4'){
        	cant="4";
       	}
       	if(this.getString("count_belt").charAt(0)=='5'){
         	cant="5";
       	}
       	if(this.getString("count_belt").charAt(0)=='6'){
        	cant="6";
       	}
       	if(this.getString("count_belt").charAt(0)=='7'){
        	cant="Mas de 6 cinturones";
       	}
       	
     	
  		return cant;
  	}
	public String id (){
  		return String.valueOf(this.getInteger("id"));
  	}

	public String id_vehicle (){
  		return this.getString("id_vehicle");
  	}
}

