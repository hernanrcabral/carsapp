package com.unrc.app;

import com.unrc.app.models.Vehicle;
import com.unrc.app.models.Truck;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;
import static org.junit.Assert.assertEquals;

public class TruckTest{
    @Before
    public void before(){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_test", "root", "root");
        Base.openTransaction();
    }

    @After
    public void after(){
        //System.out.println("TruckTest tearDown");
        Base.rollbackTransaction();
        Base.close();
    }
 @Test
    public void shouldValidateMandatoryFields(){
        Truck truck= new Truck();

       // check errors
        the(truck).shouldNotBe("valid");
        the(truck.errors().get("id_truck")).shouldBeEqual("value is missing");        
        the(truck.errors().get("count_belt")).shouldBeEqual("value is missing");
        the(truck.errors().get("id_vehicle")).shouldBeEqual("value is missing");
        
        User user = new User();
        user.set("first_name", "John", "last_name", "Doe", "email", "example@email.com");
        user.save();

		// Create truck
    	 Truck.set("id_Truck","1","count_belt","4","id_vehicle", vehicle.get("id"));

         truck.save();

   System.out.println(truck);
   System.out.println(truck.parent(Vehicle.class));


        // Everything is good:
        the(truck).shouldBe("valid");

    }
}

