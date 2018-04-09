// Déclare les broches sur lesquelles sont câblées les LEDs
const int BROCHE_LED_1 = 9;
const int BROCHE_LED_2 = 10;
const int BROCHE_LED_3 = 8;

// Précédent état de la LED 1, 2 et 3
byte etatBrocheLed1 = LOW;
byte etatBrocheLed2 = LOW;
byte etatBrocheLed3 = LOW;

unsigned long compteur;

// Fonction setup(), appelée au démarrage de la carte Arduino
void setup() {

  // Configure les broches des LEDs en sortie
  pinMode(BROCHE_LED_1, OUTPUT);
  pinMode(BROCHE_LED_2, OUTPUT);
  pinMode(BROCHE_LED_3, OUTPUT);
 
  // Configure l'état initial des LEDs
  digitalWrite(BROCHE_LED_1, etatBrocheLed1);
  digitalWrite(BROCHE_LED_2, etatBrocheLed2);
  digitalWrite(BROCHE_LED_3, etatBrocheLed3);

   compteur = 0;
}

void tache1() {

  etatBrocheLed1 = !etatBrocheLed1;
  digitalWrite(BROCHE_LED_1, etatBrocheLed1);
  
}

void tache2() {

   etatBrocheLed2 = !etatBrocheLed2;
   digitalWrite(BROCHE_LED_2, etatBrocheLed2);
  
}

void tache3() {

   etatBrocheLed3 = !etatBrocheLed3;
  digitalWrite(BROCHE_LED_3, etatBrocheLed3);
  
}


// Fonction loop(), appelée continuellement en boucle tant que la carte Arduino est alimentée
void loop() { 

  tache1();
 
  if(compteur % 2 == 0) {
    
  tache2();  // Inverse l'état de la LED 2
  }
 
  if(compteur % 4 == 0) {
 
  tache3();  // Inverse l'état de la LED 3
  }
  delay(500);
  compteur = (compteur + 1) % 100;
}

