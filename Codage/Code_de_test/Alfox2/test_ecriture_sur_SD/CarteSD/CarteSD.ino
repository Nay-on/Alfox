#include "CarteSD.h"
#include "DonneesTR.h"

CarteSD* carteSD = new CarteSD();
DonneesTR* donneesTR = new DonneesTR();
GPS* gps = new GPS();

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);

}

void loop() {
  // put your main code here, to run repeatedly:
    carteSD->creerFichier("20180308.txt");
    carteSD->ecrire(donneesTR,gps);
    Serial.println(donneesTR->getVitesseMoyenne());
    delay(10000);
}


