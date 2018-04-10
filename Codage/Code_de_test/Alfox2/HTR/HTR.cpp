#include "HTR.h"

HTR::HTR(GPS* gps){
  this->gps = gps;
}

struct tm HTR::lire(){
  return gps->getDatation();
}

