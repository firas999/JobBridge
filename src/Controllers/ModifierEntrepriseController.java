/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import models.Entreprise;
import models.Formation;
import org.controlsfx.control.Notifications;
import services.ServiceEntreprise;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ModifierEntrepriseController implements Initializable {
  public DataSource ds1;
    private Connection conn;
    public PreparedStatement st;
    public ResultSet rs;
       ServiceEntreprise EntrepriseService = new ServiceEntreprise();
    @FXML
    private TextField tfNomEntreprise;
    @FXML
    private TextField tfSecteurEntreprise;
    @FXML
    private TextField tfMatriculeFiscal;
    @FXML
    private TextField tfSiteEntreprise;
    @FXML
    private TextField tfEmailEntreprise;
    @FXML
    private TextField tfTelephoneEntreprise;
    @FXML
    private TextField tftailleEntreprise;
    @FXML
    private TextField tfIdEntreprise;
    @FXML
    private Button btnUpdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                  conn = DataSource.getInstance().getCnx();
                  tfIdEntreprise.setVisible(false);
    }    

    @FXML
    private void UpdateEntreprise(MouseEvent event) {
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource()==btnUpdate){
    UPdateEntreprise();

    
}
    }

    public void setTEXTFIELDS(String nom,String secteur,String mf,String site,String email , String tel, String taille){
        tfNomEntreprise.setText(nom);
        tfSecteurEntreprise.setText(secteur);
        tfMatriculeFiscal.setText(mf);
        tfSiteEntreprise.setText(site);
        tfEmailEntreprise.setText(email);
        tfTelephoneEntreprise.setText(tel);
        tftailleEntreprise.setText(taille);
    }
    
    void setIDentreprise(String id) {
        tfIdEntreprise.setText(id);
    }
    
        public void UPdateEntreprise(){
      int TelephoneInteger=Integer.parseInt(tfTelephoneEntreprise.getText());
        int TailleInteger=Integer.parseInt(tftailleEntreprise.getText());
        int id=Integer.parseInt(tfIdEntreprise.getText());
        
       Entreprise E= new Entreprise(tfSecteurEntreprise.getText(),tfSiteEntreprise.getText(),TailleInteger,TelephoneInteger,tfEmailEntreprise.getText(),tfNomEntreprise.getText(),tfMatriculeFiscal.getText(),id);
        
        ServiceEntreprise EntrepriseService = new ServiceEntreprise();
        EntrepriseService.modifier(E);
        notif("Modifier avec succes", "L'entreprise "+E.getNom()+" est modifié", E);
            
        btnUpdate.getScene().getWindow().hide();
      
    }
        
         public void notif(String title,String text,Entreprise E){
            
        Notifications notificationBuilder = Notifications.create()
                .title("Modifier avec succes")
                .text("L'Entreprise "+E.getNom()+" est modifiée")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
           
            }
        });
        notificationBuilder.darkStyle();
        notificationBuilder.showConfirm();
                
        }
    
}
