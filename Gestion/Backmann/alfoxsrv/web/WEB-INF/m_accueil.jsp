<%-- 
    Document   : m_accueil.jsp
    Description  : page accueil de la maintenance
    Created on : Mars 2018
--%>

<%@page import="com.persistence.DonneesTR"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.persistence.ConnexionMySQL"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.persistence.Vehicule"%>
<%@page import="java.math.BigDecimal" %>
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
                 data-theme="a" data-position="fixed" data-fullscreen="true">
                <h1><img id="logoHeader" src="images/alcisLogo.png"/>Accueil</h1>
            </div>
            
            <div role="main" class="ui-content">
                <center>
                    <% 
                        ArrayList<String> immatriculations = Vehicule.getImmatriculations(con);
                        Vehicule vehicule =  Vehicule.getByImmatriculation(con, immatriculations.get(0));
                    %>
                    <br/><br/><br/>
                    <h1>Page de la maintenance</h1>
                    <div class="grid">
                        <div class="card">
                            <div class="cardTitre">Date d'arrivée au garage</div>
                            <div class="container">
                                <div id="cnv" class="cardValeur">300 minutes</div>
                                <div class="cardUnite">DF-412-EZ</div>
                            </div>
                        </div>
                        <div class="card">
                            <div class="cardTitre">Date d'arrivée au garage</div>
                            <div class="container">
                                <div id="cnv" class="cardValeur">2 jours</div>
                                <div class="cardUnite">EF-324-ES</div>
                            </div>
                        </div>
                        <div class="card">
                            <div class="cardTitre">Date d'arrivée au garage</div>
                            <div class="container">
                                <div id="cnv" class="cardValeur">23 heures</div>
                                <div class="cardUnite">JF-311-DE</div>
                            </div>
                        </div>
                    </div>
                    <br><br><br><br><br><br><br><br><br>
                    <br><br><br><br><br><br><br><br><br>
                    <form id="formLogin" method="post" action="alfoxControl.jsp">
                        <input name="action" id="logout" type="hidden" value="logout"/><br/>
                        <button type="submit" id="submitOK" name="submitOK">Déconnexion</button>
                    </form>
                    <p class="mini">V0.1 - Mars 2018<br/>Développement : BTS SNIR Lycée V.Hugo Colomiers</p>
                    <br/><br/><br/>
                </center>
            </div>
            <%@include file="/includes/footer.jspf" %>
        </div>
    </body>
</html>