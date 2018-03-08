package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.persistence.ConnexionMySQL;
import java.sql.Connection;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/includes/header.jspf");
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>Acceuil</title> \r\n");
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
      out.write("                    <h2>Suivi et maintenance d'une flotte de véhicules.</h2>\r\n");
      out.write("                    <br/><br/>\r\n");
      out.write("                    <div class=\"logo\"><img src=\"images/logo.png\"/></div>  \r\n");
      out.write("                    <br/>\r\n");
      out.write("                    <div>\r\n");
      out.write("                        ");

                            String message = request.getParameter("message");
                            if (message != null) {
                                if (message.equalsIgnoreCase("pbLogin")) {
                                    out.print("Vérifiez le mot de passe");
                                }
                            }
                        
      out.write("\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <br/><br/><br/>\r\n");
      out.write("                    <form id=\"formLogin\" method=\"post\" action=\"alfoxControl.jsp\">\r\n");
      out.write("                        <div data-role=\"fieldcontain\">\r\n");
      out.write("                            <label for=\"mdp\">Mot de passe:</label>\r\n");
      out.write("                            <input type=\"password\" name=\"mdp\" id=\"mdp\"/>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <input name=\"action\" id=\"login\" type=\"hidden\" value=\"login_req\"/><br/>\r\n");
      out.write("                        <button type=\"submit\" id=\"submitOK\" name=\"submitOK\">OK</button>\r\n");
      out.write("                    </form>\r\n");
      out.write("                    <p class=\"mini\">V0.1 - Mars 2018<br/>Développement : BTS SNIR Lycée V.Hugo Colomiers</p>\r\n");
      out.write("                </center>\r\n");
      out.write("            </div>\r\n");
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
