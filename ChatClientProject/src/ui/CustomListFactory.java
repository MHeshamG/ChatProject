/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import chatclientproject.MainControllerClient;
import chatprojectcommon.User;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 *
 * @author mohamed hesham
 */
public class CustomListFactory implements Callback<ListView<User>,ListCell<User>>{
    
    //private String imagePath="C:/Users/mohamed hesham/Documents/NetBeansProjects/ChatProject/ChatClientProject/src/imgs";

    @Override
    public ListCell<User> call(ListView<User> param) {
        return new CustomListCell();
    }
    
    public class CustomListCell extends ListCell<User>{

        @Override
        protected void updateItem(User item, boolean empty) {
            super.updateItem(item, empty); 
            if(item!=null && !empty){
                setGraphic(createListItem(item));
            }
            else{
                //setDisable(false);
                //AnchorPane pane=new AnchorPane();
                //pane.setPrefHeight(0.0);
                setGraphic(null);
            }
        }
        
    private Parent createListItem(User user){
            
        Parent listItem=null;
        try {
            FXMLLoader loader=new FXMLLoader();
            listItem=loader.load(getClass().getResource("ListItemFXMLDocument.fxml").openStream());
            ListItemFXMLDocumentController listItemController=loader.getController();
            
            if(user.getGender().equals("f"))
                listItemController.setProfilePic(new File("src/imgs/female.png"));
            else
                listItemController.setProfilePic(new File("src/imgs/male.jpg"));
            if(user.isOnlineStatus())
                listItemController.setOnlineState(true);
            else
                listItemController.setOnlineState(false);
            
            listItemController.setUserName(user.getUserName());
            
            switch(user.getStatus()){
                case 0:
                    listItemController.setStatus("Available");
                    break;
                case 1:
                    listItemController.setStatus("Busy");
                    break;
                case 2:
                    listItemController.setStatus("Away");
                    break;
            }
            listItem.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    MainControllerClient.getInstance().setToEmail(user.getEmail());
                }
            });
            } catch (IOException ex) {
                Logger.getLogger(CustomListFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listItem;
        }
        
    }
}
