/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import chatserverproject.MainControllerServer;
import com.sun.javafx.charts.ChartLayoutAnimator;
import database.UserTableOperations;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author mohamed hesham
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button start;
    @FXML
    private Button stop;
    @FXML
    private Button viewusers;
    @FXML
    private Button statistics;
    @FXML
    private AnchorPane whiteanchorpane;
    @FXML
    private Button sendannouncment;
    
    private int numberofonline = 3;
    private int numberofoffline = 6;
    //IntegerProperty first = new SimpleIntegerProperty(1);
    //IntegerProperty second = new SimpleIntegerProperty(1);
    DoubleProperty female = new SimpleDoubleProperty(UserTableOperations.getInstance().numberOfFemaleUsers());
    DoubleProperty male = new SimpleDoubleProperty(UserTableOperations.getInstance().numberOfMaleUsers());
    DoubleProperty onlineusers = new SimpleDoubleProperty(UserTableOperations.getInstance().numberOfOnlineUsers());
    DoubleProperty offlineusers = new SimpleDoubleProperty(UserTableOperations.getInstance().numberOfOfflineUsers());
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                whiteanchorpane.getChildren().clear();
                Text start = new Text();
                start.setFont(new Font(20));
                start.setWrappingWidth(200);
                start.setLayoutX(20);
                start.setLayoutY(20);
                start.setTextAlignment(TextAlignment.JUSTIFY);
                start.setText("Service is now open");
                whiteanchorpane.getChildren().add(start);
                System.out.println("service is now open");
                MainControllerServer.getInstance().bindService();
                }
        });
         stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                whiteanchorpane.getChildren().clear();
                Text stop = new Text();
                stop.setFont(new Font(20));
                stop.setWrappingWidth(200);
                stop.setLayoutX(20);
                stop.setLayoutY(20);
                stop.setTextAlignment(TextAlignment.JUSTIFY);
                stop.setText("Service is now closed");
                whiteanchorpane.getChildren().add(stop);
                System.out.println("service is now closed");
                MainControllerServer.getInstance().unbindService();
                }
        });
        viewusers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
    /*            whiteanchorpane.getChildren().clear();
                Text total = new Text();
                Text totalnum = new Text();
                Text offlineusers = new Text();
                Text offlineWord = new Text();
                Text onlineWord = new Text();
                Text onlineusers = new Text();
                
                total.setFont(new Font(20));
                total.setWrappingWidth(200);
                total.setLayoutX(20);
                total.setLayoutY(20);
                total.setTextAlignment(TextAlignment.JUSTIFY);
                total.setText("total");
                //////////////Total word
                totalnum.setFont(new Font(20));
               // totalnum.setWrappingWidth(200);
                totalnum.setLayoutX(20);
                totalnum.setLayoutY(40);
                totalnum.setTextAlignment(TextAlignment.JUSTIFY);
                totalnum.setText(Integer.toString(numberofoffline+numberofonline));
                /////////////total number
                
                offlineWord.setFont(new Font(20));
               // offlineusers.setWrappingWidth(200);
                offlineWord.setLayoutX(80);
                offlineWord.setLayoutY(20);
                offlineWord.setTextAlignment(TextAlignment.JUSTIFY);
                offlineWord.setText("Offline");
                //////////offline word
                
                offlineusers.setFont(new Font(20));
               // offlineusers.setWrappingWidth(200);
                offlineusers.setLayoutX(80);
                offlineusers.setLayoutY(40);
                offlineusers.setTextAlignment(TextAlignment.JUSTIFY);
                offlineusers.setText(Integer.toString(numberofoffline));
                ////////////offline number
                
                onlineWord.setFont(new Font(20));
               // offlineusers.setWrappingWidth(200);
                onlineWord.setLayoutX(150);
                onlineWord.setLayoutY(20);
                onlineWord.setTextAlignment(TextAlignment.JUSTIFY);
                onlineWord.setText("Online");
                ////////// online word
                
                onlineusers.setFont(new Font(20));
                // offlineusers.setWrappingWidth(200);
                onlineusers.setLayoutX(150);
                onlineusers.setLayoutY(40);
                onlineusers.setTextAlignment(TextAlignment.JUSTIFY);
                onlineusers.setText(Integer.toString(numberofonline));
                whiteanchorpane.getChildren().addAll(total,totalnum,offlineWord,offlineusers,onlineWord,onlineusers);
      */
                 whiteanchorpane.getChildren().clear();
                PieChart chart = new PieChart();
               /* Double noofusers = UserTableOperations.getInstance().numberOfOnlineUsers();
                Double noofuserson = UserTableOperations.getInstance().numberOfOfflineUsers();
                Double noofuserss = UserTableOperations.getInstance().numberOfFemaleUsers();
                Double nodd = UserTableOperations.getInstance().numberOfMaleUsers();
                Double nfd = UserTableOperations.getInstance().numberOfTotalUsers();
                System.out.println("online" + noofusers + " offline" + noofuserson + "females "+ noofuserss + " males " + nodd + "total "+ nfd);
*/              
               PieChart.Data pie = new PieChart.Data("online",UserTableOperations.getInstance().numberOfOnlineUsers());
               pie.pieValueProperty().bind(onlineusers);
               PieChart.Data pie2 = new PieChart.Data("offline",UserTableOperations.getInstance().numberOfOfflineUsers());
               pie2.pieValueProperty().bind(offlineusers);
               
                ObservableList<PieChart.Data> data = FXCollections.observableArrayList(pie,pie2);
                //data.add(new PieChart.Data("Online",150));
               // data.add(new PieChart.Data("Offline",55));
                //data.add(new PieChart.Data("Busy",300));
                //data.add(new PieChart.Data("Away",100));
                chart.setData(data);
                new Thread(new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        while (true) {                            
                            Thread.sleep(1000);
                            onlineusers.set(UserTableOperations.getInstance().numberOfOnlineUsers());
                            offlineusers.set(UserTableOperations.getInstance().numberOfOfflineUsers());
                            //System.out.println(female.getValue());
    
                        }
                        
                        
                        //return null;
                    }
                }).start();
                
                whiteanchorpane.getChildren().addAll(chart);
            }
    
            
        });
        statistics.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                whiteanchorpane.getChildren().clear();
                PieChart chart = new PieChart();
               /* Double noofusers = UserTableOperations.getInstance().numberOfOnlineUsers();
                Double noofuserson = UserTableOperations.getInstance().numberOfOfflineUsers();
                Double noofuserss = UserTableOperations.getInstance().numberOfFemaleUsers();
                Double nodd = UserTableOperations.getInstance().numberOfMaleUsers();
                Double nfd = UserTableOperations.getInstance().numberOfTotalUsers();
                System.out.println("online" + noofusers + " offline" + noofuserson + "females "+ noofuserss + " males " + nodd + "total "+ nfd);
*/              
               PieChart.Data pie = new PieChart.Data("female",UserTableOperations.getInstance().numberOfFemaleUsers());
               pie.pieValueProperty().bind(female);
               PieChart.Data pie2 = new PieChart.Data("male",UserTableOperations.getInstance().numberOfMaleUsers());
               pie2.pieValueProperty().bind(male);
               
                ObservableList<PieChart.Data> data = FXCollections.observableArrayList(pie,pie2);
                //data.add(new PieChart.Data("Online",150));
               // data.add(new PieChart.Data("Offline",55));
                //data.add(new PieChart.Data("Busy",300));
                //data.add(new PieChart.Data("Away",100));
                chart.setData(data);
                new Thread(new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        while (true) {                            
                            Thread.sleep(1000);
                            female.set(UserTableOperations.getInstance().numberOfFemaleUsers());
                            male.set(UserTableOperations.getInstance().numberOfMaleUsers());
                            System.out.println(female.getValue());
    
                        }
                        
                        
                        //return null;
                    }
                }).start();
                
                whiteanchorpane.getChildren().addAll(chart);
            }
        });
        sendannouncment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                whiteanchorpane.getChildren().clear();
                TextField announce = new TextField();
                announce.setPromptText("Type your announcment then press enter");
                announce.setPrefColumnCount(30);
                announce.setLayoutX(25);
                announce.setLayoutY(40);
                whiteanchorpane.getChildren().add(announce);
                announce.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (event.getCode().equals(KeyCode.ENTER)){
                            String ann = announce.getText();
                            MainControllerServer.getInstance().sendAnnouncement(ann);
                       
                        }
                    }
                });
            }
        });
        
    }    
    
}
