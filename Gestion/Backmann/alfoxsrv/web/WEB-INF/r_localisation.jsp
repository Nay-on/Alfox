<%-- 
    Document   : localisation.jsp
    Description  : page localisation du parc pour le responsable
    Created on : Mars 2018
--%>

<%@page import="com.persistence.ZoneLimite"%>
<%@page import="com.persistence.DonneesTR"%>
<%@page import="com.persistence.Vehicule"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.persistence.ConnexionMySQL"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Accueil</title> 
        <%@ include file="/includes/header.jspf" %>
        <script type="text/javascript" src="js/map.js"></script>
        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAteLjItiBvWdZJNOm97mU-jWaqtJ857Fc&callback=initialize"> </script>
    </head>
    <body>
        <% 
            Connection con = (Connection) session.getAttribute("con");
            if (con == null)
                con = ConnexionMySQL.newConnexion();
            session.setAttribute("con", con);
            ArrayList<String> immatriculations = Vehicule.getImmatriculations(con);
            ArrayList<Vehicule> lstVehicule = new ArrayList<>();
            ArrayList<DonneesTR> lstDtr = new ArrayList<>();
            for (int i=0;i<immatriculations.size();i++) {
                lstVehicule.add(Vehicule.getByImmatriculation(con, immatriculations.get(i)));
                lstDtr.add(DonneesTR.getLastByImmatriculation(con, lstVehicule.get(i).getImmatriculation()));
            }
        %>
        <div data-role="page" id="page1">
            <div class="header" data-role="header" data-id="main-header" data-tap-toggle="false" 
                 data-theme="a" data-position="fixed" data-fullscreen="true">
                <h1><img id="logoHeader" src="images/alcisLogo.png"/>Localisation</h1>
                <a href="#panelZones" 
                   class="ui-btn ui-btn-icon-notext ui-corner-all ui-icon-location ui-btn-left">
                </a>
                <a href="#panelVehicules" 
                   class="ui-btn ui-btn-icon-notext ui-corner-all ui-icon-bars ui-btn-right">
                </a>
            </div>
            
            <div role="main" class="ui-content">
                <br/><br/><br/>
                <div id="test"></div>
                <!-- map google -->
                <div id="map"></div>
                <br/><br/><br/>
            </div>        
            <%@include file="/includes/footer.jspf" %>
          
        <!-- panel de zones -->
        <div id="panelZones" data-role="panel" data-position="left"  
            data-position-fixed="true" data-display="push">
            <ol id="listeZones" data-role="listview" data-icon="false">
                <li data-role="list-divider">Zones limites :</li>
                <%
                    for (int i=0;i<ZoneLimite.getLstZone(con).size();i++) {
                        out.print("<li id=" + ZoneLimite.getLstZone(con).get(i).getNom() 
                                + "><a href='#'>" +  ZoneLimite.getLstZone(con).get(i).getNom()
                                + "</a></li>");
                    }
                %>    
            </ol>
        </div>
        
        <!-- panel de véhicules -->
        <div id="panelVehicules" data-role="panel" data-position="right"  
                 data-position-fixed="true" data-display="push">
            <div data-role="collapsibleset" data-inset="false">
            <%
                for (int i=0;i<immatriculations.size();i++){
                    out.print("<div data-role='collapsibleset' data-inset='false'>");
                    out.print("<div data-role='collapsible'>");
                    out.print("<h3>" + (i+1) + " : " + lstVehicule.get(i).getImmatriculation() + "</h3>");
                    out.print("<ul data-role='listview' data-icon='false'>");
                    out.print("<li id=" +  lstVehicule.get(i).getImmatriculation() + " data-icon='false'><a href='#'>Centrer</a></li>");
                    out.print("<li data-icon='false'>Compteur : " + lstVehicule.get(i).getCompteurReel() + " km</li>");
                    out.print("<li data-icon='false'>Conso Moyenne : " + lstDtr.get(i).getConsommation() + " l/100km</li>");
                    out.print("<li data-icon='false'>Vitesse Moyenne : " + lstDtr.get(i).getVitesse() + " km/h</li>");
                    out.print("<li data-icon='false'>Régime Moyen : " + lstDtr.get(i).getRegime() + " tpm</li>");
                    out.print("</div>");
                }
            %>    
        </div>
        </div>
    </body>
</html>