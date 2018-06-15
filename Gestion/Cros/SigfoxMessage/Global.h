/*
 * File:   Global.h
 * Author: snir2g2
 *
 * Created on 14 mars 2018, 15:22
 */

#ifndef GLOBAL_H
#define GLOBAL_H

typedef enum {
    STANDARD, NORMAL, DEGRADE, DMD_GPS, GPS,
    MAINTENANCE, INIT, DORMIR, ERREUR
} Etat;

typedef enum {
    MODE_STANDARD, MODE_DMD_GPS, MODE_GPS,
    MODE_MAINTENANCE, MODE_INIT, MODE_DORMIR,
    BLUETOOTH_ON, BLUETOOTH_OFF
} Event;

typedef enum {
    ENTRY, DO, EXIT
} Mode;

// Déclaration des variables et instanciation par composition.
/*  Global DonneesTR *donneesTR;
Global Message *message;
Global LedTri *ledTri;
Global HTR *htr;
Global GPS *gps;
Global CarteSD *sd;
Global Bluetooth *bluetooth;
Global OBD2 *obd2;
Global SigfoxArduino *sigfox; */

#endif


