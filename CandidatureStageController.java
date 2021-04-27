/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import static com.github.plushaze.traynotification.notification.Notifications.SUCCESS;
import com.github.plushaze.traynotification.notification.TrayNotification;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import static java.sql.Date.valueOf;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import models.CandidatStage;
import models.Entreprise;
import models.Mail;
import models.OffreStage;
import models.Stagiaire;
import org.controlsfx.control.Notifications;
import services.ServiceEntreprise;
import services.ServiceStagiaire;
import services.serviceCandidatureStage;
import services.serviceOffreStage;
import java.text.SimpleDateFormat; 
import java.util.Locale; 

/**
 * FXML Controller class
 *
 * @author User
 */
public class CandidatureStageController implements Initializable {

    @FXML
    private TableView<CandidatStage> table_candidaturestage;
    @FXML
    private TableColumn<CandidatStage, Integer> idCandidature;
    @FXML
    private TableColumn<CandidatStage, Integer> identreprise_candidaturestage;
    @FXML
    private TableColumn<CandidatStage, Integer> idstagiaire_candidaturestage;
    @FXML
    private TableColumn<CandidatStage, Date> date_candidaturestage;
    @FXML
    private TableColumn<CandidatStage, String> email_candidaturestage;
    serviceCandidatureStage ps = new serviceCandidatureStage();
    public ObservableList<CandidatStage> list=FXCollections.observableArrayList();
    ServiceEntreprise SE = new ServiceEntreprise();
    List<Entreprise> liste = SE.afficherIdNom();
    ObservableList<Entreprise> option = FXCollections.observableArrayList(SE.afficherIdNom());
    ServiceStagiaire SS = new ServiceStagiaire();
    List<Stagiaire> listeStagiaire = SS.afficherIdNom();
    ObservableList<Stagiaire> option2 = FXCollections.observableArrayList(SS.afficherIdNom());
    @FXML
    private ComboBox entreprise_candidaturestage;
    @FXML
    private ComboBox stagiaire_candidaturestage;
    @FXML
    private DatePicker date_candidaturestage1;
    @FXML
    private TextField email_candidaturestage1;
    @FXML
    private Button ajouter_candidaturestage;
    @FXML
    private Button modifier_candidaturestage;
    @FXML
    private Button supprimer_candidaturestage;
    @FXML
    private TextField idCandidatureStage;
    @FXML
    private Button offrestage;
    @FXML
    private Button candidatstage;
    @FXML
    private TextField recherchecandidatstage;
    @FXML
    private Button offreemploi;
    @FXML
    private Button candidatemploi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        list=(ObservableList<CandidatStage>) ps.readAll();
        entreprise_candidaturestage.setItems(option);
        stagiaire_candidaturestage.setItems(option2);
        idCandidatureStage.setDisable(true);
        idCandidature.setCellValueFactory(new PropertyValueFactory<>("id"));
        identreprise_candidaturestage.setCellValueFactory(new PropertyValueFactory<>("nomentreprise"));
        idstagiaire_candidaturestage.setCellValueFactory(new PropertyValueFactory<>("nomstagiaire"));
        date_candidaturestage.setCellValueFactory(new PropertyValueFactory<>("date_candidature"));
        email_candidaturestage.setCellValueFactory(new PropertyValueFactory<>("email"));

        FilteredList<CandidatStage> filteredData = new FilteredList<>(list, b -> true);
        recherchecandidatstage.textProperty().addListener((Observable, oldValue, newValue) -> {
            filteredData.setPredicate(CandidatStage -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (CandidatStage.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });
        SortedList<CandidatStage> sorteddata = new SortedList<>(filteredData);
        sorteddata.comparatorProperty().bind(table_candidaturestage.comparatorProperty());
        table_candidaturestage.setItems(sorteddata);
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException, Exception {
        if (entreprise_candidaturestage.getSelectionModel().isEmpty() || stagiaire_candidaturestage.getSelectionModel().isEmpty() || email_candidaturestage1.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Il faut remplir toutes les informations !");
        } else if (!(Pattern.matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$", email_candidaturestage1.getText()))) {
            String i = email_candidaturestage1.getText();
            JOptionPane.showMessageDialog(null, "l'adresse mail ' " + i + " ' est invalide ");
        } else {
            int i;
            i = Integer.parseInt(entreprise_candidaturestage.getSelectionModel().getSelectedItem().toString().substring(0, entreprise_candidaturestage.getSelectionModel().getSelectedItem().toString().indexOf(":")));
            int j;
            j = Integer.parseInt(stagiaire_candidaturestage.getSelectionModel().getSelectedItem().toString().substring(0, stagiaire_candidaturestage.getSelectionModel().getSelectedItem().toString().indexOf(":")));
            

            CandidatStage p = new CandidatStage(i, j, valueOf(date_candidaturestage1.getValue()), email_candidaturestage1.getText());

            serviceCandidatureStage ps = new serviceCandidatureStage();

            ps.addCandidatStage(p); 
            ajouter_candidaturestage.getScene();
            Mail.sendMail(email_candidaturestage1.getText());
            TrayNotification tray = new TrayNotification("Candidature stage", "Candidature stage ajouter", SUCCESS);
            tray.showAndDismiss(Duration.seconds(3));
            Parent root = FXMLLoader.load(getClass().getResource("CandidatureStage.fxml"));
            ajouter_candidaturestage.getScene().setRoot(root);
    
        }
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        int i = Integer.parseInt(idCandidatureStage.getText());
        CandidatStage p = new CandidatStage(valueOf(date_candidaturestage1.getValue()), email_candidaturestage1.getText(), i);
        serviceCandidatureStage ps = new serviceCandidatureStage();

        ps.modifier(p);
        TrayNotification tray = new TrayNotification("Candidature stage", "Candidature stage modifier", SUCCESS);
            tray.showAndDismiss(Duration.seconds(3));
        Parent root = FXMLLoader.load(getClass().getResource("CandidatureStage.fxml"));
        
        modifier_candidaturestage.getScene().setRoot(root);
    }

    @FXML
    private void supprimer(ActionEvent event) throws IOException {
        int id = Integer.parseInt(idCandidatureStage.getText());
        CandidatStage E = new CandidatStage(id);

        serviceCandidatureStage ps = new serviceCandidatureStage();
        ps.supprimer(id);
        TrayNotification tray = new TrayNotification("Candidature stage", "Candidature stage supprimer", SUCCESS);
            tray.showAndDismiss(Duration.seconds(3));
        Parent root = FXMLLoader.load(getClass().getResource("CandidatureStage.fxml"));
        supprimer_candidaturestage.getScene().setRoot(root);
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @FXML
    private void handleMouseAction(MouseEvent event) {
        CandidatStage CandidatStage = table_candidaturestage.getSelectionModel().getSelectedItem();
        idCandidatureStage.setText(CandidatStage.getId().toString());
        //typestage.setItems(list);

        //date_candidaturestage1.setValue(valueOf(CandidatStage.getDate_candidature());
        email_candidaturestage1.setText(CandidatStage.getEmail());
        System.out.println("id=" + CandidatStage.getId());

    }

    @FXML
    private void offrestage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OffreStage.fxml"));
        offrestage.getScene().setRoot(root);

    }

    @FXML
    private void candidatstage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CandidatureStage.fxml"));
        candidatstage.getScene().setRoot(root);

    }

    @FXML
    private void recherchecandidatstage(ActionEvent event) {
    }

    @FXML
    private void offreemploi(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AfficherOffreEmploi.fxml"));
        offreemploi.getScene().setRoot(root);
    }

    @FXML
    private void candidatemploi(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("candidatureEmploi.fxml"));
        candidatemploi.getScene().setRoot(root);
    }
}
