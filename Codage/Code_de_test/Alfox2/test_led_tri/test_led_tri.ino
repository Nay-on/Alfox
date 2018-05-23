  #include "LedTri.h"


LedTri* maLed = new LedTri(redLedPin, greenLedPin, blueLedPin);



void setup() {
  // put your setup code here, to run once:
  
  
  
}

void loop() {
  // put your main code here, to run repeatedly:
  maLed->setCouleur(rouge, 50);
  delay(1000);
  maLed->setCouleur(rouge, 125);
  delay(1000);
  maLed->setCouleur(rouge, 255);
  delay(1000);
  maLed->setCouleur(jaune, 50);
  delay(1000);
  maLed->setCouleur(jaune, 125);
  delay(1000);
  maLed->setCouleur(jaune, 255);
  delay(1000);
  maLed->setCouleur(vert, 50);
  delay(1000);
  maLed->setCouleur(vert, 125);
  delay(1000);
  maLed->setCouleur(vert, 255);
  delay(1000);
  maLed->setCouleur(cyan, 50);
  delay(1000);
  maLed->setCouleur(cyan, 125);
  delay(1000);
  maLed->setCouleur(cyan, 255);
  delay(1000);
  maLed->setCouleur(bleu, 50);
  delay(1000);
  maLed->setCouleur(bleu, 125);
  delay(1000);
  maLed->setCouleur(bleu, 255);
  delay(1000);
  maLed->setCouleur(magenta, 50);
  delay(1000);
  maLed->setCouleur(magenta, 125);
  delay(1000);
  maLed->setCouleur(magenta, 255);
  delay(1000);
  maLed->setCouleur(125, 125, 125);
  delay(1000);
}
