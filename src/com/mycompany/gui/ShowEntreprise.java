/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.entities.Entreprise;
import com.mycompany.entities.OffreStage;
import com.mycompany.services.ServiceEntreprise;
import com.mycompany.services.ServiceOffreStage;
import static com.mycompany.utils.Statics.BASE_URL;

/**
 *
 * @author User
 */
public class ShowEntreprise extends Form {
     Component m;
    Form form;
      
    Form f;
    SpanLabel lb;
    Form f2;

       public ShowEntreprise(Form current,Entreprise E) {
    
               super("Newsfeed", BoxLayout.y());

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Offre stage " +E.getNom(), "Title")
                )
        ); 
        
        System.out.println(ServiceEntreprise.getInstance().getAllTasks());
        
                    
   Container cnt1 = new Container(BoxLayout.y());
   Container cnt2 = new Container(BoxLayout.x());
  
  Button back = new Button ("back");
  
   
       SpanLabel SLnom = new SpanLabel("ID : " + E.getId());
            SpanLabel SLprix = new SpanLabel("Nom :" + E.getNom());
            SpanLabel SLdesc = new SpanLabel("SiteWeb : " + E.getSiteWeb());
            SpanLabel SLdd = new SpanLabel("Email : " + E.getEmail());
            EncodedImage enc = EncodedImage.createFromImage(Image.createImage(1530,800), true);
		String url = BASE_URL+"/uploads/"+E.getImage();
                        ImageViewer img = new ImageViewer(URLImage.createToStorage(enc, url.substring(url.lastIndexOf("/")+1, url.length()), url));
                        img.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);
                        cnt1.add(SLnom);
            cnt1.add(SLprix);
            cnt1.add(SLdesc);
            cnt1.add(SLdd);
            cnt1.add(img);
                         cnt1.add(back);
                         back.addActionListener((evt) -> {
                new AfficherEntreprise(current).show();
            });
      
       
                        
        cnt2.add(cnt1);
      
    
       
    
   
    
    add(cnt2);
}
}
