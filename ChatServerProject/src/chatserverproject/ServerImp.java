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
}   
