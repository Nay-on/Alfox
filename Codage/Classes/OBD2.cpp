#include "OBD2.h"

OBD2::OBD2(Bluetooth* bt){
  
  this->moduleBT = bt;
  liaisonBT = moduleBT->getLiaisonBT();
  liaisonBT->write("ATS0");
  liaisonBT->write("ATH0");
  liaisonBT->write("ATR0");


}

int OBD2::demande(int numCode){
  liaisonBT->print(code[numCode]);
  String a = lireReponse();
  return a.toInt();
  
}

String OBD2::lireReponse(){
  String reponse;
  while (Serial.available() > 0) {
    if(liaisonBT->read() != '\n'){
      reponse == (String)liaisonBT->read();
    }
  }
  return reponse;
}

