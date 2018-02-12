/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserverproject;

import chatprojectcommon.User;
import database.DatabaseHandler;
import database.UserTableOperations;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        User user = new User("d", "f", "q", "f", "h");
        
        MainControllerServer.getInstance().bindService();

       // UserTableOperations.getInstance().insertUser(user);
//        UserTableOperations.getInstance().selectUser("f", "h");
        
        launch(args);
    }

}
