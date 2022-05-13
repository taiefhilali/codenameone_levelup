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
import com.codename1.ui.ComboBox;
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
import Models.Categorie;
import Models.Produit;
import Models.User;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class modifierProduitForm extends BaseForm {
    ProduitAdminForm paf;
    public modifierProduitForm(Resources res) {
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
        addTab(swipe, res.getImage("pika.png"), spacer1, "", "", "Catégorie");

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
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        Container c1 = new Container(BoxLayout.y());
        ArrayList<Categorie> cc = ServiceCategorie.getInstance().getCategories();

        TextField nom = new TextField(paf.p.getNom(), "Entrer le nom du produit");
        TextField reference = new TextField(paf.p.getReference(), "Entrer le reference");
        TextField prix = new TextField(String.valueOf(paf.p.getPrix()), "Entrer le prix du produit");
        TextField description = new TextField(paf.p.getDescription(), "Entrer le description du produit");
        TextField promotion = new TextField(String.valueOf(paf.p.getPromotion()), "Entrer le promotion du produit");
        ComboBox comboCat = new ComboBox();
        for (Categorie c : cc) {
            // Catégorie ID
            String idSubstring = c.toString().substring(25, c.toString().indexOf(",") - 2);
            // Nom Catégorie
            String nomSubstring = c.toString().substring(c.toString().indexOf(",") + 16, c.toString().indexOf("}"));
            comboCat.addItem(idSubstring + ": " + nomSubstring);

        }

        c1.add(nom);
        c1.add(reference);
        c1.add(prix);
        c1.add(description);
        c1.add(comboCat);

        c1.add(promotion);
        add(LayeredLayout.encloseIn(c1));
//        TextField nomCat = new TextField(caf.nomCategorie.getNom_categorie(), "Entrer le nom de la catégorie");
//        add(LayeredLayout.encloseIn(nomCat));
        Button btnSave = new Button("Sauvegarder");
        add(LayeredLayout.encloseIn(btnSave));

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (nom.getText().equals("") || reference.getText().equals("") || prix.getText().equals("") || description.getText().equals("") || promotion.getText().equals("")) {
                    Dialog.show("Erreur", "Veuillez remplir touts les champs ", "Annuler", "OK");
                } else {
                    
                    Produit p = new Produit();
                    p.setId_produit(paf.p.getId_produit());
                    User u1 = new User(1);
                    p.setImage("prod.jpg");
                    p.setUser(u1);
                    p.setNom(nom.getText());
                    p.setReference(reference.getText());
                    p.setPrix(Float.parseFloat(prix.getText()));
                    p.setDescription(description.getText());
                    Categorie c = new Categorie(Float.parseFloat(comboCat.getSelectedItem().toString().substring(0, comboCat.getSelectedItem().toString().indexOf(":"))));
//                System.out.println(c);
                    p.setCategorie(c);
                    p.setPromotion(Float.parseFloat(promotion.getText()));

                    if (ServiceProduits.getInstance().ModifierProduit(p)) {

                        new ProduitAdminForm(res).show();
                        Dialog.show("Confirmation de modifications", "Etês vous sure de voiloir modifier ce probléme?...", "OUI", "NON");

                    }

                }
            }
        });

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
