/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
public class DatabaseHandler 
{    
    
    private static DatabaseHandler databaseHandlerObj;
    private DatabaseHandler(){};
    
    public static DatabaseHandler  getInstance()
    {
    
        databaseHandlerObj=new DatabaseHandler();
        return databaseHandlerObj;
          
    }
    
    
    public void insert(String query)
    {
    
          
    
    }
    
    public void update (String query)
    {
      
           
        
    }
    
      public void delete  (String query)
    {
    
            
    }
    
         public  void  select(String query) 
    {
    
       
        
                
                
    }
    
}
