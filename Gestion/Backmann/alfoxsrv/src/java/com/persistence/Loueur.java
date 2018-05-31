/*
 * Projet  : Alfox
 * Fichier : User.java
 * Description : Classe interface de la table loueur
 * Cette table stocke les infos sur les loueurs connus du logiciel
 */

package com.persistence;

import java.sql.*;

public class Loueur {
    private String    nom;              // non null
    private String    prenom;           // non null
    private String    telephone;        // non null
    private String    mail;             // non null, unique
    
    /**
     * Créer un nouvel objet persistant 
     * @param con
     * @param nom
     * @param prenom
     * @param telephone
     * @param mail
     * @return 
     * @ return retourne un loueur si le mail est unique sinon null
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     *                      ou le mail est deja dans la BD
     * 
     */
    static public Loueur create(Connection con, String nom, String prenom, String telephone, String mail)  throws Exception {
        Loueur loueur = new Loueur(nom, prenom, telephone, mail);
        
        String queryString =
         "insert into loueur (Nom, Prenom, Telephone, Mail)"
            + " values ("
                + Utils.toString(nom) + ", " 
                + Utils.toString(prenom) + ", " 
                + Utils.toString(telephone) + ", " 
                + Utils.toString(mail)
            + ")";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
        return loueur;
    }
    
    /**
     * update de l'objet loueur dans la ConnexionMySQL
     * @param con
     * @throws Exception impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString =
         "update loueur set "
                + " Nom =" + Utils.toString(nom) + ","
                + " Prenom =" + Utils.toString(prenom) + "," 
                + " Telephone =" + Utils.toString(telephone) + ","  
                + " Mail =" + Utils.toString(mail)
                + " where Nom ='" + nom + "' and Prenom='" + prenom + "';";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }
    
    /**
     * Retourne un loueur trouve par son nom et prénom, saved is true
     * @param con
     * @param  nom nom du loueur recherché
     * @param  prenom prénom du loueur recherché
     * @return loueur trouvé par nom et prénom
     * @throws java.lang.Exception
     */
    public static Loueur getByNom(Connection con, String nom, String prenom) throws Exception {
        String queryString = "select * from loueur"
            + " where Nom='" + nom + "'"
            + " and Prenom='" + prenom + "';";
        Statement lStat = con.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        // y en a t'il au moins un ?
        if (lResult.next()) {
            return creerParRequete(lResult);
        }
        else
            return null;
    }
    
    /**
     * suppression de l'objet loueur dans la BD
     * @param con
     * @return 
     * @throws SQLException impossible d'accéder à la ConnexionMySQL
     */
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from loueur"
            + " where Nom='" + nom + "'"
            + " and Prenom='" + prenom + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString);
        return true;
    }
    
    private static Loueur creerParRequete(ResultSet result) throws Exception {
            String    lNom  = result.getString("Nom");
            String    lPrenom = result.getString("Prenom");
            String    lTelephone = result.getString("Telephone");
            String    lMail = result.getString("Mail");
            return    new Loueur(lNom,lPrenom, lTelephone, lMail);
    }
    
    /**
     * Cree et initialise completement Loueur
     */
    private Loueur(String nom, String prenom, String telephone, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.mail = mail;
    }
    
    // --------------------- les assesseurs ----------------------------
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public String getTelephone() {
        return telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setTelephone(String telephone) throws Exception {
        this.telephone = telephone;
    }

    public void setMail(String mail) throws Exception {
        this.mail = mail;
    }
    
    /**
     * toString() operator overload
     * @return   the result string
     */
    @Override
    public String toString() {
        return  " Nom =  " + nom + "\t" +
                " Prenom = " + Utils.toString(prenom) + 
                " Telephone = " + Utils.toString(telephone) + 
                " Mail = " + Utils.toString(mail)
                + " ";
    }
}