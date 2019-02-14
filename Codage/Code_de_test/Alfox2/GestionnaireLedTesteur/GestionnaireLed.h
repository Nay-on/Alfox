#ifndef GESTIONNAIRELED_H
#define GESTIONNAIRELED_H


#include "LedTri.h"
#include "avdweb_SAMDtimer.h"
//#define tempsLed 5

enum ETAT {NORMAL=0,GPS};

static volatile int etat = NORMAL ;
static int tempsLed;

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
