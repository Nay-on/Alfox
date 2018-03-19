#include "/home/snir2g2/Bureau/Alfox/Codage/Code_de_test/Alfox2/Bluetooth/Main.cpp/Bluetooth.h"
#include "OBD2.h"

Bluetooth* bluetooth = new Bluetooth(2,3);
OBD2* obd2 = new OBD2(bluetooth);

void setup() {
  bluetooth->connexion("18,e7,1ec629");
}

void loop() {

}
