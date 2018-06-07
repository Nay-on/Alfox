/*
 * Projet  : Alfox
 * Fichier : User.java
 * Description : Classe interface de la table donneesHisto
 * Cette table stocke les données enregistrées par la SD
 */

package com.persistence;

import java.sql.*;
import java.util.ArrayList;

public class DonneesHisto {
    private String    mode;
    private Timestamp datation; 
    private int       vitesse; 
    private int       regime; 
    private int       consommation;
    private int       vitesseMax;
    private int       regimeMax;
    private int       consoMax;
    private int       nbDefauts;
    private int       defaut1;
    private int       defaut2; 
    private int       defaut3;
    private int       defaut4; 
    private double    latitudeGPS;
    private double    longitudeGPS;
    private long      distanceParcourue;
    private int       vehiculeID; 
    
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
     * @param latitudeGPS
     * @param longitudeGPS
     * @param distanceParcourue
     * @param vehiculeID
     * @return 
     * @ return retourne une donneesHisto si la date est unique sinon null
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     *                      ou la date est deja dans la BD
     * 
     */
    static public DonneesHisto create(Connection con, String mode, Timestamp datation,
            int vitesse, int regime, int consommation, int vitesseMax, int regimeMax,
                int consoMax, int nbDefauts, int defaut1, int defaut2, int defaut3, 
                    int defaut4, double latitudeGPS, double longitudeGPS,
                        long distanceParcourue, int vehiculeID)  throws Exception {
        DonneesHisto donneesHisto = new DonneesHisto(mode, datation, vitesse, regime, 
            consommation, vitesseMax, regimeMax, consoMax, nbDefauts, defaut1,
                defaut2, defaut3, defaut4, latitudeGPS, longitudeGPS, distanceParcourue, vehiculeID);
        
        String queryString =
         "insert into donneesHisto (Mode, Datation, Vitesse, Regime, "
            + "Consommation, VitesseMax, RegimeMax, ConsoMax, "
            + "NbDefauts, Defaut1, Defaut2, Defaut3, Defaut4, "
            + "LatitudeGPS, LongitudeGPS, DistanceParcourue, VehiculeID) "
            + "values ("
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
                + Utils.toString(latitudeGPS) + ", "
                + Utils.toString(longitudeGPS) + ", " 
                + Utils.toString(distanceParcourue) + ", "
                + Utils.toString(vehiculeID)
            + ")";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
        return donneesHisto;
    }
    
    /**
     * suppression de l'objet donneesHisto dans la BD
     * @param con
     * @return 
     * @throws SQLException    impossible d'accéder à la ConnexionMySQL
     */
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from donneesHisto"
                + " where Datation='" + datation + "'"
                + " and VehiculeID = '" + vehiculeID + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString);
        return true;
    }
    
    /**
     * update de l'objet donneesHisto dans la ConnexionMySQL
     * @param con
     * @throws Exception impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString =
         "update donneesHisto set "
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
                + " LatitudeGPS =" + Utils.toString(latitudeGPS) + ","
                + " LongitudeGPS =" + Utils.toString(longitudeGPS) + "," 
                + " DistanceParcourue =" + Utils.toString(distanceParcourue) + "," 
                + " VehiculeID =" + Utils.toString(vehiculeID)
                + " where Datation ='" + datation + "'"
                + " and VehiculeID ='" + vehiculeID + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }
    
    public static DonneesHisto getLastByImmatriculation(Connection con,
            String immatriculation) throws Exception {
        String queryString = "select * from donneesHisto,vehicule"
                + " where Immatriculation = '" + immatriculation + "'"
                + " and vehicule.ID = donneesHisto.VehiculeID"
                + " order by Datation desc limit 1";
        Statement lStat = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        // y en a t'il au moins un ?
        if (lResult.next()) {
            return creerParRequete(lResult);
        } else {
            return null;
        }
    }

    public static ArrayList<DonneesHisto> getByDate(Connection con,
            String immatriculation, String dateDonnees) throws Exception {
        String queryString = "select * from donneesHisto,vehicule"
                + " where Immatriculation = '" + immatriculation + "'"
                + " and vehicule.ID = donneesHisto.VehiculeID"
                + " and Datation between '" + dateDonnees + " 00:00:00'"
                + " and '" + dateDonnees + " 23:59:59'"
                + " order by Datation desc";

        Statement lStat = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        ArrayList<DonneesHisto> lstDonneesHisto = new ArrayList<>();
        // y en a t'il au moins un ?
        while (lResult.next()) {
            lstDonneesHisto.add(creerParRequete(lResult));
        }
        return lstDonneesHisto;
    }

    public static ArrayList<DonneesHisto> getDonneesVehicule(Connection con,
            String immatriculation) throws Exception {
        String queryString = "select * from donneesHisto,vehicule"
                + " where Immatriculation = '" + immatriculation + "'"
                + " and vehicule.ID = donneesHisto.VehiculeID"
                + " order by Datation desc";
        Statement lStat = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        ArrayList<DonneesHisto> lstDonneesHisto = new ArrayList<>();
        // y en a t'il au moins un ?
        while (lResult.next()) {
            lstDonneesHisto.add(creerParRequete(lResult));
        }
        return lstDonneesHisto;
    }
    
    private static DonneesHisto creerParRequete(ResultSet result) throws Exception {
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
            double    lLatitudeGPS = result.getDouble("LatitudeGPS");
            double    lLongitudeGPS = result.getDouble("LongitudeGPS");
            long      lDistanceParcourue = result.getLong("DistanceParcourue");
            int       lVehiculeID = result.getInt("VehiculeID");
            return    new DonneesHisto(lMode, lDatation, lVitesse, lRegime, lConsommation, 
                lVitesseMax, lRegimeMax, lConsoMax, lNbDefauts, lDefaut1, lDefaut2, 
                    lDefaut3, lDefaut4, lLatitudeGPS, lLongitudeGPS, lDistanceParcourue, lVehiculeID);
    }
    
    /**
     * Cree et initialise completement DonneesHisto
     */
    private DonneesHisto(String mode, Timestamp datation, int vitesse, int regime, 
            int consommation, int vitesseMax, int regimeMax, int consoMax, 
            int nbDefauts, int defaut1, int defaut2, int defaut3, int defaut4, 
            double latitudeGPS, double longitudeGPS, long distanceParcourue, int vehiculeID) {
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
        this.latitudeGPS = latitudeGPS;
        this.longitudeGPS = longitudeGPS;
        this.distanceParcourue = distanceParcourue;
        this.vehiculeID = vehiculeID;
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
        // Conversion de la conso de dL en L
        return consommation/10;
    }

    public int getVitesseMax() {
        return vitesseMax;
    }
    public int getRegimeMax() {
        return regimeMax;
    }
    public int getConsoMax() {
        // Conversion de la conso de dL en L
        return consoMax/10;
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

    public double getLatitudeGPS() {
        return latitudeGPS;
    }
    
    public double getLongitudeGPS() {
        return longitudeGPS;
    }

    public long getDistanceParcourue() {
        return distanceParcourue;
    }
    
    public int getVehiculeID() {
        return vehiculeID;
    }

    /**
     * toString() operator overload
     * @return  the result string
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
                " LatitudeGPS = " + Utils.toString(latitudeGPS) + 
                " LongitudeGPS = " + Utils.toString(longitudeGPS) +
                " DistanceParcourue = " + Utils.toString(distanceParcourue) +
                " VehiculeID = " + Utils.toString(vehiculeID);
    }    
}