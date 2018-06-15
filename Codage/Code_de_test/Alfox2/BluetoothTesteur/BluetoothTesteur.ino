#include "Bluetooth.h"


Bluetooth* bluetooth;
char c = ' ';
boolean NL = true;

void setup() {
  Serial.begin(9600);
  delay(2500);
  bluetooth = new Bluetooth(PINALIM, PINEN);
  Serial.println("Test de la classe Bluetooth");
  /*Serial.println(*/bluetooth->connexion("2017,11,7030A")/*,BIN)*/;
  //bluetooth->connexion("780C,B8,46F54");
  delay(2000);
  Serial.println(bluetooth->isActif());
  
}

void loop() 
{
    if (bluetooth->getLiaisonBT()->available())
    {
      c = bluetooth->getLiaisonBT()->read();
      Serial.write(c);
    }

    // Read from the Serial Monitor and send to the Bluetooth module
    if (Serial.available())
    {
      c = Serial.read();
      bluetooth->getLiaisonBT()->write(c);
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
  bluetooth->getLiaisonBT()->IrqHandler();
}
