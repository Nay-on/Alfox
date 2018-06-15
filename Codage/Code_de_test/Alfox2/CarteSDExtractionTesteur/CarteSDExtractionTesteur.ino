#include "CarteSD.h"
#include "DonneesTR.h"
#include <SD.h>
#include <SPI.h>

CarteSD* carteSD;
DonneesTR* donneesTR;
int conso = 0;
enum MODE {creation, ecriture, lecture , supression , taille, extraction};

String nomDuFichier = "180606.TXT"; // nom du fichier qui va être créé, lue, et supprimer
String commande; // chaine qui va stocker la commande demander
int modeDemander=0; 
bool affichageMenu = false; // permet l'affichage du menu seulement après une commande ou au lancement 
bool etatEcriture = false;
bool etatLecture = false;
bool etatIsFull = false;
bool etatSuppression = false; 
bool etatCreation = false;
void setup() {
  Serial.begin(9600); //debut de la connection au port serie
  delay(1000);
  carteSD = new CarteSD(); // instanciation de carteSD
  donneesTR = new DonneesTR(); // instanciation de donneesTR
  
  delay(1000);
  carteSD->extractionRacine();
  delay(1000);
  
}

void loop() {

}


