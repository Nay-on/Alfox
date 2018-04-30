/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ALFOX;

/*
    Your OBD Device claims to support PIDs:

    Mode 1 (Ce mode retourne les valeurs courantes de certains capteurs)

        02 Code défaut qui a provoqué l'enregistrement de données "gelées"
        0C Régime moteur en tours/min
        0D Vitesse du véhicule en km/h
        1F Durée depuis le démarrage du moteur
        2F Niveau de carburant en %
        5C Température d'huile moteur en °C
        5E Consommation de carburant en litre/h
        5F Consommation de carburant en litre/h
        67 Température(s) d'eau moteur en °C
    
    Mode 2 (Ce mode retourne les données instantanées au moment d'un défaut
                                    sur les même données que le mode 1)

    Mode 3 (Ce mode retourne les codes défauts enregistrés)

    Mode 4 (Ce mode permet d'effacer les codes défauts enregistrés 
                                et d'éteindre le voyant défaut du moteur MIL)

    Mode 5 (Ce mode retourne les résultats des autodiagnostics 
                                effectués sur les sondes à oxygène/lambda)

    Mode 6 (Ce mode retourne les résultats des autodiagnostics effectués sur les  
            systèmes non soumis à la surveillance constante ex: echappement)

    Mode 7 (Ce mode retourne les codes défauts non confirmés)

    Mode 8 (très peu utilisé en Europe)

    Mode 9 (Ce mode retourne les informations concernant le véhicule)
        02 Numéro d'identification du véhicule (VIN)

    Mode 10 (Ce mode retourne les codes défauts non effacables)

*/

/**
 *
 * @author jpdms
 */
public class OBD2 {
    private static boolean connected = false;
    private static SimulateurVoiture voiture = null;
    
    static boolean connection(SimulateurVoiture _voiture) {
        voiture = _voiture;
        return true;
    }
    
    static boolean isConnected() {
        return true;
    }
    
    static float lireCompteur() {
        return voiture.getCompteur();
    }
    
    static float lireVitesse() {
        return voiture.getVitesse();
    }
    
    static public int lireRegime() {
        return voiture.getRegime();
    }
    
    static public float lireConsommation() {
        return voiture.getConsommation();
    }
    
    static public String lireCodeDefaut() {
        return voiture.getCodeDefaut();
    }
    
}
