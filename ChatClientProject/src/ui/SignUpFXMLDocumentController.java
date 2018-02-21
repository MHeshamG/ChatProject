/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import Validation.Validator;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
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
    private PasswordField password;
    @FXML
    private TextField email;
    @FXML
    private RadioButton maleradiobutton;
    @FXML
    private RadioButton femaleradiobutton;
    @FXML
    private Button signupbutton;
    @FXML
    private ChoiceBox country;
    @FXML
    private Button back;

    private String count;
    final ToggleGroup group = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        maleradiobutton.setToggleGroup(group);
        femaleradiobutton.setToggleGroup(group);
        maleradiobutton.setSelected(true);
        List<java.lang.String> countries = new ArrayList<>();
        countries.add("Egypt");
        countries.add("France");
        countries.add("United States");
        country.getItems().addAll(countries);
        country.setValue("Egypt");

        country.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                count = (String) country.getSelectionModel().getSelectedItem();

            }
        });

        signupbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String genderType = "";
                if (group.getSelectedToggle() != null) {
                    if (maleradiobutton.isSelected() == true) {
                        genderType = "m";
                    } else if (femaleradiobutton.isSelected() == true) {
                        genderType = "f";
                    }
                }
                User user;
                if (Validator.isValidEmail(email.getText())
                        && Validator.isValidName(name.getText())
                        /*&& Validator.isValidPassword(password.getText())*/
                        && Validator.isValidUserName(username.getText())) {
                    user = new User(name.getText(), username.getText(), genderType, email.getText(), password.getText(), count);
                    createUser(user);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid Data").show();
                }
                //hena setter equals textfields
                //radio buttons isSelected() to check if radiobutton is checked
                // System.out.println(user.getName() + genderType);
                //createUser(user);
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage stage = (Stage) name.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("/ui/SignInFXMLDocument.fxml"));
                    Scene scene = new Scene(root, 1500, 800);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(SignUpFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void createUser(User user) {

        if (MainControllerClient.getInstance().signUp(user)) {
            goToChatScene();
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Data").show();
        }
    }

    private void goToChatScene() {
        try {
            Stage stage = (Stage) name.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/ui/ChatUiFXMLDocument.fxml"));
            Scene scene = new Scene(root, 1250, 800);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(SignUpFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
