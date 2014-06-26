package com.unrc.app;

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
        the(car.errors().get("count_doors")).shouldBeEqual("value is missing");

        // Create Vehicle
        car.set("id_vehicle", "ASD123", "count_doors", 4);
        car.save();
        
        System.out.println(car);

        // Everything is good:
        the(car).shouldBe("valid");

    }
}