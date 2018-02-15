#include <SoftwareSerial.h>
#include <TinyGPS.h>
#include <String>

TinyGPS gps;

class GPS {

private:
	float longitude;
	float latitude;
	String date;
 
public:
	GPS(int rx, int tx);
	~GPS();
	void maj();
	float getLatitude() {return latitude;};
	float getLongitude() {return longitude;};
	String getDatation() {return date;};;
	
};
