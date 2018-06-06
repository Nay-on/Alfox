<%-- 
    Document     : alfox.jsp 
    Description  : Contr�leur du site (mod�le MVC)
        Toute les requ�tes utilisateur passe par ce contr�leur avec en 
            param�tre l'action � r�aliser.
        Le contr�le se fait suivant l'�tat du user : responsable ou maintenance
        Il donne acc�s aux pages contenues dans le r�pertoire WEB-INF
            r�pertoire non accessible du Web pour des raisons de s�curit�.
--%>

<%@page import="java.sql.Connection"%>
<%@page import="com.persistence.*"%>
<%@page import="com.metier.*"%>

<%
    Connection con = (Connection) session.getAttribute("con");
    if (con == null) {
        con = ConnexionMySQL.newConnexion();
    }
    session.setAttribute("con", con);

    // traitement des actions possibles
    String action = request.getParameter("action");
    User user = (User) session.getAttribute("user");
    String callBackType = request.getParameter("callBackType");
    
    // --------------------------------------------------------------------
    //               Traitement des callbacks
    // --------------------------------------------------------------------
    if (callBackType != null) {
        String sigfoxID = request.getParameter("id");
        if (Boitier.getByID(con, sigfoxID) != null) {
            request.getRequestDispatcher("WEB-INF/callback.jsp").forward(request, response);
        }
        else {
            // l'ID du boitier est inconnu, c'est pas normal
            // as de callback et on ne renvoie rien !!!
            return;
        }
    }                  
    else {
        // --------------------------------------------------------------------
        if (user == null) {    // user non connect�
            if (action.equals("login_req")) {
                request.getRequestDispatcher("WEB-INF/login_req.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {    // user connect�
            if (user.getRole().equals("responsable")) {
                if (action == null) {           // rafraichissement de la page courante
                    request.getRequestDispatcher("WEB-INF/r_accueil.jsp").forward(request, response);
                }
                else if (action.equals("accueil")) {
                    request.getRequestDispatcher("WEB-INF/r_accueil.jsp").forward(request, response);
                } else if (action.equals("localisation")) {
                    request.getRequestDispatcher("WEB-INF/r_localisation.jsp").forward(request, response);
                } else if (action.equals("infos")) {
                    request.getRequestDispatcher("WEB-INF/r_infos.jsp").forward(request, response);
                } else if (action.equals("statistiques")) {
                    request.getRequestDispatcher("WEB-INF/r_statistiques.jsp").forward(request, response);
                } else if (action.equals("gestion")) {
                    request.getRequestDispatcher("WEB-INF/r_gestion.jsp").forward(request, response);
                } else if (action.equals("r_infosByImmaAndDate")) {
                    request.getRequestDispatcher("WEB-INF/ajax_infosByImmaAndDate.jsp").forward(request, response);
                } else if (action.equals("r_infosLastDateByImmatriculation")) {
                    request.getRequestDispatcher("WEB-INF/ajax_infosLastDateByImmatriculation.jsp").forward(request, response);
                } else if (action.equals("r_getCenterByZoneName")) {
                    request.getRequestDispatcher("WEB-INF/ajax_getCenterByZoneName.jsp").forward(request, response);
                } else if (action.equals("r_getVehiculesPositions")) {
                    request.getRequestDispatcher("WEB-INF/ajax_getVehiculesPositions.jsp").forward(request, response);
                } else if (action.equals("logout")) {
                    session.invalidate();   // fermeture de la session (plus de user ni de connexion)
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                else {  // rafraichissement de la page
                    request.getRequestDispatcher("WEB-INF/r_accueil.jsp").forward(request, response);
                }
            }
            else {
                if (action == null) {           // rafraichissement de la page courante
                    request.getRequestDispatcher("WEB-INF/m_accueil.jsp").forward(request, response);
                }
                else if (action.equals("accueil")) {
                    request.getRequestDispatcher("WEB-INF/m_accueil.jsp").forward(request, response);
                } else if (action.equals("localisation")) {
                    request.getRequestDispatcher("WEB-INF/r_localisation.jsp").forward(request, response);
                } else if (action.equals("infos")) {
                    request.getRequestDispatcher("WEB-INF/m_infos.jsp").forward(request, response);
                } else if (action.equals("maintenance")) {
                    request.getRequestDispatcher("WEB-INF/m_maintenance.jsp").forward(request, response);
                } else if (action.equals("logout")) {
                    session.invalidate();   // fermeture de la session (plus de user ni de connexion)
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                else {  // rafraichissement de la page
                    request.getRequestDispatcher("WEB-INF/m_accueil.jsp").forward(request, response);
                }
            }
        }
    }
%>
