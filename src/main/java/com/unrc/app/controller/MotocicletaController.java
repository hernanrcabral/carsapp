/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unrc.app.controller;
import static spark.Spark.*;

import org.javalite.activejdbc.Base;
import java.util.*;

import com.unrc.app.models.Motocicle;
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
public class MotocicletaController {
   
    
    public static ModelAndView getMotocicles(Request request, Response response){
        Map<String, Object> attributes = new HashMap<>();
        List<Motocicle> motos = Motocicle.findAll();
        attributes.put("motocicles_count",motos.size());
        attributes.put("motocicles",motos); 
        return new ModelAndView(attributes, "motocicles.moustache");
    }
    
    public static ModelAndView getMotociclesPatente(Request request, Response response){
        Map<String, Object> attributes = new HashMap<>();
        Motocicle moto = Motocicle.findFirst("id_vehicle = ?",request.params("patent"));
        attributes.put("motocicle",moto); 
        return new ModelAndView(attributes, "motocicleId.moustache");
        
    }
}
