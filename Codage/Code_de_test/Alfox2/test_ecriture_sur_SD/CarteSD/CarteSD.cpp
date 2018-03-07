#include "CarteSD.h"

CarteSD::CarteSD() {
}

CarteSD::~CarteSD() {
}

String CarteSD::lire()
{
      if (nomFichier) {
    while (nomFichier.available()) {
      Serial.write(nomFichier.read());
    }
    nomFichier.close();
  }
  // if the file isn't open, pop up an error:
  else {
    Serial.println("error opening datalog.txt");
  }
}
/*
void CarteSD::ecrire(DonneesTR dTR,GPS gps)
{
    fichierSD = SD.open(nomFichier, FILE_WRITE);
    //fichierSD.print(heure+"   ");
    fichierSD.print(dTR.getDistanceParcourue()+"   ");
    fichierSD.print(dTR.getConsoMoyenne+"   ");
    fichierSD.print(dTR.getConsoMax
    //fichierSD.print(nbDefauts+"   ");
    //fichierSD.print(codeDf+"   ");
    fichierSD.print(dTR.VitesseMax+"   ");
    fichierSD.print(dTR.VitesseMoyenne+"   ");
    fichierSD.print(regimeMax+"   ");
    fichierSD.print(regimeMoyen+"   ");
    fichierSD.println("");


}*/

void CarteSD::effacer()
{
    Serial.println("test");
}

bool CarteSD::isFull()
{
//    SD.size();
}

void CarteSD::effacerOldData()
{

}

bool CarteSD::creerFichier(String nom)
{
    SD.open(nom);
}

bool CarteSD::supprimerFichier(String nom)
{
    SD.remove(nom);
}

