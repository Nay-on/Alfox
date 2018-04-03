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
    carteSD->nouveauFichier("290322.txt");
    donneesTR->setConsommation(conso);
    conso = conso + 1;
    carteSD->ecrire(donneesTR);
    Serial.println();
    Serial.println();
    delay(1000);
}


