package com.unrc.app;

import com.unrc.app.models.City;

public class CVVCity {

	public static void crearCiudad(int codPostal, String name){
		City buscar= City.findFirst("postal_code=?",codPostal);
		if(buscar==null){																	// Busco la cuidad si no no esta la creo
			City.createIt("postal_code",codPostal, "name", name);
		}
	}

	public static void modifCiudad(int cdPostal, String name){
		City aModif= City.findFirst("postal_code=?", cdPostal);
		aModif.set("name=?", name);
		aModif.saveIt();
	}

	public static void removeCiudad(int pc){
		City c= City.findFirst("postal_code=?", pc);
		c.delete();
	}

}
