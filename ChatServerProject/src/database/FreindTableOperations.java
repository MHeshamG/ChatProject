package database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import chatprojectcommon.*;
import java.util.ArrayList;

public class FreindTableOperations {

    private static FreindTableOperations friendTableOperations;
    
    public static FreindTableOperations getInstance(){
        
        if(friendTableOperations==null)
            friendTableOperations=new FreindTableOperations();
        
        return friendTableOperations;
    }
    
    public ArrayList<User> selectFriends(String email) {

        ArrayList<User> friends = null;
        try {
            String query;
            query = "select * " 
                    + "from " + DatabaseContract.UserTableContract.tableName + " "
                    + "where " + DatabaseContract.UserTableContract.id + " in " + " "
                    + "("
                    + "select  " + DatabaseContract.FriendTableContract.friendId + " "
                    + "from  " + DatabaseContract.FriendTableContract.tableName + " "
                    + "where  " + DatabaseContract.FriendTableContract.userId + "= "
                    + "'"+ UserTableOperations.getInstance().emailToId(email) + "');";
            
            String query2;
            query2 = "select * " 
                    + "from " + DatabaseContract.UserTableContract.tableName + " "
                    + "where " + DatabaseContract.UserTableContract.id + " in " + " "
                    + "("
                    + "select  " + DatabaseContract.FriendTableContract.userId + " "
                    + "from  " + DatabaseContract.FriendTableContract.tableName + " "
                    + "where  " + DatabaseContract.FriendTableContract.friendId + "= "
                    + "'"+ UserTableOperations.getInstance().emailToId(email) + "');";
            System.out.println(query2);

            friends = new ArrayList<User>();

            
            ResultSet rs = DatabaseHandler.getInstance().select(query);
            while (rs.next()) {

                User user = new User(
                        rs.getString(DatabaseContract.UserTableContract.name),
                        rs.getString(DatabaseContract.UserTableContract.userName),
                        rs.getString(DatabaseContract.UserTableContract.gender),
                        rs.getString(DatabaseContract.UserTableContract.email),
                        rs.getInt(DatabaseContract.UserTableContract.status),
                        rs.getBoolean(DatabaseContract.UserTableContract.online));
                friends.add(user);

            }
            
            ResultSet rs2 = DatabaseHandler.getInstance().select(query2);
            while (rs2.next()) {

                User user = new User(
                        rs2.getString(DatabaseContract.UserTableContract.name),
                        rs2.getString(DatabaseContract.UserTableContract.userName),
                        rs2.getString(DatabaseContract.UserTableContract.gender),
                        rs2.getString(DatabaseContract.UserTableContract.email),
                        rs2.getInt(DatabaseContract.UserTableContract.status),
                        rs2.getBoolean(DatabaseContract.UserTableContract.online));
                friends.add(user);

            }
        } catch (SQLException ex) {
            Logger.getLogger(FreindTableOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return friends;

    }

    public void insertFriends(String user1,String user2){
        String query;
               query="insert into " + DatabaseContract.FriendTableContract.tableName + " ("
                       +DatabaseContract.FriendTableContract.userId+" , "
                       +DatabaseContract.FriendTableContract.friendId+")"
                       + "values(" + UserTableOperations.getInstance().emailToId(user1) 
                       + ","  +UserTableOperations.getInstance().emailToId(user2) + 
                           ")";
              System.out.println(query);
              DatabaseHandler.getInstance().insert(query);
    }
}
