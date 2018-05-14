#include "OBD2.h"

OBD2::OBD2(Bluetooth* bt){
  
  this->moduleBT = bt;
  liaisonBT = moduleBT->getLiaisonBT();
  liaisonBT->write("ATS0");
  liaisonBT->write("ATH0");
  liaisonBT->write("ATR0");
  liaisonBT->write("ATSP");


}

String OBD2::demande(TCode numCode){
  liaisonBT->print(code[numCode]);
  String a = lireReponse();
  return a/*.toInt()*/;
  
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

int OBD2::lireVitesse(){
  char trame[demande(C_VITESSE).length()+1];       //remplacer testVitesse par: obd2->demande(C_VITESSE).length()+1;     /*      /!\      */
  demande(C_VITESSE).substring(4, 6).toCharArray(trame,demande(C_VITESSE).substring(4, 6).length()+1); //remplacer testVitesse par: obd2->demande(C_VITESSE);     /*      /!\      */
  int a = strtoul(trame,NULL,16);
  return a;
}

int OBD2::lireRegimeMoteur(){
  char trameA[demande(C_REGIME).length()+1];        //remplacer testRegime par: obd2->demande(C_REGIME);     /*      /!\      */
  char trameB[demande(C_REGIME).length()+1];        //remplacer testRegime par: obd2->demande(C_REGIME);     /*      /!\      */
  demande(C_REGIME).substring(4,6).toCharArray(trameA,demande(C_REGIME).substring(4,6).length()+1);  //remplacer testRegime par: obd2->demande(C_REGIME);     /*      /!\      */
  demande(C_REGIME).substring(6,8).toCharArray(trameB,demande(C_REGIME).substring(6,8).length()+1);  //remplacer testRegime par: obd2->demande(C_REGIME);     /*      /!\      */
  int a = strtoul(trameA,NULL,16);
  int b = strtoul(trameB,NULL,16);
  return ((a*256)+b)/4;
}

float OBD2::lireConsomation(){
  //retourne des litres/heure
  float conso = 0;
  char trameC[demande(C_CONSOMMATION).length()+1];
  char trameD[demande(C_CONSOMMATION).length()+1];
  demande(C_CONSOMMATION).substring(4,6).toCharArray(trameC,demande(C_CONSOMMATION).substring(4,6).length()+1);  //remplacer testRegime par: obd2->demande(C_CONSO);     /*      /!\      */
  demande(C_CONSOMMATION).substring(6,8).toCharArray(trameD,demande(C_CONSOMMATION).substring(6,8).length()+1);  //remplacer testRegime par: obd2->demande(C_CONSO);     /*      /!\      */
  float a = strtoul(trameC,NULL,HEX);
  float b = strtoul(trameD,NULL,HEX);
  conso = ((256*(a)+(b))/20);
  return conso;
}
