/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import Models.Categorie;
import Models.Produit;
import Models.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ProduitAdminForm extends BaseForm {

    ImageViewer imgR = null;
    static Produit p;

    public ProduitAdminForm(Resources res) {
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
        addTab(swipe, res.getImage("products.png"), spacer1, "", "", "Products");

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

        // ajout boutton ajout produit
        Button btnAjout = new Button("Ajouter Produit");

        add(LayeredLayout.encloseIn(btnAjout));
        btnAjout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ajouterProduitForm(res).show();

            }
        });

        ArrayList<Produit> cc = ServiceProduits.getInstance().getAllProducts();
        ArrayList<Categorie> ccc = ServiceProduits.getInstance().getAllCategories();
        ArrayList<User> usr = ServiceProduits.getInstance().getUserJoint();

        int i = 0;
        for (Produit c : cc) {

            Container c1 = new Container(BoxLayout.y());
            SpanLabel Nom = new SpanLabel("Nom du Produit : " + c.getNom(), "largeLabel");
            SpanLabel Cate = new SpanLabel("Catégorie : " + ccc.get(i).getNom_categorie(), "largeLabel");
            Label Prix = new Label("Prix : " + c.getPrix(), "largeLabel");
            Label PrixFinal = new Label("Prix Final : " + c.getPrixFinal(), "largeLabel");
            Label fournisseur = new Label("Fournisseur :   " + usr.get(i).getNom() + " " + usr.get(i).getPrenom(), "largeLabel");
            SpanLabel Description = new SpanLabel("Description : " + c.getDescription(), "largeLabel");
            Button Supp = new Button("Supprimer le Produit");
            Button Modifier = new Button("Modifier le Produit");

            Nom.getAllStyles().setFgColor(0x509c88);
            Prix.getAllStyles().setFgColor(0x509c88);
            PrixFinal.getAllStyles().setFgColor(0x509c88);
            Description.getAllStyles().setFgColor(0x509c88);
            c1.add(Nom);
            c1.add(Prix);
            c1.add(PrixFinal);
            c1.add(Description);
            c1.add(fournisseur);
            c1.add(Cate);
            c1.add(Supp);
            c1.add(Modifier);
//            try {
//                imgR = new ImageViewer(Image.createImage("/" + c.getImage()));
//                Dimension d = new Dimension(500, 500);
//
//                imgR.setPreferredSize(d);
//                c1.add(imgR);
//            } catch (IOException ex) {
//                System.out.println(ex.getMessage());
//            }

            add(LayeredLayout.encloseIn(c1));
            i++;
            Supp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Dialog.show("Alerte de suppression!", "Etes-vous sure de vouloir supprimer ce produit?", "Oui", "Non");

                    boolean state = ServiceProduits.getInstance().supprimerProduit((int) c.getId_produit());
                    new ProduitAdminForm(res).show();

                }
            });
//            c1.add(imgR);

            // Action du boutton modification
            Modifier.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
//                    Dialog.show("Alerte de modification!", "Etes-vous sure de vouloir modifier ce produit?", "Oui", "Non");
                    p = c;
                    new modifierProduitForm(res).show();
                }
            });
        }

    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

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
    }

    private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
        likes.setTextPosition(RIGHT);
        if (!liked) {
            FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
        } else {
            Style s = new Style(likes.getUnselectedStyle());
            s.setFgColor(0xff2d55);
            FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
            likes.setIcon(heartImage);
        }
        Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta,
                        BoxLayout.encloseX(likes, comments)
                ));
        add(cnt);
        image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}
