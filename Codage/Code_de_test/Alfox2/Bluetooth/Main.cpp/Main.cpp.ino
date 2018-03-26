#include "Bluetooth.h"

Bluetooth* bluetooth;

void setup() {
  bluetooth = new Bluetooth(2,3);
  Serial.begin(9600);
  Serial.println(bluetooth->connexion("b22b,1c,070ea6"));
  Serial.println(bluetooth->isActif());
}

void loop() {
  

}
