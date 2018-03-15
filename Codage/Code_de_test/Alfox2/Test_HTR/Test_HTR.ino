#include "HTR.h"
#include "RTClib.h"

DateTime dt;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  Serial.println(dt.day(), DEC);
  delay(2000);
}
