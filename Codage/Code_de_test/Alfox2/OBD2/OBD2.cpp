#include "OBD2.h"

OBD2::OBD2(Bluetooth* bt){
  
  this->moduleBT = bt;
  liaisonBT = bt->getLiaisonBT();
  
}

String OBD2::demande(String code){
  
}

