
/* 
 * File:   main.cpp
 * Author: snir2g1
 *
 * Created on 4 avril 2018, 12:43
 */

#include <cstdlib>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Message.h"

using namespace std;

void testDecoderEtat() {
    byte bMsg[] = {0x30,3,0,0,0,0,0,0};
    char msg[9];
    memcpy(msg, bMsg, sizeof bMsg);
    msg[8] = '\0';
    string s = msg;
    Etat nvEtat = Message.decoderEtat(s);
}

/*
 * 
 */
int main(int argc, char** argv) {
    testDecoderEtat();
}

