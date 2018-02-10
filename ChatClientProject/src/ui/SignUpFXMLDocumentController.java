/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import chatclientproject.MainControllerClient;
import chatprojectcommon.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author mohamed hesham
 */
public class SignUpFXMLDocumentController implements Initializable {
    
   @FXML
   private TextField name;
   @FXML
   private TextField username;
   @FXML
   private TextField password;
   @FXML
   private TextField email;
   @FXML
   private RadioButton maleradiobutton;
   @FXML
   private RadioButton femaleradiobutton;
   @FXML
   private TextField country;
   @FXML
   private Button signupbutton;
    
   final ToggleGroup group = new ToggleGroup();
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         maleradiobutton.setToggleGroup(group);
         femaleradiobutton.setToggleGroup(group);
         maleradiobutton.setSelected(true);
       
         signupbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                
                String genderType = "";
                if(group.getSelectedToggle() != null){
                    if(maleradiobutton.isSelected() == true){
                        genderType = "M";
                    }
                    else if(femaleradiobutton.isSelected() == true){
                        genderType = "F";
                    }
                }
                
                User user = new User(name.getText(),username.getText(),genderType,email.getText(),password.getText());
                //hena setter equals textfields
                //radio buttons isSelected() to check if radiobutton is checked
               // System.out.println(user.getName() + genderType);
                createUser(user);
            }
        });
    }    
    private void createUser(User user){
        
       // MainControllerClient.getInstance().signUp(user);
       goToChatScene();
    }
    
    private void goToChatScene(){
       try {
           Stage stage = (Stage) name.getScene().getWindow();
           Parent root = FXMLLoader.load(getClass().getResource("/ui/ChatUiFXMLDocument.fxml"));
           Scene scene = new Scene(root,1500,800);
           stage.setScene(scene);
       } catch (IOException ex) {
           Logger.getLogger(SignUpFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}
