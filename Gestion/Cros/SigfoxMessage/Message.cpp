/*
 * File:   Message.cpp
 * Author: snir2g1
 *
 * Created on 4 avril 2018, 12:44
 */

#include <stdio.h>
#include <cstring>
#include "Message.h"
#include <iostream>

using namespace std;

Etat etat = NORMAL;
Event event;
Mode mode;
long dureeCumulee;
long tempoMessage;
long dureeLoop;
bool chgtModeSrv;

Etat Message::decoderEtat(byte* msg) {
    // Message descendant : TM MB OB DD PP
    int code = msg[0];
    if (code != 0x30) // 0x30 est le code qui défini un msg de changement d'état
        return Etat::ERREUR;
    Etat etat = (Etat) msg[1];
    return etat;
}

void Message::nouveau(Etat etat, DonneesTR* data, byte* b) {
    switch (etat) {
        case NORMAL:
            normal(data, b);
            break;
        case DEGRADE:
            degrade(data, b);
            break;
        case DMD_GPS:
            dmdGPS(data, b);
            break;
        case GPS:
            gps(data, b);
            break;
        case DORMIR:
            dormir(data, b);
            break;
        case INIT:
            init(data, b);
            break;
    }
}

void Message::normal(DonneesTR* data, byte* bMsg) {
    // NORMAL : TM K1 K2 K3 CD CD VM VY RM RY CM CY
    // OB = Etat BT et OBD2, 00 = BT_OFF&OBD2_OFF, 01 = BT_ON&OBD2_OFF, 11 = BT_ON&OBD2_ON
    bMsg[0] = (byte) (((data->getBluetoothActif() ? 1 : 0) << 1) | (data->getOBD2Actif() ? 1 : 0));
    // NB = nb défauts
    bMsg[0] = bMsg[0] << 2 | (byte) ((data->getNbDefauts() != 0) ? 1 : 0);
    // TM = Type de message
    bMsg[0] = bMsg[0] << 4 | (byte) etat;
    // Distance parcourue depuis le dernier reset du boitier en KM
    bMsg[1] = (byte) (data->getDistanceParcourue() / 10000);
    bMsg[2] = (byte) (((int) data->getDistanceParcourue() % 10000) / 100);
    bMsg[3] = (byte) ((int) data->getDistanceParcourue() % 100);
    // les pannes valeurs entre 0 et 15
    bMsg[4] = (byte) ((data->getDefaut(0) << 4) | data->getDefaut(1));
    bMsg[5] = (byte) ((data->getDefaut(2) << 4) | data->getDefaut(3));
    // vitesse max
    bMsg[6] = (byte) data->getVitesseMax();
    // vitesse moyenne
    bMsg[7] = (byte) data->getVitesseMoyenne();
    // regime max 
    bMsg[8] = (byte) data->getRegimeMax();
    // regime moyen 
    bMsg[9] = (byte) data->getRegimeMoyen();
    // consommation max
    bMsg[10] = (byte) data->getConsoMax();
    // consommation moyenne
    bMsg[11] = (byte) data->getConsoMoyenne();
}

void Message::degrade(DonneesTR* data, byte* bMsg) {
    // DEGRADE : TM K1 K2 K3 00 00 00 00 00 00 00 00
    // OB = Etat BT et OBD2, 00 = BT_OFF&OBD2_OFF, 01 = BT_ON&OBD2_OFF, 11 = BT_ON&OBD2_ON
    bMsg[0] = (byte) (((data->getBluetoothActif() ? 1 : 0) << 1) | (data->getOBD2Actif() ? 1 : 0));
    // TM = Type de message
    bMsg[0] = (bMsg[0] << 6) | DEGRADE;
    // Distance parcourue depuis le dernier reset du boitier en KM
    bMsg[1] = (byte) (data->getDistanceParcourue() / 10000);
    bMsg[2] = (byte) (((int) data->getDistanceParcourue() % 10000) / 100);
    bMsg[3] = (byte) ((int) data->getDistanceParcourue() % 100);
    for (int i = 4; i < 12; i++)
        bMsg[i] = 0;
}

void Message::dmdGPS(DonneesTR* data, byte* bMsg) {
    // DMD_GPS : TM K1 K2 K3 L1 L2 L3 L4 G1 G2 G3 G4
    gps(data, bMsg);
    // TM = Type de message
    bMsg[0] = bMsg[0] & 0xF0; // On place le quartet de poids faible à 0
    bMsg[0] = bMsg[0] | DMD_GPS; // Pour y placer le mode
}

void Message::gps(DonneesTR* data, byte* bMsg) {
    //  GPS : TM K1 K2 K3 L1 L2 L3 L4 G1 G2 G3 G4
    // le bit de poids faible de L4 et G4 donne le signe +/- de la lat resp long
    bool negLat = false;
    bool negLg = false;
    double absLat = 0.0;
    double absLg = 0.0;
        // OB = Etat BT et OBD2, 00 = BT_OFF&OBD2_OFF, 01 = BT_ON&OBD2_OFF, 11 = BT_ON&OBD2_ON
    bMsg[0] = (byte) (((data->getBluetoothActif() ? 1 : 0) << 1) | (data->getOBD2Actif() ? 1 : 0));
    // NB = nb defauts
    bMsg[0] = bMsg[0] << 2 | (byte) ((data->getNbDefauts() != 0) ? 1 : 0);
    // TM = Type de message
    bMsg[0] = bMsg[0] << 4 | GPS;
    // Distance parcourue depuis le dernier reset du boitier en KM
    bMsg[1] = (byte) (data->getDistanceParcourue() / 10000);
    bMsg[2] = (byte) (((int) data->getDistanceParcourue() % 10000) / 100);
    bMsg[3] = (byte) ((int) data->getDistanceParcourue() % 100);
    // latitude
    absLat = data->getLatitude();
    if (absLat < 0) {
        absLat = -absLat;
        negLat = true;
    }
    long iLat = (int) (absLat * 1000000);
    bMsg[4] = (byte) (iLat / 1000000);
    bMsg[5] = (byte) (iLat % 1000000 / 10000);
    bMsg[6] = (byte) (iLat % 10000 / 100);
    bMsg[7] = (byte) (iLat % 100);
    if (negLat) // on change le bit de poids faible
        bMsg[7] = bMsg[7] | 0x01;
    else
        bMsg[7] = bMsg[7] & 0xFE;
    // longitude
    absLg = data->getLongitude();
    if (absLg < 0) {
        absLg = -absLg;
        negLg = true;
    }
    long iLg = (int) (absLg * 1000000);
    bMsg[8] = (byte) (iLg / 1000000);
    bMsg[9] = (byte) (iLg % 1000000 / 10000);
    bMsg[10] = (byte) (iLg % 10000 / 100);
    bMsg[11] = (byte) (iLg % 100);
    if (negLg) // on change le bit de poids faible
        bMsg[11] = bMsg[11] | 0x01;
    else
        bMsg[11] = bMsg[11] & 0xFE;
}

void Message::dormir(DonneesTR* data, byte* bMsg) {
    // DORMIR : TM K1 K2 K3 00 00 00 00 00 00 00 00
    degrade(data, bMsg);
    bMsg[0] = bMsg[0] & 0xF0;
    bMsg[0] = bMsg[0] | DORMIR;
}

void Message::init(DonneesTR* data, byte* bMsg) {
    // INIT : TM K1 K2 K3 00 00 00 00 00 00 00 00
    degrade(data, bMsg);
    bMsg[0] = bMsg[0] & 0xF0;
    bMsg[0] = bMsg[0] | INIT;
}