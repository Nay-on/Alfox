<%-- 
    Document   : ajax_getZones
    Created on : 6 Juin 2018, 11:00:42
    Author     : snir2g1
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.persistence.ConnexionMySQL"%>
<%@page import="com.persistence.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% // retourne la liste des nom des zones avec leurs coordonnées
    Connection con = (Connection) session.getAttribute("con");
    if (con == null)
        con = ConnexionMySQL.newConnexion();
    session.setAttribute("con", con);
    for (int i = 0; i < ZoneLimite.getLstZone(con).size(); i++) {
        String nom = ZoneLimite.getLstZone(con).get(i).getNom();
        out.print("||" + nom);
        ArrayList<Double> zonesLimites = ZoneLimite.getZoneLimites(con, nom);
        for (Double coord : zonesLimites) {
            out.print("||" + coord);
        }
        out.print("##");
    }
%>
