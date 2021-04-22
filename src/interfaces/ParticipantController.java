/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.net.URL;
import static java.sql.Date.valueOf;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.CandidatStage;
import models.Evenement;
import models.OffreStage;
import models.Participant;
import models.Stagiaire;
import services.ServiceEvenement;
import services.ServiceParticipant;
import services.ServiceStagiaire;
import services.serviceCandidatureStage;
import services.serviceOffreStage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ParticipantController implements Initializable {

    @FXML
    private TableView<Participant> tableparticipant;
    @FXML
    private TableColumn<Participant, Integer> idparticipant;
    @FXML
    private TableColumn<Participant, String> typeparticipant;
    @FXML
    private TableColumn<Participant, Integer> evenementparticipant;
    @FXML
    private TableColumn<Participant, String> nomparticipant;
    @FXML
    private TableColumn<Participant, String> prenomparticipant;
    @FXML
    private TableColumn<Participant, String> mailparticipant;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    ServiceParticipant ps = new ServiceParticipant();
    List<Participant> lr = ps.readAll();
    ObservableList<Participant> list = FXCollections.observableArrayList(lr);
    ServiceEvenement SS = new ServiceEvenement();
    List<Evenement> listeStagiaire = SS.readAll();
    ObservableList<Evenement> option2 = FXCollections.observableArrayList(SS.readAll());
    @FXML
    private TextField id_participant;
    @FXML
    private TextField type_participant;
    @FXML
    private TextField nom_participant;
    @FXML
    private TextField prenom_participant;
    @FXML
    private TextField mail_participant;
    @FXML
    private ComboBox id_evenement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 
        id_evenement.setItems(option2);
            idparticipant.setCellValueFactory(new PropertyValueFactory<>("id"));
          typeparticipant.setCellValueFactory(new PropertyValueFactory<>("type_participant"));
        evenementparticipant.setCellValueFactory(new PropertyValueFactory<>("id_evenement_id"));
                nomparticipant.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomparticipant.setCellValueFactory(new PropertyValueFactory<>("prenom"));
               mailparticipant.setCellValueFactory(new PropertyValueFactory<>("mail"));
        tableparticipant.setItems(list);
    }    

    @FXML
    private void ajouter(ActionEvent event) {
         int i;
         i = Integer.parseInt(id_evenement.getSelectionModel().getSelectedItem().toString().substring(0,id_evenement.getSelectionModel().getSelectedItem().toString().indexOf(":")));
          System.out.println(i);
          Participant p = new Participant(type_participant.getText(),i, nom_participant.getText(),prenom_participant.getText(),mail_participant.getText());
   
        ServiceParticipant ps = new ServiceParticipant();

        ps.addParticipant(p);
 
        ajouter.getScene();
    }

    @FXML
    private void modifier(ActionEvent event) {
          int i = Integer.parseInt(id_participant.getText());
        Participant p = new Participant(type_participant.getText(), nom_participant.getText(),prenom_participant.getText(),mail_participant.getText(),i);
        ServiceParticipant ps = new ServiceParticipant();

        ps.modifier(p);
        System.out.println(mail_participant.getText());
    }

    @FXML
    private void supprimer(ActionEvent event) {
         int id = Integer.parseInt(id_participant.getText());
        Participant E = new Participant(id);

        ServiceParticipant ps = new ServiceParticipant();
        ps.supprimer(id);
    }
    
}
