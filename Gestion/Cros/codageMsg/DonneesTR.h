#ifndef DONNEESTR_H
#define DONNEESTR_H

class DonneesTR {

private:
    float distanceParcourue;
    int nbDefauts;
    int defauts[4];
    int consommation;
    int consoMoyenne;
    int consoMax;
    int vitesse;
    int vitesseMoyenne;
    int vitesseMax;
    int regime;
    int regimeMoyen;
    int regimeMax;
    float latitudeGPS;
    float longitudeGPS;
    tm dateGPS;
    tm dateHTR;

public:
    void razStat();

    int getConsoMoyenne();

    int getConsoMax();

    int getVitesseMoyenne();

    int getVitesseMax();

    int getRegimeMoyen();

    int getRegimeMax();

    float getDistanceParcourue();

    float getLatitude();

    float getLongitude();

    tm   getDateHTR();

    tm   getDateGPS();

    void setVitesse(int vitesse);       // met à jour la DistanceParcourue

    void setConsommation(int consommation);

    void setRegime(int regime);
    
    void setDefauts(int codeDefauts);

    void majDistance(GPS gps);          // met à jour la DistanceParcourue 
                                        // si pas de bluetooth
    void setDateHTR(tm dateHTR);

    void setDateGPS(tm dateGPS);
    
    int getDefaut(int numero);
    
    
};

#endif
