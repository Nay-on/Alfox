#include "CarteSD.h"
#include "DonneesTR.h"
#include <SD.h>
#include <SPI.h>

CarteSD* carteSD;
DonneesTR* donneesTR;
int conso = 0;

void setup() {
  carteSD = new CarteSD();
  donneesTR = new DonneesTR();
  Serial.begin(9600);

}

void loop() {
  // put your main code here, to run repeatedly:
    carteSD->nouveauFichier("180316.txt");
    donneesTR->setConsommation(conso);
    conso = conso + 1;
    carteSD->ecrire(donneesTR);
    //carteSD->lire();
    //carteSD->effacer();
    delay(500);
}


