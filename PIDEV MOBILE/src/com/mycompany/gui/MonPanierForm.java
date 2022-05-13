/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import Models.Panier_elem;
import Models.Produit;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author ASUS
 */
public class MonPanierForm extends BaseForm{
    public MonPanierForm (Resources res){
        super("LevelUp", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("LevelUp");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("MonPanier.jpg"), spacer1, "", "", "Mon Panier");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ArrayList<Produit> cc =  servicePanierElement.getInstance().getPanierElementsProducts(1);
        ArrayList<Panier_elem> pe =  servicePanierElement.getInstance().getPanierElements(1);
        int i = 0;
        float liv = 0;
        float produits = 0;
        float Total = 0;
        
        for(Panier_elem c : pe){
          Produit e = cc.get(i);
        Container c1 = new Container(BoxLayout.y());
        SpanLabel Nom = new SpanLabel("Nom de Produit : " + e.getNom(),"largeLabel");
        Label Prix = new Label("Prix  : " + e.getPrix(),"largeLabel");
        SpanLabel Description = new SpanLabel("Description : "+ e.getDescription(),"largeLabel");
        TextField quant = new TextField();
        
        quant.setText(String.valueOf(c.getQuantite()));
        produits += e.getPrix() * c.getQuantite();
        Button Delete = new Button("Delete");
        Button Update = new Button("Update");
        c1.add(Nom);
        c1.add(Prix);
        c1.add(Description);
        c1.add(quant);
        c1.add(Delete);
        c1.add(Update);
        i++;
        add(LayeredLayout.encloseIn(c1));
        add(createLineSeparator());
        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Dialog.show("Confirmation", "Voulez vous Supprimé ce produit de panier !!! "
                               , "ok","Annuler");
                
               boolean test = servicePanierElement.getInstance().deleteElementPanier((int)c.getId_elem());
               if(test == true){
                   Dialog.show("Succés", "Produit est Supprimé avec succés !!! "
                               , "ok","");
               }else{
                   Dialog.show("Echec", "Echec de Suppression !!! "
                               , "ok","");
               }
               new MonPanierForm(res).show();
             }
        });
        
        Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(quant.getText().equals("")){
                     Dialog.show("Echec", "Remplir la Quantite !!!! "
                               , "ok","Annuler");
                }else{
                    Dialog.show("Confirmation", "Voulez vous Modifier ce produit de panier !!! "
                               , "ok","Annuler");
                   boolean test = servicePanierElement.getInstance().modifierElement(Integer.parseInt(quant.getText()),(int)c.getId_elem());
                   if(test == true){
                        Dialog.show("Succés", " Modification de produit faite avec succés !!! "
                               , "ok","Annuler");
                   }else{
                        Dialog.show("Echec", "Echec de modification !!! "
                               , "ok","Annuler");
                   }
                   new MonPanierForm(res).show();
                
                }
             }
        });
        }
        if(produits >100){
            liv = 0;
            Total = produits;
        }else if((produits <= 100)&& (produits != 0)){
            liv = 20;
            Total = liv + produits;
        }
        float prod = produits;
        float Livr = liv;
        float Tot = Total;
        Container c2 = new Container(BoxLayout.y());
        Label produitsTot = new Label("Total des Produits  : " + produits + " DN","largeLabel");
        Label Livraison = new Label("Prix de Livraison  : " + liv + " DN" ,"largeLabel");
        Label Totale = new Label("Prix Total  : " + Total + " DN" ,"largeLabel");
        produitsTot.getAllStyles().setFgColor(0x509c88);
        Livraison.getAllStyles().setFgColor(0x509c88);
        Totale.getAllStyles().setFgColor(0x509c88);
        Button Valider = new Button("Valider Votre Commande");

        c2.add(produitsTot);
        c2.add(Livraison);
        c2.add(Totale);
        c2.add(Valider);
        add(LayeredLayout.encloseIn(c2));
         Valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Dialog.show("Confirmation", "Voulez vous Valider votre Commande !!! "
                               , "ok","Annuler");
           boolean testCmd = ServiceCommande.getInstance().AjouterCommande(1,prod,Livr,Tot);
            if(testCmd == true){
                        Dialog.show("Succés", " Commande passer avec succés !!! "
                               , "ok","Annuler");
                   }else{
                        Dialog.show("Echec", "Echec de Validation !!! "
                               , "ok","Annuler");
                   }
                   new NewsfeedForm(res).show();
             }
        });
    }
    
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
       

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
}
