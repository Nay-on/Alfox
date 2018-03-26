#include "Bluetooth.h"
#include "OBD2.h"

Bluetooth* bluetooth;
OBD2* obd2;

void setup() {
  bluetooth = new Bluetooth(2,3);
  obd2 = new OBD2(bluetooth);
  bluetooth->connexion("18,e7,1ec629");
}


void loop() {
  
}
