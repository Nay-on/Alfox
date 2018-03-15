#include "DonneesTR.h"
#include "GPS.h"

	DonneesTR::DonneesTR(){
  distanceParcourue = 0;
  nbDefauts = 0;
  defauts[4]=0;
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
	DonneesTR::~DonneesTR(){
	}
	

	void DonneesTR::razStat()
	{
    nbDefauts = 0;
    defauts[4]=0;
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
		this->vitesse=vitesse;
	}


	void DonneesTR::setConsommation(int consommation)
	{
		this->consommation = consommation;
	}


	void DonneesTR::setRegime(int regime)
	{
		this->regime = regime;
	}

	float DonneesTR::getDistanceParcourue()
	{
		return distanceParcourue;
	}

/*
	void DonneesTR::majDistance(GPS gps)
	{

	}


	void DonneesTR::majData(OBD2 obd2)
	{

	}*/
