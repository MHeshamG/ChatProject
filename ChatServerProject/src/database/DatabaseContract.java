/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author mohamed hesham
 */
public class DatabaseContract {
    
    
    public static class UserTableContract{
    
        public static String tableName="users";
        public static String id="id";
        public static String name="name";
        public static String userName="username";
        public static String email="email";
        public static String password="password";
        public static String gender="gender";
        public static String status="status";
        public static String onlineStatus="onlineStatus";

        public static String profilePic="profilePic";   
        
    }
    
    
        
    public static class FriendTableContract{
        
        public static String tableName="friends";
        public static String id ="id"; 
        public static  String userId="userId";
        public static  String friendId="friendId";
        
        
    }
    
      public static class RequesttableContract{
        
        public static String tableName="requests";
        public static  String id="id"; 
        public static  String senderId="senderId";
        public static  String recieverId="recieverId";
        
        
    }
    
    
    
    
    
}
