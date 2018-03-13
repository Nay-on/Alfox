#ifndef __OBD2__
#define __OBD2__

#include <Arduino.h>
#include <SoftwareSerial.h>
#include "Bluetooth.h"


class OBD2
{

	private:
  
  enum code {
    0104, // : charge moteur calculé en pourcentage
    0105, // : Température du liquide de refroidissement en °C
    010C, // : régime moteur en tour/minutes
    010D, // : permet d'obtenir la vitesse du véhicule simulé
    011F, // : durée depuis le démarrage du moteur
    012F, // : niveau de carburant actuel de la voiture en pourcentage
    0121, // : Distance effectuée depuis que la MIL (Malfunction Indicator Lamp) est allumée en kms
    0131, // : prends le kilométrage depuis l'effacement des codes d'erreurs
    0151, // : type de carburant actuellement utilisé
    015A, // : position actuelle en pourcentage de la pédale d'accélérateur
    015C, // : température d'huile moteur en °C
    015E, // : consommation de carburant en litre/h
  };
  
  
	public:
	
		/** Constructeur **/
		OBD2(Bluetooth connexion);

    int demande(char code);
    
};

#endif
