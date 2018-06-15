/*
 * File:   main.cpp
 * Author: snir2g1
 *
 * Created on 4 avril 2018, 12:43
 */

#include <stdio.h>
#include <iostream>
#include "Message.h"

using namespace std;

byte bMsg[12];

void afficherMsg(byte* buf, int size) {
    cout << "Obtenu  : ";
    for (int i = 0; i < size; i++) {
        cout << std::dec << (int) buf[i] << ":" << std::hex << (int) buf[i] << " ";
    }
    cout << endl;
}

void testMsgUpload(Etat etat, DonneesTR* data) {
    data->razStat();
    data->initTestNORMAL();
    Message::nouveau(NORMAL, data, bMsg);
    cout << "NORMAL  : 209:d1 1:1 30:1e 0:0 42:2a 0:0 175:af 87:57 56:38 28:1c 92:5c 67:43";
    cout << endl;
    afficherMsg(bMsg, 12);
    cout << endl;
    
    data->razStat();
    data->initTestDEGRADE();
    Message::nouveau(DEGRADE, data, bMsg);
    cout << "DEGRADE : 2:2 1:1 30:1e 0:0 0:0 0:0 0:0 0:0 0:0 0:0 0:0 0:0";
    cout << endl;
    afficherMsg(bMsg, 12);
    cout << endl;
    
    data->razStat();
    data->initTestDMD_GPS();
    Message::nouveau(DMD_GPS, data, bMsg);
    cout << "DMD_GPS : 195:C3 12:c 34:22 56:38 43:2b 61:3d 59:3b 88:58 1:1 30:1e 95:5f 8:8";
    cout << endl;
    afficherMsg(bMsg, 12);
    cout << endl;
    
    data->razStat();
    data->initTestGPS1();
    Message::nouveau(GPS, data, bMsg);
    cout << "GPS     : 212:D4 1:1 30:1e 0:0 43:2b 61:3d 59:3b 82:52 1:1 30:1e 95:5f 8:8";
    cout << endl;
    afficherMsg(bMsg, 12);
    cout << endl;
    
    data->razStat();
    data->initTestGPS2();
    Message::nouveau(GPS, data, bMsg);
    cout << "GPS     : 212:D4 12:c 34:22 56:38 43:2b 61:3d 59:3b 89:59 1:1 30:1e 95:5f 9:9";
    cout << endl;
    afficherMsg(bMsg, 12);
    cout << endl;
    
    data->razStat();
    data->initTestDORMIR();
    Message::nouveau(DORMIR, data, bMsg);
    cout << "DORMIR  : 199:C7 1:1 30:1e 0:0 0:0 0:0 0:0 0:0 0:0 0:0 0:0 0:0";
    cout << endl;
    afficherMsg(bMsg, 12);
    cout << endl;
    
    data->razStat();
    data->initTestINIT();
    Message::nouveau(INIT, data, bMsg);
    cout << "INIT    : 6:6 1:1 30:1e 0:0 0:0 0:0 0:0 0:0 0:0 0:0 0:0 0:0";
    cout << endl;
    afficherMsg(bMsg, 12);
    cout << endl;
    cout << "Test terminé !!!";
}

void testMsgDownload() {
    int valEtat = NORMAL;
    for (int i = 0; i < 12; i++)
        bMsg[i] = 0;
    bMsg[0] = 0x30; // Code de changement d'état
    bMsg[1] = valEtat;
    Etat nvEtat = Message::decoderEtat(bMsg);
    cout << "Décodage Etat : NORMAL > ";
    cout << ((nvEtat == valEtat) ? "OK" : "BUG") << endl;
    
    valEtat = DEGRADE;
    for (int i = 0; i < 12; i++)
        bMsg[i] = 0;
    bMsg[0] = 0x30; // Code de changement d'état
    bMsg[1] = valEtat;
    nvEtat = Message::decoderEtat(bMsg);
    cout << "Décodage Etat : DEGRADE > ";
    cout << ((nvEtat == valEtat) ? "OK" : "BUG") << endl;
    
    valEtat = GPS;
    for (int i = 0; i < 12; i++)
        bMsg[i] = 0;
    bMsg[0] = 0x30; // Code de changement d'état
    bMsg[1] = valEtat;
    nvEtat = Message::decoderEtat(bMsg);
    cout << "Décodage Etat : GPS > ";
    cout << ((nvEtat == valEtat) ? "OK" : "BUG") << endl;
    cout << endl;
}

int main(int argc, char** argv) {
    testMsgDownload();

    DonneesTR* data = new DonneesTR();
    testMsgUpload(NORMAL, data);
}
