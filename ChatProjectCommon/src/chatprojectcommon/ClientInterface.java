/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatprojectcommon;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author mohamed hesham
 */
public interface ClientInterface extends Remote{
    
    public void receiveMessage(Message msg) throws RemoteException;
    public void receiveAnnouncment(String announ) throws RemoteException;
    public void reciveFile(byte []data, int offset, int len,String from, String fileName) throws RemoteException;
    public void receiveMessageGroup(Message msg)throws RemoteException;
    public void receiveGroup(GroupMsg g)throws RemoteException;
}
