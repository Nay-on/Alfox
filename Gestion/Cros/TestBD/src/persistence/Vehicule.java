/*
 * Projet  : eventSkyTracker
 * Fichier : User.java
 * Description : Classe interface de la table user
 * Cette table stocke les infos sur les utilisateurs connus du logiciel
 */

package persistence;

import java.sql.*;

public class Vehicule {
    private String    marque;           // la clef primaire
    private String    modele;
    private String    immatriculation;
    private Timestamp    dateMiseEnService;
    private String    motorisation;
    private String    idSigfox;
    private Timestamp dateVidange;
    private int       kmVidange;
    private boolean   horsZone;
    private int       tauxUtilisation;
    private boolean   aProbleme;
    private float     compteurReel;
    private Timestamp dateControleTechnique; 
    
    /**
     * Créer un nouvel objet persistant 
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
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     *                      ou le numero est deja dans la BD
     * 
     */
    static public Vehicule create(Connection con, String marque, String modele, 
            String immatriculation, Timestamp dateMiseEnService,
            String motorisation, String idSigfox, Timestamp dateVidange, 
            int kmVidange, boolean horsZone, int tauxUtilisation, boolean aProbleme,
            float compteurReel, Timestamp dateControleTechnique)  throws Exception {
        Vehicule vehicule = new Vehicule(marque, modele, immatriculation,
            dateMiseEnService, motorisation, idSigfox, dateVidange, 
            kmVidange, horsZone, tauxUtilisation, aProbleme,
            compteurReel, dateControleTechnique);
        
        String queryString =
         "insert into user ('Marque', 'Modele', 'Immatriculation', 'DateMiseEnService', 'Motorisation', 'IdSigfox', 'DateVidange', 'KmVidange', 'HorsZone', 'TauxUtilisation', 'AProbleme', 'CompteurReel', 'DateControleTechnique') values ("
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
     * update de l'objet contrat dans la ConnexionMySQL
     * @param con
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString =
         "update user set "
                + " `Marque` =" + Utils.toString(marque) + ","
                + " `Modele` =" + Utils.toString(modele) + "," 
                + " `Immatriculation` =" + Utils.toString(immatriculation) + ","
                + " `DateMiseEnService` =" + Utils.toString(dateMiseEnService) + ","
                + " `Motorisation` =" + Utils.toString(motorisation) + "," 
                + " `IdSigfox` =" + Utils.toString(idSigfox) + ","
                + " `DateVidange` =" + Utils.toString(dateVidange) + ","
                + " `KmVidange` =" + Utils.toString(kmVidange) + "," 
                + " `HorsZone` =" + Utils.toString(horsZone) + ","
                + " `TauxUtilisation` =" + Utils.toString(tauxUtilisation) + ","
                + " `AProbleme` =" + Utils.toString(aProbleme) + "," 
                + " `CompteurReel` =" + Utils.toString(compteurReel) + ","
                + " `DateControleTechnique` =" + Utils.toString(dateControleTechnique);
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }
    
    /**
     * Retourne un user trouve par son pseudo, saved is true
     * @param con
     * @param  immatriculation l'immatriculation a trouver
     * @return Vehicule vehicule trouve par immatriculation
     * @throws java.lang.Exception
     */
    public static Vehicule getByNumero(Connection con, String immatriculation) throws Exception {
        String queryString = "select * from vehicule where immatriculation='" + immatriculation + "'";
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
    
    private static Vehicule creerParRequete(ResultSet result) throws Exception {
            String    lMarque  = result.getString("Marque");
            String    lModele = result.getString("Modele");
            String    lImmatriculation = result.getString("Immatriculation");
            Timestamp    lDateMiseEnService = result.getTimestamp("DateMiseEnService");
            String    lMotorisation  = result.getString("Motorisation");
            String    lIdSigfox = result.getString("IdSigfox");
            Timestamp    lDateVidange = result.getTimestamp("DateVidange");
            int    lKmVidange = result.getInt("KmVidange");
            boolean    lHorsZone  = result.getBoolean("HorsZone");
            int    lTauxUtilisation = result.getInt("TauxUtilisation");
            boolean    lAProbleme = result.getBoolean("AProbleme");
            float    lCompteurReel = result.getFloat("CompteuReel");
            Timestamp    lDateControleTechnique = result.getTimestamp("DateControleTechnique");
            return    new Vehicule(lMarque, lModele, lImmatriculation, lDateMiseEnService, 
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

    public int kmVidange() {
        return kmVidange;
    }

    public void setDateVidange(Timestamp dateVidange) throws Exception {
        this.dateVidange = dateVidange;
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
    public void setDateControleTechnique(Timestamp dateControleTechnique) throws Exception {
        this.dateControleTechnique = dateControleTechnique;
    }
    
    /**
     * toString() operator overload
     * @return   the result string
     */
    @Override
    public String toString() {
        return  " Marque = " + Utils.toString(marque) + "\t" +
                " Modele = " + Utils.toString(modele) + 
                " Immatriculation = " + Utils.toString(immatriculation) + 
                " DateMiseEnService = " + Utils.toString(dateMiseEnService) +
                " Motorisation = " + Utils.toString(motorisation) + 
                " IdSigfox = " + Utils.toString(idSigfox) +
                " DateVidange = " + Utils.toString(dateVidange) + 
                " KmVidange = " + Utils.toString(kmVidange) +
                " HorsZone = " + Utils.toString(horsZone) + 
                " TauxUtilisation = " + Utils.toString(tauxUtilisation) +
                " AProbleme = " + Utils.toString(aProbleme) + 
                " CompteurReel = " + Utils.toString(compteurReel) +
                " DateControleTechnique = " + Utils.toString(dateControleTechnique)
                + " ";
    }
}