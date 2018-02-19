/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserverproject;

import chatprojectcommon.ClientInterface;
import chatprojectcommon.GroupMsg;
import chatprojectcommon.Message;
import chatprojectcommon.User;
import database.FreindTableOperations;
import database.RequestTableOperations;
import database.UserTableOperations;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamed hesham
 */
public class MainControllerServer {
    
    private static MainControllerServer mainControllerServerObj;
    private HashMap<String, ClientInterface> usersHashMap;
    private HashMap<String,GroupMsg> groupsHashMap;
    ServerImp obj;
    Registry reg;
    boolean started=false;
    private MainControllerServer(){
        try {
            usersHashMap=new HashMap<>();
            groupsHashMap=new HashMap<>();
            obj = new ServerImp();
            reg = LocateRegistry.createRegistry(2090);
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static MainControllerServer getInstance(){
        
        if(mainControllerServerObj==null)
            mainControllerServerObj=new MainControllerServer();
        
        return mainControllerServerObj;
    }
    
    public void bindService(){
        try{
        
        reg.rebind("service", obj);
        started=true;
        }
        catch(RemoteException ex){ 
            ex.printStackTrace();
        }    
    }
     public void unbindService(){
        try{
         
        if(started)    
        reg.unbind("service");
        }
        catch(RemoteException ex){ 
            ex.printStackTrace();
        } catch (NotBoundException ex) {
            Logger.getLogger(MainControllerServer.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    public boolean signup(User user){
        return UserTableOperations.getInstance().insertUser(user);
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
        UserTableOperations.getInstance().setUserOnlineStatus(email,1);
    }
    
    public ArrayList<User> getFriendsList(String email){
        return FreindTableOperations.getInstance().selectFriends(email);
    }
    
    public ArrayList<User> getRequestsList(String email){
        return RequestTableOperations.getInstance().getRequestsList(email);
    }
    
    public boolean AddRequest(String senderEmail,String receiverEmail){
        //TODO save request in database using RequetsTableOperation
        
        return RequestTableOperations.getInstance().sendRequest(senderEmail, receiverEmail);
        //System.out.println(senderEmail+" , "+receiverEmail);
    }
    
    public void addFriends(String senderEmail, String recieverEmail){
        RequestTableOperations.getInstance().comfirmRequest(senderEmail, recieverEmail);
    }
    
    public void deleteRequest(String senderEmail,String recieverEmail){
        RequestTableOperations.getInstance().deleteRequest(senderEmail, recieverEmail);
    }
    
    public void sendMessage(Message msg){
        try {
            System.out.println(msg.getTo());
            if(usersHashMap.get(msg.getTo())!=null)
                usersHashMap.get(msg.getTo()).receiveMessage(msg);
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sendAnnouncement(String announce){
         for (ClientInterface client : usersHashMap.values()) {
             try {
                 // ...
                 client.receiveAnnouncment(announce);
             } catch (RemoteException ex) {
                 Logger.getLogger(MainControllerServer.class.getName()).log(Level.SEVERE, null, ex);
             }
            }
        
    }
    
    public void sendFile(byte[] data, int offset, int len,String from,String to, String fileName){
        try {
            System.out.println(from+" "+to);
            if(usersHashMap.get(to)!=null)
                usersHashMap.get(to).reciveFile(data, offset,  len,from, fileName);
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerServer.class.getName()).log(Level.SEVERE, null, ex);
        }
       
}
    public void addGroup(String email,GroupMsg g){
        groupsHashMap.put(g.getGroupName(), g);
        System.out.println(g.getGroupName());
        for (String object : g.getMembers()) {
            try {
                if(!object.equals(email) && usersHashMap.get(object)!=null)
                usersHashMap.get(object).receiveGroup(g);
            } catch (RemoteException ex) {
                Logger.getLogger(MainControllerServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void sendMessageToGroup(String email,String groupName,Message msg){
        System.out.println(groupName);
        System.out.println(msg);
        for (String object : groupsHashMap.get(groupName).getMembers()) {
            try {
                if(!object.equals(email) && usersHashMap.get(object)!=null)
                usersHashMap.get(object).receiveMessageGroup(msg);
            } catch (RemoteException ex) {
                Logger.getLogger(MainControllerServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void end(String email){
        UserTableOperations.getInstance().setUserOnlineStatus(email,0);
        usersHashMap.put(email,null);
    }
}