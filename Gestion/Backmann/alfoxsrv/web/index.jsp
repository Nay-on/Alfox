<%-- 
    Document   : index.jsp
    Description  : page accueil anonyme
    Created on : Mars 2018

    Rmq : il reste un bug à la 1er connexion qui doit se faire deux fois
--%>

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
        <div data-role="page" id="page1">
            <div class="header" data-role="header" data-id="main-header" data-tap-toggle="false" 
                 data-theme="a" data-position="fixed" data-fullscreen="true">
                <h1><img id="logoHeader" src="images/alcisLogo.png"/>Accueil</h1>
            </div>
            
            <div role="main" class="ui-content">
                <center>

                    <br/><br/><br/>
                    <h2>Suivi et maintenance d'une flotte de véhicules V0.52</h2>
                    <br/><br/>
                    <div class="logo"><img src="images/logo.png"/></div>  
                    <br/>
                    <div>
                        <%
                            String message = request.getParameter("message");
                            if (message != null) {
                                if (message.equalsIgnoreCase("pbLogin")) {
                                    out.print("Vérifiez le mot de passe");
                                }
                            }
                        %>
                    </div>
                    <br/><br/><br/>
                    <form id="formLogin" class="form" method="post" action="alfoxControl.jsp">
                        <div data-role="fieldcontain">
                            <label for="mdp">Mot de passe:</label>
                            <input type="password" name="mdp" id="mdp"/>
                        </div>
                        <input name="action" id="login" type="hidden" value="login_req"/><br/>
                        <button type="submit" id="submitOK" name="submitOK">OK</button>
                    </form>
                    <p class="mini">V0.52 - Mars 2018<br/>Développement : BTS SNIR Lycée V.Hugo Colomiers</p>
                </center>
            </div>
        </div>
    </body>
</html>