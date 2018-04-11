/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Global.h
 * Author: snir2g2
 *
 * Created on 14 mars 2018, 15:22
 */


// permet d'include le fichier des variables globales partout,
// mais de les déclarer qu'une fois !!!
#ifndef GLOBAL_H
#define Global 
#else
#define Global extern
#endif

#define GLOBAL_H

typedef enum  { START, STANDARD, NORMAL, DEGRADE, DMD_GPS, GPS,
                        MAINTENANCE, INIT, DORMIR } Etat;
   
typedef enum  { MODE_STANDARD, MODE_DMD_GPS, MODE_GPS,
             MODE_MAINTENANCE, MODE_DORMIR, MODE_INIT,
             BLUETOOTH_ON, BLUETOOTH_OFF} Event;
                        
typedef enum { ENTRY, DO, EXIT } Mode;

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

Global Etat etat;
Global long dureeCumulee;
Global long tempoMessage;
Global long dureeLoop;

Global bool chgtModeSrv;


