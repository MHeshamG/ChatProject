package ui;

import chatclientproject.MainControllerClient;
import chatprojectcommon.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class ChatUiFXMLDocumentController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // the first step to make after the chat scene is visible is to show the freinds list  
        setFriendsList();
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