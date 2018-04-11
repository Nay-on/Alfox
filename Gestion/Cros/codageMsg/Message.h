/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Message.h
 * Author: snir2g1
 *
 * Created on 4 avril 2018, 12:44
 */

#include "Global.h"
#include "time.h"
#include "OBD2.h"
#include "DonneesTR.h"
#include <string>
 
using namespace std;
 
 
int main()
{
    string maChaine; //Création d'un objet 'maChaine' de type string
 
    return 0;
}

#ifndef MESSAGE_H
#define MESSAGE_H
using namespace std;

typedef unsigned char   byte;

//enum TM{NORMAL, DEGRADE, DMD_GPS, GPS, DORMIR};
enum EtatCom{BT_OFF, BT_ON_OBD2_OFF, BT_ON_OBD2_ON};

class Message {
public:
    Message();
    Message(const Message& orig);
    virtual ~Message();
    Etat decoderEtat(string msg);
    string nouveau(Etat etat, DonneesTR data);
private:
    string normal(DonneesTR data, EtatCom etatCom);
    string degrade(DonneesTR data, EtatCom etatCom);
    string dmdGPS(DonneesTR data, EtatCom etatCom);
    string gps(DonneesTR data, EtatCom etatCom);
    string dormir(DonneesTR data, EtatCom etatCom);
    //TM typeMessage;
    
};

#endif /* MESSAGE_H */

