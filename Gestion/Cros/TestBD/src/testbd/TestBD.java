/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testbd;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.ConnexionMySQL;
import persistence.Contrat;
import persistence.DonneesHisto;
import persistence.User;
import persistence.Loueur;
import persistence.Vehicule;
import persistence.Position;
import persistence.ZoneLimite;
import persistence.DonneesTR;

/**
 *
 * @author snir2g1
 */
public class TestBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Connection con = ConnexionMySQL.newConnexion();
            
            System.out.println("User : -------------------------------------------------------------");
            testUsers(con, "responsable");
            System.out.println();
            
            System.out.println("Vehicule : ---------------------------------------------------------");
            testVehicule(con, "AA-000-BB");
            System.out.println();
            
            System.out.println("Loueur : -----------------------------------------------------------");
            testLoueur(con, "Magritte", "René");
            System.out.println();
            
            System.out.println("ZoneLimite : -------------------------------------------------------");
            testZoneLimite(con, "Toulouse");
            System.out.println();
            
            System.out.println("Position : ---------------------------------------------------------");
            testPosition(con, 1);
            System.out.println();
            
            System.out.println("Contrat : ----------------------------------------------------------");
            testContrat(con, "1");
            System.out.println();
            
            System.out.println("DonneesHisto : -----------------------------------------------------");
            testDonneesHisto(con, "2018-01-03 17:42:37");
            System.out.println();
            
            System.out.println("DonnesTR : ---------------------------------------------------------");
            testDonneesTR(con, "2018-01-03 17:42:37");
            
        } catch (Exception ex) {
            Logger.getLogger(TestBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void testUsers(Connection con, String mdp) throws Exception {
        //test de la récupération d'un user par son mdp
        User user = User.getByMotDePasse(con, mdp);
        //test des getters
        System.out.println(user.getRole());
        System.out.println(user.getMail());
        //test des setters
        System.out.println("Test des setters :");
        user.setRole("administrateur");
        user.setMail("changementdemail@gmail.com");
        user.setMdp("changementmdp");
        user.save(con);
        System.out.println(user.getRole());
        System.out.println(user.getMail());
    }
    
    public static void testVehicule(Connection con, String immatriculation) throws Exception {
        //test de la récupération d'un user par son mdp
        Vehicule vehicule = Vehicule.getByImmatriculation(con, immatriculation);
        //test des getters
        System.out.print(vehicule.getMarque() + ";");
        System.out.print(vehicule.getModele() + ";");
        System.out.print(vehicule.getImmatriculation() + ";");
        System.out.print(vehicule.getDateMiseEnService() + ";");
        System.out.print(vehicule.getMotorisation() + ";");
        System.out.print(vehicule.getIdSigfox() + ";");
        System.out.print(vehicule.getDateVidange() + ";");
        System.out.print(vehicule.getKmVidange() + ";");
        System.out.print(vehicule.getHorsZone() + ";");
        System.out.print(vehicule.getTauxUtilisation() + ";");
        System.out.print(vehicule.getAProbleme() + ";");
        System.out.print(vehicule.getCompteurReel() + ";");
        System.out.println(vehicule.getDateControleTechnique() + ";");
        try {
            //Obtention de la date de dernière données TR reçue
            System.out.println("getLastDatation : --------------------------------------------------");
            System.out.println(vehicule.getLastDatation(con));
            //Le véhicule est-il dans sa zone associée?
            System.out.println("isDehors : ---------------------------------------------------------");
            System.out.println(vehicule.isDehors(con));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        //test des setters
        System.out.println("Test des setters :");
        vehicule.setAProbleme(true);
        vehicule.setCompteurReel(5050);
        vehicule.setDateControleTechnique("20-03-2018");
        vehicule.setDateVidange("20-03-2018");
        vehicule.setHorsZone(true);
        vehicule.setTauxUtilisation(5);
        vehicule.save(con);
        System.out.print(vehicule.getAProbleme() + ";");
        System.out.print(vehicule.getCompteurReel() + ";");
        System.out.print(vehicule.getDateControleTechnique() + ";");
        System.out.print(vehicule.getDateVidange() + ";");
        System.out.print(vehicule.getHorsZone() + ";");
        System.out.println(vehicule.getTauxUtilisation() + ";");
    }
    
    public static void testLoueur(Connection con, String nom, String prenom) throws Exception {
        //test de la récupération d'un loueur par son nom, prénom
        Loueur loueur = Loueur.getByNom(con, nom, prenom);
        //test des getters
        System.out.print(loueur.getPrenom() + ";");
        System.out.print(loueur.getTelephone() + ";");
        System.out.println(loueur.getMail() + ";");
        //test des setters
        System.out.println("Test des setters :");
        loueur.setMail("mailchange@gmail.com");
        loueur.setTelephone("0607080910");
        loueur.save(con);
        System.out.print(loueur.getTelephone() + ";");
        System.out.println(loueur.getMail() + ";");
    }
    
    public static void testZoneLimite(Connection con, String zone) throws Exception {
        //test de la récupération d'une zone par son nom
        ZoneLimite zoneLimite = ZoneLimite.getByNom(con, zone);
        //test du getter
        System.out.println(zoneLimite.getNom() + ";");
        //test des setters
        System.out.println("Test des setters :");
        zoneLimite.setNom("Sud");
        zoneLimite.save(con);
        System.out.println(zoneLimite.getNom() + ";");
    }
    
    public static void testPosition(Connection con, int ordre) throws Exception {
        //test de la récupération d'une position par son ordre
        Position position = Position.getByOrdre(con, ordre);
        //test des getters
        System.out.print(position.getOrdre() + ";");
        System.out.print(position.getLatitude() + ";");
        System.out.println(position.getLongitude() + ";");
        //test des setters
        System.out.println("Test des setters :");
        position.setOrdre(9);
        position.setLatitude((float)40.456789);
        position.setLongitude((float)41.123456);
        position.save(con);
        System.out.print(position.getOrdre() + ";");
        System.out.print(position.getLatitude() + ";");
        System.out.println(position.getLongitude() + ";");
    }
    
    public static void testContrat(Connection con, String numero) throws Exception {
        //test de la récupération d'un contrat par numéro
        Contrat contrat = Contrat.getByNumero(con, numero);
        //test des getters
        System.out.print(contrat.getDate() + ";");
        System.out.print(contrat.getType() + ";");
        System.out.println(contrat.getInfos() + ";");
        //test des setters
        System.out.println("Test des setters :");
        contrat.setInfos("blablabla");
        contrat.setType("blablablablabla");
        contrat.save(con);
        System.out.print(contrat.getType() + ";");
        System.out.println(contrat.getInfos() + ";");
        
    }
    
    public static void testDonneesHisto(Connection con, String date) throws Exception {
        //test de la récupération d'une donnée par date
        DonneesHisto donneesHisto = DonneesHisto.getByDatation(con, date);
        //test des getters
        System.out.print(donneesHisto.getMode() + ";");
        System.out.print(donneesHisto.getDatation() + ";");
        System.out.print(donneesHisto.getVitesse() + ";");
        System.out.print(donneesHisto.getRegime() + ";");
        System.out.print(donneesHisto.getConsommation() + ";");
        System.out.print(donneesHisto.getVitesseMax() + ";");
        System.out.print(donneesHisto.getRegimeMax() + ";");
        System.out.print(donneesHisto.getConsoMax() + ";");
        System.out.print(donneesHisto.getNbDefauts() + ";");
        System.out.print(donneesHisto.getDefaut1() + ";");
        System.out.print(donneesHisto.getDefaut2() + ";");
        System.out.print(donneesHisto.getDefaut3() + ";");
        System.out.print(donneesHisto.getDefaut4() + ";");
        System.out.print(donneesHisto.getLatitudeGPS() + ";");
        System.out.print(donneesHisto.getLongitudeGPS() + ";");
        System.out.println(donneesHisto.getDistanceParcourue() + ";");
    }
    
    public static void testDonneesTR(Connection con, String date) throws Exception {
        //test de la récupération d'une donnée par date
        DonneesTR donneesTR = DonneesTR.getByDatation(con, "2018-01-03 17:42:47");
        //test des getters
        System.out.print(donneesTR.getMode() + ";");
        System.out.print(donneesTR.getDatation() + ";");
        System.out.print(donneesTR.getVitesse() + ";");
        System.out.print(donneesTR.getRegime() + ";");
        System.out.print(donneesTR.getConsommation() + ";");
        System.out.print(donneesTR.getVitesseMax() + ";");
        System.out.print(donneesTR.getRegimeMax() + ";");
        System.out.print(donneesTR.getConsoMax() + ";");
        System.out.print(donneesTR.getNbDefauts() + ";");
        System.out.print(donneesTR.getDefaut1() + ";");
        System.out.print(donneesTR.getDefaut2() + ";");
        System.out.print(donneesTR.getDefaut3() + ";");
        System.out.print(donneesTR.getDefaut4() + ";");
        System.out.print(donneesTR.getLatitude() + ";");
        System.out.print(donneesTR.getLongitude() + ";");
        System.out.println(donneesTR.getDistanceParcourue() + ";");
    }
}