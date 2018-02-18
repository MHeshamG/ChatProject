/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserverproject;

import chatprojectcommon.ClientInterface;
import chatprojectcommon.GroupMsg;
import chatprojectcommon.Message;
import chatprojectcommon.ServerInterface;
import chatprojectcommon.User;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamed hesham
 */
public class ServerImp extends UnicastRemoteObject implements ServerInterface {

    

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
      return  MainControllerServer.getInstance().AddRequest(senderEmail, receiverEmail);
    }

    @Override
    public ArrayList<User> getRequestsList(String email) throws RemoteException {

        return MainControllerServer.getInstance().getRequestsList(email);
    }

    @Override
    public ArrayList<User> getFriendsList(String email) throws RemoteException {

        return MainControllerServer.getInstance().getFriendsList(email);
    }

    @Override
    public void sendMessage(Message msg) throws RemoteException {
        System.out.println(msg.getBody()); 
        MainControllerServer.getInstance().sendMessage(msg);
    }

    

    @Override
    public void sendMessageFile(byte[] data, int offset, int len,String from,String to, String fileName) throws RemoteException {
        MainControllerServer.getInstance().sendFile(data,offset,len, from, to, fileName);
    }

    @Override
    public void confirmRequest(String senderEmail, String receiverEmail) throws RemoteException {
        MainControllerServer.getInstance().addFriends(senderEmail, receiverEmail);
    }

    @Override
    public void deletRequest(String senderEmail, String receiverEmail) throws RemoteException {
        MainControllerServer.getInstance().deleteRequest(senderEmail,receiverEmail);
    }

    @Override
    public void setOnline(String email) {
        MainControllerServer.getInstance().setOnline(email);
    }

    @Override
    public void register(String email,ClientInterface cc) throws RemoteException {
        MainControllerServer.getInstance().register(email,cc);
    }

    @Override
    public void sendGroup(GroupMsg g) throws RemoteException {
        MainControllerServer.getInstance().addGroup(g);
    }

    @Override
    public void sendMessageToGroup(String groupName,Message msg) throws RemoteException {
        MainControllerServer.getInstance().sendMessageToGroup(groupName,msg);
    }

   
}
