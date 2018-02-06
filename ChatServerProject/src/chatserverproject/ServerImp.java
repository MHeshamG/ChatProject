/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserverproject;

import chatprojectcommon.ServerInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author mohamed hesham
 */
public class ServerImp extends UnicastRemoteObject implements ServerInterface{
    
    public ServerImp()throws RemoteException{
    
    }
}
