#include "LiaisonSimulateur.h"
#include "Bluetooth.h"
#include "OBD2.h"

LiaisonSimulateur* liaison;
//Bluetooth* bluetooth;
OBD2* obd2;

void setup() {
  liaison = new LiaisonSimulateur(PINALIM, PINEN);
  //bluetooth = new Bluetooth(PINALIM, PINEN);
  obd2 = new OBD2(liaison);
}


void loop() {
  
}
