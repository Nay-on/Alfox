#include "CarteSD.h"

CarteSD::CarteSD() {
  Serial.println(F("Initialisation!"));
  pinMode(11, OUTPUT); // laisser la broche SS en sortie - obligatoire avec librairie SD
  if (!SD.begin(8)) { // si la communication commence bien sur le port d'ecriture
    Serial.println(F("Initialisation impossible !"));
  }


}

CarteSD::~CarteSD() {

}
// bloque la lercture mais non bloquant au final
String CarteSD::lire() {
  fichierSD = SD.open(nomFichier);
  Serial.println(fichierSD.name());
  if (fichierSD) {
    while (fichierSD.available()) {
      Serial.write(fichierSD.read());
    }
    fichierSD.close();
  }
  // si le ficheir n'est pas ouver afficher une erreur d'ouverture
  else {
    Serial.println("erreur d'ouverture");
  }
}

// OK
void CarteSD::ecrire(DonneesTR* dTR)
{
  fichierSD = SD.open(nomFichier, FILE_WRITE);
  if (fichierSD) {
    Serial.println(fichierSD.name());
    fichierSD.println("# " + String(dTR->getConsommation()) + "   " + String(dTR->getConsoMax()) + "   " + String(dTR->getVitesseMax()) + "   " + String(dTR->getVitesseMoyenne()) + "   " + String(dTR->getRegimeMax()) + "   ");
    fichierSD.close();
    Serial.println("ecriture");
  }
  else  {
    Serial.println("erreur d'ecriture");
  }
}



void CarteSD::effacer()
{

  while (true) {

    File entry = fichierRacineSD.openNextFile(FILE_WRITE);
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
      numTabs = numTabs + 1;
      effacer();
    }
    else
    {
      Serial.print("supression de ");
      Serial.println(String(dir.name()));
      String aSupprimer = entry.name();
      entry.close();
      if (supprimerFichier(aSupprimer));
      Serial.println("supression réussie");
    }
  }
  numTabs = 0;
}



bool CarteSD::isFull() {
  Serial.println("liste Fichier");
  fichierRacineSD = SD.open("/", FILE_WRITE);
  printDirectory(fichierRacineSD, 0);
  fichierSD.close();
  Serial.println("place utilisée " + String(placePrise));
  if (placePrise < 8589934080) { // taille de la carte moins une marge de 512 octet par securité
    return true; // si la carte est plaine retourne vrais
  }
  else {
    return false; // sinon retourne faux 
  }

}


//TODO
void CarteSD::effacerOldData()
{
  //rechercher nom le plus vieux puis utiliser supprimerFichier
  // supprimer le plus vieux fichier
  //lors de effacer la supression commence par le fichier le plus récent ex 09 puis 08
  // faire liste tout les ficheir comparer nom et supprimer dernier  problème comment comparer 
  // pour resumer on fait liste fichier et on recupère le dernier mais test a faire pour ordre listeFile 
}


//fonction ok et tester nececite l'ajout d'un fichier a la base de la carte pour la suite
bool CarteSD::nouveauFichier(String nom)
{
  if (SD.exists(nom)) {
    Serial.println(F("le fichier existe déjà"));
    nomFichier = nom;
    fichierRacineSD = SD.open("/");
    return true;
  }
  else {
    fichierSD = SD.open(nom, FILE_WRITE);
    if (fichierSD) {
      fichierSD.println("Heure   KM       CMOY  NBDF  CD1    CD2    CD3    CD4   VMX   VMOY  RMX    RMOY");
      Serial.println(fichierSD.name());
      fichierSD.close();
      Serial.println("création réussi");
    }
    else {
      Serial.println(F("erreur de creation"));

      return false;
    }
    fichierRacineSD = SD.open("/");

    nomFichier = nom;
    Serial.println(nomFichier);
    return true;
  }
}



bool CarteSD::supprimerFichier(String nom)
{
  if (SD.exists(nom)) {
    SD.remove(nom);
  }
  return SD.exists(nom);
}



void CarteSD::printDirectory(File dir, int numTabs)
{
  // ne fonctionne que si il y as un dossier a la racine de la carte SD
  while (true) {

    File entry = dir.openNextFile();
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
      Serial.print("\t");
      Serial.println(entry.size(), DEC);
      placePrise = placePrise + entry.size();
    }
    entry.close();
  }
}


