/*
 * Projet  : eventSkyTracker
 * Fichier : User.java
 * Description : Classe interface de la table user
 * Cette table stocke les infos sur les utilisateurs connus du logiciel
 */

package persistence;

import java.sql.*;

public class ZoneLimite {
    private int ID;           // la clef primaire
    private String nom;
    
    /**
     * Créer un nouvel objet persistant 
     * @param con
     * @param nom
     * @return 
     * @ return retourne un loueur si le telephone est unique sinon null
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     *                      ou le telephone est deja dans la BD
     * 
     */
    static public ZoneLimite create(Connection con, String nom)  throws Exception {
        ZoneLimite zoneLimite = new ZoneLimite(nom);
        
        String queryString =
         "insert into zoneLimite (Nom) values ("
                + Utils.toString(nom)
          + ")";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
        zoneLimite.ID = ZoneLimite.getByNom(con, nom).getID();
        return zoneLimite;
    }
    
    /**
     * suppression de l'objet user dans la BD
     * @param con
     * @return 
     * @throws SQLException    impossible d'accéder à la ConnexionMySQL
     */
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from zoneLimite where Nom='" + nom + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString);
        return true;
    }
    
    /**
     * update de l'objet loueur dans la ConnexionMySQL
     * @param con
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString =
         "update zoneLimite set "
                + " Nom =" + Utils.toString(nom)
                + " where ID ='" + ID + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }
    
    /**
     * Retourne un user trouve par son pseudo, saved is true
     * @param con
     * @param  nom nom de la zone recherchee
     * @return ZoneLimite trouvee par nom
     * @throws java.lang.Exception
     */
    public static ZoneLimite getByNom(Connection con, String nom) throws Exception {
        String queryString = "select * from zoneLimite where Nom='" + nom + "';";
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
    
    private static ZoneLimite creerParRequete(ResultSet result) throws Exception {
        int lID = result.getInt("ID");
        String lNom  = result.getString("Nom");
        return new ZoneLimite(lID, lNom);
    }
    
    /**
     * Cree et initialise completement Loueur
     */
    private ZoneLimite(String nom) {
        this.nom = nom;
    }
    
    /**
     * Cree et initialise completement Loueur
     */
    private ZoneLimite(int id, String nom) {
        this.ID = id;
        this.nom = nom;
    }
    
    // --------------------- les assesseurs ----------------------------
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    

    public int getID() {
        return ID;
    }
    
    /**
     * toString() operator overload
     * @return   the result string
     */
    @Override
    public String toString() {
        return  " Nom =  " + nom + "\t"
                + " ";
    }
}