#include "avdweb_SAMDtimer.h"
#define TROIS_SECONDES 3

const byte ledPin = 6;  
const byte interruptPin = 5;
volatile byte state = LOW;
volatile byte varCompteur = 0;
volatile bool LED_ON = false;
volatile int compteur = 0;
SAMDtimer *timer4_1s;


void ISR_timer4_LED_ON(struct tc_module *const module_inst) 
{ 
  compteur++;
  if (compteur==TROIS_SECONDES)
  {
    digitalWrite(ledPin, LOW);
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
  timer4_1s = new SAMDtimer(4, ISR_timer4_LED_ON, 1e6); // ISR LED2 1Hz (0.5s on, 0.5s off)

  timer4_1s->attachInterrupt(ISR_timer4_LED_ON);
}

void loop() {
  // put your main code here, to run repeatedly:
    //digitalWrite(ledPin, state);
}

void allumageLed() {
  digitalWrite(ledPin, HIGH);
  timer4_1s->enableTimer(true);
}



