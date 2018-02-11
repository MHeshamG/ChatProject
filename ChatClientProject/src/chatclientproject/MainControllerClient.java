/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclientproject;

import chatprojectcommon.ServerInterface;
import chatprojectcommon.User;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.ChatUiFXMLDocumentController;

/**
 *
 * @author mohamed hesham
 */
public class MainControllerClient {
    
    private static MainControllerClient mainControllerClient;
    private ServerInterface server;
    private String email;// email of the user of the app
    private ChatUiFXMLDocumentController chatUiController;
    
    private MainControllerClient(){
        
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public static MainControllerClient getInstance(){
        
        if(mainControllerClient==null){
            mainControllerClient=new MainControllerClient();
        }
        return mainControllerClient;
    }
    
    public void connectToServer(){
        try {
            Registry reg=LocateRegistry.getRegistry(2090);
            server=(ServerInterface)reg.lookup("service");
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public boolean login(String email,String password){
        //TODO call login method at server
        System.out.println(email+" "+password);
        return false;
    }
    public boolean signUp(User user){
        try {
            // call signup method at server
            //System.out.println();
            server.signup(user);
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * connects to the server and send a friend requests on this email
     * @param email email of the person here the add request will be sent to
     */
    public void sendRequest(String receiverEmail){
        try {
            //TODO call server sendRequest method
            server.sendRequest(email, receiverEmail);
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(email);
    }
    
    /**
     * function to get this user friends list from the server
     * @param email of the user of the app 
     * @return ArrayList of friends
     */
    public ArrayList<User> getFriendsList(){
        //TODO call the server getFriendsList function
        
        //demo method
        
        
        return demo();
    }
    
    public void setUi(ChatUiFXMLDocumentController chatUiFXMLDocumentController){
        this.chatUiController=chatUiFXMLDocumentController;
    }
    //testing method for friends list
    private ArrayList<User> demo(){
         ArrayList<User> users =new ArrayList<User>();
        for(int i=0;i<10;i++){
            users.add(new User("Mohamed"));
        }
        return users;
    }
}
