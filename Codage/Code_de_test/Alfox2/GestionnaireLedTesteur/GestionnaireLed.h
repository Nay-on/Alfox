#ifndef GESTIONNAIRELED_H
#define GESTIONNAIRELED_H

//*****************************************************************************
// Description : Classe GestionnaireLed qui permet de gérer l'allumage et la 
// couleur de la led en fonction de l'état du système lors de l'appui simple 
// sur le bouton.
//
// La classe utilise les interruptions matérielles
//
// Installation : installer la bibliothèque avdweb_SAMDtimer library ainsi que
// ses dépendances 
// voir : https://www.avdweb.nl/arduino/timing/samd21-timer
//
// *****************************************************************************

#include "LedTri.h"
#include "avdweb_SAMDtimer.h"
#define tempsLed 5

enum ETAT {NORMAL=0,GPS};

static ETAT etat=GPS;

//const byte LED_PIN = 6;
//const byte BTN_PIN = 5;
//volatile byte state = LOW;
//volatile byte varCompteur = 0;
//volatile bool LED_ON = false;
static volatile int compteur = 0;
//int compt = 0;

static LedTri *ledTri;
static SAMDtimer *timer4;


class GestionnaireLed
{
  private:
	byte ledPin;
	byte btnPin;
	int duree;
  
  LedTri *maLed;
  SAMDtimer *timer4_1s;

  public:
	GestionnaireLed(byte redPin,byte greenPin,byte bluePin,byte btnPin,int duree=5);
	void allumer(ETAT etat);
	void eteindre();
};


#endif
