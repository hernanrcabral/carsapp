/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unrc.app.controller;

import com.unrc.app.models.Truck;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

/**
 *
 * @author nan
 */
public class CamionetaController {

    
    public static ModelAndView getTrucks(Request request, Response response){
        Map<String, Object> attributes = new HashMap<>();
        List<Truck> camionetas = Truck.findAll();
        attributes.put("trucks_count", camionetas.size());
        attributes.put("trucks",camionetas); 
        return new ModelAndView(attributes, "trucks.moustache");
    }
    
    public static ModelAndView getTrucksPatente(Request request, Response response){
        
        Map<String, Object> attributes = new HashMap<>();
        Truck camioneta = Truck.findFirst("id_vehicle = ?",request.params("patent"));
        attributes.put("truck",camioneta); 
        return new ModelAndView(attributes, "truckId.moustache");
    }
    
}
