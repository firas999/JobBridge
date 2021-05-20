/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.entities.OffreStage;
import com.mycompany.services.ServiceOffreStage;

/**
 *
 * @author User
 */
public class ShowOffreStage extends Form {
     Component m;
    Form form;
      
    Form f;
    SpanLabel lb;
    Form f2;

       public ShowOffreStage(Form current,OffreStage OS) {
    
               super("Newsfeed", BoxLayout.y());

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Offre stage " +OS.getId(), "Title")
                )
        ); 
        
        System.out.println(ServiceOffreStage.getInstance().getAllTasks());
        
                    
   Container cnt1 = new Container(BoxLayout.y());
   Container cnt2 = new Container(BoxLayout.x());
  
  Button back = new Button ("back");
  
   
        SpanLabel SLnom = new SpanLabel("ID : " + OS.getId());
            SpanLabel SLprix = new SpanLabel("Type :" + OS.getType_stage());
            SpanLabel SLdesc = new SpanLabel("Exigence : " + OS.getExigence());
            SpanLabel SLdd = new SpanLabel("Durée : " + OS.getDuree());
                         cnt1.add(SLnom);
            cnt1.add(SLprix);
            cnt1.add(SLdesc);
            cnt1.add(SLdd);
                         cnt1.add(back);
                         back.addActionListener((evt) -> {
                new AfficherOffreStage(current).show();
            });
      
       
                        
        cnt2.add(cnt1);
      
    
       
    
   
    
    add(cnt2);
}
 }
        
    

