/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclientproject;

import chatprojectcommon.ClientInterface;
import chatprojectcommon.Message;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author mohamed hesham
 */
public class ClientImp extends UnicastRemoteObject implements ClientInterface{
    
    String email;//email of this user
    
    public ClientImp()throws RemoteException{
    }

    @Override
    public void receiveMessage(Message msg) throws RemoteException {
        System.out.println("rec:"+msg.getBody());
        MainControllerClient.getInstance().appenedMsgToChat(msg);
    }
    @Override
     public void receiveAnnouncment(String announ) throws RemoteException{
         System.out.println("announ" + announ);
     
     }

    
}
