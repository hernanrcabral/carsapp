package com.unrc.app;

import com.unrc.app.models.Answer;

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
    

    // Create Answer
       answer.set("answer","Dorrego 212","user_id","nuevo@gmail.com","question_id","¿Donde lo puedo ver?");
       answer.save();

      System.out.println(answer);
  //    System.out.println(answer.parent(User.class));


        // Everything is good:
        the(answer).shouldBe("valid");

    }
}


