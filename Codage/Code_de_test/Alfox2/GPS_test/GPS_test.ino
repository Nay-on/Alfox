
 #include "GPS.h"
 
 GPS* gps = new GPS(8,7);
  unsigned long time1;
  unsigned long time2;
 
void setup() {
  
  
  Serial.begin(115200);

  
}

void loop() {
  delay(3000);
  //time1 = millis();  
  int erreur = gps->maj();

  Serial.println(gps->getLongitude(), 6);
  Serial.println(gps->getLatitude(), 6);
  
}
