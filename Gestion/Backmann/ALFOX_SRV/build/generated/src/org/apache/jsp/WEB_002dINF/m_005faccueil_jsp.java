package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.persistence.User;

public final class m_005faccueil_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/includes/header.jspf");
    _jspx_dependants.add("/includes/footer.jspf");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>Accueil</title> \r\n");
      out.write("        ");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, minimum-scale=1, maximum-scale=1\">\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"js/jquery.mobile/jquery.mobile-1.4.5.min.css\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"css/themes/jquery.mobile.icons.min.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"css/alfoxTheme.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"css/alfox.css\">\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery/jquery-1.11.2.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery.mobile/jquery.mobile-1.4.5.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <div data-role=\"page\" id=\"page1\">\r\n");
      out.write("            <div class=\"header\" data-role=\"header\" data-id=\"main-header\" data-tap-toggle=\"false\" \r\n");
      out.write("                 data-theme=\"a\" data-position=\"fixed\" data-fullscreen=\"true\">\r\n");
      out.write("                <h1><img id=\"logoHeader\" src=\"images/alcisLogo.png\"/>Accueil</h1>\r\n");
      out.write("            </div>\r\n");
      out.write("            \r\n");
      out.write("            <div role=\"main\" class=\"ui-content\">\r\n");
      out.write("                <center>\r\n");
      out.write("                    <br/><br/><br/>\r\n");
      out.write("                    <h1>Page de la maintenance</h1>\r\n");
      out.write("                    <br/><br/>\r\n");
      out.write("                    <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>\r\n");
      out.write("                    <form id=\"formLogin\" method=\"post\" action=\"alfoxControl.jsp\">\r\n");
      out.write("                        <input name=\"action\" id=\"logout\" type=\"hidden\" value=\"logout\"/><br/>\r\n");
      out.write("                        <button type=\"submit\" id=\"submitOK\" name=\"submitOK\">Déconnexion</button>\r\n");
      out.write("                    </form>\r\n");
      out.write("                    <p class=\"mini\">V0.1 - Mars 2018<br/>Développement : BTS SNIR Lycée V.Hugo Colomiers</p>\r\n");
      out.write("                    <br/><br/><br/>\r\n");
      out.write("                </center>\r\n");
      out.write("            </div>\r\n");
      out.write("            ");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"footer\" data-role=\"footer\" data-theme=\"a\" data-tap-toggle=\"false\"\r\n");
      out.write("            data-id=\"main-footer\" data-position=\"fixed\" data-fullscreen=\"true\">\r\n");
      out.write("    ");
 
        User user1 = (User) session.getAttribute("user");
        // y a t'il eu connexion ?
        if (user1 != null) {
            if (user1.getRole().equals("responsable")) {
                out.println("<div data-role='navbar' data-grid='d'>");
                out.println("<ul>");
                out.println("<li><a href='alfoxControl.jsp?action=accueil' data-ajax='false' id='navbarhome' data-icon='home'>Accueil</a></li>");
                out.println("<li><a href='alfoxControl.jsp?action=localisation' data-ajax='false' id='navbarSuivi' data-icon='location'>Localisation</a></li>");
                out.println("<li><a href='alfoxControl.jsp?action=infos' data-ajax='false' id='navbarInfos' data-icon='info'>Infos</a></li>");
                out.println("<li><a href='alfoxControl.jsp?action=statistiques' data-ajax='false' id='navbarStatistiques' data-icon='bars'>Statistiques</a></li>");
                out.println("<li><a href='alfoxControl.jsp?action=gestion' data-ajax='false' id='navbarGestion' data-icon='edit'>Gestion</a></li>");
                out.println("</ul>");
                out.println("</div>");
            }
            else {        // 4 menus
                out.println("<div data-role='navbar' data-grid='c'>"); 
                out.println("<ul>");
                out.println("<li><a href='alfoxControl.jsp?action=accueil' data-ajax='false' id='navbarhome' data-icon='home'>Accueil</a></li>");
                out.println("<li><a href='alfoxControl.jsp?action=localisation' data-ajax='false' id='navbarSuivi' data-icon='location'>Localisation</a></li>");
                out.println("<li><a href='alfoxControl.jsp?action=infos' data-ajax='false' id='navbarInfos' data-icon='info'>Infos</a></li>");
                out.println("<li><a href='alfoxControl.jsp?action=maintenance' data-ajax='false' id='navbarMaintenance' data-icon='gear'>Maintenance</a></li>");
                out.println("</ul>");
                out.println("</div>");
            }
        }
    
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
