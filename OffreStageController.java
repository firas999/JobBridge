/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import static com.github.plushaze.traynotification.notification.Notifications.SUCCESS;
import com.github.plushaze.traynotification.notification.TrayNotification;
import models.Entreprise;
import models.OffreStage;
import services.ServiceEntreprise;
import services.serviceOffreStage;
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
//import com.itextpdf.text.BaseColor;
import com.lowagie.text.pdf.PdfWriter;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static org.bouncycastle.asn1.x500.style.RFC4519Style.title;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author User
 */
public class OffreStageController implements Initializable {

    @FXML
    private TableView<OffreStage> table_offrestage;
    @FXML
    private TableColumn<OffreStage, Integer> id_offrestage;
    @FXML
    private TableColumn<OffreStage, Integer> entreprise_offrestage;
    @FXML
    private TableColumn<OffreStage, String> type_offrestage;
    @FXML
    private TableColumn<OffreStage, String> duree_offrestage;
    @FXML
    private TableColumn<OffreStage, String> exigence_offrestage;
    serviceOffreStage ps = new serviceOffreStage();
    List<OffreStage> lr = ps.readAll();
    ObservableList<OffreStage> list = FXCollections.observableArrayList(lr);
    ObservableList<String> typestage1 = FXCollections.observableArrayList("PFE", "Technicien", "Ouvrier");
    ServiceEntreprise SE = new ServiceEntreprise();
    List<Entreprise> liste = SE.afficherIdNom();
    ObservableList<Entreprise> option = FXCollections.observableArrayList(SE.afficherIdNom());
    @FXML
    private TextField exigencestage;
    @FXML
    private Button ajouterstage;
    @FXML
    private ComboBox typestage;
    @FXML
    private ComboBox id_entreprise_id;
    @FXML
    private Button modifierstage;
    @FXML
    private Button supprimerstage;
    @FXML
    private TextField idStage;
    private Button candidature_stage;
    @FXML
    private Button Excel;
    @FXML
    private Button pdf;
    @FXML
    private Button statistique;
    @FXML
    private ComboBox trioffrestage;
    ObservableList<String> trioffrestage1 = FXCollections.observableArrayList("id d'entreprise", "type", "exigence");

    @FXML
    private Button tri;
    String[] exigence = {"java", "sql", "symfony", "C", "C++", "javascript"};
    @FXML
    private TextField rechercheOffreStage;
    @FXML
    private Button offrestage;
    @FXML
    private Button candidatstage;
    @FXML
    private ComboBox years;
    ObservableList<String> years1 = FXCollections.observableArrayList("0", "1", "2", "3");
    @FXML
    private ComboBox months;
    ObservableList<String> months1 = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
    @FXML
    private ComboBox days;
    ObservableList<String> days1 = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
    @FXML
    private Button offreemploi;
    @FXML
    private Button candidatemploi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typestage.setItems(typestage1);
        id_entreprise_id.setItems(option);
        trioffrestage.setItems(trioffrestage1);
        years.setItems(years1);
        months.setItems(months1);
        days.setItems(days1);
        idStage.setDisable(true);
        id_offrestage.setCellValueFactory(new PropertyValueFactory<>("id"));
        entreprise_offrestage.setCellValueFactory(new PropertyValueFactory<>("nomentreprise"));
        type_offrestage.setCellValueFactory(new PropertyValueFactory<>("type_stage"));
        duree_offrestage.setCellValueFactory(new PropertyValueFactory<>("duree"));
        exigence_offrestage.setCellValueFactory(new PropertyValueFactory<>("exigence"));
        FilteredList<OffreStage> filteredData = new FilteredList<>(list, b -> true);
        rechercheOffreStage.textProperty().addListener((Observable, oldValue, newValue) -> {
            filteredData.setPredicate(OffreStage -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (OffreStage.getType_stage().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (OffreStage.getExigence().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });
        SortedList<OffreStage> sorteddata = new SortedList<>(filteredData);
        sorteddata.comparatorProperty().bind(table_offrestage.comparatorProperty());
        table_offrestage.setItems(sorteddata);
        TextFields.bindAutoCompletion(exigencestage, exigence);
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {

        if (id_entreprise_id.getSelectionModel().isEmpty() || typestage.getSelectionModel().isEmpty() || years.getSelectionModel().isEmpty() || months.getSelectionModel().isEmpty() || days.getSelectionModel().isEmpty() || exigencestage.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Il faut remplir toutes les informations !");
        } else if (!(Pattern.matches("[a-z ,  A-Z]*", exigencestage.getText()))) {
            JOptionPane.showMessageDialog(null, "l'exigence doit etre un texte !");
        } else {
            String test = "+P" + years.getSelectionModel().getSelectedItem().toString() + "Y" + months.getSelectionModel().getSelectedItem().toString() + "M" + days.getSelectionModel().getSelectedItem().toString() + "DT00H00M00S";
            int i;
            i = Integer.parseInt(id_entreprise_id.getSelectionModel().getSelectedItem().toString().substring(0, id_entreprise_id.getSelectionModel().getSelectedItem().toString().indexOf(":")));
            System.out.println(i);

            OffreStage p = new OffreStage(i, typestage.getSelectionModel().getSelectedItem().toString(), test, exigencestage.getText());

            serviceOffreStage ps = new serviceOffreStage();

            ps.addOfferStage(p);
            ajouterstage.getScene();
            TrayNotification tray = new TrayNotification("Offre de stage", "Offre de stage ajouter", SUCCESS);
            tray.showAndDismiss(Duration.seconds(3));
            Parent root = FXMLLoader.load(getClass().getResource("OffreStage.fxml"));
            ajouterstage.getScene().setRoot(root);
        }
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        int i = Integer.parseInt(idStage.getText());
        String test = "+P" + years.getSelectionModel().getSelectedItem().toString() + "Y" + months.getSelectionModel().getSelectedItem().toString() + "M" + days.getSelectionModel().getSelectedItem().toString() + "DT00H00M00S";

        OffreStage p = new OffreStage(typestage.getSelectionModel().getSelectedItem().toString(), test, exigencestage.getText(), i);
        serviceOffreStage ps = new serviceOffreStage();

        ps.modifier(p);
        TrayNotification tray = new TrayNotification("Offre de stage", "Offre de stage modifier", SUCCESS);
        tray.showAndDismiss(Duration.seconds(3));
        Parent root = FXMLLoader.load(getClass().getResource("OffreStage.fxml"));
        modifierstage.getScene().setRoot(root);

    }

    @FXML
    private void supprimer(ActionEvent event) throws IOException {
        int id = Integer.parseInt(idStage.getText());
        OffreStage E = new OffreStage(id);

        serviceOffreStage ps = new serviceOffreStage();
        ps.supprimer(id);
        TrayNotification tray = new TrayNotification("Offre de stage", "Offre de stage supprimer", SUCCESS);
        tray.showAndDismiss(Duration.seconds(3));
        Parent root = FXMLLoader.load(getClass().getResource("OffreStage.fxml"));
        supprimerstage.getScene().setRoot(root);

    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        OffreStage OffreStage = table_offrestage.getSelectionModel().getSelectedItem();
        idStage.setText(OffreStage.getId().toString());
        //typestage.setItems(list);
        //dureestage.setText(OffreStage.getDuree());
        exigencestage.setText(OffreStage.getExigence());
        System.out.println("id=" + OffreStage.getId());

    }

    private void candidature_stage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CandidatureStage.fxml"));
        candidature_stage.getScene().setRoot(root);
    }

    @FXML
    private void Excel(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
        serviceOffreStage Excel = new serviceOffreStage();
        Connection cnx = DataSource.getInstance().getCnx();
        String query = "Select * from offre_stage";
        PreparedStatement pst = cnx.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Offres de stage");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Entreprise");
        header.createCell(2).setCellValue("Type stage");
        header.createCell(3).setCellValue("Durée");
        header.createCell(4).setCellValue("Exigence");

        int index = 1;
        while (rs.next()) {
            XSSFRow row = sheet.createRow(index);
            row.createCell(0).setCellValue(rs.getString("id"));
            row.createCell(1).setCellValue(Excel.getNomEntrepriseFromID(rs.getInt("id_entreprise_id")));
            row.createCell(2).setCellValue(rs.getString("type_stage"));
            row.createCell(3).setCellValue(Excel.convertDate(rs.getString("duree")));
            row.createCell(4).setCellValue(rs.getString("exigence"));

            index++;
        }

        FileOutputStream file = new FileOutputStream("OffreStage.xlsx");
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
    private void pdf(ActionEvent event) throws ClassNotFoundException, ClassNotFoundException, SQLException, IOException, URISyntaxException, DocumentException {
      serviceOffreStage pdf = new serviceOffreStage();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobbridge", "root", "");
            PreparedStatement pt = con.prepareStatement("select * from offre_stage");
            ResultSet rs = pt.executeQuery();

            /* Step-2: Initialize PDF documents - logical objects */
            Document my_pdf_report = new Document() {
            };

            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("Offres de stage.pdf"));

            my_pdf_report.open();
//                       my_pdf_report.add(new Paragraph(new Date().toString()));
//                            Image img = Image.getInstance("c:/6.png");
//                            my_pdf_report.add(img);
            //   my_pdf_report.add(new Paragraph("ACTIVITES"));
            my_pdf_report.addCreationDate();

            //we have four columns in our table
            PdfPTable my_report_table = new PdfPTable(4);

            //create a cell object
            PdfPCell table_cell;

            table_cell = new PdfPCell(new Phrase(" Entreprise"));
            //  table_cell.setBackgroundColor(BaseColor.WHITE);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Type stage"));
            // table_cell.setBackgroundColor(BaseColor.WHITE);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Durée"));
            // table_cell.setBackgroundColor(BaseColor.WHITE);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Exigence"));
            // table_cell.setBackgroundColor(BaseColor.WHITE);
            my_report_table.addCell(table_cell);

            while (rs.next()) {
             
                String nom = pdf.getNomEntrepriseFromID(rs.getInt("id_entreprise_id"));
                table_cell = new PdfPCell(new Phrase(nom));
                my_report_table.addCell(table_cell);
                String titre = rs.getString("type_stage");
                table_cell = new PdfPCell(new Phrase(titre));
                my_report_table.addCell(table_cell);
                String desc = pdf.convertDate(rs.getString("duree"));
                table_cell = new PdfPCell(new Phrase(desc));
                my_report_table.addCell(table_cell);
                String d = rs.getString("exigence");
                table_cell = new PdfPCell(new Phrase(d));
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
    private void statistique(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StatistiqueOffreStage.fxml"));
        statistique.getScene().setRoot(root);
    }

    private void triT() {

        serviceOffreStage a = new serviceOffreStage();
        List<OffreStage> b = a.trieT();
        ObservableList<OffreStage> listT = FXCollections.observableArrayList(b);

        typestage.setItems(typestage1);
        id_entreprise_id.setItems(option);
        id_offrestage.setCellValueFactory(new PropertyValueFactory<>("id"));
        entreprise_offrestage.setCellValueFactory(new PropertyValueFactory<>("id_entreprise_id"));
        type_offrestage.setCellValueFactory(new PropertyValueFactory<>("type_stage"));
        duree_offrestage.setCellValueFactory(new PropertyValueFactory<>("duree"));
        exigence_offrestage.setCellValueFactory(new PropertyValueFactory<>("exigence"));
        FilteredList<OffreStage> filteredData = new FilteredList<>(listT, t -> true);
        rechercheOffreStage.textProperty().addListener((Observable, oldValue, newValue) -> {
            filteredData.setPredicate(OffreStage -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (OffreStage.getType_stage().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (OffreStage.getExigence().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });
        SortedList<OffreStage> sorteddata = new SortedList<>(filteredData);
        sorteddata.comparatorProperty().bind(table_offrestage.comparatorProperty());
        table_offrestage.setItems(sorteddata);
    }

    private void triS() {

        serviceOffreStage a = new serviceOffreStage();
        List<OffreStage> b = a.trieS();
        ObservableList<OffreStage> listS = FXCollections.observableArrayList(b);

        typestage.setItems(typestage1);
        id_entreprise_id.setItems(option);
        id_offrestage.setCellValueFactory(new PropertyValueFactory<>("id"));
        entreprise_offrestage.setCellValueFactory(new PropertyValueFactory<>("id_entreprise_id"));
        type_offrestage.setCellValueFactory(new PropertyValueFactory<>("type_stage"));
        duree_offrestage.setCellValueFactory(new PropertyValueFactory<>("duree"));
        exigence_offrestage.setCellValueFactory(new PropertyValueFactory<>("exigence"));
        FilteredList<OffreStage> filteredData = new FilteredList<>(listS, s -> true);
        rechercheOffreStage.textProperty().addListener((Observable, oldValue, newValue) -> {
            filteredData.setPredicate(OffreStage -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (OffreStage.getType_stage().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (OffreStage.getExigence().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });
        SortedList<OffreStage> sorteddata = new SortedList<>(filteredData);
        sorteddata.comparatorProperty().bind(table_offrestage.comparatorProperty());
        table_offrestage.setItems(sorteddata);
    }

    private void triE() {
        serviceOffreStage a = new serviceOffreStage();
        List<OffreStage> b = a.trieE();
        ObservableList<OffreStage> listE = FXCollections.observableArrayList(b);

        typestage.setItems(typestage1);
        id_entreprise_id.setItems(option);
        id_offrestage.setCellValueFactory(new PropertyValueFactory<>("id"));
        entreprise_offrestage.setCellValueFactory(new PropertyValueFactory<>("id_entreprise_id"));
        type_offrestage.setCellValueFactory(new PropertyValueFactory<>("type_stage"));
        duree_offrestage.setCellValueFactory(new PropertyValueFactory<>("duree"));
        exigence_offrestage.setCellValueFactory(new PropertyValueFactory<>("exigence"));
         FilteredList<OffreStage> filteredData = new FilteredList<>(listE, e -> true);
        rechercheOffreStage.textProperty().addListener((Observable, oldValue, newValue) -> {
            filteredData.setPredicate(OffreStage -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (OffreStage.getType_stage().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (OffreStage.getExigence().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });
        SortedList<OffreStage> sorteddata = new SortedList<>(filteredData);
        sorteddata.comparatorProperty().bind(table_offrestage.comparatorProperty());
        table_offrestage.setItems(sorteddata);
    }

    @FXML
    private void tri(ActionEvent event) {
        String s = trioffrestage.getSelectionModel().getSelectedItem().toString();
        System.out.println(s);
        if (s.equals("id d'entreprise")) {

            triS();

        } else if (s.equals("type")) {
            triT();
        } else {
            triE();
        }
    }

    @FXML
    private void rechercheOffreStage(ActionEvent event) {
    }

    @FXML
    private void offrestage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OffreStage.fxml"));
        offrestage.getScene().setRoot(root);
    }

    @FXML
    private void candidatstage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CandidatureStage.fxml"));
        offrestage.getScene().setRoot(root);
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
