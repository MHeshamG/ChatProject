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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamed hesham
 */
public class MainControllerClient {
    
    private static MainControllerClient mainControllerClient;
    private ServerInterface server;
    
    private MainControllerClient(){
        
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
}
