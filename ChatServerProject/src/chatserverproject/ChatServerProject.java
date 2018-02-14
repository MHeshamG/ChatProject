/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserverproject;

import chatprojectcommon.User;
import database.*;
import database.UserTableOperations;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.*;

/**
 *
 * @author mohamed hesham
 */
public class ChatServerProject extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ui/FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//test inseert        
    // User user = new User( 5,"hesham", "h", "male",  "h@","123");
//        
//        MainControllerServer.getInstance().bindService();
//
      // UserTableOperations.getInstance().insertUser(user);

       //  RequestTableOperations.sendRequest("esraa@","ali@");

//       // UserTableOperations.getInstance().selectUser("f", "h");
         ArrayList<User> obj;
         obj=new ArrayList<User>();
         obj= FreindTableOperations.selectFriends("esraa@");
         
         if(FreindTableOperations.selectFriends("esraa@").isEmpty())
         {
         System.out.println(" empty");
      
         }
         else 
         {
         System.out.println(" not empty");
            System.out.println(FreindTableOperations.selectFriends("esraa@").get(0).getEmail());
         }
          Iterator itr=obj.iterator();
         while(itr.hasNext())
         {
             
             System.out.println(itr.next());
         }
         
     

    //  RequestTableOperations obj=new RequestTableOperations();
       //obj.deleteRequest("esraa@","ali@");
       //obj.comfirmRequest("esraa@", "ali@");

/* test emailtoid
      int id;
      id=UserTableOperations.emailToId("ali@");
       System.out.println(id);


*/

// test send request
    
     //  MainControllerServer.getInstance().AddRequest("esraa@", "ali@"); */
        launch(args);
       
    }

}
