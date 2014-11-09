/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unrc.app.controller;
import static spark.Spark.*;

import com.unrc.app.models.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

/**
 *
 * @author nan
 */
public class CityController {
    
    //private static final String SESSION_NAME = "username";
    //private static String rol = "";
    
    public static ModelAndView getnewCity(Request request, Response response){   
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "newCities.moustache");
    }
    
    public static String postCity(Request request, Response response){
        City newCity = new City();
        newCity.set("postal_code",request.queryParams("postal_code"));
        newCity.set("name",request.queryParams("name"));
        newCity.saveIt();            
        return "success";
    }
    
    public static ModelAndView getCity(Request request, Response response){
        Map<String, Object> attributes = new HashMap<>();
        List<City> cities = City.findAll();
        attributes.put("cities_count", cities.size());
        attributes.put("cities", cities);
        return new ModelAndView(attributes, "cities.moustache");
    }
    
    public static ModelAndView getCityId(Request request, Response response){
        Map<String, Object> attributes = new HashMap<>();
        City city = City.findFirst("id = ?", request.params(":id"));
        attributes.put("city", city);
        return new ModelAndView(attributes, "cityId.moustache");
    }
}
