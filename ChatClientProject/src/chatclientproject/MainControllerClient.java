/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclientproject;

import chatprojectcommon.Message;
import chatprojectcommon.ServerInterface;
import chatprojectcommon.User;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import ui.ChatUiFXMLDocumentController;
import util.ChatObj;

/**
 *
 * @author mohamed hesham
 */
public class MainControllerClient {
    
    private static MainControllerClient mainControllerClient;
    private ServerInterface server;
    private String email;// email of the user of the app
    private ChatUiFXMLDocumentController chatUiController;
    private HashMap<String,ChatObj> chats; //represents the chats between individuals and groups
    private ClientImp cc;
    private String toEmail;// who am i chatting

    
    
    private MainControllerClient(){
        chats=new HashMap<>();
        try {
            cc=new ClientImp();
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        System.out.println("<>:"+email);
    }
    
    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
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
        Boolean loginResult=false;
        try {
            //TODO call login method at server
            //System.out.println(email+" "+password);
            loginResult=server.login(email, password);
            if(loginResult){
                server.register(email, cc);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loginResult;
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
    
    public void setOnline(){
        try {
            server.setOnline(email);
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * connects to the server and send a friend requests on this email
     * @param email email of the person here the add request will be sent to
     */
    public void sendRequest(String receiverEmail){
        try {
            //TODO call server sendRequest method
             System.out.println(email);
            server.sendRequest(email, receiverEmail);
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * function to get this user friends list from the server
     * @param email of the user of the app 
     * @return ArrayList of friends
     */
    public ArrayList<User> getFriendsList(){
        ArrayList<User> list=null;
        try {
            //TODO call the server getFriendsList function
            
            //demo method
            
            
            list=server.getFriendsList(email);
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public void setUi(ChatUiFXMLDocumentController chatUiFXMLDocumentController){
        this.chatUiController=chatUiFXMLDocumentController;
    }
    
    public void sendMessage(Message msg){
        try {
            server.sendMessage(msg);
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addToChatObj(String email,Message msg){
        
    }
    public void appenedMsgToChat(Message msg){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                chatUiController.appenedMsgToChat(msg);            
            }
        });
        
    }
    public ArrayList<User> getRequestsList(){
        ArrayList<User> list=null;
        try {
            list=server.getRequestsList(email);
//            System.out.println("request list: "+list.get(0).getName());
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public void confirmRequest(String recieverEmail){
        try {
            server.confirmRequest(email, recieverEmail);
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}