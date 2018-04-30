package UDPSockets;

import java.net.*;

/**
 * Client UDP temporaire permettant l'envoi d'un message textuel sur un serveur
 * 
 * @author  alex
 */
public class ClientUDP {

    /**
     * envoi en UDP d'un message textuel, sans attente de réponse
     * 
     * @param ip    adresse ip du serveur
     * @param port  numero de port du service
     * @param msg   message à envoyer au serveur
     */
    public static void envoiUDP(String ip, int port, String msg) {
        try {
            InetAddress ia = InetAddress.getByName(ip);
            DatagramPacket envoi = 
                    new DatagramPacket(msg.getBytes(), msg.length(), ia, port);
            DatagramSocket s = new  DatagramSocket();
            ia = InetAddress.getByName(ip);
            s.send(envoi);
        } catch (Exception ex) { }
    }
}