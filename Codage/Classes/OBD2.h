/*
  Librairie SoftwareSerial dans IDE ARDUINO aller dans Croquis > Inclurenune Blibliothèque >
  Gérer les Blibliothèques > Chercher "SowtwareSerial" > installer.
*/
#ifndef __OBD2__
#define __OBD2__

#include <Arduino.h>
#include "Bluetooth.h"

    typedef enum {
      C_VITESSE, C_CONSOMMATION, C_REGIME, C_DEFAUT
    } TCode;
    
class OBD2
{

	private:
    
    Bluetooth* moduleBT;
    Uart* liaisonBT;
    
    String lireReponse();
    String code[4] = {"010D", "015E", "010C", "CODE_DEFAUT_NONDEF" };
  
	public:
		/** Constructeur **/
		OBD2(Bluetooth* bt);
    bool isConnected();
    String demande(TCode numCode);
    float lireConsomation();
    int lireRegimeMoteur();
    int lireVitesse();

    
};

#endif
