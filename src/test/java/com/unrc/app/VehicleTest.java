package com.unrc.app;

import com.unrc.app.models.User;
import com.unrc.app.models.Vehicle;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;
import static org.junit.Assert.assertEquals;													


public class VehicleTest{
    @Before
    public void before(){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_test", "root", "root");
                                                          
        Base.openTransaction();
    }

    @After
    public void after(){
        System.out.println("VehicleTest tearDown");				
        Base.rollbackTransaction();
        Base.close();
    }

    @Test
    public void shouldValidateMandatoryFields(){
       Vehicle vehicle= new Vehicle();

       // check errors
        the(vehicle).shouldNotBe("valid");
        the(vehicle.errors().get("patent")).shouldBeEqual("value is missing");
        the(vehicle.errors().get("kind")).shouldBeEqual("value is missing");
		the(vehicle.errors().get("mark")).shouldBeEqual("value is missing");
        the(vehicle.errors().get("description")).shouldBeEqual("value is missing");
        the(vehicle.errors().get("type")).shouldBeEqual("value is missing");
        the(vehicle.errors().get("status")).shouldBeEqual("value is missing");  
        the(vehicle.errors().get("price")).shouldBeEqual("value is missing");
        the(vehicle.errors().get("user_id")).shouldBeEqual("value is missing");
        the(vehicle.errors().get("city_id")).shouldBeEqual("value is missing");


        User user = new User();
        user.set("first_name", "John", "last_name", "Doe", "email", "example@email.com");
        user.save();

//	City city =new City();
//		city.set ("name", "General Deheza", "postal_code", 5923);
//		city.save();

		// Create Vehicle
    	 vehicle.set("patent", "HDK526", "kind", "307", "mark", "Fiat", "type", "Car", "description"," Combustible: Diesel, Color: Negro, Transmisión: Manual,  Puertas: 4, Dirección: Hidráulica. Impecable. Sin uso. Muy pocos kilómetros. Excelente estado.  Doble airbags, frenos a disco en las cuatro ruedas, levanta cristales delanteros y traseros, alarma, llantas de aleación. Papeles al día. Listo para transferir. ", "status", "Sell", "price", 32.393, "user_id", user.get("id"), "city_id", 1);

        vehicle.save();

   System.out.println(vehicle);
   System.out.println(vehicle.parent(User.class));

        // Everything is good:
        the(vehicle).shouldBe("valid");

    }
}

