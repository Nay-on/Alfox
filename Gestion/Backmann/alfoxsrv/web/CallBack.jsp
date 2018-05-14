<%-- 
    Document   : CallBack
    Created on : 9 avr. 2018, 11:46:50
    Author     : snir2g2
--%>


<%@page import="messagesrv.MessageSrv"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.metier.*"%>
<%@page import="com.persistence.*"%>


<%
    Connection con = (Connection) session.getAttribute("con");
    if (con == null) {
        con = ConnexionMySQL.newConnexion();
        session.setAttribute("con", con);
    }
    
    String sMode[] = {"START", "STANDARD", "NORMAL", "DEGRADE", "DMD_GPS", 
                      "GPS", "MAINTENANCE", "INIT", "DORMIR", "ERREUR"};
    
    // la date et l'heure du message SIGFOX
    // the event timestamp (in seconds since the Unix Epoch)
    // le constructeur attends des millisecondes
    Date date = new Date(Long.parseLong(request.getParameter("time")) * 1000);
    Timestamp datation = new Timestamp(date.getTime());
    String device = request.getParameter("id");
    int seqNumber = Integer.parseInt(request.getParameter("seqNumber"));
    double latitude = Double.parseDouble(request.getParameter("lat"));
    double longitude = Double.parseDouble(request.getParameter("lng"));
    float snr = Float.parseFloat(request.getParameter("snr"));
    float rssi= Float.parseFloat(request.getParameter("rssi"));
    float avgSnr = Float.parseFloat(request.getParameter("avgSnr"));
    
    // traitement de la trame utile des données
    String data = request.getParameter("data");
    byte[] bData = data.getBytes();
    int vitesseMoy = 0, regimeMoy = 0, consoMoy = 0, vitesseMax = 0, 
        regimeMax = 0, consoMax = 0;
    int defaut1 = 0, defaut2 = 0, defaut3 = 0, defaut4 = 0;
    long distanceParcourue = 0L;
    
    // décodage du 1er octet TM
    boolean bluetoothActif = (bData[0] & 0x80) == 0x80;
    boolean OBD2Actif = (bData[0] & 0x40) == 0x40;
    int nbDefauts = 0;
    boolean isDefaut = (bData[0] & 0x10 >> 4) != 0;
    String mode = sMode[bData[0] & 0x0F];
    if (mode == "ERREUR") {
        // rien à faire
    } 
    else if (mode == "NORMAL") {
        // NORMAL : TM K1 K2 K3 CD CD VX VM RX RM CX CM
        distanceParcourue = bData[1]*10000 + bData[2]*100 + bData[3];
        defaut1 = (bData[4] & 0xF0) >> 4;
        defaut2 =  bData[4] & 0x0F;
        defaut3 = (bData[5] & 0xF0) >> 4;
        defaut4 =  bData[5] & 0x0F;
        nbDefauts = ((defaut1 != 0)?1:0) + ((defaut3 != 0)?1:0) 
                  + ((defaut3 != 0)?1:0) + ((defaut4 != 0)?1:0);
        vitesseMax = bData[6];
        vitesseMoy = bData[7];
        regimeMax  = bData[8]*100;
        regimeMoy  = bData[9]*100;
        consoMax   = bData[10];
        consoMoy   = bData[11];
        DonneesTR donneesTR = DonneesTR.create(con, mode, datation,
            vitesseMoy, regimeMoy, consoMoy, vitesseMax, regimeMax, consoMax, 
            nbDefauts, defaut1, defaut2, defaut3, defaut4, 
            latitude, longitude, distanceParcourue, 
            seqNumber, snr, rssi, avgSnr, device);
    }
    else if ((mode == "DEGRADE") || (mode == "INIT") || (mode == "DORMIR")) {
        // TM K1 K2 K3 00 00 00 00 00 00 00 00
        distanceParcourue = bData[1]*10000 + bData[2]*100 + bData[3];
        DonneesTR donneesTR = DonneesTR.create(con, mode, datation,
            vitesseMoy, regimeMoy, consoMoy, vitesseMax, regimeMax, consoMax, 
            nbDefauts, defaut1, defaut2, defaut3, defaut4, 
            latitude, longitude, distanceParcourue, 
            seqNumber, snr, rssi, avgSnr, device);
    }
    else if ((mode == "DMD_GPS") || (mode == "GPS")) {
        // DMD_GPS : TM K1 K2 K3 LA LA LA LA LO LO LO LO
        boolean negLat = false;
        boolean negLg = false;

        if ((bData[7] & 0x01) != 0) {
            bData[7] &= 0xFE;
            negLat = true;
        }
        if ((bData[11] & 0x01) != 0) {
            bData[11] &= 0xFE;
            negLg = true;
        }
        distanceParcourue = bData[1]*10000 + bData[2]*100 + bData[3];
        
        double lat = (float)bData[4] + (float)bData[5]/100 
                              + (float)bData[6]/10000 + (float)bData[7]/1000000;
        if (negLat) {
            lat = -lat;
        }
        double lg = (float)bData[8] + (float)bData[9]/100 
                            + (float)bData[10]/10000 + (float)bData[11]/1000000;
        if (negLg) {
            lg = -lg;
        }
        DonneesTR donneesTR = DonneesTR.create(con, mode, datation,
            vitesseMoy, regimeMoy, consoMoy, vitesseMax, regimeMax, consoMax, 
            nbDefauts, defaut1, defaut2, defaut3, defaut4, 
            latitude, longitude, distanceParcourue, 
            seqNumber, snr, rssi, avgSnr, device);
    }
  
%>