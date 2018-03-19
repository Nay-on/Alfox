#include "GPS.h"

GPS* gps = new GPS(8,7);

void setup() {

  Serial.begin(115200);
  
}

  unsigned long time1;
  unsigned long time2;
  unsigned long time3;
  unsigned long time4;
  unsigned long timeInterrupt;


SIGNAL(TIMER0_COMPA_vect){
  time3 = micros();
  gps->readDATA();
  time4 = micros();
  timeInterrupt = time4 - time3;
}

void loop() {
  time1 = millis();
  gps->maj();
  
  if(gps->isDispo()){
    Serial.println(gps->getLatitude(),6);
    Serial.println(gps->getLongitude(),6);
    
    Serial.println(gps->getDatation().tm_mday);
    Serial.println(gps->getDatation().tm_mon);
    Serial.println(gps->getDatation().tm_year);
    Serial.println();

    time2 = millis();
    //Serial.println(time2 - time1);
    //Serial.println(timeInterrupt);
    
  }

}
