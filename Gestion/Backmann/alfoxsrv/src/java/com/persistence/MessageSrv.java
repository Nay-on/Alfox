/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistence;

/**
 *
 * @author snir2g1
 */
public class MessageSrv {
    public enum DM { NV_MODE };
    public enum TM {VOID, NORMAL, DEGRADE, DMD_GPS, GPS, MAINTENANCE, 
                                    RESET, INIT, DATA1, DATA2, DATA3, SLEEP };
    
    public static String nouveauMode(int mode) {
        byte[] bMsg = {0,0,0,0,0,0,0,0};
        bMsg[0] = (byte)DM.NV_MODE.ordinal();
        bMsg[1] = (byte)mode;
        return new String(bMsg);
    }
}
