/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.entities.OffreEmploi;
import com.mycompany.myapp.services.ServiceOffreEmploi;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class AfficherOffreEmploi extends Form {

    Form current;

    public AfficherOffreEmploi(Form previous) {

        super("Newsfeed", BoxLayout.y());

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Offre emploi ", "Title")
                )
        );

        ArrayList<OffreEmploi> OffreEmplois = new ArrayList();
        OffreEmplois = ServiceOffreEmploi.getInstance().getAllTasks();
        for (OffreEmploi OS : OffreEmplois) {

            Container cnt1 = new Container(BoxLayout.y());
            Container cnt2 = new Container(BoxLayout.x());

            SpanLabel SLnom = new SpanLabel("ID : " + OS.getId());
            SpanLabel SLprix = new SpanLabel("Poste :" + OS.getPoste());
            SpanLabel SLdesc = new SpanLabel("Type : " + OS.getType());
            SpanLabel SLdd = new SpanLabel("Salaire : " + OS.getSalaire());

            Button rent = new Button("Voir plus");
            cnt1.add(SLnom);
            cnt1.add(SLprix);
            cnt1.add(SLdesc);
            cnt1.add(SLdd);

           cnt1.add(rent);
                       rent.addActionListener((evt) -> {
                new ShowOffreEmploi(current, OS).show();
            });
            cnt2.add(cnt1);

            add(cnt2);
        }
           Button btnListeTasks = new Button("Statistique offre emploi");
        btnListeTasks.addActionListener(e -> new StatistiqueOffreEmploi(current).show());
        addAll(btnListeTasks);
     
    }
 
    //add();
    /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceOffreStage.getInstance().getAllTasks().toString());
        add(sp);*/
}
