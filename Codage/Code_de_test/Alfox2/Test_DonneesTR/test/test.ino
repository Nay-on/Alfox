#include "DonneesTR.h"
#include <stdlib.h>

DonneesTR* donneesTR;
void majDataTR();

String testRegime = "010C0FA0";       //Seulement pour les tests sans bluetooth (1000rpm)         /*   /!\   */   à supprimer pour l'intégration
String testVitesse = "410D64FF";      //vitesse = 100                                             /*   /!\   */   à supprimer pour l'intégration

void setup() {
  Serial.begin(115200);
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
  
  }

void loop() {
  
  
}

void majDataTR(){
  donneesTR->setVitesse(lireVitesse());
  donneesTR->setRegime(lireRegimeMoteur());

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

int lireConsomation(){
  //     litres/heure
}





