/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import models.Entreprise;
import org.controlsfx.control.Notifications;
import services.ServiceEntreprise;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AjouterEntrepriseController implements Initializable {

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
    private Button btnInsert;
    @FXML
    private Button loadImage;
    @FXML
    private ImageView LogoEntreprise;
    private Image image;

    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void loadIMG(ActionEvent event){
        FileChooser fc=new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile!=null){
            System.out.println(selectedFile.getAbsolutePath());
            image = new Image(selectedFile.toURI().toString(),50,50,true,true);
            LogoEntreprise.setImage(image);
            LogoEntreprise.setPreserveRatio(true);
            System.out.println(selectedFile.getName()); 
        }else{
            System.out.println("erruer files");
        }
    }
    
    @FXML
    private void addEntreprise(MouseEvent event) {
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws InterruptedException {
        if (event.getSource()==btnInsert){
            insertEntreprise();
           
        }
    }
   
    
     //ajout
    public void insertEntreprise() throws InterruptedException{
        
        if (tfNomEntreprise.getText().equals("") || tfEmailEntreprise.getText().equals("")||tfMatriculeFiscal.getText().equals("")||
                tfSecteurEntreprise.getText().equals("")||tfSiteEntreprise.getText().equals("") || 
                tfTelephoneEntreprise.getText().equals("")||tftailleEntreprise.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Il faut remplir toutes les informations !");
            
        }
        else if (!(tfTelephoneEntreprise.getText().length()== 8 && tfTelephoneEntreprise.getText().matches("^[0-9]+$"))){
            JOptionPane.showMessageDialog(null, "Numero de telephone invalide!");}
                     if (!( tftailleEntreprise.getText().matches("^[0-9]+$"))){
            JOptionPane.showMessageDialog(null, "Taille doit etre un nombre!");
        } else if (!(Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"

            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", tfEmailEntreprise.getText()))||
                (!(Pattern.matches("[a-zA-Z]*", tfNomEntreprise.getText())))||
                (!(Pattern.matches("[a-zA-Z]*", tfSecteurEntreprise.getText())))||
                (!(Pattern.matches("[a-zA-Z]*", tfSiteEntreprise.getText())))){
            JOptionPane.showMessageDialog(null, "verifiez tous les champs !");
        }else{
            
        
        
        int TelephoneInteger=Integer.parseInt(tfTelephoneEntreprise.getText());
        int TailleInteger=Integer.parseInt(tftailleEntreprise.getText());
        
       Entreprise E= new Entreprise(tfSecteurEntreprise.getText(),tfSiteEntreprise.getText(),TailleInteger,TelephoneInteger,tfEmailEntreprise.getText(),tfNomEntreprise.getText(),tfMatriculeFiscal.getText());
        
        ServiceEntreprise EntrepriseService = new ServiceEntreprise();
        
        EntrepriseService.ajouter(E);
        
                         notif("Ajouter avec succes", "L'entreprise "+E.getNom()+" est ajoutée", E);
                         Thread.sleep(5000);
                          //btnInsert.getScene().getWindow().hide();
        
                }
        }
    public void notif(String title,String text,Entreprise E){
            
        Notifications notificationBuilder = Notifications.create()
                .title("Ajouter avec succes")
                .text("L'entreprise "+E.getNom()+" est ajoutée")
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
