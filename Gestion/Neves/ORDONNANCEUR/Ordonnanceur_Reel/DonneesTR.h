#ifndef DONNEES_H
#define DONNEES_H
#include <SPI.h>
#include <SD.h>
#include "GPS.h"
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
	void majDistance(GPS gps);
	//void majData(OBD2 obd2);
	float getLatitude();
	float getLongitude();
	
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
  int moyenneRegime = 0;  //nb total de valeurs
  long valeurMoyenneRegime = 0;
  int moyenneVitesse = 0; //nb total de valeurs
  int moyenneConso = 0;   //nb total de valeurs

};

#endif /* DONNEES_H */
