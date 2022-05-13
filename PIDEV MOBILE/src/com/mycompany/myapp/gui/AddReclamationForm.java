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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;

/**
 *
 * @author User
 */
public class AddReclamationForm extends Form{

    public AddReclamationForm(Form previous) {
        setTitle("Ajouter Reclamation");
        setLayout(BoxLayout.y());
        
        TextField tfDescription = new TextField("","Description");
        TextField tfWarn= new TextField("", "WARN: 0-1");
        Button btnValider = new Button("Ajouter Reclamation");
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
                if ((tfDescription.getText().length()==0)||(tfWarn.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Reclamation r= new Reclamation(tfDescription.getText().toString(),Integer.valueOf(tfWarn.getText()));
                        if( ServiceReclamation.getInstance().addReclamation(r))
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
        
        addAll(tfDescription,tfWarn,btnValider,btnMap);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
