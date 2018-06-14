
#include "Bluetooth.h"

//Création des port séries
Bluetooth::Bluetooth(int pinAlim, int pinEn) 
{
  this->pinAlim=pinAlim;
  this->pinEn=pinEn;
  serialBT= new Uart (&sercom3, 7, 6, SERCOM_RX_PAD_3, UART_TX_PAD_2);
  pinPeripheral(6, PIO_SERCOM_ALT); //Tx
  pinPeripheral(7, PIO_SERCOM_ALT); //Rx
  pinMode(pinAlim, OUTPUT); //Base Transistor
  pinMode(pinEn, OUTPUT); //EN
  /*delay(100);
  digitalWrite(pinEn, HIGH);
  delay(100);
  digitalWrite(pinAlim, HIGH);
  delay(1000);*/
  serialBT->begin(38400);
}

//Allumer ou eteindre le module bluetooth
void Bluetooth::desactiverModule()
{
  digitalWrite(this->pinAlim, LOW);
}

//Activer le mode configuration ou pas
void Bluetooth::activerModule(Mode mode)
{
  if (mode == commandeAT_ON)
  {
      digitalWrite(this->pinEn, HIGH);
  }  
  else if (mode == commandeAT_OFF)
  {
      digitalWrite(this->pinEn, LOW);  
  }
  delay(100);
  digitalWrite(this->pinAlim, HIGH);
  delay(3000);
}

//Configuration du module Bluetooth
int Bluetooth::connexion(String adresse) 
{

  //Test commande CMODE=0
  /*Serial.println("On entre dans la méthode de connexion");
  this->adresseOBD2=adresse;
  activerModule(commandeAT_OFF);
  delay(10000);
  Serial.println("Le délai est passé");*/
  
  this->adresseOBD2=adresse;
  if(this->isActif() == true)
  {
    return 1;
  }
  else
  {
    
    //Activation du mode configuration
    /*Serial.println("On entre dans le else pour relancer la procédure de connexion");
    desactiverModule();
    delay(2000);*/
    activerModule(commandeAT_ON);  
    
    int sommeErreurs = 0;

    serialBT->flush();
    delay(1000);
    //reinitialiser();
    sommeErreurs += modeMaster();
    sommeErreurs += modeConnexion();
    sommeErreurs += motDePasse();
    sommeErreurs += initialisation();
    sommeErreurs += appairage(adresseOBD2);
    sommeErreurs += bind(adresseOBD2);
    sommeErreurs += modeDeconnecte();
    sommeErreurs += lien(adresseOBD2);

    /*delay(100);
    //digitalWrite(pinEn, LOW);
    //delay(100);
  
    if(this->isActif() == true)
    {
      return 1;
    }
    else
    {
      digitalWrite(pinAlim, 0);
      delay(1000);
      digitalWrite(pinAlim, 1);
    }*/
    return sommeErreurs;
  }
}

//Vérification de la connexion
bool Bluetooth::isActif() 
{
  String contenu = "";
  serialBT->println("ATI");
  delay(100);

  //contenu = serialBT->readStringUntil('\n');
  delay(5000);//5000 par défaut
  contenu = serialBT->readStringUntil('>');
  
  //Serial.println(contenu);
  
  if (contenu.substring(0, 3) == "ELM")
  {
    return true;
  }
  else
  {
    return false;
  }
  //serialBT->flush();

}

Uart* Bluetooth::getLiaisonBT() 
{
  return serialBT;
}

//Réinitialisation du module Bluetooth
void Bluetooth::reinitialiser() 
{
  serialBT->println("AT+ORGL");
}

//Changement du mode de fonctionnement
int Bluetooth::modeMaster() 
{
  String contenu = "";
  serialBT->println("AT+ROLE=1");
  delay(100);
  while (serialBT->available() <= 0);
  contenu = serialBT->readStringUntil('\n');
  if (contenu == "OK")
  {
    return 1;
  } 
  else
  {
    return 0;
  }
}

//Changement du principe de connexion
int Bluetooth::modeConnexion() 
{
  String contenu = "";
  serialBT->println("AT+CMODE=1");
  delay(100);
  while (serialBT->available() <= 0);
  contenu = serialBT->readStringUntil('\n');
  if (contenu == "OK")
  {
    return 2;
  } 
  else
  {
    return 0;
  }
}

//Changement du mot de passe, par défaut 1234
int Bluetooth::motDePasse() 
{
  String contenu = "";
  serialBT->println("AT+PSWD=1234");
  while (serialBT->available() <= 0);
  contenu = serialBT->readStringUntil('\n');
  if (contenu == "OK")
  {
    return 4;
  } 
  else
  {
    return 0;
  }
}

//Initialisation du module bluetooth afin de pouvoir configurer la connexion
int Bluetooth::initialisation() 
{
  String contenu = "";
  serialBT->println("AT+INIT");
  delay(200);
  while (serialBT->available() <= 0);
  contenu = serialBT->readStringUntil('\n');
  if (contenu == "OK" )
  {
    return 8;
  } 
  else
  {
    return 0;
  }
}

//Appairage à l'OBD2
int Bluetooth::appairage(String adresse) 
{
  String contenu = "";
  Serial.println("Appairage");
  //serialBT->flush();
  serialBT->println("AT+PAIR=" + adresse + ",10");//10 par défaut
  //delay(5000);
  delay(100);
  while (serialBT->available() <= 0);
  /*{
    Serial.println(serialBT->read());
    }*/
  contenu = serialBT->readStringUntil('\n');
  Serial.println(contenu);
  if (contenu == "OK" )
  {
    return 16;
  } 
  else
  {
    return 0;
  }
}

//Ajout de l'adresse de l'OBD2 dans une liste d'appareil pour le CMODE=0
int Bluetooth::bind(String adresse) 
{
  String contenu = "";
  Serial.println("Bind");
  serialBT->println("AT+BIND=" + adresse);
  delay(5000);
  while (serialBT->available() <= 0);
  contenu = serialBT->readStringUntil('\n');
  Serial.println(contenu);
  if (contenu == "OK")
  {
    return 32;
  } 
  else
  {
    return 0;
  }
}

//Changement du principe de connexion en CMODE=0
int Bluetooth::modeDeconnecte() 
{
  String contenu = "";
  serialBT->println("AT+CMODE=0");
  delay(100);
  while (serialBT->available() <= 0);
  contenu = serialBT->readStringUntil('\n');
  if (contenu == "OK")
  {
    return 64;
  } 
  else
  {
    return 0;
  }
}

//Connexion manuelle au module OBD2
int Bluetooth::lien(String adresse) 
{
  String contenu = "";
  Serial.println("Link");
  serialBT->println("AT+LINK=" + adresse);
  delay(10000);
  while (serialBT->available() <= 0);
  contenu = serialBT->readStringUntil('\n');
  Serial.println(contenu);
  if (contenu == "OK")
  {
    return 128;
  } 
  else
  {
    return 0;
  }
}

Bluetooth::~Bluetooth(){
  delete this;
}

