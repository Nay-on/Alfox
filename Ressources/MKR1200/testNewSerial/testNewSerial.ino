/*
 * Programme réalisé à partir des informations situées à l'adresse suivante:
 * https://www.arduino.cc/en/Tutorial/SamdSercom
 * 
 * Ce programme envoie un message en alternance sur le TX de laison série prédéfinie Serial1
 * et sur le TX d'une nouvelle liason série créée sur les broches 0 et 1 (ou 6 et 7)
 * Les messages sont récupérés sur le RX de chaque liaison série en alternance puis transmis sur le moniteur série (Serial) 
 * 
 * Relier entres elles les broches 13 et 14 avec un fil
 * Relier également les broches 0 et 1 entres elles (ou 6 et 7 suivant la configuration choisie)
 * Ouvrir le moniteur série
 */


#include <Arduino.h>   // required before wiring_private.h
#include "wiring_private.h" // pinPeripheral() function

//Création d'une nouvelle instance de liaison série sur les broches 1 et 0 nommée serialGPS

//TX sur broche 0, RX sur broche 1
Uart serialGPS (&sercom3, 1, 0, SERCOM_RX_PAD_1, UART_TX_PAD_0);  //Rx Pin D1, Tx Pin D0

//TX sur broche 6, RX sur broche 7
//Uart serialGPS (&sercom3, 7, 6, SERCOM_RX_PAD_3, UART_TX_PAD_2);  //Rx Pin D1, Tx Pin D0

void setup() {
  Serial.begin(9600);
  Serial1.begin(9600);
  serialGPS.begin(9600); 

  //TX sur broche 0, RX sur broche 1
  pinPeripheral(0, PIO_SERCOM); //Tx
  pinPeripheral(1, PIO_SERCOM); //Rx//*

  //TX sur broche 6, RX sur broche 7
  //pinPeripheral(6, PIO_SERCOM_ALT); //Tx
  //pinPeripheral(7, PIO_SERCOM_ALT); //Rx

  delay(5000); // Delay to let everything boot up
  Serial.println("Test liaison série supplémentaire sur MKR FOX 1200\r\n");
}

void loop() 
{
  //envoie d'un texte sur le TX de la nouvelle liaison série (broche 0)
  serialGPS.println("Bonjour sur serialGPS");

  //lecture sur le RX de la nouvelle liaison série (broche 1) et affichage sur le moniteur série (USB)
  while (serialGPS.available())
  {
    Serial.write(serialGPS.read());  
  }
  
  //envoie d'un texte sur le TX de Serial1 (broche 13)
  Serial1.println("Bonjour sur Serial1\r\n");

  //lecture sur le RX de la liaison série par défaut (broche 14) et affichage sur le moniteur série (USB)
  while (Serial1.available())
  {
    Serial.write(Serial1.read());  
  }
  
  delay(1000);
}

void SERCOM3_Handler()
{
  serialGPS.IrqHandler();
}


