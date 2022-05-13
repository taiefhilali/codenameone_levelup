/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author User
 */
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
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author bhk
 */
public class ListReclamationsForm extends Form {
   Resources theme;
    public ListReclamationsForm(Form previous) {
       theme = UIManager.initFirstTheme("/theme");
        setTitle("List Livraisons");
        TextField tfPrenom= new TextField("", "recherche");
         Button rechercher = new Button ("rechercher");
         add(tfPrenom);
         add(rechercher);
        ArrayList<Reclamation> list =ServiceReclamation.getInstance().getAllReclamations();
        for(Reclamation rec: list) {
        setLayout(BoxLayout.y());
        Container cnt1 = new Container(BoxLayout.x());
        Container cnt2 = new Container(BoxLayout.y());
//        Container cnt3 = new Container(new FlowLayout(CENTER,CENTER));
//        ImageViewer img = new ImageViewer(theme.getImage("oeufs.jpg").scaled(150, 150));
//        cnt3.add(img);
        Label idReclamation= new Label ("ID / DUNS: " + rec.getId_reclamation());
        Label description= new Label ("Description : " + rec.getDescription());
        Label Warn = new Label ("Warn : " + rec.getWarn()); 
   
         //Label photo = new Label ("photo : " + soc.getPhoto()); 
       cnt2.addAll(idReclamation,description, Warn);
       Button modifier = new Button ("modifier");
       Button supprimer = new Button ("supprimer");
       cnt1.addAll(cnt2,modifier,supprimer);
        add(cnt1);
       supprimer.addActionListener(e->{
       Dialog dig = new Dialog("supression");
       if (dig.show("suppression","veuillez supprimer une Reclamation","annuler","oui"))
            dig.dispose();
       else {
       dig.dispose();
       if (ServiceReclamation.getInstance().supprimerReclamation(rec)){
           System.out.println(rec.getId_reclamation());
           new ListReclamationsForm(previous).show();}
       }
       }); 
       
       modifier.addActionListener(m-> {
       new EditReclamationForm (previous, rec).show();
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