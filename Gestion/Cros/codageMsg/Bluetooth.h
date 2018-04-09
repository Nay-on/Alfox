/*
  Librairie SoftwareSerial.h disponible dans IDE Arduino ---> Croquis ---> Inclure une bibliothèque ---> Gérer les bibliothèques
  ---> Rechercher et installer SoftwareSerial
*/


#ifndef __BLUETOOTH__
#define __BLUETOOTH__

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

#endif
