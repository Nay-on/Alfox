package ALFOX;

import java.awt.Color;
import java.util.*;
import javax.swing.UIManager;

/**
 * A traiter :
 *      . ENTRY nouveau mode : envoyer un message de suite
 *      . gérer le modeVehivule
 * Remarque :
 *      Le mode maintenance ne doit pas être suspensif (loop ?)
 * @author jpdms
 */
public class Boitier extends javax.swing.JFrame implements ISigfox {
    public enum Etat { STANDARD, NORMAL, DEGRADE, DMD_GPS, GPS, ECO,
                        MAINTENANCE, RESET, INIT, SLEEP, DATA };
    public Color[] colorLed = { 
            Color.BLUE, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.WHITE, 
            Color.PINK, Color.CYAN,  Color.MAGENTA, Color.GRAY, Color.RED, 
            Color.BLACK, Color.BLUE};
    public static String[] strEtats = { "STANDARD", "NORMAL", "DEGRADE", "DMD_GPS",
                 "GPS", "ECO", "MAINTENANCE", "RESET", "INIT", "SLEEP", "DATA"};
    public enum Event { MODE_STANDARD, DMD_GPS, MODE_GPS, MODE_ECO,
                        MODE_MAINTENANCE, MODE_RESET,  MODE_SLEEP, MODE_DATA,
                        FIN_TEMPO, BLUETOOTH_ON, BLUETOOTH_OFF, 
                        BATT_LOW, BATT_NORMAL};
    public enum Mode { ENTRY, DO, EXIT };
    
    private SimulateurVoiture voiture;
    
    // zone de données partagée
    private GPS gps = new GPS();
    private DonneesTR dpVoiture = new DonneesTR();
    private Sigfox sigfox = new Sigfox("127.0.0.1", 6000, 7000, this);
    
    private Etat etat = Etat.RESET;
    private boolean chgtModeSrv = false;
    private boolean connexionOBD2 = false;
    
    private int[] data = new int[10];
    private int nbData = 0;
    
    private long dureeEnMs = 0;                 // duree de la loop()
    private int tempoMessage = 12000;           // un msg toute les 12 secondes (theorie : 12mn)
    private int tempoEco = 10*tempoMessage;     // 10 fois moins de msg en mode ECO
    private int dureeLoop = tempoMessage/100;   // 100 acquisitions entre deux messages
    
    public Boitier() {
        initComponents();
        configLookAndFeel();
        voiture = new SimulateurVoiture(1000);  // on initialise le boitier
        OBD2.connection(voiture);
        new Scheduler(2000); // attendre 2s avant de lancer la loop()
    }
    
    public void setup() {
        setupIHM();
        // init des données vehicule
        dpVoiture.razStat();
        this.nouvelEtat(Etat.INIT);
    }
    
    // simulation de la loop d'un Arduino
    public void loop() {
        while (true) {
            // Attente passive
            attendre(dureeLoop);
            dureeEnMs = (dureeEnMs + dureeLoop) % tempoEco;
            
            // mise à jour de l'heure actuelle
            HTR.maj();
            // une fois par seconde pour simulation
            if (dureeEnMs % 1000 == 0)
                gps.maj();
            
            dpVoiture.lireOBD2Courant();
            
            majIHM();
            
            // A 3h du matin on lit les données journalières
            if ((etat == Etat.NORMAL) && (HTR.getHeure() == 3) 
                    && (HTR.getMinute() == 0) && (HTR.getSeconde() == 0)) {
                lireOBD2ParJour();
                nouvelEtat(Etat.DATA);
            }
            
            // traitement du DO de chaque état
            traiterEtat(Mode.DO);
            
            if ((dureeEnMs % tempoMessage) == 0) {
                dpVoiture.razStat();
            }
        }
    }
    
    // setup initial de l'IHM du boitier de démo
    private void setupIHM() {
        chkContact.setEnabled(false);
        chkBatterieFaible.setEnabled(false);
        chkBluetooth.setEnabled(false);
        sldRegime.setEnabled(false);
        chkDemarrer.setEnabled(false);
        sldRegime.setValue(0);
        lblRegimeMoyenValeur.setText("0");
        attendre(2000);
        chkContact.setEnabled(true);
        chkBatterieFaible.setEnabled(true);
    }
    
    // mise à jour de l'ihm du boitier de démo
    private void majIHM() {
        lblModeActuel.setText(strEtats[etat.ordinal()]);
        jpnLed.setBackground(colorLed[etat.ordinal()]);
        if (connexionOBD2) {
            lblVitesseValeur.setText("" + dpVoiture.getVitesse());
            lblVitesseMaxValeur.setText("" + dpVoiture.getVitesseMax());
            lblVitesseMoyenneValeur.setText("" + dpVoiture.getVitesseMoyenne());

            lblRegimeValeur.setText("" + dpVoiture.getRegime()*100);
            lblRegimeMaxValeur.setText("" + dpVoiture.getRegimeMax()*100);
            lblRegimeMoyenValeur.setText("" + dpVoiture.getRegimeMoyen()*100);

            lblConsoValeur.setText("" + String.format("%3.2f", dpVoiture.getConsommation()/10.0));
            lblConsoMaxValeur.setText("" + String.format("%3.2f", dpVoiture.getConsoMax()/10.0));
            lblConsoMoyenneValeur.setText("" + String.format("%3.2f", dpVoiture.getConsoMoyenne()/10.0));

            lblCompteurValeur.setText("" + String.format("%6.2f", voiture.getCompteur()));

            chkContact.setEnabled(voiture.getVitesse() == 0);
            if (voiture.getRegime() != 0)
                sldRegime.setValue(voiture.getRegime());
            else
                sldRegime.setValue(1400);
        }
        else {
            lblVitesseValeur.setText("INCONNU");
            lblVitesseMaxValeur.setText("INCONNU");
            lblVitesseMoyenneValeur.setText("INCONNU");

            lblRegimeValeur.setText("INCONNU");
            lblRegimeMaxValeur.setText("INCONNU");
            lblRegimeMoyenValeur.setText("INCONNU");

            lblConsoValeur.setText("INCONNU");
            lblConsoMaxValeur.setText("INCONNU");
            lblConsoMoyenneValeur.setText("INCONNU");

            lblCompteurValeur.setText("" + String.format("%6.2f", voiture.getCompteur()));

            chkContact.setEnabled(voiture.getVitesse() == 0);
            if (voiture.getRegime() != 0)
                sldRegime.setValue(voiture.getRegime());
            else
                sldRegime.setValue(1400);
        }
        lblNbMessages.setText("" + sigfox.getNbMsgsEnvoyes());
    }
    
    private void lireOBD2ParJour() {
        // A déterminer
    }
    
    public void traiterMsgRecu(String msg) {
        String texte = Message.toString(msg);
        System.out.println("Boitier < " +  texte + "\n");
        
        byte[] b = msg.getBytes();
        if (b[0] == Message.DM.NV_MODE.ordinal()) {
            traiterEvenement(Boitier.Event.values()[b[1]]);
            chgtModeSrv = true;
        }
        // else message inconnu ???
    }
    
// -------------- Message descendant bvers le boitier ----------------------
//                      140 par jour de 12 cars
    
// --------------------- MACHINE A ETAT ------------------------------------
    private void nouvelEtat(Etat e) {
        if (e != etat) {
            traiterEtat(Mode.EXIT);
            // on doit toujours revenir à un état de suivi normal
            if (e == Etat.STANDARD) {
                if (connexionOBD2)
                    etat = Etat.NORMAL;
                else
                    etat = Etat.DEGRADE;
            }
            else 
                etat = e;
            traiterEtat(Mode.ENTRY);
            traiterEtat(Mode.DO);
            chgtModeSrv = false;
        }
    }

    // MODE_STANDARD, DMD_GPS, MODE_GPS, MODE_ECO,
    // MODE_MAINTENANCE, MODE_RESET, FIN_TEMPO,
    // BLUETOOTH_ON, BLUETOOTH_OFF, BATT_LOW, BATT_NORMAL
    private void traiterEvenement(Event ev) {
        switch (etat) {
            case INIT:
                nouvelEtat(Etat.INIT);
                break;
            case NORMAL:
                switch (ev) {
                    case DMD_GPS:
                        nouvelEtat(Etat.DMD_GPS);
                        break;
                    case MODE_GPS:
                        nouvelEtat(Etat.GPS);
                        break;
                    case BLUETOOTH_OFF:
                        nouvelEtat(Etat.DEGRADE);
                        break;
                    /*
                    case MODE_DATA:
                        nouvelEtat(Etat.DATA);
                        break;
                    */
                    case BATT_LOW:
                        nouvelEtat(Etat.ECO);
                        break;
                    case MODE_SLEEP:
                        nouvelEtat(Etat.SLEEP);
                        break;
                    case MODE_RESET:
                        nouvelEtat(Etat.INIT);
                        break;
                }
                break;
            case DEGRADE:
                switch (ev) {
                    case DMD_GPS:
                        nouvelEtat(Etat.DMD_GPS);
                        break;
                    case MODE_GPS:
                        nouvelEtat(Etat.GPS);
                        break;
                    case BLUETOOTH_ON:
                        nouvelEtat(Etat.NORMAL);
                        break;
                    case BATT_LOW:
                        nouvelEtat(Etat.ECO);
                        break;
                    case MODE_SLEEP:
                        nouvelEtat(Etat.SLEEP);
                        break;
                    case MODE_RESET:
                        nouvelEtat(Etat.INIT);
                        break;
                }
                break;
            case DMD_GPS :
                // rien à faire
                break;
            case GPS :
                switch (ev) {
                    case MODE_STANDARD:
                        nouvelEtat(Etat.STANDARD);
                        break;
                    case MODE_SLEEP:
                        nouvelEtat(Etat.SLEEP);
                        break;
                    case MODE_RESET:
                        nouvelEtat(Etat.INIT);
                        break;
                    }
                break;
            case ECO :
                switch (ev) {
                    case BATT_NORMAL:
                        nouvelEtat(Etat.STANDARD);
                        break;
                    case MODE_SLEEP:
                        nouvelEtat(Etat.SLEEP);
                        break;
                    case MODE_RESET:
                        nouvelEtat(Etat.INIT);
                        break;
                    }
                break;
            case MAINTENANCE :
                switch (ev) {
                    case MODE_RESET:
                        nouvelEtat(Etat.INIT);
                        break;
                    case MODE_STANDARD:
                        nouvelEtat(Etat.STANDARD);
                        break;
                    }
                break;
        }
    }

    private void traiterEtat(Mode mode) {
        switch (etat) {
            case INIT:
                switch (mode) {
                    case ENTRY:
                        break;
                    case DO:
                        nouvelEtat(Etat.STANDARD);
                        break;
                    case EXIT:
                        break;
                }
                break;
            case NORMAL:
                switch (mode) {
                    case ENTRY:
                        if (chgtModeSrv)
                            dureeEnMs = 0;
                        break;
                    case DO:
                        if ((dureeEnMs % tempoMessage) == 0) {
                            sigfox.envoyer(Message.normal(
                                connexionOBD2, 
                                0, 
                                dpVoiture.getCodeDefauts(), 
                                (int)dpVoiture.getCompteur(),
                                dpVoiture.getConsoMoyenne(), 
                                dpVoiture.getVitesseMoyenne(),
                                dpVoiture.getRegimeMoyen())
                            );
                        }
                        break;
                    case EXIT:
                        break;
                }
                break;
            /*
            case DATA:
                switch (mode) {
                    case DO:
                        if ((dureeEnMs % tempoMessage) == 0) {
                            switch (nbData) {
                                case 0:
                                    sigfox.envoyer(
                                        Message.data1(connexionOBD2, data));
                                    nbData++;
                                    break;
                                case 1:
                                    sigfox.envoyer(
                                        Message.data2(connexionOBD2, data));
                                    nbData++;
                                    break;
                                case 2:
                                    sigfox.envoyer(
                                        Message.data3(connexionOBD2, data));
                                    nbData = 0;
                                    chgtModeSrv = false;
                                    nouvelEtat(Etat.STANDARD);
                                    break;
                            }
                        }
                        break;
                    case EXIT:
                        break;
                }
                break;
            */
            case DEGRADE:
                switch (mode) {
                    case ENTRY:
                        if (chgtModeSrv)
                            dureeEnMs = 0;
                        break;
                    case DO:
                        if ((dureeEnMs % tempoMessage) == 0) {
                            sigfox.envoyer(Message.degrade(connexionOBD2));
                        }
                        break;
                    case EXIT:
                        break;
                }
                break;
            case DMD_GPS:
                switch (mode) {
                    case ENTRY:
                        dureeEnMs = 0;
                        break;
                    case DO:
                        sigfox.envoyer(Message.gps(gps));
                        chgtModeSrv = false;
                        nouvelEtat(Etat.STANDARD);
                        break;
                    case EXIT:
                        break;
                }
                break;
            case GPS:
                switch (mode) {
                    case ENTRY:
                        dureeEnMs = 0;
                        break;
                    case DO:
                        if ((dureeEnMs % tempoMessage) == 0) {
                            sigfox.envoyer(Message.gps(gps));
                        }
                        break;
                    case EXIT:
                        break;
                }
                break;
            case ECO:
                switch (mode) {
                    case ENTRY:
                        if (chgtModeSrv)
                            dureeEnMs = 0;
                        break;
                    case DO:
                        if (dureeEnMs == 0) {
                            sigfox.envoyer(Message.eco(connexionOBD2));
                        }
                        break;
                    case EXIT:
                        break;
                }
                break;
            case SLEEP:
                switch (mode) {
                    case ENTRY:
                        sigfox.envoyer(Message.eco(connexionOBD2));
                }
                break;
            case MAINTENANCE:
                switch (mode) {
                    case ENTRY:
                        // afficher un id sur la console
                        System.out.println("Mode Maintenance : ");
                        break;
                    case DO:
                        break;
                    case EXIT:
                        break;
                }
                break;
            case RESET:
                switch (mode) {
                    case ENTRY:
                        break;
                    case DO:
                        nouvelEtat(Etat.INIT);
                        break;
                    case EXIT:
                        break;
                }
                break;
        }
    }
    
// ------------------ FIN MACHINE A ETAT -----------------------------------
    
    private class Scheduler extends TimerTask {
        private Scheduler(int delai) {
            new java.util.Timer().schedule(this, delai);
        }
        
        @Override
        public void run() {
            System.out.println("Scheduler lancé");
            setup();
            loop();
        }
    }
// ------------- FIN DE SIMULATION DE LA LOOP -----------------------------
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTexte = new javax.swing.JLabel();
        lblModeActuel = new javax.swing.JLabel();
        jpnLed = new javax.swing.JPanel();
        chkContact = new javax.swing.JCheckBox();
        chkBluetooth = new javax.swing.JCheckBox();
        chkDemarrer = new javax.swing.JCheckBox();
        chkBatterieFaible = new javax.swing.JCheckBox();
        sldRegime = new javax.swing.JSlider();
        lblVitesse = new javax.swing.JLabel();
        lblVitesseValeur = new javax.swing.JLabel();
        lblVitesseMoyenne = new javax.swing.JLabel();
        lblVitesseMoyenneValeur = new javax.swing.JLabel();
        lblVitesseMax = new javax.swing.JLabel();
        lblVitesseMaxValeur = new javax.swing.JLabel();
        lblRegime = new javax.swing.JLabel();
        lblRegimeValeur = new javax.swing.JLabel();
        lblRegimeMoyen = new javax.swing.JLabel();
        lblRegimeMoyenValeur = new javax.swing.JLabel();
        lblRegimeMax = new javax.swing.JLabel();
        lblRegimeMaxValeur = new javax.swing.JLabel();
        lblConso = new javax.swing.JLabel();
        lblConsoValeur = new javax.swing.JLabel();
        lblConsoMax = new javax.swing.JLabel();
        lblConsoMaxValeur = new javax.swing.JLabel();
        lblConsoMoyenne = new javax.swing.JLabel();
        lblConsoMoyenneValeur = new javax.swing.JLabel();
        lblCompteur = new javax.swing.JLabel();
        lblCompteurValeur = new javax.swing.JLabel();
        lblNbMessages = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Boitier");

        lblTexte.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblTexte.setText("Mode Actuel : ");

        lblModeActuel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblModeActuel.setText("INIT");
        lblModeActuel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jpnLed.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jpnLedLayout = new javax.swing.GroupLayout(jpnLed);
        jpnLed.setLayout(jpnLedLayout);
        jpnLedLayout.setHorizontalGroup(
            jpnLedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 31, Short.MAX_VALUE)
        );
        jpnLedLayout.setVerticalGroup(
            jpnLedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        chkContact.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        chkContact.setText("Contact");
        chkContact.setEnabled(false);
        chkContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkContactActionPerformed(evt);
            }
        });

        chkBluetooth.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        chkBluetooth.setText("Bluetooth");
        chkBluetooth.setEnabled(false);
        chkBluetooth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBluetoothActionPerformed(evt);
            }
        });

        chkDemarrer.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        chkDemarrer.setText("Moteur");
        chkDemarrer.setEnabled(false);
        chkDemarrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDemarrerActionPerformed(evt);
            }
        });

        chkBatterieFaible.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        chkBatterieFaible.setText("Batterie Faible");
        chkBatterieFaible.setEnabled(false);
        chkBatterieFaible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBatterieFaibleActionPerformed(evt);
            }
        });

        sldRegime.setMaximum(6500);
        sldRegime.setMinimum(1400);
        sldRegime.setEnabled(false);
        sldRegime.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldRegimeStateChanged(evt);
            }
        });

        lblVitesse.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblVitesse.setText("Vitesse :");

        lblVitesseValeur.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblVitesseValeur.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVitesseValeur.setText("0");

        lblVitesseMoyenne.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblVitesseMoyenne.setText("Vit Moy :");

        lblVitesseMoyenneValeur.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblVitesseMoyenneValeur.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVitesseMoyenneValeur.setText("0");

        lblVitesseMax.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblVitesseMax.setText("Vit Max :");

        lblVitesseMaxValeur.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblVitesseMaxValeur.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVitesseMaxValeur.setText("0");

        lblRegime.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblRegime.setText("Régime : ");

        lblRegimeValeur.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblRegimeValeur.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRegimeValeur.setText("0");

        lblRegimeMoyen.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblRegimeMoyen.setText("Rég Moy : ");

        lblRegimeMoyenValeur.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblRegimeMoyenValeur.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRegimeMoyenValeur.setText("0");

        lblRegimeMax.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblRegimeMax.setText("Rég Max : ");

        lblRegimeMaxValeur.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblRegimeMaxValeur.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRegimeMaxValeur.setText("0");

        lblConso.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblConso.setText("Conso : ");

        lblConsoValeur.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblConsoValeur.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblConsoValeur.setText("0");

        lblConsoMax.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblConsoMax.setText("Conso Max :");

        lblConsoMaxValeur.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblConsoMaxValeur.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblConsoMaxValeur.setText("0");

        lblConsoMoyenne.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblConsoMoyenne.setText("Conso Moy :");

        lblConsoMoyenneValeur.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblConsoMoyenneValeur.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblConsoMoyenneValeur.setText("0");

        lblCompteur.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblCompteur.setText("Compteur :");

        lblCompteurValeur.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblCompteurValeur.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCompteurValeur.setText("0");

        lblNbMessages.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNbMessages.setText("0");
        lblNbMessages.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRegime)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblVitesseMax)
                                .addComponent(lblRegimeMoyen)
                                .addComponent(lblRegimeMax)
                                .addComponent(lblConso)
                                .addComponent(lblConsoMoyenne, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblConsoMax)
                                .addComponent(lblVitesseMoyenne)
                                .addComponent(lblVitesse)
                                .addComponent(lblCompteur)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVitesseMaxValeur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRegimeMoyenValeur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRegimeMaxValeur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblConsoValeur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCompteurValeur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblConsoMaxValeur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblVitesseValeur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                .addComponent(lblVitesseMoyenneValeur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblRegimeValeur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                            .addComponent(lblConsoMoyenneValeur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(sldRegime, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(lblNbMessages, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(244, 244, 244))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jpnLed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(lblTexte)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblModeActuel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkDemarrer)
                                    .addComponent(chkContact))
                                .addGap(79, 79, 79)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkBluetooth)
                                    .addComponent(chkBatterieFaible))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpnLed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblModeActuel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTexte)))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkContact)
                    .addComponent(chkBluetooth))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkDemarrer)
                    .addComponent(chkBatterieFaible))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVitesse)
                    .addComponent(lblVitesseValeur))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVitesseMoyenne)
                    .addComponent(lblVitesseMoyenneValeur))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(sldRegime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblVitesseMax)
                            .addComponent(lblVitesseMaxValeur))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRegime)
                            .addComponent(lblRegimeValeur))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRegimeMoyen)
                            .addComponent(lblRegimeMoyenValeur, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRegimeMax)
                            .addComponent(lblRegimeMaxValeur))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblConsoValeur)
                            .addComponent(lblConso, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblConsoMoyenneValeur)
                            .addComponent(lblConsoMoyenne))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblConsoMax)
                            .addComponent(lblConsoMaxValeur))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCompteur)
                            .addComponent(lblCompteurValeur)
                            .addComponent(lblNbMessages))
                        .addContainerGap(15, Short.MAX_VALUE))))
        );

        lblVitesse.getAccessibleContext().setAccessibleName("");

        setSize(new java.awt.Dimension(440, 471));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

// ----------------------- Callback IHM ----------------------------------- 
    private void chkContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkContactActionPerformed
        if (chkContact.isSelected()) {
            voiture.setContact(true);
            chkDemarrer.setEnabled(true);
            chkBluetooth.setEnabled(true);
            chkBluetooth.setSelected(true);
            connexionOBD2 = true;
            traiterEvenement(Event.BLUETOOTH_ON);
        }
        else {
            voiture.setContact(false);
            chkDemarrer.setEnabled(false);
            chkDemarrer.setSelected(false);
            chkBluetooth.setEnabled(false);
            chkBluetooth.setSelected(false);
            sldRegime.setEnabled(false);
            dpVoiture.razStat();
            connexionOBD2 = false;
            traiterEvenement(Event.BLUETOOTH_OFF);
        }
    }//GEN-LAST:event_chkContactActionPerformed

    private void chkBluetoothActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBluetoothActionPerformed
        connexionOBD2 = chkBluetooth.isSelected();
        if (chkBluetooth.isSelected()) {
            traiterEvenement(Event.BLUETOOTH_ON);
        }
        else {
            traiterEvenement(Event.BLUETOOTH_OFF);
        } 
    }//GEN-LAST:event_chkBluetoothActionPerformed

    private void chkDemarrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDemarrerActionPerformed
        if (chkDemarrer.isSelected()) {
            voiture.setDemarrer();
            sldRegime.setEnabled(true);
            chkDemarrer.setEnabled(false);
        }
    }//GEN-LAST:event_chkDemarrerActionPerformed

    private void chkBatterieFaibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBatterieFaibleActionPerformed
        if (chkBatterieFaible.isSelected()) {
            voiture.setBatterieFaible(true);
            traiterEvenement(Event.BATT_LOW);
        }
        else {
            voiture.setBatterieFaible(false);
            traiterEvenement(Event.BATT_NORMAL);
        }
    }//GEN-LAST:event_chkBatterieFaibleActionPerformed

    private void sldRegimeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldRegimeStateChanged
        voiture.setRegime(sldRegime.getValue());
    }//GEN-LAST:event_sldRegimeStateChanged
// -------------------------------------------------------------------------- 

// ------------------------ Utilitaires -------------------------------------
    private void attendre(int duree) {
        try {
            Thread.sleep(duree);
        } catch (InterruptedException ex) { }
    }
    
// -------------------------------------------------------------------------- 
    private void configLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
        }
        catch (Exception e) {  } 
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Boitier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Boitier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Boitier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Boitier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Boitier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkBatterieFaible;
    private javax.swing.JCheckBox chkBluetooth;
    private javax.swing.JCheckBox chkContact;
    private javax.swing.JCheckBox chkDemarrer;
    private javax.swing.JPanel jpnLed;
    private javax.swing.JLabel lblCompteur;
    private javax.swing.JLabel lblCompteurValeur;
    private javax.swing.JLabel lblConso;
    private javax.swing.JLabel lblConsoMax;
    private javax.swing.JLabel lblConsoMaxValeur;
    private javax.swing.JLabel lblConsoMoyenne;
    private javax.swing.JLabel lblConsoMoyenneValeur;
    private javax.swing.JLabel lblConsoValeur;
    private javax.swing.JLabel lblModeActuel;
    private javax.swing.JLabel lblNbMessages;
    private javax.swing.JLabel lblRegime;
    private javax.swing.JLabel lblRegimeMax;
    private javax.swing.JLabel lblRegimeMaxValeur;
    private javax.swing.JLabel lblRegimeMoyen;
    private javax.swing.JLabel lblRegimeMoyenValeur;
    private javax.swing.JLabel lblRegimeValeur;
    private javax.swing.JLabel lblTexte;
    private javax.swing.JLabel lblVitesse;
    private javax.swing.JLabel lblVitesseMax;
    private javax.swing.JLabel lblVitesseMaxValeur;
    private javax.swing.JLabel lblVitesseMoyenne;
    private javax.swing.JLabel lblVitesseMoyenneValeur;
    private javax.swing.JLabel lblVitesseValeur;
    private javax.swing.JSlider sldRegime;
    // End of variables declaration//GEN-END:variables
}
