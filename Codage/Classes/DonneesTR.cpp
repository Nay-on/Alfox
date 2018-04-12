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
}

int DonneesTR::getConsommation()
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


int DonneesTR::getRegimeMoyen()
{

  return regimeMoyen;
}


int DonneesTR::getRegimeMax()
{
  return regimeMax;
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


void DonneesTR::setConsommation(int consommation)
{
  this->consommation = consommation;
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

/*
	void DonneesTR::majData(OBD2 obd2)
	{

	}*/
