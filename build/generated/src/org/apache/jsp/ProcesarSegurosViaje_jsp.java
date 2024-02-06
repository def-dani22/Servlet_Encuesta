package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public final class ProcesarSegurosViaje_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


        // Función para procesar el formulario de seguro de viaje
        void procesarFormulario(String cedulaCliente, String tipoSeguroViaje) throws SQLException {
            Connection con = null;
            PreparedStatement stmt = null;

            try {
                // Establecer conexión con la base de datos
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");
                DataSource ds = (DataSource) envCtx.lookup("jdbc/SegurosCSCDB");
                con = ds.getConnection();

                // Aquí puedes agregar la lógica para almacenar la información en la base de datos
                // por ejemplo, insertar un nuevo registro en la tabla polizaXclientes
                // con la cédula del cliente y el tipo de seguro de viaje seleccionado.

                // Ejemplo:
                // stmt = con.prepareStatement("INSERT INTO polizaXclientes (cedula, idPoliza) VALUES (?, ?)");
                // stmt.setString(1, cedulaCliente);
                // stmt.setInt(2, obtenerIdTipoSeguroViaje(tipoSeguroViaje));
                // stmt.executeUpdate();
            } finally {
                // Cerrar recursos
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            }
        }

        // Puedes agregar una función similar a obtenerIdTipoSeguroViaje para obtener el ID del tipo de seguro de viaje
    
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
      out.write("    <title>Procesando Seguro de Viaje</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <h2>Procesando Seguro de Viaje</h2>\n");
      out.write("\n");
      out.write("    ");
      out.write("\n");
      out.write("\n");
      out.write("    ");
 
        // Procesar el formulario de seguro de viaje
        String cedulaCliente = request.getParameter("cedulaCliente");
        String tipoSeguroViaje = request.getParameter("tipoSeguroViaje");

        try {
            procesarFormulario(cedulaCliente, tipoSeguroViaje);
    
      out.write("\n");
      out.write("            <p>¡El seguro de viaje ha sido procesado con éxito!</p>\n");
      out.write("    ");

        } catch (SQLException e) {
            e.printStackTrace();
    
      out.write("\n");
      out.write("            <p>Error al procesar el seguro de viaje. Por favor, inténtelo de nuevo más tarde.</p>\n");
      out.write("    ");

        }
    
      out.write("\n");
      out.write("\n");
      out.write("    <!-- Aquí puedes agregar más contenido o redirigir al usuario a otra página según tus necesidades -->\n");
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
