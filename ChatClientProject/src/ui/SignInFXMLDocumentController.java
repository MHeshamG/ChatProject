/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import chatclientproject.MainControllerClient;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        LoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                login();
            }
        });
    }
    
    private void login(){
        String email=EmailLogin.getText();
        String password=PasswordLogin.getText();
        
        MainControllerClient.getInstance().login(email, password);
    }
    
}
