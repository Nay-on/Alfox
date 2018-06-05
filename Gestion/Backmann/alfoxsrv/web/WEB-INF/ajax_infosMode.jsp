<%-- 
    Document   : ajax_infosByImmaAndDate
    Created on : 5 juin 2018, 11:00:42
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
    String mode = request.getParameter("mode");
    ArrayList<String> immatriculations = Vehicule.getImmatriculations(con);
    Vehicule vehicule =  Vehicule.getByImmatriculation(con, immatriculations.get(0));
    DonneesTR dtr = DonneesTR.getLastByImmatriculation(con, vehicule.getImmatriculation());
    for (int i = 0; i < Vehicule.size(con); i++) {
        out.print("Mode : " + dtr.getMode());
    }
%>
