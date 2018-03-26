#include "Bluetooth.h"

Bluetooth* bluetooth;

void setup() {
  bluetooth = new Bluetooth(2,3);
  Serial.begin(9600);
  Serial.println(bluetooth->connexion("B22B,1C,70EA6"));
  Serial.println(bluetooth->isActif());
}

void loop() {
  

}
