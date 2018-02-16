/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserverproject;

import chatprojectcommon.ClientInterface;
import chatprojectcommon.Message;
import chatprojectcommon.User;
import database.FreindTableOperations;
import database.RequestTableOperations;
import database.UserTableOperations;
import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamed hesham
 */
public class MainControllerServer {
    
    private static MainControllerServer mainControllerServerObj;
    private HashMap<String, ClientInterface> usersHashMap;
    
    private MainControllerServer(){
        usersHashMap=new HashMap<>();
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
    
    public void register(String email,ClientInterface cc){
        usersHashMap.put(email, cc);
        System.out.println(email);
        System.out.println(usersHashMap.get(email));
    }
    
    public void setOnline(String email){
        UserTableOperations.getInstance().setUserOnlineStatus(email);
    }
    
    public ArrayList<User> getFriendsList(String email){
        return FreindTableOperations.getInstance().selectFriends(email);
    }
    
    public void AddRequest(String senderEmail,String receiverEmail){
        //TODO save request in database using RequetsTableOperation
        
        RequestTableOperations.getInstance().sendRequest(senderEmail, receiverEmail);
        //System.out.println(senderEmail+" , "+receiverEmail);
    }
    
    public void sendMessage(Message msg){
        try {
            usersHashMap.get(msg.getTo()).receiveMessage(msg);
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
