#include "GestionnaireLed.h"

//LedTri *ledTri;
//SAMDtimer *timer4;

//static ETAT etat = 0;
GestionnaireLed* gestionnaireLed;


void setup() {
  // put your setup code here, to run once:
   gestionnaireLed = new GestionnaireLed(2,3,4,5);

}

void loop() {
  // put your main code here, to run repeatedly:
}
