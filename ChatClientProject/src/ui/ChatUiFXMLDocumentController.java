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
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import notificationmanager.NotificationHandler;

public class ChatUiFXMLDocumentController implements Initializable{
    
    private Stage stage;
    
    @FXML
    private TextField addFriendText; 
    
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
            
            vbox.setSpacing(8);
            
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
    private TextField msgText; 
    @FXML
    ListView contactList;
    @FXML
    private ImageView fileButton;
    @FXML
    private ImageView send;
    @FXML
    private  ColorPicker colorpicker;
    @FXML
    private ChoiceBox fontchoice;
    @FXML 
    VBox vbox;
    
    private String hex2="#ffffff";
    private String font="Arial";
    
    
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
    
    // setting the controls of messaging
    private void setControls(){
        
        
         send.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                   String msg=msgText.getText();
                   if(!msg.equals("")){
                       System.out.println(msg);
                       addMessageAndSend(msg);
                   }
                   System.out.println("xxxx");
            }
        });
        
         colorpicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                  Color color  = colorpicker.getValue();
                  hex2 = "#" + Integer.toHexString(color.hashCode()); 
                    msgText.setStyle("-fx-background-radius:15px;-fx-font-family:"+font+";");
            }
        });
         
         List<java.lang.String> fonts = Font.getFamilies();
         fontchoice.getItems().addAll(fonts);
         
         fontchoice.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                 font = (String) fontchoice.getSelectionModel().getSelectedItem();
                 System.out.println(font);
                 msgText.setStyle("-fx-background-radius:15px; -fx-font-family:"+font+";");
            }
        });
       
         fileButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
             @Override
             public void handle(MouseEvent event) {
                 openFileChooser();
             }
         });
    }
    
    //open file chooser to select file to be sent
    private void openFileChooser(){
        stage=(Stage)addFriendText.getScene().getWindow();
        FileChooser fileChooser=new FileChooser();
        //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        fileChooser.showOpenDialog(stage);
    }
    
    //Adds the message to vbox and sends it to server
    private void addMessageAndSend(String msg){
        HBox box=new HBox();
        box.setAlignment(Pos.BASELINE_RIGHT);
        Text txt=new Text();
        txt.setText(msg);
        txt.setStyle("-fx-fill:"+hex2+";-fx-font-family:"+font+";");
        txt.getStyleClass().add("textWhite");
        TextFlow txtFlow=new TextFlow(txt);
        txtFlow.getStyleClass().add("textFlowMessageFlipped");
        box.getChildren().add(txtFlow);
        vbox.getChildren().add(box);
        msgText.setText("");
        //send to server
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