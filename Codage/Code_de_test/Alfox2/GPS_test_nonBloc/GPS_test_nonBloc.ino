#include "GPS.h"

GPS* gps = new GPS(8,7);

void setup() {

  Serial.begin(115200);

  
}

SIGNAL(TIMER0_COMPA_vect){
  gps->readDATA();
}

void loop() {
  gps->maj();
  delay(2000);
  Serial.println(gps->getLatitude(),6);
  Serial.println(gps->getLongitude(),6);
  delay(2000);

}
