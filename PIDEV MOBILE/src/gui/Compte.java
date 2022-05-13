/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
//public class HomeForm extends Form{
//Form current;
//    public HomeForm() {
//        current=this; //Back 
//        setTitle("Home");
//        setLayout(BoxLayout.y());
//        
//        add(new Label("Choose an option"));
//      
//        Button btnListTasks = new Button("Les utilisateurs");
//      
//        btnListTasks.addActionListener(e-> new ListUsersForm(current).show());
//        addAll(btnListTasks);
//        
//        
//    }
//    
//    
//}

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.BaseForm;

import java.util.Map;
import utils.Session;

import utils.Statics;

/**
 *
 * @author LENOVO
 */
public class Compte extends BaseForm {

    private Resources theme = UIManager.initFirstTheme("/theme");

    public Compte(Resources res,Form form) {
        super("Mon profile", BoxLayout.y().yCenter());
        this.getStyle().setBgColor(0xffffff);

        Label lbimg = new Label(theme.getImage(Session.getImage()).scaled(800, 800));
        Label emailTextField = new Button(Session.getEmail());
        Label nom = new Label(Session.getNom());
        Label prenom = new Label(Session.getPrenom());
        Label adresse = new Label(Session.getAdresse());
        Label tel = new Label(Session.getTel());
        Label role = new Label(Session.getRole());
        emailTextField.setPreferredSize(new Dimension(200, 140));
        
        emailTextField.getStyle().setFgColor(0x0);
        nom.setPreferredSize(new Dimension(200, 140));
        
        nom.getStyle().setFgColor(0x0);
        prenom.setPreferredSize(new Dimension(200, 140));
       
        prenom.getStyle().setFgColor(0x0);

        adresse.setPreferredSize(new Dimension(200, 140));
        
        adresse.getStyle().setFgColor(0x0);
        tel.setPreferredSize(new Dimension(200, 140));
       
        tel.getStyle().setFgColor(0x0);
        role.setPreferredSize(new Dimension(200, 140));
   
        role.getStyle().setFgColor(0x0);
        this.add(lbimg).add(emailTextField).add(prenom).add(nom).add(role).add(adresse).add(tel);
        try {
  
            //Label welcomeLabel = new Label("Bienvenue " + Statics.user_data.getName() + " " + Statics.user_data.getLastName());
            Label welcomeLabel = new Label();
            Container centered = FlowLayout.encloseCenter(welcomeLabel);
            Toolbar tb = getToolbar();
        
        
        
        
            
//           this.getToolbar().addCommandToLeftSideMenu("Mon Profile", null, ev -> {
//                 CompteForm compte = new CompteForm(this);
//                 compte.show();
//            });
//
//            this.getToolbar().addCommandToLeftSideMenu("Gerer Utilisateurs", null, ev -> {
//                ListUsersForm hotelListInterface = new ListUsersForm(this);
//                hotelListInterface.show();
//            });
//            this.getToolbar().addCommandToLeftSideMenu("Gerer Produit", null, ev -> {
//                 ListUsersForm hotelListInterface = new ListUsersForm(this);
//                hotelListInterface.show();
//            });
//          

           
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> form.showBack());
        
    

            this.add(centered);

           
        } catch (Exception ex) {
            System.err.println(ex);
        }
        
    }

}
