#include <SoftwareSerial.h>
#include <Arduino.h>

class Bluetooth {

private :
  SoftwareSerial* serialBluetooth;

public:
  Bluetooth(int rx, int tx);
  ~Bluetooth();
  bool connexion(String adresse);
  bool isActif();
  SoftwareSerial* getLiaisonBT() {return serialBluetooth;};
};
