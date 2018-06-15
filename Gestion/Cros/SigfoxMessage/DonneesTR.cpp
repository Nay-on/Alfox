
/*
 * ATTENTION !!!
 * Classe est utilisée uniquement pour le test unitaire de la classe Message
*/
#include <stdio.h>
#include "DonneesTR.h"
void DonneesTR::razStat() {
    distanceParcourue = 0;
    nbDefauts = 0;
    defauts[0] = 0;
    defauts[1] = 0;
    defauts[2] = 0;
    defauts[3] = 0;
    consommation = 0;
    consoMoyenne = 0;
    consoMax = 0;
    vitesse = 0;
    vitesseMoyenne = 0;
    vitesseMax = 0;
    regime = 0;
    regimeMoyen = 0;
    regimeMax = 0;
    latitudeGPS = 0.0;
    longitudeGPS = 0.0;
    dateHTR.tm_year = 2018;
    dateHTR.tm_mday = 1;
    dateHTR.tm_mon = 0;
    dateHTR.tm_hour = 0;
    dateHTR.tm_min = 0;
    dateHTR.tm_sec = 0;
    OBD2Actif = false;
    bluetoothActif = false;
}

// modif pour le test unitaire de Message
void DonneesTR::initTestNORMAL() {
    // NORMAL : TM     K1  K2    K3  CD    CD  VM     VY    RM    RY    CM    CY
    // msg    : 209:d1 1:1 30:1e 0:0 42:2a 0:0 175:af 87:57 56:38 28:1c 92:5c 67:43
    // d1 = 1101 0001 
    // 2a = 0x02 + 0x0A
    // K  = 01 30 00
    OBD2Actif = true;    bluetoothActif = true;
    nbDefauts = 2; defauts[0]=0x02; defauts[1]=0x0A; defauts[2]=0; defauts[3]=0;
    distanceParcourue = 13000;
    vitesseMax = 175;    vitesseMoyenne = 87;   regimeMax = 56;
    regimeMoyen = 28;    consoMax = 92;         consoMoyenne = 67;
    consommation = 62;    vitesse = 129;        regime = 23;
    latitudeGPS = 23.23567;    longitudeGPS = 1.12345;
    dateHTR.tm_year = 2018;    dateHTR.tm_mday = 6;    dateHTR.tm_mon = 4;
    dateHTR.tm_hour = 13;      dateHTR.tm_min = 43;    dateHTR.tm_sec = 10;
}

void DonneesTR::initTestDEGRADE() {
    // NORMAL : TM  K1  K2    K3  CD  CD  VM  VY  RM  RY  CM  CY
    // msg    : 2:2 1:1 30:1e 0:0 0:0 0:0 0:0 0:0 0:0 0:0 0:0 0:0
    // d1 = 0002 0010 
    // 2a = 0x02 + 0x0A
    // K  = 01 30 00
    OBD2Actif = false;    bluetoothActif = false;
    nbDefauts = 0; defauts[0]=0; defauts[1]=0; defauts[2]=0; defauts[3]=0;
    distanceParcourue = 13000;
    vitesseMax = 0;    vitesseMoyenne = 0;   regimeMax = 0;
    regimeMoyen = 0;    consoMax = 0;         consoMoyenne = 0;
    consommation = 0;    vitesse = 0;        regime = 0;
    latitudeGPS = 0;    longitudeGPS = 0;
    dateHTR.tm_year = 2018;    dateHTR.tm_mday = 6;    dateHTR.tm_mon = 4;
    dateHTR.tm_hour = 13;      dateHTR.tm_min = 43;    dateHTR.tm_sec = 10;
}

void DonneesTR::initTestDMD_GPS() {
    // NORMAL : TM     K1   K2    K3    CD   CD  VM     VY    RM    RY    CM    CY
    // msg    : 211:C3 12:c 34:22 56:38 42:0 0:0 175:af 87:57 56:38 28:1c 92:5c 67:43
    OBD2Actif = true;    bluetoothActif = true;
    nbDefauts = 0; defauts[0]=0; defauts[1]=0; defauts[2]=0; defauts[3]=0;
    distanceParcourue = 123456;
    vitesseMax = 0;    vitesseMoyenne = 0;   regimeMax = 0;
    regimeMoyen = 0;    consoMax = 0;         consoMoyenne = 0;
    consommation = 0;    vitesse = 0;        regime = 0;
    latitudeGPS = 43.615982;    longitudeGPS = 1.309508;
    dateHTR.tm_year = 2018;    dateHTR.tm_mday = 6;    dateHTR.tm_mon = 4;
    dateHTR.tm_hour = 13;      dateHTR.tm_min = 43;    dateHTR.tm_sec = 10;
}

void DonneesTR::initTestGPS1() {
    // NORMAL : TM K1 K2 K3 L1 L2 L3 L4 G1 G2 G3 G4
    // msg    : D4 01 1E 00 2b 3d 3b 52 01 1e 5f 08
    OBD2Actif = true;    bluetoothActif = true;
    nbDefauts = 2; defauts[0]=0x02; defauts[1]=0x0A; defauts[2]=0; defauts[3]=0;
    distanceParcourue = 13000;
    vitesseMax = 0;    vitesseMoyenne = 0;   regimeMax = 0;
    regimeMoyen = 0;    consoMax = 0;         consoMoyenne = 0;
    consommation = 0;    vitesse = 0;        regime = 0;
    latitudeGPS = 43.615982;    longitudeGPS = 1.309508;
    dateHTR.tm_year = 2018;    dateHTR.tm_mday = 6;    dateHTR.tm_mon = 4;
    dateHTR.tm_hour = 13;      dateHTR.tm_min = 43;    dateHTR.tm_sec = 10;
}

void DonneesTR::initTestGPS2() {
    // NORMAL : TM K1 K2 K3 L1 L2 L3 L4 G1 G2 G3 G4
    // msg    : D4 0c 22 38 2b 3d 3b 59 01 1e 5f 08
    OBD2Actif = true;    bluetoothActif = true;
    nbDefauts = 2; defauts[0]=0x02; defauts[1]=0x0A; defauts[2]=0; defauts[3]=0;
    distanceParcourue = 123456;
    vitesseMax = 0;    vitesseMoyenne = 0;   regimeMax = 0;
    regimeMoyen = 0;    consoMax = 0;         consoMoyenne = 0;
    consommation = 0;    vitesse = 0;        regime = 0;
    latitudeGPS = -43.615988;    longitudeGPS = -01.309508;
    dateHTR.tm_year = 2018;    dateHTR.tm_mday = 6;    dateHTR.tm_mon = 4;
    dateHTR.tm_hour = 13;      dateHTR.tm_min = 43;    dateHTR.tm_sec = 10;
}

void DonneesTR::initTestINIT() {
    // NORMAL : TM  K1  K2    K3  CD  CD  VM  VY  RM  RY  CM  CY
    // msg    : 7:7 1:1 30:1e 0:0 0:0 0:0 0:0 0:0 0:0 0:0 0:0 0:0
    // d1 = 0000 0110 
    OBD2Actif = false;    bluetoothActif = false;
    nbDefauts = 0; defauts[0]=0; defauts[1]=0; defauts[2]=0; defauts[3]=0;
    distanceParcourue = 13000;
    vitesseMax = 0;    vitesseMoyenne = 0;   regimeMax = 0;
    regimeMoyen = 0;    consoMax = 0;         consoMoyenne = 0;
    consommation = 0;    vitesse = 0;        regime = 0;
    latitudeGPS = 0;    longitudeGPS = 0;
}

void DonneesTR::initTestDORMIR() {
    // NORMAL : TM  K1  K2    K3  CD  CD  VM  VY  RM  RY  CM  CY
    // msg    : 7:C7 1:1 30:1e 0:0 0:0 0:0 0:0 0:0 0:0 0:0 0:0 0:0
    // d1 = 1100 0111 
    OBD2Actif = true;    bluetoothActif = true;
    nbDefauts = 0; defauts[0]=0; defauts[1]=0; defauts[2]=0; defauts[3]=0;
    distanceParcourue = 13000;
    vitesseMax = 0;    vitesseMoyenne = 0;   regimeMax = 0;
    regimeMoyen = 0;    consoMax = 0;         consoMoyenne = 0;
    consommation = 0;    vitesse = 0;        regime = 0;
    latitudeGPS = 0;    longitudeGPS = 0;
}

int DonneesTR::getNbDefauts() {
    return nbDefauts;
}

void DonneesTR::setNbDefauts(int nb) {
    nbDefauts = nb;
}

int DonneesTR::getDefaut(int num) {
    return defauts[num]; // 4 d�fauts possibles [0,3]
}

void DonneesTR::setDefaut(int num, int code) {
    defauts[num] = code;
}

int DonneesTR::getConsoMoyenne() {
    return consoMoyenne;
}

int DonneesTR::getConsoMax() {
    return consoMax;
}

int DonneesTR::getVitesseMoyenne() {
    return vitesseMoyenne;
}

int DonneesTR::getVitesseMax() {
    return vitesseMax;
}

int DonneesTR::getRegimeMoyen() {
    return regimeMoyen;
}

int DonneesTR::getRegimeMax() {
    return regimeMax;
}

void DonneesTR::setVitesse(int _vitesse) {
    vitesse = _vitesse;
}

void DonneesTR::setConsommation(int _consommation) {
    consommation = _consommation;
}

void DonneesTR::setRegime(int _regime) {
    regime = _regime;
}

double DonneesTR::getDistanceParcourue() {
    return distanceParcourue;
}

void DonneesTR::setDistanceParcourue(double dist) {
    distanceParcourue = dist;
}

double DonneesTR::getLatitude() {
    return latitudeGPS;
}

double DonneesTR::getLongitude() {
    return longitudeGPS;
}

struct tm DonneesTR::getDateHTR() {
    return dateHTR;
}

void DonneesTR::setDateHTR(struct tm _date) {
    dateHTR = _date;
}

bool DonneesTR::getOBD2Actif() {
    return OBD2Actif;
}

void DonneesTR::setOBD2Actif(bool actif) {
    OBD2Actif = actif;
}

bool DonneesTR::getBluetoothActif() {
    return bluetoothActif;
}

void DonneesTR::setBluetoothActif(bool actif) {
    bluetoothActif = actif;
}

