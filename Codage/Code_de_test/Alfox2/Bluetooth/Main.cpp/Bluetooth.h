#include <SoftwareSerial.h>
#include <Arduino.h>

class Bluetooth {

private :
  SoftwareSerial* serialBluetooth;

public:
	Bluetooth(int rx, int tx);
	~Bluetooth();
	void connexion();
	bool isActive();
};
