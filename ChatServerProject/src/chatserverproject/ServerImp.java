/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserverproject;

import chatprojectcommon.ClientInterface;
import chatprojectcommon.Message;
import chatprojectcommon.ServerInterface;
import chatprojectcommon.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mohamed hesham
 */
public class ServerImp extends UnicastRemoteObject implements ServerInterface {

    HashMap<String, ClientInterface> usersHashMap;

    public ServerImp() throws RemoteException {

    }

    @Override
    public void signup(User user) throws RemoteException {
        MainControllerServer.getInstance().signup(user);
    }

    @Override
    public boolean login(String email, String password) throws RemoteException {
        return MainControllerServer.getInstance().login(email, password);
    }

    @Override
    public boolean sendRequest(String senderEmail, String receiverEmail) {
        //TODO save request at database requests table
        MainControllerServer.getInstance().AddRequest(senderEmail, receiverEmail);
        return false;
    }

    @Override
    public ArrayList<User> getRequestsList(String email) throws RemoteException {

        return null;
    }

    @Override
    public ArrayList<User> getFriendsList(String email) throws RemoteException {

        return null;
    }

    @Override
    public void sendMessage(Message msg) throws RemoteException {
        System.out.println(msg.getBody()); 
    }
}
