<%-- 
    Document   : infos.jsp
    Description  : page accueil du responsable
    Created on : Mars 2018
--%>

<%@page import="com.persistence.Utils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.persistence.Vehicule"%>
<%@page import="com.persistence.DonneesHisto"%>
<%@page import="com.persistence.ConnexionMySQL"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Accueil</title> 
        <%@ include file="/includes/header.jspf" %>
    </head>
    <body>
        <% 
            Connection con = (Connection) session.getAttribute("con");
            if (con == null)
                con = ConnexionMySQL.newConnexion();
            session.setAttribute("con", con);
        %>
        <div data-role="page" id="page1">
            <div class="header" data-role="header" data-id="main-header" data-tap-toggle="false" 
                 data-position="fixed">
                <h1><img id="logoHeader" src="images/alcisLogo.png"/>Infos</h1>
                <p class="mode" >
                <% 
                    ArrayList<String> immatriculations = Vehicule.getImmatriculations(con);
                    Vehicule vehicule =  Vehicule.getByImmatriculation(con, immatriculations.get(0));
                    DonneesHisto dhisto = DonneesHisto.getLastByImmatriculation(con, vehicule.getImmatriculation());
                    ArrayList<Vehicule> lstVehicule = new ArrayList<>();
                    ArrayList<DonneesHisto> lstDhisto = new ArrayList<>();
                    for (int i=0;i<immatriculations.size();i++) {
                        lstVehicule.add(Vehicule.getByImmatriculation(con, immatriculations.get(i)));
                        lstDhisto.add(DonneesHisto.getLastByImmatriculation(con, lstVehicule.get(i).getImmatriculation()));
                    }
                %>
                </p>
                <a href="#panelVehicules" 
                   class="ui-btn ui-btn-icon-notext ui-corner-all ui-icon-gear ui-btn-right">
                </a>
            </div>
            
            <div role="main" class="ui-content">
                <center>
                    <p class="mess"></p>
                    <form class="form" >
                        <div class="ui-field-contain">
                            <label class="label" for="select-native-1">Véhicule :</label>
                            <select name="select-native-1" id="select-native-1">
                        <% 
                            // recup l'immatriculation des véhicules
                            int nb = Vehicule.size(con);
                            for (int i = 0; i< nb; i++) {
                                out.print("<option value=" + (i+1) + ">" + immatriculations.get(i) + "</option>");
                            }
                        %>
                             
                            </select>
                        </div>
                        <input value="2018-03-20" id="date" type="date">
                    </form>
                    <table data-role="table" id="movie-table-custom" data-mode="reflow" class="table-stripe movie-list ui-responsive">
                    <thead>
                        <tr>
                          <th data-priority="1">Numéro</th>
                          <th data-priority="2">Date</th>
                          <th data-priority="2">Kilométrage</th>
                          <th data-priority="3">Vitesse Moyenne</th>
                          <th data-priority="3">Consommation Moyenne</th>
                          <th data-priority="4">Latitude</th>
                          <th data-priority="4">Longitude</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            ArrayList<DonneesHisto> donnees = DonneesHisto.getByDate(con, vehicule.getImmatriculation(), "2018-01-03");
                            // recup la liste des données tr pour ce véhicule et cette date
                            for (int i = 0; i<donnees.size(); i++) {
                                out.print("<tr><td>" + i + "</td>");
                                out.print("<td>" + donnees.get(i).getDatation() + "</td>");
                                out.print("<td>" + donnees.get(i).getDistanceParcourue() + " km" + "</td>");
                                out.print("<td>" + donnees.get(i).getVitesse() + " km/h" +"</td>");
                                out.print("<td>" + donnees.get(i).getConsommation() + " l/100" + "</td>");
                                out.print("<td>" + donnees.get(i).getLatitudeGPS()+ "</td>");
                                out.print("<td>" + donnees.get(i).getLongitudeGPS() + "</td> </tr>");
                            }
                        %>
                    </tbody>
                    </table>
                    <br/><br/><br/><br/><br/>
                </center>
            </div>
                        <div id="panelVehicules" data-role="panel" data-position="right"  
                 data-position-fixed="true" data-display="push">
                <p>Changement de mode :</p>
                <div data-role="collapsibleset" data-inset="false">
                    <a href="#positionWindow" class="ui-btn ui-btn-a ui-icon-arrow-r ui-btn-icon-left ui-shadow ui-corner-all" data-rel="popup" data-position-to="window">
                        <h4>DORMIR</h4>
                        <div data-role="popup" id="positionWindow" class="ui-content">
                            <p align="center">Etes-vous sûr de vouloir passer en mode DORMIR ?</p>
                                <button class="ok" style="width: 200px">OK</button>
                                <button class="annuler" style="width: 200px" onclick="window.location.href='alfoxControl.jsp?action=infos'">Annuler</button>
                        </div>
                    </a>
                    <a href="#positionWindow1" class="ui-btn ui-btn-a ui-icon-arrow-r ui-btn-icon-left ui-shadow ui-corner-all" data-rel="popup" data-position-to="window">
                        <h4>GPS</h4>
                        <div data-role="popup" id="positionWindow1" class="ui-content">
                            <p align="center">Etes-vous sûr de vouloir passer en mode GPS ?</p>
                            <button class="ok" style="width: 200px">OK</button>
                            <button class="annuler" style="width: 200px" onclick="window.location.href='alfoxControl.jsp?action=infos'">Annuler</button>
                        </div>
                    </a>
                    <a href="#positionWindow2" class="ui-btn ui-btn-a ui-icon-arrow-r ui-btn-icon-left ui-shadow ui-corner-all" data-rel="popup" data-position-to="window">
                        <h4>Demande GPS</h4>
                        <div data-role="popup" id="positionWindow2" class="ui-content">
                            <p align="center">Etes-vous sûr de vouloir passer en mode Demande GPS ?</p>
                            <button class="ok" style="width: 200px">OK</button>
                            <button class="annuler" style="width: 200px" onclick="window.location.href='alfoxControl.jsp?action=infos'">Annuler</button>
                        </div>
                    </a>
                    <a href="#positionWindow3" class="ui-btn ui-btn-a ui-icon-arrow-r ui-btn-icon-left ui-shadow ui-corner-all" data-rel="popup" data-position-to="window">
                        <h4>RESET</h4>
                        <div data-role="popup" id="positionWindow3" class="ui-content">
                            <p align="center">Etes-vous sûr de vouloir passer en mode RESET ?</p>
                            <button class="ok" style="width: 200px">OK</button>
                            <button class="annuler" style="width: 200px" onclick="window.location.href='alfoxControl.jsp?action=infos'">Annuler</button>
                        </div>
                    </a>
                </div>
            </div>
            <%@include file="/includes/footer.jspf"%>
        </div>
    </body>
</html>