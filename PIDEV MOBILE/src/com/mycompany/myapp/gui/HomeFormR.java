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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author bhk
 */
public class HomeFormR extends Form{
Form current;
private Resources theme = UIManager.initFirstTheme("/theme");
    public HomeFormR(Resources res,Form form) {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
         Button lbimg = new Button(theme.getImage("rec2.png").scaled(1100, 1100));
        Button btnAddReclamation = new Button("Add Reclamation");
        
        btnAddReclamation.setPreferredSize(new Dimension(200,140));
        btnAddReclamation.getStyle().setBgTransparency(255);
        btnAddReclamation.getStyle().setBgColor(0xcfb9);
        btnAddReclamation.getStyle().setFgColor(0xffffff);
      
        
        Button btnListReclamations = new Button("List Reclamations");
        
        btnListReclamations.setPreferredSize(new Dimension(200,140));
        btnListReclamations.getStyle().setBgTransparency(255);
        btnListReclamations.getStyle().setBgColor(0xcfb9);
        btnListReclamations.getStyle().setFgColor(0xffffff);

        
        btnAddReclamation.addActionListener(e-> new AddReclamationForm(current).show());
        btnListReclamations.addActionListener(e-> new ListReclamationsForm(current).show());
        addAll(lbimg,btnAddReclamation,btnListReclamations);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> form.showBack());
    }
    
    
}