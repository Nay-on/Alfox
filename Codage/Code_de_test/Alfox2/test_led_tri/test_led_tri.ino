#include "LedTri.h"


LedTri* maLed = new LedTri(9, 10, 11); 
void setup() {
  // put your setup code here, to run once:
  
  
  
}

void loop() {
  // put your main code here, to run repeatedly:
  maLed->setCouleur(rouge);
  delay(1000);
  maLed->setCouleur(jaune);
  delay(1000);
  maLed->setCouleur(vert);
  delay(1000);
  maLed->setCouleur(cyan);
  delay(1000);
  maLed->setCouleur(bleu);
  delay(1000);
  maLed->setCouleur(magenta);
  delay(1000);
  maLed->setCouleur(125, 125, 125);
  delay(1000);
}
