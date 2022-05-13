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
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import Models.Commande;
import Models.Commande_elem;
import Models.Produit;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class MesCommandesForm extends BaseForm{
    
   public static ArrayList<Commande_elem> ccc = new ArrayList<>();
    public static ArrayList<Produit> pr = new ArrayList<>();
    
        public MesCommandesForm (Resources res){
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
        addTab(swipe, res.getImage("mescommandes.jpg"), spacer1, "", "", "Mes Commandes");
                
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
        
        int i = 1;
        ArrayList<Commande> cc =  ServiceCommande.getInstance().getAllCommandes(1);
        for(Commande c : cc){
        Container c1 = new Container(BoxLayout.y());
        Label Num = new Label("Commande n° : " + i,"largeLabel");
        Label PrixProduits = new Label("Total des produits : " + c.getPrix_produits(),"largeLabel");
        Label PrixLivraison = new Label("Prix Livraison : "+ c.getPrix_livraison(),"largeLabel");
        Label PrixTotal = new Label("Prix Total :    " + c.getPrix_total(),"largeLabel");
        Label Mode = new Label("Mode de Paiement : " + c.getMode(),"largeLabel");
        Button Delete = new Button("Delete");
        Button Details = new Button("Details");
        c1.add(Num);
        c1.add(PrixProduits);
        c1.add(PrixLivraison);
        c1.add(PrixTotal);
        c1.add(Mode);
        c1.add(Delete);
        c1.add(Details);
        i++;
        add(LayeredLayout.encloseIn(c1));
        
        Details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               ccc =  ServiceCommande.getInstance().getCommandeDetails(c.getId_commande());
               pr = ServiceCommande.getInstance().getCommandeDetailsOff(c.getId_commande());
               new CommandeDetailsForm(res).show();
             }
        });
        
        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Dialog.show("Confirmation", "Voulez vous Supprimé cette Commande !!! "
                               , "ok","Annuler");
                
               boolean test = ServiceCommande.getInstance().deleteCommande(c.getId_commande());
               if(test == true){
                   Dialog.show("Succés", "Votre Commande est Supprimé avec succés !!! "
                               , "ok","");
               }else{
                   Dialog.show("Echec", "Echec de Suppression !!! "
                               , "ok","");
               }
               new MesCommandesForm(res).show();
             }
        });
        }
        
       
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
