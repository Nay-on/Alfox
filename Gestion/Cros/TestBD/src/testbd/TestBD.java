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
            User user = User.getByMotDePasse(con, "responsable");
            System.out.println(user.getRole());
            System.out.println(user.getMail());
        
            System.out.println("Vehicule : -------------------------------------------------------------");
            Vehicule vehicule = Vehicule.getByImmatriculation(con, "AA-000-BB");
            System.out.println(vehicule.getMarque());
            System.out.println(vehicule.getModele());
            System.out.println(vehicule.getImmatriculation());
            System.out.println(vehicule.getDateMiseEnService());
            System.out.println(vehicule.getMotorisation());
            System.out.println(vehicule.getIdSigfox());
            System.out.println(vehicule.getDateVidange());
            System.out.println(vehicule.getKmVidange());
            System.out.println(vehicule.getHorsZone());
            System.out.println(vehicule.getTauxUtilisation());
            System.out.println(vehicule.getAProbleme());
            System.out.println(vehicule.getCompteurReel());
            System.out.println(vehicule.getDateControleTechnique());
            
            System.out.println("Loueur : -------------------------------------------------------------");
            Loueur loueur = Loueur.getByNom(con, "Magritte", "René");
            System.out.println(loueur.getPrenom());
            System.out.println(loueur.getTelephone());
            System.out.println(loueur.getMail());
            
            System.out.println("ZoneLimite : -------------------------------------------------------------");
            ZoneLimite zoneLimite = ZoneLimite.getByNom(con, "Toulouse");
            System.out.println(zoneLimite.getNom());
            
            System.out.println("Position : -------------------------------------------------------------");
            Position position = Position.getByOrdre(con, 1);
            System.out.println(position.getOrdre());
            System.out.println(position.getLatitude());
            System.out.println(position.getLongitude());
            
            System.out.println("Contrat : -------------------------------------------------------------");
            Contrat contrat = Contrat.getByNumero(con, "1");
            System.out.println(contrat.getDate());
            System.out.println(contrat.getType());
            System.out.println(contrat.getInfos());
            
            System.out.println("DonneesHisto : -------------------------------------------------------------");
            DonneesHisto donneesHisto = DonneesHisto.getByDatation(con, "2018-01-03 17:42:37");
            System.out.println(donneesHisto.getMode());
            System.out.println(donneesHisto.getDatation());
            System.out.println(donneesHisto.getVitesse());
            System.out.println(donneesHisto.getRegime());
            System.out.println(donneesHisto.getConsommation());
            System.out.println(donneesHisto.getVitesseMax());
            System.out.println(donneesHisto.getRegimeMax());
            System.out.println(donneesHisto.getConsoMax());
            System.out.println(donneesHisto.getNbDefauts());
            System.out.println(donneesHisto.getDefaut1());
            System.out.println(donneesHisto.getDefaut2());
            System.out.println(donneesHisto.getDefaut3());
            System.out.println(donneesHisto.getDefaut4());
            System.out.println(donneesHisto.getLatitudeGPS());
            System.out.println(donneesHisto.getLongitudeGPS());
            System.out.println(donneesHisto.getDistanceParcourue());
            
            System.out.println("DonnesTR : -------------------------------------------------------------");
            DonneesTR donneesTR = DonneesTR.getByDatation(con, "2018-01-03 17:42:47");
            System.out.println(donneesTR.getMode());
            System.out.println(donneesTR.getDatation());
            System.out.println(donneesTR.getVitesse());
            System.out.println(donneesTR.getRegime());
            System.out.println(donneesTR.getConsommation());
            System.out.println(donneesTR.getVitesseMax());
            System.out.println(donneesTR.getRegimeMax());
            System.out.println(donneesTR.getConsoMax());
            System.out.println(donneesTR.getNbDefauts());
            System.out.println(donneesTR.getDefaut1());
            System.out.println(donneesTR.getDefaut2());
            System.out.println(donneesTR.getDefaut3());
            System.out.println(donneesTR.getDefaut4());
            System.out.println(donneesTR.getLatitude());
            System.out.println(donneesTR.getLongitude());
            System.out.println(donneesTR.getDistanceParcourue());
            
            System.out.println("isDehors : -------------------------------------------------------------");
            System.out.println(vehicule.isDehors(con));
            
        } catch (Exception ex) {
            Logger.getLogger(TestBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
