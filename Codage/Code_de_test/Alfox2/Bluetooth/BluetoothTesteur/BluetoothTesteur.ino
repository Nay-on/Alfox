#include "Bluetooth.h"


Bluetooth* bluetooth;

void setup() {
  Serial.begin(9600);
  delay(10000);
  bluetooth = new Bluetooth(/*&serialSercom3*/);
  Serial.println("Test de la classe Bluetooth");
  Serial.println(bluetooth->connexion("B22B,1C,70EA6"),BIN);
  delay(2000);
  
  Serial.println(bluetooth->isActif());
  
}

void loop() {
  

}

void SERCOM3_Handler()
{
  bluetooth->getLiaisonBT()->IrqHandler();
}
