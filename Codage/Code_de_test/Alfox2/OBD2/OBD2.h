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
    String code[4] = {"010D", "015E", "010C", "CODE_DEFAUT_NONDEF" };
  
	public:
		/** Constructeur **/
		OBD2(Bluetooth* bt);
    bool isConnected();
    int demande(int numCode);

    typedef enum {
      C_VITESSE, C_CONSOMMATION, C_REGIME, C_DEFAUT
    } TCode;
    
};

#endif
