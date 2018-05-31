/*
 * Projet  : Alfox
 * Fichier : User.java
 * Description : Classe interface de la table contrat
 * Cette table stocke les contrats connus du logiciel
 */

package com.persistence;

import java.sql.*;

public class Contrat {
    private String    numero;
    private Timestamp dateCreation;
    private String    modele;
    private String    infos;
    private int       loueurID;
    private int       vehiculeID;
    private int       zoneLimiteID;
    
    /**
     * Créer un nouvel objet persistant 
     * @param con
     * @param numero
     * @param dateCreation
     * @param modele
     * @param infos
     * @param loueurID
     * @param vehiculeID
     * @param zoneLimiteID
     * @return 
     * @ return un contrat si le numero est unique sinon null
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     *                      ou le numero est deja dans la BD
     * 
     */
    static public Contrat create(Connection con, String numero, Timestamp dateCreation, String modele, String infos,
        int loueurID, int vehiculeID, int zoneLimiteID) throws Exception {
        Contrat contrat = new Contrat(numero, dateCreation, modele, infos, loueurID, vehiculeID, zoneLimiteID);
        
        String queryString =
         "insert into contrat (Numero, DateCreation, Modele, Infos, LoueurID, VehiculeID, ZoneLimiteID ) values ("
                + Utils.toString(numero) + ", " 
                + Utils.toString(dateCreation) + ", " 
                + Utils.toString(modele) + ", " 
                + Utils.toString(infos) + ", " 
                + Utils.toString(loueurID) + ", " 
                + Utils.toString(vehiculeID) + ", " 
                + Utils.toString(zoneLimiteID) 
          + ")";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
        return contrat;
    }
    
    /**
     * update de l'objet contrat dans la ConnexionMySQL
     * @param con
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString =
         "update contrat set "
                + " DateCreation = " + Utils.toString(dateCreation) + "," 
                + " Modele = " + Utils.toString(modele) + ","  
                + " Infos = " + Utils.toString(infos) + "," 
                + " LoueurID = " + Utils.toString(loueurID) + "," 
                + " VehiculeID = " + Utils.toString(vehiculeID) + "," 
                + " ZoneLimiteID = " + Utils.toString(zoneLimiteID)
                + " where Numero ='" + numero + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }
    
    /**
     * suppression de l'objet contrat dans la BD
     * @param con
     * @return 
     * @throws SQLException    impossible d'accéder à la ConnexionMySQL
     */
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from contrat where Numero='" + numero + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString);
        return true;
    }
    
    /**
     * Retourne un contrat trouve par son numero, saved is true
     * @param con
     * @param  numero le numero à trouver
     * @return Contrat contrat trouve par numero
     * @throws java.lang.Exception
     */
    public static Contrat getByNumero(Connection con, String numero) throws Exception {
        String queryString = "select * from contrat where Numero='" + numero + "'";
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
    
    private static Contrat creerParRequete(ResultSet result) throws Exception {
            String    lNumero  = result.getString("Numero");
            Timestamp lDateCreation = result.getTimestamp("DateCreation");
            String    lModele = result.getString("Modele");
            String    lInfos = result.getString("Infos");
            int       lLoueurID = result.getInt("LoueurID");
            int       lVehiculeID = result.getInt("VehiculeID");
            int       lZoneLimiteID = result.getInt("ZoneLimiteID");
            return    new Contrat(lNumero,lDateCreation, lModele, lInfos, lLoueurID, lVehiculeID, lZoneLimiteID);
    }
    
    /**
     * Cree et initialise completement Contrat
     */
    private Contrat(String numero, Timestamp dateCreation, String modele, String infos, int loueurID,
            int VehiculeID, int zoneLimiteID) {
        this.numero = numero;
        this.dateCreation = dateCreation;
        this.modele = modele;
        this.infos = infos;
        this.loueurID = loueurID;
        this.vehiculeID = VehiculeID;
        this.zoneLimiteID = zoneLimiteID;
    }
    
    // --------------------- les assesseurs ----------------------------
    public String getNumero() {
        return numero;
    }

    public Timestamp getDate() {
        return dateCreation;
    }
    public String getType() {
        return modele;
    }

    public String getInfos() {
        return infos;
    }
    
    public int getLoueurID() {
        return loueurID;
    }
    
    public int getVehiculeID() {
        return vehiculeID;
    }
    
    public int getZoneLimiteID() {
        return zoneLimiteID;
    }

    public void setType(String modele) throws Exception {
        this.modele = modele;
    }

    public void setInfos(String infos) throws Exception {
        this.infos = infos;
    }
    
    public void setLoueurID(int loueurID) throws Exception {
        this.loueurID = loueurID;
    }

    public void setVehiculeID(int VehiculeID) throws Exception {
        this.vehiculeID = VehiculeID;
    }
    
    public void setZoneLimiteID(int zoneLimiteID) throws Exception {
        this.zoneLimiteID = zoneLimiteID;
    }
    
    /**
     * toString() operator overload
     * @return   the result string
     */
    @Override
    public String toString() {
        return  " Numero =  " + numero + "\t" +
                " DateCreation = " + Utils.toString(dateCreation) + 
                " Modele = " + Utils.toString(modele) + 
                " Infos = " + Utils.toString(infos)
                + " ";
    }
}