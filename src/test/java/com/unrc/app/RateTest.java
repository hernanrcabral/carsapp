package com.unrc.app;

import com.unrc.app.models.User;
import com.unrc.app.models.Post;
import com.unrc.app.models.Rate;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;
import static org.junit.Assert.assertEquals;

public class RateTest{
    @Before
    public void before(){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_test", "root", "root");
        Base.openTransaction();
    }

    @After
    public void after(){
        //System.out.println("RateTest tearDown");
        Base.rollbackTransaction();
        Base.close();
    }
 @Test
    public void shouldValidateMandatoryFields(){
       Rate rate= new Rate();

       // check errors
        the(rate).shouldNotBe("valid");
        the(rate.errors().get("id_rate")).shouldBeEqual("value is missing");        
        the(rate.errors().get("point")).shouldBeEqual("value is missing");
        the(rate.errors().get("user_id")).shouldBeEqual("value is missing");
        the(rate.errors().get("id_post")).shouldBeEqual("value is missing");
        
        User user = new User();
        user.set("first_name", "John", "last_name", "Doe", "email", "example@email.com");
        user.save();

		// Create question
    	 rate.set("id_rate","1","point","5","user_id", user.get("id"),"id_post", post.get("id"));

         rate.save();

   System.out.println(rate);
   System.out.println(rate.parent(User.class));


        // Everything is good:
        the(rate).shouldBe("valid");

    }
}

