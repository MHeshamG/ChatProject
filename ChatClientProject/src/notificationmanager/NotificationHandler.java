/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationmanager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author mohamed hesham
 */
public class NotificationHandler {

    private static NotificationHandler handler;
    private Notifications notificationsBuilder;
    
    
    private NotificationHandler() {
       notificationsBuilder=Notifications.create();
    }
    
    
    
    public static  NotificationHandler getInstance(){
        
        if(handler==null){
            handler=new NotificationHandler();
        }
        return handler;
    }
    
    public void shOwNewMessageNotification(String msg){
     
        createNotification("New Message", msg,null, null);
    }
    
    private void createNotification(String title,String msg,Node node,EventHandler<ActionEvent> event){
        notificationsBuilder.title(title)
              .text(msg)
              .hideAfter(Duration.seconds(5))
              .graphic(node)
              .darkStyle()
              .position(Pos.BOTTOM_RIGHT)
              .onAction(event);
      notificationsBuilder.show();
    }
}
