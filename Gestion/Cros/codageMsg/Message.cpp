/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Message.cpp
 * Author: snir2g1
 * 
 * Created on 4 avril 2018, 12:44
 */

#include "Message.h"
#include <stdio.h>
#include <string.h>
using namespace std;

Message::Message() {
}

Message::Message(const Message& orig) {
}

Message::~Message() {
}

Etat Message::decoderEtat(string msg) {
    
}

string Message::normal(DonneesTR data) {
    // NORMAL : TM CD CD K1 K2 K3 VM VY RM RY CM CY
    byte bMsg[] = {0,0,0,0,0,0,0,0,0,0,0,0};
    // type de trame
    bMsg[0] = (byte)(Etat)NORMAL;
    // l'état BT et OBD2, 00 = BT_OFF&OBD2_OFF, 01 = BT_ON&OBD2_OFF, 11 = BT_ON&OBD2_ON
    bMsg[1] = (byte)(((data.getBluetoothActif() ? 1 : 0) << 1) & (data.getOBD2Actif() ? 1 : 0));
    // le mode du véhicule                  // non utilisé !!!
    bMsg[2] = (byte)(Etat)NORMAL;
    // les pannes valeurs entre 0 et 15
    int nb = 0;
    for (int i = 0; i < 4; i++)
        nb += (data.getDefaut(i) > 0 ? 1 : 0);
    bMsg[3] = (byte)nb;            // pas de panne
    bMsg[4] = (byte)((data.getDefaut(0) << 4) | data.getDefaut(1));
    bMsg[5] = (byte)((data.getDefaut(2) << 4) | data.getDefaut(3)); 
    // Distance parcourue entre deux messages en m
    bMsg[6] = (byte)(data.getDistanceParcourue()/10000);
    bMsg[7] = (byte)(((int)data.getDistanceParcourue()%10000)/100);
    bMsg[8] = (byte)((int)data.getDistanceParcourue()%100);
    // consommation moyenne
    bMsg[9] = (byte)data.getConsoMoyenne();
    // vitesse moyenne
    bMsg[10] = (byte)data.getVitesseMoyenne();
    // regime moyen 
    bMsg[11] = (byte)data.getRegimeMoyen();
    // Convertion en string et return
    char string[11];
    memcpy(string, bMsg, sizeof bMsg);
    string[sizeof bMsg] = '\0';
    return string;
}

string Message::degrade(DonneesTR data) {
    // DEGRADE : TM OB 00 00 00 00 00 00 00 00 00 00
    byte bMsg[] = {0,0,0,0,0,0,0,0,0,0,0,0};
    bMsg[0] = (byte)(byte)(Etat)DEGRADE;
    // la connexion Bluetooth à l'OBD2
    bMsg[1] = (byte)(((data.getBluetoothActif() ? 1 : 0) << 1) & (data.getOBD2Actif() ? 1 : 0));
    // Conversion en string et return
    char string[11];
    memcpy(string, bMsg, sizeof bMsg);
    string[sizeof bMsg] = '\0';
    return string;
}

string Message::dmdGPS(DonneesTR data) {
    // DMD_GPS : TM HH MM SS L1 L2 L3 L4 G1 G2 G3 G4
    string msg = gps(data);
    // Convertion du string en byte et return
    char* bMsg = new char[msg.size() + 1];
    strcpy(bMsg, msg.c_str());
    bMsg[0] = (byte)(Etat)DMD_GPS;
    // Convertion en string et return
    char string[11];
    memcpy(string, bMsg, sizeof bMsg);
    string[sizeof bMsg] = '\0';
    return string;
}

string Message::gps(DonneesTR data) {
     //  GPS : TM HH MM SS L1 L2 L3 L4 G1 G2 G3 G4
    bool negLat = false;
    bool negLg = false;
    float absLat = 0.0;
    float absLg = 0.0;
    byte bMsg[] = {0,0,0,0,0,0,0,0,0,0,0,0};
    bMsg[0] = (byte)(Etat)GPS;
    bMsg[1] = (byte)data.getDateHTR().tm_hour;
    bMsg[2] = (byte)data.getDateHTR().tm_min;
    bMsg[3] = (byte)data.getDateHTR().tm_sec;

    // latitude
    absLat = data.getLatitude();
    if (absLat < 0) {
        absLat = - absLat;
        negLat = true;
    }
    long iLat = (int)(absLat*1000000);
    bMsg[4] = (byte)(iLat/1000000);
    bMsg[5] = (byte)(iLat%1000000 / 10000);
    bMsg[6] = (byte)(iLat%10000 / 100);
    bMsg[7] = (byte)(iLat%100);
    if (negLat)    // on change le bit de poids faible
        bMsg[7] |= 0x01;
    else
        bMsg[7] &= 0xFE;

    // longitude
    absLg = data.getLongitude();
    if (absLg < 0) {
        absLg = -absLg;
        negLg = true;
    }
    long iLg = (int)(absLg*1000000);
    bMsg[8] = (byte)(iLg/1000000);
    bMsg[9] = (byte)(iLg%1000000 / 10000);
    bMsg[10] = (byte)(iLg%10000 / 100);
    bMsg[11] = (byte)(iLg%100);
    if (negLg)    // on change le bit de poids faible
        bMsg[11] |= 0x01;
    else
        bMsg[11] &= 0xFE;

    // Convertion en string et return
    char string[11];
    memcpy(string, bMsg, sizeof bMsg);
    string[sizeof bMsg] = '\0';
    return string;
    }

string Message::dormir(DonneesTR data) {
    // SLEEP : TM CO 00 00 00 00 00 00 00 00 00 00
    string msg = degrade(data);
    // Convertion du string en byte et return
    char* bMsg = new char[msg.size() + 1];
    strcpy(bMsg, msg.c_str());;
    bMsg[0] = (byte)(Etat)DORMIR;
    // Convertion en string et return
    char string[11];
    memcpy(string, bMsg, sizeof bMsg);
    string[sizeof bMsg] = '\0';
    return string;
}