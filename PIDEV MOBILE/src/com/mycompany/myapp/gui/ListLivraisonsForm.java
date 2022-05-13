/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.services.ServiceLivraison;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author bhk
 */
public class ListLivraisonsForm extends Form {
   Resources theme;
    public ListLivraisonsForm(Form previous) {
       theme = UIManager.initFirstTheme("/theme");
        setTitle("List Livraisons");
        TextField tfPrenom= new TextField("", "recherche");
         Button rechercher = new Button ("rechercher");
         add(tfPrenom);
         add(rechercher);
        ArrayList<Livraison> list =ServiceLivraison.getInstance().getAllLivraisons();
        for(Livraison liv: list) {
        setLayout(BoxLayout.y());
        Container cnt1 = new Container(BoxLayout.x());
        Container cnt2 = new Container(BoxLayout.y());
//        Container cnt3 = new Container(new FlowLayout(CENTER,CENTER));
//        ImageViewer img = new ImageViewer(theme.getImage("oeufs.jpg").scaled(150, 150));
//        cnt3.add(img);
        Label idLivraison = new Label ("ID / DUNS: " + liv.getId_livraison());
        Label date= new Label ("Date : " + liv.getDate());
        Label EtatLivraison = new Label ("EtatLivraison : " + liv.getEtat_livraison()); 
   
         //Label photo = new Label ("photo : " + soc.getPhoto()); 
       cnt2.addAll(idLivraison,date, EtatLivraison);
       Button modifier = new Button ("modifier");
       
       Button supprimer = new Button ("supprimer");
       cnt1.addAll(cnt2,modifier,supprimer);
        add(cnt1);
       supprimer.addActionListener(e->{
       Dialog dig = new Dialog("supression");
       if (dig.show("suppression","veuillez supprimer une Livraison","annuler","oui"))
            dig.dispose();
       else {
       dig.dispose();
       if (ServiceLivraison.getInstance().supprimerLivraison(liv)){
           System.out.println(liv.getId_livraison());
           new ListLivraisonsForm(previous).show();}
       }
       }); 
       
       modifier.addActionListener(m-> {
       new EditLivraisonForm(previous, liv).show();
       });
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
                
            
       
       }
//          rechercher.addActionListener(m-> {
//       new RechercherSocieteForm(previous, tfPrenom.getText().toString()).show();
//       });
//       /* SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceSociete.getInstance().getAllSocietes().toString());
//        add(sp); */
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
   }

}