/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import API.sendMail;
import entity.Certification;
import entity.DemandeCertification;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.CertificationService;
import services.DemandeCertificationService;

/**
 * FXML Controller class
 *
 * @author ouaji
 */
public class AjouterDemandeCertifController implements Initializable {

    private TextField certif;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField experience;
    @FXML
    private TextField email;
    @FXML
    private Button AjouterDmndCert;
    @FXML
    private DatePicker date;
    
   DemandeCertificationService dc = new DemandeCertificationService();
     @FXML
    private ComboBox  comb_Cert;;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
      
        
    List<Certification> liste = dc.getCertification();
    ObservableList<Certification> option = FXCollections.observableArrayList(dc.getCertification());
    comb_Cert.setItems(option);
        
        
        
        
        
    }    

    @FXML
    private void AjouterDemandeCertif(ActionEvent event) throws IOException {
        
        
         LocalDate now = LocalDate.now();  
//        int b= Integer.parseInt(certif.getText());
      //  int o= (int)comb_Cert.getSelectionModel().getSelectedItem();
        
        
        int i;
         i = Integer.parseInt(comb_Cert.getSelectionModel().getSelectedItem().toString().substring(0,comb_Cert.getSelectionModel().getSelectedItem().toString().indexOf(":")));
         // System.out.println(i);
        
        
        
      

        
       DemandeCertification ce = new DemandeCertification(i,nom.getText(),prenom.getText(),Date.valueOf(date.getValue()),experience.getText(),email.getText());

        DemandeCertificationService cs = new DemandeCertificationService();

        cs.insert(ce);
      
            sendMail a = new sendMail();
        try {
            a.sendMail1(email.getText());
        } catch (Exception ex) {
            Logger.getLogger(AjouterDemandeCertifController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterDemandeCertif.fxml"));
        Parent root = loader.load();
        AjouterDemandeCertifController resultat1 = loader.getController();
        
        
        
    }
    
}
