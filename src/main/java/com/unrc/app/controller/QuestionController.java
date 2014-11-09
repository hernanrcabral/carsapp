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
public class QuestionController {
    
    public static String getnewQuestion(Request request, Response response) {
        String  form= "<form action= \"/questions \" method= \"post\">";
    
        form +="Email Usuario que realiza la pregunta: ";         
        // seleccionar el usuario q corresponde la pregunta a agregar
        form+="<select name=\"email\">";
        List<User> users = User.findAll();
         for (User u:users){
            String mail = u.getString("email");
            form+="<option value =\""+mail+"\">";
            form+=" "+u.getString("email");
         };
        form+="</select><br>";

        form +="Publicacion: ";         
        // seleccionar la publicacion a la q corresponde la pregunta a agregar
        form+="<select name=\"description\">";
        List<Post> posts = Post.findAll();
          for (Post p:posts){
            String desc = p.getString("description");
            form+="<option value =\""+desc+"\">";
            form+=" "+p.getString("description");
          };
        form+="</select><br>";

        form += "Pregunta: <input type=\"text\" name=\"question\" ><br>";

        form +="<input value=\"Guardar\" type=\"submit\" > <br>";
        form +="</form>";
        
        return form;
    }
    
    public static String postQuestion(Request request, Response response, String role){
        Question newQuestion = new Question();
        String mail = request.queryParams("email");
        String desc = request.queryParams("description");
            
        User d = User.findFirst("email = ?",mail);
        Post p = Post.findFirst("description = ?",desc);

        newQuestion.set("user_id",d.get("email"));  
        newQuestion.set("post_id",p.get("description")); 
        newQuestion.set("question",request.queryParams("question"));
        newQuestion.saveIt();
        String a ;
          if (role.matches("super")){
                a="/hell1";
          }else{
            if(role.matches("admin")){
                    a="/hell";
            }else{
               a="/hell2";     
            }
         }
        response.redirect(a);
        return "success";
    }
    
    public static ModelAndView getQuestion(Request request,Response response){
        Map<String, Object> attributes = new HashMap<>();
        List<Question> questions = Question.findAll();
        attributes.put("questions_count", questions.size());
        attributes.put("questions", questions);
        return new ModelAndView(attributes, "questions.moustache");
    }
    
    public static ModelAndView getQuestionId(Request request,Response response){
        Map<String, Object> attributes = new HashMap<>();
        Question question = Question.findFirst("id = ?", request.params(":id"));
        attributes.put("question", question);
        return new ModelAndView(attributes, "questionId.moustache");
    }
}
