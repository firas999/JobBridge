/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.user;
import Services.serviceuser;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ModifierEmplyerController implements Initializable {

    @FXML
    private TextField textfieldnom;
    @FXML
    private TextField textfieldprenom;
    @FXML
    private TextField textfieldmotdepasse;
    @FXML
    private TextField textfieldtype;
    @FXML
    private TextField textfielddiplome;
    @FXML
    private Button btn_modifier;
    @FXML
    private TextField textfieldentreprise;
    @FXML
    private TextField textfieldtypedecontrat;
    @FXML
    private TextField textfieldposteoccupe;
    @FXML
    private DatePicker textfielddate;
    @FXML
    private TextField textfieldcompetence;
    String imgUrl  ="";
    private FileChooser uploadPic;
    private File picPath;
    @FXML
    private ImageView imageToPost;
    @FXML
    private Button addImage;
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 user u = new user();
    @FXML
    private void modifier(MouseEvent event) {
        serviceuser cs = new serviceuser(); 
    	if (!textfieldnom.getText().equals("") && !textfieldprenom.getText().equals("")
				&& !textfieldmotdepasse.getText().equals("") && !textfieldtype.getText().equals("") 
				&& !textfielddiplome.getText().equals("") && !textfieldentreprise.getText().equals("") &&
                        ! textfieldtypedecontrat.getText().equals("") && !textfieldposteoccupe.getText().equals("")
                       && !textfieldcompetence.getText().equals("") ) {
          
           
          String str="[\"ROLE_EMPLOYER\"]";
          String image =imgUrl;
				cs.modifier(new user(u.getId(), textfieldnom.getText(),textfieldprenom.getText(),
						 textfieldmotdepasse.getText(), textfieldtype.getText(),"employer",textfielddiplome.getText(),textfieldentreprise.getText(),textfieldtypedecontrat.getText(),textfieldposteoccupe.getText(),Date.valueOf(textfielddate.getValue()),textfieldcompetence.getText(),null, 
						 null,0,0,image, str, 0
                                     
				));
                            Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  User Modifié")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(1));
               n.darkStyle();
               n.show();
          
			   FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/afficherEmployer.fxml"));

        try {
            Parent root = loader.load();
            btn_modifier.getScene().setRoot(root);
        } catch (IOException ex) {

    }}}
 public void setUser(user u) {
		this.u = u;
	}
     private AnchorPane ap;

    public void updateField() throws IOException {
		textfieldnom.setText(u.getNom());
		textfieldprenom.setText(u.getPrenom());
                textfieldmotdepasse.setText(u.getMotdepasse());
                textfieldtype.setText(u.getType());
                textfielddiplome.setText(u.getDiplome());
                textfieldentreprise.setText(u.getEntreprise());
                textfieldtypedecontrat.setText(u.getTypedecontrat());
                textfieldposteoccupe.setText(u.getPosteoccupe());
                 Date d = u.getDate();
                 textfielddate.setValue(d.toLocalDate()); 
                
                textfieldcompetence.setText(u.getCompetence());
                Image image ;
                image = new Image(u.getImg());
                 this.imageToPost.setImage(image);
                
               AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/afficherEmployer.fxml"));
              
               
              
                
		
	}
     @FXML
    private void backToAffichage(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/afficherEmployer.fxml"));

        try {
            Parent root = loader.load();
            btn_back.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }
     @FXML
    private void addImage(ActionEvent event) {
          Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        uploadPic = new FileChooser();
        uploadPic.setTitle("Select the image you want to add");
        picPath = uploadPic.showOpenDialog(stage);
        System.out.println(picPath.toString());
        try {
            imgUrl = picPath.toURI().toURL().toExternalForm();

            BufferedImage buffImage = ImageIO.read(picPath);
            Image up = SwingFXUtils.toFXImage(buffImage, null); 
           imageToPost.setImage(up);
        } catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }
    
   
}
