#include <SoftwareSerial.h>

SoftwareSerial BTserial(7, 8); // RX, TX

//  SerialIn_SerialOut_HM-10_01
//
//  Uses hardware serial to talk to the host computer and SoftwareSerial for communication with the bluetooth module
//
//  What ever is entered in the serial monitor is sent to the connected device
//  Anything received from the connected device is copied to the serial monitor
//  Does not send line endings to the HM-10
//
//  Pins
//  BT VCC to Arduino 5V out. 
//  BT GND to GND
//  Arduino D7 (SS RX) - BT TX no need voltage divider 
//  Arduino D8 (SS TX) - BT RX through a voltage divider (5v to 3.3v)
//
//  Exemple de commande AT qu'on peut transmettre au module:
//  AT
//  AT+VERR?  //pour connaitre la version du firmware
//  AT+NAME?  //pour connaitre le nom du module
//  AT
 
 
char c=' ';
boolean NL = true;
 
void setup() 
{
    Serial.begin(9600);
    Serial.print("Nom du fichier:   ");   Serial.println(__FILE__);
    Serial.print("Date de téléchargement: ");   Serial.println(__DATE__);
    Serial.println(" ");
 
    BTserial.begin(9600);  
    Serial.println("BTserial démarrée à 9600 bauds");
}
 
void loop()
{
    // Read from the Bluetooth module and send to the Arduino Serial Monitor
    if (BTserial.available())
    {
        c = BTserial.read();
        Serial.write(c);
    }
 
 
    // Read from the Serial Monitor and send to the Bluetooth module
    if (Serial.available())
    {
        c = Serial.read();
 
        // do not send line end characters to the HM-10
        if (c!=10 & c!=13 ) 
        {  
             BTserial.write(c);
        }
 
        // Echo the user input to the main window. 
        // If there is a new line print the ">" character.
        if (NL) { Serial.print("\r\n>");  NL = false; }
        Serial.write(c);
        if (c==10) { NL = true; }
    }
}
