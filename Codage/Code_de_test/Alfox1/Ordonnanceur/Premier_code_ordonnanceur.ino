// Déclare les broches sur lesquelles sont câblées les LEDs
const int BROCHE_LED_1 = 9;
const int BROCHE_LED_2 = 10;
const int BROCHE_LED_3 = 8;

// Nombre de millisecondes entre deux changements d'état des LED
const unsigned long BLINK_INTERVAL_1 = 1000;
const unsigned long BLINK_INTERVAL_2 = 500;
const unsigned long BLINK_INTERVAL_3 = 2000;

// Précédente valeur de millis() pour la LED 1, 2 et 3
unsigned long previousMillisLed1 = 0;
unsigned long previousMillisLed2 = 0;
unsigned long previousMillisLed3 = 0;

// Précédent état de la LED 1, 2 et 3
byte etatBrocheLed1 = LOW;
byte etatBrocheLed2 = LOW;
byte etatBrocheLed3 = LOW;

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
}

// Fonction loop(), appelée continuellement en boucle tant que la carte Arduino est alimentée
void loop() {

  // Récupére la valeur actuelle de millis()
  unsigned long currentMillis = millis();
  
  // Si BLINK_INTERVAL_1 ou plus millisecondes se sont écoulés
  if(currentMillis - previousMillisLed1 >= BLINK_INTERVAL_1) {
    
    // Garde en mémoire la valeur actuelle de millis()
    previousMillisLed1 = currentMillis;
    
    // Inverse l'état de la LED 1
    etatBrocheLed1 = !etatBrocheLed1;
    digitalWrite(BROCHE_LED_1, etatBrocheLed1);
  }
  
  // Si BLINK_INTERVAL_2 ou plus millisecondes se sont écoulés
  if(currentMillis - previousMillisLed2 >= BLINK_INTERVAL_2) {
    
    // Garde en mémoire la valeur actuelle de millis()
    previousMillisLed2 = currentMillis;
    
    // Inverse l'état de la LED 2
    etatBrocheLed2 = !etatBrocheLed2;
    digitalWrite(BROCHE_LED_2, etatBrocheLed2);
  }
  
  // Si BLINK_INTERVAL_3 ou plus millisecondes se sont écoulés
  if(currentMillis - previousMillisLed3 >= BLINK_INTERVAL_3) {
    
    // Garde en mémoire la valeur actuelle de millis()
    previousMillisLed3 = currentMillis;
    
    // Inverse l'état de la LED 3
    etatBrocheLed3 = !etatBrocheLed3;
    digitalWrite(BROCHE_LED_3, etatBrocheLed3);
  }
}
