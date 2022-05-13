/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import Post.gui.AllComment;
import Post.gui.AllFacture;

import Post.gui.AllPosts;
import Post.gui.rating;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.HomeForm;
import com.mycompany.myapp.gui.HomeFormR;
import gui.ListUsersForm;
import gui.Compte;
import utils.Session;
/**
 * Base class for the forms with common functionality
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {

    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }
    
    
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("Avata.png"), "PictureWhiteBackgrond"))
        ));
        
        tb.addMaterialCommandToSideMenu("Newsfeed", FontImage.MATERIAL_UPDATE, e -> new NewsfeedForm(res).show());
        tb.addMaterialCommandToSideMenu("Acceuil", FontImage.MATERIAL_SETTINGS, e -> new Compte(res,this).show());
     
        if(Session.getRole().equals("administrateur"))
            
        { tb.addMaterialCommandToSideMenu("Utilisateurs", FontImage.MATERIAL_SETTINGS, e -> new ListUsersForm(this).show());
        tb.addMaterialCommandToSideMenu("Catégories", FontImage.MATERIAL_CATEGORY, e -> new CategoryAdminForm(res).show());
                tb.addMaterialCommandToSideMenu("Produits Admin", FontImage.MATERIAL_ADMIN_PANEL_SETTINGS, e -> new ProduitAdminForm(res).show());
                        tb.addMaterialCommandToSideMenu("Gestion livraison", FontImage.MATERIAL_CAR_RENTAL, e -> new HomeForm(res,this).show());

        }
         if(Session.getRole().equals("client"))
         { tb.addMaterialCommandToSideMenu("Produits Client", FontImage.MATERIAL_GAMES, e -> new showproducts(res).show());

        tb.addMaterialCommandToSideMenu("Gestion Post", FontImage.MATERIAL_CHAT, e -> new AllPosts(res).show());
        tb.addMaterialCommandToSideMenu(  "Commentaires", FontImage.MATERIAL_COMMENT, e -> new AllComment(res).show());
                tb.addMaterialCommandToSideMenu(  "Panier", FontImage.MATERIAL_SHOP, e -> new MonPanierForm(res).show());
             tb.addMaterialCommandToSideMenu("Commandes", FontImage.MATERIAL_SHOPPING_CART, e -> new MesCommandesForm(res).show());
                          tb.addMaterialCommandToSideMenu("Reclamation", FontImage.MATERIAL_WARNING, e -> new HomeFormR(res,this).show());


         }        
        if(Session.getRole().equals("fournisseur")){ tb.addMaterialCommandToSideMenu("Gestion facture", FontImage.MATERIAL_RECEIPT, e -> new AllFacture(res).show());
}
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new WalkthruForm(res).show());


    }

    Image getIcon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void actionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
