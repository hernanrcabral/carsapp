package com.unrc.app;

import com.unrc.app.models.Vehicle;
import com.unrc.app.models.Car;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;
import static org.junit.Assert.assertEquals;													


public class CarTest{
    @Before
    public void before(){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_test", "root", "root");
                                                          
        Base.openTransaction();
    }

    @After
    public void after(){
        System.out.println("CarTest tearDown");				
        Base.rollbackTransaction();
        Base.close();
    }

    @Test
    public void shouldValidateMandatoryFields(){
       Car car= new Car();

       // check errors
        the(car).shouldNotBe("valid");
        the(car.errors().get("id_vehicle")).shouldBeEqual("value is missing");
        the(car.errors().get("count_doors")).shouldBeEqual("value is missing");
		the(car.errors().get("id")).shouldBeEqual("value is missing");


        Vehicle vehicle= new Vehicle();
    	 vehicle.set("patent", "HDK526", "kind", "307", "mark", "Fiat", "description"," Combustible: Diesel, Color: Negro. Sin uso. Muy pocos kilómetros. Excelente estado. Alarma. Papeles al día. Listo para transferir. ", "status", "Sell", "price", 32.393, "user_id",1, "city_id", 1);      
		  vehicle.save();

		// Create Vehicle
    	 car.set("id",1, "id_vehicle", vehicle.get("patent"), "count_doors", 4);
         car.save();

		System.out.println(car);

        // Everything is good:
        the(car).shouldBe("valid");

    }
}


 

