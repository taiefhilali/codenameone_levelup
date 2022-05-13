/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author msi
 */
public class Facture {
       private int id_facture;
    private String date;
    private String prix_total;

    public Facture() {
    }

    public Facture(int id_facture, String date, String prix_total) {
        this.id_facture = id_facture;
        this.date = date;
        this.prix_total = prix_total;
    }

    public Facture(int id_facture) {
        this.id_facture = id_facture;
    }

    public int getId_facture() {
        return id_facture;
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(String prix_total) {
        this.prix_total = prix_total;
    }

    @Override
    public String toString() {
        return "Facture{" + "id_facture=" + id_facture + ", date=" + date + ", prix_total=" + prix_total + '}';
    }

  
   
}
