#include "GPS.h"

void setup() {

  Serial.begin(115200);
  GPS* gps = new GPS(8,7);
  
}

void loop() {
  

}
