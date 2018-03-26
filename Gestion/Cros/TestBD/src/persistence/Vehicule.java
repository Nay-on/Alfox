/*
 * Projet  : eventSkyTracker
 * Fichier : User.java
 * Description : Classe interface de la table user
 * Cette table stocke les infos sur les utilisateurs connus du logiciel
 */
package persistence;

import java.sql.*;
import java.util.ArrayList;
import persistence.Utils;

public class Vehicule {

    private String marque;           // la clef primaire
    private String modele;
    private String immatriculation;
    private Timestamp dateMiseEnService;
    private String motorisation;
    private String idSigfox;
    private Timestamp dateVidange;
    private int kmVidange;
    private boolean horsZone;
    private int tauxUtilisation;
    private boolean aProbleme;
    private float compteurReel;
    private Timestamp dateControleTechnique;

    /**
     * Créer un nouvel objet persistant
     *
     * @param con
     * @param marque
     * @param modele
     * @param immatriculation
     * @param dateMiseEnService
     * @param motorisation
     * @param idSigfox
     * @param dateVidange
     * @param kmVidange
     * @param horsZone
     * @param tauxUtilisation
     * @param aProbleme
     * @param compteurReel
     * @param dateControleTechnique
     * @return
     * @ return retourne un vehicule si l'immatriculation est unique sinon null
     * @throws Exception impossible d'accéder à la ConnexionMySQL ou le numero
     * est deja dans la BD
     *
     */
    static public Vehicule create(Connection con, String marque, String modele,
            String immatriculation, Timestamp dateMiseEnService,
            String motorisation, String idSigfox, Timestamp dateVidange,
            int kmVidange, boolean horsZone, int tauxUtilisation, boolean aProbleme,
            float compteurReel, Timestamp dateControleTechnique) throws Exception {
        Vehicule vehicule = new Vehicule(marque, modele, immatriculation,
                dateMiseEnService, motorisation, idSigfox, dateVidange,
                kmVidange, horsZone, tauxUtilisation, aProbleme,
                compteurReel, dateControleTechnique);

        String queryString
                = "insert into vehicule (Marque, Modele, Immatriculation, DateMiseEnService, Motorisation, IdSigfox, DateVidange, KmVidange, HorsZone, TauxUtilisation, AProbleme, CompteurReel, DateControleTechnique) values ("
                + Utils.toString(marque) + ", "
                + Utils.toString(modele) + ", "
                + Utils.toString(immatriculation) + ", "
                + Utils.toString(dateMiseEnService) + ", "
                + Utils.toString(motorisation) + ", "
                + Utils.toString(idSigfox) + ", "
                + Utils.toString(dateVidange) + ", "
                + Utils.toString(kmVidange) + ", "
                + Utils.toString(horsZone) + ", "
                + Utils.toString(tauxUtilisation) + ", "
                + Utils.toString(aProbleme) + ", "
                + Utils.toString(compteurReel) + ", "
                + Utils.toString(dateControleTechnique)
                + ")";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
        return vehicule;
    }

    /**
     * suppression de l'objet user dans la BD
     *
     * @param con
     * @return
     * @throws SQLException impossible d'accéder à la ConnexionMySQL
     */
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from vehicule where Immatriculation='" + immatriculation + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString);
        return true;
    }

    /**
     * update de l'objet contrat dans la ConnexionMySQL
     *
     * @param con
     * @throws Exception impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString
                = "update vehicule set "
                + " Marque =" + Utils.toString(marque) + ","
                + " Modele =" + Utils.toString(modele) + ","
                + " Immatriculation =" + Utils.toString(immatriculation) + ","
                + " DateMiseEnService =" + Utils.toString(dateMiseEnService) + ","
                + " Motorisation =" + Utils.toString(motorisation) + ","
                + " IdSigfox =" + Utils.toString(idSigfox) + ","
                + " DateVidange =" + Utils.toString(dateVidange) + ","
                + " KmVidange =" + Utils.toString(kmVidange) + ","
                + " HorsZone =" + Utils.toString(horsZone) + ","
                + " TauxUtilisation =" + Utils.toString(tauxUtilisation) + ","
                + " AProbleme =" + Utils.toString(aProbleme) + ","
                + " CompteurReel =" + Utils.toString(compteurReel) + ","
                + " DateControleTechnique =" + Utils.toString(dateControleTechnique)
                + " where Immatriculation ='" + immatriculation + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }

    /**
     * Retourne un user trouve par son pseudo, saved is true
     *
     * @param con
     * @param immatriculation l'immatriculation a trouver
     * @return Vehicule vehicule trouve par immatriculation
     * @throws java.lang.Exception
     */
    public static Vehicule getByImmatriculation(Connection con, String immatriculation) throws Exception {
        String queryString = "select * from vehicule where Immatriculation='" + immatriculation + "'";
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

    public Timestamp getLastDatation(Connection con) throws Exception {
        //Récupération de la date de la dernière donnée TR enregistrée pour le véhicule associé
        String queryString = "select Datation from donneesTR, vehicule where VehiculeID = vehicule.ID and vehicule.Immatriculation = \""
                + this.immatriculation + "\" order by Datation desc limit 1;";
        Statement lStat = con.createStatement( //peut générer une exception si problème avec la requête SQL
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet lDate = lStat.executeQuery(queryString);
        //Si il y a une donnée on retourne la date
        if (lDate.next()) {
            Timestamp date = lDate.getTimestamp("Datation");
            return date;
        } //Sinon on génère une exception
        else {
            throw new Exception("Aucune donnée TR");
        }
    }

    // Obligation de passer par la méthode getLastDatation() avant
    // Met à jour horsZone
    public boolean isDehors(Connection con) throws Exception {
        //Récupération du tableau de position de la zone associée par le contrat au véhicule
        String queryString = "select * from position, zoneLimite where  ZoneLimiteID = zoneLimite.ID and Nom = (select Nom from contrat, vehicule, zoneLimite where VehiculeID = vehicule.ID and ZoneLimiteID = zoneLimite.ID and Immatriculation='" + immatriculation + "') order by Ordre";
        Statement lStat = con.createStatement( //peut générer une exception si problème avec la requête SQL
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        // on met les points dans 2 collections Arraylist de Float
        // Float avec un F majuscule est une classe !)
        ArrayList<Float> xap = new ArrayList<>();
        ArrayList<Float> yap = new ArrayList<>();
        while (lResult.next()) {
            xap.add(lResult.getFloat("Latitude"));
            yap.add(lResult.getFloat("Longitude"));
        }
        // on transforme les collections en tableaux d'objets
        int nbPoints = xap.size();
        Float[] xtp = xap.toArray(new Float[0]);
        Float[] ytp = yap.toArray(new Float[0]);

        // les tableaux sont maintenant des tableaux de Float !
        //Récupération de la dernière latitude et longitude enregistrée
        String queryString2 = "select Latitude, Longitude from donneesTR, vehicule where VehiculeID = vehicule.ID and vehicule.Immatriculation = \""
                + this.immatriculation + "\" order by Datation desc limit 1;";
        Statement lStat2 = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet req = lStat2.executeQuery(queryString2);
        float latitude = 0;
        float longitude = 0;
        //Si il y a une donnée on récupère la latitude et longitude
        if (req.next()) {
            latitude = req.getFloat("Latitude");
            longitude = req.getFloat("Longitude");
        } //Sinon on génère une exception
        else {
            throw new Exception("Aucune donnée TR");
        }
        //on vérifie que le point se situe dans la zone

        int i, j;
        boolean isDehors = false;
        for (i = 0, j = nbPoints - 1; i < nbPoints; j = i++) {
            if ((((ytp[i] <= longitude) && (longitude < ytp[j])) || ((ytp[j] <= longitude) && (longitude < ytp[i])))
                    && (latitude < (xtp[j] - xtp[i]) * (longitude - ytp[i]) / (ytp[j] - ytp[i]) + xtp[i])) {
                isDehors = !isDehors;
            }
        }
        horsZone = isDehors;
        return !isDehors;
    }

    private static Vehicule creerParRequete(ResultSet result) throws Exception {
        String lMarque = result.getString("Marque");
        String lModele = result.getString("Modele");
        String lImmatriculation = result.getString("Immatriculation");
        Timestamp lDateMiseEnService = result.getTimestamp("DateMiseEnService");
        String lMotorisation = result.getString("Motorisation");
        String lIdSigfox = result.getString("IdSigfox");
        Timestamp lDateVidange = result.getTimestamp("DateVidange");
        int lKmVidange = result.getInt("KmVidange");
        boolean lHorsZone = result.getBoolean("HorsZone");
        int lTauxUtilisation = result.getInt("TauxUtilisation");
        boolean lAProbleme = result.getBoolean("AProbleme");
        float lCompteurReel = result.getFloat("CompteurReel");
        Timestamp lDateControleTechnique = result.getTimestamp("DateControleTechnique");
        return new Vehicule(lMarque, lModele, lImmatriculation, lDateMiseEnService,
                lMotorisation, lIdSigfox, lDateVidange, lKmVidange,
                lHorsZone, lTauxUtilisation, lAProbleme, lCompteurReel,
                lDateControleTechnique);
    }

    /**
     * Cree et initialise completement Contrat
     */
    private Vehicule(String marque, String modele,
            String immatriculation, Timestamp dateMiseEnService,
            String motorisation, String idSigfox, Timestamp dateVidange,
            int kmVidange, boolean horsZone, int tauxUtilisation, boolean aProbleme,
            float compteurReel, Timestamp dateControleTechnique) {
        this.marque = marque;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.dateMiseEnService = dateMiseEnService;
        this.motorisation = motorisation;
        this.idSigfox = idSigfox;
        this.dateVidange = dateVidange;
        this.kmVidange = kmVidange;
        this.horsZone = horsZone;
        this.tauxUtilisation = tauxUtilisation;
        this.aProbleme = aProbleme;
        this.compteurReel = compteurReel;
        this.dateControleTechnique = dateControleTechnique;
    }

    // --------------------- les assesseurs ----------------------------
    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public Timestamp getDateMiseEnService() {
        return dateMiseEnService;
    }

    public String getMotorisation() {
        return motorisation;
    }

    public String getIdSigfox() {
        return idSigfox;
    }

    public Timestamp getDateVidange() {
        return dateVidange;
    }

    public int getKmVidange() {
        return kmVidange;
    }

    public boolean getHorsZone() {
        return horsZone;
    }

    public int getTauxUtilisation() {
        return tauxUtilisation;
    }

    public boolean getAProbleme() {
        return aProbleme;
    }

    public float getCompteurReel() {
        return compteurReel;
    }

    public Timestamp getDateControleTechnique() {
        return dateControleTechnique;
    }

    public void setKmVidange(int kmVidange) {
        this.kmVidange = kmVidange;
    }

    public void setDateVidange(String dateVidange) throws Exception {
        this.dateVidange = Utils.stringToTimestamp(dateVidange);
    }

    public void setHorsZone(boolean horsZone) throws Exception {
        this.horsZone = horsZone;
    }

    public void setTauxUtilisation(int tauxUtilisation) throws Exception {
        this.tauxUtilisation = tauxUtilisation;
    }

    public void setAProbleme(boolean aProbleme) throws Exception {
        this.aProbleme = aProbleme;
    }

    public void setCompteurReel(float compteurReel) throws Exception {
        this.compteurReel = compteurReel;
    }

    public void setDateControleTechnique(String dateControleTechnique) throws Exception {
        this.dateControleTechnique = Utils.stringToTimestamp(dateControleTechnique);
    }

    /**
     * toString() operator overload
     *
     * @return the result string
     */
    @Override
    public String toString() {
        return " Marque = " + Utils.toString(marque) + "\t"
                + " Modele = " + Utils.toString(modele)
                + " Immatriculation = " + Utils.toString(immatriculation)
                + " DateMiseEnService = " + Utils.toString(dateMiseEnService)
                + " Motorisation = " + Utils.toString(motorisation)
                + " IdSigfox = " + Utils.toString(idSigfox)
                + " DateVidange = " + Utils.toString(dateVidange)
                + " KmVidange = " + Utils.toString(kmVidange)
                + " HorsZone = " + Utils.toString(horsZone)
                + " TauxUtilisation = " + Utils.toString(tauxUtilisation)
                + " AProbleme = " + Utils.toString(aProbleme)
                + " CompteurReel = " + Utils.toString(compteurReel)
                + " DateControleTechnique = " + Utils.toString(dateControleTechnique)
                + " ";
    }
}
