#ifndef __GPS__
#define __GPS__

#include <Arduino.h>
#include <SoftwareSerial.h>


class GPS
{

	private:

		float latitude;
		float longitude; 
		char datation;
    bool isAvailable1;
    //TinyGPS gps;
    SoftwareSerial* serialGPS;

	public:
	
		/** Constructeur **/
		GPS(int rx, int tx);

		int maj();
		float getLatitude() {return latitude;};
    float getLongitude() {return longitude;};
		char getDatation() {return datation;};
    bool isAvailable();
		
};

#endif
