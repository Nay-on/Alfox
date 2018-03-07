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
	// pas de getDefaut ou de getNbDefaut
private:
	float distanceParcourue = 0;
	int nbDefauts = 0;
	int defauts[4];
	int consommation = 0;
	int consoMoyenne = 0;
	int consoMax = 0;
	int vitesse = 0;
	int vitesseMoyenne = 0;
	int vitesseMax = 0;
	int regime = 0;
	int regimeMoyen = 0;
	int regimeMax = 0;

};

#endif /* CARTESD_H */
