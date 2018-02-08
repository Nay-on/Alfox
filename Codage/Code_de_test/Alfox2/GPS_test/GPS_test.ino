#include <SoftwareSerial.h>
#include <TinyGPS.h>


TinyGPS gps;
SoftwareSerial ss(8, 7);

void setup()
{
  Serial.begin(115200);
  ss.begin(9600);
  Serial.println("Projet Alfox");
  Serial.println();
}

void loop()
{
  bool newData = false;
  unsigned long chars;
  unsigned short sentences, failed;

  for (unsigned long start = millis(); millis() - start < 1000;)
  {
    while (ss.available())
    {
      char c = ss.read();
      if (gps.encode(c))
        newData = true;
    }
  }

  if (newData)
  {
    float flat, flon;
    unsigned long age;
    gps.f_get_position(&flat, &flon, &age);
    print_date(gps);
    Serial.print("LAT = ");
    Serial.print(flat == TinyGPS::GPS_INVALID_F_ANGLE ? 0.0 : flat, 6);
    Serial.print("    LON = ");
    Serial.println(flon == TinyGPS::GPS_INVALID_F_ANGLE ? 0.0 : flon, 6);
  }
  
  gps.stats(&chars, &sentences, &failed);
  if (chars == 0)
    Serial.println("** Pas de signal GPS **");
}

//fonction temps
static void print_date(TinyGPS &gps)
{
  int annee;
  byte mois, jour, heure, minute, seconde, hundredths;
  unsigned long age;
  gps.crack_datetime(&annee, &mois, &jour, &heure, &minute, &seconde, &hundredths, &age);
  if (age == TinyGPS::GPS_INVALID_AGE)
    Serial.print("********** ******** ");
  else
  {
    char sz[32];
    sprintf(sz, "%02d/%02d/%02d     %02d:%02d:%02d     ",
        jour, mois, annee, heure, minute, seconde);
    Serial.print(sz);
  }
}

