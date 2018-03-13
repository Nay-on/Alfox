#ifndef __GPS__
#define __GPS__

#include <Arduino.h>
#include <SoftwareSerial.h>
#include <Adafruit_GPS.h>


class GPS
{

	private:

		float latitude;
		float longitude; 
		char datation;
    bool available = false;
    
    boolean usingInterrupt = false;
    Adafruit_GPS* gps;
    SoftwareSerial* serialGPS;
    void useInterrupt(boolean v);

	public:
	
		/** Constructeur **/
		GPS(int rx, int tx);

		int maj();
		float getLatitude() {return latitude;};
   	float getLongitude() {return longitude;};
		char getDatation() {return datation;};
   	bool isAvailable();
    void readDATA();
		
};

#endif
