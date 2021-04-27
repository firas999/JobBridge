/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.user;
import Services.serviceuser;
import Utils.UserSession;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AffichercCandidatController implements Initializable {


    @FXML
    private Button btn_ajout;
    @FXML
    private TableView<user> affichercandidat;
    @FXML
    private TableColumn<user, String> nomcandidat;
    @FXML
    private TableColumn<user, String> prenomcandidat;
    @FXML
    private TableColumn<user, String> motdepassecandidat;
    @FXML
    private TableColumn<user, String> typecandidat;
    @FXML
    private TableColumn<user, String> discrcandidat;
    @FXML
    private TableColumn<user, String> secteurcandidat;
    @FXML
    private TableColumn<user, String> sitewebcandidat;
    @FXML
    private TableColumn<user, Integer> taillecandidat;
    @FXML
    private TableColumn<user, Integer> numcandidat;
    @FXML
    private TableColumn<user, String> imagecandidat;
          serviceuser cn = new serviceuser();
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprim;
    @FXML
    private AnchorPane afficheremployer;
    @FXML
    private Button back;
    @FXML
    private Text labelll;
    @FXML
    private TextField search;
    @FXML
    private TableColumn<user, Integer> follow;
    @FXML
    private Button btn_block;
    @FXML
    private Button btn_unblock;
    @FXML
    private TableColumn<user, String> block;
    private ImageView imageUser;
    @FXML
    private Circle circle;

 user u = UserSession.getInstance().getLoggedUser();
    @FXML
    private ImageView img_ajout;
    @FXML
    private ImageView img_edit;
    @FXML
    private ImageView img_delete;
    @FXML
    private ImageView img_block;
    @FXML
    private ImageView img_unblock;
    @FXML
    private Button email;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (u.getRoles().equals("[\"ROLE_ADMIN\"]")){
            img_ajout.setVisible(true);
            img_ajout.setVisible(true);
            img_delete.setVisible(true);
          img_edit.setVisible(true);
          img_block.setVisible(true);
          img_unblock.setVisible(true);
        }
        else{
            img_ajout.setVisible(false);
            img_ajout.setVisible(false);
            img_delete.setVisible(false);
          img_edit.setVisible(false);
          img_block.setVisible(false);
          img_unblock.setVisible(false);
        }
        
       labelll.setText(u.getNom()+" "+u.getPrenom());
                 Image image ;
                image = new Image(u.getImg());
               
                circle.setFill(new ImagePattern(image));
               loadData();

    }    
  

       public void loadData() {
        afficher_membre();
       
    }  
    public void afficher_membre(){
        
        ObservableList<user> list =  cn.afficher();
   
        nomcandidat.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcandidat.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        motdepassecandidat.setCellValueFactory(new PropertyValueFactory<>("motdepasse"));
        typecandidat.setCellValueFactory(new PropertyValueFactory<>("type"));
        discrcandidat.setCellValueFactory(new PropertyValueFactory<>("discr"));
         secteurcandidat.setCellValueFactory(new PropertyValueFactory<>("secteur"));
          sitewebcandidat.setCellValueFactory(new PropertyValueFactory<>("siteweb"));
           taillecandidat.setCellValueFactory(new PropertyValueFactory<>("tailleentreprise"));
         numcandidat.setCellValueFactory(new PropertyValueFactory<>("numerotelephone"));
          imagecandidat.setCellValueFactory(new PropertyValueFactory<>("image1"));
          follow.setCellValueFactory(new PropertyValueFactory<>("nbr_follow"));
          block.setCellValueFactory(new PropertyValueFactory<>("block"));
        affichercandidat.setItems(list);
    }

    @FXML
    private void ajoutercandidat(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjoutercCandidat.fxml"));

        try {
            Parent root = loader.load();
            btn_ajout.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void modifierClicked(MouseEvent event) throws IOException {
            user u = null;
		u = affichercandidat.getSelectionModel().getSelectedItem();
		
		if(u != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierCandidat.fxml"));
			 
       
       
      
 Parent parent1 = loader.load();

       
          btn_modifier.getScene().setRoot(parent1);
          ModifierCandidatController controller = (ModifierCandidatController) loader.getController();
            controller.setUser(u);
			controller.updateField();
    
		}
  
    }
    @FXML
    private void supprimercandidat(ActionEvent event) {
      user u = null;
		u = affichercandidat.getSelectionModel().getSelectedItem();
		if(u != null) {
			cn.supprimer(u);
                       
			Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  User suprrimé")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(5));
               n.darkStyle();
               n.show();
			   afficher_membre();
		}
    }

    @FXML
    private void backtoAcceuil(ActionEvent event) {
   FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Acceuil.fxml"));
        try {
            Parent root = loader.load();
            back.getScene().setRoot(root);
        } catch (IOException ex) {
           
    } }

    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void searchTextChanged(InputMethodEvent event) {
    }

    @FXML
    private void searchKeyRelaesed(KeyEvent event) { 
        affichercandidat.getItems().setAll(cn.search(search.getText()));
    }

    
     private Stage primaryStage;
 
    @FXML
    private void pdf_coach(ActionEvent event) {
         //imprim
         System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(this.primaryStage); 
            
    Node root = this.affichercandidat;
           job.printPage(root);
           job.endJob();
    }
    
}

    @FXML
    private void follow(ActionEvent event) { 
               user candidat = null;
		candidat = affichercandidat.getSelectionModel().getSelectedItem();
                cn.follow(u, candidat);
                 loadData();
                //candidat.setNbr_follow(2);
                
    }

    @FXML
    private void unfollow(ActionEvent event) {
         user candidat = null;
		candidat = affichercandidat.getSelectionModel().getSelectedItem();
                cn.unfollow(u, candidat);
                 loadData();
                
    }

    @FXML
    private void block(ActionEvent event) {
        user candidat= null;
		candidat = affichercandidat.getSelectionModel().getSelectedItem();
                cn.block(candidat);
                loadData();
    }

    @FXML
    private void unblock(ActionEvent event) {
          user candidat= null;
		candidat = affichercandidat.getSelectionModel().getSelectedItem();
                cn.unblock(candidat);
                loadData();

    }

    @FXML
    private void profile(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Profile_user.fxml"));
        try {
            Parent root = loader.load();
            back.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void emailsubmit(ActionEvent event) { FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/email.fxml"));
        try {
            Parent root = loader.load();
            back.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }
}
