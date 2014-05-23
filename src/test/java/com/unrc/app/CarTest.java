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
        //System.out.println("CarTest tearDown");
        Base.rollbackTransaction();
        Base.close();
    }
 @Test
    public void shouldValidateMandatoryFields(){
        Car car= new Car();

       // check errors
        the(car).shouldNotBe("valid");
        the(car.errors().get("id_car")).shouldBeEqual("value is missing");        
        the(car.errors().get("count_doors")).shouldBeEqual("value is missing");
        the(car.errors().get("id_vehicle")).shouldBeEqual("value is missing");
        
        User user = new User();
        user.set("first_name", "John", "last_name", "Doe", "email", "example@email.com");
        user.save();

		// Create car
    	 Car.set("id_car","1","count_doors","4","id_vehicle", vehicle.get("id"));

         car.save();

   System.out.println(car);
   System.out.println(car.parent(Vehicle.class));


        // Everything is good:
        the(car).shouldBe("valid");

    }
}

