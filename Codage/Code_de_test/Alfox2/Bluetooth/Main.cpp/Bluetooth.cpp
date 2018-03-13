#include "Bluetooth.h"

Bluetooth::Bluetooth(int rx, int tx){
  serialBluetooth = new SoftwareSerial(rx,tx);
  serialBluetooth->begin(38400);
}

bool Bluetooth::connexion(String adresse){
    Serial.println("Envoi des commandes AT");
    serialBluetooth->println("AT+ROLE=1");
    while (serialBluetooth->available()<=0);
    while (serialBluetooth->available()>0){
       Serial.write(serialBluetooth->read());
    } 
    serialBluetooth->println("AT+CMODE=1");
    while (serialBluetooth->available()<=0);
    while (serialBluetooth->available()>0){
       Serial.write(serialBluetooth->read());
    }
    serialBluetooth->println("AT+PSWD=1234");
    while (serialBluetooth->available()<=0);
    while (serialBluetooth->available()>0){
       Serial.write(serialBluetooth->read());
    } 
    serialBluetooth->println("AT+INQM=0,8,15");
    while (serialBluetooth->available()<=0);
    while (serialBluetooth->available()>0){
       Serial.write(serialBluetooth->read());
    }
    serialBluetooth->println("AT+INIT");
    while (serialBluetooth->available()<=0);
    while (serialBluetooth->available()>0){
       Serial.write(serialBluetooth->read());
    }
    serialBluetooth->println("AT+PAIR="+ adresse +",15");
    while (serialBluetooth->available()<=0);
    while (serialBluetooth->available()>0){
       Serial.write(serialBluetooth->read());
    }
    serialBluetooth->println("AT+BIND="+adresse);
    while (serialBluetooth->available()<=0);
    while (serialBluetooth->available()>0){
       Serial.write(serialBluetooth->read());
    }
    serialBluetooth->println("AT+CMODE=0");
    while (serialBluetooth->available()<=0);
    while (serialBluetooth->available()>0){
       Serial.write(serialBluetooth->read());
    }
}

bool Bluetooth::isActif(){
    String contenu = "test";
    serialBluetooth->println("ATZ");
    while (serialBluetooth->available()<=0);
    while (serialBluetooth->available()>0){
    contenu  = serialBluetooth->read();
    }
    if(contenu == "ERROR:(0)" ) putchar("Le bluetooth n'est pas configuré");
    else putchar("Le bluetooth est bien configuré");
    
    
}
    

