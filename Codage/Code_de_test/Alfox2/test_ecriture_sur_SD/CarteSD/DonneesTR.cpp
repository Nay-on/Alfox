#include "DonneesTR.h"	

	DonneesTR::DonneesTR(){
	}
	DonneesTR::~DonneesTR(){
	}
	

	void DonneesTR::razStat()
	{

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
