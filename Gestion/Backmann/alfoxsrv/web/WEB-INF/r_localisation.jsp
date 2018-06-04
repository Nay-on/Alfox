<%-- 
    Document   : localisation.jsp
    Description  : page localisation du parc pour le responsable
    Created on : Mars 2018
--%>

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
    </head>
    <body>
        <% 
            Connection con = (Connection) session.getAttribute("con");
            if (con == null)
                con = ConnexionMySQL.newConnexion();
            session.setAttribute("con", con);
            ArrayList<String> immatriculations = Vehicule.getImmatriculations(con);
            Vehicule vehicule =  Vehicule.getByImmatriculation(con, immatriculations.get(0));
            DonneesTR dtr = DonneesTR.getLastByImmatriculation(con, vehicule.getImmatriculation());
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
                <div id="map"></div>
                <script>
                    function initMap() {
                        var livh = {lat:  43.615796, lng: 1.30944};
                        var map = new google.maps.Map(document.getElementById('map'), {
                            zoom: 16,
                            center: livh
                        });
                        var marker = new google.maps.Marker({
                            position: livh,
                            map: map
                        });
                    }
                </script>
                <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAteLjItiBvWdZJNOm97mU-jWaqtJ857Fc&callback=initMap">
                </script>
                <br/><br/><br/>
            </div>        
            <%@include file="/includes/footer.jspf" %>
          
        <!-- panel de zones -->
        <div id="panelZones" data-role="panel" data-position="left"  
            data-position-fixed="true" data-display="push">
            <ol data-role="listview" data-icon="false">
                <li data-role="list-divider">Zones limites :</li>
                <li><a href="#">Toulouse</a></li>
                <li><a href="#">Sud Ouest</a></li>
                <li><a href="#">Paris</a></li>
                <li><a href="#">France</a></li>
                <li><a href="#">Europe</a></li>
            </ol>
        </div>
        
        <!-- panel de véhicules -->
        <div id="panelVehicules" data-role="panel" data-position="right"  
                 data-position-fixed="true" data-display="push">
            <div data-role="collapsibleset" data-inset="false">
                <div data-role="collapsible">
                    <h3>1 : ED-592-CY</h3>
                    <ul data-role="listview" data-icon="false">
                        <li data-role="list-divider">Samedi 14 Février 2018</li>
                        <li data-icon="false"><a href="#">Centrer</a></li>
                        <li data-icon="false">Compteur : <%= vehicule.getCompteurReel() + " km" %></li>
                        <li data-icon="false">ConsoMoy : <%= dtr.getConsommation() + " l"%></li>
                        <li data-icon="false">VitMoy : <%= dtr.getVitesse() +" km/h"%></li>
                        <li data-icon="false">RégimeMoy : <%= dtr.getRegime() + " tpm" %></li>
                    </ul>
                </div>
                <div data-role="collapsible">
                    <h3>2 : ED-593-VS</h3>
                    <ul data-role="listview">
                        <li data-role="list-divider">Jeudi 12 Février 2018</li>
                        <li data-icon="false"><a href="#">Centrer</a></li>
                        <li data-icon="false">Compteur : <%= vehicule.getCompteurReel() + " km" %></li>
                        <li data-icon="false">ConsoMoy : <%= dtr.getConsommation() + " l"%></li>
                        <li data-icon="false">VitMoy : <%= dtr.getVitesse() +" km/h"%></li>
                        <li data-icon="false">RégimeMoy : <%= dtr.getRegime() + " tpm" %></li>
                    </ul>
                </div>
                <div data-role="collapsible">
                    <h3>3 : EE-239-QM</h3>
                    <ul data-role="listview">
                        <li data-role="list-divider">Jeudi 12 Février 2018</li>
                        <li data-icon="false"><a href="#">Centrer</a></li>
                        <li data-icon="false">Compteur : <%= vehicule.getCompteurReel() + " km" %></li>
                        <li data-icon="false">ConsoMoy : <%= dtr.getConsommation() + " l"%></li>
                        <li data-icon="false">VitMoy : <%= dtr.getVitesse() +" km/h"%></li>
                        <li data-icon="false">RégimeMoy : <%= dtr.getRegime() + " tpm" %></li>
                    </ul>
                </div>
                <div data-role="collapsible">
                    <h3>4 : EE-300-QM</h3>
                    <ul data-role="listview">
                        <li data-role="list-divider">Jeudi 12 Février 2018</li>
                        <li data-icon="false"><a href="#">Centrer</a></li>
                        <li data-icon="false">Compteur : <%= vehicule.getCompteurReel() + " km" %></li>
                        <li data-icon="false">ConsoMoy : <%= dtr.getConsommation() + " l"%></li>
                        <li data-icon="false">VitMoy : <%= dtr.getVitesse() +" km/h"%></li>
                        <li data-icon="false">RégimeMoy : <%= dtr.getRegime() + " tpm" %></li>
                    </ul>
                </div>
                <div data-role="collapsible">
                    <h3>5 : EK-462-GX</h3>
                    <ul data-role="listview">
                        <li data-role="list-divider">Jeudi 12 Février 2018</li>
                        <li data-icon="false"><a href="#">Centrer</a></li>
                        <li data-icon="false">Compteur : <%= vehicule.getCompteurReel() + " km" %></li>
                        <li data-icon="false">ConsoMoy : <%= dtr.getConsommation() + " l"%></li>
                        <li data-icon="false">VitMoy : <%= dtr.getVitesse() +" km/h"%></li>
                        <li data-icon="false">RégimeMoy : <%= dtr.getRegime() + " tpm" %></li>
                    </ul>
                </div>
                <div data-role="collapsible">
                    <h3>6 : EM-045-BC</h3>
                    <ul data-role="listview">
                        <li data-role="list-divider">Jeudi 12 Février 2018</li>
                        <li data-icon="false"><a href="#">Centrer</a></li>
                        <li data-icon="false">Compteur : <%= vehicule.getCompteurReel() + " km" %></li>
                        <li data-icon="false">ConsoMoy : <%= dtr.getConsommation() + " l"%></li>
                        <li data-icon="false">VitMoy : <%= dtr.getVitesse() +" km/h"%></li>
                        <li data-icon="false">RégimeMoy : <%= dtr.getRegime() + " tpm" %></li>
                    </ul>
                </div>
                <div data-role="collapsible">
                    <h3>7 : EM-150-BE</h3>
                    <ul data-role="listview">
                        <li data-role="list-divider">Jeudi 12 Février 2018</li>
                        <li data-icon="false"><a href="#">Centrer</a></li>
                        <li data-icon="false">Compteur : <%= vehicule.getCompteurReel() + " km" %></li>
                        <li data-icon="false">ConsoMoy : <%= dtr.getConsommation() + " l"%></li>
                        <li data-icon="false">VitMoy : <%= dtr.getVitesse() +" km/h"%></li>
                        <li data-icon="false">RégimeMoy : <%= dtr.getRegime() + " tpm" %></li>
                    </ul>
                </div>
                <div data-role="collapsible">
                    <h3>8 : EM-862-ML</h3>
                    <ul data-role="listview">
                        <li data-role="list-divider">Jeudi 12 Février 2018</li>
                        <li data-icon="false"><a href="#">Centrer</a></li>
                        <li data-icon="false">Compteur : <%= vehicule.getCompteurReel() + " km" %></li>
                        <li data-icon="false">ConsoMoy : <%= dtr.getConsommation() + " l"%></li>
                        <li data-icon="false">VitMoy : <%= dtr.getVitesse() +" km/h"%></li>
                        <li data-icon="false">RégimeMoy : <%= dtr.getRegime() + " tpm" %></li>
                    </ul>
                </div>
            </div>
        </div>
        </div>
    </body>
</html>