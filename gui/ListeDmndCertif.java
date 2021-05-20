/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.mycompany.pi.entities.Certification;
import com.mycompany.pi.entities.DemandeCertif;
import com.mycompany.myapp.services.ServiceCertification;
import com.mycompany.myapp.services.ServiceDemandeCertif;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 *
 * @author ouaji
 */

public class ListeDmndCertif extends Form{

    private ServiceDemandeCertif sv;
    
    public ListeDmndCertif(Form previous) {
        
        sv = new ServiceDemandeCertif();
        
        
       
        
        
        
        
        
        
        setTitle("Liste demande Cert");
        setLayout(BoxLayout.y());
        
     
         Button screen = new Button("Screen");

        screen.addActionListener(e -> {
            
             Form form = Display.getInstance().getCurrent();
        if (form != null) {
            
            Image screenshot = Image.createImage(form.getWidth(), form.getHeight());
form.revalidate();
form.setVisible(true);
form.paintComponent(screenshot.getGraphics(), true);

String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot1.png";
try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
} catch(IOException err) {
    Log.e(err);
}
            System.out.println("done");
        }  
            
            });
        
        
           addAll(screen);
        
        
        List<DemandeCertif> Demandecertifs = sv.getAllTasks0();
        
        for (int i = 0; i < Demandecertifs.size(); i++) {
               add(addDemandeCertifItem(Demandecertifs.get(i)));
        }
        
        getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            previous.showBack();
        });
        
    }
        
    
    public Container addDemandeCertifItem(DemandeCertif demandeCertif){
        
        Container holder = new Container(BoxLayout.x());
        Container details = new Container(BoxLayout.y());
        
        
        
       

      //  Label lbid = new Label(certifs.getId());
      
        System.out.println("------------");
     //   Label lbtype = new Label("Type : "+demandeCertif.getType());
        Label lbnpreom = new Label("Prenom : "+demandeCertif.getPrenom_participant());
        Label lbid = new Label("ID : "+String.valueOf(demandeCertif.getId()) );
        Label lbnom = new Label("Nom : "+demandeCertif.getNom_participant());
        Label lbemail = new Label("Email : "+demandeCertif.getEmail());
        
        
        
        
              
       
        details.addAll(lbid,lbnom, lbnpreom,lbemail);
        
        
        
        
         
        
        
        
        
        
                        
              System.out.println("------------");

        
        holder.addAll( details);
        
    
        
        holder.setLeadComponent(lbid);
        
        return holder;
    }
}