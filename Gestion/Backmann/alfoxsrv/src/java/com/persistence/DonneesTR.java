/*
 * Projet  : Alfox
 * Fichier : User.java
 * Description : Classe interface de la table donneesTR
 * Cette table stocke les données TR obtenues par message SIGFOX
 *
 * Pb : l'enregistrement est réalisé en 2 temps :
 *      1. on recoit les datas du message
 *      2. on reçoit la position Sigfox Atlas de l'origine du message
 *      Mais on ne peut pas être sûr de l'ordre des réceptions entre 1 et 2.
 *      Il faut donc d'abord vérifier si l'enregistrement n'existe pas déjà
 *      avant de le créer ou de le mettre à jour si il existe déjà !
 *
 * Modification 31 Mai 2018 :
 *      remplacement du terme 'Device' et du champ 'device' par 'SigfoxID'
 *      création ou mise à jour : 
 *          la sauvegarde de create() est reporté dans save().
 */
package com.persistence;

import java.sql.*;
import java.util.ArrayList;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class DonneesTR {
    static String sMode[] = {"STANDARD", "NORMAL", "DEGRADE", "DMD_GPS",
        "GPS", "MAINTENANCE", "INIT", "DORMIR", "ERREUR"};
    private String mode;
    private Timestamp datation;
    private int vitesse;
    private int regime;
    private int consommation;
    private int vitesseMax;
    private int regimeMax;
    private int consoMax;
    private int nbDefauts;
    private int defaut1;
    private int defaut2;
    private int defaut3;
    private int defaut4;
    private double latitudeGPS;
    private double longitudeGPS;
    private double latitude;
    private double longitude;
    private int radius;
    private long distanceParcourue;
    private int seqNumber;
    private double snr;
    private double rssi;
    private double avgSnr;
    private int vehiculeID;

    /**
     * Créer un nouvel objet persistant
     *
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
     * @param latitudeGPS
     * @param longitudeGPS
     * @param longitude
     * @param distanceParcourue
     * @param seqNumber
     * @param radius
     * @param snr
     * @param rssi
     * @param avgSnr
     * @param vehiculeID
     * @return
     * @ return retourne une donneesTR si la date est unique sinon null
     * @throws Exception impossible d'accéder à la ConnexionMySQL ou la date est
     * deja dans la BD
     *
     */
    static public DonneesTR create(Connection con, String mode, int seqNumber,
            Timestamp datation,
            int vitesse, int regime, int consommation,
            int vitesseMax, int regimeMax, int consoMax,
            int nbDefauts, int defaut1, int defaut2, int defaut3, int defaut4,
            double latitudeGPS, double longitudeGPS,
            double snr, double rssi, double avgSnr, int radius,
            double latitude, double longitude,
            long distanceParcourue, int vehiculeID) throws Exception {
        // instanciation de l'objet avec les données initiales
        DonneesTR donneesTR = new DonneesTR(mode, seqNumber, datation,
                vitesse, regime, consommation,
                vitesseMax, regimeMax, consoMax,
                nbDefauts, defaut1, defaut2, defaut3, defaut4,
                latitudeGPS, longitudeGPS,
                snr, rssi, avgSnr, radius,
                latitude, longitude, distanceParcourue, vehiculeID);
        // la sauvegarde est maintenant faite dans save()
        // pour une création ou une mise à jour
        donneesTR.save(con);
        return donneesTR;
    }

    /**
     * suppression de l'objet donneesTR dans la BD
     *
     * @param con
     * @return
     * @throws SQLException impossible d'accéder à la ConnexionMySQL
     */
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from donneesTR"
                + " where SeqNumber =" + seqNumber
                + " and Datation = '" + datation + "' "
                + " and VehiculeID = " + vehiculeID;
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString);
        return true;
    }

    /**
     * update de l'objet donneesTR dans la ConnexionMySQL
     *
     * @param con
     * @throws Exception impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString = "";
        // je regarde d'abord si l'enregistrement existe déjà pour ce message
        queryString = "select ID, Mode from donneesTR "
                + " where SeqNumber = " + seqNumber
                + " and Datation = '" + datation + "' "
                + " and VehiculeID = " + vehiculeID;
        Statement lStat = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        // si il existe, on l'update par l'ID
        if (lResult.next()) {
            int id = lResult.getInt("ID");
            // mode de l'enregistrement (utile pour "GEO")
            String modeActuel = lResult.getString("Mode");
            
            if (mode.equals("NORMAL")) {
                // datas véhicule et kilométrage
                // NORMAL : TM K1 K2 K3 CD CD VX VM RX RM CX CM
                queryString = "update donneesTR set "
                        + " Mode =" + Utils.toString(mode) + ","
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
                        + " DistanceParcourue =" + Utils.toString(distanceParcourue)
                        + " where ID=" + id;
            } else if ((mode.equals("DEGRADE")) || (mode.equals("INIT")) || (mode.equals("DORMIR"))) {
                // kilométrage seulement
                // TM K1 K2 K3 00 00 00 00 00 00 00 00
                queryString = "update donneesTR set "
                        + " Mode =" + Utils.toString(mode) + ","
                        + " DistanceParcourue =" + Utils.toString(distanceParcourue)
                        + " where ID=" + id;
            } else if ((mode.equals("DMD_GPS")) || (mode.equals("GPS"))) {
                // datas position GPS et kilométrage
                // DMD_GPS : TM K1 K2 K3 LA LA LA LA LO LO LO LO
                queryString = "update donneesTR set "
                        + " Mode =" + Utils.toString(mode) + ","
                        + " LatitudeGPS =" + Utils.toString(latitudeGPS) + ","
                        + " LongitudeGPS =" + Utils.toString(longitudeGPS) + ","
                        + " DistanceParcourue =" + Utils.toString(distanceParcourue)
                        + " where ID=" + id;
            } else if (mode.equals("GEO")) {
                queryString = "update donneesTR set "
                        + " Snr =" + snr + ","
                        + " Rssi =" + rssi + ","
                        + " AvgSnr =" + avgSnr + ","
                        + " Radius =" + radius + ","
                        + " Latitude =" + Utils.toString(latitude) + ","
                        + " Longitude =" + Utils.toString(longitude)
                        + " where ID=" + id;
            }
        }
        else {  // si il n'existe pas encore, on le crée
            if (mode.equals("NORMAL")) {
                // datas véhicule et kilométrage
                // NORMAL : TM K1 K2 K3 CD CD VX VM RX RM CX CM
                queryString =
                    "insert into donneesTR (Mode, SeqNumber, Datation,"
                    + " Vitesse, Regime, Consommation,"
                    + " VitesseMax, RegimeMax, ConsoMax,"
                    + " NbDefauts, Defaut1, Defaut2, Defaut3, Defaut4,"
                    + " Snr, Rssi, AvgSnr, Radius, Latitude, Longitude,"
                    + " DistanceParcourue, VehiculeID)"
                    + " values ("
                    + Utils.toString(mode) + ", "
                    + Utils.toString(seqNumber) + ", "
                    + Utils.toString(datation) + ", "
                    // les datas
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
                    // pas d'infos sur le signal, ni sur la position
                    + " NULL, NULL, NULL, NULL, NULL, NULL, "
                    // autres datas
                    + Utils.toString(distanceParcourue) + ", "
                    + Utils.toString(vehiculeID)
                    + ")";
            } else if ((mode.equals("DEGRADE")) || (mode.equals("INIT")) || (mode.equals("DORMIR"))) {
                // kilométrage seulement
                // TM K1 K2 K3 00 00 00 00 00 00 00 00
                queryString =
                    "insert into donneesTR (Mode, SeqNumber, Datation,"
                    + " Snr, Rssi, AvgSnr, Radius, Latitude, Longitude,"
                    + " DistanceParcourue, VehiculeID) "
                    + " values ("
                    + Utils.toString(mode) + ", "
                    + Utils.toString(seqNumber) + ", "
                    + Utils.toString(datation) + ", "
                    // pas d'infos sur le signal, ni sur la position
                    + " NULL, NULL, NULL, NULL, NULL, NULL, "
                    // autres datas
                    + Utils.toString(distanceParcourue) + ", "
                    + Utils.toString(vehiculeID)
                    + ")";
            } else if ((mode.equals("DMD_GPS")) || (mode.equals("GPS"))) {
                // kilométrage seulement
                // TM K1 K2 K3 00 00 00 00 00 00 00 00
                queryString =
                    "insert into donneesTR (Mode, SeqNumber, Datation,"
                    + " Snr, Rssi, AvgSnr, Radius,"
                    + " LatitudeGPS, LongitudeGPS, DistanceParcourue, VehiculeID)"
                    + " values ("
                    + Utils.toString(mode) + ", "
                    + Utils.toString(seqNumber) + ", "
                    + Utils.toString(datation) + ", "
                    // pas d'infos sur le signal
                    + " NULL, NULL, NULL, NULL, "
                    // autres datas
                    + Utils.toString(latitudeGPS) + ", "
                    + Utils.toString(longitudeGPS) + ", "
                    + Utils.toString(distanceParcourue) + ", "
                    + Utils.toString(vehiculeID)
                    + ")";
            } else if (mode.equals("GEO")) {
                // il faut récupérer le dernier mode pour l'ajouter ici
                // car on ne le connait pas encore
                queryString = "select Mode from donneesTR"
                + " where SeqNumber = " + seqNumber
                + " and Datation = '" + datation + "' "
                + " and VehiculeID = " + vehiculeID
                + " order by Datation desc limit 1";
                
                lStat = con.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                lResult = lStat.executeQuery(queryString);
                // y en a t'il au moins un ?
                String modeActuel = "NORMAL";
                if (lResult.next()) {
                    modeActuel = lResult.getString("Mode");
                }
                queryString =
                    "insert into donneesTR (Mode, SeqNumber, Datation,"
                    + " Snr, Rssi, AvgSnr, Radius,"
                    + " Latitude, Longitude, DistanceParcourue, VehiculeID)"
                    + " values ("
                    + Utils.toString(modeActuel) + ", "
                    + Utils.toString(seqNumber) + ", "
                    + Utils.toString(datation) + ", "
                    // pas d'infos sur le signal, ni sur la position
                    + Utils.toString(snr) + ", "
                    + Utils.toString(rssi) + ", "
                    + Utils.toString(avgSnr) + ", "
                    + Utils.toString(radius) + ", "
                    + Utils.toString(latitude) + ", "
                    + Utils.toString(longitude) + ", "
                    + " NULL, "
                    + Utils.toString(vehiculeID)
                    + ")";
            }
        }
        lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }

    /**
     * Sauvegarde des datas du message Sigfox DATA
     * @param con
     * @param sigfoxID
     * @param seqNumber
     * @param datation
     * @param data
     * @return 
     * @throws Exception 
     */
    public static String saveData(Connection con, String sigfoxID, int seqNumber,
            Timestamp datation, String data) throws Exception {
        int vitesseMoy = 0, regimeMoy = 0, consoMoy = 0, vitesseMax = 0,
                regimeMax = 0, consoMax = 0;
        int defaut1 = 0, defaut2 = 0, defaut3 = 0, defaut4 = 0;
        long distanceParcourue = 0L;
        double latitudeGPS = 0, longitudeGPS = 0;
        double latitude = 0, longitude = 0;
        Boitier boitier = Boitier.getByID(con, sigfoxID);
        if (boitier == null) {
            // le boitier n'existe pas ???
            return "ERREUR";
        }
        int vehiculeID = boitier.getVehiculeID();
        // conversion de la chaine hexa en tableau de byte
        HexBinaryAdapter adapter = new HexBinaryAdapter();
        byte[] bData = adapter.unmarshal(data);

        // décodage du 1er octet TM
        boolean bluetoothActif = false;
        bluetoothActif = (bData[0] & 0x80) == 0x80;
        boolean OBD2Actif = false;
        OBD2Actif = (bData[0] & 0x40) == 0x40;
        int nbDefauts = 0;
        boolean isDefaut = false;
        isDefaut = (bData[0] & 0x10 >> 4) != 0;
        String mode = "";
        mode = sMode[bData[0] & 0x0F];
        if (mode.equals("NORMAL")) {
            // datas véhicule et kilométrage
            // NORMAL : TM K1 K2 K3 CD CD VX VM RX RM CX CM
            distanceParcourue = bData[1] * 10000 + bData[2] * 100 + bData[3];
            defaut1 = (bData[4] & 0xF0) >> 4;
            defaut2 = bData[4] & 0x0F;
            defaut3 = (bData[5] & 0xF0) >> 4;
            defaut4 = bData[5] & 0x0F;
            nbDefauts = ((defaut1 != 0) ? 1 : 0) + ((defaut2 != 0) ? 1 : 0)
                    + ((defaut3 != 0) ? 1 : 0) + ((defaut4 != 0) ? 1 : 0);
            vitesseMax = bData[6];
            if (vitesseMax < 0) {
                vitesseMax = 256 + vitesseMax;
            }
            vitesseMoy = bData[7];
            if (vitesseMoy < 0) {
                vitesseMoy = 256 + vitesseMoy;
            }
            regimeMax = bData[8] * 100;
            regimeMoy = bData[9] * 100;
            consoMax = bData[10];
            if (consoMax < 0) {
                consoMax = 256 + consoMax;
            }
            consoMoy = bData[11];
            if (consoMoy < 0) {
                consoMoy = 256 + consoMoy;
            }
        } else if ((mode.equals("DEGRADE")) || (mode.equals("INIT")) || (mode.equals("DORMIR"))) {
            // kilométrage seulement
            // TM K1 K2 K3 00 00 00 00 00 00 00 00
            distanceParcourue = bData[1] * 10000 + bData[2] * 100 + bData[3];
        } else if ((mode.equals("DMD_GPS")) || (mode.equals("GPS"))) {
            // datas position GPS et kilométrage
            // DMD_GPS : TM K1 K2 K3 LA LA LA LA LO LO LO LO
            distanceParcourue = bData[1] * 10000 + bData[2] * 100 + bData[3];
            boolean negLat = false;
            boolean negLg = false;
            // gestion du signe des données GPS.
            if ((bData[7] & 0x01) != 0) {
                bData[7] &= 0xFE;
                negLat = true;
            }
            if ((bData[11] & 0x01) != 0) {
                bData[11] &= 0xFE;
                negLg = true;
            }
            latitudeGPS = (float) bData[4] + (float) bData[5] / 100
                    + (float) bData[6] / 10000 + (float) bData[7] / 1000000;
            if (negLat) {
                latitudeGPS = -latitudeGPS;
            }
            longitudeGPS = (float) bData[8] + (float) bData[9] / 100
                    + (float) bData[10] / 10000 + (float) bData[11] / 1000000;
            if (negLg) {
                longitudeGPS = -longitudeGPS;
            }
        } else {
            return "ERREUR";     // mode inconnu
        }
        // crée un objet donnéesTR avec des données Diffèrentes suivant le mode
        create(con, mode, seqNumber, datation,
                vitesseMoy, regimeMoy, consoMoy,
                vitesseMax, regimeMax, consoMax,
                nbDefauts, defaut1, defaut2, defaut3, defaut4,
                latitudeGPS, longitudeGPS,
                0, 0, 0, 0, latitude, longitude,
                distanceParcourue, vehiculeID);
        return mode;
    }
    
    /**
     * Sauvegarde des informations sur le signal et la géolocalisation SIGFOX
     *    du message GEOLOC
     * @param con
     * @param sigfoxID
     * @param seqNumber
     * @param datation
     * @param snr
     * @param rssi
     * @param avgSnr
     * @param radius
     * @param latitudeSigfox
     * @param longitudeSigfox
     * @throws Exception 
     */
    public static void saveGeo(Connection con, String sigfoxID, int seqNumber,
        Timestamp datation, double snr, double rssi, double avgSnr, int radius,
        double latitudeSigfox, double longitudeSigfox) throws Exception {

        Boitier boitier = Boitier.getByID(con, sigfoxID);
        if (boitier == null) {
            // le boitier n'existe pas !!!
            return;
        }
        int vehiculeID = boitier.getVehiculeID();

        create(con, "GEO", seqNumber, datation,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.0, 0.0,
            snr, rssi, avgSnr, radius,
            latitudeSigfox, longitudeSigfox, 0, vehiculeID);
    }

    /**
     * Retourne une donneesTR trouve par sa date, saved is true
     *
     * @param con
     * @param immatriculation date de donneesTR a trouver
     * @return donneesTR trouv" par immatriculation
     * @throws java.lang.Exception
     */
    public static DonneesTR getLastByImmatriculation(Connection con,
            String immatriculation) throws Exception {
        String queryString = "select * from donneesTR,vehicule"
                + " where Immatriculation = '" + immatriculation + "'"
                + " and vehicule.ID = donneesTR.VehiculeID"
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

    public static ArrayList<DonneesTR> getByDate(Connection con,
            String immatriculation, String dateDonnees) throws Exception {
        String queryString = "select * from donneesTR,vehicule"
                + " where Immatriculation = '" + immatriculation + "'"
                + " and vehicule.ID = donneesTR.VehiculeID"
                + " and Datation between '" + dateDonnees + " 00:00:00'"
                + " and '" + dateDonnees + " 23:59:59'"
                + " order by Datation desc";

        Statement lStat = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        ArrayList<DonneesTR> lstDonneesTR = new ArrayList<>();
        // y en a t'il au moins un ?
        while (lResult.next()) {
            lstDonneesTR.add(creerParRequete(lResult));
        }
        return lstDonneesTR;
    }

    public static ArrayList<DonneesTR> getDonneesVehicule(Connection con,
            String immatriculation) throws Exception {
        String queryString = "select * from donneesTR,vehicule"
                + " where Immatriculation = '" + immatriculation + "'"
                + " and vehicule.ID = donneesTR.VehiculeID"
                + " order by Datation desc";
        Statement lStat = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        ArrayList<DonneesTR> lstDonneesTR = new ArrayList<>();
        // y en a t'il au moins un ?
        while (lResult.next()) {
            lstDonneesTR.add(creerParRequete(lResult));
        }
        return lstDonneesTR;
    }

    private static DonneesTR creerParRequete(ResultSet result) throws Exception {
        String lMode = result.getString("Mode");
        int lSeqNumber = result.getInt("SeqNumber");
        Timestamp lDatation = result.getTimestamp("Datation");
        int lVitesse = result.getInt("Vitesse");
        int lRegime = result.getInt("Regime");
        int lConsommation = result.getInt("Consommation");
        int lVitesseMax = result.getInt("VitesseMax");
        int lRegimeMax = result.getInt("RegimeMax");
        int lConsoMax = result.getInt("ConsoMax");
        int lNbDefauts = result.getInt("NbDefauts");
        int lDefaut1 = result.getInt("Defaut1");
        int lDefaut2 = result.getInt("Defaut2");
        int lDefaut3 = result.getInt("Defaut3");
        int lDefaut4 = result.getInt("Defaut4");
        double lLatitudeGPS = result.getDouble("LatitudeGPS");
        double lLongitudeGPS = result.getDouble("LongitudeGPS");
        double lSnr = result.getDouble("Snr");
        double lRssi = result.getDouble("Rssi");
        double lAvgSnr = result.getDouble("AvgSnr");
        int lRadius = result.getInt("Radius");
        double lLatitude = result.getDouble("Latitude");
        double lLongitude = result.getDouble("Longitude");
        long lDistanceParcourue = result.getLong("DistanceParcourue");
        int lVehiculeID = result.getInt("VehiculeID");
        return new DonneesTR(lMode, lSeqNumber, lDatation,
                lVitesse, lRegime, lConsommation,
                lVitesseMax, lRegimeMax, lConsoMax,
                lNbDefauts, lDefaut1, lDefaut2, lDefaut3, lDefaut4,
                lLatitudeGPS, lLongitudeGPS,
                lSnr, lRssi, lAvgSnr, lRadius,
                lLatitude, lLongitude, lDistanceParcourue, lVehiculeID);
    }

    /**
     * Cree et initialise completement DonneesTR
     */
    private DonneesTR(String mode, int seqNumber, Timestamp datation,
            int vitesse, int regime, int consommation,
            int vitesseMax, int regimeMax, int consoMax,
            int nbDefauts, int defaut1, int defaut2, int defaut3, int defaut4,
            double latitudeGPS, double longitudeGPS, 
            double snr, double rssi, double avgSnr, int radius,
            double latitude, double longitude, long distanceParcourue, int vehiculeID) {
        this.mode = mode;
        this.seqNumber = seqNumber;
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
        this.snr = snr;
        this.rssi = rssi;
        this.avgSnr = avgSnr;
        this.radius = radius;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public long getDistanceParcourue() {
        return distanceParcourue;
    }

    public int getSeqNumber() {
        return seqNumber;
    }

    public int getRadius() {
        return radius;
    }

    public double getSnr() {
        return snr;
    }

    public double getRssi() {
        return rssi;
    }

    public double getAvgSnr() {
        return avgSnr;
    }

    public int getVehiculeID() {
        return vehiculeID;
    }

    /**
     * toString() operator overload
     *
     * @return the result string
     */
    @Override
    public String toString() {
        return " Mode = " + Utils.toString(mode) + "\t"
            + " SeqNumber = " + Utils.toString(seqNumber)
            + " Datation = " + Utils.toString(datation)
            + " Vitesse = " + Utils.toString(vitesse)
            + " Regime = " + Utils.toString(regime)
            + " Consommation = " + Utils.toString(consommation)
            + " VitesseMax = " + Utils.toString(vitesseMax)
            + " RegimeMax = " + Utils.toString(regimeMax)
            + " ConsoMax = " + Utils.toString(consoMax)
            + " NbDefauts = " + Utils.toString(nbDefauts)
            + " Defaut1 = " + Utils.toString(defaut1)
            + " Defaut2 = " + Utils.toString(defaut2)
            + " Defaut3 = " + Utils.toString(defaut3)
            + " Defaut4 = " + Utils.toString(defaut4)
            + " LatitudeGPS = " + Utils.toString(latitudeGPS)
            + " LongitudeGPS = " + Utils.toString(longitudeGPS)
            + " Latitude = " + Utils.toString(latitude)
            + " Longitude = " + Utils.toString(longitude)
            + " Snr = " + Utils.toString(snr)
            + " Rssi = " + Utils.toString(rssi)
            + " AvgSnr = " + Utils.toString(snr)
            + " Radius = " + Utils.toString(radius)
            + " DistanceParcourue = " + Utils.toString(distanceParcourue)
            + " VehiculeID = " + Utils.toString(vehiculeID);
    }
}
