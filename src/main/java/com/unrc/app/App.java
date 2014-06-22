package com.unrc.app;
import static spark.Spark.*;

import org.javalite.activejdbc.Base;
import com.unrc.app.models.User;
<<<<<<< HEAD
=======
import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello cruel World!" );

        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_development", "root", "root");
    
        get("/users", (request,response) ->{
            return User.findAll();
        });

        get("/users", (request,response) ->{
            return User.findByld(request.params(":id"));
        });

        Base.close();
       
    


//opcional--
    get("/users",
            (request,response) -> {
                Map<String.objet> attrs = new HashMap<>();
                    <User> users = user.findAll();
                List
                ahrs.put("user_count",users.size());)  
                ahrs.put("userr",users);
                return new ModelAndView(ahrs,"users.moustaches")


    }
}



/*package com.unrc.app;

import org.javalite.activejdbc.Base;
>>>>>>> 5afebade3331c9cf17a6ddf8f3d693cf6af19ae4
import com.unrc.app.models.Vehicle;
import com.unrc.app.models.Post;
import com.unrc.app.models.Question;
import com.unrc.app.models.City;
import com.unrc.app.models.Car;
import com.unrc.app.models.Truck;
import com.unrc.app.models.Other;
import com.unrc.app.models.Motocicle;

import java.util.*;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
         
        System.out.println( "Hello cruel World!" );

        before ((request, response) -> {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_development", "root", "root");
        });

        
        get("/hello",(request, response) -> {
            return "Hello World!";
         });
    

        //
        //   TRATO USUARIO
        //


        get("/newUsers", (request,response) ->{
			String  form= "<form action= \"/users \" method= \"post\">";
			form += "Nombre: <input type=\"text\" name=\"first_name\" ><br>";
			form += "Apellido: <input type=\"text\" name=\"last_name\" > <br> ";
		   	form += "E-mail: <input type=\"text\" name=\"email\" > <br>";
		 	form  +="<input value=\"Registrarse\" type=\"submit\" > <br>";
			form +="</form>";
			return form ;
		});


        get("/users", (request,response) -> {
	       return User.findAll();
        });
    
        get("/users/:id", (request,response) -> {
            return User.findById(request.params(":id"));
        });

        post("/users", (request,response) ->{
            User newUser = new User();
            newUser.set("first_name",request.queryParams("first_name"));
            newUser.set("last_name",request.queryParams("last_name"));
            newUser.set("email",request.queryParams("email"));
            newUser.saveIt();
            response.redirect("/users");
            return "success";
         });

            // TRATO CUIDAD

        get("/newCities", (request,response) ->{
            String  form= "<form action= \"/cities \" method= \"post\">";
            form += "Nombre: <input type=\"text\" name=\"name\" ><br>";
            form += "Codigo Postal: <input type=\"text\" name=\"postal_code\" ><br>";
            form +="<input value=\"Guardar\" type=\"submit\" > <br>";
            form +="</form>";
            return form ;
        });


        get("/cities", (request,response) ->{
            return City.findAll();
        });

        get("/cities/:id", (request,response) -> {
            return City.findById(request.params(":id"));
        });


        post("/cities", (request,response) ->{
            City newCity = new City();
            newCity.set("postal_code",request.queryParams("postal_code"));
            newCity.set("name",request.queryParams("name"));
            newCity.saveIt();
            response.redirect("/cities");
            return "success";
         });

        
        //     TRATO DE VEHICULO
        

        get("newVehicles", (request,response) ->{
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
            form +="Estado: <input type=\"text\" name=\"status\" > <br>";
            form +="Precio: <input type=\"text\" name=\"price\" > <br>";
            form +="Descripcion: <input type=\"text\" name=\"description\" > <br>";

            form+="Tipo Vehiculo : ";
            form+="<select name=\"type\">";
            form+="<option value=\"1\">Automóvil</option>";
            form+="<option value=\"2\">Camioneta</option>"; 
            form+="<option value=\"3\">Motocicleta</option>";
            form+="<option value=\"4\">Otros</option>";
            form+="</select><br>";

            form+="Rellene solo los campos correspondientes a su tipo de vehiculo: <br>";
            form+="<br> Automóvil: <br>";
            form+="Ingrese cantidad de puertas: ";
            form+="<select name=\"num\">";
            form+="<option value=\"1\">Una</option>";
            form+="<option value=\"2\">Dos</option>";
            form+="<option value=\"3\">Tres</option>";
            form+="<option value=\"4\">Cuatro</option>";
            form+="<option value=\"5\">Cinco</option>";
            form+="<option value=\"6\">Seis</option>";
            form+="<option value=\"7\">Siete</option>";
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
            return form ;
        });


        get("/vehicles", (request,response) ->{
            return Vehicle.findAll();
        });


        post("/vehicles", (request,response) ->{

           Vehicle newVehicle = new Vehicle(); 
           String mail = request.queryParams("email");
           String cod_postal = request.queryParams("postal_code");

           City c = City.findFirst("postal_code = ?",cod_postal);
           User d = User.findFirst("email = ?",mail);
                
            newVehicle.set("user_id",d.get("id"));
            newVehicle.set("city_id",c.get("id"));
            newVehicle.set("status",request.queryParams("status"));
            newVehicle.set("patent",request.queryParams("patent"));
            newVehicle.set("kind",request.queryParams("kind"));
            newVehicle.set("mark",request.queryParams("mark"));
            newVehicle.set("description",request.queryParams("description")); 
            newVehicle.set("price",request.queryParams("price"));         
            newVehicle.saveIt();

            if (request.queryParams("type")=="1"){
                Car a= new Car();
                a.set("count_doors",request.queryParams("num"));
                a.set("id_vehicle",newVehicle.getString("patent"));
                a.saveIt();
            }else{
                if (request.queryParams("type")=="2"){
                Truck t= new Truck();
                t.set("count_belt",request.queryParams("cant"));
                t.set("id_vehicle",newVehicle.getString("patent"));
                t.saveIt();
                }
                else{
                    if (request.queryParams("type")=="3"){
                    Motocicle m= new Motocicle();
                    m.set("cylinder",request.queryParams("cylinder"));
                    m.set("id_vehicle",newVehicle.getString("patent"));
                    m.saveIt();
                    }
                    else{
                        if (request.queryParams("type")=="4"){
                           Other o = new Other();
                            o.set("c_other",request.queryParams("c_other"));
                            o.set("id_vehicle",newVehicle.getString("patent"));
                            o.saveIt();
                        }
                    }
                }
            }


            response.redirect("/vehicles");
            return "success";
         });



        get("/", (request,response) -> {
            return "Hello world cruel";
        });
  
    

       after ((request,response)-> {
        Base.close();           
        });
   }
}
