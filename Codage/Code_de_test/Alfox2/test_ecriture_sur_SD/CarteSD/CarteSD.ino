#include "CarteSD.h"
#include "DonneesTR.h"

CarteSD carteSD;
DonneesTR donneesTR;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);

}

void loop() {
  // put your main code here, to run repeatedly:
    carteSD.effacer();
    Serial.println(donneesTR.getVitesseMoyenne());
    delay(10000);
}
