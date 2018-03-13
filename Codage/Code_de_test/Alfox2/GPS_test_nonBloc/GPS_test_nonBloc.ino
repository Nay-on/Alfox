#include "GPS.h"

GPS* gps = new GPS(8,7);

void setup() {

  Serial.begin(115200);
  
}

  unsigned long time1;
  unsigned long time2;

SIGNAL(TIMER0_COMPA_vect){
  gps->readDATA();
}

void loop() {
  time1 = millis();
  gps->maj();
  if(gps->isAvailable()){
    //delay(1000);
    Serial.println(gps->getLatitude(),6);
    Serial.println(gps->getLongitude(),6);
    time2 = millis();
    //Serial.println(time2 - time1 - 1000);
  }

}
