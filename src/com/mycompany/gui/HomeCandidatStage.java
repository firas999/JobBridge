/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author User
 */
public class HomeCandidatStage extends Form {
    
     Form current;
private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    
    
    
    public HomeCandidatStage() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
       /* Button btnAddTask = new Button("Add CS");*/
        Button btnList1Tasks = new Button("Offre emploi");
        Button btnListeTasks = new Button("Offre stage");
        Button btnListTasks = new Button("Entreprise");
       // btnAddTask.addActionListener(e -> new AjouterCandidatStage(current).show());
        btnList1Tasks.addActionListener(e -> new AfficherOffreEmploi(current).show());
        btnListeTasks.addActionListener(e -> new AfficherOffreStage(current).show());
        btnListTasks.addActionListener(e -> new AfficherEntreprise(current).show());
        addAll(btnList1Tasks);
        addAll(btnListeTasks);
        addAll(btnListTasks);
    }
    
}
