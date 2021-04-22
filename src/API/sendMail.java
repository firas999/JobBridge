/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;



import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import javax.mail.internet.AddressException;

/**
 *
 * @author ouaji
 */
public class sendMail {
    

    public void sendMail1(String recepient) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
            //Enable authentication
        properties.put("mail.smtp.auth", "true");
            //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
            //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
            //Set smtp port
        properties.put("mail.smtp.port", "587");

            //Your gmail address
        String myAccountEmail = "ouajihjebali@gmail.com";
            //Your gmail password
        String password = "Nuttertools147";

            //Create a session with account credentials
        
            
                Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                    @Override
                        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                            return new javax.mail.PasswordAuthentication(myAccountEmail, password);
                        }
                    });
            
                    Message message = prepareMessage(session, myAccountEmail,recepient);
                    Transport.send(message);
                            System.out.println("message sent successful");
            
                            
                            
    }
    
    
private static Message prepareMessage(Session session, String myAccountEmail,String recepient)
{

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
             message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
             message.setSubject("Votre demande est enregistréé");
             message.setText("Votre demande est enregistréé");
             return message;
        } catch (Exception ex) {
            Logger.getLogger(sendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
return null;

}
    

}