#ifndef __OBD2__
#define __OBD2__

#include <Arduino.h>
#include <SoftwareSerial.h>
#include "Bluetooth.h"


class OBD2
{

	private:
    
    Bluetooth* moduleBT;
    SoftwareSerial* liaisonBT;
    
    String lireReponse();
  
	public:
	
		/** Constructeur **/
		OBD2(Bluetooth* bt);
    bool isConnected();
    String demande(String code);
    
};

#endif
