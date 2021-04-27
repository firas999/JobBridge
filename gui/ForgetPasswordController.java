/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.ControleSaisie;
import Services.serviceuser;
import Utils.DataSource;
import Utils.UserSession;
import com.teknikindustries.bulksms.SMS;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.springframework.security.crypto.bcrypt.BCrypt;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField code;
    @FXML
    private TextField newpassword;
    @FXML
    private Button btn_submit;
    @FXML
    private Button btn_verif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    
    serviceuser ms =new serviceuser();
    public static int generateRandomIntIntRange() {
        Random r = new Random();
        return r.nextInt((1000 - 50) + 1) + 50;
    }
    int x=generateRandomIntIntRange();
     ControleSaisie cs =new ControleSaisie();
    private Boolean verifchampsCin()
    { if(nom.getText().isEmpty())
    {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "verifier votre nom  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
        return false;
    }
 
        return true;
    }
    
    
    
    

    @FXML
    private void verifier_cin(ActionEvent event) throws SQLException {
           if(verifchampsCin()==true)
        {
            String nomm = nom.getText();
           
        
            if(ms.RecupPwd(nomm)==1){
            
//                pour afficher le code generateur dans notif
                String str = Integer.toString(x);
                System.out.println(str);

        TrayNotification tray = null;
        tray = new TrayNotification("bien", str, NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));
            
            
            UserSession.setInstance(nomm);
//            pour SMS
//            SMS
               SMS     sms =new SMS();
            String num_tel ="+216"+ UserSession.getInstance().getNumerotelephone();
            System.out.println(num_tel);
            sms.SendSMS("mohamedd55", "aRTBEN55991044", str, num_tel, "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
            System.out.println("test sms");
        
//        try{
//            String host ="smtp.gmail.com" ;
//            String user = "moez.benammar@esprit.tn";
//            String pass = "181JMT1153";
//            String to =UserSession.getInstance().get() ;
//            String from ="mehdi.dagdagui@esprit.tn" ;
//            String subject = "Code de verification ";
//            String messageText = "votre code de verification est : " +str;
//            boolean sessionDebug = false;
//
//            Properties props = System.getProperties();
//
//            props.put("mail.smtp.starttls.enable", "true");
//            props.put("mail.smtp.host", host);
//            props.put("mail.smtp.port", "587");
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.starttls.required", "true");
//
//            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//            Session mailSession = Session.getDefaultInstance(props, null);
//            mailSession.setDebug(sessionDebug);
//            Message msg = new MimeMessage(mailSession);
//            msg.setFrom(new InternetAddress(from));
//            InternetAddress[] address = {new InternetAddress(to)};
//            msg.setRecipients(Message.RecipientType.TO, address);
//            msg.setSubject(subject); msg.setSentDate(new java.util.Date());
//            msg.setText(messageText);
//
//           Transport transport=mailSession.getTransport("smtp");
//           transport.connect(host, user, pass);
//           transport.sendMessage(msg, msg.getAllRecipients());
//           transport.close();
//           System.out.println("message send successfully");
//        }catch(Exception ex)
//        {
//            System.out.println(ex.getMessage());
//        }
//        
//        
//        
//    
//        
       }
        else
        {
            
              
            TrayNotification tray = null;
        tray = new TrayNotification("Error", "nom introuvable  ", NotificationType.ERROR);
        tray.showAndDismiss(Duration.seconds(5));
        }
    }
    }
     @FXML   
     private void conf_pwd(ActionEvent event) throws SQLException {
         if(verifchamps()==true)
    {
        int code1=x;
        String codeF = code.getText();
        int cf=(Integer.parseInt(codeF));
        System.out.println(code1);
              if (cf == code1) 
              {
                   System.out.println("bien");
               //String nvmdp=tf_nv_pwd.getText();
                String nvmdp = BCrypt.hashpw(newpassword.getText(), BCrypt.gensalt(13));
            
               String requete="UPDATE user SET motdepasse='"+nvmdp+"' WHERE nom = '"+nom.getText()+"'";
              Connection cnx = DataSource.getInstance().getCnx();
                  PreparedStatement pst = 
                     cnx.prepareStatement(requete);
                  pst.executeUpdate();
              } 
    }         
    }
     private Boolean verifchamps()
    { if(code.getText().isEmpty() || newpassword.getText().isEmpty())
    {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "il faut remplire les champs   ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
        return false;
    }
    return true;
    }
    
    }
    

