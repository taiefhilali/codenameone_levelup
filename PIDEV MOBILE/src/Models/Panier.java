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
public class Panier {
    
    //var
    private float id_panier;
    private User client;

    //constructeur

    public Panier() {
    }
    
    public Panier(float id_panier) {
        this.id_panier = id_panier;
    }
    
    public Panier(float id_panier, User client) {
        this.id_panier = id_panier;
        this.client = client;
    }

    public float getId_panier() {
        return id_panier;
    }

    public void setId_panier(float id_panier) {
        this.id_panier = id_panier;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }



    @Override
    public String toString() {
        return "Panier{" + "id_panier=" + id_panier + ", client=" + client + '}';
    }
    
    
    
    
    
}
