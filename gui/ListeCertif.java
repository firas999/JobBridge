/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.mycompany.pi.entities.Certification;
import com.mycompany.myapp.services.ServiceCertification;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 *
 * @author ouaji
 */
/*
public class ListeCertif extends Form {
    
    public ListeCertif(Form previous) {
    
       setTitle("List Certif");
        
       SpanLabel sp = new SpanLabel();
        sp.setText(ServiceCertification.getInstance().getAllTasks().toString());
        add(sp);
    }
}
*/
//Certification m = new ServiceCertification().getAllTasks();
      
         /*    super("Certification",BoxLayout.y());
                 this.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
             for (Certification c : new ServiceCertification().getAllTasks()) {

            this.add(addItem_Certification(c));

        }
               this.revalidate();
            });
        });
      
      
      
      
        Label adresse_mail = new Label("Adresse_mail : "+c.getAdresse_mail());
       
       Label date_fin_contrat = new Label("Date_fin_contrat : "+c.getDate_fin_contrat());
      
      
      
    }
    
}

*/
public class ListeCertif extends Form{

    private ServiceCertification sv;
    
    public ListeCertif(Form previous) {
        
        sv = new ServiceCertification();
        
        
       
        
        
        
        
        
        
        setTitle("Liste de Cert");
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
        
        
        
        List<Certification> certifs = sv.getAllTasks();
        
        for (int i = 0; i < certifs.size(); i++) {
           add(addCertifItem(certifs.get(i)));
           
        }
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> {new HomeForm().show();});
    }
        
    
    public Container addCertifItem(Certification certification){
        
        Container holder = new Container(BoxLayout.x());
        Container details = new Container(BoxLayout.y());
        
        
        
       

      //  Label lbid = new Label(certifs.getId());
      
        System.out.println("------------");
        Label lbtype = new Label("Type : "+certification.getType());
        Label lbnom = new Label("Nom : "+certification.getNom());
        Label lbid = new Label("ID : "+String.valueOf(certification.getId()) );
        Label lbdes = new Label("Desceiption : "+certification.getDescription());
        Label lbprix = new Label("Prix : "+certification.getPrix());
        
        
         Button Delete =new Button("Supprimer");
       
            Delete.getAllStyles().setBgColor(0xF36B08);
            Delete.addActionListener((ActionEvent e) -> {
               Dialog alert = new Dialog("Attention");
                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cet événement?\nCette action est irréversible!");
                alert.add(message);
                Button ok = new Button("Confirmer");
                Button cancel = new Button(new Command("Annuler"));
                //User clicks on ok to delete account
                ok.addActionListener(new ActionListener() {
                  
                    public void actionPerformed(ActionEvent evt) {
                       sv.Delete0(certification.getId());
                     
                        alert.dispose();
                        refreshTheme();
                    }
                    
                }
                                  );

                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();
                
       
                Form previous;
                 previous = this;
                  new ListeCertif(previous).show();
            });
        
              addAll(Delete);
       
        details.addAll(lbid,lbtype, lbnom,lbdes,lbprix);
        
        
        
        
        
                        
              System.out.println("------------");

        
        holder.addAll( details);
        
    
        
        holder.setLeadComponent(lbid);
        
        return holder;
     
    }
    
    
}


