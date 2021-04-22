/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static java.lang.Double.parseDouble;
import java.net.URL;
import java.sql.Date;
import static java.sql.Date.valueOf;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import models.CandidatStage;
import models.Evenement;
import models.OffreStage;
import services.ServiceEvenement;
import services.serviceCandidatureStage;
import services.serviceOffreStage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EvenementController implements Initializable {

    @FXML
    private TableView<Evenement> table_evenement;
    @FXML
    private TableColumn<Evenement, Integer> id_evenement;
    @FXML
    private TableColumn<Evenement, String> nom_evenement;
    @FXML
    private TableColumn<Evenement, Date> date_evenement;
    @FXML
    private TableColumn<Evenement, String> adresse_evenement;
    @FXML
    private TableColumn<Evenement, String> description_evenement;
    @FXML
    private TableColumn<Evenement, String> entreprise_evenement;
    @FXML
    private TableColumn<Evenement, Double> prix_evenement;
    @FXML
    private TableColumn<Evenement, Integer> cap_evenement;
    @FXML
    private TableColumn<Evenement, Time> horaire_evenement;
    ServiceEvenement ps = new ServiceEvenement();
    List<Evenement> lr = ps.readAll();
    ObservableList<Evenement> list = FXCollections.observableArrayList(lr);
    @FXML
    private TextField ideven;
    @FXML
    private TextField nomeven;
    @FXML
    private TextField adresseeven;
    @FXML
    private TextField descriptioneven;
    @FXML
    private TextField entrepriseeven;
    @FXML
    private TextField prixeven;
    @FXML
    private TextField capeven;
    @FXML
    private TextField horaireeven;
    @FXML
    private DatePicker dateeven;
    @FXML
    private Button AjouterEven;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         id_evenement.setCellValueFactory(new PropertyValueFactory<>("id"));
          nom_evenement.setCellValueFactory(new PropertyValueFactory<>("nom"));
        date_evenement.setCellValueFactory(new PropertyValueFactory<>("date"));
                adresse_evenement.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        description_evenement.setCellValueFactory(new PropertyValueFactory<>("description"));
             entreprise_evenement.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
                prix_evenement.setCellValueFactory(new PropertyValueFactory<>("prix"));
        cap_evenement.setCellValueFactory(new PropertyValueFactory<>("cap"));
        horaire_evenement.setCellValueFactory(new PropertyValueFactory<>("horaire"));
        table_evenement.setItems(list);
    }    

    @FXML
    private void ajouter(ActionEvent event) {
         
         int i; 
        i = Integer.parseInt(capeven.getText());
         double j; 
        j = parseDouble(prix_evenement.getText());
        DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Time k;
        k =Time.valueOf(horaire_evenement.getText());
        System.out.println(k);
 System.out.println(descriptioneven.getText());
        
 Evenement p = new Evenement(nomeven.getText(),valueOf(dateeven.getValue()),adresseeven.getText(),descriptioneven.getText(),entrepriseeven.getText(),j,i,k);
 
   
        ServiceEvenement ps = new ServiceEvenement();

        ps.addEvenement(p);
 
        AjouterEven.getScene();

    }
    
}
