/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.ControleSaisie;
import Models.user;
import Services.serviceuser;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import tray.notification.NotificationType; 
import tray.notification.TrayNotification;
import org.controlsfx.control.Notifications; 
/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AjoutercCandidatController implements Initializable {

    @FXML
    private TextField textfieldnom;
    @FXML
    private TextField textfieldprenom;
    @FXML
    private TextField textfieldmotdepasse;
    @FXML
    private TextField textfieldtype;
    @FXML
    private TextField textfieldsecteur;
    @FXML
    private Button btn_ajout;
    @FXML
    private TextField textfieldsiteweb;
    @FXML
    private TextField textfieldtaille;
    @FXML
    private TextField textfieldnum;
 String imgUrl  ="";
    private FileChooser uploadPic;
    private File picPath;
    @FXML
    private ImageView imageToPost;
    @FXML
    private Button addImage;
    @FXML
    private Button back;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   serviceuser cn = new serviceuser();
    @FXML
    private void ajout(ActionEvent event) {
       if (verifchamps() == true) {
           user c= new user() ;  
//            c.setDiscr("candidat");
           
       Image image1 = null;
                
            c.setNom(textfieldnom.getText());
            c.setPrenom(textfieldprenom.getText());
            c.setMotdepasse(textfieldmotdepasse.getText());
           c.setType(textfieldtype.getText());
        
            c.setSecteur(textfieldsecteur.getText());
           c.setSiteweb(textfieldsiteweb.getText());
             String taille = textfieldtaille.getText();
            c.setTailleentreprise(Integer.parseInt(taille));
             String num = textfieldnum.getText();
            c.setNumerotelephone(Integer.parseInt(num));
        
            String image = imgUrl; 
            c.setImg(image);            
            String str="[\"ROLE_CANDIDATE\"]";
             c.setRoles(str) ; 
             c.setDiscr("candidat");
            
             
           // img_user.setImg(new Image("file:/C:/Users/trabe/Desktop/Integration/src/images/" + c.getImg())); 
            cn.ajouter(c);
            Notifications n = Notifications.create()   
                              .title("Sucess")
                              .text("Candidat Added ")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(5));
               n.darkStyle();
               n.show();
        }else {
			Notifications n = Notifications.create()
                              .title("FAIL")
                              .text("Error, insert data")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(1));
               n.darkStyle();
               n.show();
		}
    }
    
    ControleSaisie cs = new ControleSaisie(); 
    TrayNotification tray = null; 
     
    
    private Boolean verifchamps() {
        if (textfieldnom.getText().isEmpty() || textfieldprenom.getText().isEmpty()
                || textfieldmotdepasse.getText().isEmpty() || textfieldtype.getText().isEmpty()
                || textfieldsecteur.getText().isEmpty() || textfieldsiteweb.getText().isEmpty()
                || textfieldtaille.getText().isEmpty() || textfieldnum.getText().isEmpty()) {
            tray = new TrayNotification("Erreur", "Il faut remplire tous les champs ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;}
        if(textfieldnum.getText().length()<8 ||textfieldnum.getText().length()>8||!cs.isInte(textfieldnum.getText()))
        {
            
            tray = new TrayNotification("Erreur", "Your phone number must be 8 numbers !", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
               if(!cs.isInte(textfieldtaille.getText()))
        {
            tray = new TrayNotification("Erreur", "number!", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
     
     
       

        return true;
           

        }

       

      
@FXML
    private void addImage(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        uploadPic = new FileChooser();
        uploadPic.setTitle("Select the image you want to add");
        picPath = uploadPic.showOpenDialog(stage);
        System.out.println(picPath.toString());
        try {
            imgUrl = picPath.toURI().toURL().toExternalForm();

            BufferedImage buffImage = ImageIO.read(picPath);
            Image up = SwingFXUtils.toFXImage(buffImage, null); 
            imageToPost.setImage(up);
        } catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void backToAffichage(ActionEvent event) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AfficherCandidat.fxml"));

        try {
            Parent root = loader.load();
            back.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }
}
