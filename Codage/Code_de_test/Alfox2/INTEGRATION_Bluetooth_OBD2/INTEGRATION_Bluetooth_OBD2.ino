#include "Bluetooth.h"
#include "DonneesTR.h"
#include "OBD2.h"
#include "CarteSD.h"
//#define DEBUG
#define PERIODE_ECH 5000 //en millisecondes

Bluetooth* bluetooth;
OBD2* obd2;
DonneesTR* donneesTR;
CarteSD* carteSD;

char c = ' ';
boolean NL = true;
unsigned long periode;
unsigned long initial;

void setup()
{  
  Serial.begin(115200);
  carteSD = new CarteSD();
  
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

  //ELM327 (Bleu)
  int resultatConnexion = bluetooth->connexion("2017,11,7030A");
  //Simulateur
  //int resultatConnexion = bluetooth->connexion("18,E7,1EC629");    
  
  
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
      delay(250);
      Serial.print("Régime moteur : ");
      Serial.println(obd2->lireRegimeMoteur());
      delay(250);
      Serial.print("Consomation : ");
      Serial.println(obd2->lireConsomation());
      initial = millis();
    }
    majDataTR();
    carteSD->nouveauFichier("180516.txt");
    carteSD->ecrire(donneesTR);
}

void majDataTR(){
  donneesTR->setVitesse(obd2->lireVitesse());
  donneesTR->setRegime(obd2->lireRegimeMoteur());
  donneesTR->setConsommation(obd2->lireConsomation());
}

void SERCOM3_Handler()
{
  bluetooth->getLiaisonBT()->IrqHandler();
}
