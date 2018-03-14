#include "LedTri.h"
using namespace std;


LedTri::LedTri(int pLedRougePin, int pLedVertPin, int pLedBleuPin ) {
    ledRougePin = pLedRougePin;
    ledVertPin = pLedVertPin;
    ledBleuPin = pLedBleuPin;    
    
}


/* la fonction permet d'afficher la couleur Rouge sur la led rgb */
void LedTri::setRouge(){
  // Valeurs à modifier entre 1 et 255 pour varier l'intensité de la LED de couleur.
  analogWrite(ledRougePin, 255); 
  analogWrite(ledVertPin, 0);
  analogWrite(ledBleuPin, 0);
}


/* la fonction permet d'afficher la couleur Jaune sur la led rgb */
void LedTri::setJaune(){
  // Valeurs à modifier entre 1 et 255 pour varier l'intensité de la LED de couleur.
  analogWrite(ledRougePin, 255); 
  analogWrite(ledVertPin, 255);
  analogWrite(ledBleuPin, 0);
}


/* la fonction permet d'afficher la couleur vert sur la led rgb */
void LedTri::setVert(){
  // Valeurs à modifier entre 1 et 255 pour varier l'intensité de la LED de couleur.
  analogWrite(ledRougePin, 0); 
  analogWrite(ledVertPin, 255);
  analogWrite(ledBleuPin, 0);
}


/* la fonction permet d'afficher la couleur cyan sur la led rgb */
void LedTri::setCyan(){
  // Valeurs à modifier entre 1 et 255 pour varier l'intensité de la LED de couleur.
  analogWrite(ledRougePin, 0); 
  analogWrite(ledVertPin, 255);
  analogWrite(ledBleuPin, 255);
}


/* la fonction permet d'afficher la couleur bleu sur la led rgb */
void LedTri::setBleu(){
  // Valeurs à modifier entre 1 et 255 pour varier l'intensité de la LED de couleur.
  analogWrite(ledRougePin, 0); 
  analogWrite(ledVertPin, 0);
  analogWrite(ledBleuPin, 255);
}


/* la fonction permet d'afficher la couleur magenta sur la led rgb */
void LedTri::setMagenta(){
  // Valeurs à modifier entre 1 et 255 pour varier l'intensité de la LED de couleur.
  analogWrite(ledRougePin, 255); 
  analogWrite(ledVertPin, 0);
  analogWrite(ledBleuPin, 255);
}


void LedTri::setCouleur(int rouge, int vert, int bleu){
  // introduit les valeurs des variables précédentes
  analogWrite(ledRougePin, rouge); 
  analogWrite(ledVertPin, vert);
  analogWrite(ledBleuPin, bleu);
}


void LedTri::setCouleur(COLOR c) {
    switch (c) {
    case 1:
      setRouge();
      break;
    case 2:
      setJaune();
      break;
    case 3:
      setVert();
      break;
    case 4:
      setCyan();
      break;
    case 5:
      setBleu();
      break;
    case 6:
      setMagenta();
      break;
    default:
      break;
    }
    
}

