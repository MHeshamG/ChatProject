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
    public void sendRequest(String senderEmail, String receiverEmail) {
        //TODO save request at database requests table
        MainControllerServer.getInstance().AddRequest(senderEmail, receiverEmail);
    }

    @Override
    public ArrayList<User> getRequestsList(String email) throws RemoteException {

        return null;
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
    public boolean requestToSendFile(String fileName, String to) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendMessageFile(byte[] data, int offset, int len, String fileName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void confirmRequest(String senderEmail, String receiverEmail) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletRequest(String senderEmail, String receiverEmail) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOnline(String email) {
        MainControllerServer.getInstance().setOnline(email);
    }

    @Override
    public void register(String email,ClientInterface cc) throws RemoteException {
        MainControllerServer.getInstance().register(email,cc);
    }
}
