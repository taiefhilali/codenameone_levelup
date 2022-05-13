/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.User;
import com.mycompany.myapp.entities.services.ServiceUser;

/**
 *
 * @author bhk
 */
public class AddUserForm extends Form{
 private Resources theme = UIManager.initFirstTheme("/theme");
    public AddUserForm(Form previous) {
 super("Ajouter Utilisateur", BoxLayout.y().yCenter());
           this.getStyle().setBgColor(0xffffff);
        Button b = new Button("se connecter");
           Label lbimg = new Label(theme.getImage("user-add.png").scaled(500, 500));
  
           lbimg.getStyle().setMarginBottom(40);
        this.getStyle().setBgColor(0xffffff);
         TextField tfEmail = new TextField("","Email");
         tfEmail.setPreferredSize(new Dimension(200,140));
         
         TextField tfPrenom = new TextField("","Prenom");tfPrenom.setPreferredSize(new Dimension(200,140));
         
         TextField tfNom = new TextField("","Nom");tfNom.setPreferredSize(new Dimension(200,140));
         AutoCompleteTextField tfRole = new AutoCompleteTextField("administrateur","livreur","fournisseur");tfRole.setPreferredSize(new Dimension(200,140));
         TextField tfPassword = new TextField("","Password");tfPassword.setPreferredSize(new Dimension(200,140));
         tfPassword.setConstraint(TextField.PASSWORD);
       
        Button btnValider = new Button("Ajouter");
          btnValider.getStyle().setBgTransparency(255);
        btnValider.getStyle().setBgColor(0xcfb9);
        btnValider.getStyle().setFgColor(0xffffff);
        btnValider.getStyle().setMarginTop(40);
      
        
       
          
            Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
            btnValider.setPreferredSize(new Dimension(200,140));
         
            btnValider.getStyle().setBorder(RoundBorder.create().
                    rectangle(true).
                    color(0xcfb9).
                    strokeColor(0).
                    strokeOpacity(120).
                    stroke(borderStroke));
        
  
       
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfEmail.getText().length()==0)||(tfPrenom.getText().length()==0)||(tfNom.getText().length()==0)||(tfRole.getText().length()==0)||(tfPassword.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        User t = new User( tfEmail.getText().toString(),  tfPassword.getText().toString(),  tfRole.getText().toString(),  tfNom.getText().toString(),  tfPrenom.getText().toString());
                        if( ServiceUser.getInstance().addUser(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(lbimg,tfEmail,tfPrenom,tfNom,tfRole,tfPassword,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.show());
                
    }
    
    
}
