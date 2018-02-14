/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserverproject;

import chatprojectcommon.User;
import database.RequestTableOperations;
import database.UserTableOperations;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author mohamed hesham
 */
public class MainControllerServer {
    
    private static MainControllerServer mainControllerServerObj;
    
    private MainControllerServer(){
    
    }
    
    public static MainControllerServer getInstance(){
        
        if(mainControllerServerObj==null)
            mainControllerServerObj=new MainControllerServer();
        
        return mainControllerServerObj;
    }
    
    public void bindService(){
        try{
        ServerImp obj = new ServerImp();
        Registry reg = LocateRegistry.createRegistry(2090);
        reg.rebind("service", obj);
        }
        catch(RemoteException ex){ 
            ex.printStackTrace();
        }    
    }
    
    public void signup(User user){
        UserTableOperations.getInstance().insertUser(user);
        //System.out.println(user.getEmail());
    }
    
    public boolean login(String email,String password){
       return UserTableOperations.getInstance().selectUser(email,password);
    }
    
    public void AddRequest(String senderEmail,String receiverEmail){
        //TODO save request in database using RequetsTableOperation
        
        RequestTableOperations.sendRequest(senderEmail, receiverEmail);
        System.out.println(senderEmail+" , "+receiverEmail);
    }
}
