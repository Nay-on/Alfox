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

void setup() {
  Serial.begin(9600); //debut de la connection au port serie
  delay(1000);
  carteSD = new CarteSD(); // instanciation de carteSD
  donneesTR = new DonneesTR(); // instanciation de donneesTR
  delay(1000);
  

  
}

void loop() {

  while (Serial.available()) { // tant que des caractères sont en attente d'être lus
        char c = Serial.read(); // on lit le charactère
        commande = commande +c; 

    }
    delay(1000);
    if(commande=="extraction"){
      carteSD->extractionRacine();
      commande = "";
    }
    else{
      commande = "";
    }

}


