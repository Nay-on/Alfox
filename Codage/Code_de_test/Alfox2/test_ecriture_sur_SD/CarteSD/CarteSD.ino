#include "CarteSD.h"
#include "DonneesTR.h"
#include <SD.h>
#include <SPI.h>

CarteSD* carteSD;
DonneesTR* donneesTR;
int conso = 0;
unsigned long time1;
unsigned long time2;
unsigned long time3;
unsigned long time4;

void setup() {
  carteSD = new CarteSD();
  donneesTR = new DonneesTR();
  Serial.begin(9600);

  


}

void loop() {
    time1 = millis();
    carteSD->nouveauFichier("180322.txt");
    time2 = millis();
    Serial.println("temps creation "+ String(time2-time1));
    donneesTR->setConsommation(conso);
    conso = conso + 1;
    time3 = millis();
    carteSD->ecrire(donneesTR);
    time4 = millis();
    Serial.println("temps ecriture "+ String(time4-time3));
    //carteSD->lire();
    //Serial.println();
    //time1 = millis();
    
    //time2 = millis();
    //Serial.println("temps isFull "+ String(time2-time1));
    Serial.println();
    Serial.println();
    delay(1000);
}


