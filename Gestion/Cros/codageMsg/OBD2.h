/*
  Librairie SoftwareSerial dans IDE ARDUINO aller dans Croquis > Inclurenune Blibliothèque >
  Gérer les Blibliothèques > Chercher "SowtwareSerial" > installer.
*/
#ifndef __OBD2__
#define __OBD2__

class OBD2
{

	private:
    
	public:
		/** Constructeur **/
    bool isConnected();
    int demande(int numCode);

    typedef enum {
      C_VITESSE, C_CONSOMMATION, C_REGIME, C_DEFAUT
    } TCode;
    
};

#endif
