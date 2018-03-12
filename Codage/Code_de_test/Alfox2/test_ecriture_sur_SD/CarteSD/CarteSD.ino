#include "CarteSD.h"
#include "DonneesTR.h"
#include <SD.h>
#include <SPI.h>

CarteSD* carteSD = new CarteSD();
DonneesTR* donneesTR = new DonneesTR();
GPS* gps = new GPS();

void setup() {
  // put your setup code here, to run once:

  Serial.begin(9600);

}

void loop() {
  // put your main code here, to run repeatedly:
    carteSD->creerFichier("180311.txt");
    carteSD->ecrire(donneesTR,gps);
    Serial.println(donneesTR->getVitesseMoyenne());
    delay(10000);
}


