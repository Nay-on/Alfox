#include "GPS.h"
#include "HTR.h"

GPS* gpsDateEtHeure;
HTR* htr;
void setup() {

  Serial.begin(9600);
  
  gpsDateEtHeure = new GPS();
  htr = new HTR(gpsDateEtHeure);

}

void loop() {

  gpsDateEtHeure->maj();
  
  if(gpsDateEtHeure->isDispo()){
    Serial.println(htr->lire().tm_mday);
    Serial.println(htr->lire().tm_mon);
    Serial.println(htr->lire().tm_year);
  }
  
}

