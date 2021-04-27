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
import java.sql.Date;
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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AfficherEmployerController implements Initializable {

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
    private TableColumn<user, String> diplomeemployer;
    @FXML
    private TableColumn<user, String> entrepriseemployer;
    @FXML
    private TableColumn<user, String> contratemployer;
    @FXML
    private TableColumn<user, String> posteoccupeemployer;
    @FXML
    private TableColumn<user, Date> dateemployer;
    @FXML
    private TableColumn<user, String> competenceemployer;
    @FXML
    private TableColumn<user, String> imagecandidat;
    serviceuser cn = new serviceuser();
    @FXML
    private Button btn_ajout;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprim;
    @FXML
    private TableView<user> afficheremployer;
    @FXML
    private Button back;
    @FXML
    private Circle circle;
    @FXML
    private Text laabell;
user u = UserSession.getInstance().getLoggedUser();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (u.getRoles().equals("[\"ROLE_ADMIN\"]")){
            btn_ajout.setVisible(true);
            btn_modifier.setVisible(true);
            btn_supprim.setVisible(true);
               }
        else{
               btn_ajout.setVisible(false);
            btn_modifier.setVisible(false);
            btn_supprim.setVisible(false);

        }
        loadData();
        laabell.setText(u.getNom()+" "+u.getPrenom());
                 Image image ;
                image = new Image(u.getImg());
               
                circle.setFill(new ImagePattern(image));
               loadData();
    }    
    public void loadData() {
        afficher_membre();
       
    }  
     public void afficher_membre(){
        
        ObservableList<user> list =  cn.afficherEmlpoyer();
   
        nomcandidat.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcandidat.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        motdepassecandidat.setCellValueFactory(new PropertyValueFactory<>("motdepasse"));
        typecandidat.setCellValueFactory(new PropertyValueFactory<>("type"));
        discrcandidat.setCellValueFactory(new PropertyValueFactory<>("discr"));
        diplomeemployer.setCellValueFactory(new PropertyValueFactory<>("diplome"));
        entrepriseemployer.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
        contratemployer.setCellValueFactory(new PropertyValueFactory<>("typedecontrat"));
        posteoccupeemployer.setCellValueFactory(new PropertyValueFactory<>("posteoccupe"));
        dateemployer.setCellValueFactory(new PropertyValueFactory<>("date"));
        competenceemployer.setCellValueFactory(new PropertyValueFactory<>("competence"));
          
          imagecandidat.setCellValueFactory(new PropertyValueFactory<>("image1"));
      
        afficheremployer.setItems(list);
    }
    @FXML
    private void ajouteremployer(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ajouterEmployer.fxml"));

        try {
            Parent root = loader.load();
            btn_ajout.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void modifierClicked(MouseEvent event) throws IOException {
     user u = null;
		u = afficheremployer.getSelectionModel().getSelectedItem();
		
		if(u != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/modifierEmployer.fxml"));
			 
       
       
      
 Parent parent1 = loader.load();

       
          btn_modifier.getScene().setRoot(parent1);
          ModifierEmplyerController controller = (ModifierEmplyerController) loader.getController();
            controller.setUser(u);
			controller.updateField();
    
		}
  }

    @FXML
    private void supprimeremployer(ActionEvent event) {
   user u = null;
		u = afficheremployer.getSelectionModel().getSelectedItem();
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
    private void backacceuil(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Acceuil.fxml"));
        try {
            Parent root = loader.load();
            back.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }

    @FXML
    private void profile(MouseEvent event) {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Profile_user.fxml"));
        try {
            Parent root = loader.load();
            back.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    
}}
    

