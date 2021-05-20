/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.entities.OffreStage;
import com.mycompany.services.ServiceOffreStage;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class AfficherOffreStage extends Form {

    Form current;

    public AfficherOffreStage(Form previous) {

        super("Newsfeed", BoxLayout.y());

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Offre stage ", "Title")
                )
        );

        ArrayList<OffreStage> OffreStages = new ArrayList();
        OffreStages = ServiceOffreStage.getInstance().getAllTasks();
        for (OffreStage OS : OffreStages) {

            Container cnt1 = new Container(BoxLayout.y());
            Container cnt2 = new Container(BoxLayout.x());

            SpanLabel SLnom = new SpanLabel("ID : " + OS.getId());
            SpanLabel SLprix = new SpanLabel("Type :" + OS.getType_stage());
            SpanLabel SLdesc = new SpanLabel("Exigence : " + OS.getExigence());
            SpanLabel SLdd = new SpanLabel("Durée : " + OS.getDuree());

            Button rent = new Button("Voir plus");
            cnt1.add(SLnom);
            cnt1.add(SLprix);
            cnt1.add(SLdesc);
            cnt1.add(SLdd);

             cnt1.add(rent);
                       rent.addActionListener((evt) -> {
                new ShowOffreStage(current, OS).show();
            });
            cnt2.add(cnt1);

            add(cnt2);
        }
           Button btnListeTasks = new Button("Statistique offre stage");
        btnListeTasks.addActionListener(e -> new StatistiqueOffreStage(current).show());
        addAll(btnListeTasks);
     
    }
 
    //add();
    /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceOffreStage.getInstance().getAllTasks().toString());
        add(sp);*/
}
