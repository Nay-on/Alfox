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
        out.print("<tr><td>" + i + "</td>");
        out.print("<td>" + donnees.get(i).getDatation() + "</td>");
        out.print("<td>" + donnees.get(i).getDistanceParcourue() + " km" + "</td>");
        out.print("<td>" + donnees.get(i).getVitesse() + " km/h" +"</td>");
        out.print("<td>" + donnees.get(i).getConsommation() + " l/100" + "</td>");
        out.print("<td>" + donnees.get(i).getLatitude() + "</td>");
        out.print("<td>" + donnees.get(i).getLongitude() + "</td> </tr>");
    }
%>
