/*
 * Projet  : Aflox
 * Fichier : User.java
 * Description : Classe interface de la table zoneLimite
 * Cette table stocke les zones limites connus du logiciel
 */

package com.persistence;

import java.sql.*;
import java.util.ArrayList;

public class ZoneLimite {
    private int ID;         // la clef primaire
    private String nom;     // non null, unique
    
    /**
     * Créer un nouvel objet persistant 
     * @param con
     * @param nom
     * @return 
     * @ return retourne une zoneLimite
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     * 
     */
    static public ZoneLimite create(Connection con, String nom)  throws Exception {
        ZoneLimite zoneLimite = new ZoneLimite(nom);
        
        String queryString =
         "insert into zoneLimite (Nom)"
            + " values ("
                + Utils.toString(nom)
            + ")";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
        zoneLimite.ID = ZoneLimite.getByNom(con, nom).getID();
        return zoneLimite;
    }
    
    /**
     * suppression de l'objet zoneLimite dans la BD
     * @param con
     * @return 
     * @throws SQLException impossible d'accéder à la ConnexionMySQL
     */
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from zoneLimite where Nom='" + nom + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString);
        return true;
    }
    
    /**
     * update de l'objet zoneLimite dans la ConnexionMySQL
     * @param con
     * @throws Exception impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString =
         "update zoneLimite set "
                + " Nom =" + Utils.toString(nom)
                + " where ID ='" + ID + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }
    
    public static int size(Connection con) throws Exception {
        String queryString = "select count(*) as count from zoneLimite";
        Statement lStat = con.createStatement(
                                            ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                            ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        if (lResult.next())
            return (lResult.getInt("count"));
        else 
            return 0;
    }
    
    public static ArrayList<ZoneLimite> getLstZone(Connection con) throws Exception {
        String queryString = "select * from zoneLimite";
        Statement lStat = con.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        ArrayList<ZoneLimite> lstZone = new ArrayList<>();
        while (lResult.next()) {
            lstZone.add(creerParRequete(lResult));
        }
        return lstZone;
    }
    
    /**
     * Retourne une zoneLimite trouve par son nom, saved is true
     * @param con
     * @param  nom nom de la zone recherchée
     * @return zoneLimite trouvee par nom
     * @throws java.lang.Exception
     */
    public static ZoneLimite getByNom(Connection con, String nom) throws Exception {
        String queryString = "select * from zoneLimite where Nom='" + nom + "'";
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
    
    public static double getLatCentre(Connection con, String nom) throws Exception {
        String queryString = "select min(Latitude) as minLat,"
                + " max(Latitude) as maxLat"
                + " from position, zoneLimite where Nom = '" + nom + "'"
                + " and zoneLimite.ID = ZoneLimiteID";
        Statement lStat = con.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        
        if (lResult.next()) {
            double minLat = lResult.getDouble("minLat");
            double maxLat = lResult.getDouble("maxLat");
            return (minLat + maxLat) / 2;
        }
        else
            return 0.0;
    }
    
    public static double getLgCentre(Connection con, String nom) throws Exception {
        String queryString = "select min(Longitude) as minLg, max(Longitude)"
                +" as maxLg from position, zoneLimite where Nom = '" + nom
                + "' and zoneLimite.ID = ZoneLimiteID";
        Statement lStat = con.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        
        if (lResult.next()) {
            double minLg = lResult.getDouble("minLg");
            double maxLg = lResult.getDouble("maxLg");
            return (minLg + maxLg) / 2;
        }
        else
            return 0.0;
    }
    
    private static ZoneLimite creerParRequete(ResultSet result) throws Exception {
        int lID = result.getInt("ID");
        String lNom  = result.getString("Nom");
        return new ZoneLimite(lID, lNom);
    }
    
    /**
     * Cree et initialise completement zoneLimite sans ID
     */
    private ZoneLimite(String nom) {
        this.nom = nom;
    }
    
    /**
     * Cree et initialise completement zoneLimite avec un ID
     */
    private ZoneLimite(int id, String nom) {
        this.ID = id;
        this.nom = nom;
    }
    
    // --------------------- les assesseurs ----------------------------
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    

    public int getID() {
        return ID;
    }
    
    /**
     * toString() operator overload
     * @return   the result string
     */
    @Override
    public String toString() {
        return  " Nom =  " + nom;
    }
}