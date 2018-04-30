package ALFOX;

import UDPSockets.ClientUDP;
import UDPSockets.IMessagerie;
import UDPSockets.ServeurUDP;
import java.util.TimerTask;

/**
 *
 * @author jpdms
 */
public class Sigfox implements IMessagerie {
    private String ip;
    private int portReception;
    private int portEmission;
    private ServeurUDP serveurUDP;
    private Boitier iMsgSigfox;
    private int nbMsgsEnvoyes = 0;
    private final int NB_MAX = 5;
    private long delaiGlissant = 10000;          // 10s (au lieu de 10mn)
    
    public Sigfox(String _ip, int _portEmission, int _portReception, Boitier _iMsgSigfox) {
        ip = _ip;
        portEmission  = _portEmission;
        portReception = _portReception;
        try {
            serveurUDP = new ServeurUDP(portReception, this);
        } catch (Exception ex) { }
        iMsgSigfox = _iMsgSigfox;
        new GestionNbMsgs(0);
    }
    
    @Override
    // Callback quand un message arrive sur le serveurUDP
    public void nouveauMessage(String msg) {
        iMsgSigfox.traiterMsgRecu(msg);
    }
    
    public boolean envoyer(String msg) {
        if (nbMsgsEnvoyes < NB_MAX) {
            System.out.println("msg envoyé > " + Message.toString(msg));
            ClientUDP.envoiUDP(ip, portEmission, msg);
            nbMsgsEnvoyes++;
            return true;
        }
        else {
            System.out.println("qQuota de messages atteint !");
            return false;
        }
    }
    
    public int getNbMsgsEnvoyes() {
        return nbMsgsEnvoyes;
    }
    
    public void decNbMsgsEnvoyes() {
        if (nbMsgsEnvoyes > 0)
            nbMsgsEnvoyes--;
    }
    
    // gére la décrémentation pour le quota glissant (5 par heure)
    private class GestionNbMsgs extends TimerTask {
        private GestionNbMsgs(int delai) {
            new java.util.Timer().schedule(this, delai);
        }

        @Override
        public void run() {
            while(true) {
                attendre(delaiGlissant);
                decNbMsgsEnvoyes();
            }
        }
        
        private void attendre(long duree) {
            try {
                Thread.sleep(duree);
            } catch (InterruptedException ex) { }
        }
    }
}
