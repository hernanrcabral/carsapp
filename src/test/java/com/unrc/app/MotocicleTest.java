package com.unrc.app;

import com.unrc.app.models.Vehicle;
import com.unrc.app.models.Motocicle;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;
import static org.junit.Assert.assertEquals;

public class MotocicleTest{
    @Before
    public void before(){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_test", "root", "root");
        Base.openTransaction();
    }

    @After
    public void after(){
        //System.out.println("MotocicleTest tearDown");
        Base.rollbackTransaction();
        Base.close();
    }
 @Test
    public void shouldValidateMandatoryFields(){
        Motocicle motocicle= new Motocicle();

       // check errors
        the(motocicle).shouldNotBe("valid");
        the(motocicle.errors().get("id_motocicle")).shouldBeEqual("value is missing");        
        the(motocicle.errors().get("cylinder")).shouldBeEqual("value is missing");
        the(motocicle.errors().get("id_vehicle")).shouldBeEqual("value is missing");
        
        User user = new User();
        user.set("first_name", "John", "last_name", "Doe", "email", "example@email.com");
        user.save();

		// Create motocicle
    	 Motocicle.set("id_motocicle","1","cilinder","125","id_vehicle", vehicle.get("id"));

         motocicle.save();

   System.out.println(motocicle);
   System.out.println(motocicle.parent(Vehicle.class));


        // Everything is good:
        the(motocicle).shouldBe("valid");

    }
}

