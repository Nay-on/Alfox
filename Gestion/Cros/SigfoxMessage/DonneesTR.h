/*
 * File:   DonneesTR.h
 * Author: snir2g2
 *
 * Created on 14 mars 2018, 14:12
 */

#ifndef DONNEESTR_H
#define DONNEESTR_H

#include <time.h>

class DonneesTR {
private:
    double distanceParcourue;
    int nbDefauts;
    int defauts[4];
    int consommation;
    int consoMoyenne;
    int consoMax;
    int vitesse;
    int vitesseMoyenne;
    int vitesseMax;
    int regime;
    int regimeMoyen;
    int regimeMax;
    double latitudeGPS;
    double longitudeGPS;
    tm dateHTR;
    bool OBD2Actif;
    bool bluetoothActif;

public:
    void initTestNORMAL();
    void initTestDEGRADE();
    void initTestDMD_GPS();
    void initTestGPS1();
    void initTestGPS2();
    void initTestDORMIR();
    void initTestINIT();
    void razStat();
    int getConsoMoyenne();
    int getConsoMax();
    int getVitesseMoyenne();
    int getVitesseMax();
    int getRegimeMoyen();
    int getRegimeMax();
    double getDistanceParcourue();
    double getLatitude();
    double getLongitude();
    tm getDateHTR();
    int getDefaut(int numero);
    int getNbDefauts();
    bool getOBD2Actif();
    bool getBluetoothActif();
    void setVitesse(int vitesse); // met à jour la DistanceParcourue
    void setConsommation(int consommation);
    void setRegime(int regime);
    void setNbDefauts(int nombre);
    void setDefaut(int num, int codeDefaut);
    //void majDistance(GPS gps);          // met à jour la DistanceParcourue 
    // si pas de bluetooth
    void setDateHTR(tm dateHTR);
    void setDistanceParcourue(double distance);
    void setOBD2Actif(bool actif);
    void setBluetoothActif(bool actif);
};

#endif
