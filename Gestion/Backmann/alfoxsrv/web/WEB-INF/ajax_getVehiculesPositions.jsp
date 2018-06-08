<%-- 
    Document   : ajax_getVehiculesPositions
    Created on : 6 Juin 2018, 11:00:42
    Author     : snir2g1
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.persistence.ConnexionMySQL"%>
<%@page import="com.persistence.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% // initialisation de la connexion à la BDD
    Connection con = (Connection) session.getAttribute("con");
    if (con == null) {
        con = ConnexionMySQL.newConnexion();
    }
    session.setAttribute("con", con);

    
    String immatriculation = request.getParameter("immatriculation");
    
    // retourne par Ajax les infos du véhicule concerné
    if (immatriculation == null) {
        ArrayList<String> immatriculations = Vehicule.getImmatriculations(con);
        ArrayList<Vehicule> lstVehicule = new ArrayList<>();
        ArrayList<DonneesTR> lstDtr = new ArrayList<>();
        // retourne par Ajax les infos du véhicule concerné
        for (int i = 0; i < immatriculations.size(); i++) {
            lstVehicule.add(Vehicule.getByImmatriculation(con, immatriculations.get(i)));
            lstDtr.add(DonneesTR.getLastByImmatriculation(con, lstVehicule.get(i).getImmatriculation()));
        }
        for (int i = 0; i < lstVehicule.size(); i++) {
            out.print("||" + lstVehicule.get(i).isDehors(con) + "||" + lstVehicule.get(i).getAProbleme() + "||" + lstDtr.get(i).getMode() + "||" + lstVehicule.get(i).getImmatriculation() + "||" + lstDtr.get(i).getLatitude() + "||" + lstDtr.get(i).getLongitude());
        }
        out.print("||");
    }
    else {
        Vehicule vehicule = Vehicule.getByImmatriculation(con, immatriculation);
        DonneesTR donneesTR = DonneesTR.getLastByImmatriculation(con, vehicule.getImmatriculation());
        
        out.print("||" + donneesTR.getLatitude() + "||" + donneesTR.getLongitude() + "||");
    }
%>
