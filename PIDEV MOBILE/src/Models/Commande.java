/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;


/**
 *
 * @author ASUS
 */
public class Commande {
    
    //var
   private int id_commande;
   private User client;
   private float prix_livraison;
   private float prix_produits;
   private float prix_total;
   private String date_commande;
   String mode;
    
   //Constructeur
    public Commande() {
    }  
    public Commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public Commande(int id_commande, User client, float prix_livraison, float prix_produits, float prix_total, String date_commande) {
        this.id_commande = id_commande;
        this.client = client;
        this.prix_livraison = prix_livraison;
        this.prix_produits = prix_produits;
        this.prix_total = prix_total;
        this.date_commande = date_commande;
    }

    public Commande(int id_commande, float prix_livraison, float prix_produits, float prix_total, String date_commande,String mode) {
        this.id_commande = id_commande;
        this.prix_livraison = prix_livraison;
        this.prix_produits = prix_produits;
        this.prix_total = prix_total;
        this.date_commande = date_commande;
        this.mode = mode;
    }
    
    
    public Commande(int id_commande, User client, float prix_livraison, String date_commande) {
        this.id_commande = id_commande;
        this.client = client;
        this.prix_livraison = prix_livraison;
        this.date_commande = date_commande;
    }

    public Commande(int id_commande, float prix_livraison, String date_commande) {
        this.id_commande = id_commande;
        this.prix_livraison = prix_livraison;
        this.date_commande = date_commande;
    }
    
    //Getters and setters
    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public float getPrix_produits() {
        return prix_produits;
    }

    public void setPrix_produits(float prix_produits) {
        this.prix_produits = prix_produits;
    }

    public float getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(float prix_total) {
        this.prix_total = prix_total;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }


    public float getPrix_livraison() {
        return prix_livraison;
    }

    public void setPrix_livraison(float prix_livraison) {
        this.prix_livraison = prix_livraison;
    }

    public String getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(String date_commande) {
        this.date_commande = date_commande;
    }

    @Override
    public String toString() {
        return "Commande{" + "id_commande=" + id_commande + ", client=" + client + ", prix_livraison=" + prix_livraison + ", prix_produits=" + prix_produits + ", prix_total=" + prix_total + ", date_commande=" + date_commande + ", mode=" + mode + '}';
    }


   
    
   
   
}
