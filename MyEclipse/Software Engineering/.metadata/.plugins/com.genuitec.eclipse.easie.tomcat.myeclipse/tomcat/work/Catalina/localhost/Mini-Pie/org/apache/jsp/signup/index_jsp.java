package org.apache.jsp.signup;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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

      out.write('\n');

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n");
      out.write("<html>\n");
      out.write("  <head>\n");
      out.write("    <base href=\"");
      out.print(basePath);
      out.write("\">\n");
      out.write("    <title>Sign Up - Mini-Pie Web Service</title>\n");
      out.write("    <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"./style.css\">\n");
      out.write("  </head>\n");
      out.write("  \n");
      out.write("  <body>\n");
      out.write("  \t<div id=\"header\" >\n");
      out.write("  \t\t<h1 id=\"title\" ><a href=\"./\">Mini-Pie Web Service</a></h1>\n");
      out.write("  \t</div>\n");
      out.write("  \t<div id=\"content\" >\n");
      out.write("\t  \t<h2>Sign Up</h2>\n");
      out.write("\t    <form name=\"signup\" id=\"signup\" action=\"./signup/signup.jsp\" method=\"post\">\n");
      out.write("\t      <table border=\"0\">\n");
      out.write("\t        <tr>\n");
      out.write("\t          <td>Username:</td>\n");
      out.write("\t          <td><input type=\"text\" name=\"username\" id=\"username\"/></td>\n");
      out.write("\t        </tr>\n");
      out.write("\t        <tr>\n");
      out.write("\t          <td>Display name:</td>\n");
      out.write("\t          <td><input type=\"text\" name=\"displayname\" id=\"displayname\"/></td>\n");
      out.write("\t        </tr>\n");
      out.write("\t        <tr>\n");
      out.write("\t          <td>Email:</td>\n");
      out.write("\t          <td><input type=\"text\" name=\"email\" id=\"email\"/></td>\n");
      out.write("\t        </tr>\n");
      out.write("\t        <tr>\n");
      out.write("\t          <td>Password:</td>\n");
      out.write("\t          <td><input type=\"password\" name=\"password\" id=\"password\"/></td>\n");
      out.write("\t        </tr> \n");
      out.write("\t        <tr>\n");
      out.write("\t          <td colspan=\"2\" align=\"center\"><input type=\"submit\" value=\"Submit\"/></td>\n");
      out.write("\t        </tr>\n");
      out.write("\t      </table>\n");
      out.write("\t      These information could not be modified after submitting.\n");
      out.write("\t    </form>\n");
      out.write("    </div>\n");
      out.write("  </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
