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
    private Statement stmt;
    private ResultSet selectedData=null;
   
    private DatabaseHandler(){
    
       connect();
    
    };
   
    
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
                 // DriverManager.registerDriver(new OracleDriver());
            // Second Step -----> open connection
            //  con is null if no connection
           // con = DriverManager.getConnection(
             //       "jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "hr");
            System.out.println("connection "+ con);
              stmt =con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        
  
        }
    catch(SQLException e ){
          
        e.printStackTrace();
    
    }
        
    catch(Exception ex){
          
         ex.printStackTrace();
    }  
        
   
    }
    
    
    public boolean insert(String query)
    {
    boolean result=true;
        try {
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            result=false;
            //Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    return result;
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
