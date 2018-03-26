/*
 * Projet  : eventSkyTracker
 * Fichier : User.java
 * Description : Classe interface de la table user
 * Cette table stocke les infos sur les utilisateurs connus du logiciel
 */

package persistence;

import java.sql.*;
import java.util.ArrayList;

public class Position {
    private int    ordre;           // la clef primaire
    private float  latitude;
    private float  longitude;
    
    /**
     * Créer un nouvel objet persistant 
     * @param con
     * @param ordre
     * @param zoneLimiteID
     * @param latitude
     * @param longitude
     * @return 
     * @ return retourne un loueur si le telephone est unique sinon null
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     *                      ou le telephone est deja dans la BD
     * 
     */
    static public Position create(Connection con, int ordre, int zoneLimiteID, float latitude, float longitude)  throws Exception {
        Position position = new Position(ordre, latitude, longitude);
        
        String queryString =
         "insert into position (Ordre, ZoneLimiteID, Latitude, Longitude) values ("
                + Utils.toString(ordre) + ", " 
                + Utils.toString(zoneLimiteID) + ", " 
                + Utils.toString(latitude) + ", " 
                + Utils.toString(longitude)
          + ")";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
        return position;
    }
    
    /**
     * suppression de l'objet user dans la BD
     * @param con
     * @return 
     * @throws SQLException    impossible d'accéder à la ConnexionMySQL
     */
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from position where Ordre='" + ordre + "'";
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
         "update position set "
                + " Ordre =" + Utils.toString(ordre) + ","
                + " Latitude =" + Utils.toString(latitude) + "," 
                + " Longitude =" + Utils.toString(longitude)
                + " where Ordre ='" + ordre + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }
    
    /**
     * Retourne un user trouve par son pseudo, saved is true
     * @param con
     * @param  ordre ordre du point recherché
     * @return Position trouvee par ordre
     * @throws java.lang.Exception
     */
    public static ArrayList<Position> getByZone(Connection con, int zone) throws Exception {
        String queryString = "select * from position where ZoneLimiteID='" + zone + "' order by Ordre";
        Statement lStat = con.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        ArrayList<Position> lstPos = new  ArrayList<Position>();
        while (lResult.next()) {
            lstPos.add(creerParRequete(lResult));
        }
        return lstPos;
    }
    
    private static Position creerParRequete(ResultSet result) throws Exception {
            int       lOrdre  = result.getInt("Ordre");
            float     lLatitude = result.getFloat("Latitude");
            float     lLongitude = result.getFloat("Longitude");
            return    new Position(lOrdre, lLatitude, lLongitude);
    }
    
    /**
     * Cree et initialise completement Loueur
     */
    private Position(int ordre, float latitude, float longitude) {
        this.ordre = ordre;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    // --------------------- les assesseurs ----------------------------
    public int getOrdre() {
        return ordre;
    }

    public float getLatitude() {
        return latitude;
    }
    public float getLongitude() {
        return longitude;
    }

    public void setOrdre(int ordre) throws Exception {
        this.ordre = ordre;
    }

    public void setLatitude(float latitude) throws Exception {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) throws Exception {
        this.longitude = longitude;
    }
    
    /**
     * toString() operator overload
     * @return   the result string
     */
    @Override
    public String toString() {
        return  " Ordre =  " + ordre + "\t" +
                " Latitude = " + Utils.toString(latitude) + 
                " Longitude = " + Utils.toString(longitude)
                + " ";
    }
}