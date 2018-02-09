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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author mohamed hesham
 */
public class ListItemFXMLDocumentController implements Initializable {
    
    private Stage stage;
    
    @FXML
    Text statusText;
    
    @FXML
    AnchorPane listItem;
    
    @FXML
    Circle profilePicCircle;
    
    @FXML
    Circle onlineCircle;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public File getProfilePicImageFile(){
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        return fileChooser.showOpenDialog(stage);
    }
    public void setProfilePic(File fileImage){
        if(fileImage!=null){
        Image img=new Image("file:"+fileImage.getAbsolutePath());
        ImagePattern imagePattern= new ImagePattern(img);
        profilePicCircle.setFill(imagePattern);
    }
    }
    
    public void setOnlineState(boolean state){
        if(!state){
            onlineCircle.setFill(Color.valueOf("#ea1f46"));
        }
        else{
             onlineCircle.setFill(Color.valueOf("#4eea98"));
        }
    }
    
    public void setStatus(String status){
        statusText.setText(status);
    }
}
