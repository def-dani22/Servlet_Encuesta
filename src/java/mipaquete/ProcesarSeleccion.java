import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProcesarSeleccion")
public class ProcesarSeleccion extends HttpServlet {
    private static final String JDBC_URL = "jdbc:sqlserver://DESKTOP-KNSONQV:1433;databaseName=SegurosCSC;user=sa;password=1234;loginTimeout=30;";
    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Connection con = null;

        try {
            // Cargar el controlador JDBC
            Class.forName(JDBC_DRIVER);

            // Establecer conexión con la base de datos
            con = DriverManager.getConnection(JDBC_URL);

            // Resto del código para procesar la selección y redirigir
            String tipoSeguro = request.getParameter("tipoSeguro");

            if ("viaje".equals(tipoSeguro)) {
                // Redirigir a la página de seguros de viaje
                response.sendRedirect("SegurosViaje.jsp");
            } else if ("estudiante".equals(tipoSeguro)) {
                // Redirigir a la página de seguros de estudiantes
                response.sendRedirect("SegurosEstudiantes.jsp");
            } else {
                // Manejar opción no válida
                response.sendRedirect("PaginaError.jsp");
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Manejar errores de conexión
            e.printStackTrace();
            response.sendRedirect("PaginaError.jsp");
        } finally {
            // Cerrar la conexión
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
