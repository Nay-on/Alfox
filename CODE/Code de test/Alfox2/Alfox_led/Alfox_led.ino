/*
*************Projet Alfox*************
*
 */

const int led = 13; //broche led
void setup()
{
pinMode(led, OUTPUT); //led = sortie
}


void loop() //Boucle
{
digitalWrite(led, HIGH); //allumer led
delay(1000);
digitalWrite(led, LOW); // Eteindre led
delay(2000);
}
