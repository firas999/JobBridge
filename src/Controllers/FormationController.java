/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import utils.DataSource;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.Notifications;
import java.io.IOException;
import static java.sql.Date.valueOf;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import models.Entreprise;
import models.Formation;
import org.controlsfx.control.Notifications;
import services.ServiceEntreprise;
import services.ServiceFormation;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class FormationController implements Initializable {

     public DataSource ds1;
    private Connection conn;
    public PreparedStatement st;
    public ResultSet rs;
    services.ServiceFormation serviceFormation = new ServiceFormation();
            ObservableList<Formation> data=FXCollections.observableArrayList();
            
    private TextField tfIdFormation;
     @FXML
    private TableView<Formation> TVformation;
    @FXML
    private TableColumn<Formation, Integer> tvIDformation;
    @FXML
    private TableColumn<Formation, String> tvDescriptionFormation;
    @FXML
    private TableColumn<Formation, Integer> tvVHformation;
    @FXML
    private TableColumn<Formation, Date> tvDATEformation;
    @FXML
    private TableColumn<Formation, String> tvAdresseformation;
    @FXML
    private TableColumn<Formation,Integer> tvPRIXformation;
    @FXML
    private TableColumn<Formation, Integer> tvPromoFormation;
    private ComboBox<String> tfEntrepriseFormation;
    @FXML
    private TableColumn<?, ?> tvFE;
    @FXML
    private TableColumn<Formation, String> tvActions;
    @FXML
    private TableColumn<Formation, String> tvActions1;
    @FXML
    private TextField liveSearchFormation;
    @FXML
    private Button pdf;
    @FXML
    private Button exel;
    @FXML
    private Button EntreprisePageBack;
    @FXML
    private Button FormationPageback;
    @FXML
    private Button actualiser;
    
    
    
     public void notif(String title,String text,Formation E){
            
        Notifications notificationBuilder = Notifications.create()
                .title("ajouter avec succes")
                .text("ajouter avec succes")
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
                        //create cell factory to insert a button in every row
   Callback<TableColumn<Formation,String>,TableCell<Formation,String>> cellFactory3=(param) -> {
       //make the table cell containing button
       final TableCell<Formation,String> cell=new TableCell<Formation,String>(){
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
                           
                           Formation selectedFormation = getTableView().getItems().get(getIndex());
                               
                              // EntrepriseService.afficher();
                               System.out.println("xxxxxxxxxxxxx"+selectedFormation.getId().getClass());
                               
                               
                           try {
                               FXMLLoader loader=new FXMLLoader(getClass().getResource("/Interface/ModifierFormation.fxml"));
        
                               Parent root=loader.load();
                               ModifierFormationController MFcontroller =loader.getController();
                               
                               MFcontroller.setTfIdFormation(selectedFormation.getId().toString());
                               MFcontroller.setTextFields(selectedFormation.getDescription(), selectedFormation.getVolumeHoraire().toString(),
                                       selectedFormation.getDate_formation(), selectedFormation.getAdresse(), selectedFormation.getPrix().toString());
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
   Callback<TableColumn<Formation,String>,TableCell<Formation,String>> cellFactory2=(param) -> {
       //make the table cell containing button
       final TableCell<Formation,String> cell=new TableCell<Formation,String>(){
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
                           
                           Formation selectedFormation = getTableView().getItems().get(getIndex());
                               
                              // EntrepriseService.afficher();
                               System.out.println("xxxxxxxxxxxxx"+selectedFormation.getId().getClass());
                               
                               
                               serviceFormation.supprimer(selectedFormation.getId());
                               notif("Suppression effectuée", "La formation"+selectedFormation.getDescription()+"est supprimée", selectedFormation);
                               
                           
                         
                       }
                       
                   });
                   setGraphic(editbutton);
                   setText(null);
                   
               }
           }
           
       };
       return cell;
   };
      tvActions.setCellFactory(cellFactory2);
           tvActions1.setCellFactory(cellFactory3);
        conn = DataSource.getInstance().getCnx();
         
         
         
        
         showFormation();
         FilteredList<Formation> filteredData = new FilteredList<> (data,b -> true);
          liveSearchFormation.textProperty().addListener((Observable, oldValue , newValue)-> {
              filteredData.setPredicate(Formation-> {
              if(newValue == null || newValue.isEmpty()) {
                  return true;
              }
              
                      String lowerCaseFilter = newValue.toLowerCase();
                      if(Formation.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                      else if (Formation.getDate_formation().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                      else if (Formation.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                      else if (Formation.getNomEntreprise().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                      else if (Formation.getPrix().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                       else if (Formation.getVolumeHoraire().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                        //else if (Formation.getTelephone().toString().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                      else return false;
            
          });
          });
          SortedList<Formation> sorteddata = new SortedList<>(filteredData);
          sorteddata.comparatorProperty().bind(TVformation.comparatorProperty());
         TVformation.setItems(sorteddata);
         
         
    }    
    
    
     public void DELETEFormation(){
          int id=Integer.parseInt(tfIdFormation.getText());
           Formation F= new Formation(id);
        
        ServiceFormation FormationService = new ServiceFormation();
        FormationService.supprimer(id);
        showFormation();
      
    }
    
   
     
    public void showFormation(){
 
        data=(ObservableList<Formation>) serviceFormation.afficher();
        tvDescriptionFormation.setCellValueFactory(new PropertyValueFactory<>("Description"));
        tvVHformation.setCellValueFactory(new PropertyValueFactory<>("VolumeHoraire"));
        tvDATEformation.setCellValueFactory(new PropertyValueFactory<>("date_formation"));
        tvAdresseformation.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tvPRIXformation.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tvPromoFormation.setCellValueFactory(new PropertyValueFactory<>("promo"));
        tvFE.setCellValueFactory(new PropertyValueFactory<>("NomEntreprise"));
        
    }

    @FXML
    private void pdf(ActionEvent event) throws ClassNotFoundException, ClassNotFoundException, SQLException, IOException, URISyntaxException, DocumentException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobbridge", "root", "");
            PreparedStatement pt = con.prepareStatement("select * from formation");
            ResultSet rs = pt.executeQuery();

            /* Step-2: Initialize PDF documents - logical objects */
            Document my_pdf_report = new Document() {
            };

            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("Formations.pdf"));

            my_pdf_report.open();
//                       my_pdf_report.add(new Paragraph(new Date().toString()));
//                            Image img = Image.getInstance("c:/6.png");
//                            my_pdf_report.add(img);
            //   my_pdf_report.add(new Paragraph("ACTIVITES"));
            my_pdf_report.addCreationDate();

            //we have four columns in our table
            PdfPTable my_report_table = new PdfPTable(6);

            //create a cell object
            PdfPCell table_cell;

            table_cell = new PdfPCell(new Phrase(" Description"));
            //  table_cell.setBackgroundColor(BaseColor.WHITE);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("volume Horaire"));
            // table_cell.setBackgroundColor(BaseColor.WHITE);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("date formation"));
            // table_cell.setBackgroundColor(BaseColor.WHITE);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("adresse"));
            // table_cell.setBackgroundColor(BaseColor.WHITE);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("prix"));
            //  table_cell.setBackgroundColor(BaseColor.WHITE);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("promotion"));
            //  table_cell.setBackgroundColor(BaseColor.WHITE);
            my_report_table.addCell(table_cell);

            while (rs.next()) {

                String nom = rs.getString("description");
                table_cell = new PdfPCell(new Phrase(nom));
                my_report_table.addCell(table_cell);
                int titre = rs.getInt("volume_horaire");
                table_cell = new PdfPCell(new Phrase(titre+""));
                my_report_table.addCell(table_cell);
                String desc = rs.getString("date_formation");
                table_cell = new PdfPCell(new Phrase(desc));
                my_report_table.addCell(table_cell);
                String d = rs.getString("adresse");
                table_cell = new PdfPCell(new Phrase(d));
                my_report_table.addCell(table_cell);
                int p = rs.getInt("prix");
                table_cell = new PdfPCell(new Phrase(p+""));
                my_report_table.addCell(table_cell);
                int promo=rs.getInt("promo");
                String s="Pas de promotion";
                if (promo==1)
                s ="en promotion";
                
                table_cell = new PdfPCell(new Phrase(s));
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
        }

    
    }

    @FXML
    private void exel(ActionEvent event)  throws SQLException, FileNotFoundException, IOException {
        Connection cnx = DataSource.getInstance().getCnx();
        String query = "Select * from formation";
        PreparedStatement pst = cnx.prepareStatement(query);
        ResultSet rs = pst.executeQuery();


        
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("formation");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("Description");
        header.createCell(1).setCellValue("volume Horaire");
        header.createCell(2).setCellValue("date formation");
        header.createCell(3).setCellValue("adresse");
        header.createCell(4).setCellValue("prix");
        header.createCell(5).setCellValue("promotion");

        int index = 1;
        while (rs.next()) {
            XSSFRow row = sheet.createRow(index);
            row.createCell(0).setCellValue(rs.getString("Description"));
            row.createCell(1).setCellValue(rs.getString("volume_horaire"));
            row.createCell(2).setCellValue(rs.getString("date_formation"));
            row.createCell(3).setCellValue(rs.getString("adresse"));
            row.createCell(4).setCellValue(rs.getString("prix"));
            row.createCell(5).setCellValue(rs.getString("promo"));

            index++;
        }

        FileOutputStream file = new FileOutputStream("Formations.xlsx");
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
    private void EntreprisePageBack(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("/javafxapplication5/Entreprise.fxml"));
        EntreprisePageBack.getScene().setRoot(root);
    }

    @FXML
    private void FormationPageback(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Interface/Formation.fxml"));
        FormationPageback.getScene().setRoot(root);
    }

    @FXML
    private void actualiser(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/Interface/Formation.fxml"));
        actualiser.getScene().setRoot(root); 
    }
    
    }

    
    
    


    

