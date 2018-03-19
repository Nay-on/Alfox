#include "CarteSD.h"
#include "DonneesTR.h"
#include <SD.h>
#include <SPI.h>

CarteSD* carteSD;
DonneesTR* donneesTR;
GPS* gps;

void setup() {
  carteSD = new CarteSD();
  donneesTR = new DonneesTR();
  gps = new GPS();
  Serial.begin(9600);

}

void loop() {
  // put your main code here, to run repeatedly:
    carteSD->nouveauFichier("180316.txt");
    carteSD->ecrire(donneesTR,gps);
    //carteSD->effacer();
    delay(500);
}


