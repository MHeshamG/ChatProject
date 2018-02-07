/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclientproject;

import chatprojectcommon.User;

/**
 *
 * @author mohamed hesham
 */
public class MainControllerClient {
    
    private static MainControllerClient mainControllerClient;
    
    private MainControllerClient(){
        
    }
    
    public static MainControllerClient getInstance(){
        
        if(mainControllerClient==null){
            mainControllerClient=new MainControllerClient();
        }
        return mainControllerClient;
    }
    
    public boolean login(String email,String password){
        //TODO call login method at server
        System.out.println(email+" "+password);
        return false;
    }
    public boolean signUp(User user){
        // call signup method at server
        //System.out.println();
        return false;
    }
}
