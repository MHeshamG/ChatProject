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
import javafx.util.Callback;

/**
 *
 * @author DR Gamal
 */
public class CustomRequestListFactory implements Callback<ListView<User>,ListCell<User>>{
    
    @Override
    public ListCell<User> call(ListView<User> param) {
        return new CustomRequestListFactory.CustomListCell();
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
            listItem=loader.load(getClass().getResource("RequestListFXMLDocument.fxml").openStream());
            RequestListFXMLDocumentController listItemController=loader.getController();
            
            if(user.getGender().equals("f"))
                listItemController.setProfilePic(new File("src/imgs/female.png"));
            else
                listItemController.setProfilePic(new File("src/imgs/male.jpg"));
            
            listItemController.setUserName(user.getUserName());
            listItemController.setEmail(user.getEmail());
            
            } catch (IOException ex) {
                Logger.getLogger(CustomListFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listItem;
        }
        
    }
}
