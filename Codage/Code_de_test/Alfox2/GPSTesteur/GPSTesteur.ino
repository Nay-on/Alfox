#include "GPS.h"
#include <Arduino.h>

GPS* gps;

void setup() 
{
  gps = new GPS();
  Serial.begin(115200);
  Serial1.begin(9600);
  configureInterrupt_timer4_1ms();
  delay(5000);
  Serial.println("Test de la classe GPS");
  
}

  unsigned long time1;
  unsigned long time2;
  unsigned long time3;
  unsigned long time4;
  unsigned long timeInterrupt;


void loop() {
  //Serial.print(gps->readDATA());
  time1 = millis();
  gps->maj();
  
  if(gps->isDispo()){
    Serial.println(gps->getLatitude(),6);
    Serial.println(gps->getLongitude(),6);
    
    Serial.println(gps->getDatation().tm_mday);
    Serial.println(gps->getDatation().tm_mon);
    Serial.println(gps->getDatation().tm_year);
    Serial.println("-------------------------");

    time2 = millis();
    //Serial.println(time2 - time1);
    //Serial.println(timeInterrupt);
    
  }

}


void TC4_Handler()                              // Interrupt Service Routine (ISR) for timer TC4
{     
  
  // Check for overflow (OVF) interrupt
  if (TC4->COUNT16.INTFLAG.bit.OVF && TC4->COUNT16.INTENSET.bit.OVF)             
  {
    char c = gps->readDATA();
    REG_TC4_INTFLAG = TC_INTFLAG_OVF;         // Clear the OVF interrupt flag
  }
}


//Fonction englobant la configuration et le démarrage des interruptions du Timer 4 toutes les 1 ms.
void configureInterrupt_timer4_1ms()
{
  // Set up the generic clock (GCLK4) used to clock timers
  REG_GCLK_GENDIV = GCLK_GENDIV_DIV(1) |          // On divise les 48MHz  par 1: 48MHz/1=48MHz
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
 
  REG_TC4_COUNT16_CC0 = 47999;                    // Le timer compte de 0 à 47999 soit 48000/48000000 = 1 ms, puis déclenche une interruption
  while (TC4->COUNT16.STATUS.bit.SYNCBUSY);       // Wait for synchronization

  //NVIC_DisableIRQ(TC4_IRQn);
  //NVIC_ClearPendingIRQ(TC4_IRQn);
  NVIC_SetPriority(TC4_IRQn, 0);    // Set the Nested Vector Interrupt Controller (NVIC) priority for TC4 to 0 (highest)
  NVIC_EnableIRQ(TC4_IRQn);         // Connect TC4 to Nested Vector Interrupt Controller (NVIC)

  REG_TC4_INTFLAG |= TC_INTFLAG_OVF;              // Clear the interrupt flags
  REG_TC4_INTENSET = TC_INTENSET_OVF;             // Enable TC4 interrupts
  // REG_TC4_INTENCLR = TC_INTENCLR_OVF;          // Disable TC4 interrupts
 
  REG_TC4_CTRLA |= TC_CTRLA_PRESCALER_DIV1 |      // On met le présidviseur à 1. Pourrait valoir DIV2, DIC4, DIV8, DIV16, DIV64, DIV256, DIV1024
                   TC_CTRLA_WAVEGEN_MFRQ |        // Timer 4 en mode "match frequency" (MFRQ) 
                   TC_CTRLA_ENABLE;               // Enable TC4
  while (TC4->COUNT16.STATUS.bit.SYNCBUSY);       // Wait for synchronization
}
