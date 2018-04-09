/*
 * Clignotement de la led embaraquée (broche 6) de la carte MKR FOX 1200 en utilisant le timer 4 du proc SAMD21.
 * La led change d'état toutes les 500ms (2Hz).
 * La fréqeunce d'horloge du microcontrôleur est de 48 MHz
 * On commence par diviser la fréquence par 2 -> 24 MHz
 * On redivise la fréquence par 256 grâce au PRE_SCALER -> 93750 Hz
 * Il faut donc compter de 0 à 46874 à cette fréquence pour obtenir 0.5 s
 * La foncution TC4_Handler() est alors appelé en tant qu'ISR (Interruption Sub Routine)
 * Elle allume et éteint la led en alternance.
 */

#define PIN_LED 6
volatile bool etat;


// Set timer TC4 to call the TC4_Handler every millisecond
void setup() {
  Serial.begin(115200);
  etat = true;
  //config de la sortie connectée à la led interne
  pinMode(PIN_LED,OUTPUT);

  
  // Set up the generic clock (GCLK4) used to clock timers
  REG_GCLK_GENDIV = GCLK_GENDIV_DIV(2) |          // On divise les 48MHz  par 2: 48MHz/2 = 24MHz
                    GCLK_GENDIV_ID(4);            // Select Generic Clock (GCLK) 4
  while (GCLK->STATUS.bit.SYNCBUSY);              // Wait for synchronization

  REG_GCLK_GENCTRL = GCLK_GENCTRL_IDC |           // Set the duty cycle to 50/50 HIGH/LOW
                     GCLK_GENCTRL_GENEN |         // Enable GCLK4
                     GCLK_GENCTRL_SRC_DFLL48M |   // Set the 48MHz clock source
                     GCLK_GENCTRL_ID(4);          // Select GCLK4
  while (GCLK->STATUS.bit.SYNCBUSY);              // Wait for synchronization

  // Feed GCLK4 to TC4 and TC5
  REG_GCLK_CLKCTRL = GCLK_CLKCTRL_CLKEN |         // Enable GCLK4 to TC4 and TC5
                     GCLK_CLKCTRL_GEN_GCLK4 |     // Select GCLK4
                     GCLK_CLKCTRL_ID_TC4_TC5;     // Feed the GCLK4 to TC4 and TC5
  while (GCLK->STATUS.bit.SYNCBUSY);              // Wait for synchronization
 
  REG_TC4_COUNT16_CC0 = 46874;                    // Le timer compte de 0 à 46874 à une fréquence de 93750Hz puis déclenche une interruption. Soit une interruption toutes les 0.5s
  while (TC4->COUNT16.STATUS.bit.SYNCBUSY);       // Wait for synchronization

  //NVIC_DisableIRQ(TC4_IRQn);
  //NVIC_ClearPendingIRQ(TC4_IRQn);
  NVIC_SetPriority(TC4_IRQn, 0);    // Set the Nested Vector Interrupt Controller (NVIC) priority for TC4 to 0 (highest)
  NVIC_EnableIRQ(TC4_IRQn);         // Connect TC4 to Nested Vector Interrupt Controller (NVIC)

  REG_TC4_INTFLAG |= TC_INTFLAG_OVF;              // Clear the interrupt flags
  REG_TC4_INTENSET = TC_INTENSET_OVF;             // Enable TC4 interrupts
  // REG_TC4_INTENCLR = TC_INTENCLR_OVF;          // Disable TC4 interrupts
 
  REG_TC4_CTRLA |= TC_CTRLA_PRESCALER_DIV256 |   // On met le présidviseur à 256. 24000000/256 = 93750Hz
                   TC_CTRLA_WAVEGEN_MFRQ |        // Timer 4 en mode "match frequency" (MFRQ) 
                   TC_CTRLA_ENABLE;               // Enable TC4
  while (TC4->COUNT16.STATUS.bit.SYNCBUSY);       // Wait for synchronization
}

void loop() 
{
   //Rien à faire
}

void TC4_Handler()                              // Interrupt Service Routine (ISR) for timer TC4
{     
  
  // Check for overflow (OVF) interrupt
  if (TC4->COUNT16.INTFLAG.bit.OVF && TC4->COUNT16.INTENSET.bit.OVF)             
  {
    etat = !etat;
    digitalWrite(PIN_LED,etat);
    REG_TC4_INTFLAG = TC_INTFLAG_OVF;         // Clear the OVF interrupt flag
  }
}
