/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unrc.app.controller;
import static spark.Spark.*;

import org.javalite.activejdbc.Base;
import java.util.*;

import com.unrc.app.models.Car;
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
public class AutoController {
    
    public static ModelAndView getCars(Request request,Response response){
            Map<String, Object> attributes = new HashMap<>();
            List<Car> cars = Car.findAll();
            attributes.put("cars_count", cars.size());
            attributes.put("cars", cars);
            return new ModelAndView(attributes,"cars.moustache");
    }
    
    public static ModelAndView getCarsPatente(Request request,Response response){
        Map<String, Object> attributes= new HashMap<>();
        Car auto= Car.findFirst("id_vehicle = ?",request.params("patent"));
        attributes.put("car",auto);
        return new ModelAndView(attributes,"carId.moustache");  
    }
    
}
