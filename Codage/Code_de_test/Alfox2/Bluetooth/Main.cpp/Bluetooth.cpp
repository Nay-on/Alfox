#include "Bluetooth.h"

Bluetooth::Bluetooth(int rx, int tx){
  serialBluetooth = new SoftwareSerial(rx,tx);
  serialBluetooth->begin(9600);
}

void Bluetooth::connexion(){
  
}

