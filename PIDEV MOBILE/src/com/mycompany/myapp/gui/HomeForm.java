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
public class HomeForm extends Form{
Form current;
private Resources theme = UIManager.initFirstTheme("/theme");
    public HomeForm(Resources res,Form form) {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
          Button lbimg = new Button(theme.getImage("liv.png").scaled(800, 800));
        Button btnAddLivraison = new Button("Add Livraison");
         btnAddLivraison.setPreferredSize(new Dimension(200,140));
        btnAddLivraison.getStyle().setBgTransparency(255);
        btnAddLivraison.getStyle().setBgColor(0xcfb9);
        btnAddLivraison.getStyle().setFgColor(0xffffff);
     
        Button btnListLivraisons = new Button("List Livraisons");
        
        btnListLivraisons.setPreferredSize(new Dimension(200,140));
        btnListLivraisons.getStyle().setBgTransparency(255);
        btnListLivraisons.getStyle().setBgColor(0xcfb9);
        btnListLivraisons.getStyle().setFgColor(0xffffff);
       
        
        btnAddLivraison.addActionListener(e-> new AddLivraisonForm(current).show());
        btnListLivraisons.addActionListener(e-> new ListLivraisonsForm(current).show());
        addAll(lbimg,btnAddLivraison,btnListLivraisons);
        
                  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> form.showBack());

    }
    
    
}
