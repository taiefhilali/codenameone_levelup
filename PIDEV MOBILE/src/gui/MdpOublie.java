/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import java.util.Random;

import com.mycompany.myapp.entities.services.AuthService;

/**
 *
 * @author beldi
 */
public class MdpOublie extends Form {

    private Resources theme = UIManager.initFirstTheme("/theme");
    int randomCode;

    public MdpOublie(Resources res) {

        super("Connexion", BoxLayout.y().yCenter());
        this.getStyle().setBgColor(0xffffff);
        Button b = new Button("Mot de passe oublie");
        Button c = new Button("Envoyer");
        Label lbimg = new Label(theme.getImage("image-asset.png").scaled(800, 800));
        TextField emailTextField = new TextField("", "Adresse e-mail", 20, TextField.EMAILADDR);
        emailTextField.setPreferredSize(new Dimension(200, 140));
        
        b.setPreferredSize(new Dimension(200, 140));
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
        TextField passwordTextField = new TextField("", "Password");
        passwordTextField.setPreferredSize(new Dimension(200, 140));
        c.addActionListener(ev -> {
            if ((emailTextField.getText().length() == 0)) {
                Dialog.show("Alert", "Entrer Votre Email", new Command("OK"));
            } else {
                Random rand = new Random();
                randomCode = rand.nextInt(999999);
                try {
                    System.out.println(AuthService.getInstance().Password(emailTextField.getText(), randomCode));
                } catch (Exception ex) {

                }

            }

        });
        b.addActionListener(ev -> {
            if ((passwordTextField.getText().length() == 0)) {
                Dialog.show("Alert", "Entrer le code ", new Command("OK"));
            } else {
                if (randomCode == Integer.valueOf(passwordTextField.getText())) {
                    //creation form
                    Form f2 = new Form(BoxLayout.y().yCenter());
                    f2.getStyle().setBgColor(0xffffff);
                    //creation bouton
                    Button p = new Button("Modifier");
                    Label img = new Label(theme.getImage("image-asset.png").scaled(800, 800));
                    //creation password
                    TextField password = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
                    password.setPreferredSize(new Dimension(200, 140));
                 
                    p.setPreferredSize(new Dimension(200, 140));
                    p.getStyle().setBgTransparency(255);
                    p.getStyle().setBgColor(0xcfb9);
                    p.getStyle().setFgColor(0xffffff);
                    

                    Stroke border = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
                    p.getStyle().setBorder(RoundBorder.create().
                            rectangle(true).
                            color(0xcfb9).
                            strokeColor(0).
                            strokeOpacity(120).
                            stroke(border));
                    
                    p.addActionListener(e -> {
                       LoginInterface login = new LoginInterface(res);
                       login.show();

                    });
                   f2.add(img).add(password).add(p);
                    f2.show();               
                } else {
                    Dialog.show("Alert", "Code incorrecte", new Command("OK"));
                   
                }

            }

        });
        this.add(lbimg).add(emailTextField).add(c)
                .add(passwordTextField).add(b);
    }

  
}
