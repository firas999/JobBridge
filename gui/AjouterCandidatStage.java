/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.CandidatStage;
import com.mycompany.myapp.services.ServiceCandidatStage;
import java.util.Date;

/**
 *
 * @author User
 */
public class AjouterCandidatStage extends Form {
    
    
    
     public AjouterCandidatStage(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
         setTitle("Ajouter candidat stage");
        setLayout(BoxLayout.y());
        
        TextField tf = new TextField("","Date");
        SimpleDateFormat formater = new SimpleDateFormat("DD-MM-yyyy");
           
      //  TextField tfPrix = new TextField("","Prix");
        TextField tfidentreprise_id = new TextField("","identreprise_id");
        
        TextField tfidstagiaire_id = new TextField("","idstagiaire_id");
          Picker tfdate_candidature = new Picker();
          TextField tfemail = new TextField("","email");
        Button btnValider = new Button("Add task");
       
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfemail.getText().length()==0)||(tfidstagiaire_id.getText().length()==0)||(tfidentreprise_id.getText().length()==0))
                     Dialog.show("Remplir les champs", "", "ok", null);
                else
                {
               
                     /*   CandidatStage c = new CandidatStage(Integer.parseInt(tfidentreprise_id.getText()),Integer.parseInt(tfidstagiaire_id.getText()), (Date) tfdate_candidature.getDate(),tfemail.getText());
                        if( ServiceCandidatStage.getInstance().addTask(c))
                          Dialog.show("Connexion Accept", "", "ok", null);
                        else
                           Dialog.show("Servor Error", "", "ok", null);*/
                    } 
                    
                }
                
                
            
        });
        
     
                   addAll(tfidentreprise_id,tfidstagiaire_id,tfdate_candidature,tfemail,btnValider);

      //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
        //        , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
}
