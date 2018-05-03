#include "DonneesTR.h"
#include <stdlib.h>

DonneesTR* donneesTR;
void majDataTR();

String testRegime = "010C0FA0";       //Seulement pour les tests sans bluetooth (1000rpm)         /*   /!\   */   à supprimer pour l'intégration
String testVitesse = "410D64FF";      //vitesse = 100                                             /*   /!\   */   à supprimer pour l'intégration
String testConso = "015E0151";

void setup() {
  Serial.begin(115200);
  delay(9000);
  donneesTR = new DonneesTR();
  majDataTR();
  //-----------------------------------------------------------------------------------------------------------//--------------------------//
  Serial.print("Vitesse moy : ");                                                                              //                          //
  Serial.println(donneesTR->getVitesseMoyenne());                                                              //                          //
  Serial.print("Vitesse max : ");                                                                              //                          //
  Serial.println(donneesTR->getVitesseMax());                                                                  //                          //
  Serial.println("==================================");                                                        //  A supprimer :           //
  Serial.print("Regime moyen :");                                                                              //  utile pour le debug     //
  Serial.println(donneesTR->getRegimeMoyen());                                                                 //                          //
  Serial.print("Regime max : ");                                                                               //                          //
  Serial.println(donneesTR->getRegimeMax());                                                                   //                          //
  Serial.println("==================================nouvelle valeur d'entrée : vitesse 200 regime 2000");      //                          //
  //-----------------------------------------------------------------------------------------------------------//--------------------------//
  testVitesse = "410DC8FF";
  testRegime = "010C1F40";
  majDataTR();
  //-----------------------------------------------------------------------------------------------------------//--------------------------//
  Serial.print("Vitesse moy : ");                                                                              //                          //
  Serial.println(donneesTR->getVitesseMoyenne());                                                              //                          //
  Serial.print("Vitesse max : ");                                                                              //                          //
  Serial.println(donneesTR->getVitesseMax());                                                                  //                          //
                                                                                                               //                          //
  Serial.println("==================================");                                                        //  A supprimer :           //
  Serial.print("Regime moyen :");                                                                              //  utile pour le debug     //
  Serial.println(donneesTR->getRegimeMoyen());                                                                 //                          //
  Serial.print("Regime max : ");                                                                               //                          //
  Serial.println(donneesTR->getRegimeMax());                                                                   //                          //
  Serial.println("==================================");                                                        //                          //
  //-----------------------------------------------------------------------------------------------------------//--------------------------//
  Serial.print("Consomation en L/100KM : ");                                                                   //                          //
  Serial.println(donneesTR->getConsommation());                                                                //                          //
  Serial.println("==================================");                                                        //                          //   
  //-----------------------------------------------------------------------------------------------------------//--------------------------//

  }

void loop() {
  
  
}

void majDataTR(){
  donneesTR->setVitesse(lireVitesse());
  donneesTR->setRegime(lireRegimeMoteur());
  donneesTR->setConsommation(lireConsomation());
}

int lireVitesse(){
  char trame[testVitesse.length()+1];       //remplacer testVitesse par: obd2->demande(C_VITESSE).length()+1;     /*      /!\      */
  testVitesse.substring(4, 6).toCharArray(trame,testVitesse.substring(4, 6).length()+1); //remplacer testVitesse par: obd2->demande(C_VITESSE);     /*      /!\      */
  int a = strtoul(trame,NULL,16);
  return a;
}

int lireRegimeMoteur(){
  char trameA[testRegime.length()+1];        //remplacer testRegime par: obd2->demande(C_REGIME);     /*      /!\      */
  char trameB[testRegime.length()+1];        //remplacer testRegime par: obd2->demande(C_REGIME);     /*      /!\      */
  testRegime.substring(4,6).toCharArray(trameA,testRegime.substring(4,6).length()+1);  //remplacer testRegime par: obd2->demande(C_REGIME);     /*      /!\      */
  testRegime.substring(6,8).toCharArray(trameB,testRegime.substring(6,8).length()+1);  //remplacer testRegime par: obd2->demande(C_REGIME);     /*      /!\      */
  int a = strtoul(trameA,NULL,16);
  int b = strtoul(trameB,NULL,16);
  return ((a*256)+b)/4;
}

float lireConsomation(){
  //retourne des litres/heure
  float conso = 0;
  char trameC[testConso.length()+1];
  char trameD[testConso.length()+1];
  testConso.substring(4,6).toCharArray(trameC,testConso.substring(4,6).length()+1);  //remplacer testRegime par: obd2->demande(C_CONSO);     /*      /!\      */
  testConso.substring(6,8).toCharArray(trameD,testConso.substring(6,8).length()+1);  //remplacer testRegime par: obd2->demande(C_CONSO);     /*      /!\      */
  float a = strtoul(trameC,NULL,HEX);
  float b = strtoul(trameD,NULL,HEX);
  conso = (256*(a)+(b))/20;
  return conso;
}





