/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unrc.app.controller;
import static spark.Spark.*;

import org.javalite.activejdbc.Base;
import java.util.*;

import com.unrc.app.models.Post;
import com.unrc.app.models.User;
import com.unrc.app.models.Vehicle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

/**
 *
 * @author silvia
 */
public class PublicacionController {
    
    public static String getnewPublicacion(Request request, Response response) {
        String form= "<form action= \"/posts \" method= \"post\">";
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
        form += "Publicacion: <input type=\"text\" name=\"description\" ><br>";
        form +="Patente del Vehiculo: ";
        // selecciona el vehiculo al q corresponde el post a crear
        form+="<select name=\"patent\">";
        List<Vehicle> vehicles = Vehicle.findAll();
        for (Vehicle v:vehicles){
            String patente = v.getString("patent");
            form+="<option value =\""+patente+"\">";
            form+=" "+v.getString("patent");
        };
        form+="</select><br>";
        form +="<input value=\"Guardar\" type=\"submit\" > <br>";
        form +="</form>";
        
        return form;
    }
    
    public static String postsPublicacion(Request request, Response response, String role){
        Post newPost = new Post();

        String mail = request.queryParams("email");
        String pat = request.queryParams("patent");
            
        User d = User.findFirst("email = ?",mail);
        Vehicle p = Vehicle.findFirst("patent = ?",pat);

        newPost.set("user_id",d.get("email"));
        newPost.set("vehicle_id",p.get("patent"));
        newPost.set("description",request.queryParams("description"));
        newPost.saveIt();         

        String a ;
          if (role.matches("super")){
                a="/hell1";
          }else{
                a="/hell";
         }
        response.redirect(a);
  
        return "success";
    }
    
    public static ModelAndView getpostsPublicacion(Request request,Response response){
        Map<String, Object> attributes = new HashMap<>();
        List<Post> posts = Post.findAll();
        attributes.put("post_count", posts.size());
        attributes.put("posts", posts);
        return new ModelAndView(attributes, "posts.moustache");
    }
    
    public static ModelAndView getpostidPublicacion(Request request,Response response){
        Map<String, Object> attributes = new HashMap<>();
        Post post = Post.findFirst("id = ?", request.params(":id"));
        attributes.put("post", post);
        return new ModelAndView(attributes, "postId.moustache");
    }

    
}
