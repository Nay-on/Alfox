#include "LedTri.h"
#include "avdweb_SAMDtimer.h"
#define tempsLed 5

const byte ledPin = 6;
const byte interruptPin = 5;
volatile byte state = LOW;
volatile byte varCompteur = 0;
volatile bool LED_ON = false;
volatile int compteur = 0;
SAMDtimer *timer4_1s;
LedTri* maLed;
int compt = 0;


void ISR_timer4_LED_OFF(struct tc_module *const module_inst) 
{ 
  compteur++;
  if (compteur==tempsLed)
  {
    maLed->eteindre();
    compteur = 0;
    timer4_1s->enableTimer(false);
  }
}

//

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(ledPin,OUTPUT);
  pinMode(interruptPin,INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(interruptPin), allumageLed, FALLING);
  //gestion de la led
  timer4_1s = new SAMDtimer(4, ISR_timer4_LED_OFF, 1e6); // ISR LED2 1Hz (0.5s on, 0.5s off)
  maLed = new LedTri (redLedPin, greenLedPin, blueLedPin);
  timer4_1s->attachInterrupt(ISR_timer4_LED_OFF);
}

void loop() {
  // put your main code here, to run repeatedly:
    //digitalWrite(ledPin, state);
    compt ++;
    Serial.println(compt);
    delay(250);
}

void allumageLed() {
  maLed->setCouleur(cyan, 255);
  timer4_1s->enableTimer(true);
}



