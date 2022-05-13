/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
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
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class ProductDetailsForm extends BaseForm {

    showproducts d;
    private Resources theme = UIManager.initFirstTheme("/theme");
    ImageViewer imgR = null;

    public ProductDetailsForm(Resources res) {
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
        addTab(swipe, res.getImage("pika.png"), spacer1, "", "", "Product Details");

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
        SpanLabel Nom = new SpanLabel("Nom du Produit : " + d.p.getNom(), "largeLabel");
//        addTab(swipe, res.getImage("details.png"), spacer1, "", "", "Product Details");
        System.out.println(d.p.getImage());

//        Button lbimg = new Button(res.getImage(d.p.getImage()).scaled(300, 300));
        Label Prix = new Label("Prix : " + d.p.getPrix(), "largeLabel");
        SpanLabel Description = new SpanLabel("Description : " + d.p.getDescription(), "largeLabel");
        Button Ajout = new Button("Ajouter au panier");
        TextField quant = new TextField("", "entrer Quantite");

        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        Container c1 = new Container(BoxLayout.y());

        Nom.getAllStyles().setFgColor(0x509c88);
        Prix.getAllStyles().setFgColor(0x509c88);
        Description.getAllStyles().setFgColor(0x509c88);

//        Image
//        ImageViewer imgA = null;
//       
//          try {
//            imgA = new ImageViewer(Image.createImage("/" + d.p.getImage()));
//
//            Dimension d = new Dimension(300, 300);
//
//            imgA.setPreferredSize(d);
//
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//        System.out.println(d.p.getImage());
        Ajout.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
if(quant.getText().equals("")){
                     Dialog.show("Echec", "Remplir la Quantite !!!! "
                               , "ok","Annuler");
                }else{
                    Dialog.show("Confirmation", "Voulez vous Ajouter ce produit au panier !!! "
                               , "ok","Annuler");
                   boolean test = servicePanierElement.getInstance().AjouterElement(Integer.parseInt(quant.getText()),(int)d.p.getId_produit(),1);
                   if(test == true){
                        Dialog.show("Succés", " Ajout du produit faite avec succés !!! "
                               , "ok","Annuler");
                   }else{
                        Dialog.show("Echec", "Echec d'ajout !!! "
                               , "ok","Annuler");
                   }
                   new MonPanierForm(res).show();
                }
            }
        });

        c1.add(Nom);
//        c1.add(lbimg);
        c1.add(Prix);
        c1.add(Description);
        c1.add(quant);
        c1.add(Ajout);
//        c1.add(imgR);
//        c1.add(imgA);
//
//        try {
//            imgR = new ImageViewer(Image.createImage("/" + d.p.getImage()));
//            Dimension d = new Dimension(500, 500);
//
//            imgR.setPreferredSize(d);
//            c1.add(imgR);
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//        c1.add(imgR);
        add(LayeredLayout.encloseIn(c1));
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

}
