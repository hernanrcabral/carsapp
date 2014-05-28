package com.unrc.app;

import org.javalite.activejdbc.Base;
import com.unrc.app.models.User;

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
        User user = new User();
        user.set("first_name","Marilyn");
        user.set("last_name", "Monroe");
		user.set("email","monroe@hotmail.com");
		user.set("pass","mmonroe112");
        // user.set("dob", "1935-12-06");
        user.saveIt();

        //User.createIt("first_name", "Marcelo", "last_name", "Uva");

        Base.close();
         //get("/hello",(request, response) -> {
         // return "Hello World!";
     	// });
   }
}



/*package com.unrc.app;

import org.javalite.activejdbc.Base;
import com.unrc.app.models.Vehicle;
import com.unrc.app.models.User;
import com.unrc.app.models.Post;
import com.unrc.app.models.Answer;
import com.unrc.app.models.Question;
//import com.unrc.app.models.Car;
import com.unrc.app.models.Rate;
import com.unrc.app.models.City;
/*
/**
 * Hello world!
 *
 */
/*
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello cruel World!" );

        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_development", "root", "root");

        City city = new City();
        city.set("postal_code", "5923");
        city.set("name","General Deheza");
        city.saveIt();

        User user = new User();
        user.set("first_name", "Marilyn");
        user.set("last_name", "Monroe");
         user.set("email", "mmonroe@gmail.com");
		user.set("pass","monroe");
        user.saveIt();

        User unNuevo=User.createIt("first_name", "Marcelo", "last_name", "Uva","email","muva@gmail.com","pass","muva");
		unNuevo.add(city);
		unNuevo.saveIt();
// FALTA TABLA DE CAR
  //      Car car = new Car();
        Vehicle vehicle = new Vehicle();
        vehicle.set("kind","307");
        vehicle.set("patent","HDK526");
        vehicle.set("price","12.321");
        vehicle.set("mark","Fiat");
        vehicle.set("description","Combustible: Diesel, Color: Negro, Transmisión: Manual,  Puertas: 4, Dirección: Hidráulica. Impecable. Sin uso. Muy pocos kilómetros. Excelente estado.  Doble airbags, frenos a disco en las cuatro ruedas, levanta cristales delanteros y traseros, alarma, llantas de aleación. Papeles al día. Listo para transferir.");

   //     car.set("doors","3","transmission","Manual","direction","Hidraulica");
        user.add(vehicle);
    //    vehicle.add(car);
        user.saveIt();
        vehicle.saveIt();
      //  car.saveIt();
        
        Post post = new Post();
        post.set("description","Vendo Fiat 307 bastante usado");
        user.add(post);
        post.saveIt();
        user.saveIt();
        
        Question question = new Question();
        question.set("description","Me interesa,¿Cuando podria verlo?");
        post.add(question);
        post.add(unNuevo);
        question.saveIt();
        post.saveIt();
        
        Answer answer = new Answer();
        answer.set("description","Estoy todos los  dias en Av. Sabatini 3021. Te espero");
        question.add(answer);
        answer.saveIt();
        question.saveIt();

		Rate rate = new Rate();
        rate.set("point","7");
        rate.saveIt();

        Base.close();
    }
}
*/
