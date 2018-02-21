/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclientproject;

import java.util.Optional;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author mohamed hesham
 */
public class ChatClientProject extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        String Ip="";
        TextInputDialog dialog = new TextInputDialog();
 
        dialog.setTitle("Chat");
        dialog.setHeaderText("Enter your Ip:");
        dialog.setContentText("Ip:");
 
        Optional<String> result = dialog.showAndWait();
        
        if(result.isPresent()){
            Ip=result.get();
            if(!Ip.equals("")){
                MainControllerClient.getInstance().setIp(Ip);
                MainControllerClient.getInstance().connectToServer();
            }
        }
        else{
            System.out.println("close");
            System.exit(0);
        }
        
        Parent root = FXMLLoader.load(getClass().getResource("/ui/SignInFXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.show();
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                MainControllerClient.getInstance().end();
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        launch(args);
    }
    
}
