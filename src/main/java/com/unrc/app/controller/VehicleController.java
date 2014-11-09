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
public class VehicleController {
    
    public static String getnewVehicle(Request request, Response response) {
        Map<String, Object> attributes = new HashMap<>();
        String  form= "<form action= \"/vehicles \" method= \"post\">";
            form +="Patente: <input type=\"text\" name=\"patent\" ><br>";
            form +="Modelo: <input type=\"text\" name=\"kind\" > <br> ";

            form += "Codigo Postal: ";
            form+="<select name=\"postal_code\">";
            List<City> cities = City.findAll();
            for (City c:cities) {
                String postal_code = c.getString("postal_code");
                form+="<option value =\""+postal_code+"\">";
                form+=" "+c.getString("postal_code");
            };
            form+="</select><br>";

            form +="Email: ";         
            // selecciona usuario al q corresponde el vehiculo a crear
            form+="<select name=\"email\">";
            List<User> users = User.findAll();
            for (User u:users){
                String mail = u.getString("email");
                form+="<option value =\""+mail+"\">";
                form+=" "+u.getString("email");
            };
            form+="</select><br>";

            form +="Marca: <input type=\"text\" name=\"mark\" > <br>";

            form+="Tipo Vehiculo : ";
            form+="<select name=\"type\">";
            form+="<option value=\"1\">Automóvil</option>";
            form+="<option value=\"2\">Camioneta</option>"; 
            form+="<option value=\"3\">Motocicleta</option>";
            form+="<option value=\"4\">Otros</option>";
            form+="</select><br>";

            form+="Complete solo los campos que corresponden a su tipo de vehiculo: <br>";
            form+="<br> Automóvil: <br>";
            form+="Ingrese cantidad de puertas: ";
            form+="<select name=\"num\">";
            form+="<option value=\"1\">Una</option>";
            form+="<option value=\"2\">Dos</option>";
            form+="<option value=\"3\">Tres</option>";
            form+="<option value=\"4\">Cuatro</option>";
            form+="<option value=\"5\">Cinco</option>";
            form+="<option value=\"6\">Seis</option>";
            form+="<option value=\"7\">Mas</option>";
            form+="</select><br>";

            form+="<br> Motocicleta: <br>";
            form+="Cilindrada: <input type=\"text\" name=\"cylinder\" ><br>";

            form+="<br> Camioneta: <br>";
            form+="Ingrese cantidad de cinturones: ";
            form+="<select name=\"cant\">";
            form+="<option value=\"1\">Uno</option>";
            form+="<option value=\"2\">Dos</option>";
            form+="<option value=\"3\">Tres</option>";
            form+="<option value=\"4\">Cuatro</option>";
            form+="<option value=\"5\">Cinco</option>";
            form+="<option value=\"6\">Seis</option>";
            form+="<option value=\"7\">Siete</option>";
            form+="</select><br>";

            form+="<br> Otros: <br>";
            form+="Adicional: <input type =\"text\" name=\"c_other\" ><br>";

            form +="<input value=\"Guardar\" type=\"submit\" > <br>";
            form +="</form>";         
        
        return form;
    }
    
    public static String postVehicle(Request request, Response response, String role){
        Vehicle newVehicle = new Vehicle(); 
        String mail = request.queryParams("email");
        String cod_postal = request.queryParams("postal_code");

        City c = City.findFirst("postal_code = ?",cod_postal);
        User d = User.findFirst("email = ?",mail);
                
        newVehicle.set("user_id",d.get("email"));
        newVehicle.set("city_id",c.get("name"));
        newVehicle.set("patent",request.queryParams("patent"));
        newVehicle.set("kind",request.queryParams("kind"));
        newVehicle.set("mark",request.queryParams("mark"));
        newVehicle.saveIt();
          if (request.queryParams("type").charAt(0)=='1'){
            Car a= new Car();
            a.set("count_doors",request.queryParams("num"));
            a.set("id_vehicle",newVehicle.getString("patent"));
            a.saveIt();
          }
          if (request.queryParams("type").charAt(0)=='2'){
            Truck t= new Truck();
            t.set("count_belt",request.queryParams("cant"));
            t.set("id_vehicle",newVehicle.getString("patent"));
            t.saveIt();
          }
          if (request.queryParams("type").charAt(0)=='3'){
            Motocicle m= new Motocicle();
            m.set("cylinder",request.queryParams("cylinder"));
            m.set("id_vehicle",newVehicle.getString("patent"));
            m.saveIt();
          }
          if (request.queryParams("type").charAt(0)=='4'){
            Other o = new Other();
            o.set("c_other",request.queryParams("c_other"));
            o.set("id_vehicle",newVehicle.getString("patent"));
            o.saveIt();
          }
          String a ;
          if (role.matches("super")){
                a="/hell1";
          }else{
                a="/hell";
          }
        response.redirect(a);
        return "success";
    }
    
    public static ModelAndView getVehicle(Request request,Response response){
        Map<String, Object> attributes = new HashMap<>();
        List<Vehicle> vehicles = Vehicle.findAll();
        attributes.put("vehicles_count", vehicles.size());
        attributes.put("vehicles", vehicles);
        return new ModelAndView(attributes, "vehicles.moustache");
    }
    
    public static ModelAndView getVehicleId(Request request,Response response){
        Map<String, Object> attributes = new HashMap<>();
        Vehicle vehicle = Vehicle.findFirst("id = ?", request.params(":id"));
        attributes.put("vehicle", vehicle);
        return new ModelAndView(attributes, "vehicleId.moustache");
    }
}