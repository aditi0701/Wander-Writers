package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class addComments_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

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

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("     <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>addComemnts</title>\n");
      out.write("         <link rel=\"shortcut icon\" type=\"image/png\" href=\"favicon-32x32.png\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"login_signup.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            out.println(poem_id);
            
      out.write("\n");
      out.write("            \n");
      out.write("        <div class=\"loginbox\">\n");
      out.write("            <img src=\"books.png\" class=\"books\">\n");
      out.write("            <h1>Share your views: </h1>\n");
      out.write("            <form action=\"addComments\"  method=\"post\">\n");
      out.write("                <label for=\"comment\">Comment</label><br>\n");
      out.write("                <textarea name=\"comment\" rows=\"4\" cols=\"60\"></textarea>\n");
      out.write("                <label>Rate the Content: </label>\n");
      out.write("                \n");
      out.write("            <div class=\"rating\">\n");
      out.write("                    <input class=\"star star-5\" id=\"star-5\" type=\"radio\" name=\"star\" value=\"5\"/>\n");
      out.write("                    <label class=\"star star-5\" for=\"star-5\"></label>\n");
      out.write("\n");
      out.write("                    <input class=\"star star-4\" id=\"star-4\" type=\"radio\" name=\"star\" value=\"4\"/>\n");
      out.write("                    <label class=\"star star-4\" for=\"star-4\"></label>\n");
      out.write("\n");
      out.write("                    <input class=\"star star-3\" id=\"star-3\" type=\"radio\" name=\"star\" value=\"3\"/>\n");
      out.write("                    <label class=\"star star-3\" for=\"star-3\"></label>\n");
      out.write("\n");
      out.write("                    <input class=\"star star-2\" id=\"star-2\" type=\"radio\" name=\"star\" value=\"2\"/>\n");
      out.write("                    <label class=\"star star-2\" for=\"star-2\"></label>\n");
      out.write("\n");
      out.write("                    <input class=\"star star-1\" id=\"star-1\" type=\"radio\" name=\"star\"  value=\"1\"/>\n");
      out.write("                    <label class=\"star star-1\" for=\"star-1\"></label>\n");
      out.write("            </div>    \n");
      out.write("            \n");
      out.write("            </form>\n");
      out.write("            \n");
      out.write("        </div>    \n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
