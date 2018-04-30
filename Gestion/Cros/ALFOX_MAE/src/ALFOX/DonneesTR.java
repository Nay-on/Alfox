package ALFOX;

/**
 *
 * @author jpdms
 */
public class DonneesTR {
    public enum ModeVehicule {CONTACT_OFF, CONTACT_ON, ARRET, ROULAGE};
    
    private OBD2 obd2;
    private ModeVehicule modeVehicule = ModeVehicule.CONTACT_OFF; // non utilisé
    private float compteur = 0;     // Compteur initial
    private int codeDefauts[] = new int[4];
    private int consommation = 0;    // en dl
    private float consoMoyenne = 0;    // en dl
    private int consoMax = 0;        // en dl
    private int vitesse = 0;
    private int vitesseMax = 0;
    private float vitesseMoyenne = 0;
    private int regime = 0;          // en 100 tr/mn
    private int regimeMax = 0;       // en 100 tr/mn
    private float regimeMoyen = 0;     // en 100 tr/mn
    private int nbConso = 0;
    private int nbVitesse = 0;
    private int nbRegime = 0;

    // Constructeur
    public void DataVehicule(OBD2 _obd2) {
        this.obd2 = _obd2;
    }

    public int[] getCodeDefauts() {
        return codeDefauts;
    }

    public int getCodeDefaut(int i) {
        return codeDefauts[i];
    }

    public void setCodeDefaut(int i, int code) {
        codeDefauts[i] = code;
    }
    
    public void lireOBD2Courant() {
        // avec maj des moyenne et max
        setCompteur((int)OBD2.lireCompteur());
        
        // regime en 100tr/mn
        setRegime((OBD2.lireRegime() + 50)/100);
        setVitesse((int)OBD2.lireVitesse());
        // conso en dl
        setConsommation((int)((OBD2.lireConsommation() + 0.05)*10));
         
        // modeVehicule = ModeVehicule.CONTACT_OFF;
    }

    public void razStat() {
        lireOBD2Courant();
        regimeMoyen = regime;
        vitesseMoyenne = vitesse;
        consoMoyenne = consommation;
        regimeMax = regime;
        vitesseMax = vitesse;
        consoMax = consommation;
        nbRegime = 1;
        nbVitesse = 1;
        nbConso = 1;
    }

    public void setConsommation(int _consommation) {
        consommation = _consommation;
        if (consommation > consoMax)
            consoMax = consommation;
        if (nbConso == 0) {
            consoMoyenne = consommation;
        }
        else {
            // le produit donne de grandes valeurs
            consoMoyenne = (consoMoyenne * nbConso + consommation)/(nbConso + 1);
        }
        nbConso += 1;
    }

    public void setVitesse(int _vitesse) {
        vitesse = _vitesse;
        if (vitesse > vitesseMax)
            vitesseMax = vitesse;
        if (nbVitesse == 0) {
            vitesseMoyenne = vitesse;
            System.out.println("init");
        }
        else {
            // le produit donne de grandes valeurs
            vitesseMoyenne = (vitesseMoyenne * nbVitesse + vitesse)/(nbVitesse + 1);
        }
        nbVitesse += 1;
    }

    public void setRegime(int _regime) {
        regime = _regime;
        if (regime > regimeMax)
            regimeMax = regime;
        if (nbRegime == 0) {
            regimeMoyen = regime;
        }
        else {
            // le produit donne de grandes valeurs
            regimeMoyen = (regimeMoyen * nbRegime + regime)/(nbRegime + 1);
        }
        nbRegime += 1;
    }

    public int getConsommation() {
        return consommation;
    }

    public int getConsoMoyenne() {
        return (int)consoMoyenne;
    }

    public int getConsoMax() {
        return consoMax;
    }

    public int getVitesse() {
        return vitesse;
    }

    public int getVitesseMoyenne() {
        return (int)vitesseMoyenne;
    }

    public int getVitesseMax() {
        return vitesseMax;
    }
    
    public int getRegime() {
        return regime;
    }

    public int getRegimeMoyen() {
        return (int)regimeMoyen;
    }

    public int getRegimeMax() {
        return regimeMax;
    }

    public float getCompteur() {
        return compteur;
    }

    public void setCompteur(float _compteur) {
        compteur = _compteur;
    }
    
    public ModeVehicule getModeVehicule() {
        return modeVehicule;
    }

    public void setModeVehicule(ModeVehicule _modeVehicule) {
        modeVehicule = _modeVehicule;
    }
    
}
