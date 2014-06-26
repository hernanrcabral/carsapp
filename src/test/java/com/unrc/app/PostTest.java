package com.unrc.app;

import com.unrc.app.models.Post;


import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;
import static org.junit.Assert.assertEquals;                                                


public class PostTest{
    @Before
    public void before(){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_test", "root", "root");
        Base.openTransaction();
    }

    @After
    public void after(){
        System.out.println("PostTest tearDown");
        Base.rollbackTransaction();
        Base.close();
    }

    @Test
    public void shouldValidateMandatoryFields(){
       Post post= new Post();

       // check errors
        the(post).shouldNotBe("valid");
        the(post.errors().get("description")).shouldBeEqual("value is missing");


        // Create Vehicle
         post.set("id", 1,"description","Excelente Oferta","user_id","nuevo@gmail.com","vehicle_id","ABC123");
         post.save();

         System.out.println(post);



        // Everything is good:
        the(post).shouldBe("valid");

    }
}    

