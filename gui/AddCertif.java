/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.pi.entities.Certification;
import com.mycompany.myapp.services.ServiceCertification;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ouaji
 */

public class AddCertif extends Form {
    
    
    
     public AddCertif(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
         setTitle("Certif");
        setLayout(BoxLayout.y());
        
        TextField tfType = new TextField("","Type");
       // SimpleDateFormat formater = new SimpleDateFormat("DD-MM-yyyy");
           
        TextField tfPrix = new TextField("","Prix");
        TextField tfDescription = new TextField("","Description");
        TextField tfNom = new TextField("","Nom");
        TextField tfEntId = new TextField("","EntId");
          Picker tfDateNaissance = new Picker();
        Button btnValider = new Button("Add task");
       
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfType.getText().length()==0)||(tfDescription.getText().length()==0))
                     Dialog.show("Remplir les champs", "", "ok", null);
                else
                {
               
                        Certification c = new Certification(tfType.getText(),tfDateNaissance.getDate(),Integer.parseInt(tfPrix.getText()),tfDescription.getText(),tfNom.getText(),Integer.parseInt(tfEntId.getText()));
                        if( ServiceCertification.getInstance().addTask(c))
                         Dialog.show("Connexion Accept", "", "ok", null);
                        else
                          Dialog.show("Servor Error", "", "ok", null);
                    } 
                    
                }
                
                
            
        });
        
     
                   addAll(tfType,tfPrix,tfDescription,tfNom,tfEntId,tfDateNaissance,btnValider);

      //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
        //        , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
}
    
    
    

