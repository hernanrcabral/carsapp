/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unrc.app.controller;

import com.unrc.app.models.Other;
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
public class OtrosVehiculosController {

    
    
    public static ModelAndView getOthers(Request request, Response response){
        Map<String, Object> attributes = new HashMap<>();
        List<Other> otros = Other.findAll();
        attributes.put("others_count",otros.size());
        attributes.put("others",otros); 
        return new ModelAndView(attributes, "others.moustache");
    }
    
    public static ModelAndView getOthersPatente(Request request, Response response){
        Map<String, Object> attributes = new HashMap<>();
        Other otro = Other.findFirst("id_vehicle = ?",request.params("patent"));
        attributes.put("other",otro); 
        return new ModelAndView(attributes, "otherId.moustache");
    }
    
        
}
