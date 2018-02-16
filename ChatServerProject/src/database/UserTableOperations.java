/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import chatprojectcommon.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamed hesham
 */
public class UserTableOperations {

    private static UserTableOperations userTableOperationsObj;

    public static UserTableOperations getInstance() {

        if (userTableOperationsObj == null) {
            userTableOperationsObj = new UserTableOperations();
        }

        return userTableOperationsObj;
    }

    public void insertUser(User user){
        String query;

        query = "insert into " + DatabaseContract.UserTableContract.tableName + "("
                +DatabaseContract.UserTableContract.userName
                + "," + DatabaseContract.UserTableContract.name
                + "," + DatabaseContract.UserTableContract.password
                + "," + DatabaseContract.UserTableContract.email
                + "," + DatabaseContract.UserTableContract.gender 
                +","+ DatabaseContract.UserTableContract.online
                +","+ DatabaseContract.UserTableContract.status
                +","+ DatabaseContract.UserTableContract.country
                +") "
                + "values("   
                + "'" + user.getUserName() +"'"
                + "," + "'"+ user.getName() + "'"
                + "," + "'" +user.getPassword() + "'"
                + "," + "'"+ user.getEmail()+"'"
                + "," + "'" +user.getGender()+ "'"
                +","+"'1'"
                +","+"'0'"
                +",'"+user.getCountry()
                + "');";
        
        System.out.println(query);
        DatabaseHandler.getInstance().insert(query);
    }

    public boolean selectUser(String email, String password){
        String query;
        boolean checkIfExist = true;
        query = "select * from " + DatabaseContract.UserTableContract.tableName
                + " where " + DatabaseContract.UserTableContract.email + " = " + "'" + email + "'"
                + "and " + DatabaseContract.UserTableContract.password + "=" + "'" + password + "'";

        System.out.println(query);
        ResultSet selectedData = DatabaseHandler.getInstance().select(query);
        try {
            checkIfExist = selectedData.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserTableOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return checkIfExist;

    }

    public int emailToId(String email){
           ResultSet rs=null;
           
           int res=0;
        try {
            String query;
           

            query = "select " + DatabaseContract.UserTableContract.id
                    + " from " + DatabaseContract.UserTableContract.tableName
                    + " where " + DatabaseContract.UserTableContract.email + "=" + "'" + email + "'";
            System.out.println(query);

            rs = DatabaseHandler.getInstance().select(query);
           if(rs.next()){
                res=rs.getInt(DatabaseContract.UserTableContract.id); 
        }else{
           res=-1;
           } 
        }
        catch (SQLException ex) {
            Logger.getLogger(UserTableOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
               return res;    
    }

    public boolean setUserOnlineStatus(String email){
        String query;

        query = "update " + DatabaseContract.UserTableContract.tableName
                + " set " + DatabaseContract.UserTableContract.online + "=" + '1'
                + " where " + DatabaseContract.UserTableContract.email + "=" + "'" + email + "'";
        System.out.println(query);
        DatabaseHandler.getInstance().update(query);

        return true;
    }
    
}
