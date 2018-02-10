package ui;

import chatclientproject.MainControllerClient;
import chatprojectcommon.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import notificationmanager.NotificationHandler;

public class ChatUiFXMLDocumentController implements Initializable{
    
    @FXML
    private TextField addFriendText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        // freinds tab partition initialize
            
            // the first step to make after the chat scene is visible is to show the freinds list  
            setFriendsList();
            
            //add listener to add friend text field
            handleAddFriendTextField();
            
            //test Notification
            NotificationHandler.getInstance().shOwNewMessageNotification("hi there");
            
        /**************************************************/

        //groups tab partition initialize

        /*************************************************/

        //requests partition initialize

        /************************************************/

        // create Group partition initialize

        /************************************************/

        //profile partition initialize

        /***********************************************/
    }
    
    // freinds tab partition
    ObservableList<User> friendsList;
    
    @FXML
    ListView contactList;
    
    public void setFriendsList(){
        friendsList=FXCollections.observableArrayList(MainControllerClient.getInstance().getFriendsList());
        contactList.setItems(friendsList);
        contactList.setCellFactory(new CustomListFactory());
    }
    
    private void handleAddFriendTextField(){
        addFriendText.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    String email=addFriendText.getText();
                    if(event.getCode()==KeyCode.ENTER && !email.equals("")){
                        MainControllerClient.getInstance().sendRequest(email);
                    }
                }
            });
    }
    /**************************************************/
    
    //groups tab partition
    
    /*************************************************/
    
    //requests partition
    
    /************************************************/
    
    // create Group partition
    
    /************************************************/
    
    //profile partition
    
    /***********************************************/
}