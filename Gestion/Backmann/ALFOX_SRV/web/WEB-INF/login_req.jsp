<%-- 
    Document    : login_req
    Description : recoit par Ajax le mot de passe pour identifier et se connecter
--%>

<%@page import="metier.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="persistence.*"%>

<%
    Connection con = (Connection) session.getAttribute("con");
    if (con == null) {
        con = ConnexionMySQL.newConnexion();
        session.setAttribute("con", con);
    }
    // on récupère le mdp saisi
    String password = request.getParameter("mdp");
    String pwd2 = persistence.Utils.encryptPassword(password);
    User user = User.getByMotDePasse(con, pwd2);
    // la personne existe t'elle ?
    if (user != null) {                 
        // création du user dans l'objet session de Tomcat
        session.setAttribute("user", user);
        request.getRequestDispatcher("../alfoxControl.jsp?action=accueil").forward(request, response);
    }
    else {      // le mot de passe n'existe pas
        request.getRequestDispatcher("../index.jsp?message=pbLogin").forward(request, response);
    }
%>
 