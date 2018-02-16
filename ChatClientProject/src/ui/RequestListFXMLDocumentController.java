/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DR Gamal
 */
public class RequestListFXMLDocumentController implements Initializable {
    
    private Stage stage;
    @FXML
    private Text userName;
    
    @FXML
    AnchorPane listItem;
    
    @FXML
    Circle profilePicCircle;
    
    @FXML
    private Button accept;
    @FXML
    private Button ignore;
    @FXML
    private Label label;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        accept.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                accept.setStyle("-fx-background-color: transparent;");
                accept.setText("");
                ignore.setStyle("-fx-background-color: transparent;");
                ignore.setText("");
                label.setText("The request is accepted");
            }
        });
        ignore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                accept.setStyle("-fx-background-color: transparent;");
                accept.setText("");
                ignore.setStyle("-fx-background-color: transparent;");
                ignore.setText("");
                label.setText("The request is ignored");
            }
        });
    } 
    public void setProfilePic(File fileImage){
        if(fileImage!=null){
        Image img=new Image("file:"+fileImage.getAbsolutePath());
        ImagePattern imagePattern= new ImagePattern(img);
        profilePicCircle.setFill(imagePattern);
        }
    }
    public void setUserName(String name){
        userName.setText(name);
    }
    
}
