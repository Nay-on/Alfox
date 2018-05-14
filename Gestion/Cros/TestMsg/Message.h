/*
 * File:   Message.h
 * Author: snir2g1
 *
 * Created on 4 avril 2018, 12:44
 */

#ifndef MESSAGE_H
#define MESSAGE_H

#include "Global.h"
#include "DonneesTR.h"

using namespace std;

typedef unsigned char byte;

class Message {
public:
    static Etat decoderEtat(byte* msg);
    static byte* nouveau(Etat etat, DonneesTR* data);
private:
    static byte* normal(DonneesTR* data);
    static byte* degrade(DonneesTR* data);
    static byte* dmdGPS(DonneesTR* data);
    static byte* gps(DonneesTR* data);
    static byte* dormir(DonneesTR* data);
    static byte* init(DonneesTR* data);
};

#endif /* MESSAGE_H */

