<%-- 
    Document   : ajax_infosByImmaAndDate
    Created on : 14 mai 2018, 11:00:42
    Author     : snir2g1
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.persistence.ConnexionMySQL"%>
<%@page import="com.persistence.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% // initialisation de la connexion à la BDD
    Connection con = (Connection) session.getAttribute("con");
    if (con == null)
        con = ConnexionMySQL.newConnexion();
    session.setAttribute("con", con);
    
    // retourne par Ajax les infos du véhicule concerné
    String immatriculation = request.getParameter("immatriculation");
    String date = request.getParameter("date");
    String idSigfox = Vehicule.getByImmatriculation(con, immatriculation).getImmatriculation();
    ArrayList<DonneesTR> donnees = DonneesTR.getByDate(con, idSigfox, date);
    
    for (int i = 0; i < donnees.size(); i++) {
        DonneesTR dtr = donnees.get(i);
        out.print("<tr><td>" + i + "</td>");
        out.print("<td>" + dtr.getDatation() + "</td>");
        out.print("<td>" + dtr.getDistanceParcourue() + " km" + "</td>");
        out.print("<td>" + dtr.getVitesse() + " km/h" +"</td>");
        out.print("<td>" + dtr.getConsommation() + " l/100" + "</td>");
        out.print("<td>" + dtr.getLatitude() + "</td>");
        out.print("<td>" + dtr.getLongitude() + "</td> </tr>");
    }
    String strMode = donnees.get(0).getMode();
    out.print("||" + strMode);
%>
