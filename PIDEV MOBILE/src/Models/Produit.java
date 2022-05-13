/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Iskander
 */
public class Produit {

    private float id_produit;
    private String nom;
    private String reference;  // 0x00001 // How to?
    private Categorie categorie;
    private float prix;
    private String description;
    // private String etat ? // présent ou non dans le stock // Stock  // id du stock
    private User user; // = Fid_fournisseur
    private float promotion;
    private String image;
    public float prixFinal;
    // Constructeur vide
    public Produit() {
    }

    // Constructeur paramétrés (tout les attributs)
    public Produit(float id_produit, String nom, String reference, Categorie categorie, float prix, String description, User user, float promotion, String image, float prixFinal) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.reference = reference;
        this.categorie = categorie;
        this.prix = prix;
        this.description = description;
        this.user = user;
        this.promotion = promotion;
        this.image = image;
        this.prixFinal=prixFinal;
    }

    // Constructeur sans l'attribut ID
    public Produit(String nom, String reference, Categorie categorie, float prix, String description, User user, float promotion, String image) {
        this.nom = nom;
        this.reference = reference;
        this.categorie = categorie;
        this.prix = prix;
        this.description = description;
        this.user = user;
        this.promotion = promotion;
        this.image = image;
    }
    
      // Constructeur sans l'attribut ID
    public Produit(float id,String nom, String reference, Categorie categorie, float prix, String description, User user, float promotion, String image) {
        this.id_produit = id;
        this.nom = nom;
        this.reference = reference;
        this.categorie = categorie;
        this.prix = prix;
        this.description = description;
        this.user = user;
        this.promotion = promotion;
        this.image = image;
    }

    public Produit(float id_produit, String nom, float prix, String description, String image) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.image = image;
    }

    
    public Produit(float id_produit, String nom, String reference, float prix, String description) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.reference = reference;
        this.prix = prix;
        this.description = description;
    }

    
    
    public Produit(float id, String nom, float prix, String description) {
        this.id_produit = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
    }
    // Constructeur sans image

    public Produit(float id_produit, String nom, String reference, Categorie categorie, float prix, String description, User user, float promotion) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.reference = reference;
        this.categorie = categorie;
        this.prix = prix;
        this.description = description;
        this.user = user;
        this.promotion = promotion;
    }

    /// TEST 
    // Constructeur sans id et sans image
    public Produit(String nom, String reference, Categorie categorie, float prix, String description, User user, float promotion) {
        this.nom = nom;
        this.reference = reference;
        this.categorie = categorie;
        this.prix = prix;
        this.description = description;
        this.user = user;
        this.promotion = promotion;
    }

    // Création des getters et Setters
    public float getId_produit(){
        return id_produit;
    }

    public void setId_produit(float id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getPromotion() {
        return promotion;
    }

    public void setPromotion(float promotion) {
        this.promotion = promotion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrixFinal() {
        return prixFinal;
    }

    public void setPrixFinal(float prixFinal) {
        this.prixFinal = prixFinal;
    }

    
    // To String
    @Override   
    public String toString() {
        return " ** Les produits sont : {" + "id_produit=" + id_produit + ", nom=" + nom + ", reference=" + reference + ", categorie=" + categorie + ", prix=" + prix + ", description=" + description + ", user=" + user + ", promotion=" + promotion+ ", image=" + image + ", prixFinal=" + prixFinal + '}'; 
    }

    // La méthode de concaténation pour le 1ér métier
    public String concat() {
        return id_produit + "-" + nom + "-" + reference + "-" + categorie.getId_categorie() + "-" + categorie.getNom_categorie() + "-" + prix + "-" + description + "-" + user.getEmail()+ "-" + promotion + "-" + image + "-"; // probléme d'espace / path : 7othom f dossier sans espace
    }
}
