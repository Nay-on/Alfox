package ALFOX;

import java.nio.charset.StandardCharsets;

/**
 *
 * @author jpdms
 */
public class Message {
    public enum DM { NV_MODE };
    public enum TM {VOID, NORMAL, DEGRADE, DMD_GPS, GPS, ECO, MAINTENANCE, 
                                    RESET, INIT, DATA1, DATA2, DATA3, SLEEP };
    
    // ------------------- msg descendant ----------------------------------
    public static String nouveauMode(int mode) {
        byte[] bMsg = {0,0,0,0,0,0,0,0};
        bMsg[0] = (byte)DM.NV_MODE.ordinal();
        bMsg[1] = (byte)mode;
        return new String(bMsg);
    }
    
    // --------------------- msg montant -----------------------------------
    public static String normal(boolean connected, int modeVehicule, 
        int[] defauts, int km, int consoMoy, int vitesseMoy, int regimeMoy) {
        // NORMAL : TM CO MV ND CD CD KM KM KM CC VM RM
        byte[] bMsg = {0,0,0,0,0,0,0,0,0,0,0,0};
        // type de trame
        bMsg[0] = (byte)TM.NORMAL.ordinal();
        // la connexion Bluetooth à l'OBD2
        bMsg[1] = (byte)(connected ? 1 : 0);
        // le mode du véhicule                  // non utilisé !!!
        bMsg[2] = (byte)modeVehicule;
        // les pannes valeurs entre 0 et 15
        int nb = 0;
        for (int i = 0; i < 4; i++)
            nb += (defauts[i] > 0 ? 1 : 0);
        bMsg[3] = (byte)nb;            // pas de panne
        bMsg[4] = (byte)((defauts[0] << 4) | defauts[1]);
        bMsg[5] = (byte)((defauts[2] << 4) | defauts[3]); 
        // Kilometrage
        bMsg[6] = (byte)(km/10000);
        bMsg[7] = (byte)((km%10000)/100);
        bMsg[8] = (byte)(km%100);
        // consommation moyenne
        bMsg[9] = (byte)consoMoy;
        // vitesse moyenne
        bMsg[10] = (byte)vitesseMoy;
        // regime moyen 
        bMsg[11] = (byte)regimeMoy;
        return new String(bMsg);
    }
    
    public static String degrade(boolean connected) {
        // DEGRADE : TM CO 00 00 00 00 00 00 00 00 00 00
        byte[] bMsg = {0,0,0,0,0,0,0,0,0,0,0,0};
        bMsg[0] = (byte)TM.DEGRADE.ordinal();
        // la connexion Bluetooth à l'OBD2
        bMsg[1] = (byte)(connected ? 1 : 0);
        return new String(bMsg);
    }
    
    public static String dmdGPS(GPS gps) {
        // DMD_GPS : TM HH MM SS LA LA LA LA LO LO LO LO
        String msg = gps(gps);
        byte[] bMsg = msg.getBytes();
        bMsg[0] = (byte)TM.DMD_GPS.ordinal();
        return new String(bMsg);
    }
    
    // reste le pb de la date (on envoi que l'heure) ?
    public static String gps(GPS gps) {
         //  GPS : TM HH MM SS LA LA LA LA LO LO LO LO
        boolean negLat = false;
        boolean negLg = false;
        float absLat = 0.0f;
        float absLg = 0.0f;
        byte[] bMsg = {0,0,0,0,0,0,0,0,0,0,0,0};
        bMsg[0] = (byte)TM.GPS.ordinal();
        gps.maj();
        bMsg[1] = (byte)gps.getHeureGPS();
        bMsg[2] = (byte)gps.getMinuteGPS();
        bMsg[3] = (byte)gps.getSecondeGPS();
        
        // latitude
        absLat = gps.getLatitude();
        if (absLat < 0) {
            absLat = - absLat;
            negLat = true;
        }
        long iLat = (int)(absLat*1000000);
        bMsg[4] = (byte)(iLat/1000000);
        bMsg[5] = (byte)(iLat%1000000 / 10000);
        bMsg[6] = (byte)(iLat%10000 / 100);
        bMsg[7] = (byte)(iLat%100);
        if (negLat)    // on change le bit de poids faible
            bMsg[7] |= 0x01;
        else
            bMsg[7] &= 0xFE;
        
        // longitude
        absLg = gps.getLongitude();
        if (absLg < 0) {
            absLg = -absLg;
            negLg = true;
        }
        long iLg = (int)(absLg*1000000);
        bMsg[8] = (byte)(iLg/1000000);
        bMsg[9] = (byte)(iLg%1000000 / 10000);
        bMsg[10] = (byte)(iLg%10000 / 100);
        bMsg[11] = (byte)(iLg%100);
        if (negLg)    // on change le bit de poids faible
            bMsg[11] |= 0x01;
        else
            bMsg[11] &= 0xFE;
        
        return new String(bMsg);
    }
    
    public static String eco(boolean connected) {
        // ECO : TM CO 00 00 00 00 00 00 00 00 00 00
        String msg = degrade(connected);
        byte[] bMsg = msg.getBytes();
        bMsg[0] = (byte)TM.ECO.ordinal();
        return new String(bMsg);
    }
    
    public static String sleep(boolean connected) {
        // SLEEP : TM CO 00 00 00 00 00 00 00 00 00 00
        String msg = degrade(connected);
        byte[] bMsg = msg.getBytes();
        bMsg[0] = (byte)TM.SLEEP.ordinal();
        return new String(bMsg);
    }
    
    public static String data1(boolean connected, int[] data) {
        // DATA1 : TM CO D1 D2 D3 D4 D5 D6 D7 D8 D9 D10
        byte[] bMsg = {0,0,0,0,0,0,0,0,0,0,0,0};
        bMsg[0] = (byte)TM.DATA1.ordinal();
        bMsg[1] = (byte)(connected ? 1 : 0);
        for (int i = 2; i < data.length; i++)
            bMsg[i] = (byte)data[i];
        return new String(bMsg);
    }
    
    public static String data2(boolean connected, int[] data) {
        // DATA1 : TM CO D1 D2 D3 D4 D5 D6 D7 D8 D9 D10
        byte[] bMsg = {0,0,0,0,0,0,0,0,0,0,0,0};
        bMsg[0] = (byte)TM.DATA2.ordinal();
        bMsg[1] = (byte)(connected ? 1 : 0);
        for (int i = 2; i < data.length; i++)
            bMsg[i] = (byte)data[i];
        return new String(bMsg);
    }
    
    public static String data3(boolean connected, int[] data) {
        // DATA1 : TM CO D1 D2 D3 D4 D5 D6 D7 D8 D9 D10
        byte[] bMsg = {0,0,0,0,0,0,0,0,0,0,0,0};
        bMsg[0] = (byte)TM.DATA3.ordinal();
        bMsg[1] = (byte)(connected ? 1 : 0);
        for (int i = 2; i < data.length; i++)
            bMsg[i] = (byte)data[i];
        return new String(bMsg);
    }
    
    public static String toString(String msg) {
        byte[] b = msg.getBytes(StandardCharsets.ISO_8859_1);
        String texte = "";
        for (int i = 0; i < b.length; i++) {
            texte = texte + String.format("%02x ", b[i]);
        }
        return texte;
    }
    
    public static String toData(String msg) {
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
            // NORMAL : TM CO MV ND CD CD KM KM KM CC VM RM
            texte = "N> CD:" + (int)b[1] + " MV:" + (int)b[2] + " ND:" + (int)b[3]
                    + " CD:" + (int)b[4] + " CD:" + (int)b[5] 
                    + " KM:" + (int)(b[6]*10000 + b[7]*100 + b[8])
                    + " CC:" + (int)b[9] + " VM:" + (int)b[10] + " RM:" + (int)b[11];
        }
        else if ((int)b[0] == TM.DEGRADE.ordinal()) {
            // DEGRADE: TM CO 00 00 00 00 00 00 00 00 00 00
            texte = "D> CD:" + (int)b[1];
        }
        else if ((int)b[0] == TM.ECO.ordinal()) {
            // ECO : TM CO 00 00 00 00 00 00 00 00 00 00
            texte = "E> CD:" + (int)b[1];
        }
        else if ((int)b[0] == TM.DMD_GPS.ordinal() || (int)b[0] == TM.GPS.ordinal()) {
            // DMD_GPS : TM HH MM SS LA LA LA LA LO LO LO LO
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
