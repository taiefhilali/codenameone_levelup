/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import Models.Categorie;
//import Services.ServiceCategorie;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class CategoryAdminForm extends BaseForm {

    NewsfeedForm d;
    static Categorie nomCategorie;

    public CategoryAdminForm(Resources res) {
        super("LevelUp", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("LevelUp");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("categories.jpg"), spacer1, "", "", "Gestion des catégories");

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

        // suppression catégorie
        ArrayList<Categorie> cc = ServiceCategorie.getInstance().getCategories();
        Button Ajout = new Button("Ajouter une Catégorie");
        add(LayeredLayout.encloseIn(Ajout));

        for (Categorie c : cc) {
            Container c1 = new Container(BoxLayout.y());
            SpanLabel NomCategorie = new SpanLabel("Nom Catégorie : " + c.getNom_categorie(), "largeLabel");

            // Déclaration des bouttons cruds
            Button Modif = new Button("Modifier catégorie");
            Button Supp = new Button("Supprimer catégorie");

            NomCategorie.getAllStyles().setFgColor(0x509c88);

            c1.add(NomCategorie);
            c1.add(Modif);
            c1.add(Supp);

            add(LayeredLayout.encloseIn(c1));
            // Bouton suppression
            Supp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Dialog.show("Alerte de suppression!", "Etes-vous sure de vouloir supprimer cette catégorie?", "Oui", "Non");
                  //  boolean state = 
                  ServiceCategorie.getInstance().supprimerCategorie((int) c.getId_categorie());
                  
                    new CategoryAdminForm(res).show();

                }
            });
            Modif.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    nomCategorie = c;
                    new AddCategorieForm(res).show();
                }
            });
            Ajout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    new ajouterCategorieForm(res).show();

                }
            }
            );
        }

    }

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
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

        // affichage cat
    }

}
