#include "CarteSD.h"

CarteSD::CarteSD() {
  //Serial.println(F("Initialisation!"));                // debug 
  pinMode(11, OUTPUT);                                 // laisser la broche SS en sortie - obligatoire avec librairie SD
  if (!SD.begin(11)) {                                 // si la communication commence bien sur le port d'ecriture
    Serial.println(F("Initialisation impossible !"));
  }


}

CarteSD::~CarteSD() {

}
                                                        // bloque la lercture mais non bloquant au final
String CarteSD::lire(String nomFichierALire) {
  fichierSD = SD.open(nomFichierALire);                 //ouverture du fichier
  //Serial.println(fichierSD.name());                   // debug
  if (fichierSD) {
    while (fichierSD.available()) {                     // tant qu'il y as quelque chose de non lus dans le fichier
      Serial.write(fichierSD.read());                   // lecture du fichier
    }
    fichierSD.close();                                  // fermeture du fichier
  }
                                                        // si le ficheir n'est pas ouver afficher une erreur d'ouverture
  else {
    Serial.println("erreur d'ouverture");               // debug
  }
}

// OK
void CarteSD::ecrire(DonneesTR* dTR)
{
  fichierSD = SD.open(nomFichier, FILE_WRITE);          // ouverture du fichier en ecriture et creation si il n'existe pas 
  if (fichierSD) {// si l'ouverture as réussie
    Serial.println(fichierSD.name());                   // debug
    fichierSD.println("# " + String(dTR->getConsommation()) +"  "+ String(dTR->getVitesse()));
                                                        // ecriture des donnée 
    fichierSD.close();                                  // fermeture du fichier
    Serial.println("ecriture");                         // debug
  }
  else  {
    Serial.println("erreur d'ecriture");                // debug 
  }
}



void CarteSD::effacer()
{

  while (true) {                                         // boucle pour le programem recurcif 

    File entry = fichierRacineSD.openNextFile(FILE_WRITE); // ouverture de la carte SD a la racine 
    if (! entry)                                        // si l'ouverture as bien marcher 
    {
      if (numTabs == 0)                                 // nombre de dossier depuis la deja passé
        Serial.println("liste des fichier complète");   // debug
      return;                                           // sortie de la boucle 
    }
    for (uint8_t i = 0; i < numTabs; i++) 
      Serial.print('\t');
    Serial.print(entry.name());
    if (entry.isDirectory())                             //cette boucle sert a faire la liste des fichier 
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
  if (placePrise < 8589934080) {                          // taille de la carte moins une marge de 512 octet par securité
    return true;                                          // si la carte est plaine retourne vrais
  }
  else {
    return false;                                         // sinon retourne faux 
  }

}


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
    Serial.println(F("le fichier existe déjà"));          // debug
    nomFichier = nom;                                     // nom du fichier journalier
    fichierRacineSD = SD.open("/");                       //nom du fichier racine
    return true;                                          // le fichier existe bien
  }
  else {                                                  // si le ficheir n'exitste pas 
    fichierSD = SD.open(nom, FILE_WRITE);                 // creation et ouverture en ecriture
    if (fichierSD) {                                      // si le fichier as bien été crée
      fichierSD.println("  KM       CMOY  NBDF  CD1    CD2    CD3    CD4   VMX   VMOY  RMX    RMOY"); // entête
      Serial.println(fichierSD.name());                   // debug
      fichierSD.close();                                  // fermeture
      Serial.println("création réussi");                  // debug
    }
    else {                                                // si la création as echouer
      Serial.println(F("erreur de creation"));            // debug

      return false;                                       //le ficheir n'as pas bien été crée
    }
    fichierRacineSD = SD.open("/");                       // ouverture du fichier

    nomFichier = nom;
    Serial.println(nomFichier);
    return true;
  }
}



bool CarteSD::supprimerFichier(String nom)
{
  if (SD.exists(nom)) {                                   // si le fichier existe 
    SD.remove(nom);                                       // suppression
  }
  return SD.exists(nom);                                  // retourne etat fichier si il existe ou non 
}


                                                          // code recuperer d'internet qui permet de faire la liste de tout le fichier 
void CarteSD::printDirectory(File dir, int numTabs)
{
                                                          //code recuperer sur le site internet arduino classe SD
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


