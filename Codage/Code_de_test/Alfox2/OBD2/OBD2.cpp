#include "OBD2.h"

OBD2::OBD2(Bluetooth* bt){
  
  this->moduleBT = bt;
  liaisonBT = bt->getLiaisonBT();
  
}

String OBD2::demande(String code){
  liaisonBT->print(code);
  
}

String OBD2::lireReponse(){
  String reponse;
  while (Serial.available() > 0) {
    if(liaisonBT->read() != '\n'){
      reponse == liaisonBT->read();
    }
  }
}

