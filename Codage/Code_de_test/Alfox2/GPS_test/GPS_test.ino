
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

  for (unsigned long start = millis(); millis() - start < 500;)
  {
    while (ss.available())
    {
      char c = ss.read();
      if (gps.encode(c))
        newData = true;
    }
  }
  if (newData)
  {
    float flat, flon;
    unsigned long age;
    gps.f_get_position(&flat, &flon, &age);
    print_date(gps);
    Serial.print("LAT = ");
    Serial.print(flat == TinyGPS::GPS_INVALID_F_ANGLE ? 0.0 : flat, 6);
    Serial.print("    LON = ");
    Serial.println(flon == TinyGPS::GPS_INVALID_F_ANGLE ? 0.0 : flon, 6);
  }
  
}
