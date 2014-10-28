package com.unrc.app.models;

import org.javalite.activejdbc.Model;

import com.unrc.app.ElasticSearch;
import java.util.*;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;

public class User extends Model {
 static {
      validatePresenceOf("first_name", "last_name","email", "role", "password");
  }
  
  	public String email (){
  		return this.getString("email");
 	}

	public String id (){
  		return String.valueOf(this.getInteger("id"));
  	}

  	public String first_name (){
  		return this.getString("first_name");
  	}

    	public String last_name (){
      		return this.getString("last_name");
    	}
	
	public String role (){
      		return this.getString("role");
    	}
   
    public String password (){
  		return this.getString("password");
  	}

    public void afterCreate(){

      Map<String, Object> json = new HashMap<>();
      json.put("name", this.get("first_name"));
      json.put("surname",this.get("last_name"));
      json.put("email", this.get("email"));
      json.put("password", this.get("password"));
      json.put("role", this.get("role"));	
      json.put("id", this.getId());

      ElasticSearch.client().prepareIndex("users", "user",String.valueOf(this.getId()))
                            .setSource(json)
                            .execute()
                            .actionGet();
      getUserElasticsearch();
      
    }

     public void getUserElasticsearch(){
         GetResponse response = ElasticSearch.client().prepareGet("users", "user", String.valueOf(this.getId()))
                                                   .execute()
                                                   .actionGet();
            
         System.out.println("source---"+response.getSource());
     }  

    //Busca si el id del Usuario existe
    public static Boolean existUser(int user_id){
        return (User.first("id = ? ", user_id) != null);
    }
   
    
    // Busca el id del Usuario, si existe, lo borra de 
    public static void deleteUser(int user_id){
     if(existUser(user_id)){
         User.delete("id = ?", user_id);
         Vehicle.delete("user_id=?", user_id);
         Post.delete("user_id=?", user_id);
         Answer.delete("user_id=?", user_id);
         Question.delete("user_id=?", user_id);
        }
        
    }

}

