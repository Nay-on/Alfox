
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
  delay(100);
  digitalWrite(pinEn, HIGH);
  delay(100);
  digitalWrite(pinAlim, HIGH);
  delay(1000);
  serialBT->begin(38400);
}

int Bluetooth::connexion(String adresse) 
{
  this->adresseOBD2=adresse;
  if(this->isActif() == true)
  {
    return 1;
  }
  else
  {
    int sommeErreurs = 0;

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

bool Bluetooth::isActif() 
{
  String contenu = "";
  serialBT->println("ATI");
  /*while (serialBT->available() <= 0){Serial.print(serialBT->read());};*/
  //contenu = serialBT->readStringUntil('\n');
  delay(5000);//5000 par défaut
  while (serialBT->available() <= 0);
  while (serialBT->available() > 0) 
  {
    contenu += serialBT->read();
  }
  
  //Serial.println(contenu);
  
  if (contenu.substring(0, 3) == "697")
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

int Bluetooth::reinitialiser() 
{
  serialBT->println("AT+ORGL");
}

int Bluetooth::modeMaster() 
{
  String contenu = "";
  serialBT->println("AT+ROLE=1");
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

int Bluetooth::modeConnexion() 
{
  String contenu = "";
  serialBT->println("AT+CMODE=1");
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


int Bluetooth::initialisation() 
{
  String contenu = "";
  serialBT->println("AT+INIT");
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


int Bluetooth::appairage(String adresse) 
{
  String contenu = "";
  Serial.println("Appairage");
  serialBT->println("AT+PAIR=" + adresse + ",10");//10 par défaut
  //delay(5000);
  delay(100);
  while (serialBT->available() <= 0);
  /*{
    Serial.println(serialBT->read());
    }*/
  contenu = serialBT->readStringUntil('\n');
  Serial.println(contenu);
  /*delay(5000);//5000 par défaut
  while (serialBT->available() <= 0);
  while (serialBT->available() > 0) 
  {
    contenu += serialBT->read();
  }
  Serial.println(contenu);
  */
  if (contenu == "OK" )
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

int Bluetooth::modeDeconnecte() 
{
  String contenu = "";
  serialBT->println("AT+CMODE=0");
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

int Bluetooth::lien(String adresse) 
{
  String contenu = "";
  Serial.println("Link");
  serialBT->println("AT+LINK=" + adresse);
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

