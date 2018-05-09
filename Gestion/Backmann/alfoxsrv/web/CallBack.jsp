<%-- 
    Document   : CallBack
    Created on : 9 avr. 2018, 11:46:50
    Author     : snir2g2
--%>


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
    
    String data = request.getParameter("data");
    Timestamp datation = Utils.stringToTimestamp(request.getParameter("time"));
    String device = request.getParameter("id");
    int seqNumber = Integer.parseInt(request.getParameter("seqNumber"));
    double latitude = Double.parseDouble(request.getParameter("lat"));
    double longitude = Double.parseDouble(request.getParameter("lng"));
    float snr = Float.parseFloat(request.getParameter("snr"));
    float rssi= Float.parseFloat(request.getParameter("rssi"));
    float avgSnr = Float.parseFloat(request.getParameter("avgSnr"));
    
    DonneesTR.create(con, data, datation, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.0, 0.0, 0, 0);
%>