#include "OBD2.h"

uint16_t hex2uint16(const char *p)
{
  char c = *p;
  uint16_t i = 0;
  //On balaye les 4 caractères de la chaîne, chacun correspondant à un digit de la valeur hexa finale
  for (uint8_t n = 0; c && n < 4; c = *(++p)) {
    if (c >= 'A' && c <= 'F') { //si c est une des lettres d'un nombre hexa
      c -= 7;  //c contient un code ASCII correspondant à une valeur hexa comprise entre 3A et 3F. Plus tard on gardera uniquement 0xA à 0xF
    } else if (c>='a' && c<='f') {
      c -= 39; //c vaut aussi une valeur hexa comprise entre 3A et 3F, plus tard on gardera uniquement 0xA à 0xF
        } else if (c == ' ' && n == 2) {
            continue;
        } else if (c < '0' || c > '9') { //si c n'est ni une lettre entre A et F ni un chifre, on quitte le test
      break;
        }
      //Après avoir passé ces différents tests, c a été modifié ou c et le code ASCII d'un chiffre donc 0x30 < c < 0x39
    i = (i << 4) | (c & 0xF); //on décale i de 4 bits (un digit) et on "ajoute" la valeur hexa des 4 bits de poids faibles de c à savoir une valeur hexa comprise entre 0 et F
    n++; // on passe au digit suivant
  }
  return i;
}

byte hex2uint8(const char *p)
{
  byte c1 = *p;
  byte c2 = *(p + 1);
  if (c1 >= 'A' && c1 <= 'F')
    c1 -= 7;
  else if (c1 >='a' && c1 <= 'f')
      c1 -= 39;
  else if (c1 < '0' || c1 > '9')
    return 0;

  if (c2 == 0)
    return (c1 & 0xf);
  else if (c2 >= 'A' && c2 <= 'F')
    c2 -= 7;
  else if (c2 >= 'a' && c2 <= 'f')
      c2 -= 39;
  else if (c2 < '0' || c2 > '9')
    return 0;

  return c1 << 4 | (c2 & 0xf);
}


OBD2::OBD2(Bluetooth* bt){
  
  this->moduleBT = bt;
  liaisonBT = moduleBT->getLiaisonBT();
  liaisonBT->flush();
  //liaisonBT->println("AT S0");
  //Serial.println(lireReponse());
  //liaisonBT->println("AT");
  //Serial.println(lireReponse());
  //liaisonBT->println("AT H0");
  //Serial.println(lireReponse());  
  //liaisonBT->println("AT R0");
  //Serial.println(lireReponse());
  liaisonBT->println("AT E0");
  Serial.println(lireReponse());
  liaisonBT->println("AT SP0");
  Serial.println(lireReponse());
}


String OBD2::demande(TCode numCode){
  liaisonBT->println(code[numCode]);
  String a = lireReponse();
  return a/*.toInt()*/;
  
}

String OBD2::lireReponse(){
  String reponse = "";
  delay(100);
  while (liaisonBT->available() <= 0);
  reponse = liaisonBT->readStringUntil('>');
  return reponse;
}

int OBD2::lireVitesse(){
  String reponse = demande(C_VITESSE);
   //Si la vitesse n'est pas suffisante, pas de donnée, on renvoie -1
  if (reponse.substring(0,7) == "NO DATA")
    return -1;

   //Si le contact est coupé, la connexion est impossible, on renvoie -2
  if (reponse.substring(0,17) == "UNABLE TO CONNECT")
    return -2;
  
  //Si la connexion bluetooth est perdue on renvoie -3
  if (reponse.substring(0,9) == "ERROR:(0)")
    return -3;
  
  return hex2uint8(reponse.substring(DEBUT_POIDS_FORT,FIN_POIDS_FORT).c_str());
}

int OBD2::lireRegimeMoteur(){
  String reponse = demande(C_REGIME);

  //Si le moteur n'est pas démarré on renvoie -1
  if (reponse.substring(0,7) == "NO DATA")
    return -1;
    
  //Si le contact est coupé, la connexion est impossible, on renvoie -2
  if (reponse.substring(0,17) == "UNABLE TO CONNECT")
    return -2;
  
  //Si la connexion bluetooth est perdue on renvoie -3
  if (reponse.substring(0,9) == "ERROR:(0)")
    return -3;
    
#ifdef DEBUG
  Serial.println(reponse);
  Serial.println(reponse.substring(DEBUT_POIDS_FORT,FIN_POIDS_FORT));
  Serial.println(reponse.substring(DEBUT_POIDS_FAIBLE,FIN_POIDS_FAIBLE));
#endif
  String trame  = "";
  trame = reponse.substring(DEBUT_POIDS_FORT,FIN_POIDS_FORT);
  trame += reponse.substring(DEBUT_POIDS_FAIBLE,FIN_POIDS_FAIBLE);
  return hex2uint16(trame.c_str())/4;
}

float OBD2::lireConsomation(){
  String reponse = demande(C_CONSOMMATION);
  
  //Si la vitesse n'est pas suffisante, pas de donnée, on renvoie -1
  if (reponse.substring(0,7) == "NO DATA")
    return -1;
  
  //Si le contact est coupé, la connexion est impossible, on renvoie -2
  if (reponse.substring(0,17) == "UNABLE TO CONNECT")
    return -2;
  
  //Si la connexion bluetooth est perdue on renvoie -3
  if (reponse.substring(0,9) == "ERROR:(0)")
    return -3;
    
#ifdef DEBUG
  Serial.println(reponse);
  Serial.println(reponse.substring(DEBUT_POIDS_FORT,FIN_POIDS_FORT));
  Serial.println(reponse.substring(DEBUT_POIDS_FAIBLE,FIN_POIDS_FAIBLE));
#endif
  String trame = reponse.substring(DEBUT_POIDS_FORT,FIN_POIDS_FORT);
  trame += reponse.substring(DEBUT_POIDS_FAIBLE,FIN_POIDS_FAIBLE);
  return ((float)hex2uint16(trame.c_str()))/20;
}
