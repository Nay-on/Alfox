<%-- 
    Document   : r_accueil.jsp
    Description  : page accueil du responsable (tableau de bord du parc)
    Created on : Mars 2018
--%>

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
                    <h1>Tableau de bord</h1>
                    <br/><br/>
                    <br/><br/><br/><br/><br/>
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