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
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.User;
import com.mycompany.myapp.entities.services.AuthService;
import utils.Session;

/**
 *
 * @author beldi
 */
public class CompteForm   extends Form {

    private Resources theme = UIManager.initFirstTheme("/theme");

    public CompteForm(Form previous) {
        super("Mon profile", BoxLayout.y().yCenter());
        this.getStyle().setBgColor(0xffffff);

        Button lbimg = new Button(theme.getImage(Session.getImage()).scaled(800, 800));
        Button emailTextField = new Button(Session.getEmail());
        Button nom = new Button(Session.getNom());
        Button prenom = new Button(Session.getPrenom());
        Button adresse = new Button(Session.getAdresse());
        Button tel = new Button(Session.getTel());
        Button role = new Button(Session.getRole());
        emailTextField.setPreferredSize(new Dimension(200, 140));
        emailTextField.getStyle().setMarginTop(5);
        emailTextField.getStyle().setMarginBottom(3);
        emailTextField.getStyle().setFgColor(0x0);
        nom.setPreferredSize(new Dimension(200, 140));
        nom.getStyle().setMarginTop(3);
        nom.getStyle().setMarginBottom(3);
        nom.getStyle().setFgColor(0x0);
        prenom.setPreferredSize(new Dimension(200, 140));
        prenom.getStyle().setMarginTop(3);
        prenom.getStyle().setMarginBottom(3);
        prenom.getStyle().setFgColor(0x0);

        adresse.setPreferredSize(new Dimension(200, 140));
        adresse.getStyle().setMarginTop(3);
        adresse.getStyle().setMarginBottom(3);
        adresse.getStyle().setFgColor(0x0);
        tel.setPreferredSize(new Dimension(200, 140));
        tel.getStyle().setMarginTop(3);
        tel.getStyle().setMarginBottom(3);
        tel.getStyle().setFgColor(0x0);
        role.setPreferredSize(new Dimension(200, 140));
        role.getStyle().setMarginTop(3);
        role.getStyle().setMarginBottom(3);
        role.getStyle().setFgColor(0x0);
        this.add(lbimg).add(emailTextField).add(prenom).add(nom).add(role).add(adresse).add(tel);
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

   
}
