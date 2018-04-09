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

#ifndef MESSAGE_H
#define MESSAGE_H
using namespace std;

typedef unsigned char   byte;

class Message {
public:
    Message();
    Message(const Message& orig);
    virtual ~Message();
    Etat decoderEtat(string msg);
    string nouveau(Etat etat, DonneesTR data);
private:
    string normal(DonneesTR data);
    string degrade(DonneesTR data);
    string dmdGPS(DonneesTR data);
    string GPS(DonneesTR data);
    string dormir(DonneesTR data);
    enum TM{NORMAL, DEGRADE, DMD_GPS, GPS, DORMIR};
};

#endif /* MESSAGE_H */

