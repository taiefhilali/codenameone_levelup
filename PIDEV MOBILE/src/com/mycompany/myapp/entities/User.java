/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


public class User {

    //var
    private int id_user;
    private String email;
    private String password;
    private String role;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String dns;

    public User(String email, String password, String role, String nom, String prenom) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
    }
    private String image;

    //constructeur sans paramétres
    public User() {
    }

    //constructeur all
    public User(int id_user, String email, String password, String role, String nom, String prenom, String adresse, String tel, String dns, String image) {
        this.id_user = id_user;
        this.email = email;
        this.password = password;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.dns = dns;
        this.image = image;
    }

    public User(int id_user, String email, String password, String role, String nom, String prenom, String adresse, String tel, String dns) {
        this.id_user = id_user;
        this.email = email;
        this.password = password;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.dns = dns;
    }
    //constructeur id

    public User(int id_user) {
        this.id_user = id_user;
    }

    public User(String email, String password, String role, String nom, String prenom, String adresse, String tel, String dns, String image) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.dns = dns;
        this.image = image;
    }

    public User(int id_user, String email) {
        this.id_user = id_user;
        this.email = email;
    }
     public User(String email) {
        this.email = email;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //constructeur sans id 
    public User(String email, String password, String role, String nom, String prenom, String adresse, String tel, String dns) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.dns = dns;
    }

    public User(String email, String password, String role, String nom, String prenom, String dns) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.dns = dns;

    }

    public User(int id_user, String email, String role, String nom, String prenom) {
        this.id_user = id_user;
        this.email = email;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
    }

    //getter et setter
    public int getId() {
        return id_user;
    }

    public void setId(int id) {
        this.id_user = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

//    public String encrypt(String password) {
//        String Newstr = "";
//
//        for (int i = 0; i < password.length(); i++) {
//            char ch = Character.toLowerCase(password.charAt(i));
//            switch (ch) {
//                case 'a':
//                    Newstr = Newstr + "{";
//                    break;
//                case 'b':
//                    Newstr = Newstr + "}";
//                    break;
//                case 'c':
//                    Newstr = Newstr + "#";
//                    break;
//                case 'd':
//                    Newstr = Newstr + "~";
//                    break;
//                case 'e':
//                    Newstr = Newstr + "+";
//                    break;
//                case 'f':
//                    Newstr = Newstr + "-";
//                    break;
//                case 'g':
//                    Newstr = Newstr + "*";
//                    break;
//                case 'h':
//                    Newstr = Newstr + "@";
//                    break;
//                case 'i':
//                    Newstr = Newstr + "/";
//                    break;
//                case 'j':
//                    Newstr = Newstr + "\\";
//                    break;
//                case 'k':
//                    Newstr = Newstr + "?";
//                    break;
//                case 'l':
//                    Newstr = Newstr + "$";
//                    break;
//                case 'm':
//                    Newstr = Newstr + "!";
//                    break;
//                case 'n':
//                    Newstr = Newstr + "^";
//                    break;
//                case 'o':
//                    Newstr = Newstr + "(";
//                    break;
//                case 'p':
//                    Newstr = Newstr + ")";
//                    break;
//                case 'q':
//                    Newstr = Newstr + "<";
//                    break;
//                case 'r':
//                    Newstr = Newstr + ">";
//                    break;
//                case 's':
//                    Newstr = Newstr + "=";
//                    break;
//                case 't':
//                    Newstr = Newstr + ";";
//                    break;
//                case 'u':
//                    Newstr = Newstr + ",";
//                    break;
//                case 'v':
//                    Newstr = Newstr + "_";
//                    break;
//                case 'w':
//                    Newstr = Newstr + "[";
//                    break;
//                case 'x':
//                    Newstr = Newstr + "]";
//                    break;
//                case 'y':
//                    Newstr = Newstr + ":";
//                    break;
//                case 'z':
//                    Newstr = Newstr + "\"";
//                    break;
//                case ' ':
//                    Newstr = Newstr + " ";
//                    break;
//                case '.':
//                    Newstr = Newstr + '3';
//                    break;
//                case ',':
//                    Newstr = Newstr + "1";
//                    break;
//                case '(':
//                    Newstr = Newstr + '4';
//                    break;
//                case '\"':
//                    Newstr = Newstr + '5';
//                    break;
//                case ')':
//                    Newstr = Newstr + "7";
//                    break;
//                case '?':
//                    Newstr = Newstr + "2";
//                    break;
//                case '!':
//                    Newstr = Newstr + "8";
//                    break;
//                case '-':
//                    Newstr = Newstr + "6";
//                    break;
//                case '%':
//                    Newstr = Newstr + "9";
//                    break;
//                case '1':
//                    Newstr = Newstr + "r";
//                    break;
//                case '2':
//                    Newstr = Newstr + "k";
//                    break;
//                case '3':
//                    Newstr = Newstr + "b";
//                    break;
//                case '4':
//                    Newstr = Newstr + "e";
//                    break;
//                case '5':
//                    Newstr = Newstr + "q";
//                    break;
//                case '6':
//                    Newstr = Newstr + "h";
//                    break;
//                case '7':
//                    Newstr = Newstr + "u";
//                    break;
//                case '8':
//                    Newstr = Newstr + "y";
//                    break;
//                case '9':
//                    Newstr = Newstr + "w";
//                    break;
//                case '0':
//                    Newstr = Newstr + "z";
//                    break;
//                default:
//                    Newstr = Newstr + "0";
//                    break;
//            }
//        }
//
//        return Newstr;
//    }

    @Override
    public String toString() {
        return "User {" + "id_user=" + id_user + ", email=" + email + ", password=" + password + ", role=" + role + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", tel=" + tel + ", dns=" + dns + '}';
    }

}