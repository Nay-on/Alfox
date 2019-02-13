#include "GestionnaireLed.h"

#define SORTIE_LED_ROUGE 2
#define SORTIE_LED_VERTE 3
#define SORTIE_LED_BLEUE 4
#define ENTREE_BOUTON 5

GestionnaireLed* gestionnaireLed;


void setup() {
  // put your setup code here, to run once:
   gestionnaireLed = new GestionnaireLed(SORTIE_LED_ROUGE,SORTIE_LED_VERTE,SORTIE_LED_BLEUE,ENTREE_BOUTON);

}

void loop() {
  // put your main code here, to run repeatedly:
}
