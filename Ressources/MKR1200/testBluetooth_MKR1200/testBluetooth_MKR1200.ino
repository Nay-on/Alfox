/*
 * Programme de test dd'une liaison série supplémentaire pour le module buetooth HC-05 
 * sur la carte MKR FOX 1200.
 * câblage du module HC-05 :
 * RX du module HC-05 sur la broche 6 de la MKR FOX 1200  
 * TX du module HC-05 sur la broche 7 de la MKR FOX 1200 *  
 */


#include <Arduino.h>   // required before wiring_private.h
#include "wiring_private.h" // pinPeripheral() function

//Création de la nouvelle liaison série.
//TX broche 6 de la MKR FOX 1200, RX broche 7
Uart serialBT (&sercom3, 7, 6, SERCOM_RX_PAD_3, UART_TX_PAD_2);  //Rx Pin D7, Tx Pin D6

char c = ' ';
boolean NL = true;

void setup() 
{
    //Mise en place liasion monitoring + message d'introduction
    Serial.begin(9600);
    Serial.print("Sketch:   ");  
    Serial.println(__FILE__);
    Serial.print("Uploaded: ");   Serial.println(__DATE__);
    Serial.println(" ");
    
    //Configuration des broches pour la liasion séire SERCOM3 (connectée au module HC-05)
    //TX broche 6, RX broche 7
    pinPeripheral(6, PIO_SERCOM_ALT); //Tx
    pinPeripheral(7, PIO_SERCOM_ALT); //Rx
    delay(5000); // Delay to let everything boot up
    Serial.println("Test liaison série supplémentaire sur MKR FOX 1200\r\n");
    
    //démarrage de la lisaison série      
    serialBT.begin(38400);
    Serial.println("serialBT démarrée à 38400 bauds");
    
    /*Serial.println("Envoi des commandes AT");
    serialBT.println("AT+ROLE=1");
    delay(100);
    serialBT.println("AT+CMODE=1");
    delay(100);
    serialBT.println("AT+PSWD=1234");
    delay(100);
    serialBT.println("AT+INQM=0,8,15");
    delay(100);
    serialBT.println("AT+RESET");
    delay(1000);
    serialBT.println("AT+INIT");
    delay(200);
    serialBT.println("AT+INQ");
    Serial.println("Attente de la réponse");
    while (serialBT.available()<=0); 
    Serial.println("Affichage de la réponse");
    while (serialBT.available()>0)
    {
       Serial.write(serialBT.read());
    }
    delay(20000);
    serialBT.println("AT+DISC");*/
}


void loop() 
{
    if (serialBT.available())
    {
      c = serialBT.read();
      Serial.write(c);
    }

    // Read from the Serial Monitor and send to the Bluetooth module
    if (Serial.available())
    {
      c = Serial.read();
      serialBT.write(c);
      if (NL) { Serial.print("\r\n>");  NL = false; }
      Serial.write(c);
      if (c==10) 
      {
        NL = true; 
      }
  }
}


void SERCOM3_Handler()
{
  serialBT.IrqHandler();
}
