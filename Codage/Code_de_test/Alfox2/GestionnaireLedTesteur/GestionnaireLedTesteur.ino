#include "GestionnaireLed.h"
#include "LedTri.h"

//LedTri *ledTri;
//SAMDtimer *timer4;

static volatile GestionnaireLed* gestionnaireLed;


void setup() {
  //put your setup code here, to run once:
  gestionnaireLed = new GestionnaireLed(2,3,4,5,3);
 
}

void loop() {
  // put your main code here, to run repeatedly:
}
