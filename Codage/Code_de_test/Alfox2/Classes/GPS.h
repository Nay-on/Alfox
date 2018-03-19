#ifndef __GPS__
#define __GPS__

#include <Arduino.h>
#include <SoftwareSerial.h>
#include <Adafruit_GPS.h>
#include <time.h>


class GPS
{

	private:

		float latitude;
		float longitude; 
		struct tm datation;
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
		struct tm getDatation() {return datation;};
   	bool isDispo();
    void readDATA();
		
};

#endif
