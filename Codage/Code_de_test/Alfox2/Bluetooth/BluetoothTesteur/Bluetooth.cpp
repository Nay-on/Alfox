
#include "Bluetooth.h"

Bluetooth::Bluetooth() {
  serialBT= new Uart (&sercom3, 7, 6, SERCOM_RX_PAD_3, UART_TX_PAD_2);
  pinPeripheral(6, PIO_SERCOM_ALT); //Tx
  pinPeripheral(7, PIO_SERCOM_ALT); //Rx
  serialBT->begin(38400);
}

int Bluetooth::connexion(String adresse) {

  int sommeErreurs = 128;

  sommeErreurs += modeMaster();
  sommeErreurs += modeConnection();
  sommeErreurs += motDePasse();
  sommeErreurs += initialisation();
  sommeErreurs += appairage(adresse);
  sommeErreurs += lien(adresse);
  sommeErreurs += modeDeconnecte();


  return sommeErreurs;

}

bool Bluetooth::isActif() {
  String contenu = "";
  delay(2000);
  serialBT->println("ATZ");
  while (serialBT->available() <= 0);
  while (serialBT->available() > 0) {
    contenu  += serialBT->read();
  }
  Serial.println(contenu);
  if (contenu == "6982827982584048411310" || contenu == "") {
    return 0;
  }
  else {
    return 1;
  }


}


Uart* Bluetooth::getLiaisonBT() {
  return serialBT;
}

int Bluetooth::modeMaster() {
  String contenu = "";
  serialBT->println("AT+ROLE=1");
  while (serialBT->available() <= 0);
  while (serialBT->available() > 0) {
    contenu += serialBT->read();
  }
  if (contenu == "79751310")
  {
    return 1;
  } else
  {
    return 0;
  }
}

int Bluetooth::modeConnection() {
  String contenu = "";
  serialBT->println("AT+CMODE=1");
  while (serialBT->available() <= 0);
  while (serialBT->available() > 0) {
    contenu += serialBT->read();
  }
  if (contenu == "79751310")
  {
    return 2;
  } else
  {
    return 0;
  }
}


int Bluetooth::motDePasse() {
  String contenu = "";
  serialBT->println("AT+PSWD=1234");
  while (serialBT->available() <= 0);
  while (serialBT->available() > 0) {
    contenu += serialBT->read();
  }
  if (contenu == "79751310")
  {
    return 4;
  } else
  {
    return 0;
  }
}


int Bluetooth::initialisation() {
  String contenu = "";
  serialBT->println("AT+INIT");
  while (serialBT->available() <= 0);
  while (serialBT->available() > 0) {
    contenu += serialBT->read();
  }
  if (contenu == "79751310")
  {
    return 8;
  } else
  {
    return 0;
  }
}


int Bluetooth::appairage(String adresse) {
  String contenu = "";
  serialBT->println("AT+PAIR=" + adresse + ",15");
  while (serialBT->available() <= 0);
  while (serialBT->available() > 0) {
    contenu += serialBT->read();
  }
  if (contenu == "79751310")
  {
    return 16;
  } else
  {
    return 0;
  }
}


int Bluetooth::lien(String adresse) {
  String contenu = "";
  serialBT->println("AT+BIND=" + adresse);
  while (serialBT->available() <= 0);
  while (serialBT->available() > 0) {
    contenu += serialBT->read();
  }
  if (contenu == "79751310")
  {
    return 32;
  } else
  {
    return 0;
  }
}


int Bluetooth::modeDeconnecte() {
  String contenu = "";
  serialBT->println("AT+CMODE=0");
  while (serialBT->available() <= 0);
  while (serialBT->available() > 0) {
    contenu += serialBT->read();
  }
  if (contenu == "79751310")
  {
    return 64;
  } else
  {
    return 0;
  }
}



