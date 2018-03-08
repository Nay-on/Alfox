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
import persistence.User;
import persistence.Loueur;

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
            /*
            Contrat contrat = Contrat.getByNumero(con, "0001");
            System.out.println(contrat.getDate());
            System.out.println(contrat.getType());
            System.out.println(contrat.getInfos());
            */
            Loueur loueur = Loueur.getByNom(con, "Michel");
            System.out.println(loueur.getPrenom());
            System.out.println(loueur.getTelephone());
            System.out.println(loueur.getMail());
        } catch (Exception ex) {
            Logger.getLogger(TestBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
