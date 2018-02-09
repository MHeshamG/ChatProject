/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserverproject;

import chatprojectcommon.ServerInterface;
import chatprojectcommon.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author mohamed hesham
 */
public class ServerImp extends UnicastRemoteObject implements ServerInterface{
    
    public ServerImp()throws RemoteException{
    
    }

    @Override
    public void signup(User user) throws RemoteException{
        MainControllerServer.getInstance().signup(user);
    }

    @Override
    public boolean login(String email, String password) throws RemoteException{
       return MainControllerServer.getInstance().login(email,password);    
    }

    @Override
    public void sendRequest(String senderEmail,String receiverEmail) {
        //TODO save request at database requests table
    }

    @Override
    public ArrayList<User> getRequestsList(String email) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<User> getFriendsList(String email) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}   
