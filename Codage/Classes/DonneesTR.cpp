#include "DonneesTR.h"


DonneesTR::DonneesTR() {
  distanceParcourue = 0;
  nbDefauts = 0;
  defauts[4] = 0;
  consommation = 0;
  consoMoyenne = 0;
  consoMax = 0;
  vitesse = 0;
  vitesseMoyenne = 0;
  vitesseMax = 0;
  regime = 0;
  regimeMoyen = 0;
  regimeMax = 0;
  latitude = 0;
  longitude = 0;
}

DonneesTR::~DonneesTR() {
}


void DonneesTR::razStat()
{
  nbDefauts = 0;
  defauts[4] = 0;
  consommation = 0;
  consoMoyenne = 0;
  consoMax = 0;
  vitesse = 0;
  vitesseMoyenne = 0;
  vitesseMax = 0;
  regime = 0;
  regimeMoyen = 0;
  regimeMax = 0;
  latitude = 0;
  longitude = 0;
}

float DonneesTR::getConsommation()
{
  return consommation;
}


int DonneesTR::getConsoMoyenne()
{
  return consoMoyenne;
}

int DonneesTR::getConsoMax()
{
  return consoMax;
}


int DonneesTR::getVitesseMoyenne()
{
  return vitesseMoyenne;
}


int DonneesTR::getVitesseMax()
{
  return vitesseMax;
}


int DonneesTR::getVitesse()
{
  return vitesse;
}


int DonneesTR::getRegimeMoyen()
{
  return regimeMoyen;
}


int DonneesTR::getRegimeMax()
{
  return regimeMax;
}


int DonneesTR::getRegime()
{
  return regime;
}

void DonneesTR::setVitesse(int vitesse)
{
  this->vitesse = vitesse;
  if (vitesse > vitesseMax)
    vitesseMax = vitesse;

  valeurMoyenneVitesse *= moyenneVitesse;
  valeurMoyenneVitesse += vitesse;
  moyenneVitesse++;
  valeurMoyenneVitesse /= moyenneVitesse;
  vitesseMoyenne = valeurMoyenneVitesse ;
}


void DonneesTR::setConsommation(float consommation)
{
  float vitesseFloat = this->vitesse;
  this->consommation = ((1/vitesseFloat)*(consommation))*100;

}


void DonneesTR::setRegime(int regime)
{
  this->regime = regime;
  if (regime > regimeMax)
    regimeMax = regime;

  valeurMoyenneRegime *= moyenneRegime;
  valeurMoyenneRegime += regime;
  moyenneRegime++;
  valeurMoyenneRegime /= moyenneRegime;
  regimeMoyen = valeurMoyenneRegime ;
}


float DonneesTR::getDistanceParcourue()
{
  return distanceParcourue;
}


void DonneesTR::majDistance() // d = vt
{
  distanceParcourue += (vitesse*5)/3.6;
}


void DonneesTR::setLatitude(float latitude){
  this->latitude = latitude;
}


void DonneesTR::setLongitude(float longitude){
  this->longitude = longitude;
}


float DonneesTR::getLatitude() {
	return latitude;
}


float DonneesTR::getLongitude() {
	return longitude;
}
/*
	void DonneesTR::majData(OBD2 obd2)
	{

	}*/
