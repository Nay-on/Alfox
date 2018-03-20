#include "Bluetooth.h"

Bluetooth* bluetooth;

void setup() {
  bluetooth = new Bluetooth(2,3);
  Serial.begin(9600);
  bluetooth->connexion("18,e7,1ec629");
  bluetooth->isActif();
}

void loop() {
  

}
