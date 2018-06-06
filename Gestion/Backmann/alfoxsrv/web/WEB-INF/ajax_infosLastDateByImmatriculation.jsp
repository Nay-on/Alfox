<%-- 
    Document   : ajax_infosLastDateByImmatriculation
    Created on : 5 juin 2018, 11:00:42
    Author     : snir2g1
--%>

<%@page import="java.sql.Timestamp"%>
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
    
    // retourne par Ajax le dernier jour enregistré pour un vehicule
    String immatriculation = request.getParameter("immatriculation");
    DonneesTR dtr = DonneesTR.getLastByImmatriculation(con, immatriculation);
    String[] parts = dtr.getDatation().toString().split(" ");
    out.print("||" + parts[0] + "||");
%>
