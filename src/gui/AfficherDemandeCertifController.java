/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entity.Certification;
import entity.DemandeCertification;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import services.DemandeCertificationService;


/**
 * FXML Controller class
 *
 * @author ouaji
 */
public class AfficherDemandeCertifController implements Initializable {

    @FXML
    private TableView<DemandeCertification> DemandeCertifTable;
    @FXML
    private TableColumn<DemandeCertification,String> idcol;
    @FXML
    private TableColumn<DemandeCertification,String> certifcol;
    @FXML
    private TableColumn<DemandeCertification,String> nom1col;
    @FXML
    private TableColumn<DemandeCertification,String> date1col;
    @FXML
    private TableColumn<DemandeCertification,String> experiencecol;
    @FXML
    private TableColumn<DemandeCertification,String> emailcol;

    
    
    
    @FXML
    private TableColumn<DemandeCertification,String> prenom1col;
    @FXML
    private TextField RecherDCertif;
    @FXML
    private Button AjDmdCertif;
    @FXML
    private Button ref;
    
     DemandeCertificationService m1= new DemandeCertificationService();
 
    List<DemandeCertification> dc=m1.readAll();
    ObservableList<DemandeCertification> demandelist=FXCollections.observableArrayList(dc);
    @FXML
    private TableColumn SupCol;
    @FXML
    private Pagination pagination;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
   
        
        
        // TODO
        
     idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
     certifcol.setCellValueFactory(new PropertyValueFactory<>("certification_id"));
     nom1col.setCellValueFactory(new PropertyValueFactory<>("nom_participant"));
     prenom1col.setCellValueFactory(new PropertyValueFactory<>("prenom_participant"));
     date1col.setCellValueFactory(new PropertyValueFactory<>("date_demande"));
     experiencecol.setCellValueFactory(new PropertyValueFactory<>("experience_participant"));
     emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
     
     
         int paginas =1;
if(demandelist.size()%filpag()==0)
{
    paginas =demandelist.size()/filpag();
}
else if(demandelist.size()>filpag()){
    paginas =demandelist.size()/filpag()+1;
}
pagination.setPageCount(paginas);
pagination.setCurrentPageIndex(0);
pagination.setPageFactory(this::createPage);
     
     
     
     
     
     
     //BOUTTON DE SUPPRESSION
        //creation du cell factory pour inserer le boutton dans chaque ligne
        Callback<TableColumn<DemandeCertification,String>, TableCell<DemandeCertification,String>> cellFactory =(param) -> {
          final TableCell<DemandeCertification,String> cell=new TableCell<DemandeCertification,String>(){
             
          
          //override de la methode updateItem
         @Override
         public void updateItem(String item,boolean empty){
             super.updateItem(item, empty);
             if (empty) {
                 setGraphic(null);
                 setText(null);
             }
                else{
                // creation du boutton
                final Button deleteButton = new Button("supprimer");
                //liaison avec listener
                
                 setGraphic(deleteButton);
                 setText(null);
                 deleteButton.setOnAction(e -> {
                     //extraire les infos de la ligne selectionnée
                     DemandeCertification act = getTableView().getItems().get(getIndex());
                     m1.supprimer(act.getId());
                     JOptionPane.showMessageDialog(null, "DemandeCertification supprimée !");

               
                     
                 });
             }
            };
          };  
            return cell;   
            
           
            
        };
        
        
       SupCol.setCellFactory(cellFactory); 
       DemandeCertifTable.setItems(demandelist);
        DemandeCertifTable.getItems().clear();
                                 DemandeCertifTable.getItems().addAll(m1.readAll());
        
        
              //recherche dynamique 
            FilteredList<DemandeCertification> filteredData = new FilteredList<> (demandelist,b -> true);
          RecherDCertif.textProperty().addListener((Observable, oldValue , newValue)-> {
              filteredData.setPredicate(event-> {
              if(newValue == null || newValue.isEmpty()) {
                  return true;
              }
              
                      String lowerCaseFilter = newValue.toLowerCase();
                      if(event.getNom_participant().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                      else if (event.getPrenom_participant().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                      else return false;
            
          });
          });
     
           SortedList<DemandeCertification> sorteddata = new SortedList<>(filteredData);
          sorteddata.comparatorProperty().bind(DemandeCertifTable.comparatorProperty());
          DemandeCertifTable.setItems(sorteddata);
        
     
        
    }    

    @FXML
    private void AjDmdCertif1(ActionEvent event) throws IOException {
   
        
     try {
            Parent parent = FXMLLoader.load(getClass().getResource("AjouterDemandeCertif.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.out.println("erreur");
        }
    }
    

    
    private void loadDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @FXML
    private void AfficherDmndCert(ActionEvent event) {
        
       List<DemandeCertification> lr=m1.readAll();
     ObservableList<DemandeCertification> db=FXCollections.observableArrayList(lr);

     idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
     certifcol.setCellValueFactory(new PropertyValueFactory<>("certification_id"));
     nom1col.setCellValueFactory(new PropertyValueFactory<>("nom_participant"));
     prenom1col.setCellValueFactory(new PropertyValueFactory<>("prenom_participant"));
     date1col.setCellValueFactory(new PropertyValueFactory<>("date_demande"));
     experiencecol.setCellValueFactory(new PropertyValueFactory<>("experience_participant"));
     emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
     
         int paginas =1;
if(db.size()%filpag()==0)
{
    paginas =db.size()/filpag();
}
else if(db.size()>filpag()){
    paginas =db.size()/filpag()+1;
}
pagination.setPageCount(paginas);
pagination.setCurrentPageIndex(0);
pagination.setPageFactory(this::createPage);
     
     
     
   //  DemandeCertifTable.setItems(db);
    }
    
    
    
     public int filpag() {
        return 2;
    }

    private Node createPage(int pagIndex) {
        
        int fromIndex = pagIndex * filpag();
        int toIndex = Math.min(fromIndex + filpag(), demandelist.size());
        DemandeCertifTable.setItems(FXCollections.observableArrayList(demandelist.subList(fromIndex, toIndex)));

        return new BorderPane(DemandeCertifTable);
        
    }
    
     
    
    
    
}
