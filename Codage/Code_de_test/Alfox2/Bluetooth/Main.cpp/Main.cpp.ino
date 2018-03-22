#include "Bluetooth.h"

Bluetooth* bluetooth;

void setup() {
  bluetooth = new Bluetooth(2,3);
  Serial.begin(9600);
  Serial.println(bluetooth->connexion("18,e7,1ec629"));
  Serial.println(bluetooth->isActif());
}

void loop() {
  

}
