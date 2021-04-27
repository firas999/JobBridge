/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class EmailController implements Initializable {

    @FXML
    private TextField from;
    @FXML
    private TextField to;
    @FXML
    private TextArea description;
    @FXML
    private Button btn_submit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submit(ActionEvent event) {   
          
      
//        
        try{
            String host ="smtp.gmail.com" ;
            String user = "mariem.azouz@esprit.tn";
            String pass = "hesoyam123";
            String to1 =to.getText() ;
            String from1 =from.getText();
            String subject = "you have an email for you ";
            String messageText = description.getText();
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null); 
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from1));
            InternetAddress[] address = {new InternetAddress(to1)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new java.util.Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
            TrayNotification tray = null;
        tray = new TrayNotification("message sent successfully","aaa" , NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AfficherCandidat.fxml"));

        try {
            Parent root = loader.load();
            btn_submit.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
}
