/*
 * Projet  : Alfox
 * Fichier : User.java
 * Description : Classe interface de la table donneesTR
 * Cette table stocke les données TR
 */

package com.persistence;

import java.sql.*;

public class DonneesTR {
    private String    mode;             // non null
    private Timestamp datation;         // non null
    private int       vitesse;          // non null
    private int       regime;           // non null
    private int       consommation;     // non null
    private int       vitesseMax;       // non null
    private int       regimeMax;        // non null
    private int       consoMax;         // non null
    private int       nbDefauts;        // non null
    private int       defaut1;          // non null
    private int       defaut2;          // non null
    private int       defaut3;          // non null
    private int       defaut4;          // non null
    private double    latitude;         // non null
    private double    longitude;        // non null
    private long      distanceParcourue;// non null
    
    /**
     * Créer un nouvel objet persistant 
     * @param con
     * @param mode
     * @param datation
     * @param vitesse
     * @param regime
     * @param consommation
     * @param vitesseMax
     * @param regimeMax
     * @param consoMax
     * @param nbDefauts
     * @param defaut1
     * @param defaut2
     * @param defaut3
     * @param defaut4
     * @param latitude
     * @param longitude
     * @param distanceParcourue
     * @param vehiculeID
     * @return 
     * @ return retourne une donneesTR si la date est unique sinon null
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     *                      ou la date est deja dans la BD
     * 
     */
    static public DonneesTR create(Connection con, String mode, Timestamp datation,
            int vitesse, int regime, int consommation, int vitesseMax, int regimeMax,
                int consoMax, int nbDefauts, int defaut1, int defaut2, int defaut3, 
                    int defaut4, double latitude, double longitude,
                        long distanceParcourue, int vehiculeID)  throws Exception {
        DonneesTR donneesTR = new DonneesTR(mode, datation, vitesse, regime, 
            consommation, vitesseMax, regimeMax, consoMax, nbDefauts, defaut1,
                defaut2, defaut3, defaut4, latitude, longitude, distanceParcourue);
        
        String queryString =
         "insert into donneesTR (Mode, Datation, Vitesse, Regime, Consommation, VitesseMax, RegimeMax, ConsoMax, NbDefauts, Defaut1, Defaut2, Defaut3, Defaut4, Latitude, Longitude, DistanceParcourue, VehiculeID) values ("
                + Utils.toString(mode) + ", " 
                + Utils.toString(datation) + ", " 
                + Utils.toString(vitesse) + ", "
                + Utils.toString(regime) + ", " 
                + Utils.toString(consommation) + ", " 
                + Utils.toString(vitesseMax) + ", " 
                + Utils.toString(regimeMax) + ", " 
                + Utils.toString(consoMax) + ", "
                + Utils.toString(nbDefauts) + ", " 
                + Utils.toString(defaut1) + ", " 
                + Utils.toString(defaut2) + ", " 
                + Utils.toString(defaut3) + ", " 
                + Utils.toString(defaut4) + ", " 
                + Utils.toString(latitude) + ", "
                + Utils.toString(longitude) + ", " 
                + Utils.toString(distanceParcourue) + ", " 
                + Utils.toString(vehiculeID)
          + ")";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
        return donneesTR;
    }
    
    /**
     * suppression de l'objet donneesTR dans la BD
     * @param con
     * @return 
     * @throws SQLException impossible d'accéder à la ConnexionMySQL
     */
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from donneesTR where Datation='" + datation + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString);
        return true;
    }
    
    /**
     * update de l'objet donneesTR dans la ConnexionMySQL
     * @param con
     * @throws Exception impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString =
         "update donneesTR set "
                + " Mode =" + Utils.toString(mode) + ","
                + " Datation =" + Utils.toString(datation) + "," 
                + " Vitesse =" + Utils.toString(vitesse) + ","
                + " Regime =" + Utils.toString(regime) + ","
                + " Consommation =" + Utils.toString(consommation) + "," 
                + " VitesseMax =" + Utils.toString(vitesseMax) + ","
                + " RegimeMax =" + Utils.toString(regimeMax) + ","
                + " ConsoMax =" + Utils.toString(consoMax) + "," 
                + " NbDefauts =" + Utils.toString(nbDefauts) + ","
                + " Defaut1 =" + Utils.toString(defaut1) + ","
                + " Defaut2 =" + Utils.toString(defaut2) + ","
                + " Defaut3 =" + Utils.toString(defaut3) + ","
                + " Defaut4 =" + Utils.toString(defaut4) + ","
                + " Latitude =" + Utils.toString(latitude) + ","
                + " Longitude =" + Utils.toString(longitude) + "," 
                + " DistanceParcourue =" + Utils.toString(distanceParcourue)
                + " where Datation ='" + datation + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }
    
    /**
     * Retourne une donneesTR trouve par sa date, saved is true
     * @param con
     * @param  datation date de donneesTR a trouver
     * @return donneesTR trouv" par date
     * @throws java.lang.Exception
     */
    public static DonneesTR getByDatation(Connection con, String datation) throws Exception {
        String queryString = "select * from donneesTR where Datation='" + datation + "'";
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
    
    private static DonneesTR creerParRequete(ResultSet result) throws Exception {
            String    lMode  = result.getString("Mode");
            Timestamp lDatation = result.getTimestamp("Datation");
            int       lVitesse = result.getInt("Vitesse");
            int       lRegime = result.getInt("Regime");
            int       lConsommation  = result.getInt("Consommation");
            int       lVitesseMax = result.getInt("VitesseMax");
            int       lRegimeMax = result.getInt("RegimeMax");
            int       lConsoMax = result.getInt("ConsoMax");
            int       lNbDefauts  = result.getInt("NbDefauts");
            int       lDefaut1 = result.getInt("Defaut1");
            int       lDefaut2 = result.getInt("Defaut2");
            int       lDefaut3 = result.getInt("Defaut3");
            int       lDefaut4 = result.getInt("Defaut4");
            double    lLatitude = result.getDouble("Latitude");
            double    lLongitude = result.getDouble("Longitude");
            long      lDistanceParcourue = result.getLong("DistanceParcourue");
            return    new DonneesTR(lMode, lDatation, lVitesse, lRegime, lConsommation, 
                lVitesseMax, lRegimeMax, lConsoMax, lNbDefauts, lDefaut1, lDefaut2, 
                    lDefaut3, lDefaut4, lLatitude, lLongitude, lDistanceParcourue);
    }
    
    /**
     * Cree et initialise completement DonneesTR
     */
    private DonneesTR(String mode, Timestamp datation,
            int vitesse, int regime, int consommation, int vitesseMax, int regimeMax,
                int consoMax, int nbDefauts, int defaut1, int defaut2, int defaut3, 
                    int defaut4, double latitude, double longitude, long distanceParcourue) {
        this.mode = mode;
        this.datation = datation;
        this.vitesse = vitesse;
        this.regime = regime;
        this.consommation = consommation;
        this.vitesseMax = vitesseMax;
        this.regimeMax = regimeMax;
        this.consoMax = consoMax;
        this.nbDefauts = nbDefauts;
        this.defaut1 = defaut1;
        this.defaut2 = defaut2;
        this.defaut3 = defaut3;
        this.defaut4 = defaut4;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distanceParcourue = distanceParcourue;
    }
    
    // --------------------- les assesseurs ----------------------------
    public String getMode() {
        return mode;
    }

    public Timestamp getDatation() {
        return datation;
    }
    public int getVitesse() {
        return vitesse;
    }

    public int getRegime() {
        return regime;
    }
    
    public int getConsommation() {
        return consommation;
    }

    public int getVitesseMax() {
        return vitesseMax;
    }
    public int getRegimeMax() {
        return regimeMax;
    }
    public int getConsoMax() {
        return consoMax;
    }
    public int getNbDefauts() {
        return nbDefauts;
    }
    public int getDefaut1() {
        return defaut1;
    }
    public int getDefaut2() {
        return defaut2;
    }
    public int getDefaut3() {
        return defaut3;
    }
    public int getDefaut4() {
        return defaut4;
    }

    public double getLatitude() {
        return latitude;
    }
    
    public double getLongitude() {
        return longitude;
    }

    public long getDistanceParcourue() {
        return distanceParcourue;
    }

    /**
     * toString() operator overload
     * @return   the result string
     */
    @Override
    public String toString() {
        return  " Mode = " + Utils.toString(mode) + "\t" +
                " Datation = " + Utils.toString(datation) + 
                " Vitesse = " + Utils.toString(vitesse) + 
                " Regime = " + Utils.toString(regime) +
                " Consommation = " + Utils.toString(consommation) + 
                " VitesseMax = " + Utils.toString(vitesseMax) +
                " RegimeMax = " + Utils.toString(regimeMax) + 
                " ConsoMax = " + Utils.toString(consoMax) +
                " NbDefauts = " + Utils.toString(nbDefauts) + 
                " Defaut1 = " + Utils.toString(defaut1) +
                " Defaut2 = " + Utils.toString(defaut2) +
                " Defaut3 = " + Utils.toString(defaut3) +
                " Defaut4 = " + Utils.toString(defaut4) +
                " Latitude = " + Utils.toString(latitude) + 
                " Longitude = " + Utils.toString(longitude) +
                " DistanceParcourue = " + Utils.toString(distanceParcourue)
                + " ";
    }
}