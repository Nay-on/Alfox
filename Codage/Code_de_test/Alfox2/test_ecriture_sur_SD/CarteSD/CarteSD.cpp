#include "CarteSD.h"

CarteSD::CarteSD() {
      Serial.println(F("Initialisation!"));
      pinMode(10, OUTPUT); // laisser la broche SS en sortie - obligatoire avec librairie SD
      if(!SD.begin(1)) { // si la communication commence bien sur le port d'ecriture
        Serial.println(F("Initialisation impossible !"));
      }
}

CarteSD::~CarteSD() {

}
/*
  String CarteSD::lire()
  {
  if (fichierSD) {
    while (fichierSD.available()) {
      Serial.write(fichierSD.read());
    }
    nomFichier.close();
  }
  // if the file isn't open, pop up an error:
  else {
    Serial.println("error opening datalog.txt");
    // lire tant qu'il y as quelque chose sur la carte
  }
  }*/

void CarteSD::ecrire(DonneesTR* dTR, GPS* gps)
{
  fichierSD = SD.open(nomFichier, FILE_WRITE);
  //fichierSD.print(heure+"   ");
  fichierSD.print(dTR->getDistanceParcourue());
  fichierSD.print("   ");
  fichierSD.print(dTR->getConsoMoyenne() + "   ");
  fichierSD.print(dTR->getConsoMax() + "   ");
  //fichierSD.print(nbDefauts+"   ");
  //fichierSD.print(codeDf+"   ");
  fichierSD.print(dTR->getVitesseMax() + "   ");
  fichierSD.print(dTR->getVitesseMoyenne() + "   ");
  fichierSD.print(dTR->getRegimeMax() + "   ");
  fichierSD.print(dTR->getRegimeMoyen() + "   ");
  fichierSD.println("");
  fichierSD.close();
  Serial.println("ecriture");


}

void CarteSD::effacer()
{
  // effacer tout  openNextFile()
  //this->supprimerFichier(nomFichier);
}

bool CarteSD::isFull()
{
  //SD.size(); // a voir si possible utiliser sur fichier ou pas
  // passer sur tout le fichier de recuperer taille 
}

void CarteSD::effacerOldData()
{
  //rechercher nom le plus vieux pui utiliser supprimerFichier
}

bool CarteSD::creerFichier(String nom)
{
  Serial.println("passage dans création fichier");
  if (SD.exists(nom)) {
    Serial.println(F("le fichier existe déjà"));
    return true;
  }
  else {
    Serial.println("création fichier");
    fichierSD = SD.open(nom);
    if (fichierSD) {
      fichierSD.println("Heure   KM       CMOY  NBDF  CD1    CD2    CD3    CD4   VMX   VMOY  RMX    RMOY");
      fichierSD.close();
      Serial.println("création réussi");
    }
    else {
      Serial.println(F("erreur de creation"));
      return false;
    }
    String nomFichier = nom;
    return true;
  }
}

bool CarteSD::supprimerFichier(String nom)
{
  if (SD.exists(nom)) {
    SD.remove(nom);
  }
}
// ajouter methode qui donne liste tout les fichiers SD.listfiles

