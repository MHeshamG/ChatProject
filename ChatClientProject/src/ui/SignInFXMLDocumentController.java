/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import chatclientproject.MainControllerClient;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.imageio.metadata.IIOInvalidTreeException;

/**
 *
 * @author mohamed hesham
 */
public class SignInFXMLDocumentController implements Initializable {
    
    @FXML
    private TextField EmailLogin;
    
    @FXML
    private TextField PasswordLogin;
    
    @FXML
    private Button LoginButton;
    @FXML
    private Button signUpButton;
    @FXML
    private Text invalidText;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        LoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                login();
            }
        });
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage stage = (Stage) signUpButton.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("SignUpFXMLDocument.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                } catch (IOException ex) {
                    Logger.getLogger(SignInFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    private void login(){
        String email=EmailLogin.getText();
        String password=PasswordLogin.getText();
        
       if( MainControllerClient.getInstance().login(email, password)){
            try {
                MainControllerClient.getInstance().setEmail(email);
                MainControllerClient.getInstance().setOnline();
                Stage stage = (Stage) signUpButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("ChatUiFXMLDocument.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                
            } catch (IOException ex) {
                Logger.getLogger(SignInFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       else{
           invalidText.setText("Invalid email or password");
       }
    }
    
}
