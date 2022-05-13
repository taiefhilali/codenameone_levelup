/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.User;
import java.util.List;
import com.mycompany.myapp.entities.services.ServiceUser;

/**
 *
 * @author bhk
 */
public class ListUsersForm extends Form {

    private Resources theme = UIManager.initFirstTheme("/theme");

    public ListUsersForm(Form previous) {

        super("Liste des utilisateurs", BoxLayout.y());

        SpanLabel sp = new SpanLabel();
        Button btnAddTask = new Button("Ajouter Utilisateur");
        btnAddTask.setPreferredSize(new Dimension(200,140));
        btnAddTask.getStyle().setBgTransparency(255);
        btnAddTask.getStyle().setBgColor(0xcfb9);
        btnAddTask.getStyle().setFgColor(0xffffff);
       
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        btnAddTask.getStyle().setBorder(RoundBorder.create().
                rectangle(true).
                color(0xcfb9).
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        btnAddTask.addActionListener(e -> new AddUserForm(previous).show());
        add(btnAddTask);
        List<User> users = ServiceUser.getInstance().getAllTasks();
        for (User u : users) {
            add(addItem(u, previous));
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public Container addItem(User us, Form previous) {

        Container cnt1 = new Container(BoxLayout.x());
        Button lbnom = new Button(us.getPrenom() + " " + us.getNom());
        Label email = new Label(us.getPrenom() + " " + us.getEmail());

        lbnom.getStyle().setFgColor(0x0);

        cnt1.add(lbnom);


        Container cnt = new Container(BoxLayout.x());

        Button lbimg = new Button(theme.getImage(us.getImage()).scaled(300, 300));

        cnt.addAll(lbimg, cnt1);
        cnt.getStyle().setBgTransparency(255);
        cnt.getStyle().setBgColor(0xFFFFFF);
       
                    Form f2 = new Form(BoxLayout.y());
        lbimg.addActionListener(e -> {

   
        Label lb1 = new Label();
        Label lb2 = new Label();
        Label lb3 = new Label();
        Label lb4 = new Label();
        Label lb5 = new Label();
        Label lb6 = new Label();
        Label lb7 = new Label();

        f2.add(lb1);
        f2.add(lb2);
        f2.add(lb3);
        f2.add(lb4);
        f2.add(lb5);
        f2.add(lb6);
        f2.add(lb7);
            lb1.setIcon(theme.getImage(us.getImage()).scaled(700, 700));
           
            
            
            lb2.setText(us.getPrenom() + " " + us.getNom());
            lb3.setText(us.getEmail());
            lb4.setText(us.getAdresse());
            lb5.setText(us.getTel());
            lb6.setText(us.getDns());
            lb7.setText(us.getRole());
            lb2.getStyle().setFgColor(0x0);
            lb3.getStyle().setFgColor(0x0);
            lb4.getStyle().setFgColor(0x0);
            lb5.getStyle().setFgColor(0x0);
            lb6.getStyle().setFgColor(0x0);
            lb7.getStyle().setFgColor(0x0);

            Button btnEdit = new Button("Modifier Utilisateur");
            Button btnDelete = new Button("Supprimer Utilisateur");
            btnEdit.addActionListener(x -> new EditUserForm(previous, us).show());
            btnEdit.getStyle().setBgTransparency(255);
            btnEdit.getStyle().setBgColor(0xcfb9);
            btnEdit.getStyle().setFgColor(0xffffff);
            btnDelete.getStyle().setBgTransparency(255);
            btnDelete.getStyle().setBgColor(0xcfb9);
            btnDelete.getStyle().setFgColor(0xffffff);
            Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
            btnEdit.setPreferredSize(new Dimension(200,140));
            btnDelete.setPreferredSize(new Dimension(200,140));
            btnEdit.getStyle().setBorder(RoundBorder.create().
                    rectangle(true).
                    color(0xcfb9).
                    strokeColor(0).
                    strokeOpacity(120).
                    stroke(borderStroke));
            btnDelete.getStyle().setBorder(RoundBorder.create().
                    rectangle(true).
                    color(0xcfb9).
                    strokeColor(0).
                    strokeOpacity(120).
                    stroke(borderStroke));
            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Dialog.show("Supprimer un utilisateur", "Voulez Vous vraiment supprimer l'utilisateur", "Oui", "Non");
                    ServiceUser.getInstance().deleteUser(us);
                }
            });
            f2.add(btnEdit);
            f2.add(btnDelete);
            f2.getStyle().setBgTransparency(255);
            f2.getStyle().setBgColor(0xFFFFFF);

            f2.show();
            
refreshTheme();

      
        });
  f2.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> this.showBack());
        return cnt;
    }

}
