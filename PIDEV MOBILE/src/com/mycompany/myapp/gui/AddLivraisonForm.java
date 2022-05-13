/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

/**
 *
 * @author User
 */
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.services.ServiceLivraison;

/**
 *
 * @author bhk
 */
public class AddLivraisonForm extends Form{

    public AddLivraisonForm(Form previous) {
        setTitle("Add a new livraison");
        setLayout(BoxLayout.y());
        
        TextField tfDate = new TextField("","Date");
        TextField tfEtatlivraison= new TextField("", "Etatlivraison: En cours-livrée-Confirmée");
        Button btnValider = new Button("Ajouter livraison");
        Button btnMap = new Button ("Map");
        btnMap.addPointerPressedListener(l -> {
           
            new MapForm();
           
        });
        
        btnValider.setPreferredSize(new Dimension(200,140));
        btnValider.getStyle().setBgTransparency(255);
        btnValider.getStyle().setBgColor(0xcfb9);
        btnValider.getStyle().setFgColor(0xffffff);
      
        btnMap.setPreferredSize(new Dimension(200,140));
        btnMap.getStyle().setBgTransparency(255);
        btnMap.getStyle().setBgColor(0xcfb9);
        btnMap.getStyle().setFgColor(0xffffff);
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDate.getText().length()==0)||(tfEtatlivraison.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Livraison l = new Livraison(tfDate.getText().toString(),tfEtatlivraison.getText());
                        if( ServiceLivraison.getInstance().addLivraison(l))
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
        
        addAll(tfDate,tfEtatlivraison,btnValider,btnMap);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}

