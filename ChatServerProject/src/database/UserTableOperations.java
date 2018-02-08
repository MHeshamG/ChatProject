/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import chatprojectcommon.User;

/**
 *
 * @author mohamed hesham
 */
public class UserTableOperations {

    private UserTableOperations userTableOperationsObj;

    public static UserTableOperations getInstance() {

        return null;
    }

    public void insertUser(User user) {
        String query;

        query = "insert into " + DatabaseContract.UserTableContract.tableName + "(" + DatabaseContract.UserTableContract.userName
                + "," + DatabaseContract.UserTableContract.name
                + "," + DatabaseContract.UserTableContract.password
                + "," + DatabaseContract.UserTableContract.email
                + "," + DatabaseContract.UserTableContract.gender + ")"
                + "values(" + user.getUserName()
                + "," + user.getName()
                + "," + user.getPassword()
                + "," + user.getEmail()
                + "," + user.getGender() + ")";

        DatabaseHandler.getInstance().insert(query);
    }

    public boolean selectUser(String email, String password) {

        return false;
    }
}
