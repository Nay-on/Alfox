/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    
    public static String toData(String msg) throws Exception {
        Connection con = ConnexionMySQL.newConnexion();
        byte[] b = msg.getBytes(StandardCharsets.US_ASCII);
        boolean negLat = false;
        boolean negLg = false;
        String texte = "";
        
        if ((b[7] & 0x01) != 0) {
            b[7] &= 0xFE;
            negLat = true;
        }
        if ((b[11] & 0x01) != 0) {
            b[11] &= 0xFE;
            negLg = true;
        }
        // VOID, NORMAL, DEGRADE, DMD_GPS, GPS, ECO, MAINTENANCE, RESET, INIT, DATA1, DATA2, DATA3
        if ((int)b[0] == TM.VOID.ordinal()) {
            
        }
        else if ((int)b[0] == TM.NORMAL.ordinal()) {
            // NORMAL : TM CD CD K1 K2 K3 VM VY RM RY CM CY
            // Mode boitîer
            String  mode = "NORMAL";
            // Datation
            Date actuelle = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String date = dateFormat.format(actuelle);
            Timestamp datation = Utils.stringToTimestamp(date);
            // Vitesse
            
            // Consommation
            int consommation = (int)b[11];
            
            
            DonneesTR.create(con, mode, datation, consommation, 
            int vitesse, int regime, int consommation, int vitesseMax, int regimeMax,
                int consoMax, int nbDefauts, int defaut1, int defaut2, int defaut3, 
                    int defaut4, double latitude, double longitude,
                        long distanceParcourue, int seqNumber, float snr, float rssi,
                            float avgSnr, String device);
            texte = "N> CD:" + (int)b[1] + " MV:" + (int)b[2] + " ND:" + (int)b[3]
                    + " CD:" + (int)b[4] + " CD:" + (int)b[5] 
                    + " KM:" + (int)(b[6]*10000 + b[7]*100 + b[8])
                    + " CC:" + (int)b[9] + " VM:" + (int)b[10] + " RM:" + (int)b[11];
        }
        else if ((int)b[0] == TM.DEGRADE.ordinal()) {
            // DEGRADE: TM OB 00 00 00 00 00 00 00 00 00 00
            texte = "D> CD:" + (int)b[1];
        }
        else if ((int)b[0] == TM.DMD_GPS.ordinal() || (int)b[0] == TM.GPS.ordinal()) {
            // DMD_GPS : TM HH MM SS L1 L2 L3 L4 G1 G2 G3 G4
            texte = "G> H:" + (int)b[1] + " M:" + (int)b[2] + " S:" + (int)b[3];
            double lat = (float)b[4] + (float)b[5]/100 + (float)b[6]/10000 + (float)b[7]/1000000;
            if (negLat)
                lat = -lat;
            texte += String.format(" L:%2.5f", lat);
            double lg = (float)b[8] + (float)b[9]/100 + (float)b[10]/10000 + (float)b[11]/1000000;
            if (negLg)
                lg = -lg;
            texte += String.format(" G:%2.5f", lg);
        }
        else if ((int)b[0] == TM.DATA1.ordinal()) {
            texte = "D1> CD:" + (int)b[1];
            for (int i = 2; i < 12; i++)
                texte += "   " + (int)b[i];
        }
        else if ((int)b[0] == TM.DATA2.ordinal()) {
            texte = "D2> CD:" + (int)b[1];
            for (int i = 2; i < 12; i++)
                texte += "   " + (int)b[i];
        }
        else if ((int)b[0] == TM.DATA3.ordinal()) {
            texte = "D3> CD:" + (int)b[1];
            for (int i = 2; i < 12; i++)
                texte += "   " + (int)b[i];
        }
        else if ((int)b[0] == TM.RESET.ordinal()) {
            texte = "R> CD:" + (int)b[1];
        }
        else if ((int)b[0] == TM.INIT.ordinal()) {
            texte = "I> CD:" + (int)b[1];
        }
        return texte;
    }
}
