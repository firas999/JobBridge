/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.ServiceCertification;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public HomeForm() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choisissez une option"));
        Button btnAddTask = new Button("Liste des évènements");
        Button btnAddTask1 = new Button("Statistiques évènements");
         Button btnCert = new Button("Liste des Certifications");
         Button btnDmndCert = new Button("Liste Demandes Certifications");
          Button StatCertif = new Button("Statistique Certifications");
    Button btnList1Tasks = new Button("Offre emploi");
        Button btnListeTasks = new Button("Offre stage");
        Button btnListTasks = new Button("Entreprise");
       // btnAddTask.addActionListener(e -> new AjouterCandidatStage(current).show());
 
          
            

        btnAddTask.addActionListener(e -> new ListEvenement(current).show());
        btnAddTask1.addActionListener(e -> new Stat().createPieChartForm().show());
          btnCert.addActionListener(e -> new ListeCertif(current).show());
           btnDmndCert.addActionListener(e -> new ListeDmndCertif(current).show());
          StatCertif.addActionListener(e -> new StatCertif().createPieChartForm("Coachs", new ServiceCertification().getStat()));
       //  StatCertif.addActionListener(e -> new StatCertif().createPieChartForm().show());
            btnList1Tasks.addActionListener(e -> new AfficherOffreEmploi(current).show());
        btnListeTasks.addActionListener(e -> new AfficherOffreStage(current).show());
        btnListTasks.addActionListener(e -> new AfficherEntreprise(current).show());
        addAll(btnList1Tasks);
        addAll(btnListeTasks);
        addAll(btnListTasks);
       
       
       
       
       addAll(btnAddTask,btnAddTask1,btnCert,btnDmndCert,StatCertif);
        
        
        

    }

}
