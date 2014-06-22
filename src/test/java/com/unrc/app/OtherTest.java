package com.unrc.app;

import com.unrc.app.models.Other;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;
import static org.junit.Assert.assertEquals;                                                    


public class OtherTest{
    @Before
    public void before(){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_test", "root", "root");                                                       
        Base.openTransaction();
    }

    @After
    public void after(){
        System.out.println("OtherTest tearDown");               
        Base.rollbackTransaction();
        Base.close();
    }

    @Test
    public void shouldValidateMandatoryFields(){
        Other  other= new Other();
     
       // check errors
        the(other).shouldNotBe("valid");
        the(other.errors().get("id_vehicle")).shouldBeEqual("value is missing");
        the(other.errors().get("c_other")).shouldBeEqual("value is missing");
        the(other.errors().get("id")).shouldBeEqual("value is missing");


        // Create Vehicle
        other.set("id",1, "id_vehicle", 1, "c_other", 5);
        other.save();
//      other.setParent(user);

        System.out.println(other);

        // Everything is good:
        the(other).shouldBe("valid");

    }
}


 

