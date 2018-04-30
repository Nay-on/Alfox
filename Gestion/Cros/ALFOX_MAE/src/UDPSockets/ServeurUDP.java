package UDPSockets;

import java.net.*;

/**
 * Serveur UDP en attente de message textuel
 * le message est ensuite passé �  l'interface IMessagerie pour traitement.
 * 
 * @author alex
 */
public class ServeurUDP extends Thread {
    private byte[] tampon = new byte[1000];
    private DatagramSocket socket;
    private DatagramPacket reception;
    private String msg;
    private IMessagerie iMsg;

    /**
     * Création du serveur UDP avec son port d'écoute
     * 
     * @param port  numero de port du service
     * @param iMsg  l'instance devant traiter le message
     */
    public ServeurUDP(int port, IMessagerie iMsg) throws Exception {
	this.iMsg = iMsg;
        socket = new DatagramSocket(port);
        reception = new DatagramPacket( tampon, tampon.length );
        start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                socket.receive( reception );
            } catch (Exception ex) {  }
            msg = new String( tampon, 0, reception.getLength());
            iMsg.nouveauMessage(msg);
        }
    }
}