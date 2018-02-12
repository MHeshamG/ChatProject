/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*; 
import java.util.logging.Level;
import java.util.logging.Logger;
public class DatabaseHandler 
{    
    
    private static DatabaseHandler databaseHandlerObj;
    
    private DatabaseHandler(){
    
       connect();
    
    };
   
    
     static Statement stmt;
    
    public static DatabaseHandler  getInstance()
    {
    
        if(databaseHandlerObj==null)
            databaseHandlerObj=new DatabaseHandler();
        
        return databaseHandlerObj;
          
    }
    
    
    public void connect()
    {
           Connection con=null; 
        try{
              Class.forName("com.mysql.jdbc.Driver");
              con =DriverManager.getConnection("jdbc:mysql://localhost:3306/javaprojectdatabase","root","");
              
              stmt =con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        
  
        }
    catch(SQLException e ){
          
        e.printStackTrace();
    
    }
        
    catch(Exception ex){
          
         ex.printStackTrace();
    }  
        
   
    }
    
    
    public void insert(String query)
    {
    
        try {
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    
    public void update (String query)
    {
      
          try {
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
    }
    
    
    
    
    
      public void delete  (String query)
    {
    
        try {
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      
      
      
      
      
         public  ResultSet  select(String query) 
    {
          ResultSet selectedData=null;
       
          try {
              if(stmt==null)
                  System.out.println("yes");
                 selectedData=stmt.executeQuery(query);
                 
          } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          return selectedData ;
                
    }
    
}
