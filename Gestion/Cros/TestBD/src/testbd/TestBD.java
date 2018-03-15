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
            User user = User.getByMotDePasse(con, "responsable");
            System.out.println(user.getRole());
            System.out.println(user.getMail());
            
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
            
            Loueur loueur = Loueur.getByNom(con, "Magritte", "René");
            System.out.println(loueur.getPrenom());
            System.out.println(loueur.getTelephone());
            System.out.println(loueur.getMail());
            
            Position position = Position.getByOrdre(con, 1);
            System.out.println(position.getOrdre());
            System.out.println(position.getLatitude());
            System.out.println(position.getLongitude());
            
            ZoneLimite zoneLimite = ZoneLimite.getByNom(con, "Toulouse");
            System.out.println(zoneLimite.getNom());
            System.out.println(zoneLimite.isDehors((float)43.591344, (float)1.422877));
            
            Contrat contrat = Contrat.getByNumero(con, "0001");
            System.out.println(contrat.getDate());
            System.out.println(contrat.getType());
            System.out.println(contrat.getInfos());
            
            DonneesHisto donnesHisto = DonneesHisto.getByDatation(con, "2018-13-03");
        } catch (Exception ex) {
            Logger.getLogger(TestBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
