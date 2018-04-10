
#include "Bluetooth.h"

Bluetooth::Bluetooth(/*Uart* serialBluetooth*/) {
  serialBT= new Uart (&sercom3, 7, 6, SERCOM_RX_PAD_3, UART_TX_PAD_2);
  pinPeripheral(6, PIO_SERCOM_ALT); //Tx
  pinPeripheral(7, PIO_SERCOM_ALT); //Rx
  //serialBT = serialBluetooth; 
  delay(5000);
  serialBT->begin(38400);
}

int Bluetooth::connexion(String adresse) {

  int sommeErreurs = 0;

  sommeErreurs += modeMaster();
  sommeErreurs += modeConnection();
  sommeErreurs += motDePasse();
  sommeErreurs += initialisation();
  sommeErreurs += appairage(adresse);
  delay(10000);
  sommeErreurs += bind(adresse);
  sommeErreurs += modeDeconnecte();
  sommeErreurs += lien(adresse);


  return sommeErreurs;

}

bool Bluetooth::isActif() {
  String contenu = "";
  delay(2000);
  serialBT->println("ATZ");
  delay(100);
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
  delay(100);
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
  delay(100);
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
  delay(100);
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
  delay(200);
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
  serialBT->println("AT+PAIR=" + adresse + ",10");
  delay(100);
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


int Bluetooth::bind(String adresse) {
  String contenu = "";
  serialBT->println("AT+BIND=" + adresse);
  delay(100);
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
  delay(100);
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

int Bluetooth::lien() {
  String contenu = "";
  serialBT->println("AT+LINK=" + adresse);
  delay(100);
  while (serialBT->available() <= 0);
  while (serialBT->available() > 0) {
    contenu += serialBT->read();
  }
  if (contenu == "79751310")
  {
    return 128;
  } else
  {
    return 0;
  }
}



