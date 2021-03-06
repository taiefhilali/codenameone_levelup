/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author User
 */
public class Livraison {
    
    //var
    private int id_livraison;
    private int id_commande;
    private int id_user;
    private String date;
    private String etat_livraison;
    
    //constructeur

    public Livraison() {
    }

    public Livraison(int id_livraison, int id_commande, int id_user, String date, String etat_livraison) {
        this.id_livraison = id_livraison;
        this.id_commande = id_commande;
        this.id_user = id_user;
        this.date = date;
        this.etat_livraison = etat_livraison;
    }

    public Livraison(int id_commande, int id_user, String date, String etat_livraison) {
        this.id_commande = id_commande;
        this.id_user = id_user;
        this.date = date;
        this.etat_livraison = etat_livraison;
    }

    public Livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

   

  

  

    
    //getter et setter

    public int getId_livraison() {
        return id_livraison;
    }

    public int getId_commande() {
        return id_commande;
    }

    public int getId_user() {
        return id_user;
    }

  

    public String getDate() {
        return date;
    }

    public String getEtat_livraison() {
        return etat_livraison;
    }

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

   

    public void setDate(String date) {
        this.date = date;
    }

    public void setEtat_livraison(String etat_livraison) {
        this.etat_livraison = etat_livraison;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id_livraison=" + id_livraison + ", commande=" + id_commande + ", user=" + id_user + ", date=" + date + ", etat_livraison=" + etat_livraison + '}';
    }

    public Livraison(String date, String etat_livraison) {
        this.date = date;
        this.etat_livraison = etat_livraison;
    }


 

   
    
}


