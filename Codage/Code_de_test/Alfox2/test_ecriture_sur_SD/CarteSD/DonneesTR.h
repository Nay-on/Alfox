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
//	void majDistance(GPS gps);
//	void majData(OBD2 obd2);
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

};

#endif /* DONNEES_H */
