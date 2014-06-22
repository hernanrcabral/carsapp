package com.unrc.app;

import com.unrc.app.models.Car;
import com.unrc.app.models.User;

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
        User user = new User();
        
        user.set("id",1,"first_name", "John", "last_name", "Doe", "email", "example@email.com");
        user.save();
     
       // check errors
        the(car).shouldNotBe("valid");
        the(car.errors().get("id_vehicle")).shouldBeEqual("value is missing");
        the(car.errors().get("count_doors")).shouldBeEqual("value is missing");
        the(car.errors().get("id")).shouldBeEqual("value is missing");

        // Create Vehicle
        car.set("id",1, "id_vehicle", 1, "count_doors", 4);
        car.save();
        
        System.out.println(car);

        // Everything is good:
        the(car).shouldBe("valid");

    }
}