#include "LedTri.h"
using namespace std;


LedTri::LedTri(int pLedRougePin, int pLedVertPin, int pLedBleuPin ) {
    ledRougePin = pLedRougePin;
    ledVertPin = pLedVertPin;
    ledBleuPin = pLedBleuPin;    
    
}


/* la fonction permet d'afficher la couleur Rouge sur la led rgb */
void LedTri::setRouge(int lumi){
  // Valeurs à modifier entre 1 et 255 pour varier l'intensité de la LED de couleur.
  analogWrite(ledRougePin, lumi); 
  analogWrite(ledVertPin, 0);
  analogWrite(ledBleuPin, 0);
}


/* la fonction permet d'afficher la couleur Jaune sur la led rgb */
void LedTri::setJaune(int lumi){
  // Valeurs à modifier entre 1 et 255 pour varier l'intensité de la LED de couleur.
  analogWrite(ledRougePin, lumi); 
  analogWrite(ledVertPin, lumi/2);
  analogWrite(ledBleuPin, 0);
}


/* la fonction permet d'afficher la couleur vert sur la led rgb */
void LedTri::setVert(int lumi){
  // Valeurs à modifier entre 1 et 255 pour varier l'intensité de la LED de couleur.
  analogWrite(ledRougePin, 0); 
  analogWrite(ledVertPin, lumi);
  analogWrite(ledBleuPin, 0);
}


/* la fonction permet d'afficher la couleur cyan sur la led rgb */
void LedTri::setCyan(int lumi){
  // Valeurs à modifier entre 1 et 255 pour varier l'intensité de la LED de couleur.
  analogWrite(ledRougePin, 0); 
  analogWrite(ledVertPin, lumi);
  analogWrite(ledBleuPin, lumi);
}


/* la fonction permet d'afficher la couleur bleu sur la led rgb */
void LedTri::setBleu(int lumi){
  // Valeurs à modifier entre 1 et 255 pour varier l'intensité de la LED de couleur.
  analogWrite(ledRougePin, 0); 
  analogWrite(ledVertPin, 0);
  analogWrite(ledBleuPin, lumi);
}


/* la fonction permet d'afficher la couleur magenta sur la led rgb */
void LedTri::setMagenta(int lumi){
  // Valeurs à modifier entre 1 et 255 pour varier l'intensité de la LED de couleur.
  analogWrite(ledRougePin, lumi); 
  analogWrite(ledVertPin, 0);
  analogWrite(ledBleuPin, lumi);
}


void LedTri::setCouleur(int rouge, int vert, int bleu){
  // introduit les valeurs des variables précédentes
  analogWrite(ledRougePin, rouge); 
  analogWrite(ledVertPin, vert);
  analogWrite(ledBleuPin, bleu);
}

void LedTri::eteindre(){
  // éteind la led
  analogWrite(ledRougePin, 0);
  analogWrite(ledVertPin, 0);
  analogWrite(ledBleuPin, 0);
}
  

void LedTri::setCouleur(COLOR c, int lumi) {
    switch (c) {
    case 1:
      setRouge(lumi);
      break;
    case 2:
      setJaune(lumi);
      break;
    case 3:
      setVert(lumi);
      break;
    case 4:
      setCyan(lumi);
      break;
    case 5:
      setBleu(lumi);
      break;
    case 6:
      setMagenta(lumi);
      break;
    default:
      break;
    }
    
}

