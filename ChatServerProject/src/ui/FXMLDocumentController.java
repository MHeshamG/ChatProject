/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import chatserverproject.MainControllerServer;
import com.sun.javafx.charts.ChartLayoutAnimator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    private int numberofonline = 3;
    private int numberofoffline = 6;
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                whiteanchorpane.getChildren().clear();
                System.out.println("service is now open");
                MainControllerServer.getInstance().bindService();
                }
        });
         stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                whiteanchorpane.getChildren().clear();
                System.out.println("service is now closed");
                MainControllerServer.getInstance().unbindService();
                }
        });
        viewusers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                whiteanchorpane.getChildren().clear();
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
                        
            }
        });
        statistics.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                whiteanchorpane.getChildren().clear();
                PieChart chart = new PieChart();
                ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
                data.add(new PieChart.Data("Online",150));
                data.add(new PieChart.Data("Offline",55));
                data.add(new PieChart.Data("Busy",300));
                data.add(new PieChart.Data("Away",100));
                
                chart.setData(data);
                whiteanchorpane.getChildren().addAll(chart);
            }
        });
        
    }    
    
}
