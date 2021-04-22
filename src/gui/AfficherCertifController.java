/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.BaseColor;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import entity.Certification;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import services.CertificationService;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.controlsfx.control.Notifications;
import utils.DataSource;



/**
 * FXML Controller class
 *
 * @author ouaji
 */
public class AfficherCertifController implements Initializable {

    
    
    
    
    
    
     @FXML
    private TableView<Certification> CertifTable;
    @FXML
    private TableColumn<Certification , Integer> idcol;
    
      @FXML
    private TableColumn delCol;
    
    @FXML
    private TableColumn<Certification, String> typecol;
    @FXML
    private TableColumn<Certification, Date> datecol;
    @FXML
    private TableColumn<Certification, Integer> prixcol;
    @FXML
    private TableColumn<Certification, String> desccol;
    @FXML
    private TableColumn<Certification, String> nomcol;
    @FXML
    private TableColumn<Certification, String> entreprisecol;
    @FXML
    private Button Refraichir;
    @FXML
    private Button Ajouter;
   
    
    
     CertificationService sp= new CertificationService();
 
    List<Certification> lr=sp.readAll();
    ObservableList<Certification> datalist=FXCollections.observableArrayList(lr);
    @FXML
    private Button Btn_Trie;
    @FXML
    private TextField STitre;
    @FXML
    private Button demande;
    private Label date;
    @FXML
    private Button Modfier;
    @FXML
    private TextField typemod;
    @FXML
    private TextField descmod;
    @FXML
    private TextField prixmod;
    @FXML
    private ComboBox<String> nommod;
    @FXML
    private DatePicker datemod;
    @FXML
    private TextField entreprisemod;
    @FXML
    private Button enr;
    @FXML
    private Label type;
    @FXML
    private Label prix;
    @FXML
    private Label desc;
    @FXML
    private Label nom;
    @FXML
    private Label entreprise;
    @FXML
    private AnchorPane Ldate;
    @FXML
    private Button pdf1;
    private Pagination pagination;
  
    
    
    
    
    
    CertificationService sa = new CertificationService();
    List<Certification> l = sa.readAll();
    ObservableList<Certification> data = FXCollections.observableArrayList(l);
    @FXML
    private Button stat;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
          ObservableList<String> list= FXCollections.observableArrayList("Informatique","Electronique","Mecanique");
        //nommod.setItems(list);
        
        
        
        
        
        
        
        // TODO
     idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
     typecol.setCellValueFactory(new PropertyValueFactory<>("type"));
     datecol.setCellValueFactory(new PropertyValueFactory<>("date_passage"));
     prixcol.setCellValueFactory(new PropertyValueFactory<>("prix"));
     desccol.setCellValueFactory(new PropertyValueFactory<>("description"));
     nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
     entreprisecol.setCellValueFactory(new PropertyValueFactory<>("entreprise_id"));
         CertifTable.setItems(data);

     
     
     
     
      //BOUTTON DE SUPPRESSION
        //creation du cell factory pour inserer le boutton dans chaque ligne
        Callback<TableColumn<Certification,String>, TableCell<Certification,String>> cellFactory =(param) -> {
          final TableCell<Certification,String> cell=new TableCell<Certification,String>(){
             
          
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
                     Certification act = getTableView().getItems().get(getIndex());
                     sp.supprimer(act.getId());
                       CertifTable.getItems().clear();
                                 CertifTable.getItems().addAll(sp.readAll());
                     JOptionPane.showMessageDialog(null, "Certification supprimée !");
                      
                                 
                                 
                 });
             }
            };
         
          };  
            return cell;  
            
            
            
            
            
            
        };
        delCol.setCellFactory(cellFactory);
        
        CertifTable.setItems(datalist);
     
        
        
        
        
           //recherche dynamique 
            FilteredList<Certification> filteredData = new FilteredList<> (datalist,b -> true);
          STitre.textProperty().addListener((Observable, oldValue , newValue)-> {
              filteredData.setPredicate(event-> {
              if(newValue == null || newValue.isEmpty()) {
                  return true;
              }
              
                      String lowerCaseFilter = newValue.toLowerCase();
                      if(event.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                      else if (event.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                      else return false;
            
          });
          });
          SortedList<Certification> sorteddata = new SortedList<>(filteredData);
          sorteddata.comparatorProperty().bind(CertifTable.comparatorProperty());
          CertifTable.setItems(sorteddata);
     
     
     
     
     

    }    
    
    
     
  
    
    

    
    
    

    private void loadDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void Refraichir(ActionEvent event) {
        CertificationService sp = new CertificationService();
              List<Certification> lr=sp.readAll();
     ObservableList<Certification> db=FXCollections.observableArrayList(lr);

     idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
     typecol.setCellValueFactory(new PropertyValueFactory<>("type"));
     datecol.setCellValueFactory(new PropertyValueFactory<>("date_passage"));
     prixcol.setCellValueFactory(new PropertyValueFactory<>("prix"));
     desccol.setCellValueFactory(new PropertyValueFactory<>("description"));
       nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
     entreprisecol.setCellValueFactory(new PropertyValueFactory<>("entreprise_id"));
     
    CertifTable.setItems(db);
        
    
    
          
        
       
        
    
}

    private void search(ObservableList<Certification> db) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void AjouterCertif1(ActionEvent event) throws IOException {
   
        
     try {
            Parent parent = FXMLLoader.load(getClass().getResource("AjouterCertif.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.out.println("erreur");
        }
       
        
    }

    @FXML
    private void TrieDesc(ActionEvent event) {
   
          List<Certification> lr=sp.Trie_Desc();
     ObservableList<Certification> db=FXCollections.observableArrayList(lr);
     //col_idcoach.setCellValueFactory(new PropertyValueFactory<>("id_coach"));
     idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
     typecol.setCellValueFactory(new PropertyValueFactory<>("type"));
     datecol.setCellValueFactory(new PropertyValueFactory<>("date_passage"));
     prixcol.setCellValueFactory(new PropertyValueFactory<>("prix"));
     desccol.setCellValueFactory(new PropertyValueFactory<>("description"));
       nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
     entreprisecol.setCellValueFactory(new PropertyValueFactory<>("entreprise_id"));
     
     CertifTable.setItems(db);
        
        
        
        
    }


 

    @FXML
    private void demandeCertif(ActionEvent event) {
        
        
          try {
            Parent parent = FXMLLoader.load(getClass().getResource("AfficherDemandeCertif.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.out.println("erreur");
        }
       
        
        
        
    }

    @FXML
    private void ModifierCertif(ActionEvent event) {
        
        ObservableList<String> list= FXCollections.observableArrayList("Informatique","Electronique","Mecanique");
        nommod.setItems(list);
        
        
           Certification Certification = new Certification();

        Certification = CertifTable.getSelectionModel().getSelectedItem();

        if (Certification == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veillez Choisir une Certification");
            alert.show();
        } 
        
        
        else {

            System.out.println(Certification);
            /**
             * ******************
             */
            type.setVisible(true);
           // Ldate.setVisible(true);
            prix.setVisible(true);
            desc.setVisible(true);
            nom.setVisible(true);
            entreprise.setVisible(true);

            /**
             * ****************
             */
            typemod.setVisible(true);
            datemod.setVisible(true);
            prixmod.setVisible(true);
            descmod.setVisible(true);
            nommod.setVisible(true);
            entreprisemod.setVisible(true);

            typemod.setText(Certification.getType());
             datemod.setValue(Certification.getDate_passage().toLocalDate());
            prixmod.setText(Double.toString(Certification.getPrix()));
            descmod.setText(Certification.getDescription());
            Certification.setNom(nommod.getSelectionModel().getSelectedItem().toString());
            entreprisemod.setText(Double.toString(Certification.getEntreprise_id()));
            

        }
        
        
         
} 
    
         

        
        
    

    @FXML
    private void EnregistrerMod(ActionEvent event) {
        
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("vous êtes sûr de modifier l'utilisateur ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            Certification Certification = new Certification();
            Certification = CertifTable.getSelectionModel().getSelectedItem();
            CertificationService CertificationService = new CertificationService();

            Certification.setType(typemod.getText());
            Certification.setDate_passage(Date.valueOf(datemod.getValue()));
            prixmod.setText(Double.toString(Certification.getPrix()));
            Certification.setDescription(descmod.getText());
            Certification.setNom(nommod.getSelectionModel().getSelectedItem());
            entreprisemod.setText(Double.toString(Certification.getEntreprise_id()));

            CertificationService.modifier_2(Certification, Certification.getId());

            /**
             * ******************
             */
            type.setVisible(false);
           // Ldate.setVisible(false);
            prix.setVisible(false);
            desc.setVisible(false);
            nom.setVisible(false);
            entreprise.setVisible(false);

            /**
             * ****************
             */
            typemod.setVisible(false);
            datemod.setVisible(false);
            prixmod.setVisible(false);
            descmod.setVisible(false);
            nommod.setVisible(false);
            entreprisemod.setVisible(false);
            
            
                   
        Notifications notificationBuilder = Notifications.create()
.title("Félicitation")
.text("Certification Modifiée")
.graphic(null)
.hideAfter(Duration.seconds(5))                
.position(Pos.TOP_RIGHT)
.onAction(new EventHandler<ActionEvent>(){
@Override
public void handle(ActionEvent event) {
    System.out.println("clicked in notification");
}
});
          notificationBuilder.showConfirm();
          
          
          
           CertifTable.getItems().clear();
                                 CertifTable.getItems().addAll(sp.readAll());
            
            

//             CertifTable.getItems().clear();
  //          CertifTable.getItems().addAll(CertificationService.readAll());

         
    
            

            /**
             * ***** notif ****
             */
            /*  TrayNotification tray = new TrayNotification("Avec succès", "Maison N° " + users.getId() + " Modification Effectuée avec Succés !", SUCCESS);
        tray.setAnimationType(AnimationType.POPUP);
        tray.showAndDismiss(Duration.seconds(10));
        /**
         * ********************
             */
        }
    }
    

    @FXML
    private void pdf(ActionEvent event) throws ClassNotFoundException, ClassNotFoundException, SQLException, IOException, URISyntaxException {
   
     try {
              Class.forName("com.mysql.jdbc.Driver");
                  Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/jobbridge", "root", "");
      PreparedStatement pt = con.prepareStatement("select * from certification");
            ResultSet rs = pt.executeQuery();
            
                       /* Step-2: Initialize PDF documents - logical objects */

                       com.itextpdf.text.Document my_pdf_report = new com.itextpdf.text.Document();
                       
                       PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_report_from_sql_using_java.pdf"));
                       
                        my_pdf_report.open();  
//                       my_pdf_report.add(new Paragraph(new Date().toString()));
//                            Image img = Image.getInstance("c:/6.png");
//                            my_pdf_report.add(img);
                             my_pdf_report.add(new Paragraph("ACTIVITES"));
                       my_pdf_report.addCreationDate();
              
                       
                       //we have four columns in our table
                       PdfPTable my_report_table = new PdfPTable(7);
                             
                       //create a cell object
                       PdfPCell table_cell;
                       
  
                       
                       
                       
                                       table_cell=new PdfPCell(new Phrase(" id"));
                                      table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("type"));
                                        table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("date_passage"));
                                       table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("prix"));
                                       table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                        table_cell=new PdfPCell(new Phrase("desc"));
                                       table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                        table_cell=new PdfPCell(new Phrase("nom"));
                                       table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                        table_cell=new PdfPCell(new Phrase("ent_id"));
                                       table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       
                                       

                                      while(rs.next()){
                                      
                                       String id= rs.getString("id");
                                       table_cell=new PdfPCell(new Phrase(id));
                                       my_report_table.addCell(table_cell);
                                       String type=rs.getString("type");
                                       table_cell=new PdfPCell(new Phrase(type));
                                       my_report_table.addCell(table_cell);
                                       String dt=rs.getString("date_passage");
                                       table_cell=new PdfPCell(new Phrase(dt));
                                       my_report_table.addCell(table_cell);
                                       String d=rs.getString("prix");
                                       table_cell=new PdfPCell(new Phrase(d));
                                       my_report_table.addCell(table_cell);
                                       String de=rs.getString("description");
                                       table_cell=new PdfPCell(new Phrase(de));
                                       my_report_table.addCell(table_cell);
                                       String db=rs.getString("nom");
                                       table_cell=new PdfPCell(new Phrase(db));
                                       my_report_table.addCell(table_cell);
                                       String e=rs.getString("entreprise_id");
                                       table_cell=new PdfPCell(new Phrase(e));
                                       my_report_table.addCell(table_cell);
                                        
                       }
                       /* Attach report table to PDF */
                       
                       my_pdf_report.add(my_report_table); 
                       
             System.out.println(my_pdf_report);
                       my_pdf_report.close();
                       JOptionPane.showMessageDialog(null, "impression effectuée");

                       /* Close all DB related objects */
                       rs.close();
                       pt.close(); 
                       con.close();               


       } catch (FileNotFoundException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
       } catch (DocumentException ex) {
             Logger.getLogger(AfficherCertifController.class.getName()).log(Level.SEVERE, null, ex);
         }


    }

    private void excel(ActionEvent event) throws SQLException, FileNotFoundException, IOException{
        Connection cnx = DataSource.getInstance().getCnx();
        String query = "Select * from certification";
         PreparedStatement pst = cnx.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            
            //  InputStream inp = new FileInputStream("workbook.xlsx");
//Workbook wb = WorkbookFactory.create(inp);
//Sheet sheet = wb.getSheetAt(0);
            


//File filename = new File("C:\\Users\\ouaji\\Desktop\\certif.xlsx");
//FileInputStream isr= new FileInputStream(filename);

XSSFWorkbook wb = new XSSFWorkbook();

            
      //     XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Détails Certification");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("id");
            header.createCell(1).setCellValue("type");
            header.createCell(2).setCellValue("dtae");
            header.createCell(3).setCellValue("prix");
            header.createCell(3).setCellValue("description");
            header.createCell(3).setCellValue("nom");
            header.createCell(3).setCellValue("entreprise_id");


            
            int index = 1;
            while(rs.next()){
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("id"));
                row.createCell(1).setCellValue(rs.getString("type"));
                row.createCell(2).setCellValue(rs.getString("date_passage"));
                row.createCell(3).setCellValue(rs.getString("prix"));
                 row.createCell(3).setCellValue(rs.getString("description"));
                row.createCell(3).setCellValue(rs.getString("nom"));
                row.createCell(3).setCellValue(rs.getString("entreprise_id"));


                index++;
            }
            
            FileOutputStream file = new FileOutputStream("C:\\Users\\ouaji\\Desktop\\certif.xlsx");
            wb.write(file);
            file.close();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Exportation effectuée!!!");
            alert.showAndWait();
            pst.close();
            rs.close();
    }

    @FXML
    private void stat(ActionEvent event) {
        
              try {
            Parent parent = FXMLLoader.load(getClass().getResource("Stat_Certif.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.out.println("erreur");
        }
        
        
        
        
    }


 
   
    
    
    
}