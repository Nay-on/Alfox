#include "Bluetooth.h"
#include "OBD2.h"

Bluetooth* bluetooth;
OBD2* obd2;

char c = ' ';
boolean NL = true;

void setup()
{  
  Serial.begin(9600);
  Serial.println("Test bluetooth et obd2");
  delay(10000);
  Serial.println("Création bluetooth");
  bluetooth = new Bluetooth(PINALIM, PINEN);
  delay(10000);
  Serial.println("Connexion bluetooth");
  int resultatConnexion = bluetooth->connexion("E094,67,48C348");
  Serial.println(resultatConnexion,BIN);
  delay(5000);
  Serial.println("Création OBD2");
  obd2 = new OBD2(bluetooth);
  Serial.println(bluetooth->isActif());
}

void loop()
{
/*    if (bluetooth->getLiaisonBT()->available())
    {
      c = bluetooth->getLiaisonBT()->read();
      Serial.write(c);
    }*/
    Serial.print("Vitesse : ");
    Serial.println(obd2->lireVitesse());
    Serial.print("Régime moteur : ");
    Serial.println(obd2->lireRegimeMoteur());
    Serial.print("Consomation : ");
    Serial.println(obd2->lireConsomation());

    // Read from the Serial Monitor and send to the Bluetooth module
/*    if (Serial.available())
    {
      c = Serial.read();
      bluetooth->getLiaisonBT()->write(c);
      if (NL) { Serial.print("\r\n>");  NL = false; }
      Serial.write(c);
      if (c==10) 
      {
        NL = true; 
      }
  }*/
  delay(1000);
}

void SERCOM3_Handler()
{
  bluetooth->getLiaisonBT()->IrqHandler();
}
