/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationmanager;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author DR Gamal
 */
public class NotificationFXMLDocumentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView image;
    @FXML
    private Text textheader;
    @FXML
    private Text textbody;
    @FXML
    private AnchorPane anchorpane;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //imgs[0]= ;
        //imgs[1]= ;
        
    }    
    public void setImage(String img){
       Image imag = new Image(new File(img).toURI().toString(), 180, 180, true, true);
       image.setImage(imag);
    }
    public void setTextHeader(String text){
        textheader.setText(text);
    }
    public void setTextBody(String text){
        textbody.setText(text);
    }
}
