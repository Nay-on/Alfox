#include "Bluetooth.h"

Bluetooth* bluetooth = new Bluetooth(2,3);

void setup() {
  Serial.begin(9600);
  bluetooth->connexion("18,e7,1ec629");
}

void loop() {
  

}
