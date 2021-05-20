/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.netdev.mindspace.services.UserService;

/**
 *
 * @author hp
 */
public class SignUpForm extends BaseForm{
     public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
       
        TextField nom = new TextField("", "First Name", 20, TextField.ANY);
        TextField prenom = new TextField("", "Name", 20, TextField.ANY);
        TextField motdepasse = new TextField("", "Motdepasse", 20, TextField.PASSWORD);
        TextField confirmMotdepasse = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        TextField type = new TextField("", "type", 20, TextField.ANY);
        TextField secteur = new TextField("", "secteur", 20, TextField.ANY);
        TextField siteweb = new TextField("", "siteweb", 20, TextField.ANY);
        TextField tailleentreprise = new TextField("", "tailleentreprise", 20, TextField.ANY);
        TextField numerotelephone = new TextField("", "phone", 20, TextField.ANY);
        TextField img = new TextField("", "img", 20, TextField.ANY);
        
        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        motdepasse.setSingleLineTextArea(false);
        confirmMotdepasse.setSingleLineTextArea(false);
        type.setSingleLineTextArea(false);
        secteur.setSingleLineTextArea(false);
        siteweb.setSingleLineTextArea(false);
        tailleentreprise.setSingleLineTextArea(false);
        numerotelephone.setSingleLineTextArea(false);
        img.setSingleLineTextArea(false);
        
           
            
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                
               
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
                new FloatingHint(motdepasse),
                createLineSeparator(),
                new FloatingHint(confirmMotdepasse),
                createLineSeparator(),
                new FloatingHint(type),
                createLineSeparator(),
                new FloatingHint(secteur),
                createLineSeparator(),
                new FloatingHint(siteweb),
                createLineSeparator(),
                new FloatingHint(tailleentreprise),
                createLineSeparator(),
                new FloatingHint(numerotelephone),
                createLineSeparator(),
                new FloatingHint(img),
                createLineSeparator()
                
                
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener((e)-> {
            UserService.getInstance().signup(nom, prenom, motdepasse,type, secteur, siteweb, tailleentreprise,numerotelephone,img, res);
            Dialog.show("succes", "maintenant vous etes Inscri", "ok",null);
            new NewsfeedForm(res).show();
        });
        
    }
    
    
}
