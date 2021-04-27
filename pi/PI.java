/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import Models.*;
import Services.*;
import Utils.DataSource;
import java.io.IOException;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Dell
 */
public class PI extends Application {
   public static Stage primaryStage = null;
    
    
    public void start(Stage s) throws IOException {
        
        
        primaryStage=s;
        primaryStage.setTitle("MindSpace");
//        Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherCandidat.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("../gui/Login.fxml"));
        Scene scene = new Scene(root,750,500);
//        scene.getStylesheets().add(getClass().getResource("/gui/Design.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
