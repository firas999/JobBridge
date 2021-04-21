/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import javax.management.Notification;
import javax.swing.JOptionPane;
import models.Entreprise;
import org.controlsfx.control.Notifications;
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
    
              ObservableList<Entreprise> data=FXCollections.observableArrayList();

    private TextField tfIdEntreprise;

    private TextField tfNomEntreprise;

    private TextField tfSecteurEntreprise;

    private TextField tfMatriculeFiscal;

    private TextField tfSiteEntreprise;

    private TextField tfEmailEntreprise;

    private TextField tfTelephoneEntreprise;

    private TextField tftailleEntreprise;

    @FXML
   private TableView<Entreprise> TVentreprise;
    
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

    
    ServiceEntreprise EntrepriseService = new ServiceEntreprise();
    @FXML
    private TableColumn tvActions;
    @FXML
    private TableColumn tvActions1;
    @FXML
    private TableColumn tvActions2;
    @FXML
    private TextField liveSearchEntreprise;
    @FXML
    private TableColumn tvActions4;
    
    


    void DeleteEntreprise() {

    }

    void UpdateEntreprise() {

    }

    @FXML
    void addEntreprise() {

    }

    @FXML
    void handleButtonAction(ActionEvent event) {
if (event.getSource()==btnInsert){
   try {
                               FXMLLoader loader=new FXMLLoader(getClass().getResource("/Interface/AjouterEntreprise.fxml"));
        
                               Parent root=loader.load();
                              // AjouterEntrepriseController AEcontroller =loader.getController();
                               
                               //AEcontroller.setIDentreprise(selectedEntreprise.getId().toString());
                               Stage  stage=new Stage();
                               stage.setScene(new Scene(root));
                               stage.show();
                               
                               
//                             
                           } catch (IOException ex) {
                               Logger.getLogger(EntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
                           }
    
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

   
        
        public void notif(String title,String text,Entreprise E){
            
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
        notificationBuilder.showInformation();
                
        }
//affichage
       public void showEntreprise() {
          data=(ObservableList<Entreprise>) EntrepriseService.afficher();
           
        tvSecteurEntreprise.setCellValueFactory(new PropertyValueFactory<>("Secteur"));
        tvSiteEntreprise.setCellValueFactory(new PropertyValueFactory<>("SiteWeb"));
        tvTailleEntreprise.setCellValueFactory(new PropertyValueFactory<>("Taille"));
        tvTelephneEntreprise.setCellValueFactory(new PropertyValueFactory<>("Telephone"));
        tvEmailEntreprise.setCellValueFactory(new PropertyValueFactory<>("email"));
        tvNomEntreprise.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        tvMatriculeFiscalEntreprise.setCellValueFactory(new PropertyValueFactory<>("MatriculeFiscal"));
        
         
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
   
        

        
         Callback<TableColumn<Entreprise,String>,TableCell<Entreprise,String>> cellFactory4=(param) -> {
       //make the table cell containing button
       final TableCell<Entreprise,String> cell=new TableCell<Entreprise,String>(){
           @Override
           protected void updateItem(String item, boolean empty) {
               super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
               //ensure that cell is created only on non-empty rows
               if (empty){
                   setGraphic(null);
                   setText(null);
                   
               }else{
                   //create the action buton
                   
                   final Button editbutton=new Button("Voir plus ");
                   editbutton.setOnAction(edit->{
                       if (getTableView().getItems().get(getIndex()) == null) {
                           System.out.println("**************");
                       } else {
                           
                            Entreprise selectedEntreprise = getTableView().getItems().get(getIndex());
                               
                              // EntrepriseService.afficher();
                               System.out.println("xxxxxxxxxxxxx"+selectedEntreprise.getId().getClass());
                               
                               
                           try {
                               FXMLLoader loader=new FXMLLoader(getClass().getResource("/Interface/DetailEntreprise.fxml"));
        
                               Parent root=loader.load();
                               DetailEntrepriseController Detailcontroller =loader.getController();
                               
                               Detailcontroller.setTEXTFIELDS(selectedEntreprise.getNom(),selectedEntreprise.getSecteur()
                                       , selectedEntreprise.getMatriculeFiscal(),selectedEntreprise.getSiteWeb(),
                                       selectedEntreprise.getEmail(),selectedEntreprise.getTelephone().toString(),selectedEntreprise.getTaille().toString());
                               Stage  stage=new Stage();
                               stage.setScene(new Scene(root));
                               stage.show();
                               
                               
                               
                               
//                             
                           } catch (IOException ex) {
                               Logger.getLogger(EntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                         
                       }
                       
                   });
                   setGraphic(editbutton);
                   setText(null);
                   
               }
           }
           
       };
       return cell;
   };
        
        
               //create cell factory to insert a button in every row
   Callback<TableColumn<Entreprise,String>,TableCell<Entreprise,String>> cellFactory1=(param) -> {
       //make the table cell containing button
       final TableCell<Entreprise,String> cell=new TableCell<Entreprise,String>(){
           @Override
           protected void updateItem(String item, boolean empty) {
               super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
               //ensure that cell is created only on non-empty rows
               if (empty){
                   setGraphic(null);
                   setText(null);
                   
               }else{
                   //create the action buton
                   
                   final Button editbutton=new Button("modifer ");
                   editbutton.setOnAction(edit->{
                       if (getTableView().getItems().get(getIndex()) == null) {
                           System.out.println("**************");
                       } else {
                           
                            Entreprise selectedEntreprise = getTableView().getItems().get(getIndex());
                               
                              // EntrepriseService.afficher();
                               System.out.println("xxxxxxxxxxxxx"+selectedEntreprise.getId().getClass());
                               
                               
                           try {
                               FXMLLoader loader=new FXMLLoader(getClass().getResource("/Interface/ModifierEntreprise.fxml"));
        
                               Parent root=loader.load();
                               ModifierEntrepriseController AFcontroller =loader.getController();
                               
                               AFcontroller.setIDentreprise(selectedEntreprise.getId().toString());
                               AFcontroller.setTEXTFIELDS(selectedEntreprise.getNom(),selectedEntreprise.getSecteur()
                                       , selectedEntreprise.getMatriculeFiscal(),selectedEntreprise.getSiteWeb(),
                                       selectedEntreprise.getEmail(),selectedEntreprise.getTelephone().toString(),selectedEntreprise.getTaille().toString());
                               Stage  stage=new Stage();
                               stage.setScene(new Scene(root));
                               stage.show();
                               
                               
                               
                               
//                             
                           } catch (IOException ex) {
                               Logger.getLogger(EntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                         
                       }
                       
                   });
                   setGraphic(editbutton);
                   setText(null);
                   
               }
           }
           
       };
       return cell;
   };

        
        
       //create cell factory to insert a button in every row
   Callback<TableColumn<Entreprise,String>,TableCell<Entreprise,String>> cellFactory3=(param) -> {
       //make the table cell containing button
       final TableCell<Entreprise,String> cell=new TableCell<Entreprise,String>(){
           @Override
           protected void updateItem(String item, boolean empty) {
               super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
               //ensure that cell is created only on non-empty rows
               if (empty){
                   setGraphic(null);
                   setText(null);
                   
               }else{
                   //create the action buton
                   
                   final Button editbutton=new Button("supprimer ");
                   editbutton.setOnAction(edit->{
                       if (getTableView().getItems().get(getIndex()) == null) {
                           System.out.println("**************");
                       } else {
                           
                           Entreprise selectedEntreprise = getTableView().getItems().get(getIndex());
                               
                              // EntrepriseService.afficher();
                               System.out.println("xxxxxxxxxxxxx"+selectedEntreprise.getId());
                               
                         EntrepriseService.supprimerFormationCreerParEntreprise(selectedEntreprise.getId());
                         EntrepriseService.supprimer(selectedEntreprise.getId());
                         
                           notif("Suppression effectuée", "L'entreprise "+selectedEntreprise.getNom()+" est supprimée", selectedEntreprise);
                           
                       }
                       
                   });
                   setGraphic(editbutton);
                   setText(null);
                   
               }
           }
           
       };
       return cell;
   };





//create cell factory to insert a button in every row
   Callback<TableColumn<Entreprise,String>,TableCell<Entreprise,String>> cellFactory2=(param) -> {
       //make the table cell containing button
       final TableCell<Entreprise,String> cell=new TableCell<Entreprise,String>(){
           @Override
           protected void updateItem(String item, boolean empty) {
               super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
               //ensure that cell is created only on non-empty rows
               if (empty){
                   setGraphic(null);
                   setText(null);
                   
               }else{
                   //create the action buton
                   
                   final Button editbutton=new Button("Ajouter Formation ");
                   editbutton.setOnAction(edit->{
                       if (getTableView().getItems().get(getIndex()) == null) {
                           System.out.println("**************");
                       } else {
                           
                           Entreprise selectedEntreprise = getTableView().getItems().get(getIndex());
                               
                              // EntrepriseService.afficher();
                               System.out.println("xxxxxxxxxxxxx"+selectedEntreprise.getId().getClass());
                               
                               
                           try {
                               FXMLLoader loader=new FXMLLoader(getClass().getResource("/Interface/AjouterFormations.fxml"));
        
                               Parent root=loader.load();
                               AjouterFormationsController AFcontroller =loader.getController();
                               
                               AFcontroller.setIDentreprise(selectedEntreprise.getNom());
                               Stage  stage=new Stage();
                               stage.setScene(new Scene(root));
                               stage.show();
                               
                               
//                             
                           } catch (IOException ex) {
                               Logger.getLogger(EntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           
                         
                       }
                       
                   });
                   setGraphic(editbutton);
                   setText(null);
                   
               }
           }
           
       };
       return cell;
   };
    tvActions4.setCellFactory(cellFactory4);
      tvActions2.setCellFactory(cellFactory1);
   tvActions.setCellFactory(cellFactory2);
   tvActions1.setCellFactory(cellFactory3);

//        tfIdEntreprise.setVisible(false);
                  conn = DataSource.getInstance().getCnx();
showEntreprise();


 FilteredList<Entreprise> filteredData = new FilteredList<> (data,b -> true);
          liveSearchEntreprise.textProperty().addListener((Observable, oldValue , newValue)-> {
              filteredData.setPredicate(Entreprise-> {
              if(newValue == null || newValue.isEmpty()) {
                  return true;
              }
              
                      String lowerCaseFilter = newValue.toLowerCase();
                      if(Entreprise.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                      else if (Entreprise.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                      else if (Entreprise.getMatriculeFiscal().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                      else if (Entreprise.getSiteWeb().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                      else if (Entreprise.getSecteur().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                       else if (Entreprise.getTaille().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                        else if (Entreprise.getTelephone().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                      else return false;
            
          });
          });
          SortedList<Entreprise> sorteddata = new SortedList<>(filteredData);
          sorteddata.comparatorProperty().bind(TVentreprise.comparatorProperty());
                  TVentreprise.setItems(sorteddata); 

    }

      /*Entreprise E = TVentreprise.getSelectionModel().getSelectedItem();
       tfIdEntreprise.setText(E.getId().toString());
        tfNomEntreprise.setText(E.getNom());
         tfSecteurEntreprise.setText(E.getSecteur());
          tfMatriculeFiscal.setText(E.getMatriculeFiscal());
           tfSiteEntreprise.setText(E.getSiteWeb());
            tfEmailEntreprise.setText(E.getEmail());
             tfTelephoneEntreprise.setText(E.getTelephone().toString());
              tftailleEntreprise.setText(E.getTaille().toString());
              System.out.println("id="+E.getNom());
             */
   
    @FXML
    public void changeSecteurCellEvent(CellEditEvent edittedCell){
        Entreprise E=TVentreprise.getSelectionModel().getSelectedItem();
        E.setSecteur(edittedCell.getNewValue().toString());
        ServiceEntreprise EntrepriseService = new ServiceEntreprise();
        EntrepriseService.modifierChamp("secteur",E.getSecteur(),E.getId());
    }
    
    @FXML
    public void changeNomCellEvent(CellEditEvent edittedCell){
        Entreprise E=TVentreprise.getSelectionModel().getSelectedItem();
        E.setNom(edittedCell.getNewValue().toString());
        
        ServiceEntreprise EntrepriseService = new ServiceEntreprise();
        EntrepriseService.modifierChamp("nom",E.getNom(),E.getId());
    }
    
    @FXML
     public void changeSiteCellEvent(CellEditEvent edittedCell){
        Entreprise E=TVentreprise.getSelectionModel().getSelectedItem();
        E.setSiteWeb(edittedCell.getNewValue().toString());
        
        ServiceEntreprise EntrepriseService = new ServiceEntreprise();
        EntrepriseService.modifierChamp("site_web",E.getSiteWeb(),E.getId());
    }
    @FXML
      public void changeEmailCellEvent(CellEditEvent edittedCell){
        Entreprise E=TVentreprise.getSelectionModel().getSelectedItem();
        E.setEmail(edittedCell.getNewValue().toString());
        
        ServiceEntreprise EntrepriseService = new ServiceEntreprise();
        EntrepriseService.modifierChamp("email",E.getEmail(),E.getId());
    }
      
    @FXML
       public void changeMatriculeCellEvent(CellEditEvent edittedCell){
        Entreprise E=TVentreprise.getSelectionModel().getSelectedItem();
        E.setMatriculeFiscal(edittedCell.getNewValue().toString());
        
        
        ServiceEntreprise EntrepriseService = new ServiceEntreprise();
        EntrepriseService.modifierChamp("matricule_fiscal",E.getMatriculeFiscal(),E.getId());
    }
       
    @FXML
          public void changeTailleCellEvent(CellEditEvent edittedCell){
        Entreprise E=TVentreprise.getSelectionModel().getSelectedItem();
        E.setTaille(Integer.parseInt(edittedCell.getNewValue().toString()));
        
        ServiceEntreprise EntrepriseService = new ServiceEntreprise();
        EntrepriseService.modifierChamp("taille",E.getTaille().toString(),E.getId());
    }
          
    @FXML
            public void changeTelephoneCellEvent(CellEditEvent edittedCell){
        Entreprise E=TVentreprise.getSelectionModel().getSelectedItem();
        E.setTelephone(Integer.parseInt(edittedCell.getNewValue().toString()));
        
        ServiceEntreprise EntrepriseService = new ServiceEntreprise();
        EntrepriseService.modifierChamp("telephone",E.getTelephone().toString(),E.getId());
    }

    @FXML
    private void pdf(ActionEvent event) {
    }

    @FXML
    private void Excel(ActionEvent event) {
    }
         
    
    
            
            
}
