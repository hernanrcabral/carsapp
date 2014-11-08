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
import spark.Spark;
import spark.Session;

/**
 *
 * @author nan
 */
public class UserController {
    
    private static final String SESSION_NAME = "username";
    private static String rol = "";
    
    public static ModelAndView getnewUser(Request request, Response response){   
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "newUsers.moustache");
    }
    
    public static String postUser(Request request, Response response){
        User newUser = new User();
        request.session().attribute(SESSION_NAME, request.queryParams("email"));
        newUser.set("first_name",request.queryParams("first_name"));
        newUser.set("last_name",request.queryParams("last_name"));
        newUser.set("email",request.queryParams("email"));
        newUser.set("password",request.queryParams("password"));
	if (rol.matches("super")){
       	    newUser.set("role","admin");
	    response.redirect("/hell1");
        }else{
            newUser.set("role","user");
	    rol = "user";
	    response.redirect("/hell2");
        }
        newUser.saveIt();            
        return "success";
    }
    
    public static String postLogin(Request request, Response response){
        User usuarioLog = User.findFirst("email = ?", request.queryParams("email"));
			
	if  (usuarioLog.password().matches(request.queryParams("password"))){
            request.session().attribute(SESSION_NAME, usuarioLog.email());
            rol = usuarioLog.role();
            if (usuarioLog.role().matches("user")){ 
		response.redirect("/hell2");
	    }
	    if (usuarioLog.role().matches("admin")){ 
		response.redirect("/hell");
	    }	
	    if (usuarioLog.role().matches("super")){
                response.redirect("/hell1");
            }
	}else{
            response.redirect("/HError");
	}
        return "success";
    }
    
    public static ModelAndView getUser(Request request, Response response){
        Map<String, Object> attributes = new HashMap<>();
        List<User> users = User.findAll();
        attributes.put("users_count", users.size());
        attributes.put("users", users);
        return new ModelAndView(attributes, "users.moustache");
    }
    
    public static ModelAndView getUserId(Request request, Response response){
            
            Map<String, Object> attributes = new HashMap<>();
                User user = User.findFirst("id = ?", request.params(":id"));
                attributes.put("user", user);

                return new ModelAndView(attributes, "userId.moustache");
    }

    public String getRole(){
    	return rol;
    }
    
}
