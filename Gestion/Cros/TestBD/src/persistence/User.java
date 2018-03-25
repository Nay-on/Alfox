/*
 * Projet  : eventSkyTracker
 * Fichier : User.java
 * Description : Classe interface de la table user
 * Cette table stocke les infos sur les utilisateurs connus du logiciel
 */

package persistence;

import java.sql.*;

public class User {
    private String    role;           // la clef primaire
    private String    mdp;
    private String    mail;            // son mail (unique)
    
    /**
     * Créer un nouvel objet persistant 
     * @param con
     * @param role
     * @param mdp
     * @param mail
     * @return 
     * @ return  un user si le pseudo est unique sinon null
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     *                      ou le pseudo est deja dans la BD
     * 
     */
    static public User create(Connection con, String role, String mdp, String mail)  throws Exception {
        User user = new User(role, mdp, mail);
        
        String queryString =
         "insert into user ('Role', 'Mdp', 'Mail') values ("
                + Utils.toString(role) + ", " 
                + Utils.toString(mdp) + ", " 
                + Utils.toString(mail)
          + ")";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
        return user;
    }
    
    /**
     * suppression de l'objet user dans la BD
     * @param con
     * @return 
     * @throws SQLException    impossible d'accéder à la ConnexionMySQL
     */
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from user where Mdp='" + mdp + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString);
        return true;
    }
    
    /**
     * update de l'objet user dans la ConnexionMySQL
     * @param con
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString =
         "update user set "
                + " 'Role' =" + Utils.toString(role) + "," 
                + " 'Mdp' =" + Utils.toString(mdp) + ","  
                + " 'Mail' =" + Utils.toString(mail);
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }
    
    /**
     * Retourne un user trouve par son pseudo, saved is true
     * @param con
     * @param  mdp le pseudo à trouver
     * @return User user trouve par pseudo
     * @throws java.lang.Exception
     */
    public static User getByMotDePasse(Connection con, String mdp) throws Exception {
        String queryString = "select * from user where Mdp='" + mdp + "'";
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
    
    private static User creerParRequete(ResultSet result) throws Exception {
            String    lRole  = result.getString("Role");
            String    lMdp = result.getString("Mdp");
            String    lMail = result.getString("Mail");
            return    new User(lRole,lMdp,lMail);
    }
    
    /**
     * Cree et initialise completement User
     */
    private User(String role, String mdp, String mail) {
        this.role = role;
        this.mdp = mdp;
        this.mail = mail;
    }
    
    // --------------------- les assesseurs ----------------------------
    public String getRole() {
        return role;
    }

    public String getMdp() {
        return mdp;
    }

    public String getMail() {
        return mail;
    }
    
    public void setRole(String role) throws Exception {
        this.role = role;
    }
    
    public void setMail(String mail) throws Exception {
        this.mail = mail;
    }

    public void setMdp(String mdp) throws Exception {
        this.mdp = mdp;
    }
    
    /**
     * toString() operator overload
     * @return   the result string
     */
    @Override
    public String toString() {
        return  " Role =  " + role + "\t" +
                " Mdp = " + Utils.toString(mdp) + 
                " Mail = " + Utils.toString(mail)
                + " ";
    }
}