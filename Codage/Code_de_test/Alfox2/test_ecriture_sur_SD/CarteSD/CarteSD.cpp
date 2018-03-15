#include "CarteSD.h"

CarteSD::CarteSD() {
      Serial.println(F("Initialisation!"));
      pinMode(10, OUTPUT); // laisser la broche SS en sortie - obligatoire avec librairie SD
      if(!SD.begin(4)) { // si la communication commence bien sur le port d'ecriture
        Serial.println(F("Initialisation impossible !"));
      }
      

}

CarteSD::~CarteSD() {

}

String CarteSD::lire(String nom){
  fichierSD=SD.open(nom);
  if (fichierSD) {
    while (fichierSD.available()) {
      Serial.write(fichierSD.read());
    }
    fichierSD.close();
  }
  // if the file isn't open, pop up an error:
  else {
    Serial.println("error opening");
  }
}


void CarteSD::ecrire(DonneesTR* dTR, GPS* gps)
{
  fichierSD = SD.open(nomFichier, FILE_WRITE);
  //fichierSD.print(heure+"   ");
  if(fichierSD){
    fichierSD.print("100");
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
  else  {
    Serial.println("erreur d'ecriture");
  }
}


void CarteSD::effacer()
{
  fichierRacineSD = SD.open("/");
  printDirectory(fichierRacineSD,0);
  fichierSD.close();
  /*
  while (true) {

    File entry = fichierRacineSD.openNextFile();
    if (!entry) {
      Serial.println(fichierRacineSD.openNextFile());
      Serial.println("aucun fichier trouver");
      break;
    }
    else {
    
    supprimerFichier(entry.name());
    Serial.println(entry.name());
    }
  }*/
}

bool CarteSD::isFull(){
while (true) {

    File entry =  fichierRacineSD.openNextFile();
    if (! entry) {
      Serial.println("plus de fichier");
      break;
    }
    else {
    
    placePrise=placePrise+entry.size();
    entry.close();
    }
  }
}


//TODO
void CarteSD::effacerOldData()
{
  //rechercher nom le plus vieux pui utiliser supprimerFichier
}



bool CarteSD::nouveauFichier(String nom)
{
  if (SD.exists(nom)) {
    Serial.println(F("le fichier existe déjà"));
    String nomFichier = nom;
    return true;
  }
  else {

    fichierSD = SD.open(nom,FILE_WRITE);
    if (fichierSD) {
      fichierSD.println("Heure   KM       CMOY  NBDF  CD1    CD2    CD3    CD4   VMX   VMOY  RMX    RMOY");
      fichierSD.close();
      Serial.println("création réussi");
    }
    else {
      Serial.println(F("erreur de creation"));
      return false;
    }
    fichierRacineSD = SD.open("/");
    
    nomFichier = nom;
    return true;
  }
}

bool CarteSD::supprimerFichier(String nom)
{
  if (SD.exists(nom)) {
    SD.remove(nom);
  }
}

void CarteSD::printDirectory(File dir, int numTabs)
{
  // ne fonctionne que si il y as un dossier a la racine de la carte SD
  while (true){
    Serial.println(dir);
    File entry = dir.openNextFile();
    Serial.println(entry.name());
    if (! entry)
    {
      if (numTabs == 0)
        Serial.println("liste des fichier complète");
      return;
    }
    for (uint8_t i = 0; i < numTabs; i++)
      Serial.print('\t');
    Serial.print(entry.name());
    if (entry.isDirectory())
    {
      Serial.println("/");
      printDirectory(entry, numTabs + 1);
    }
    else
    {
      Serial.print("\t\t");
      Serial.println(entry.size(), DEC);
    }
    entry.close();
  }
}


