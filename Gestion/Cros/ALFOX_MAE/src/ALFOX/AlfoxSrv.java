package ALFOX;

import UDPSockets.ServeurUDP;
import java.util.TimerTask;
import javax.swing.UIManager;

public class AlfoxSrv extends javax.swing.JFrame implements ISigfox {
    private ServeurUDP serveurUDP;
    private SigfoxSrv sigfox = new SigfoxSrv("127.0.0.1", 7000, 6000, this);
   
    public AlfoxSrv() {  
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
        }   
        catch (Exception e) {  } 
        initComponents();
        new AfficheNbMsgs(0);
    }
    
// ------------- Messages montant vers le serveur ---------------------
    @Override
    // Callback quand un message arrive sur le serveurUDP
    public void traiterMsgRecu(String msg) {
        String texte = Message.toData(msg);
        // on l'affiche dans la zone des messages reçus
        txtMessages.append(texte + "\n");
        byte[] b = msg.getBytes();
        // 3 entête de DATA pour un seul mode
        if (b[0] >= Boitier.strEtats.length)
            b[0] = (byte)(Boitier.strEtats.length - 1);
        lblMode.setText(Boitier.strEtats[b[0]]);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblModeActuel = new javax.swing.JLabel();
        btnNvMode = new javax.swing.JButton();
        scrollModePanel = new javax.swing.JScrollPane();
        lstMode = new javax.swing.JList<>();
        lblMode = new javax.swing.JLabel();
        scrollMsgPanel = new javax.swing.JScrollPane();
        txtMessages = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        lblNbMessages = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ALFOX SERVEUR");

        lblModeActuel.setText("Mode actuel : ");

        btnNvMode.setText("Demande Nouveau Mode");
        btnNvMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNvModeActionPerformed(evt);
            }
        });

        lstMode.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "STANDARD", "DMD_GPS", "GPS", "ECO", "MAINTENANCE", "RESET", "SLEEP", "DATA" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        scrollModePanel.setViewportView(lstMode);

        lblMode.setBackground(new java.awt.Color(255, 255, 255));
        lblMode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMode.setText("INIT");
        lblMode.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        txtMessages.setEditable(false);
        txtMessages.setColumns(20);
        txtMessages.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        txtMessages.setRows(5);
        txtMessages.setWrapStyleWord(true);
        txtMessages.setDragEnabled(false);
        scrollMsgPanel.setViewportView(txtMessages);

        jLabel1.setText("Messages reçus :");

        lblNbMessages.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNbMessages.setText("0");
        lblNbMessages.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollMsgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(scrollModePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnNvMode, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblModeActuel))
                                .addGap(52, 52, 52)
                                .addComponent(lblNbMessages, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblModeActuel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNbMessages))
                        .addGap(27, 27, 27)
                        .addComponent(btnNvMode, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollModePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollMsgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNvModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNvModeActionPerformed
        // -------------- Message descendant bvers le boitier ----------------------
        //                      4 par jour de 8 cars
        int selection = lstMode.getSelectedIndex();
        // test si il y a eu une séelection
        if ((selection >= 0) && (lstMode.getSelectedIndex() <= 10))
            sigfox.envoyer(Message.nouveauMode(lstMode.getSelectedIndex()));
    }//GEN-LAST:event_btnNvModeActionPerformed
    
    // ffiche le nb de msgs restant pour le quota glissant (5 par heure)
    private class AfficheNbMsgs extends TimerTask {
        private AfficheNbMsgs(int delai) {
            new java.util.Timer().schedule(this, delai);
        }

        @Override
        public void run() {
            while(true) {
                attendre(1000);
                lblNbMessages.setText("" + sigfox.getNbMsgsEnvoyes());
            }
        }
        
        private void attendre(long duree) {
            try {
                Thread.sleep(duree);
            } catch (InterruptedException ex) { }
        }
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
            java.util.logging.Logger.getLogger(AlfoxSrv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlfoxSrv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlfoxSrv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlfoxSrv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AlfoxSrv().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNvMode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblMode;
    private javax.swing.JLabel lblModeActuel;
    private javax.swing.JLabel lblNbMessages;
    private javax.swing.JList<String> lstMode;
    private javax.swing.JScrollPane scrollModePanel;
    private javax.swing.JScrollPane scrollMsgPanel;
    private javax.swing.JTextArea txtMessages;
    // End of variables declaration//GEN-END:variables
}
