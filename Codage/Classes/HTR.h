#ifndef __HTR__
#define __HTR__

#include <Arduino.h>
#include <Adafruit_GPS.h>
#include "GPS.h"
#include <time.h>


class HTR
{

  private:
  
    GPS* gps;

  public:
  
    HTR(GPS* gps);
    struct tm lire();

};

#endif
