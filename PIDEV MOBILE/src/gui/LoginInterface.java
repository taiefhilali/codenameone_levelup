/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.ActivateForm;
import com.mycompany.gui.NewsfeedForm;
import entities.User;


import com.mycompany.myapp.entities.services.AuthService;

/**
 *
 * @author LENOVO
 */
public class LoginInterface extends Form {
 private Resources theme = UIManager.initFirstTheme("/theme");
    public LoginInterface(Resources res) {
        super("Connexion", BoxLayout.y().yCenter());
           this.getStyle().setBgColor(0xffffff);
        Button b = new Button("se connecter");
           Label lbimg = new Label(theme.getImage("image-asset.png").scaled(800, 800));
        TextField emailTextField = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        emailTextField.setPreferredSize(new Dimension(200,140));
      b.setPreferredSize(new Dimension(200,140));
        b.getStyle().setBgTransparency(255);
        b.getStyle().setBgColor(0xcfb9);
        b.getStyle().setFgColor(0xffffff);
      
        
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        b.getStyle().setBorder(RoundBorder.create().
                rectangle(true).
                color(0xcfb9).
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        TextField passwordTextField = new TextField("", "Password", 50, TextField.PASSWORD);
             passwordTextField.setPreferredSize(new Dimension(200,140));
        b.addActionListener(ev -> {
            if ((emailTextField.getText().length() == 0) || (passwordTextField.getText().length() == 0)) {
                Dialog.show("Alert", "Tout les champs sont requis", new Command("OK"));
            } else {
                User u = new User(emailTextField.getText(), passwordTextField.getText());

                int response;
                try {
                    response = AuthService.getInstance().login(u);
                    switch (response) {
                        case 200:
                            if (Dialog.show("Success", "connecté !", "OK", null)) {
                         new NewsfeedForm(res).show();

                            }
                            break;
                        case 400:
                            Dialog.show("ERROR", "Email incorrecte ", new Command("OK"));
                            break;
                        case 404:
                            Dialog.show("ERROR", "Mot de passe incorrecte", new Command("OK"));
                            break;
                        default:
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                            break;
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }
        }
        );
        Button notAuser = new Button("Créer un compte !");
        notAuser.addActionListener(el -> {
            RegisterInterface registerInterface = new RegisterInterface(res);
            registerInterface.show();
        });
        Button resetPassword = new Button("Mot de passe oublié ?");
        resetPassword.addActionListener(el -> {
            MdpOublie mdp= new MdpOublie(res);
            mdp.show();
       
        });
        this.add(lbimg).add(emailTextField)
                .add(passwordTextField).add(b).add(notAuser).add(resetPassword);

    }

   
}
