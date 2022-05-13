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
public class Comment {
  private int idc;
    private String label;
    private String contenu;
private int  resp;
    public Comment() {
    }

    public Comment(int idc) {
        this.idc = idc;
    }

    public Comment(int idc, String label, String contenu, int resp) {
        this.idc = idc;
        this.label = label;
        this.contenu = contenu;
        this.resp = resp;
    }

    public int getResp() {
        return resp;
    }

    public void setResp(int resp) {
        this.resp = resp;
    }

   

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Comment{" + "idc=" + idc + ", label=" + label + ", contenu=" + contenu + ", resp=" + resp + '}';
    }

   
       
}
