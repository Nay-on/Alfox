#include <SoftwareSerial.h>
#include <Arduino.h>

class Bluetooth {

private :
  SoftwareSerial* serialBluetooth;
  

public:
  Bluetooth(int rx, int tx);
  ~Bluetooth();
  int connexion(String adresse);
  bool isActif();
  SoftwareSerial* getLiaisonBT();
  int modeMaster();
  int modeConnection();
  int motDePasse();
  int initialisation();
  int appairage(String adresse);
  int lien(String adresse);
  int modeDeconnecte();
};
