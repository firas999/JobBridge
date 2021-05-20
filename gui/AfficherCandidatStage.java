/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.mycompany.myapp.services.ServiceCandidatStage;

/**
 *
 * @author User
 */
public class AfficherCandidatStage extends Form {
    public AfficherCandidatStage(Form previous) {
       setTitle("Afficher candidat stage");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceCandidatStage.getInstance().getAllTasks().toString());
        add(sp);
    }
    
}
