#ifndef CARTESD_H
#define CARTESD_H
#include <SPI.h>
#include <SD.h>
#include <Arduino.h>

class CarteSD {
public:
    CarteSD();
    virtual ~CarteSD();
    String lire();
    //void ecrire(DonneesTR dTR,GPS gps);
    void effacer();
    bool isFull();
    void effacerOldData();
    bool creerFichier(String nom);
    bool supprimerFichier(String nom);
    File nomFichier;
    
private:

};

#endif /* CARTESD_H */
