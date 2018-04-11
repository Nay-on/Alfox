#include "GPS.h"
#include "wiring_private.h" // pinPeripheral() function

GPS::GPS()
{
  //Création de la liaison série
	//serialGPS = new Uart(&sercom3, rx, tx, SERCOM_RX_PAD_1, UART_TX_PAD_0);  //Rx Pin 8, Tx Pin 7
  serialGPS = &Serial1;

 //Lancer la liaison série
  serialGPS->begin(9600);
  //Création du gps > librairie Adafruit_gps
	gps = new Adafruit_GPS(serialGPS);
  
 
  //pinPeripheral(tx, PIO_SERCOM); //Tx
  //pinPeripheral(rx, PIO_SERCOM); //Rx
  delay(5000); // délai pour laisser le temps que tout se configure correctement

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
    return 0;
    
  }
}

char GPS::readDATA()
{
  //Lit la donnée gps
  char c = gps->read();
  return c;
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




