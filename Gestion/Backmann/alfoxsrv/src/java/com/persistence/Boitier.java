/*
 * Projet  : Alfox
 * Fichier : Boitier.java
 * Description : Classe interface de la table boitier
 * Cette table stocke les infos sur les boitiers embarqués ds les véhicules
 */

package com.persistence;

import java.sql.*;

public class Boitier {
    private String  SigfoxID;       // clef primaire
    private String  ModeActuel;
    private String  ModeDemande;
    private int     NbMsgDownlink;
    private boolean CommValide;
    private int     VehiculeID;
    
    /**
     * Créer un nouvel objet persistant 
     * @param con
     * @param SigfoxID
     * @param ModeActuel
     * @param ModeDemande
     * @param NbMsgDownlink
     * @param CommValide
     * @param VehiculeID
     * @return 
     * @ return retourne un loueur si le mail est unique sinon null
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     *                      ou le mail est deja dans la BD
     * 
     */
    static public Boitier create(Connection con, String SigfoxID, String ModeActuel, 
            String ModeDemande, int NbMsgDownlink, boolean CommValide, int VehiculeID)  throws Exception {
        Boitier boitier = new Boitier(SigfoxID, ModeActuel, ModeDemande, NbMsgDownlink, CommValide, VehiculeID);
        
        String queryString =
         "insert into boitier (SigfoxID, ModeActuel, ModeDemande, "
            + "NbMsgDownlink, CommValide, VehiculeID) values ("
                + Utils.toString(SigfoxID) + ", " 
                + Utils.toString(ModeActuel) + ", " 
                + Utils.toString(ModeDemande) + ", " 
                + Utils.toString(NbMsgDownlink) + ", " 
                + Utils.toString(CommValide) + ", "
                + Utils.toString(VehiculeID)
            + ")";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
        return boitier;
    }
    
    /**
     * update de l'objet loueur dans la ConnexionMySQL
     * @param con
     * @throws Exception impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString =
         "update boitier set "
                + " ModeActuel =" + Utils.toString(ModeActuel) + ","
                + " ModeDemande =" + Utils.toString(ModeDemande) + "," 
                + " NbMsgDownlink =" + Utils.toString(NbMsgDownlink) + ","  
                + " CommValide =" + Utils.toString(CommValide) + ","
                + " VehiculeID ="+ Utils.toString(VehiculeID)
                + " where SigfoxID = " + SigfoxID + "';";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }
    
    /**
     * Retourne un boitier trouve par son SigfoxID
     * @param  con
     * @param  sigfoxID ID recherché
     * @return boitier trouvé son SigfoxID
     * @throws java.lang.Exception
     */
    public static Boitier getByID(Connection con, String sigfoxID) throws Exception {
        String queryString = "select * from boitier where SigfoxID='" + sigfoxID + "';";
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
    
    /**
     * suppression de l'objet loueur dans la BD
     * @param con
     * @return 
     * @throws SQLException impossible d'accéder à la ConnexionMySQL
     */
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from boitier where SigfoxID='" + SigfoxID + "';";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString);
        return true;
    }
    
    private static Boitier creerParRequete(ResultSet result) throws Exception {
            String    lSigfoxID  = result.getString("SigfoxID");
            String    lModeActuel = result.getString("ModeActuel");
            String    lModeDemande = result.getString("ModeDemande");
            int       lNbMsgDownlink = result.getInt("NbMsgDownlink");
            boolean   lCommValide = result.getBoolean("CommValide");
            int       lVehiculeID = result.getInt("VehiculeID");
            return    new Boitier(lSigfoxID,lModeActuel, lModeDemande, lNbMsgDownlink, lCommValide, lVehiculeID);
    }
    
    /**
     * Cree et initialise completement Loueur
     */
    private Boitier(String SigfoxID, String ModeActuel, String ModeDemande,
            int NbMsgDownlink, boolean CommValide, int VehiculeID) {
        this.SigfoxID = SigfoxID;
        this.ModeActuel = ModeActuel;
        this.ModeDemande = ModeDemande;
        this.NbMsgDownlink = NbMsgDownlink;
        this.CommValide = CommValide;
        this.VehiculeID = VehiculeID;
    }
    
    // --------------------- les assesseurs ----------------------------
    public String getSigfoxID() {
        return SigfoxID;
    }
 
    public int getVehiculeID() {
        return VehiculeID;
    }

    public String getModeActuel() {
        return ModeActuel;
    }
    public String getModeDemande() {
        return ModeDemande;
    }

    public int getNbMsgDownlink() {
        return NbMsgDownlink;
    }

    public boolean getCommValide() {
        return CommValide;
    }

    public void setSigfoxID(String SigfoxID) throws Exception {
        this.SigfoxID = SigfoxID;
    }

    public void setVehiculeID(int VehiculeID) throws Exception {
        this.VehiculeID = VehiculeID;
    }

    public void setModeActuel(String ModeActuel) throws Exception {
        this.ModeActuel = ModeActuel;
    }

    public void setModeDemande(String ModeDemande) throws Exception {
        this.ModeDemande = ModeDemande;
    }

    public void setNbMsgDownlink(int NbMsgDownlink) throws Exception {
        this.NbMsgDownlink = NbMsgDownlink;
    }

    public void setNCommValide(boolean CommValide) throws Exception {
        this.CommValide = CommValide;
    }
    
    /**
     * toString() operator overload
     * @return   the result string
     */
    @Override
    public String toString() {
        return  " SigfoxID =  "     + SigfoxID + "\t" +
                " ModeActuel =  "   + ModeActuel +
                " ModeDemande = "   + ModeDemande + 
                " NbMsgDownlink = " + Utils.toString(NbMsgDownlink) + 
                " CommValide = "    + Utils.toString(CommValide)+ 
                " VehiculeID = "    + Utils.toString(VehiculeID);
    }
}