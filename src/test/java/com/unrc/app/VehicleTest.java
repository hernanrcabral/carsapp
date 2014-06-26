package com.unrc.app;

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

        // Create Vehicle
        vehicle.set("patent", "HDK526", "kind", "307", "mark", "Fiat","user_id", "nuevo@gmail.com", "city_id","Rosario");
        vehicle.save();

//        vehicle.setParent(user);

        System.out.println(vehicle);

        // Everything is good:
        the(vehicle).shouldBe("valid");

    }
}


