#ifndef DONNEES_H
#define DONNEES_H
#include <SPI.h>
#include <SD.h>
#include <Arduino.h>


class DonneesTR {
  public:
    DonneesTR();
    virtual ~DonneesTR();
    void razStat();
    float getConsommation();
    int getConsoMoyenne();
    int getConsoMax();
    int getVitesseMoyenne();
    int getVitesseMax();
    int getVitesse();
    int getRegime();
    int getRegimeMoyen();
    int getRegimeMax();
    void setVitesse(int vitesse);
    void setConsommation(float consommation);
    void setRegime(int regime);
    float getDistanceParcourue();
    void majDistance();
    //void majData(OBD2 obd2);
    float getLatitude();
    float getLongitude();

    void setLatitude(float latitude);
    void setLongitude(float longitude);

    
    // pas de getDefaut ou de getNbDefaut ni de set
  private:
    float distanceParcourue;
    int nbDefauts;
    int defauts[4];
    float consommation;
    float consoMoyenne;
    float consoMax;
    int vitesse;
    int vitesseMoyenne;
    int vitesseMax;
    int regime;
    int regimeMoyen;
    int regimeMax;
    float latitude;
    float longitude;

    int moyenneRegime = 0;  //nb total de valeurs
    long valeurMoyenneRegime = 0;
    int moyenneVitesse = 0; //nb total de valeurs
    long valeurMoyenneVitesse = 0;
    int moyenneConso = 0;   //nb total de valeurs

};

#endif /* DONNEES_H */
