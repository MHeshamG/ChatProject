/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclientproject;

import chatprojectcommon.GroupMsg;
import chatprojectcommon.Message;
import chatprojectcommon.ServerInterface;
import chatprojectcommon.User;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import notificationmanager.NotificationHandler;
import ui.ChatUiFXMLDocumentController;
import util.ChatObj;

/**
 *
 * @author mohamed hesham
 */
public class MainControllerClient {

    private static MainControllerClient mainControllerClient;
    private ServerInterface server;
    private String email;// email of the user of the app
    private ChatUiFXMLDocumentController chatUiController;
    private HashMap<String, ChatObj> chats; //represents the chats between individuals and groups
    private ClientImp cc;
    private String toEmail;// who am i chatting
    private HashMap<String, GroupMsg> groupChats;
    private String toGroup="";

    boolean file_result = false;

    private MainControllerClient() {
        chats = new HashMap<>();
        groupChats=new HashMap<>();
        try {
            cc = new ClientImp();
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        System.out.println("<>:" + email);
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
        toGroup="";
    }

    public static MainControllerClient getInstance() {

        if (mainControllerClient == null) {
            mainControllerClient = new MainControllerClient();
        }
        return mainControllerClient;
    }

    public void connectToServer() {
        try {
            Registry reg = LocateRegistry.getRegistry(2090);
            server = (ServerInterface) reg.lookup("service");
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean login(String email, String password) {
        Boolean loginResult = false;
        try {
            //TODO call login method at server
            //System.out.println(email+" "+password);
            loginResult = server.login(email, password);
            if (loginResult) {
                server.register(email, cc);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loginResult;
    }

    public boolean signUp(User user) {
        try {
            // call signup method at server
            //System.out.println();
            server.signup(user);
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void setOnline() {
        try {
            server.setOnline(email);
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * connects to the server and send a friend requests on this email
     *
     * @param email email of the person here the add request will be sent to
     */
    public boolean sendRequest(String receiverEmail) {
        boolean result = true;
        try {
            //TODO call server sendRequest method
            System.out.println(email);
            result = server.sendRequest(email, receiverEmail);
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * function to get this user friends list from the server
     *
     * @param email of the user of the app
     * @return ArrayList of friends
     */
    public ArrayList<User> getFriendsList() {
        ArrayList<User> list = null;
        try {
            //TODO call the server getFriendsList function

            //demo method
            list = server.getFriendsList(email);
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void setUi(ChatUiFXMLDocumentController chatUiFXMLDocumentController) {
        this.chatUiController = chatUiFXMLDocumentController;
    }

    public void setFriendChattingName(String name) {
        chatUiController.setChattingFriendName(name);
    }

    public void renderMessagesToUi(String ToEmail) {
        chatUiController.addMessagesToChatBox(chats.get(ToEmail));
    }

    public void clearVbox() {
        chatUiController.clearVbox();
    }

    ///////////////////////requests////////////////////////////////////////////////
    public ArrayList<User> getRequestsList() {
        ArrayList<User> list = null;
        try {
            list = server.getRequestsList(email);
//            System.out.println("request list: "+list.get(0).getName());
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void confirmRequest(String recieverEmail) {
        try {
            server.confirmRequest(recieverEmail, email);
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRequest(String recieverEmail) {
        try {
            server.deletRequest(recieverEmail, email);
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    public void sendMessage(Message msg) {
        try {
            server.sendMessage(msg);
            // chats.get(toEmail).appenedMessage(msg);
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void appenedReceivedMessageToChatObj(Message msg) {
        System.out.println("message from:" + msg.getFrom());
        //System.out.println("chatobj:"+ chats.get(msg.getTo()));
        chats.get(msg.getFrom()).appenedMessage(msg);
        System.out.println(msg.getFrom() + " " + chats.get(msg.getFrom()).getSize());

        NotificationHandler.getInstance().shOwNewMessageNotification(msg.getFrom(), 1);
        if (toEmail != null && toEmail.equals(msg.getFrom())) {
            System.out.println("talking to each other");
            System.out.println("x" + toEmail);
            System.out.println("x1" + msg.getFrom());
            appenedMsgToChat(msg);
        }
        
    }

    public void appenedSentMessageToChatObj(Message msg) {
        chats.get(msg.getTo()).appenedMessage(msg);
        System.out.println("message to: " + msg.getTo());
        System.out.println(msg.getTo() + " " + chats.get(msg.getTo()).getSize());
    }

    public void createChatObj(String ToEmail) {
        if (chats.get(ToEmail) == null) {
            System.out.println("created chat obj " + ToEmail);
            chats.put(ToEmail, new ChatObj());
        }
        // toEmail=ToEmail;
    }

    public void appenedMsgToChat(Message msg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                chatUiController.appenedMsgToChat(msg);
            }
        });
    }

    public void sendFile(Message msg, File file) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                FileInputStream input = null;
                byte data[] = new byte[16*1024];
                int Rbytes;
                try {

                    input = new FileInputStream(file);
                    while ((Rbytes = input.read(data)) > 0) {
                        //System.out.println("receiving");
                        server.sendMessageFile(data, 0, Rbytes, email, toEmail, file.getName());
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        input.close();
                    } catch (IOException ex) {
                        Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

    }

    //*****************************group part******************************************//
    // adds group to groups hashmap
    public void addGroup(String name) {
        groupChats.put(name, new GroupMsg(name));
    }

   

    public GroupMsg getGroup(String name) {
        return groupChats.get(name);
    }
    public void sendGroupToServer(String name){
        try {
            System.out.println(name);
            server.sendGroup(getGroup(name));
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setToGroup(String g){
        toGroup=g;
    }
    public String getToGroup(){
        return toGroup;
    }
    public void sendMessageToGroup(Message msg){
        System.out.println(toGroup);
        System.out.println(msg.getBody());
        try {
            server.sendMessageToGroup(toGroup,msg);
        } catch (RemoteException ex) {
            Logger.getLogger(MainControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
