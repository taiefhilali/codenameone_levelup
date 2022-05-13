/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
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
 * @author beldi
 */
public class EditUserForm extends Form {
     private Resources theme = UIManager.initFirstTheme("/theme");
     public EditUserForm(Form previous,User u) {
      super("Modifier Utilisateur", BoxLayout.y().yCenter());
      
           this.getStyle().setBgColor(0xffffff);
      
           Label lbimg = new Label(theme.getImage(u.getImage()).scaled(500, 500));
           
         TextField tfEmail = new TextField(u.getEmail(),"Email");tfEmail.setPreferredSize(new Dimension(200,140));
         TextField tfPrenom = new TextField(u.getPrenom(),"Prenom");tfPrenom.setPreferredSize(new Dimension(200,140));
         TextField tfNom = new TextField(u.getNom(),"Nom");tfNom.setPreferredSize(new Dimension(200,140));
         TextField tfRole = new TextField(u.getRole(),"Role");tfRole.setPreferredSize(new Dimension(200,140));
       
       
        Button btnValider = new Button("Modifier user");
             btnValider.getStyle().setBgTransparency(255);
        btnValider.getStyle().setBgColor(0xcfb9);
        btnValider.getStyle().setFgColor(0xffffff);
     
        
       
          
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
                if ((tfEmail.getText().length()==0)||(tfPrenom.getText().length()==0)||(tfNom.getText().length()==0)||(tfRole.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                         User t = new User(u.getId(), tfEmail.getText().toString(),tfRole.getText().toString(),   tfNom.getText().toString(),  tfPrenom.getText().toString() );
                        if( ServiceUser.getInstance().editUser(t))
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
        
        addAll(lbimg,tfEmail,tfPrenom,tfNom,tfRole,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
                
    }
    
}
