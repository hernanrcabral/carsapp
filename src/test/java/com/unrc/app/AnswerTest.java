package com.unrc.app;

import com.unrc.app.models.User;
import com.unrc.app.models.Answer;
import com.unrc.app.models.Question;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;
import static org.junit.Assert.assertEquals;

public class AnswerTest{
    @Before
    public void before(){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_test", "root", "root");
                                                          
        Base.openTransaction();
    }

    @After
    public void after(){
      System.out.println("AnswerTest tearDown");
        Base.rollbackTransaction();
        Base.close();
    }
 @Test
    public void shouldValidateMandatoryFields(){
       Answer answer= new Answer();

       // check errors
        the(answer).shouldNotBe("valid");
        the(answer.errors().get("answer")).shouldBeEqual("value is missing");
        the(answer.errors().get("question_id")).shouldBeEqual("value is missing");
        the(answer.errors().get("user_id")).shouldBeEqual("value is missing");

        User user = new User();
        user.set("id",1,"first_name", "John", "last_name", "Doe", "email", "example@email.com");
        user.save();

    // Create Answer
       answer.set("id",1,"answer","lo podes venir a ver a casa","user_id", user.get("id"),"question_id",1);
       answer.save();

      System.out.println(answer);
  //    System.out.println(answer.parent(User.class));


        // Everything is good:
        the(answer).shouldBe("valid");

    }
}


