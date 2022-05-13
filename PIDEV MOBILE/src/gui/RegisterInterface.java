/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author beldi
 */

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import entities.User;



import com.mycompany.myapp.entities.services.AuthService;

/**
 *
 * @author LENOVO
 */
public class RegisterInterface extends Form {

    public RegisterInterface(Resources res) {
        
        super("Inscription", BoxLayout.y().yCenter());
         this.getStyle().setBgColor(0xffffff);
        Button b = new Button("S'inscrire");
        TextField nameTextField = new TextField("", "Nom", 50, TextField.USERNAME);
        TextField lastNameTextField = new TextField("", "Prénom", 50, TextField.USERNAME);
        TextField emailTextField = new TextField("", "E-Mail", 50, TextField.EMAILADDR);
        TextField passwordTextField = new TextField("", "Mot de passe", 50, TextField.PASSWORD);
        TextField secondPasswordTextField = new TextField("", "Ré entrer votre mot de passe", 50, TextField.PASSWORD);
        TextField adresse = new TextField("", "Adresse");
        TextField tel = new TextField("", "Telephone");
        
        BrowserComponent browser = new BrowserComponent();
        browser.setURL("http://localhost/recaptcha.html");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
                browser.setPreferredSize(new Dimension(200,900));
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
           b.addActionListener(ev -> {
            if ((emailTextField.getText().length() == 0) || (passwordTextField.getText().length() == 0)
                    || lastNameTextField.getText().length() == 0 || (secondPasswordTextField.getText().length() == 0)
                    || nameTextField.getText().length() == 0 || adresse.getText().length() == 0 || tel.getText().length() == 0 ){
                Dialog.show("Alert", "Tout les champs sont requis", new Command("OK"));
            }
            if (!passwordTextField.getText().equals(secondPasswordTextField.getText())) {
                Dialog.show("Alert", "Les mots de passe ne se correspondent pas", new Command("OK"));
            } else {
                     String s=AuthService.getInstance().Recaptcha();
                System.out.println(s);
                SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
              
               User u = new User(emailTextField.getText(), passwordTextField.getText(), "client", nameTextField.getText(),
                       lastNameTextField.getText(),
                       adresse.getText(),
                       tel.getText(),
                       format.format(datePicker.getDate()
                       )
                      );
                int response;
                try {
                    response = AuthService.getInstance().register(u);
                    switch (response) {
                        case 200:
                            if (Dialog.show("Success", "Votre compte a été créé avec succès", "OK", null)) {
                           LoginInterface loginInterface = new LoginInterface(res);
                           loginInterface.showBack();
                            }
                            break;
                    
                        case 404:
                            Dialog.show("ERROR", "Mot de passe ou email sont incorrectes", new Command("OK"));
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

        Button alrdyUser = new Button("Vous avec un compte ?");

        alrdyUser.addActionListener(el
                -> {
            LoginInterface loginInterface = new LoginInterface(res);
            loginInterface.showBack();
        }
        );

        this.add(emailTextField)
                .add(nameTextField).add(lastNameTextField)
                .add(passwordTextField).add(secondPasswordTextField).add(adresse).add(tel).add(datePicker).add(browser).add(b).add(alrdyUser);

    }

 
}
