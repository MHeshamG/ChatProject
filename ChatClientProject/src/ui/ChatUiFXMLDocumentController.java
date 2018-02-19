package ui;

import chatclientproject.MainControllerClient;
import chatprojectcommon.GroupMsg;
import chatprojectcommon.Message;
import chatprojectcommon.User;
import java.io.File;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import notificationmanager.NotificationHandler;
import util.ChatObj;

public class ChatUiFXMLDocumentController implements Initializable {

    private Stage stage;

    @FXML
    private TextField addFriendText;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        MainControllerClient.getInstance().setUi(this);

        // freinds tab partition initialize
        setControls();
        setFriendsList();
        setRequestsList();

        // the first step to make after the chat scene is visible is to show the freinds list and create chatObjs  
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            updateFriendsList();
                            updateRequestList();
                        }
                    });
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ChatUiFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

        //add listener to add friend text field
        handleAddFriendTextField();

        //test Notification
//        NotificationHandler.getInstance().shOwNewMessageNotification("hello");
        vbox.setSpacing(8);
        
        

        /**
         * ***********************************************
         */
        //groups tab partition initialize
        setGroupTextFields();
        //* ***********************************************/
        //requests partition initialize
        /**
         * *********************************************
         */
    }

    // freinds tab partition
    ObservableList<User> friendsList;

    @FXML
    private TextField msgText;
    @FXML
    ListView contactList;
    @FXML
    private ImageView fileButton;
    @FXML
    private ImageView send;
    @FXML
    private ColorPicker colorpicker;
    @FXML
    private ChoiceBox fontchoice;
    @FXML
    VBox vbox;
    @FXML
    private Text friendChattingText;
    @FXML
    private ChoiceBox fontsize;


    private String hex2 = "#ffffff";
    private String font = "Arial";
    private String fonttsize;

    public void setFriendsList() {
        ArrayList<User> list = MainControllerClient.getInstance().getFriendsList();
        int friendsLength = list.size();
        
        friendsList = FXCollections.observableArrayList(list);
        contactList.setItems(friendsList);
        contactList.setCellFactory(new CustomListFactory());
       
    }

    private void updateFriendsList() {
        friendsList.clear();
        friendsList.addAll(MainControllerClient.getInstance().getFriendsList());
    }

    private void handleAddFriendTextField() {
        addFriendText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String email = addFriendText.getText();
                if (event.getCode() == KeyCode.ENTER && !email.equals("")) {
                    if (!email.equals(MainControllerClient.getInstance().getEmail())) {
                        if (MainControllerClient.getInstance().sendRequest(email)) {
                            new Alert(Alert.AlertType.INFORMATION, "Request sent").show();
                        } else {
                            new Alert(Alert.AlertType.INFORMATION, "Invalid email").show();
                        }
                    } else {
                        new Alert(Alert.AlertType.INFORMATION, "Invalid email").show();
                    }
                    addFriendText.setText("");
                }
            }
        });
        
    }

    public void setChattingFriendName(String name) {
        friendChattingText.setText(name);
    }

    // setting the controls of messaging
    private void setControls() {
        send.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!showDialog())return;
                String msg = msgText.getText();
                if (!msg.equals("") && MainControllerClient.getInstance().getToGroup().equals("")) {
                    System.out.println(msg);
                    //create message
                    Message message = new Message(msg, MainControllerClient.getInstance().getEmail(),
                            MainControllerClient.getInstance().getToEmail(), font, hex2, 0);
                    addMessage(message);
                    sendMsg(message);
                } else {
                    if (!msg.equals("")) {
                        Message message = new Message(msg, MainControllerClient.getInstance().getEmail(),
                                MainControllerClient.getInstance().getToGroup(), font, hex2, 0);
                        MainControllerClient.getInstance().sendMessageToGroup(message);
                        addMessage(message);
                    }
                }
            }
        });

        colorpicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                Color color = colorpicker.getValue();
                hex2 = "#" + Integer.toHexString(color.hashCode());
                msgText.setStyle("-fx-background-radius:15px;-fx-font-family:" + font + ";"+"-fx-text-fill:"+hex2+";-fx-font-size:"+fonttsize+";");
            }
        });

        List<java.lang.String> fonts = Font.getFamilies();
        fontchoice.getItems().addAll(fonts);

        fontchoice.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                font = (String) fontchoice.getSelectionModel().getSelectedItem();
                System.out.println(font);
                msgText.setStyle("-fx-background-radius:15px; -fx-font-family:" + font + ";"+"-fx-text-fill:"+hex2+";-fx-font-size:"+fonttsize+";");
            }
        });

         List<java.lang.String> fontsizee = new ArrayList<>();
            fontsizee.add("20");
            fontsizee.add("21");
            fontsizee.add("22");
            fontsizee.add("23");
            fontsize.getItems().addAll(fontsizee);

        fontsize.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                 fonttsize = (String) fontsize.getSelectionModel().getSelectedItem();
                 System.out.println(fonttsize);
                msgText.setStyle("-fx-background-radius:15px; -fx-font-family:" + font + ";"+"-fx-text-fill:"+hex2+";-fx-font-size:"+fonttsize+";");
            }
        });    
        
        fileButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                File file = openFileChooser();
                if(!showDialog())return;
                if (file != null) {
                    MainControllerClient.getInstance().sendFile(new Message(null, MainControllerClient.getInstance().getEmail(),
                            MainControllerClient.getInstance().getToEmail(), null, null, Integer.parseInt(fonttsize)), file);
                }
            }
        });
    }

    //open file chooser to select file to be sent
    private File openFileChooser() {
        stage = (Stage) addFriendText.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        return fileChooser.showOpenDialog(stage);
    }

    //Adds the message i want to send to the vbox 
    private void addMessage(Message msg) {
        HBox box = new HBox();
        box.setAlignment(Pos.BASELINE_RIGHT);
        Text txt = new Text();
        txt.setText(msg.getBody());
        txt.setStyle("-fx-fill:" + hex2 + ";-fx-font-family:" + font + ";-fx-font-size:"+msg.getFontSize()+";");
        txt.getStyleClass().add("textWhite");
        TextFlow txtFlow = new TextFlow(txt);
        txtFlow.getStyleClass().add("textFlowMessageFlipped");
        box.getChildren().add(txtFlow);
        vbox.getChildren().add(box);
        msgText.setText("");

    }

    //adds the message i recieved to vbox
    public void appenedMsgToChat(Message msg) {
        HBox box = new HBox();
        box.setAlignment(Pos.BASELINE_LEFT);
        Text txt = new Text();
        txt.setText(msg.getBody());
        txt.setStyle("-fx-fill:" + hex2 + ";-fx-font-family:" + font + ";-fx-font-size:"+msg.getFontSize()+";");
        TextFlow txtFlow = new TextFlow(txt);
        txtFlow.getStyleClass().add("textFlowMessage");
        box.getChildren().add(txtFlow);
        vbox.getChildren().add(box);
    }

    public void addMessagesToChatBox(ChatObj chat) {
        for (Message msg : chat.getMessages()) {
            if (!msg.getFrom().equals(MainControllerClient.getInstance().getEmail())) {
                appenedMsgToChat(msg);
            } else {
                addMessage(msg);
            }
        }
    }

    public void sendMsg(Message message) {

        //send to server
        MainControllerClient.getInstance().sendMessage(message);
        //appened to chat obj
        MainControllerClient.getInstance().appenedSentMessageToChatObj(message);
    }

    public void clearVbox() {
        vbox.getChildren().clear();
    }

    public boolean showDialog(){
        if(MainControllerClient.getInstance().getToEmail().equals("") && MainControllerClient.getInstance().getToGroup().equals("")){
                    new Alert(Alert.AlertType.ERROR,"Choose friend first").show();
                    return false;
                }
        return true;
    }
    
    /**
     * ***********************************************
     */
    //groups tab partition
    @FXML
    private TextField GroupNameTextField;
    @FXML
    private TextField AddMemberTextField;
    @FXML
    private Button createGroupButton;
    @FXML
    private ListView contactListCreateGroup;
    @FXML
    private ObservableList<GroupMsg> groupList;

    public void setGroupTextFields() {
        GroupNameTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String groupName = GroupNameTextField.getText();
                if (event.getCode() == KeyCode.ENTER && !groupName.equals("")) {
                    // MainControllerClient.getInstance().sendRequest(email);
                    System.out.println(LocalTime.now() + ";" + groupName);
                    GroupMsg g = new GroupMsg(groupName);
                    MainControllerClient.getInstance().addGroup(groupName);
                    if (groupList == null) {
                        groupList = FXCollections.observableArrayList();
                        contactListCreateGroup.setCellFactory(groupCallback);
                        groupList.add(g);
                    }
                }
            }
        });
        AddMemberTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String groupName = GroupNameTextField.getText();
                String memberEmail = AddMemberTextField.getText();
                if (event.getCode() == KeyCode.ENTER && !memberEmail.equals("")) {
                    // MainControllerClient.getInstance().sendRequest(email);
                    //System.out.println(memberEmail);
                    MainControllerClient.getInstance().getGroup(groupName).addToMembers(memberEmail);
                    new Alert(Alert.AlertType.INFORMATION, memberEmail + " added to " + groupName).show();
                    AddMemberTextField.setText("");
                }
            }
        });
        createGroupButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String groupName = GroupNameTextField.getText();
                MainControllerClient.getInstance().sendGroupToServer(groupName);
                new Alert(Alert.AlertType.INFORMATION, "group created").show();
                GroupNameTextField.setText("");
                contactListCreateGroup.setItems(groupList);
            }
        });
    }
    public void appenedGroup(GroupMsg g){
        if(groupList==null)
            groupList=FXCollections.observableArrayList();
        groupList.add(g);
        contactListCreateGroup.setItems(groupList);
        contactListCreateGroup.setCellFactory(groupCallback);
        contactListCreateGroup.refresh();
    }
    ////////////////////////////////////////////////////////////////////////////

    //requests partition
    @FXML
    private ListView requestList;

    ObservableList<Object> requestsList;

    private void setRequestsList() {
        ArrayList<User> list = MainControllerClient.getInstance().getRequestsList();
        requestsList = FXCollections.observableArrayList(list);

        requestList.setCellFactory(new CustomRequestListFactory());
        requestList.setItems(requestsList);
    }

    private void updateRequestList() {
        requestsList.clear();
        requestsList.addAll(MainControllerClient.getInstance().getRequestsList());
    }

    /**
     * *********************************************
     */
    
    
    Callback<ListView<GroupMsg>, ListCell<GroupMsg>> groupCallback=new Callback<ListView<GroupMsg>, ListCell<GroupMsg>>() {
                            @Override
                            public ListCell<GroupMsg> call(ListView<GroupMsg> param) {
                                return new ListCell<GroupMsg>() {
                                    @Override
                                    
                                    protected void updateItem(GroupMsg item, boolean empty) {
                                        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                                        if (item != null && !empty) {
                                            Text text = new Text(item.getGroupName());
                                            text.setStyle("-fx-fill:#ffffff;");
                                            text.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                                @Override
                                                public void handle(MouseEvent event) {
                                                    System.out.println("group pressed");
                                                    MainControllerClient.getInstance().setToGroup(item.getGroupName());
                                                    clearVbox();
                                                    setChattingFriendName(item.getGroupName());
                                                }
                                            });
                                            setGraphic(text);
                                        } else {
                                            setGraphic(null);
                                        }
                                    }
                                };
                            }

                        };
}
