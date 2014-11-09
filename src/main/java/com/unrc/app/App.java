package com.unrc.app;
import com.unrc.app.controller.*;
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
import org.elasticsearch.node.*;
import org.elasticsearch.client.*;


import org.elasticsearch.search.SearchHit;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders.*;
import org.elasticsearch.index.query.*;

/**
 * Hello world!
 *
 */
public class App 
{
   private static final String SESSION_NAME = "username";
   private static String rol = "";

   private static UserController usuario = new UserController();
   	
   public static void main( String[] args )
    {
         
        System.out.println( "Hello cruel World!" );

        before ((request, response) -> {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_development", "root", "root");
        });
        
        
        get("/", (request, response) -> {
			request.session(true);
            String name = request.session().attribute(SESSION_NAME);
            if (name == null) {
				Map<String, Object> attributes = new HashMap<>();
				return new ModelAndView(attributes, "hello.mustache");
				

            } else {
                if (usuario.getRole().matches("super")){
                    Map<String, Object> attributes = new HashMap<>();
                    return new ModelAndView(attributes, "hell1.mustache");
                }
                if (usuario.getRole().matches("admin")){
                    Map<String, Object> attributes = new HashMap<>();
				    return new ModelAndView(attributes, "hell.mustache");
                }
                else{
                    Map<String, Object> attributes = new HashMap<>();
                    return new ModelAndView(attributes, "hell2.mustache");
                }		
            }
        },
            new MustacheTemplateEngine()
            );

        get("/HError",(request, response) -> { // Agregamos un metodo nuevo
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "HError.mustache");
        },
         new MustacheTemplateEngine()
        );

        get("/hell2",(request, response) -> { // Agregamos un metodo nuevo
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "hell2.mustache");
        },
         new MustacheTemplateEngine()
        );
		
		get("/hell",(request, response) -> { // Agregamos un metodo nuevo
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "hell.mustache");
        },
         new MustacheTemplateEngine()
        );
        
        get("/hell1",(request, response) -> { // Agregamos un metodo nuevo
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "hell1.mustache");
        },
         new MustacheTemplateEngine()
        );


//-------------------------------------------------------------------------------------------
//                  TRATO USUARIO
//-------------------------------------------------------------------------------------------
        get("/newUsers", (request,response) -> 
            usuario.getnewUser(request, response),
            new MustacheTemplateEngine()
        );

        post("/users", (request,response) ->
            usuario.postUser(request, response)
         );
         
        post("/login", (request,response) ->
             usuario.postLogin(request,response)
         );

        get("/users",(request, response) -> 
             usuario.getUser(request, response),
            new MustacheTemplateEngine()    
        );

 
        get("/users/:id", (request,response) -> 
            usuario.getUserId(request, response),
            new MustacheTemplateEngine()    
        );


//-------------------------------------------------------------------------------------------
//                               TRATO CIUDAD
//-------------------------------------------------------------------------------------------
        get("/newCities", (request,response) -> 
            CityController.getnewCity(request, response)
            ,
            new MustacheTemplateEngine()
        );

        post("/cities", (request,response) ->
            CityController.postCity(request, response)
         );
         
        get("/cities",(request, response) -> 
             CityController.getCity(request, response)
            ,
            new MustacheTemplateEngine()    
        );

 
        get("/cities/:id", (request,response) -> 
            CityController.getCityId(request, response)
            ,
            new MustacheTemplateEngine()    
        );


//-------------------------------------------------------------------------------------------
//               TRATO VEHICULO
//-------------------------------------------------------------------------------------------
        get("/newVehicles", (request,response) -> 
            {return VehicleController.getnewVehicle(request, response);}
        );

        post("/vehicles", (request,response) ->
            VehicleController.postVehicle(request, response, usuario.getRole())
         );
         
        get("/vehicles",(request, response) -> 
             VehicleController.getVehicle(request, response)
            ,
            new MustacheTemplateEngine()    
        );

 
        get("/vehicles/:patent", (request,response) -> 
            VehicleController.getVehicleId(request, response)
            ,
            new MustacheTemplateEngine()    
        );


//-------------------------------------------------------------------------------------------
//                  TRATO AUTOS
//-------------------------------------------------------------------------------------------
         get("/cars",(request,response)->
            AutoController.getCars(request, response),
         new MustacheTemplateEngine()
        ); 
       
        get("/cars/:patent", (request,response) -> 
            AutoController.getCarsPatente(request,response),
            new MustacheTemplateEngine()
        );

//-------------------------------------------------------------------------------------------
//                     TRATO CAMIONETAS
//-------------------------------------------------------------------------------------------
         get("/trucks",(request,response)->
            CamionetaController.getTrucks(request,response),
            new MustacheTemplateEngine()
        );

        get("/trucks/:patent", (request,response) -> 
            CamionetaController.getTrucksPatente(request,response),
            new MustacheTemplateEngine()
        );


//-------------------------------------------------------------------------------------------
//                   TRATO MOTOCICLETAS
//-------------------------------------------------------------------------------------------
         get("/motocicles", (request,response) ->
            MotocicletaController.getMotocicles(request,response),
            new MustacheTemplateEngine()
        );

        get("/motocicles/:patent", (request,response) -> 
            MotocicletaController.getMotociclesPatente(request,response),
            new MustacheTemplateEngine()
        );


//-------------------------------------------------------------------------------------------
//                      TRATO OTRO TIPOS DE VEHICULOS
//-------------------------------------------------------------------------------------------
        get("/others", (request,response) ->
            OtrosVehiculosController.getOthers(request,response),
            new MustacheTemplateEngine()
        );

        get("/others/:patent", (request,response) -> 
            OtrosVehiculosController.getOthersPatente(request,response),
            new MustacheTemplateEngine()
        );


//-------------------------------------------------------------------------------------------
//                             TRATO PUBLICACION
//-------------------------------------------------------------------------------------------
        get("/newPosts", (request,response) -> 
            {return PublicacionController.getnewPublicacion(request, response);}
        );

        post("/posts", (request,response) ->
            PublicacionController.postsPublicacion(request, response, usuario.getRole())
         );
         
        get("/posts",(request, response) -> 
            PublicacionController.getpostsPublicacion(request, response),
            new MustacheTemplateEngine()    
        );

 
        get("/post/:id", (request,response) -> 
            PublicacionController.getpostidPublicacion(request, response),
            new MustacheTemplateEngine()    
        );


//-------------------------------------------------------------------------------------------
//                                TRATO LAS PREGUNTAS
//-------------------------------------------------------------------------------------------
        get("/newQuestions", (request,response) -> 
            {return QuestionController.getnewQuestion(request, response);}
        );

        post("/questions", (request,response) ->
            QuestionController.postQuestion(request, response, usuario.getRole())
        );
         
        get("/questions",(request, response) -> 
             QuestionController.getQuestion(request, response),
            new MustacheTemplateEngine()    
        );

 
        get("/question/:id", (request,response) -> 
            QuestionController.getQuestionId(request, response),
            new MustacheTemplateEngine()    
        );


//-------------------------------------------------------------------------------------------
//                                TRATO LAS RESPUESTAS
//-------------------------------------------------------------------------------------------
        get("/newAnswers", (request,response) -> 
            {return AnswerController.getnewAnswer(request, response);}
        );

        post("/answers", (request,response) ->
            AnswerController.postAnswer(request, response, usuario.getRole())
        );
         
        get("/answers",(request, response) -> 
             AnswerController.getAnswer(request, response),
            new MustacheTemplateEngine()    
        );

 
        get("/answer/:id", (request,response) -> 
            AnswerController.getAnswerId(request, response),
            new MustacheTemplateEngine()    
        );

        after ((request,response)-> {
        Base.close();           
        });
   }
}
