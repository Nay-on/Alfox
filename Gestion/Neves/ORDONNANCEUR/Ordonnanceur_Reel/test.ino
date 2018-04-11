#include "DonneesTR.h"

DonneesTR* donneesTR;
void majDataTR();

int testRegime = 0x410C2B34;       //Seulement pour les tests sans bluetooth
//int testVitesse = ;

void setup() {
  Serial.begin(115200);
  donneesTR = new DonneesTR();
  delay(50);
  majDataTR();

  }

void loop() {
  
  
}

void majDataTR(){
  
  //test += 0xffff;             //remplacer test par un obd2->demande(C_REGIME);
  long a = testRegime>>8;
  //a >>=8;
  long b = testRegime<<8;
  b /= 0x100;
  //Serial.println(test, HEX);        //DEBUG : affiche les valeurs sans la console
  //Serial.println(a, HEX);           //DEBUG : affiche les valeurs sans la console
  //Serial.println(b, HEX);           //DEBUG : affiche les valeurs sans la console
  long testest = ((a*256)+b)/4;
  //Serial.print("Régime moteur : ");       //DEBUG : affiche les valeurs sans la console
  //Serial.print(testest);                  //DEBUG : affiche les valeurs sans la console
  donneesTR->setRegime(((a*256)+b)/4);
  
}

