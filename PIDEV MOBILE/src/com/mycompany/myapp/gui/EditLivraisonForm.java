package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.services.ServiceLivraison;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class EditLivraisonForm extends Form{
      public EditLivraisonForm(Form previous, Livraison l) {
    setTitle("Modifier livraison");
        setLayout(BoxLayout.y());
        
        TextField tfDate = new TextField(l.getDate(),"Date");
        TextField tfEtatlivraison= new TextField(l.getEtat_livraison(), "Etatlivraison: En cours-livrée-Confirmée");
        Button btnValider = new Button("Modifier livraison");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDate.getText().length()==0)||(tfEtatlivraison.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Livraison l = new Livraison(tfDate.getText().toString(),tfEtatlivraison.getText());
                        if( ServiceLivraison.getInstance().modifierLivraison(l))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfDate,tfEtatlivraison,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }

    
}
