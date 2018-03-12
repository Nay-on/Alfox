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

