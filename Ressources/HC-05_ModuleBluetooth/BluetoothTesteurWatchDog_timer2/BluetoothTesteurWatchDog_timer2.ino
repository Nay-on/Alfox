#include "Bluetooth.h"
//#include "avdweb_SAMDtimer.h"


Bluetooth* bluetooth;
volatile int watchDog=0;
char c = ' ';
boolean NL = true;

void setup() {
  //Configuration du Timer/WatchDog
  //SAMDtimer *timerWatchBT = new SAMDtimer(4,compter_1mn,1e6);
  
  Serial.begin(115200);
  delay(10000);
  Serial.println("Test de la classe Bluetooth");
  bluetooth = new Bluetooth(PINALIM, PINEN);
  Serial.println("Attente connexion automatique (CMODE=0)");
  delay(10000);
  
  //Pour le module noir KONNWEI
  //Serial.println(bluetooth->connexion("B22B,1C,70EA6"),BIN);

  //Pour le module bleu OBD2
  int resultatConnexion = bluetooth->connexion("2017,11,7030A");
  //Serial.println(resultatConnexion,BIN);

  //Pour le module HC-06
  //Serial.println(bluetooth->connexion("0013,12,267317"),BIN);
  //Pour le Simulateur
  //Serial.println(bluetooth->connexion("18,E7,1EC629"),BIN);
  
  delay(5000);
  Serial.print("Is actif ? : ");
  Serial.println(bluetooth->isActif(),BIN);
  Serial.println();
  
}

void loop() 
{
    if (bluetooth->getLiaisonBT()->available())
    {
      c = bluetooth->getLiaisonBT()->read();
      Serial.write(c);
    }

    // Read from the Serial Monitor and send to the Bluetooth module
    if (Serial.available())
    {
      c = Serial.read();
      bluetooth->getLiaisonBT()->write(c);
      if (NL) { Serial.print("\r\n>");  NL = false; }
      Serial.write(c);
      if (c==10) 
      {
        NL = true; 
      }
    }
    if (watchDog>=60)
    {
      watchDog=0;
      if(!bluetooth->isActif())
      {
        bluetooth->connexion("2017,11,7030A");  
      }     
    }
}

void SERCOM3_Handler()
{
  bluetooth->getLiaisonBT()->IrqHandler();
}

//Routine d'interruption du Timer 4
void compter_1mn(struct tc_module *const module_inst)
{
  watchDog++;
}

