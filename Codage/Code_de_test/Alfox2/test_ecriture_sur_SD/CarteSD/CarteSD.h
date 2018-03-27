#ifndef CARTESD_H
#define CARTESD_H
#include <SPI.h>
#include <SD.h>
#include <Arduino.h>
#include "DonneesTR.h"


class CarteSD {
public:
    CarteSD();
    virtual ~CarteSD();
    String lire();
    void ecrire(DonneesTR* dTR);
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
    File dir;
    int placePrise=0;
     int numTabs =0;
};

#endif /* CARTESD_H */
