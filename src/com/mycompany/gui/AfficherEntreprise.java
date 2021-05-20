/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
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
import java.util.ArrayList;
import com.codename1.components.ImageViewer;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;

import static com.mycompany.utils.Statics.BASE_URL;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class AfficherEntreprise extends Form {

    Form current;

    public AfficherEntreprise(Form previous) {

        super("Newsfeed", BoxLayout.y());

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Entreprise ", "Title")
                )
        );

        ArrayList<Entreprise> Entreprises = new ArrayList();
        Entreprises = ServiceEntreprise.getInstance().getAllTasks();
        for (Entreprise E : Entreprises) {

            Container cnt1 = new Container(BoxLayout.y());
            Container cnt2 = new Container(BoxLayout.x());

            SpanLabel SLnom = new SpanLabel("ID : " + E.getId());
            SpanLabel SLprix = new SpanLabel("Nom :" + E.getNom());
            SpanLabel SLdesc = new SpanLabel("SiteWeb : " + E.getSiteWeb());
            SpanLabel SLdd = new SpanLabel("Email : " + E.getEmail());
            EncodedImage enc = EncodedImage.createFromImage(Image.createImage(1530,800), true);
		String url = BASE_URL+"/uploads/"+E.getImage();
                        ImageViewer img = new ImageViewer(URLImage.createToStorage(enc, url.substring(url.lastIndexOf("/")+1, url.length()), url));
                        img.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);
            Button rent = new Button("Voir plus");
            cnt1.add(SLnom);
            cnt1.add(SLprix);
            cnt1.add(SLdesc);
            cnt1.add(SLdd);
            cnt1.add(img);
             cnt1.add(rent);
                         rent.addActionListener((evt) -> {
                new ShowEntreprise(current, E).show();
            });
            cnt2.add(cnt1);

            add(cnt2);
        }
         Button btnListeTasks = new Button("Statistique entreprise");
        btnListeTasks.addActionListener(e -> new StatistiqueEntreprise(current).show());
        addAll(btnListeTasks);
    }
    //add();
    /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceOffreStage.getInstance().getAllTasks().toString());
        add(sp);*/
    
}
