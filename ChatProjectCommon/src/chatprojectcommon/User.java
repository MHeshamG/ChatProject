/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatprojectcommon;

import java.io.Serializable;

/**
 *
 * @author mohamed hesham
 */
public class User implements Serializable{
    private  String name;
    private  String userName;
    private  String gender;
    private  String email;
    private  String password;
    private  int status;
    private boolean onlineStatus;
    private String profilepic;
    private String country;
    

    
    
    public User(){
    
    }
    public User(String name){
        this.name=name;
    }

    public User(String name, String userName, String gender, String email, int status, boolean onlineStatus) {
        this.name = name;
        this.userName = userName;
        this.gender = gender;
        this.email = email;
        this.status = status;
        this.onlineStatus = onlineStatus;
    }

       public User(String username, String gender, String email, int status, boolean onlineStatus ,String profilePic ) {
        this.userName = name;
        this.gender = gender;
        this.email = email;
        this.status = status;
        this.onlineStatus = onlineStatus;
        this.profilepic=profilepic;
       
    }

    public User(String name, String userName, String gender, String email, String password,String country) {
        this.name = name;
        this.userName = userName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.country=country;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
     public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(boolean onlineStatus) {
        this.onlineStatus = onlineStatus;
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
