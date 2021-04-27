/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.ControleSaisie;
import Models.user;
import Services.serviceuser;
import Utils.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class LoginController implements Initializable {

    @FXML
    private Button login;
    @FXML
    private TextField nom;
    @FXML
    private TextField motdepasse;
    @FXML
    private AnchorPane aaa;
    @FXML
    private Button forgetpass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODOmm
    }    
 
    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
//         serviceuser sU = new serviceuser ();
//		user u = sU.login(nom.getText(), motdepasse.getText());  
//		if (u.getId() > -1 && u.getEtat()==0) {
//                        sU.userInfos = u; 
//                          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AfficherCandidat.fxml"));
//
//        try {
//            Parent root = loader.load();
//            login.getScene().setRoot(root);
//        } catch (IOException ex) {
//           
//    }
//    
////			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/affichage_candidatFront.fxml"));               
////			Parent parent = loader.load();  
////			aaa.getChildren().setAll(parent);   
//
//           AffichercCandidatController controller = (AffichercCandidatController) loader.getController();
//            controller.setUser(u); 
//			controller.refresh();
//		}  
//                else if ( u.getEtat()==1) {
//                    Notifications n = Notifications.create()
//                              .title("FAIL")
//                              .text("  you are blocked !!!! ")
//                              .position(Pos.TOP_CENTER)
//                              .hideAfter(Duration.seconds(1));
//               n.darkStyle();
//               n.show();
//
//                    
//                }
//		else {
//			Notifications n = Notifications.create()
//                              .title("SUCCESS")
//                              .text("  Incorrect Email or Password! ")
//                              .position(Pos.TOP_CENTER)
//                              .hideAfter(Duration.seconds(1));
//               n.darkStyle();
//               n.show();
//		}
serviceuser ms = new serviceuser ();
          if(verifchampsMembre()==true)
        {
        String nom1 = nom.getText();
       // String pwd = DigestUtils.shaHex(password.getText());//crypt
        UserSession.setInstance(nom1); 
        ms.loginMembre(nom1,motdepasse.getText());
        if(ms.loginMembre(nom1,motdepasse.getText())==1)
        {
            TrayNotification tray = null;
             user u = UserSession.getInstance().getLoggedUser();
             if(u.getEtat()==1){
                  tray = new TrayNotification("FAIL", "you are blocked !!!"+ UserSession.getInstance().getNom(), NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
                 
             }else{
            tray = new TrayNotification("welcom back", "Nice to see you  "+ UserSession.getInstance().getNom(), NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(5));
            Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
       	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Acceuil.fxml"));               
			Parent parent = loader.load();  
			aaa.getChildren().setAll(parent);   
             }
        }
        else
        {
            System.out.println(ms.loginMembre(nom1,motdepasse.getText()));
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "Email ou password incorrect  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
        }
        }
    }
    
    
      ControleSaisie css =new ControleSaisie();
    private Boolean verifchampsMembre()
    {
        if(nom.getText().isEmpty() || motdepasse.getText().isEmpty())
        {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "nom ou password incorrect  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
        
       
        
        
        return true;
        
    }

    @FXML
    private void forgetpassword(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/forgetPassword.fxml"));
          try {
            Parent root = loader.load();
            forgetpass.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }
    
    }
    

