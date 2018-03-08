/*
 * Projet  : eventSkyTracker
 * Fichier : User.java
 * Description : Classe interface de la table user
 * Cette table stocke les infos sur les utilisateurs connus du logiciel
 */

package persistence;

import java.sql.*;

public class Contrat {
    private String    numero;           // la clef primaire
    private String    date;
    private String    type;
    private String    infos;
    
    /**
     * Créer un nouvel objet persistant 
     * @param con
     * @param numero
     * @param date
     * @param type
     * @param infos
     * @return 
     * @ return  un contrat si le numero est unique sinon null
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     *                      ou le numero est deja dans la BD
     * 
     */
    static public Contrat create(Connection con, String numero, String date, String type, String infos)  throws Exception {
        Contrat contrat = new Contrat(numero, date, type, infos);
        
        String queryString =
         "insert into user (`numero`, 'date', `type`, `infos`) values ("
                + Utils.toString(numero) + ", " 
                + Utils.toString(date) + ", " 
                + Utils.toString(type) + ", " 
                + Utils.toString(infos)
          + ")";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
        return contrat;
    }
    
    /**
     * update de l'objet contrat dans la ConnexionMySQL
     * @param con
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString =
         "update user set "
                + " `numero` =" + Utils.toString(numero) + ","
                + " `date` =" + Utils.toString(date) + "," 
                + " `type` =" + Utils.toString(type) + ","  
                + " `infos` =" + Utils.toString(infos);
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }
    
    /**
     * Retourne un user trouve par son pseudo, saved is true
     * @param con
     * @param  numero le numero à trouver
     * @return Contrat contrat trouve par numero
     * @throws java.lang.Exception
     */
    public static Contrat getByNumero(Connection con, String numero) throws Exception {
        String queryString = "select * from contrat where numero='" + numero + "'";
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
    
    private static Contrat creerParRequete(ResultSet result) throws Exception {
            String    lNumero  = result.getString("numero");
            String    lDate = result.getString("date");
            String    lType = result.getString("type");
            String    lInfos = result.getString("infos");
            return    new Contrat(lNumero,lDate, lType, lInfos);
    }
    
    /**
     * Cree et initialise completement Contrat
     */
    private Contrat(String numero, String date, String type, String infos) {
        this.numero = numero;
        this.date = date;
        this.type = type;
        this.infos = infos;
    }
    
    // --------------------- les assesseurs ----------------------------
    public String getNumero() {
        return numero;
    }

    public String getDate() {
        return date;
    }
    public String getType() {
        return type;
    }

    public String getInfos() {
        return infos;
    }

    public void setType(String type) throws Exception {
        this.type = type;
    }

    public void setInfos(String infos) throws Exception {
        this.infos = infos;
    }
    
    /**
     * toString() operator overload
     * @return   the result string
     */
    @Override
    public String toString() {
        return  " numero =  " + numero + "\t" +
                " date = " + Utils.toString(date) + 
                " type = " + Utils.toString(type) + 
                " infos = " + Utils.toString(infos)
                + " ";
    }
}