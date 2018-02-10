package ui;

import chatclientproject.MainControllerClient;
import chatprojectcommon.User;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import notificationmanager.NotificationHandler;

public class ChatUiFXMLDocumentController implements Initializable{
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        // freinds tab partition initialize
            setControls();
            
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
    private TextField addFriendText; 
    @FXML
    private TextField msgText; 
    @FXML
    ListView contactList;
    @FXML
    private ImageView send;
    @FXML
    private  ColorPicker colorpicker;
    @FXML
    private ChoiceBox fontchoice;
    
    private String hex2="000000";
    
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
    private void setControls(){
        
        
         send.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                   msgText.setText("");
                   System.out.println("xxxx");
            }
        });
        
         colorpicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                  Color color  = colorpicker.getValue();
                  hex2 = "#" + Integer.toHexString(color.hashCode()); 
                  msgText.setStyle("-fx-background-radius:15px; -fx-text-inner-color:"+hex2+";");
            }
        });
         
         List<java.lang.String> fonts = Font.getFamilies();
         fontchoice.getItems().addAll(fonts);
         
         fontchoice.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                 String font = (String) fontchoice.getSelectionModel().getSelectedItem();
                 System.out.println(font);
                 msgText.setStyle("-fx-background-radius:15px;-fx-text-inner-color:"+hex2+";" +"-fx-font-family:"+font+";");
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