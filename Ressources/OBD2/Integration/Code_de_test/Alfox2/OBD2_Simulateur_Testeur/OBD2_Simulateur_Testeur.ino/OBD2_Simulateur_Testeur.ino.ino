#include "Bluetooth.h"
#include "DonneesTR.h"
#include "OBD2.h"
#include "CarteSD.h"
#define DEBUG

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
  Serial.begin(9600);
  carteSD = new CarteSD();
  donneesTR = new DonneesTR();
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
  //Connexion au module bleu
  //int resultatConnexion = bluetooth->connexion("2017,11,7030A");
  
  //Connexion au module noir KONNWEI
  //int resultatConnexion = bluetooth->connexion("B22B,1C,70EA6");

  //Connexion au module bleu Verseillie
  //int resultatConnexion = bluetooth->connexion("2017,09,300c25"); 
  
  
//#ifdef DEBUG
//  Serial.println(resultatConnexion,BIN);
//#endif
  delay(5000);
#ifdef DEBUG
  Serial.print("Is actif? : ");
  //Serial.println(bluetooth->isActif());
  bluetooth->isActif();
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
//      Serial.print("Vitesse : ");
//      Serial.println(obd2->lireVitesse());
//      delay(250);
//      Serial.print("Régime moteur : ");
//      Serial.println(obd2->lireRegimeMoteur());
//      delay(250);
//      Serial.print("Consomation : ");
//      Serial.println(obd2->lireConsomation());
      initial = millis();
      majDataTR();
      Serial.println("***************************************************************************************");
      Serial.println("Vitesse actuelle : "+String(donneesTR->getVitesse())+" km/h");
      Serial.println("Vitesse Max : "+String(donneesTR->getVitesseMax())+" km/h");
      Serial.println("Régime moteur : "+String(donneesTR->getRegime())+" tr/mn");
      Serial.println("Régime max : "+String(donneesTR->getRegimeMax())+" tr/mn");
      donneesTR->majDistance();
      Serial.println("Disatnce parcourue : "+String(donneesTR->getDistanceParcourue())+" m"); 
      Serial.println("***************************************************************************************");
      Serial.println();
      Serial.println();
      //carteSD->nouveauFichier("180516.txt");
      //carteSD->ecrire(donneesTR);
    }
    
}

void majDataTR()
{
  donneesTR->setVitesse(obd2->lireVitesse());
  delay(500);
  donneesTR->setRegime(obd2->lireRegimeMoteur());
  delay(500);
  donneesTR->setConsommation(obd2->lireConsomation());
  delay(500);
}

void SERCOM3_Handler()
{
  bluetooth->getLiaisonBT()->IrqHandler();
}
