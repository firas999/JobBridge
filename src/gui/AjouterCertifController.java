/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Certification;
import entity.Entreprise;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import static java.nio.file.Files.list;
import java.sql.Date;
import java.time.LocalDate;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.CertificationService;

/**
 * FXML Controller class
 *
 * @author ouaji
 */
public class AjouterCertifController implements Initializable {

    
    @FXML
    private Button btn;
    @FXML
    private TextField prix;
    @FXML
    private TextField desc;
    private TextField nom;
    @FXML
    private TextField type;
    @FXML
    private DatePicker dt0;
    @FXML
    private ComboBox comb;
    @FXML
    private Label LabelMessage;
    @FXML
    private ComboBox comb_ent;
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    CertificationService dc = new CertificationService();
   
    ObservableList<String> list= FXCollections.observableArrayList("Informatique","Electronique","Mecanique");
        comb.setItems(list);
        
        
        
        List<Entreprise> liste = dc.getEntreprise();
    ObservableList<Entreprise> option = FXCollections.observableArrayList(dc.getEntreprise());
    comb_ent.setItems(option);
        
        
        
    }
    

    @FXML
    private void AjouterCertif(ActionEvent event) throws IOException {
        LocalDate now = LocalDate.now();  
        int b= Integer.parseInt(prix.getText());
         int i;
         i = Integer.parseInt(comb_ent.getSelectionModel().getSelectedItem().toString().substring(0,comb_ent.getSelectionModel().getSelectedItem().toString().indexOf(":")));
        String nomCert= comb.getSelectionModel().getSelectedItem().toString();
CertificationService cs = new CertificationService();
        

        

           if (type.getText().equals("") || desc.getText().equals("") || prix.getText().equals("") )
       {
         LabelMessage.setText("TOUS LES CHAMPS SONT OBLIGATOIRES !");
       }
      
       
        else if(0>b){
              LabelMessage.setText("Le Prix Doit etre >0 !");     
       }
       
       
      
       else if(!( Pattern.matches("[a-zA-Z]*", desc.getText()))){
           LabelMessage.setText("Description doit etre un texte !");
       }
       else{
        
        
               Certification ce = new Certification(type.getText(),Date.valueOf(dt0.getValue()),b,desc.getText(),nomCert,i);
        cs.insert(ce);
        
         
        
        
        
        
        Notifications notificationBuilder = Notifications.create()
.title("Félicitation")
.text("Certification Ajoutée")
.graphic(null)
.hideAfter(Duration.seconds(5))                
.position(Pos.TOP_RIGHT)
.onAction(new EventHandler<ActionEvent>(){
@Override
public void handle(ActionEvent event) {
    System.out.println("clicked in notification");
}
});
          notificationBuilder.showConfirm();
        
        
        
    }
    
    }
}
