/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatprojectcommon;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author mohamed hesham
 */
public interface ServerInterface extends Remote {
    public void signup(User user) throws RemoteException;
    public boolean login(String email,String password) throws RemoteException;
    public boolean sendRequest(String senderEmail,String receiverEmail) throws RemoteException;
    public ArrayList<User> getRequestsList(String email)throws RemoteException;
    public ArrayList<User> getFriendsList(String email) throws RemoteException;
}
