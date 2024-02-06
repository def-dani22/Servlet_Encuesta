package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public final class mostrar_005fcontrato_005festudiantes_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=UTF-8");
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
      out.write("<html lang=\"es\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <title>Contrato Estudiantil</title>\n");
      out.write("    <!-- Agrega aquí cualquier otro recurso necesario (CSS, JS) -->\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("    <h1>Contrato Estudiantil</h1>\n");
      out.write("\n");
      out.write("    ");
      out.write("\n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("\n");
      out.write("    ");

        try {
            String idPoliza = request.getParameter("idPoliza");
            String tipoPoliza = request.getParameter("tipoPoliza");
            String cedula = request.getParameter("cedula");

            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/SegurosCSC");
            Connection conn = ds.getConnection();

            // Lógica para procesar la contratación del seguro
            // Puedes insertar en la base de datos, generar contratos, etc.

            // Por ejemplo, puedes insertar en la tabla polizaXestudiantes
            PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO polizaXestudiantes (cedula, idPoliza) VALUES (?, ?)");
            insertStmt.setString(1, cedula);
            insertStmt.setString(2, idPoliza);
            insertStmt.executeUpdate();
            insertStmt.close();

            // Obtener información del beneficiario (puedes personalizar esto según tus necesidades)
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Estudiantes WHERE cedula = " + cedula);
            rs.next();
            String nombreBeneficiario = rs.getString("nombre") + " " + rs.getString("apellido");
            rs.close();
            stmt.close();
    
      out.write("\n");
      out.write("\n");
      out.write("            <p>Contrato generado con éxito para ");
      out.print( tipoPoliza );
      out.write(" a nombre de ");
      out.print( nombreBeneficiario );
      out.write(".</p>\n");
      out.write("            <p><a href=\"paginaPrincipal.html\">Volver a la página principal</a></p>\n");
      out.write("\n");
      out.write("    ");

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
    
      out.write("\n");
      out.write("            <p>Error al procesar la contratación del seguro.</p>\n");
      out.write("            <p><a href=\"paginaPrincipal.html\">Volver a la página principal</a></p>\n");
      out.write("    ");

        }
    
      out.write("\n");
      out.write("\n");
      out.write("</body>\n");
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
