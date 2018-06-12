
#include "Bluetooth.h"

Bluetooth::Bluetooth(int pinAlim, int pinEn) 
{
  this->pinAlim=pinAlim;
  this->pinEn=pinEn;
  serialBT= new Uart (&sercom3, 7, 6, SERCOM_RX_PAD_3, UART_TX_PAD_2);
  pinPeripheral(6, PIO_SERCOM_ALT); //Tx
  pinPeripheral(7, PIO_SERCOM_ALT); //Rx
  pinMode(pinAlim, OUTPUT); //Base Transistor
  pinMode(pinEn, OUTPUT); //EN
  serialBT->begin(38400);
}

//Déactiver matériellement le module BT
void Bluetooth::desactiverModule()
{
  digitalWrite(this->pinAlim, LOW);
}

//Activer le module BT en mode commande AT ou non.
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

int Bluetooth::connexion(String adresse) 
{
  Serial.println("On entre dans la méthode de connexion");
  this->adresseOBD2=adresse;
  activerModule(commandeAT_OFF);
  delay(10000);
  Serial.println("Le délai est passé");

  
  if(this->isActif() == true)
  {
    Serial.println("isActif et à true");
    return 1;
  }
  else
  {
    Serial.println("On entre dans le else pour relancer la procédure de connexion");
    desactiverModule();
    delay(2000);
    activerModule(commandeAT_ON);
    
    int sommeErreurs = 0;
    serialBT->flush();
    delay(1000);
    reinitialiser();
    sommeErreurs += modeMaster();
    sommeErreurs += modeConnexion();
    sommeErreurs += motDePasse();
    sommeErreurs += initialisation();
    sommeErreurs += appairage(adresseOBD2);
    sommeErreurs += bind(adresseOBD2);
    sommeErreurs += modeDeconnecte();
    sommeErreurs += lien(adresseOBD2);
    
    return sommeErreurs;
  }
}

bool Bluetooth::isActif() 
{
  String contenu = "";
  serialBT->println("ATI");
  delay(100);
  //while (serialBT->available() <= 0);
  contenu = serialBT->readStringUntil('>');
  if (contenu.substring(0, 3) == "ELM")
  {
    return true;
  }
  else
  {
    return false;
  }
}


Uart* Bluetooth::getLiaisonBT() 
{
  return serialBT;
}

void Bluetooth::reinitialiser() 
{
  //desactiverModule();
  //delay(1000);
  //activerModule(commandeAT_ON);
  serialBT->println("AT+ORGL");
}

int Bluetooth::modeMaster() 
{
  String contenu = "";
  serialBT->println("AT+ROLE=1");
  delay(100);
  while (serialBT->available() <= 0);
  while (serialBT->available() > 0) 
  {
    contenu += serialBT->read();
  }
  if (contenu == "79751310")
  {
    return 1;
  } 
  else
  {
    return 0;
  }
}

int Bluetooth::modeConnexion() 
{
  String contenu = "";
  serialBT->println("AT+CMODE=1");
  delay(100);
  while (serialBT->available() <= 0);
  while (serialBT->available() > 0) 
  {
    contenu += serialBT->read();
  }
  if (contenu == "79751310")
  {
    return 2;
  } 
  else
  {
    return 0;
  }
}


int Bluetooth::motDePasse() 
{
  String contenu = "";
  serialBT->println("AT+PSWD=1234");
  delay(100);
  while (serialBT->available() <= 0);
  while (serialBT->available() > 0) 
  {
    contenu += serialBT->read();
  }
  if (contenu == "79751310")
  {
    return 4;
  } 
  else
  {
    return 0;
  }
}


int Bluetooth::initialisation() 
{
  String contenu = "";
  serialBT->println("AT+INIT");
  delay(200);
  while (serialBT->available() <= 0);
  while (serialBT->available() > 0) 
  {
    contenu += serialBT->read();
  }
  if (contenu == "79751310" )
  {
    return 8;
  } 
  else
  {
    return 0;
  }
}


int Bluetooth::appairage(String adresse) 
{
  String contenu = "";
  Serial.println("Apparaige");
  serialBT->flush();
  serialBT->println("AT+PAIR=" + adresse + ",10");
  delay(100);
  while (serialBT->available() <= 0);
  while (serialBT->available() > 0) 
  {
    contenu += serialBT->read();
  }
  Serial.println(contenu);

  if (contenu == "79751310" )//|| contenu == "")
  {
    return 16;
  } 
  else
  {
    return 0;
  }
}


int Bluetooth::bind(String adresse) 
{
  String contenu = "";
  Serial.println("Bind");
  serialBT->println("AT+BIND=" + adresse);
  delay(5000);
  while (serialBT->available() <= 0);
  while (serialBT->available() > 0) 
  {
    contenu += serialBT->read();
  }
  Serial.println(contenu);

  if (contenu == "79751310")
  {
    return 32;
  } 
  else
  {
    return 0;
  }
}

int Bluetooth::modeDeconnecte() 
{
  String contenu = "";
  serialBT->println("AT+CMODE=0");
  delay(100);
  while (serialBT->available() <= 0);
  while (serialBT->available() > 0) 
  {
    contenu += serialBT->read();
  }
  if (contenu == "79751310")
  {
    return 64;
  } 
  else
  {
    return 0;
  }
}

int Bluetooth::lien(String adresse) 
{
  String contenu = "";
  Serial.println("Link");
  serialBT->println("AT+LINK=" + adresse);
  delay(10000);
  while (serialBT->available() <= 0);
  while (serialBT->available() > 0) 
  {
    contenu += serialBT->read();
  }
  Serial.println(contenu);
  if (contenu == "79751310")
  {
    return 128;
  } 
  else
  {
    return 0;
  }
}



