package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public final class SegurosViaje_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


        // Función para obtener los tipos de seguros de viaje desde la base de datos
        ResultSet obtenerTiposSeguroViaje() throws SQLException {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                // Establecer conexión con la base de datos
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");
                DataSource ds = (DataSource) envCtx.lookup("jdbc/SegurosCSCDB");
                con = ds.getConnection();

                // Obtener tipos de seguros de viaje
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM tipoPolizaViajes");

                return rs;
            } finally {
                // Cerrar recursos
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            }
        }
    
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
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>Formulario de Seguro de Viaje</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <h2>Formulario de Seguro de Viaje</h2>\n");
      out.write("\n");
      out.write("    ");
      out.write("\n");
      out.write("\n");
      out.write("    ");
 
        // Procesar el formulario para obtener los datos del cliente y los tipos de seguro de viaje
        String cedulaCliente = request.getParameter("cedulaCliente");
        String nombreCliente = "";
        String apellidoCliente = "";

        // Obtener información del cliente desde la base de datos
        Connection conCliente = null;
        PreparedStatement stmtCliente = null;
        ResultSet rsCliente = null;

        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource dsCliente = (DataSource) envCtx.lookup("jdbc/SegurosCSCDB");
            conCliente = dsCliente.getConnection();

            stmtCliente = conCliente.prepareStatement("SELECT nombre, apellido FROM Clientes WHERE cedula = ?");
            stmtCliente.setString(1, cedulaCliente);

            rsCliente = stmtCliente.executeQuery();
            if (rsCliente.next()) {
                nombreCliente = rsCliente.getString("nombre");
                apellidoCliente = rsCliente.getString("apellido");
            } else {
                // Manejar cliente no encontrado
            }
        } finally {
            // Cerrar recursos
            if (rsCliente != null) rsCliente.close();
            if (stmtCliente != null) stmtCliente.close();
            if (conCliente != null) conCliente.close();
        }
    
      out.write("\n");
      out.write("\n");
      out.write("    <p>Cliente: ");
      out.print( nombreCliente );
      out.write(' ');
      out.print( apellidoCliente );
      out.write("</p>\n");
      out.write("\n");
      out.write("    <form action=\"ProcesarSeguroViaje.jsp\" method=\"post\">\n");
      out.write("        <!-- Aquí puedes agregar campos adicionales para la información del seguro de viaje -->\n");
      out.write("\n");
      out.write("        <label for=\"tipoSeguroViaje\">Seleccionar Tipo de Seguro de Viaje:</label>\n");
      out.write("        <select name=\"tipoSeguroViaje\">\n");
      out.write("            ");
 
                // Obtener y mostrar los tipos de seguros de viaje desde la base de datos
                try {
                    ResultSet rsTiposSeguroViaje = obtenerTiposSeguroViaje();

                    while (rsTiposSeguroViaje.next()) {
                        String tipoPoliza = rsTiposSeguroViaje.getString("tipoPoliza");
            
      out.write("\n");
      out.write("                        <option value=\"");
      out.print( tipoPoliza );
      out.write('"');
      out.write('>');
      out.print( tipoPoliza );
      out.write("</option>\n");
      out.write("            ");

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            
      out.write("\n");
      out.write("        </select>\n");
      out.write("\n");
      out.write("        <br>\n");
      out.write("\n");
      out.write("        <input type=\"hidden\" name=\"cedulaCliente\" value=\"");
      out.print( cedulaCliente );
      out.write("\">\n");
      out.write("        <input type=\"submit\" value=\"Continuar\">\n");
      out.write("    </form>\n");
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
