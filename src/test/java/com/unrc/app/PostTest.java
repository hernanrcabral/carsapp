package com.unrc.app;

import com.unrc.app.models.User;
import com.unrc.app.models.Post;
import com.unrc.app.models.Vehicle;
import com.unrc.app.models.Question;

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
	    System.out.println("UserTest tearDown");
        Base.rollbackTransaction();
        Base.close();
    }

    @Test
    public void shouldValidateMandatoryFields(){
       Post post= new Post();

       // check errors
        the(post).shouldNotBe("valid");
        the(post.errors().get("id")).shouldBeEqual("value is missing");
        the(vehicle.errors().get("description")).shouldBeEqual("value is missing");
        the(vehicle.errors().get("user_id")).shouldBeEqual("value is missing");
        the(vehicle.errors().get("vehicle_id")).shouldBeEqual("value is missing");
        the(vehicle.errors().get("question_id")).shouldBeEqual("value is missing");
        
        User user = new User();
        user.set("first_name", "John", "last_name", "Doe", "email", "example@email.com");
        user.save();

		// Create Vehicle
    	 post.set("id", 1,"description","Excelente Oferta","user_id", user.get("id"), question.get("id"), vehicle.get("patent"));

        post.save();

   System.out.println(post);
   System.out.println(post.parent(User.class));


        // Everything is good:
        the(vehicle).shouldBe("valid");

    }
}

