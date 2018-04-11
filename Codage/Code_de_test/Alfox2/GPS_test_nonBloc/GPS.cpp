#include "GPS.h"


/*
 *REMARQUE:
 *Le constructeur doit mettre en oeuvre le pattern singleton pour qu'il n'y ait qu'une seule instance du module GPS
 *au lancement de l'application. 
 *Le module GPS est également utilisé pour la classe HTR. Il ne faut pas créer deux modules GPS utilisant chacun 
 *la liaison Serial
 */
 

GPS::GPS()
{
  //Créer la liaison série
	serialGPS = &Serial1;
  //Lancer la liaison série
  serialGPS->begin(9600);
  //Création du gps > librairie Adafruit_gps
	gps = new Adafruit_GPS(serialGPS);

  //Cette ligne sert à réduire la reception du gps en ignorant les données peu utile.
  gps->sendCommand(PMTK_SET_NMEA_OUTPUT_RMCONLY);
  //Taux de rafraichissement des données, plus que 1Hz posera des porblèmes pour la lecture des données
  gps->sendCommand(PMTK_SET_NMEA_UPDATE_1HZ);
  //Permet de récupérer les infos du signal
  gps->sendCommand(PGCMD_ANTENNA);  
}

int GPS::maj()
{
  if (gps->newNMEAreceived()) //si une nouvelle données gps est disponible
  {
    if (!gps->parse(gps->lastNMEA()))
      return -1;
    
    //Mettre les valeurs dans les attributs
    this->latitude = gps->latitudeDegrees;
    this->longitude = gps->longitudeDegrees;

    this->datation.tm_year = gps->year;
    this->datation.tm_mon = gps->month - 1;
    this->datation.tm_mday = gps->day;
    this->datation.tm_hour = gps->hour;
    this->datation.tm_min = gps->minute;
    this->datation.tm_sec = gps->seconds;
    
    //bool qui dit que la donnée est prête à être récupérée
    available = true;
  }


}

void GPS::readDATA()
{
  //Lis la données gps
  char c = gps->read();
}

bool GPS::isDispo()
{
  //Valeur qui dit que la donnée est prête à être récupéré
  if(available == true)
  {
    available = false;
    return true;
  }
  else
    return false;
}

struct tm GPS::getDatationUltimate()
{
  this->datation.tm_year = gps->year;
  this->datation.tm_mon = gps->month - 1;
  this->datation.tm_mday = gps->day;
  this->datation.tm_hour = gps->hour;
  this->datation.tm_min = gps->minute;
  this->datation.tm_sec = gps->seconds;
}




