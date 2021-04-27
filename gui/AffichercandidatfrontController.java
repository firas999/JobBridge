/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.user;
import Services.serviceuser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AffichercandidatfrontController implements Initializable {

    @FXML
    private TableView<user> affichercandidat;
    @FXML
    private TableColumn<user, String> nomcandidat;
    @FXML
    private TableColumn<user, String> prenomcandidat;
    @FXML
    private TableColumn<user, String> typecandidat;
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
    private Button btn_ajout;
    @FXML
    private Button btn_modif;
    @FXML
    private Button btn_supprim;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }    
       public void loadData() {
        afficher_membre();}
        
     public void afficher_membre(){
        
        ObservableList<user> list =  cn.afficher();
   
        nomcandidat.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcandidat.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        typecandidat.setCellValueFactory(new PropertyValueFactory<>("type"));
         secteurcandidat.setCellValueFactory(new PropertyValueFactory<>("secteur"));
          sitewebcandidat.setCellValueFactory(new PropertyValueFactory<>("siteweb"));
           taillecandidat.setCellValueFactory(new PropertyValueFactory<>("tailleentreprise"));
         numcandidat.setCellValueFactory(new PropertyValueFactory<>("numerotelephone"));
          imagecandidat.setCellValueFactory(new PropertyValueFactory<>("image1"));
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

       
          btn_modif.getScene().setRoot(parent1);
          ModifierCandidatController controller = (ModifierCandidatController) loader.getController();
            controller.setUser(u);
			controller.updateField();
    
		}
    }
 user u =new user();
    public void setUser(user u) {
		this.u = u;
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
    private void follow(ActionEvent event) {  
        user candidat = null;
		candidat = affichercandidat.getSelectionModel().getSelectedItem();
                cn.follow(u, candidat);
                 loadData();
    }

    @FXML
    private void unfollow(ActionEvent event) {
          user candidat= null;
		candidat = affichercandidat.getSelectionModel().getSelectedItem();
                cn.unblock(candidat);
                loadData();

    }
}
