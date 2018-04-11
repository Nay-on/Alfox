#include <SPI.h>
#include <SD.h>
int Pin = 2;
String date ="05032018";
String kilometrage ="100000"; 
String consomationMoyenne = "10,4";
String nbDefauts = "4";
String codeDf1 = "1111";
String codeDf2 = "2222";
String codeDf3 = "3333";
String codeDf4 = "4444";
String vitesseMax = "300";
String vitesseMoyenne ="100";
String regimeMax = "6500";
String regimeMoyen ="2500";
String heure ="00:00";
char buf[100];
unsigned long time1;
unsigned long time2;
String formatFichier =".txt";
String nomFichier =date + formatFichier;
File fichierSD;

void setup()
{
    pinMode(Pin, OUTPUT);
    Serial.begin(115200);
    pinMode(10, OUTPUT); // laisser la broche SS en sortie - obligatoire avec librairie SD
    if(!SD.begin(4)) { // si la communication commence bien sur le port d'ecriture
        Serial.println(F("Initialisation impossible !"));
        return;
    }
    Serial.println(F("Initialisation OK"));
    fichierSD = SD.open(nomFichier, FILE_WRITE);           //Ouverture du fichier
    fichierSD.println("KM : Distance");
    fichierSD.println("CMOY : consommation Moyenne");
    fichierSD.println("NBDF : nombre de defauts");
    fichierSD.println("CD* : code defaut *");
    fichierSD.println("VMX : vitesse maximum");
    fichierSD.println("VMOY : vitesse moyenne");
    fichierSD.println("RMX : regime maximum");
    fichierSD.println("RMOY : regime moyen");
    fichierSD.println("");
    fichierSD.println("");
    fichierSD.println("Heure   KM       CMOY  NBDF  CD1    CD2    CD3    CD4   VMX   VMOY  RMX    RMOY");
    fichierSD.close();
}

void loop()
{
    time1 = millis();
    digitalWrite(2, HIGH);
    fichierSD = SD.open(nomFichier, FILE_WRITE);           //Ouverture du fichier
    SD.mkdir("essai.txt");

    fichierSD.print(heure+"   ");
    fichierSD.print(kilometrage+"   ");
    fichierSD.print(consomationMoyenne+"   ");
    fichierSD.print(nbDefauts+"   ");
    fichierSD.print(codeDf1+"   ");
    fichierSD.print(codeDf2+"   ");
    fichierSD.print(codeDf3+"   ");
    fichierSD.print(codeDf4+"   ");
    fichierSD.print(vitesseMax+"   ");
    fichierSD.print(vitesseMoyenne+"   ");
    fichierSD.print(regimeMax+"   ");
    fichierSD.print(regimeMoyen+"   ");
    fichierSD.println("");
    fichierSD.println(millis()); //milis permet de connaitre le temps de fonctionement de la arduino depuis sa dernière réinitialisation
    Serial.println("ecriture des donnée");
    fichierSD.close();
    time2 = millis();
    Serial.println(time2-time1); // permet de savoir le delais d'ecriture des donnée
    digitalWrite(2, LOW);
    delay(1000);

    
}


