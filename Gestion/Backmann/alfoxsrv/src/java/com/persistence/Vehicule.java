/*
 * Projet  : Alfox
 * Fichier : User.java
 * Description : Classe interface de la table vehicule
 * Cette table stocke les infos sur les véhicules connus du logiciel
 */
package com.persistence;

import java.sql.*;
import java.util.ArrayList;

public class Vehicule {

    private String marque;                          // non null
    private String modele;                          // non null
    private String immatriculation;                 // non null, unique
    private Timestamp dateMiseEnService;            // non null
    private String motorisation;                    // non null
    private String idSigfox;                        // unique
    private Timestamp dateVidange;
    private int kmVidange;
    private boolean horsZone;                       // non null
    private int tauxUtilisation;                    // non null
    private boolean aProbleme;                      // non null
    private double compteurReel;                    // non null
    private Timestamp dateControleTechnique;        // non null

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
     * @throws Exception impossible d'accéder à la ConnexionMySQL 
     *                   ou le numero l'immatriculation est deja dans la BD
     *
     */
    static public Vehicule create(Connection con, String marque, String modele,
            String immatriculation, Timestamp dateMiseEnService,
                String motorisation, String idSigfox, Timestamp dateVidange,
                    int kmVidange, boolean horsZone, int tauxUtilisation, boolean aProbleme,
                        double compteurReel, Timestamp dateControleTechnique) throws Exception {
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
     * suppression de l'objet vehicule dans la BD
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
     * update de l'objet vehicule dans la ConnexionMySQL
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
    
    public static ArrayList<String> getImmatriculations(Connection con) throws Exception {
        String queryString = "select Immatriculation from vehicule order by Immatriculation";
        Statement lStat = con.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        ArrayList<String> lstImmatriculation = new ArrayList<>();
        while (lResult.next()) {
            lstImmatriculation.add(lResult.getString("Immatriculation"));
        }
        return lstImmatriculation;
    }

    /**
     * Retourne un vehicule trouve par son immatriculation, saved is true
     *
     * @param con
     * @param immatriculation l'immatriculation a trouver
     * @return vehicule trouvé par immatriculation
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
        // Récupération de la date de la dernière donnée TR enregistrée pour le véhicule associé
        String queryString = "select Datation from donneesTR, vehicule where IdSigfox = donneesTR.Device and vehicule.Immatriculation = \""
                + this.immatriculation + "\" order by Datation desc limit 1;";
        Statement lStat = con.createStatement( // peut générer une exception si problème avec la requête SQL
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet lDate = lStat.executeQuery(queryString);
        // Si il y a une donnée on retourne la date
        if (lDate.next()) {
            Timestamp date = lDate.getTimestamp("Datation");
            return date;
        } // Sinon on génère une exception
        else {
            throw new Exception("Aucune donnée TR");
        }
    }
    
    public static double getKmMoyenFlotte(Connection con) throws Exception {
        double totalKm = 0;
        String queryString = "select CompteurReel from vehicule";
        Statement lStat = con.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        ArrayList<Double> lstCompteurReel = new ArrayList<>();
        while (lResult.next()) {
            lstCompteurReel.add(lResult.getDouble("CompteurReel"));
        }
        for (int i = 0 ; i < lstCompteurReel.size() ; i++) {
            totalKm += lstCompteurReel.get(i);
        }
        return totalKm / lstCompteurReel.size();
    }
    
    public static double getConsoMoyenneFlotte(Connection con) throws Exception {
        double totalConso = 0;
        String queryString = "select Consommation from donneesTR";
        Statement lStat = con.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        ArrayList<Integer> lstConso = new ArrayList<>();
        while (lResult.next()) {
            lstConso.add(lResult.getInt("Consommation"));
        }
        for (int i = 0 ; i < lstConso.size() ; i++) {
            totalConso += lstConso.get(i);
        }
        return totalConso / lstConso.size();
    }
    
    public static double getConsoMoyenneMensuelleFlotte(Connection con) throws Exception {
        double consoMoyenneFlotte = Vehicule.getConsoMoyenneFlotte(con);
        int ageMoyenFlotte = (int)(Vehicule.getAgeMoyenFlotte(con) / 30);
        return consoMoyenneFlotte / ageMoyenFlotte;
    }
    
    public static double getKmMoyenMensuelFlotte(Connection con) throws Exception {
        double kmMoyenFlotte = Vehicule.getKmMoyenFlotte(con);
        int ageMoyenFlotte = (int)(Vehicule.getAgeMoyenFlotte(con) / 30);
        return kmMoyenFlotte / ageMoyenFlotte;
    }
    
    public static int getAgeMoyenFlotte(Connection con) throws Exception {
        long ms = 0;
        Timestamp dateDuJour = Utils.getDateDuJour();
        String queryString = "select * from vehicule";
        Statement lStat = con.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        ArrayList<Timestamp> lstDateMiseEnService = new ArrayList<>();
        while (lResult.next()) {
            lstDateMiseEnService.add(lResult.getTimestamp("DateMiseEnService"));
        }
        for (int i = 0 ; i < lstDateMiseEnService.size() ; i++) {
            ms = ms + (dateDuJour.getTime() - lstDateMiseEnService.get(i).getTime()); 
        }
        int jour = (int)(ms / 86400000);
        return jour/lstDateMiseEnService.size();
    }
    
    public static int nbVehiculesDehors(Connection con) throws Exception {
        int nbVehiculesDehors = 0;
        String queryString = "select * from vehicule";
        Statement lStat = con.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        ArrayList<Vehicule> lstVehicule = new ArrayList<>();
        while (lResult.next()) {
            lstVehicule.add(creerParRequete(lResult));
        }
        for (int i = 0 ; i < lstVehicule.size() ; i++) {
            if (lstVehicule.get(i).isDehors(con) == true) {
                nbVehiculesDehors += 1;
            }
        }
        return nbVehiculesDehors;
    }
    
    // Obligation de passer par la méthode getLastDatation() avant
    // Met à jour horsZone
    public boolean isDehors(Connection con) throws Exception {
        //Récupération du tableau de position de la zone associée par le contrat au véhicule
        String queryString = "select * from position, zoneLimite where  ZoneLimiteID = zoneLimite.ID and Nom = (select Nom from contrat, vehicule, zoneLimite where Device = vehicule.IdSigfox and ZoneLimiteID = zoneLimite.ID and Immatriculation='" + immatriculation + "') order by Ordre";
        Statement lStat = con.createStatement( //peut générer une exception si problème avec la requête SQL
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        // On met les points dans 2 collections Arraylist de Dloat
        // Dloat avec un D majuscule est une classe !)
        ArrayList<Double> xap = new ArrayList<>();
        ArrayList<Double> yap = new ArrayList<>();
        while (lResult.next()) {
            xap.add(lResult.getDouble("Latitude"));
            yap.add(lResult.getDouble("Longitude"));
        }
        // On transforme les collections en tableaux d'objets
        int nbPoints = xap.size();
        Double[] xtp = xap.toArray(new Double[0]);
        Double[] ytp = yap.toArray(new Double[0]);

        // Les tableaux sont maintenant des tableaux de doubles !
        // Récupération de la dernière latitude et longitude enregistrée
        String queryString2 = "select Latitude, Longitude from donneesTR, vehicule where Device = vehicule.IdSigfox and vehicule.Immatriculation = \""
                + this.immatriculation + "\" order by Datation desc limit 1;";
        Statement lStat2 = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet req = lStat2.executeQuery(queryString2);
        double latitude = 0;
        double longitude = 0;
        // Si il y a une donnée on récupère la latitude et longitude
        if (req.next()) {
            latitude = req.getDouble("Latitude");
            longitude = req.getDouble("Longitude");
        }// Sinon on génère une exception
        else {
            throw new Exception("Aucune donnée TR");
        }
        // On vérifie que le point se situe dans la zone

        int i, j;
        boolean isDehors = false;
        for (i = 0, j = nbPoints - 1; i < nbPoints; j = i++) {
            if ((((ytp[i] <= longitude) && (longitude < ytp[j])) || ((ytp[j] <= longitude) && (longitude < ytp[i])))
                    && (latitude < (xtp[j] - xtp[i]) * (longitude - ytp[i]) / (ytp[j] - ytp[i]) + xtp[i])) {
                isDehors = !isDehors;
            }
        }
        // On met à jour horsZone avec la valeur donnée par la méthode isDehors()
        horsZone = isDehors;
        return !isDehors;
    }
    
    public int getTempsAlcis(Connection con) throws Exception {
        int temps = 0; // Temps en minutes
        //Récupération du tableau de position de la zone associée par le contrat au véhicule
        String queryString = "select * from position, zoneLimite where  ZoneLimiteID = zoneLimite.ID and Nom = (select Nom from contrat, vehicule, zoneLimite where Device = vehicule.IdSigfox and ZoneLimiteID = 'Alcis' and Immatriculation='" + immatriculation + "') order by Ordre";
        Statement lStat = con.createStatement( //peut générer une exception si problème avec la requête SQL
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        // On met les points dans 2 collections Arraylist de Dloat
        // Dloat avec un D majuscule est une classe !)
        ArrayList<Double> xap = new ArrayList<>();
        ArrayList<Double> yap = new ArrayList<>();
        while (lResult.next()) {
            xap.add(lResult.getDouble("Latitude"));
            yap.add(lResult.getDouble("Longitude"));
        }
        // On transforme les collections en tableaux d'objets
        int nbPoints = xap.size();
        Double[] xtp = xap.toArray(new Double[0]);
        Double[] ytp = yap.toArray(new Double[0]);

        // Les tableaux sont maintenant des tableaux de doubles !
        // Récupération de la dernière latitude et longitude enregistrée
        String queryString2 = "select Latitude, Longitude from donneesTR, vehicule where Device = vehicule.IdSigfox and vehicule.Immatriculation = \""
                + this.immatriculation + "\" order by Datation desc limit 1;";
        Statement lStat2 = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet req = lStat2.executeQuery(queryString2);
        double latitude = 0;
        double longitude = 0;
        // Si il y a une donnée on récupère la latitude et longitude
        if (req.next()) {
            latitude = req.getDouble("Latitude");
            longitude = req.getDouble("Longitude");
        }// Sinon on génère une exception
        else {
            throw new Exception("Aucune donnée TR");
        }
        // On vérifie que le point se situe dans la zone

        int i, j;
        boolean isDehors = false;
        for (i = 0, j = nbPoints - 1; i < nbPoints; j = i++) {
            if ((((ytp[i] <= longitude) && (longitude < ytp[j])) || ((ytp[j] <= longitude) && (longitude < ytp[i])))
                    && (latitude < (xtp[j] - xtp[i]) * (longitude - ytp[i]) / (ytp[j] - ytp[i]) + xtp[i])) {
                isDehors = !isDehors;
            }
        }
        
        if (!isDehors == false) {
            temps += 10; // On ajoute l'intervalle entre 2 messages : 10 min
        }
        else {
            temps = 0;
        }
        return temps;
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
        double lCompteurReel = result.getDouble("CompteurReel");
        Timestamp lDateControleTechnique = result.getTimestamp("DateControleTechnique");
        return new Vehicule(lMarque, lModele, lImmatriculation, lDateMiseEnService,
                lMotorisation, lIdSigfox, lDateVidange, lKmVidange,
                lHorsZone, lTauxUtilisation, lAProbleme, lCompteurReel,
                lDateControleTechnique);
    }
    
     
    /**
     * Indique le nb de vehicules dans la base de données
     * @param con
     * @return le nombre de vehicules
     * @throws java.lang.Exception
     */
    public static int size(Connection con) throws Exception {
        String queryString = "select count(*) as count from vehicule";
        Statement lStat = con.createStatement(
                                            ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                            ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        if (lResult.next())
            return (lResult.getInt("count"));
        else 
            return 0;
    }

    /**
     * Cree et initialise completement Vehicule
     */
    private Vehicule(String marque, String modele,
            String immatriculation, Timestamp dateMiseEnService,
            String motorisation, String idSigfox, Timestamp dateVidange,
            int kmVidange, boolean horsZone, int tauxUtilisation, boolean aProbleme,
            double compteurReel, Timestamp dateControleTechnique) {
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

    public double getCompteurReel() {
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

    public void setCompteurReel(double compteurReel) throws Exception {
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
