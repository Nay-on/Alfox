#include <Arduino.h>
extern HardwareSerial Serial; 

void setup() {
  pinMode(13, OUTPUT);
  Serial.begin(9600);
}

void loop() {
  Serial.println("ON");
  digitalWrite(13, HIGH);
  delay(1000);
  Serial.println("OFF");
  digitalWrite(13, LOW);
  delay(1000);
  
}