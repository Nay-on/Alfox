/*
 * Projet  : Alfox
 * Fichier : User.java
 * Description : Classe interface de la table position
 * Cette table stocke les points définissant chaque zone
 */

package persistence;

import java.sql.*;
import java.util.ArrayList;

public class Position {
    private int    ordre;           // non null
    private double  latitude;       // non null
    private double  longitude;      // non null
        
    /**
     * Créer un nouvel objet persistant 
     * @param con
     * @param ordre
     * @param zoneLimiteID
     * @param latitude
     * @param longitude
     * @return 
     * @ return retourne une position
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     * 
     */
    static public Position create(Connection con, int ordre, int zoneLimiteID, double latitude, double longitude)  throws Exception {
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
     * suppression de l'objet position dans la BD
     * @param con
     * @return 
     * @throws SQLException impossible d'accéder à la ConnexionMySQL
     */
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from position where Ordre='" + ordre + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString);
        return true;
    }
    
    /**
     * update de l'objet position dans la ConnexionMySQL
     * @param con
     * @throws Exception impossible d'accéder à la ConnexionMySQL
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
     * Retourne une collection de positions trouvées par nom de zone, saved is true
     * @param con
     * @param zone
     * @return collection de positions
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
            double    lLatitude = result.getDouble("Latitude");
            double    lLongitude = result.getDouble("Longitude");
            return    new Position(lOrdre, lLatitude, lLongitude);
    }
    
    /**
     * Cree et initialise completement Position
     */
    private Position(int ordre, double latitude, double longitude) {
        this.ordre = ordre;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    // --------------------- les assesseurs ----------------------------
    public int getOrdre() {
        return ordre;
    }

    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }

    public void setOrdre(int ordre) throws Exception {
        this.ordre = ordre;
    }

    public void setLatitude(double latitude) throws Exception {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) throws Exception {
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