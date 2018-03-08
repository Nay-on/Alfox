#include "GPS.h"

GPS::GPS(int rx, int tx){

  serialGPS = new SoftwareSerial(rx,tx);
  serialGPS->begin(9600);
}

int GPS::maj(){

  bool newData = false;
  unsigned long chars;
  unsigned short sentences, failed;
  //if(serialGPS->available() > 0)
  for (unsigned long start = millis(); millis() - start < 1000;)
  {
    //int j = serialGPS->available();
    //Serial.println(j);
    while (serialGPS->available() > 0)
    //for (int i =0 ; i < j ; i++)
    {
      //Serial.println(serialGPS.available());
      char c = serialGPS->read();
      Serial.write(c);
      if (gps.encode(c))
        newData = true;
    }
  }
  if (newData == true)
  {
    unsigned long age;
    gps.f_get_position(&latitude, &longitude, &age);
    latitude == TinyGPS::GPS_INVALID_F_ANGLE ? 0.0 : latitude, 6;
    longitude == TinyGPS::GPS_INVALID_F_ANGLE ? 0.0 : longitude, 6;
    Serial.println("récupération lat lon");
  }
}

bool GPS::isAvailable(){
   return isAvailable1;
}
