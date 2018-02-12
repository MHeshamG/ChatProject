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
                        + DatabaseContract.UserTableContract.userName + " ','  "
                      + DatabaseContract.UserTableContract.gender + " ','  "
                     + DatabaseContract.UserTableContract.status + " ','  "
                     + DatabaseContract.UserTableContract.onlineStatus + " ','  "
                     + DatabaseContract.UserTableContract.profilePic  +
                     "from "  +  DatabaseContract.UserTableContract.tableName + " "
                     + "where " + DatabaseContract.UserTableContract.id + " in " + " "
                     
                     + "select  " + DatabaseContract.FriendTableContract.friendId + " "
                     + "from  " + DatabaseContract.FriendTableContract.tableName + " "
                     + "where  " + DatabaseContract.FriendTableContract.userId + "= "
                     + UserTableOperations.emailToId(email)  ;
             
            ArrayList<User> friends=new ArrayList<User>();
            
            
             ResultSet rs;
             rs= DatabaseHandler.getInstance().select(query);
             while(rs.next())
             {
                 
            /* User user=new User( 
                     rs.getString(DatabaseContract.UserTableContract.userName),
                     rs.getString(DatabaseContract.UserTableContract.gender),
                     rs.getString(DatabaseContract.UserTableContract.email), 
                     rs.getString(DatabaseContract.UserTableContract.status), 
                     rs.getBoolean(DatabaseContract.UserTableContract.onlineStatus),
                     rs.getString(DatabaseContract.UserTableContract.profilePic)
                     
                   ) ;*/
            // friends.add(user);
                     
               
             }
         } catch (SQLException ex) {
             Logger.getLogger(FreindTableOperations.class.getName()).log(Level.SEVERE, null, ex);
         }
          
         
          
         
         
         
                
      }
    
        
    
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

