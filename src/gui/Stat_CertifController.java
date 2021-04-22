/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Certification;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import services.CertificationService;

/**
 * FXML Controller class
 *
 * @author ouaji
 */
public class Stat_CertifController implements Initializable {

    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private BarChart<?, ?> chart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
         CertificationService us= new CertificationService();
        List<Certification> uss = us.readAll();
      
        XYChart.Series set= new XYChart.Series<>();
        int i=0;
        int m=0;
        int e=0;
        for(Certification u:uss)
        {
if(u.getNom().equals("Informatique")){i=i+1;}
else if(u.getNom().equals("Mecanique") ){m=m+1;}
else if(u.getNom().equals("Electronique") ){e=e+1;}


        }
        set.getData().add(new XYChart.Data("Informatique",i));
        set.getData().add(new XYChart.Data("Mecanique",m));
                set.getData().add(new XYChart.Data("Electronique",e));

        // TODO
        System.out.println(i+e+m);
        chart.getData().addAll(set);
        
        
        
        
        
    }    
    
}
