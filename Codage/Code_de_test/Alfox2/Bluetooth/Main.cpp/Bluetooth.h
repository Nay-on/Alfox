#include <SoftwareSerial.h>
#include <Arduino.h>

class Bluetooth {

public:
	Bluetooth(int rx, int tx);
	~Bluetooth();
	Dev connexion();
	bool isActive();
};
