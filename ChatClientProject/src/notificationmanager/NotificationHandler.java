/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationmanager;

import java.io.IOException;
import chatprojectcommon.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import ui.ListItemFXMLDocumentController;

/**
 *
 * @author mohamed hesham
 */
public class NotificationHandler {

    private static NotificationHandler handler;
    private Notifications notificationsBuilder;
    
    Parent notifywindow=null;
    NotificationFXMLDocumentController notificationcontroller;
    
    String[] imgs = {"src/imgs/interface.png","src/imgs/friendrequestnotify.png","src/imgs/female.png","src/imgs/male.jpg"};
    
    
    private NotificationHandler() {
        
        try {
            notificationsBuilder=Notifications.create();
            FXMLLoader loader=new FXMLLoader();
            notifywindow =loader.load(getClass().getResource("NotificationFXMLDocument.fxml").openStream());
            notificationcontroller =loader.getController();
        } catch (IOException ex) {
            Logger.getLogger(NotificationHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public static  NotificationHandler getInstance(){
        
        if(handler==null){
            handler=new NotificationHandler();
        }
        return handler;
    }
    
    public void shOwNewMessageNotification(User user,String msg,int flag){
        
        switch(flag){
            case 1:
                notificationcontroller.setImage(imgs[0]);
                notificationcontroller.setTextHeader("New Message recieved");
                notificationcontroller.setTextBody("from: "+ user.getUserName());
                createNotification(notifywindow, null);
            break;
            case 2:
                notificationcontroller.setImage(imgs[1]);
                notificationcontroller.setTextHeader("New Friend Request");
                notificationcontroller.setTextBody("from: "+ user.getUserName());
                createNotification(notifywindow, null);
            break;
            case 3:
                if(user.getGender().equals("M")){
                    notificationcontroller.setImage(imgs[3]);
                    notificationcontroller.setTextHeader("Online User");
                    notificationcontroller.setTextBody(user.getUserName());
                    createNotification(notifywindow, null);
                }
                else if(user.getGender().equals("F")){
                    notificationcontroller.setImage(imgs[2]);
                    notificationcontroller.setTextHeader("Online User");
                    notificationcontroller.setTextBody(user.getUserName());
                    createNotification(notifywindow, null);
                }
            break;
            
        }
    }
    
    private void createNotification(Node node,EventHandler<ActionEvent> event){
        notificationsBuilder.hideAfter(Duration.seconds(5))
              .graphic(node)
              .position(Pos.BOTTOM_RIGHT)
              .onAction(event);
      notificationsBuilder.show();
    }
}
