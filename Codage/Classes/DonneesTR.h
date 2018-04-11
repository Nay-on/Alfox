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
    int getConsommation();
    int getConsoMoyenne();
    int getConsoMax();
    int getVitesseMoyenne();
    int getVitesseMax();
    int getRegimeMoyen();
    int getRegimeMax();
    void setVitesse(int vitesse);
    void setConsommation(int consommation);
    void setRegime(int regime);
    float getDistanceParcourue();
    void majDistance();
    //void majData(OBD2 obd2);
    float getLatitude() {
      return latitude;
    };
    float getLongitude() {
      return longitude;
    };

    // pas de getDefaut ou de getNbDefaut ni de set
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
    float latitude;
    float longitude;

    int moyenneRegime = 0;  //nb total de valeurs
    long valeurMoyenneRegime = 0;
    int moyenneVitesse = 0; //nb total de valeurs
    long valeurMoyenneVitesse = 0;
    int moyenneConso = 0;   //nb total de valeurs

};

#endif /* DONNEES_H */
