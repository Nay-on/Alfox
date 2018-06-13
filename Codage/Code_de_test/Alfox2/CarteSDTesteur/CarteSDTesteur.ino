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
  delay(1000);
  carteSD = new CarteSD(); // instanciation de carteSD
  donneesTR = new DonneesTR(); // instanciation de donneesTR
  Serial.begin(9600); //debut de la connection au port serie
  delay(5000);
}

void loop() {
    if(!affichageMenu){ // si le booleen est faux alors on affiche le menu 
       Serial.println();
       delay(10);
       Serial.println();
       delay(10);
       Serial.println("mode 1 = creation");
       delay(10);
       Serial.println("mode 2 = ecriture");
       delay(10);
       Serial.println("mode 3 = lecture");
       delay(10);
       Serial.println("mode 4 = supression");
       delay(10);
       Serial.println("mode 5 = taille");
       delay(10);
       Serial.println("mode 6 = extraction");
       delay(10);
       Serial.println();
       delay(10);
       Serial.println();
       delay(10);
       affichageMenu=true; // pour eviter l'affichange du menu plusieurs fois d'affilé
    }
    delay(100);// delay obligatoir pour s'assurer de l'affichage complet du menu 
    while (Serial.available()) { // tant que des caractères sont en attente d'être lus
        char c = Serial.read(); // on lit le charactère
        commande = c; 
        modeDemander = commande.toInt();
    }

    delay(1000);
    
    switch (modeDemander){ //permet de réglér les bug de compilation liée a arduino 
        case 1 : // création du fichier
            etatCreation = carteSD->nouveauFichier(nomDuFichier);
            modeDemander = 0;
            affichageMenu=false;
            if(!etatCreation){
              Serial.println("Création du fichier impossible"); 
            }
            else{
              Serial.println("Création du fichier réussi");
            }
        break;
        case 2 :  // écriture d'un valeur incrémenté
            donneesTR->setConsommation(conso);
            conso = conso + 1;
            etatEcriture = carteSD->ecrire(donneesTR);
            modeDemander = 0;
            affichageMenu=false;
            
            if(!etatEcriture){
              Serial.println("écriture sur le fichier impossible"); 
            }
            else{
              Serial.println("écriture sur le fichier réussie");
            }
        break;
        case 3 : // lecture du fichier
            etatLecture = carteSD->lire(nomDuFichier);
            modeDemander = 0;
            affichageMenu=false;
            if(!etatLecture){
              Serial.println("Lecture du fichier impossible"); 
            }
            else{
              Serial.println("Lecture du fichier réussi");
            }
        break;
        case 4 : // supression du fichier
            etatSuppression = carteSD->supprimerFichier(nomDuFichier);
            modeDemander = 0;
            affichageMenu=false;
            if(!etatSuppression){
              Serial.println("impossible de faire la liste des fichiers"); 
            }
            else{
              Serial.println("liste des fichiers réussie");
            }
        break;
        case 5 : //place utiliser sur la carte SD 
            etatIsFull = carteSD->isFull();
            modeDemander = 0;
            affichageMenu=false;
            if(!etatIsFull){
              Serial.println("impossible de faire la liste des fichiers"); 
            }
            else{
              Serial.println("liste des fichiers réussie");
            }
        break;
        case 6 : // ecriture de tout les fichiers a la racide de la carte SD 
            carteSD->extractionRacine();
            modeDemander = 0;
            affichageMenu=false;
        break;    
      }
 
}


