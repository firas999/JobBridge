/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import models.Entreprise;
import services.ServiceEntreprise;
import utils.DataSource;

/**
 *
 * @author dell
 */
public class EntrepriseController implements Initializable {
    public DataSource ds1;
    private Connection conn;
    public PreparedStatement st;
    public ResultSet rs;
    
    @FXML
    private TextField tfIdEntreprise;

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
   private TableView<Entreprise> TVentreprise;
    @FXML
    private TableColumn<Entreprise, Integer> tvIdEntreprise;
    @FXML
    private TableColumn<Entreprise, String> tvNomEntreprise;
    @FXML
    private TableColumn<Entreprise, String> tvSecteurEntreprise;
    @FXML
    private TableColumn<Entreprise, String> tvMatriculeFiscalEntreprise;
    @FXML
    private TableColumn<Entreprise, String> tvSiteEntreprise;
    @FXML
    private TableColumn<Entreprise, String> tvEmailEntreprise;
    @FXML
    private TableColumn<Entreprise, Integer> tvTelephneEntreprise;
    @FXML
    private TableColumn<Entreprise, Integer> tvTailleEntreprise;
    @FXML
    private Button btnInsert;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;
    
    ServiceEntreprise EntrepriseService = new ServiceEntreprise();
    


    @FXML
    void DeleteEntreprise() {

    }

    @FXML
    void UpdateEntreprise() {

    }

    @FXML
    void addEntreprise() {

    }

    @FXML
    void handleButtonAction(ActionEvent event) {
if (event.getSource()==btnInsert){
    insertEntreprise();
    
}else if (event.getSource()==btnUpdate){
    UPdateEntreprise();
    
}else if (event.getSource()==btnDelete){
    DELETEentreprise();
    
}
    }
    
    //delete 
    public void DELETEentreprise(){
          int id=Integer.parseInt(tfIdEntreprise.getText());
           Entreprise E= new Entreprise(id);
        
        ServiceEntreprise EntrepriseService = new ServiceEntreprise();
        EntrepriseService.supprimer(id);
        showEntreprise();
      
    }
//modifier
    public void UPdateEntreprise(){
      int TelephoneInteger=Integer.parseInt(tfTelephoneEntreprise.getText());
        int TailleInteger=Integer.parseInt(tftailleEntreprise.getText());
        int id=Integer.parseInt(tfIdEntreprise.getText());
       Entreprise E= new Entreprise(tfSecteurEntreprise.getText(),tfSiteEntreprise.getText(),TailleInteger,TelephoneInteger,tfEmailEntreprise.getText(),tfNomEntreprise.getText(),tfMatriculeFiscal.getText(),id);
        
        ServiceEntreprise EntrepriseService = new ServiceEntreprise();
        EntrepriseService.modifier(E);
      
       showEntreprise();
    }
    //ajout
    public void insertEntreprise(){
        int TelephoneInteger=Integer.parseInt(tfTelephoneEntreprise.getText());
        int TailleInteger=Integer.parseInt(tftailleEntreprise.getText());
        
       Entreprise E= new Entreprise(tfSecteurEntreprise.getText(),tfSiteEntreprise.getText(),TailleInteger,TelephoneInteger,tfEmailEntreprise.getText(),tfNomEntreprise.getText(),tfMatriculeFiscal.getText());
        
        ServiceEntreprise EntrepriseService = new ServiceEntreprise();
        EntrepriseService.ajouter(E);
        
        showEntreprise();
        
                }
        
//affichage
       public void showEntreprise() {
          ObservableList<Entreprise> data=FXCollections.observableArrayList(EntrepriseService.afficher());
           
        tvIdEntreprise.setCellValueFactory(new PropertyValueFactory<>("id"));
        tvSecteurEntreprise.setCellValueFactory(new PropertyValueFactory<>("Secteur"));
        tvSiteEntreprise.setCellValueFactory(new PropertyValueFactory<>("SiteWeb"));
        tvTailleEntreprise.setCellValueFactory(new PropertyValueFactory<>("Taille"));
        tvTelephneEntreprise.setCellValueFactory(new PropertyValueFactory<>("Telephone"));
        tvEmailEntreprise.setCellValueFactory(new PropertyValueFactory<>("email"));
        tvNomEntreprise.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        tvMatriculeFiscalEntreprise.setCellValueFactory(new PropertyValueFactory<>("MatriculeFiscal"));
        TVentreprise.setItems(data);
        
           
       }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TVentreprise.setEditable(true);
        tvSecteurEntreprise.setCellFactory(TextFieldTableCell.forTableColumn());
        tvTailleEntreprise.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                tvTelephneEntreprise.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tvNomEntreprise.setCellFactory(TextFieldTableCell.forTableColumn());
        tvMatriculeFiscalEntreprise.setCellFactory(TextFieldTableCell.forTableColumn());
        tvSiteEntreprise.setCellFactory(TextFieldTableCell.forTableColumn());
        tvEmailEntreprise.setCellFactory(TextFieldTableCell.forTableColumn());

        
                  conn = DataSource.getInstance().getCnx();
showEntreprise();

    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
       Entreprise E = TVentreprise.getSelectionModel().getSelectedItem();
       tfIdEntreprise.setText(E.getId().toString());
        tfNomEntreprise.setText(E.getNom());
         tfSecteurEntreprise.setText(E.getSecteur());
          tfMatriculeFiscal.setText(E.getMatriculeFiscal());
           tfSiteEntreprise.setText(E.getSiteWeb());
            tfEmailEntreprise.setText(E.getEmail());
             tfTelephoneEntreprise.setText(E.getTelephone().toString());
              tftailleEntreprise.setText(E.getTaille().toString());
              System.out.println("id="+E.getNom());
             
    }
    
    public void changeSecteurCellEvent(CellEditEvent edittedCell){
        Entreprise E=TVentreprise.getSelectionModel().getSelectedItem();
        E.setSecteur(edittedCell.getNewValue().toString());
        ServiceEntreprise EntrepriseService = new ServiceEntreprise();
        EntrepriseService.modifierChamp("secteur",E.getSecteur(),E.getId());
    }
    
    public void changeNomCellEvent(CellEditEvent edittedCell){
        Entreprise E=TVentreprise.getSelectionModel().getSelectedItem();
        E.setNom(edittedCell.getNewValue().toString());
        
        ServiceEntreprise EntrepriseService = new ServiceEntreprise();
        EntrepriseService.modifierChamp("nom",E.getNom(),E.getId());
    }
    
     public void changeSiteCellEvent(CellEditEvent edittedCell){
        Entreprise E=TVentreprise.getSelectionModel().getSelectedItem();
        E.setSiteWeb(edittedCell.getNewValue().toString());
        
        ServiceEntreprise EntrepriseService = new ServiceEntreprise();
        EntrepriseService.modifierChamp("site_web",E.getSiteWeb(),E.getId());
    }
      public void changeEmailCellEvent(CellEditEvent edittedCell){
        Entreprise E=TVentreprise.getSelectionModel().getSelectedItem();
        E.setEmail(edittedCell.getNewValue().toString());
        
        ServiceEntreprise EntrepriseService = new ServiceEntreprise();
        EntrepriseService.modifierChamp("email",E.getEmail(),E.getId());
    }
      
       public void changeMatriculeCellEvent(CellEditEvent edittedCell){
        Entreprise E=TVentreprise.getSelectionModel().getSelectedItem();
        E.setMatriculeFiscal(edittedCell.getNewValue().toString());
        
        ServiceEntreprise EntrepriseService = new ServiceEntreprise();
        EntrepriseService.modifierChamp("matricule_fiscal",E.getMatriculeFiscal(),E.getId());
    }
       
          public void changeTailleCellEvent(CellEditEvent edittedCell){
        Entreprise E=TVentreprise.getSelectionModel().getSelectedItem();
        E.setTaille(Integer.parseInt(edittedCell.getNewValue().toString()));
        
        ServiceEntreprise EntrepriseService = new ServiceEntreprise();
        EntrepriseService.modifierChamp("taille",E.getTaille().toString(),E.getId());
    }
          
            public void changeTelephoneCellEvent(CellEditEvent edittedCell){
        Entreprise E=TVentreprise.getSelectionModel().getSelectedItem();
        E.setTelephone(Integer.parseInt(edittedCell.getNewValue().toString()));
        
        ServiceEntreprise EntrepriseService = new ServiceEntreprise();
        EntrepriseService.modifierChamp("telephone",E.getTelephone().toString(),E.getId());
    }
}