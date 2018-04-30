package ALFOX;

import java.util.TimerTask;

public class SimulateurVoiture extends TimerTask {
    private int dureeSimulation = 100;      // 100ms entre chaque mise à jour
    private float coefVitesse = 3600*10.0f;
    private float compteur = 123456.00f;    // Compteur initial
    private String codeDefaut = "P0102";
    private float consommation = 0;
    private float vitesse = 0;
    private int regime = 0;
    private boolean contact = false;
    private boolean moteurTourne = false;
    private boolean batterieFaible = false;
    
    SimulateurVoiture(int delai) {
        new java.util.Timer().schedule(this, delai);
    }

    @Override
    public void run() {
        System.out.println("Voiture active");
        while(true) {
            attendre(dureeSimulation);
            if (contact) {
                vitesse = (regime - 1400) / 23;
                if (regime < 1450)
                    vitesse = 0;
                if (vitesse > 0) {
                    compteur = compteur + vitesse/coefVitesse;
                    consommation = 6.0f + (regime - 1400) / 1250.0f;
                }
            }
        }
    }

    public boolean isContact() {
        return contact;
    }

    public void setContact(boolean allumer) {
        if (vitesse > 0) // on ne peut couper la voiture qu'à l'arrêt
            return;
        // couper le contact à l'arrêt coupe le moteur
        if (!allumer) { 
            if (moteurTourne && (vitesse == 0)) {
                moteurTourne = false;
                regime = 0;
                vitesse = 0;
                consommation = 0;
            }
        }
        this.contact = allumer;
    }

    public void setDemarrer() {
        if (!contact)       // on ne démarre que si contact batterie
            return;
        if (!moteurTourne) {
            moteurTourne = true;
            regime = 1400;
        }
    }
    
    public boolean isBatterieFaible() {
        return batterieFaible;
    }

    public void setBatterieFaible(boolean batterieFaible) {
        this.batterieFaible = batterieFaible;
    }

    public float getCompteur() {
        return compteur;
    }

    public String getCodeDefaut() {
        return codeDefaut;
    }

    public int getRegime() {
        return regime;
    }

    public void setRegime(int regime) {
        if (!contact)   // on ne peut changer le regime que si le moteur tourne
            return;
        this.regime = regime;
    }

    public float getConsommation() {
        return consommation;
    }

    public float getVitesse() {
        return vitesse;
    }
    
// ------------------------ Utilitaires -------------------------------------
    private void attendre(int duree) {
        try {
            Thread.sleep(duree);
        } catch (InterruptedException ex) { }
    }
}