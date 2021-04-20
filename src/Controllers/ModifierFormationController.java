/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import static java.sql.Date.valueOf;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
import javafx.util.Duration;
import models.Formation;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import services.ServiceFormation;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ModifierFormationController implements Initializable {
public DataSource ds1;
    private Connection conn;
    public PreparedStatement st;
    public ResultSet rs;
        services.ServiceFormation serviceFormation = new ServiceFormation();
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
    private Button btnUpdateFormation;
    @FXML
    private TextField tfIdFormation;

    String[] possibleAdresse={"soukra","laouina","manzah","mourouj","Ben arous","zahra","mahdia","monastir","mednin"};
    String[] possibleFormation={"Java","php","sql","mysql","qt","symfony","c","c++","python","laravel"};
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = DataSource.getInstance().getCnx();
        TextFields.bindAutoCompletion(tfDescriptionFormation, possibleFormation);
        TextFields.bindAutoCompletion(tfAdresseFormation, possibleAdresse);
        tfIdFormation.setVisible(false);
    }    
    
    public void setTextFields(String desc,String vh,Date date,String adresse,String prix){
        tfAdresseFormation.setText(adresse);
        tfDescriptionFormation.setText(desc);
        tfPrixFormation.setText(prix);
        tfVolumeHoraireFormation.setText(vh);
        tfDateFormation.setValue(date.toLocalDate());
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
 if (event.getSource()==btnUpdateFormation){
    UPdateFormation();
          btnUpdateFormation.getScene().getWindow().hide();
 }}
 
    public TextField getTfIdFormation() {
        return tfIdFormation;
    }

    public void setTfIdFormation(String text){
       tfIdFormation.setText(text); 
   }
     public void UPdateFormation(){
      int VH=Integer.parseInt(tfVolumeHoraireFormation.getText());
       int Prix=Integer.parseInt(tfPrixFormation.getText());
       int id=Integer.parseInt(tfIdFormation.getText());
       int Promo=0;
        if (CHBoxPromoFormation.isSelected())
            Promo=1;
        else Promo=0;
       Formation F= new Formation(tfDescriptionFormation.getText(),VH,valueOf(tfDateFormation.getValue()),tfAdresseFormation.getText(),Prix,Promo,id);
        
        ServiceFormation FormationService = new ServiceFormation();
        FormationService.modifier(F);
         notif("Modifier avec succes", "La formation "+F.getDescription()+" est Modifée", F);
       
      
       
    }
      public void notif(String title,String text,Formation F){
            
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
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
