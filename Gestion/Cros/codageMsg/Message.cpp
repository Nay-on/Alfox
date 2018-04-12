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
#include <stdlib.h>
#include <string.h>
using namespace std;

Message::Message() {
}

Message::Message(const Message& orig) {
}

Message::~Message() {
}

Etat Message::decoderEtat(string msg) {
    // Message descendant : TM MB OB DD PP
    // Convertion du string en byte
    Etat etat = (Etat)atoi(msg.substr(1, 1).c_str());
    return etat;
}

string Message::nouveau(Etat etat, DonneesTR data) {
    switch (etat) {
        case NORMAL :
            normal(data);
            break;
        case DEGRADE:
            degrade(data);
            break;
        case DMD_GPS:
            dmdGPS(data);
            break;
        case GPS :
            gps(data);
            break;
        case DORMIR:
            dormir(data);
            break;
        default:
            break;    
    }    
}

string Message::normal(DonneesTR data) {
    // NORMAL : TM CD CD K1 K2 K3 VM VY RM RY CM CY
    
    byte bMsg[] = {0,0,0,0,0,0,0,0,0,0,0,0};
    // TM = OB1 OB2 ND1 ND2 TM1 TM2 TM3 TM4
    // OB = état BT et OBD2, 00 = BT_OFF&OBD2_OFF, 01 = BT_ON&OBD2_OFF, 11 = BT_ON&OBD2_ON
    bMsg[0] = (byte)(((data.getBluetoothActif() ? 1 : 0) << 1) & (data.getOBD2Actif() ? 1 : 0));
    // NB = nb défauts
    bMsg[0] = bMsg[0] << 2 & (byte)data.getNbDefauts();
    // TM = Type de message
    bMsg[0] = bMsg[0] << 4 & (byte)decoderEtat();
    // les pannes valeurs entre 0 et 15
    bMsg[1] = (byte)((data.getDefaut(0) << 4) | data.getDefaut(1));
    bMsg[2] = (byte)((data.getDefaut(2) << 4) | data.getDefaut(3)); 
    // Distance parcourue entre deux messages en m
    bMsg[3] = (byte)(data.getDistanceParcourue()/10000);
    bMsg[4] = (byte)(((int)data.getDistanceParcourue()%10000)/100);
    bMsg[5] = (byte)((int)data.getDistanceParcourue()%100);
    // vitesse max
    bMsg[6] = (byte)data.getVitesseMax();
    // vitesse moyenne
    bMsg[7] = (byte)data.getVitesseMoyenne();
    // regime max 
    bMsg[8] = (byte)data.getRegimeMax();
    // regime moyen 
    bMsg[9] = (byte)data.getRegimeMoyen();
    // consommation max
    bMsg[10] = (byte)data.getConsoMax();
    // consommation moyenne
    bMsg[11] = (byte)data.getConsoMoyenne();
    
    // Convertion en string et return
    char string[11];
    memcpy(string, bMsg, sizeof bMsg);
    string[sizeof bMsg] = '\0';
    return string;
}

string Message::degrade(DonneesTR data) {
    // DEGRADE : TM OB 00 00 00 00 00 00 00 00 00 00
    
    byte bMsg[] = {0,0,0,0,0,0,0,0,0,0,0,0};
    bMsg[0] = (byte)decoderEtat();
    // OB = état BT et OBD2, 00 = BT_OFF&OBD2_OFF, 01 = BT_ON&OBD2_OFF, 11 = BT_ON&OBD2_ON
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
    bMsg[0] = (byte)decoderEtat();
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
    bMsg[0] = (byte)decoderEtat();
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
    strcpy(bMsg, msg.c_str());
    bMsg[0] = (byte)decoderEtat();
    // Convertion en string et return
    char string[11];
    memcpy(string, bMsg, sizeof bMsg);
    string[sizeof bMsg] = '\0';
    return string;
}