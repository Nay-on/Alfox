#include "Bluetooth.h"
#include "OBD2.h"
//#define DEBUG
#define PERIODE_ECH 5000 //en millisecondes

Bluetooth* bluetooth;
OBD2* obd2;

char c = ' ';
boolean NL = true;
unsigned long periode;
unsigned long initial;

void setup()
{  
  Serial.begin(115200);
#ifdef DEBUG
  Serial.println("Test bluetooth et obd2");
#endif
  delay(1000);
#ifdef DEBUG
  Serial.println("Création bluetooth");
#endif
  bluetooth = new Bluetooth(PINALIM, PINEN);
  delay(5000);
#ifdef DEBUG
  Serial.println("Connexion bluetooth");
#endif
  int resultatConnexion = bluetooth->connexion("2017,11,7030A");
#ifdef DEBUG
  Serial.println(resultatConnexion,BIN);
#endif
  delay(5000);
#ifdef DEBUG
  Serial.print("Is actif? : ");
  Serial.println(bluetooth->isActif());
  Serial.println("Création OBD2");
#endif
  obd2 = new OBD2(bluetooth);
  initial = millis();
}

void loop()
{
    periode = millis() - initial;
    //delay(5000);
/*    if (bluetooth->getLiaisonBT()->available())
    {
      c = bluetooth->getLiaisonBT()->read();
      Serial.write(c);
    }*/
    if (periode >= PERIODE_ECH)
    {
      Serial.print("Vitesse : ");
      Serial.println(obd2->lireVitesse());
      delay(200);
      Serial.print("Régime moteur : ");
      Serial.println(obd2->lireRegimeMoteur());
      delay(200);
      Serial.print("Consomation : ");
      Serial.println(obd2->lireConsomation());
      initial = millis();
    }
}

void SERCOM3_Handler()
{
  bluetooth->getLiaisonBT()->IrqHandler();
}
