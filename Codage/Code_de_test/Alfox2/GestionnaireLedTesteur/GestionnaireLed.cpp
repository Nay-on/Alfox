#include "GestionnaireLed.h"


void ISR_timer4_LED_OFF(struct tc_module *const module_inst) 
{ 
  compteur++;
  if (compteur==tempsLed)
  {
    ledTri->eteindre();
    compteur = 0;
      if(etat == NORMAL)
    etat = GPS;
      else if (etat ==GPS) 
    etat = NORMAL;
    timer4->enableTimer(false);
  }
}



void allumerLed() {
  switch (etat)
  {
    case NORMAL:
      ledTri->setCouleur(cyan,255);  
      break;
    case GPS:
      ledTri->setCouleur(magenta,255);  
      break;
  } 
  timer4->enableTimer(true);
}


GestionnaireLed::GestionnaireLed(byte redPin,byte greenPin,byte bluePin,byte btnPin,int duree)
{
	maLed = new LedTri(redPin,greenPin,bluePin);
  ledTri = maLed;
	timer4_1s = new SAMDtimer(4, ISR_timer4_LED_OFF, 1e6);
  timer4=timer4_1s;
	timer4_1s->attachInterrupt(ISR_timer4_LED_OFF);
 
  pinMode(btnPin,INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(btnPin),  allumerLed, FALLING);

}

void GestionnaireLed::allumer(ETAT etat)
{
  switch (etat)
  {
    case NORMAL:
    {
      ledTri->setCouleur(bleu,255);  
    }break;
    case GPS:
    {
      ledTri->setCouleur(rouge,255);  
    }break;
  }  
}

void GestionnaireLed::eteindre()
{
  
}
