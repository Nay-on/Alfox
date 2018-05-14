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

void afficherMsg(byte* buf, int size) {
    for (int i = 0; i < size; i++) {
        std::cout << std::dec << (int) buf[i] << ":" << std::hex << (int) buf[i] << " ";
    }
    std::cout << std::endl;
}

void testNouveau(Etat etat, DonneesTR* data) {
    int a = data->getConsoMax();
    byte* bMsg = Message::nouveau(NORMAL, data);
    afficherMsg(bMsg, 12);
}

void testDecoderEtat() {
    int valEtat = (int)Etat::NORMAL;
    byte bMsg[] = {0x30, valEtat, 0, 0, 0, 0, 0, 0};
    Etat nvEtat = Message::decoderEtat(bMsg);
    std::cout << ((nvEtat == valEtat) ? "bien" : "faux") << endl;
}

int main(int argc, char** argv) {
    testDecoderEtat();

    DonneesTR* data = new DonneesTR();
    data->razStat();
    data->initTest();
    testNouveau(NORMAL, data);
}
