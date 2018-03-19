void setup() {
  // put your setup code here, to run once:

}

loop() {
//variable
  const int dureeEnSeconde = 0;
  
//debut ??
    // millis retourne un entier contenant le nombre de millisecondes depuis le démarrage du programme
    unsigned long heureDebut = millis();
    // ---------- TRAITEMENTS SYSTEMATIQUES -------------
    // le GPS, on récupère l'heure et la position
    if gps.isDispo() {
        donneesTR.setLatitude(gps.getLatitude());
        donneesTR.setLongitude(gps.getLongitude());
        donneesTR.setDateGPS(gps.getDate());
    }
    // Horloge Temps Réel
    donneesTR.setDateHTR(htr.getDate());
    // LED
    led.setCouleur(couleurEtatCrt);
    // ---------------------------------------------------
    // ------------- TRAITEMENTS EVENEMENTS -------------
    // réception d’un message Sigfox de changement d’état
    if sigfox.msgPresent() {
        msg = sigfox.lire;
        nouvelEtat = message.decode(msg);
        mae.nouvelEvent(nouvelEtat);
    }
    // Surveillance du Bluetooth
    if (BLUETOOTH_ON) {
        if (mae.getEtat = DEGRADE) {
            mae.nouvelEvent(BLUETOOTH_ON);
        }
    else  (mae.getEtat = NORMAL) 
        mae.nouvelEvent(BLUETOOTH_OFF);     
    }
    // Surveillance de la voie série
    if serial a reçu un caractère {
        car <- serial.lire
        if car =’#’ {
          mae.nouvelEvent(MAINTENANCE);
        }
    }    
    // ---------------------------------------------------
    // -------------- TRAITEMENTS COURANTS ---------------
    mae.traiterEtat(DO);
    // ------- Cycle de traitement de 1s exactement ------
    dormir(1000 – (millis - heureDebut));
    dureeEnSeconde = dureeEnSeconde + 1;
//fin ????
    
    // ---------------------------------------------------
    
    // Procédures appelées dans le traitement DO de la MAE
//traitement12mn
//debut ??
    sigfox.envoyer(message.codage(mae.etat, donneesTR));
    carteSD.ecrire(donneesTR);
//fin  ??
// on le traite en une seule fois (c’est suspensif)
// durée approximative 3x50ms (A vérifier)
// les setter de donnéesTR font les calculs de moyennes, max et km
// acqDonneesVehicule
// debut
        obd2.demande(‘vitesse’);
        vitesse = obd2.lire();
        donneesTR.setVitesse(vitesse);
        obd2.demande(‘regime’);
        regime = obd2.lire();
        donneesTR.setVitesse(regime);
        obd2.demande(‘consommation’);
        consommation <- obd2.lire();
        donneesTR.setVitesse(consommation);
        obd2.demande(‘Defauts’);
        defauts = obd2.lire();
        if defauts {
            nbDefauts = nbDefauts + 1;
            donneesTR.setDefauts(defauts);
        }
}
// ---------------------------------------------------
