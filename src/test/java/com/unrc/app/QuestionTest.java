package com.unrc.app;

import com.unrc.app.models.Question;


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
   
        the(question.errors().get("question")).shouldBeEqual("value is missing");
        
        // Create question
        question.set("id",1,"question","¿Donde puedo verlo?","user_id","nuevo@gmail.com","post_id","Vendo Vehiculo.Papeles al dia.","user_id");
        question.save();

          System.out.println(question);



        // Everything is good:
        the(question).shouldBe("valid");

    }
}


