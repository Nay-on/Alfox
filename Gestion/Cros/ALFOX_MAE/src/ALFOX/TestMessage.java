/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ALFOX;

import java.nio.ByteBuffer;

/**
 *
 * @author jpdms
 */
public class TestMessage {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final GPS gps = new GPS();
        
        gps.maj();
        System.out.println("H:" + gps.getHeureGPS() + " M:" + gps.getMinuteGPS() 
                + " S:" + gps.getSecondeGPS()
                + " L:" + gps.getLatitude() + " G:" + gps.getLongitude());
        String msg = Message.gps(gps);
        System.out.println(Message.toString(msg));
        System.out.println(Message.toData(msg));
    }
}
