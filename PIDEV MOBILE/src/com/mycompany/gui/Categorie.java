/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

/**
 *
 * @author Iskander
 */
public class Categorie {

    private float id_categorie;
    private String nom_categorie; // Seuelement???

    // Constructor vide
    public Categorie() {
    }

    // Constructor paramétre
    public Categorie(float id_categorie, String nom_categorie) {
        this.id_categorie = id_categorie;
        this.nom_categorie = nom_categorie;
    }

    // Constructor sans id
    public Categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    //Constructeur avec id seuelemnt
    public Categorie(float id_categorie) {
        this.id_categorie = id_categorie;
    }

    // Getters & Setters
    public float getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(float id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    @Override
    public String toString() {
        return "Categorie: {" + "id_categorie=" + id_categorie + ", nom_categorie=" + nom_categorie + '}';
    }

    public String afficherParNomCat() {
        return nom_categorie;
    }
}
