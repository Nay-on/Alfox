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

#ifndef MESSAGE_H
#define MESSAGE_H
using namespace std;

class Message {
public:
    Message();
    Message(const Message& orig);
    virtual ~Message();
    Etat decoderEtat(string msg);
    string nouveau(Etat etat, DonneesTR datas);
private:
    string normal(DonneesTR datas);
    string degrade();
    string dmdGPS(GPS gps);
    string GPS(GPS gps);
    string dormir();
};

#endif /* MESSAGE_H */

