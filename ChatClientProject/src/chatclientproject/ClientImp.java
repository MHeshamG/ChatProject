/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclientproject;

import chatprojectcommon.ClientInterface;
import chatprojectcommon.GroupMsg;
import chatprojectcommon.Message;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

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
       // MainControllerClient.getInstance().appenedMsgToChat(msg);
       MainControllerClient.getInstance().createChatObj(msg.getFrom());
       MainControllerClient.getInstance().appenedReceivedMessageToChatObj(msg);
    }
    @Override
     public void receiveAnnouncment(String announ) throws RemoteException{
         //System.out.println("announ" + announ);
         MainControllerClient.getInstance().receiveAnn(announ);
     }

    @Override
    public void reciveFile(byte[] data, int offset, int len, String from, String fileName) throws RemoteException {
         try {
            FileOutputStream output=new FileOutputStream("src/received_files/"+fileName,true);
            output.write(data,offset,len);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClientImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void receiveMessageGroup(Message msg) throws RemoteException {
        if(!MainControllerClient.getInstance().getToGroup().equals(""))
            MainControllerClient.getInstance().appenedMsgToChat(msg);
    }

    @Override
    public void receiveGroup(GroupMsg g) throws RemoteException {
        MainControllerClient.getInstance().appenedGroup(g);
    }
    }
    
