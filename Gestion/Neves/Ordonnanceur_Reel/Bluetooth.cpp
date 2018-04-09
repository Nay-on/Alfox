#include "Bluetooth.h"
#include <SoftwareSerial.h>


Bluetooth::Bluetooth(int rx, int tx){
  serialBluetooth = new SoftwareSerial(rx,tx);
  serialBluetooth->begin(38400);
}

bool Bluetooth::connexion(String adresse){
    digitalWrite(5,HIGH);
    delay(1000);
    digitalWrite(4,HIGH);
    delay(1000);
    
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
    delay(1000);
    digitalWrite(4,LOW);
   
}

bool Bluetooth::isActif(){
    String contenu = "test";
    serialBluetooth->println("ATZ");
    while (serialBluetooth->available()<=0);
    while (serialBluetooth->available()>0){
    contenu  = serialBluetooth->read();
    }
    if(contenu == "ERROR:(0)" ){ 
      return 0;
    }
    else {
      return 1;
    }
    
    
}


SoftwareSerial* Bluetooth::getLiaisonBT(){
  return serialBluetooth;
}

