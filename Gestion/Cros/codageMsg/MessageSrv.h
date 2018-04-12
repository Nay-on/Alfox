/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   MessageSrv.h
 * Author: snir2g1
 *
 * Created on 12 avril 2018, 16:03
 */

#ifndef MESSAGESRV_H
#define MESSAGESRV_H
#include <string>
#include "DonneesTR.h"
#include "Global.h"

using namespace std;

class MessageSrv {
public:
    MessageSrv();
    MessageSrv(const MessageSrv& orig);
    virtual ~MessageSrv();
    string nouveauMode(Mode mode);
    bool decoder(DonneesTR data);
private:

};

#endif /* MESSAGESRV_H */

