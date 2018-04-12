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
#include "OBD2.h"
#include "DonneesTR.h"
#include <string>

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
    string gps(DonneesTR data);
    string dormir(DonneesTR data);    
};

#endif /* MESSAGE_H */

