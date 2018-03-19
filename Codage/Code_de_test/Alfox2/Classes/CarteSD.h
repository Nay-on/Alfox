#ifndef CARTESD_H
#define CARTESD_H
#include <SPI.h>
#include <SD.h>
#include <Arduino.h>
#include "DonneesTR.h"
#include "GPS.h"


class CarteSD {
public:
    CarteSD();
    virtual ~CarteSD();
    String lire();
    void ecrire(DonneesTR* dTR,GPS* gps);
    void effacer();
    bool isFull();
    void effacerOldData();
    bool nouveauFichier(String nom);
    bool supprimerFichier(String nom);
    void printDirectory(File dir, int numTabs);

    
private:
    String nomFichier;
    File fichierSD;
    File fichierRacineSD;
    int placePrise=0;
};

#endif /* CARTESD_H */
