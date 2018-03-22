

#include "Bluetooth.h"

Bluetooth::Bluetooth(int rx, int tx){
  serialBluetooth = new SoftwareSerial(rx,tx);
  serialBluetooth->begin(38400);
  pinMode(4,OUTPUT);
  digitalWrite(4,HIGH);
  delay(1000);
  pinMode(5,OUTPUT);
  digitalWrite(5,LOW);
}

int Bluetooth::connexion(String adresse){
    digitalWrite(4,HIGH);
    delay(1000);
    digitalWrite(5,HIGH);
    delay(1000);

    int sommeErreurs = 128;
    
    sommeErreurs += modeMaster();
    sommeErreurs += modeConnection();
    sommeErreurs += motDePasse();
    sommeErreurs += initialisation();
    sommeErreurs += appairage(adresse);
    sommeErreurs += lien(adresse);
    sommeErreurs += modeDeconnecte();
    
    delay(1000);
    digitalWrite(4,LOW);

    return sommeErreurs;
   
}

bool Bluetooth::isActif(){
    String contenu = "test";
    serialBluetooth->println("ATZ");
    while (serialBluetooth->available()<=0);
    while (serialBluetooth->available()>0){
    contenu  = serialBluetooth->read();
    }
    if(contenu == "ERROR:(0)" || contenu == ""){ 
      return 0;
    }
    else {
      return 1;
    }
    
    
}


SoftwareSerial* Bluetooth::getLiaisonBT(){
  return serialBluetooth;
}

int Bluetooth::modeMaster(){
    String contenu = "";
    serialBluetooth->println("AT+ROLE=1");
    while (serialBluetooth->available() <= 0);
    while (serialBluetooth->available() > 0) {
       contenu += serialBluetooth->read();
    }
    if(contenu =="OK")
    {
      return 2;
    }else
    {
      return 0;
    }
}

int Bluetooth::modeConnection(){
    String contenu = "test";
    serialBluetooth->println("AT+CMODE=1");
    while (serialBluetooth->available()<=0);
    while (serialBluetooth->available()>0){
       contenu = serialBluetooth->read();
       if(contenu =="OK")
       {
        return 2;
       }else
       {
        return 0;
       }
    }
}

int Bluetooth::motDePasse(){
    String contenu = "test";
    serialBluetooth->println("AT+PSWD=1234");
    while (serialBluetooth->available()<=0);
    while (serialBluetooth->available()>0){
       contenu = serialBluetooth->read();
       if(contenu =="OK")
       {
        return 4;
       }else
       {
        return 0;
       }
    } 
}

int Bluetooth::initialisation(){
    String contenu = "test";
    serialBluetooth->println("AT+INIT");
    while (serialBluetooth->available()<=0);
    while (serialBluetooth->available()>0){
       contenu = serialBluetooth->read();
       if(contenu =="OK")
       {
        return 8;
       }else
       {
        return 0;
       }
    } 
}

int Bluetooth::appairage(String adresse){
    String contenu = "test";
    serialBluetooth->println("AT+PAIR="+ adresse +",15");
    while (serialBluetooth->available()<=0);
    while (serialBluetooth->available()>0){
       contenu = serialBluetooth->read();
       if(contenu =="OK")
       {
        return 16;
       }else
       {
        return 0;
       }
    } 
}

int Bluetooth::lien(String adresse){
    String contenu = "test";
    serialBluetooth->println("AT+BIND="+adresse);
    while (serialBluetooth->available()<=0);
    while (serialBluetooth->available()>0){
       contenu = serialBluetooth->read();
       if(contenu =="OK")
       {
        return 32;
       }else
       {
        return 0;
       }
    } 
}

int Bluetooth::modeDeconnecte(){
    String contenu = "test";
    serialBluetooth->println("AT+CMODE=0");
    while (serialBluetooth->available()<=0);
    while (serialBluetooth->available()>0){
       contenu = serialBluetooth->read();
       if(contenu =="OK")
       {
        return 64;
       }else
       {
        return 0;
       }
    } 
}



