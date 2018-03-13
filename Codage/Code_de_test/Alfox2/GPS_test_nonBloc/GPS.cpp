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
  if (! usingInterrupt) {
    char c = gps->read();
  }
  if (gps->newNMEAreceived()) {
    if (!gps->parse(gps->lastNMEA()))
    return;
  }
  this->latitude = gps->latitudeDegrees;
  this->longitude = gps->longitude;
  
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

void GPS::readDATA(){
  char c = gps->read();
}

