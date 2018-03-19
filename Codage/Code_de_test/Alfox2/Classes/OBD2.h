#ifndef __OBD2__
#define __OBD2__

#include <Arduino.h>
#include <SoftwareSerial.h>
#include "/home/snir2g2/Bureau/Alfox/Codage/Code_de_test/Alfox2/Bluetooth/Main.cpp/Bluetooth.h"


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
