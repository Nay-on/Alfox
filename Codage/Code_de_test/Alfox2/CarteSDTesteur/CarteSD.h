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
    bool lire(String fichierALire);  
    bool lire(File* fichierSD); 
    bool ecrire(DonneesTR* dTR);
    void effacer();
    bool isFull();
    void effacerOldData();
    bool nouveauFichier(String nom);
    bool supprimerFichier(String nom);
    void printDirectory(File dir, int numTabs);
    void extraction(String dir,int numTabs);
    void extractionRacine();
    
private:
    String nomFichier;// nom du fichier journalier 
    File fichierSD;// fichier journaliers ( change chaque jours) 
    File fichierRacineSD;// normalement "/"
    File dir; // fichier temporaire lors de la liste de tout les fichier
    int placePrise=0; // utiliser dans is full
    int numTabs =0; // utiliser chaque fois que l'on fait une liste de fichier permet de partir de la racine 
};

#endif /* CARTESD_H */
