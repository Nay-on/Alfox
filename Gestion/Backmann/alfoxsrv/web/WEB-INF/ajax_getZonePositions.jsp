<%-- 
    Document   : ajax_getCenterByZoneName
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
    String zoneName = request.getParameter("zoneName");
    
    ArrayList<Double> lstLat = ZoneLimite.getLatByZone(con, zoneName);
    ArrayList<Double> lstLg = ZoneLimite.getLgByZone(con, zoneName);
    for (int i = 0 ; i < lstLat.size() ; i++) {
        out.print("||" + lstLat.get(i) + "||" + lstLg.get(i));
    }
    out.print("||");
%>
