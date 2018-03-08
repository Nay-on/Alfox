#include "GPS.h"

GPS::GPS(int rx, int tx){

	serialGPS = new SoftwareSerial(rx,tx);
	gps = new Adafruit_GPS(serialGPS);
	serialGPS->begin(9600);
 
  gps->sendCommand(PMTK_SET_NMEA_OUTPUT_RMCONLY);
  gps->sendCommand(PMTK_SET_NMEA_UPDATE_1HZ);
  gps->sendCommand(PGCMD_ANTENNA);
  useInterrupt(true);
  
}

int GPS::maj(){

  delay(1000);

}

bool GPS::isAvailable(){

}

void GPS::useInterrupt(boolean v) {
  if (v) {
    OCR0A = 0xAF;
    TIMSK0 |= _BV(OCIE0A);
    usingInterrupt = true;
  } else {
    TIMSK0 &= ~_BV(OCIE0A);
    usingInterrupt = false;
  }
}

void GPS::SIGNAL(TIMER0_COMPA_vect){
    char c = gps.read();
  }

