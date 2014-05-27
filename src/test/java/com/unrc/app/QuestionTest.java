package com.unrc.app;

import com.unrc.app.models.User;
import com.unrc.app.models.Question;
import com.unrc.app.models.Answer;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;
import static org.junit.Assert.assertEquals;

public class QuestionTest{
    @Before
    public void before(){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_test", "root", "root");
        Base.openTransaction();
    }

    @After
    public void after(){
        System.out.println("QuestionTest tearDown");
        Base.rollbackTransaction();
        Base.close();
    }
 @Test
    public void shouldValidateMandatoryFields(){
       Question question= new Question();

       // check errors
        the(question).shouldNotBe("valid");
        the(question.errors().get("id")).shouldBeEqual("value is missing");        
        the(question.errors().get("description")).shouldBeEqual("value is missing");
        the(question.errors().get("user_id")).shouldBeEqual("value is missing");
        the(question.errors().get("answer_id")).shouldBeEqual("value is missing");
        
        User user = new User();
        user.set("id",1,"first_name", "John", "last_name", "Doe", "email", "example@email.com");
        user.save();

		// Create question
    	 question.set("id",1,"description","¿Donde puedo ir a ver el auto?","user_id", user.get("id"),"answer_id", 4);
         question.save();

		  System.out.println(question);
		  System.out.println(question.parent(User.class));


        // Everything is good:
        the(question).shouldBe("valid");

    }
}


