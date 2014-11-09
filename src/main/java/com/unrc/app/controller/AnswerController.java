/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unrc.app.controller;
import static spark.Spark.*;

import org.javalite.activejdbc.Base;
import java.util.*;

import com.unrc.app.models.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

/**
 *
 * @author Silvia
 */
public class AnswerController {
    
    public static String getnewAnswer(Request request, Response response) {
        String form= "<form action= \"/answers \" method= \"post\">";
        form +="Email: ";
        // selecciona el usuario q corresponde al post a agregar
        form+="<select name=\"email\">";
        List<User> users = User.findAll();
        for (User u:users){
            String mail = u.getString("email");
            form+="<option value =\""+mail+"\">";
            form+=" "+u.getString("email");
        };
        form+="</select><br>";

        form += "Pregunta a responder: ";
    
        form+="<select name=\"question\">";
        List<Question> questions = Question.findAll();
        for (Question q:questions){
            String des = q.getString("question");
            form+="<option value =\""+des+"\">";
            form+=" "+q.getString("question");
        };
        form+="</select><br>";
        form += "Respuesta: <input type=\"text\" name=\"answer\" ><br>";
        form +="<input value=\"Guardar\" type=\"submit\" > <br>";
        form +="</form>";
        
        return form;
    }
    
    public static String postAnswer(Request request, Response response, String role){
        Answer newAnswer = new Answer();
        String preg = request.queryParams("question");
        String mail = request.queryParams("email");
    
        Question q = Question.findFirst("question = ?",preg);
        User d = User.findFirst("email = ?",mail);

        newAnswer.set("user_id",d.get("email"));
        newAnswer.set("question_id",q.get("question"));
        newAnswer.set("answer",request.queryParams("answer"));
        newAnswer.saveIt();
         String a ;
         if (role.matches("super")){
                a="/hell1";
         }else{
                a="/hell";     
         }
        response.redirect(a);
        return "success";
    }
    
    public static ModelAndView getAnswer(Request request,Response response){
        Map<String, Object> attributes = new HashMap<>();
        List<Answer> answers = Answer.findAll();
        attributes.put("answers_count", answers.size());
        attributes.put("answers", answers);
        return new ModelAndView(attributes, "answers.moustache");
    }
    
    public static ModelAndView getAnswerId(Request request,Response response){
        Map<String, Object> attributes = new HashMap<>();
        Answer answer = Answer.findFirst("id = ?", request.params(":id"));
        attributes.put("answer", answer);
        return new ModelAndView(attributes, "answerId.moustache");
    }
}
