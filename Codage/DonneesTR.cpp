#include "DonneesTR.h"

void DonneesTR::razStat() {
    ;
}

int DonneesTR::getConsoMoyenne() {
    return this->consoMoyenne;
}

int DonneesTR::getConsoMax() {
    return this->consoMax;
}

int DonneesTR::getVitesseMoyenne() {    
    return this->vitesseMoyenne;
}

int DonneesTR::getVitesseMax() {
    return this->vitesseMax;
}

int DonneesTR::getRegimeMoyen() {
    return this->regimeMoyen;
}

int DonneesTR::getRegimeMax() {
    return this->regimeMax;
}

void DonneesTR::setVitesse(int vitesse) {
    this->vitesse = vitesse;
}

void DonneesTR::setConsommation(int consommation) {
    this->consommation = consommation;
}

void DonneesTR::setRegime(int regime) {
    this->regime = regime;
}

float DonneesTR::getDistanceParcourue() {
    return this->distanceParcourue;
}

void DonneesTR::majDistance(GPS gps) {
    ;
}

float DonneesTR::getLatitude() {
    ;
}

float DonneesTR::getLongitude() {
    ;
}

tm DonneesTR::getDateHTR() {
    return this->dateHTR;
}

void DonneesTR::setDateHTR(tm dateHTR) {
    this->dateHTR = dateHTR;
}

tm DonneesTR::getDateGPS() {
    return this->dateGPS;
}

void DonneesTR::setDateGPS(tm dateGPS) {
    this->dateGPS = dateGPS;
}
