<%-- 
    Document   : CallBack
    Created on : 9 avr. 2018, 11:46:50
    Author     : snir2g2
--%>


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

    // la date et l'heure du message SIGFOX
    // the event timestamp (in seconds since the Unix Epoch)
    // le constructeur attends des millisecondes
    int seqNumber = Integer.parseInt(request.getParameter("seqNumber"));
    String device = request.getParameter("id");
    Date date = new Date(Long.parseLong(request.getParameter("time")) * 1000);
    Timestamp datation = new Timestamp(date.getTime());
    double latitude = Double.parseDouble(request.getParameter("lat"));
    double longitude = Double.parseDouble(request.getParameter("lng"));
    float snr = Float.parseFloat(request.getParameter("snr"));
    float rssi = Float.parseFloat(request.getParameter("rssi"));
    float avgSnr = Float.parseFloat(request.getParameter("avgSnr"));
    // traitement de la trame utile des données
    String data = request.getParameter("data");
    DonneesTR.saveData(con, data, datation, latitude, longitude, 
                                    device, seqNumber, snr, rssi, avgSnr);
%>