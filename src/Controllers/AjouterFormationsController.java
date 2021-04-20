/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.sql.Connection;
import static java.sql.Date.valueOf;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import models.Entreprise;
import models.Formation;
import org.controlsfx.control.Notifications;
import services.ServiceFormation;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AjouterFormationsController implements Initializable {
 public DataSource ds1;
    private Connection conn;
    public PreparedStatement st;
    public ResultSet rs;
    services.ServiceFormation serviceFormation = new ServiceFormation();
    @FXML
    private TextField tfIdFormation;
    @FXML
    private TextField tfDescriptionFormation;
    @FXML
    private TextField tfVolumeHoraireFormation;
    @FXML
    private DatePicker tfDateFormation;
    @FXML
    private TextField tfAdresseFormation;
    @FXML
    private TextField tfPrixFormation;
    @FXML
    private CheckBox CHBoxPromoFormation;
    @FXML
    private TextField idEntreprise;
    @FXML
    private Button btnInsertFormation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idEntreprise.setVisible(false);
        conn = DataSource.getInstance().getCnx();
    }    
   public void setIDentreprise(String text){
       idEntreprise.setText(text); 
   }
   
      public void insertFormation(){
        
            
           if (tfDescriptionFormation.getText().equals("") || tfVolumeHoraireFormation.getText().equals("")||tfAdresseFormation.getText().equals("")||
                tfPrixFormation.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Il faut remplir toutes les informations !");
            
        }
        else if((!(Pattern.matches("[0-9]*", tfVolumeHoraireFormation.getText())))||
                (!(Pattern.matches("[0-9]*", tfPrixFormation.getText())))) {
            JOptionPane.showMessageDialog(null, "verifiez tous les champs !");
        } else if (!(Pattern.matches("[a-zA-Z]*", tfDescriptionFormation.getText()))||
                (!(Pattern.matches("[a-zA-Z]*", tfAdresseFormation.getText())))){
            JOptionPane.showMessageDialog(null, "verifiez tous les champs !");
        }else{
            
        
        ServiceFormation FormationService = new ServiceFormation();
        int VolumeHoraireInteger=Integer.parseInt(tfVolumeHoraireFormation.getText());
        int PrixInteger=Integer.parseInt(tfPrixFormation.getText());
        
        //Promo checkBox
        int Promo=0;
        if (CHBoxPromoFormation.isSelected())
            Promo=1;
        else Promo=0;
        
        //get entreprise ID 
       
       Formation F= new Formation(tfDescriptionFormation.getText(),VolumeHoraireInteger,valueOf(tfDateFormation.getValue()),
               tfAdresseFormation.getText(),PrixInteger,Promo);
        
        
        FormationService.ajouter(F,idEntreprise.getText());
        notif("Ajouter avec succes", "La formation "+F.getDescription()+" est ajoutée", F);
                    btnInsertFormation.getScene().getWindow().hide();
        }
     
                }

    @FXML
      private void handleButtonAction(ActionEvent event) {
        if (event.getSource()==btnInsertFormation){
    insertFormation();

}
    }
      
         public void notif(String title,String text,Formation F){
            
        Notifications notificationBuilder = Notifications.create()
                .title("Ajouter avec succes")
                .text("La Formation "+F.getDescription()+" est ajoutée")
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
