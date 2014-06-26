package com.unrc.app;
import static spark.Spark.*;

import org.javalite.activejdbc.Base;
import java.util.*;

import com.unrc.app.models.User;
import com.unrc.app.models.Vehicle;
import com.unrc.app.models.Post;
import com.unrc.app.models.Question;
import com.unrc.app.models.City;
import com.unrc.app.models.Car;
import com.unrc.app.models.Truck;
import com.unrc.app.models.Other;
import com.unrc.app.models.Motocicle;

import com.unrc.app.models.Answer;
import spark.ModelAndView;
import spark.Spark;


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
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "hello.mustache");
        },
         new MustacheTemplateEngine()
        );



//-------------------------------------------------------------------------------------------
//                  TRATO USUARIO
//-------------------------------------------------------------------------------------------


        get("/newUsers", (request,response) ->{
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "newUsers.moustache");
        },
            new MustacheTemplateEngine()
        );

        post("/users", (request,response) ->{
            User newUser = new User();
            newUser.set("first_name",request.queryParams("first_name"));
            newUser.set("last_name",request.queryParams("last_name"));
            newUser.set("email",request.queryParams("email"));
            newUser.saveIt();
            response.redirect("/hello");
            return "success";
         });


        get("/users",(request, response) -> {
                Map<String, Object> attributes = new HashMap<>();
                List<User> users = User.findAll();
                attributes.put("users_count", users.size());
                attributes.put("users", users);
                return new ModelAndView(attributes, "users.moustache");
            },
            new MustacheTemplateEngine()    
        );

 
        get("/users/:id", (request,response) -> {
                Map<String, Object> attributes = new HashMap<>();
                User user = User.findFirst("id = ?", request.params(":id"));
                attributes.put("user", user);

                return new ModelAndView(attributes, "userId.moustache");
            },
            new MustacheTemplateEngine()    
        );


//-------------------------------------------------------------------------------------------
//                               TRATO CIUDAD
//-------------------------------------------------------------------------------------------



        get("/newCities", (request,response) ->{
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "newCities.moustache");
        },
            new MustacheTemplateEngine()
        );

        post("/cities", (request,response) ->{
            City newCity = new City();
            newCity.set("postal_code",request.queryParams("postal_code"));
            newCity.set("name",request.queryParams("name"));
            newCity.saveIt();
            response.redirect("/hello");
            return "success";
         });

 

        get("/cities", (request,response) ->{
            Map<String, Object> attributes = new HashMap<>();
            List<City> cities= City.findAll();
            attributes.put("cities_count",cities.size());
            attributes.put("cities",cities);
            return new ModelAndView(attributes, "cities.moustache");
            },
            new MustacheTemplateEngine()    
        );
       

        get("/cities/:id", (request,response) -> {
                Map<String, Object> attributes = new HashMap<>();
                City city = City.findFirst("id = ?", request.params(":id"));
                attributes.put("city", city);
                return new ModelAndView(attributes, "cityId.moustache");
            },
            new MustacheTemplateEngine()    

        );
 

//-------------------------------------------------------------------------------------------
//               TRATO VEHICULO
//-------------------------------------------------------------------------------------------


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
            return form ;
        });


        post("/vehicles", (request,response) ->{

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
                    
            response.redirect("/hello");
            return "success";
         });

        

        get("/vehicles", (request,response) ->{
            Map<String, Object> attributes= new HashMap<>();
            List<Vehicle> vehicles= Vehicle.findAll();
            attributes.put("vehicles_count",vehicles.size());
            attributes.put("vehicles", vehicles);
            return new ModelAndView(attributes,"vehicles.moustache");
            },
            new MustacheTemplateEngine()
        );

        get("/vehicles/:patent", (request,response) -> {
            Map<String, Object> attributes= new HashMap<>();
            Vehicle vehicle = Vehicle.findFirst("patent = ?",request.params("patent"));
            attributes.put("vehicle",vehicle);
            return new ModelAndView(attributes, "vehicleId.moustache");
            },
            new MustacheTemplateEngine()    
        );

//-------------------------------------------------------------------------------------------
//                  TRATO AUTOS
//-------------------------------------------------------------------------------------------



        get("/cars",(request,response)->{
            Map<String, Object> attributes = new HashMap<>();
            List<Car> cars = Car.findAll();
            attributes.put("cars_count", cars.size());
            attributes.put("cars", cars);
            return new ModelAndView(attributes,"cars.moustache");
        },
         new MustacheTemplateEngine()
        ); 
       

        get("/cars/:patent", (request,response) -> {
            Map<String, Object> attributes= new HashMap<>();
            Car auto= Car.findFirst("id_vehicle = ?",request.params("patent"));
            attributes.put("car",auto);
            return new ModelAndView(attributes,"carId.moustache");  
            },
            new MustacheTemplateEngine()
        );

//-------------------------------------------------------------------------------------------
//                     TRATO CAMIONETAS
//-------------------------------------------------------------------------------------------

         get("/trucks",(request,response)->{
            Map<String, Object> attributes = new HashMap<>();
            List<Truck> camionetas = Truck.findAll();
            attributes.put("trucks_count", camionetas.size());
            attributes.put("trucks",camionetas); 
            return new ModelAndView(attributes, "trucks.moustache");
            },
            new MustacheTemplateEngine()
        );

        get("/trucks/:patent", (request,response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Truck camioneta = Truck.findFirst("id_vehicle = ?",request.params("patent"));
            attributes.put("truck",camioneta); 
            return new ModelAndView(attributes, "truckId.moustache");
            },
            new MustacheTemplateEngine()
        );


//-------------------------------------------------------------------------------------------
//                   TRATO MOTOCICLETAS
//-------------------------------------------------------------------------------------------


         get("/motocicles", (request,response) ->{
            Map<String, Object> attributes = new HashMap<>();
            List<Motocicle> motos = Motocicle.findAll();
            attributes.put("motocicles_count",motos.size());
            attributes.put("motocicles",motos); 
            return new ModelAndView(attributes, "motocicles.moustache");
            },
            new MustacheTemplateEngine()
        );

        get("/motocicles/:patent", (request,response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Motocicle moto = Motocicle.findFirst("id_vehicle = ?",request.params("patent"));
            attributes.put("motocicle",moto); 
            return new ModelAndView(attributes, "motocicleId.moustache");
            },
            new MustacheTemplateEngine()
        );



//-------------------------------------------------------------------------------------------
//                      TRATO OTRO TIPOS DE VEHICULOS
//-------------------------------------------------------------------------------------------


        get("/others", (request,response) ->{
            Map<String, Object> attributes = new HashMap<>();
            List<Other> otros = Other.findAll();
            attributes.put("others_count",otros.size());
            attributes.put("others",otros); 
            return new ModelAndView(attributes, "others.moustache");
            },
            new MustacheTemplateEngine()
        );

        get("/others/:patent", (request,response) -> {
            Map<String, Object> attributes = new HashMap<>();
            Other otro = Other.findFirst("id_vehicle = ?",request.params("patent"));
            attributes.put("other",otro); 
            return new ModelAndView(attributes, "otherId.moustache");
            },
            new MustacheTemplateEngine()
        );



//-------------------------------------------------------------------------------------------
//                             TRATO PUBLICACION
//-------------------------------------------------------------------------------------------
  
        get("/newPosts", (request,response) ->{
            String  form= "<form action= \"/posts \" method= \"post\">";

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
            return form ;
        });


        post("/posts", (request,response) ->{
            Post newPost = new Post();

            String mail = request.queryParams("email");
            String pat = request.queryParams("patent");
            
            User d = User.findFirst("email = ?",mail);
            Vehicle p = Vehicle.findFirst("patent = ?",pat);



            newPost.set("user_id",d.get("email"));
            newPost.set("vehicle_id",p.get("patent"));
            newPost.set("description",request.queryParams("description"));
            newPost.saveIt();         

            response.redirect("/hello");
  
          return "success";
         });


    get("/posts",(request, response) -> {
                Map<String, Object> attributes = new HashMap<>();
                List<Post> posts = Post.findAll();
                attributes.put("post_count", posts.size());
                attributes.put("posts", posts);
                return new ModelAndView(attributes, "posts.moustache");
            },
            new MustacheTemplateEngine()    

        );

 
        get("/post/:id", (request,response) -> {
                Map<String, Object> attributes = new HashMap<>();
                Post post = Post.findFirst("id = ?", request.params(":id"));
                attributes.put("post", post);
                return new ModelAndView(attributes, "postId.moustache");
            },
            new MustacheTemplateEngine()    
        );

//-------------------------------------------------------------------------------------------
//                                TRATO LAS PREGUNTAS
//-------------------------------------------------------------------------------------------

        get("/newQuestions", (request,response) -> {
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
            return form ;
        });


        post("/questions", (request,response) ->{
            Question newQuestion = new Question();
            String mail = request.queryParams("email");
            String desc = request.queryParams("description");
            
            User d = User.findFirst("email = ?",mail);
            Post p = Post.findFirst("description = ?",desc);

            newQuestion.set("user_id",d.get("email"));  
            newQuestion.set("post_id",p.get("description")); 
            newQuestion.set("question",request.queryParams("question"));
            newQuestion.saveIt();

            response.redirect("/hello");
            return "success";
         });

        get("/questions",(request, response) -> {
                Map<String, Object> attributes = new HashMap<>();
                List<Question> questions = Question.findAll();
                attributes.put("questions_count", questions.size());
                attributes.put("questions", questions);
                return new ModelAndView(attributes, "questions.moustache");
            },
            new MustacheTemplateEngine()    

        );

 
        get("/question/:id", (request,response) -> {
                Map<String, Object> attributes = new HashMap<>();
                Question question = Question.findFirst("id = ?", request.params(":id"));
                attributes.put("question", question);
                return new ModelAndView(attributes, "questionId.moustache");
            },
            new MustacheTemplateEngine()    
        );

         //
        //      TRATO LAS RESPUESTAS
        //

         get("/newAnswers", (request,response) ->{
            String  form= "<form action= \"/answers \" method= \"post\">";
            form +="Email Usuario Que Responde: ";
            // selecciona el usuario q corresponde la respuesta a agregar
            form+="<select name=\"email\">";
            List<User> users = User.findAll();
            for (User u:users){
                String mail = u.getString("email");
                form+="<option value =\""+mail+"\">";
                form+=" "+u.getString("email");
            };
            form+="</select><br>";

            form+="Pregunta a responder: ";
            form+="<select name=\"question\">";
            List<Question> questions = Question.findAll();
            for (Question q:questions){
                String des = q.getString("question");
                form+="<option value =\""+des+"\">";
                form+=" "+q.getString("question");
            };
            form+="</select><br>";

            form += "Respuesta: <input type=\"text\" name=\"answer\" ><br>";
            form +="<input value=\"Guardar\" type=\"submit\" > <br>";
            form +="</form>";
            return form ;
        });


       post("/answers", (request,response) ->{
            Answer newAnswer = new Answer();

            String preg = request.queryParams("question");
            String mail = request.queryParams("email");
    
            Question q = Question.findFirst("question = ?",preg);
            User d = User.findFirst("email = ?",mail);

            newAnswer.set("user_id",d.get("email"));
            newAnswer.set("question_id",q.get("question"));
            newAnswer.set("answer",request.queryParams("answer"));
            newAnswer.saveIt();
            response.redirect("/hello");
            return "success";
         });
        
        get("/answers",(request, response) -> {
                Map<String, Object> attributes = new HashMap<>();
                List<Answer> answers = Answer.findAll();
                attributes.put("answers_count", answers.size());
                attributes.put("answers", answers);
                return new ModelAndView(attributes, "answers.moustache");
            },
            new MustacheTemplateEngine()    

        );

 
        get("/answer/:id", (request,response) -> {
                Map<String, Object> attributes = new HashMap<>();
                Answer answer = Answer.findFirst("id = ?", request.params(":id"));
                attributes.put("answer", answer);
                return new ModelAndView(attributes, "answerId.moustache");
            },
            new MustacheTemplateEngine()    
        );
 

       after ((request,response)-> {
        Base.close();           
        });
   }
}
