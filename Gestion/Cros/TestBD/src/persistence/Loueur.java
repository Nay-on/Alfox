/*
 * Projet  : eventSkyTracker
 * Fichier : User.java
 * Description : Classe interface de la table user
 * Cette table stocke les infos sur les utilisateurs connus du logiciel
 */

package persistence;

import java.sql.*;

public class Loueur {
    private String    nom;           // la clef primaire
    private String    prenom;
    private String    telephone;
    private String    mail;
    
    /**
     * Créer un nouvel objet persistant 
     * @param con
     * @param nom
     * @param prenom
     * @param telephone
     * @param mail
     * @return 
     * @ return  un loueur si le telephone est unique sinon null
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     *                      ou le telephone est deja dans la BD
     * 
     */
    static public Loueur create(Connection con, String nom, String prenom, String telephone, String mail)  throws Exception {
        Loueur loueur = new Loueur(nom, prenom, telephone, mail);
        
        String queryString =
         "insert into user (`numero`, 'date', `type`, `infos`) values ("
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
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString =
         "update user set "
                + " `numero` =" + Utils.toString(nom) + ","
                + " `date` =" + Utils.toString(prenom) + "," 
                + " `type` =" + Utils.toString(telephone) + ","  
                + " `infos` =" + Utils.toString(mail);
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }
    
    /**
     * Retourne un user trouve par son pseudo, saved is true
     * @param con
     * @param  nom le nom à trouver
     * @return Loueur loueur trouve par nom
     * @throws java.lang.Exception
     */
    public static Loueur getByNom(Connection con, String nom) throws Exception {
        String queryString = "select * from loueur where nom='" + nom + "'";
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
    
    private static Loueur creerParRequete(ResultSet result) throws Exception {
            String    lNom  = result.getString("nom");
            String    lPrenom = result.getString("prenom");
            String    lTelephone = result.getString("telephone");
            String    lMail = result.getString("mail");
            return    new Loueur(lNom,lPrenom, lTelephone, lMail);
    }
    
    /**
     * Cree et initialise completement Loueur
     */
    private Loueur(String numero, String date, String type, String infos) {
        this.nom = numero;
        this.prenom = date;
        this.telephone = type;
        this.mail = infos;
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
        return  " numero =  " + nom + "\t" +
                " date = " + Utils.toString(prenom) + 
                " type = " + Utils.toString(telephone) + 
                " infos = " + Utils.toString(mail)
                + " ";
    }
}