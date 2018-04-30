/*
 * Projet  : Alfox
 * Fichier : User.java
 * Description : Classe interface de la table donneesHisto
 * Cette table stocke les données enregistrées par la SD
 */

package persistence;

import java.sql.*;

public class DonneesHisto {
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
    private double    latitudeGPS;      // non null
    private double    longitudeGPS;     // non null
    private long      distanceParcourue;// non null
    private int       seqNumber;        // non null
    private float     snr;              // non null
    private float     rssi;             // non null
    private float     avgSnr;           // non null
    private static String    device;           // non null
    
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
     * @param seqNumber
     * @param snr
     * @param rssi
     * @param avgSnr
     * @param device
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
                        long distanceParcourue, int seqNumber, float snr, float rssi,
                            float avgSnr, String device)  throws Exception {
        DonneesHisto donneesHisto = new DonneesHisto(mode, datation, vitesse, regime, 
            consommation, vitesseMax, regimeMax, consoMax, nbDefauts, defaut1,
                defaut2, defaut3, defaut4, latitudeGPS, longitudeGPS, distanceParcourue,
                    seqNumber, snr, rssi, avgSnr, device);
        
        String queryString =
         "insert into donneesHisto (Mode, Datation, Vitesse, Regime, Consommation, VitesseMax, RegimeMax, ConsoMax, NbDefauts, Defaut1, Defaut2, Defaut3, Defaut4, LatitudeGPS, LongitudeGPS, DistanceParcourue, SeqNumber, Snr, Rssi, AvgSnr, Device) values ("
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
                + Utils.toString(seqNumber) + ", "
                + Utils.toString(snr) + ", "
                + Utils.toString(rssi) + ", "
                + Utils.toString(avgSnr) + ", "
                + Utils.toString(device)
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
        String queryString = "delete from donneesHisto, vehicule where Datation='" + datation + "' and device = vehicule.IdSigfox and vehicule.IdSigfox = '" + device + "'";
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
                + " SeqNumber =" + Utils.toString(seqNumber) + "," 
                + " Snr =" + Utils.toString(snr) + "," 
                + " Rssi =" + Utils.toString(rssi) + "," 
                + " AvgSnr =" + Utils.toString(avgSnr) + "," 
                + " Device =" + Utils.toString(device)
                + " where Datation ='" + datation + "'"
                + " and device = vehicule.Idsigfox "
                + " and device ='" + device + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }
    
    /**
     * Retourne un donneesHisto trouve par sa date, saved is true
     * @param con
     * @param  datation date de donneesHisto a trouver
     * @return donneesHisto trouvé par date
     * @throws java.lang.Exception
     */
    public static DonneesHisto getByDatation(Connection con, String datation) throws Exception {
        String queryString = "select * from donneesHisto, vehicule where Datation='" + datation + "' and device = vehicule.IdSigfox and vehicule.IdSigfox = '" + device + "'";
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
            int       lSeqNumber = result.getInt("SeqNumber");
            float     lSnr = result.getFloat("Snr");
            float     lRssi = result.getFloat("Rssi");
            float     lAvgSnr = result.getFloat("AvgSnr");
            String    lDevice = result.getString("Device");
            return    new DonneesHisto(lMode, lDatation, lVitesse, lRegime, lConsommation, 
                lVitesseMax, lRegimeMax, lConsoMax, lNbDefauts, lDefaut1, lDefaut2, 
                    lDefaut3, lDefaut4, lLatitudeGPS, lLongitudeGPS, lDistanceParcourue,
                        lSeqNumber, lSnr, lRssi, lAvgSnr, lDevice);
    }
    
    /**
     * Cree et initialise completement DonneesHisto
     */
    private DonneesHisto(String mode, Timestamp datation,
            int vitesse, int regime, int consommation, int vitesseMax, int regimeMax,
                int consoMax, int nbDefauts, int defaut1, int defaut2, int defaut3, 
                    int defaut4, double latitudeGPS, double longitudeGPS, long distanceParcourue,
                        int seqNumber, float snr, float rssi, float avgSnr, String device) {
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
        this.seqNumber = seqNumber;
        this.snr = snr;
        this.rssi = rssi;
        this.avgSnr = avgSnr;
        this.device = device;
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

    public double getLatitudeGPS() {
        return latitudeGPS;
    }
    
    public double getLongitudeGPS() {
        return longitudeGPS;
    }

    public long getDistanceParcourue() {
        return distanceParcourue;
    }
    
    public long getSeqNumber() {
        return distanceParcourue;
    }
    
    public long getSnr() {
        return distanceParcourue;
    }
    
    public long getRssi() {
        return distanceParcourue;
    }
    
    public long getAvgSnr() {
        return distanceParcourue;
    }
    
    public long getDevice() {
        return distanceParcourue;
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
                " SeqNumber = " + Utils.toString(seqNumber) +
                " Snr = " + Utils.toString(snr) +
                " Rssi = " + Utils.toString(rssi) +
                " AvgSnr = " + Utils.toString(snr) +
                " Device = " + Utils.toString(device)
                + " ";
    }
}