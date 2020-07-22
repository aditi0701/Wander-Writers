package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class addPost_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"shortcut icon\" type=\"image/png\" href=\"favicon-32x32.png\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"addPost.css\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        <form>\n");
      out.write("            \n");
      out.write("            <label for=\"title\">Title</label><br>\n");
      out.write("            <input type=\"text\" id=\"title\" name=\"title\" size=\"7\" ><br><br>\n");
      out.write("            \n");
      out.write("            <label for=\"content\">Summary</label><br>\n");
      out.write("            <input type=\"text\" id=\"summary\" name=\"summary\"><br><br>\n");
      out.write("            <textarea name=\"summary\" rows=\"10\" cols=\"50\"></textarea>\n");
      out.write("            \n");
      out.write("           <p><label for=\"category\">Category</label><br>\n");
      out.write("            <input list=\"category\" name=\"category\" placeholder=\"select\" required>\n");
      out.write("            <datalist id=\"category\">\n");
      out.write("            <option value=\"Fashion\">\n");
      out.write("            <option value=\"Food\">\n");
      out.write("            <option value=\"Travel\">\n");
      out.write("            <option value=\"Music\">\n");
      out.write("            <option value=\"Lifestyle\">\n");
      out.write("            <option value=\"Fitness\"> \n");
      out.write("            <option value=\"Sports\">\n");
      out.write("            <option value=\"Finance\">\n");
      out.write("            <option value=\"Political\">\n");
      out.write("            <option value=\"Parenting\">\n");
      out.write("            <option value=\"Business\">\n");
      out.write("            <option value=\"Personal\">\n");
      out.write("            <option value=\"Educational\">  \n");
      out.write("            <option value=\"Movie\">  \n");
      out.write("            <option value=\"Gaming\">    \n");
      out.write("            <option value=\"Others\">    \n");
      out.write("            </datalist>\n");
      out.write("            </p>\n");
      out.write("            \n");
      out.write("            \n");
      out.write("            <label for=\"content\">Content</label><br>\n");
      out.write("            <input type=\"text\" id=\"content\" name=\"content\" col=\"20\" row=\"50\"><br><br>\n");
      out.write("              <textarea name=\"content\" rows=\"10\" cols=\"50\"></textarea>\n");
      out.write("            <input type=\"submit\" value=\"Post\">\n");
      out.write("            \n");
      out.write("            \n");
      out.write("            \n");
      out.write("        </form>\n");
      out.write("            \n");
      out.write("       \n");
      out.write("        </div>\n");
      out.write("         </div>        \n");
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
