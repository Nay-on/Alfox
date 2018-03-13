#ifndef __OBD2__
#define __OBD2__

#include <Arduino.h>
#include <SoftwareSerial.h>
#include "Bluetooth.h"


class OBD2
{

	private:
    
    Bluetooth* moduleBT;
  
	public:
	
		/** Constructeur **/
		OBD2(Bluetooth* moduleBT);
    int connexion(Bluetooth* moduleBT);
    int demande(String code);
    
};

#endif
