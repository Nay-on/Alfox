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
    static void nouveau(Etat etat, DonneesTR* data, byte* b);
private:
    static void normal(DonneesTR* data, byte* b);
    static void degrade(DonneesTR* data, byte* b);
    static void dmdGPS(DonneesTR* data, byte* b);
    static void gps(DonneesTR* data, byte* b);
    static void dormir(DonneesTR* data, byte* b);
    static void init(DonneesTR* data, byte* b);
};

#endif /* MESSAGE_H */

