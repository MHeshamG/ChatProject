/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import chatprojectcommon.*;
import java.util.ArrayList;

/**
 *
 * @author mohamed hesham
 */
public class FreindTableOperations {
    
     public void selectFriends(String email)
     {
         try {
             String query ;
             query="select "  +  DatabaseContract.UserTableContract.id  + "  ' , ' "
                     + DatabaseContract.UserTableContract.email + " ','  "
                     + DatabaseContract.UserTableContract.profilePic  +
                     "from "  +  DatabaseContract.UserTableContract.tableName + " "
                     + "where " + DatabaseContract.UserTableContract.id + " in " + " "
                     
                     + "select  " + DatabaseContract.FriendTableContract.friendId + " "
                     + "from  " + DatabaseContract.FriendTableContract.tableName + " "
                     + "where  " + DatabaseContract.FriendTableContract.userId + "= "
                     + UserTableOperations.emailToId(email)  ;
             
            ArrayList<User> frinds=new ArrayList<User>();
            
             ResultSet rs;
             rs= DatabaseHandler.getInstance().select(query);
             while(rs.next())
             {
                 
                    
               
             }
         } catch (SQLException ex) {
             Logger.getLogger(FreindTableOperations.class.getName()).log(Level.SEVERE, null, ex);
         }
          
         
          
         
         
         
                
      }
    
        
    
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

