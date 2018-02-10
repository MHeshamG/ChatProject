/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclientproject;

import chatprojectcommon.ClientInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author mohamed hesham
 */
public class ClientImp extends UnicastRemoteObject implements ClientInterface{
    
    String email;//email of this user
    
    public ClientImp(String email)throws RemoteException{
        this.email=email;
    }
    
    
}
