/*
  Librairie SoftwareSerial.h disponible dans IDE Arduino ---> Croquis ---> Inclure une bibliothèque ---> Gérer les bibliothèques
  ---> Rechercher et installer SoftwareSerial
*/


#ifndef __BLUETOOTH__
#define __BLUETOOTH__

#include <Arduino.h>
#include "wiring_private.h"

class Bluetooth {

private :
  Uart* serialBT;
  

public:
  Bluetooth();
  ~Bluetooth();
  int connexion(String adresse);
  bool isActif();
  Uart* getLiaisonBT();
  int modeMaster();
  int modeConnection();
  int motDePasse();
  int initialisation();
  int appairage(String adresse);
  int lien(String adresse);
  int modeDeconnecte();
};

#endif
