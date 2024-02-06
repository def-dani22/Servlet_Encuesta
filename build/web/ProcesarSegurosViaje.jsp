<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Procesando Seguro de Viaje</title>
</head>
<body>
    <h2>Procesando Seguro de Viaje</h2>

    <%!
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
    %>

    <% 
        // Procesar el formulario de seguro de viaje
        String cedulaCliente = request.getParameter("cedulaCliente");
        String tipoSeguroViaje = request.getParameter("tipoSeguroViaje");

        try {
            procesarFormulario(cedulaCliente, tipoSeguroViaje);
    %>
            <p>¡El seguro de viaje ha sido procesado con éxito!</p>
    <%
        } catch (SQLException e) {
            e.printStackTrace();
    %>
            <p>Error al procesar el seguro de viaje. Por favor, inténtelo de nuevo más tarde.</p>
    <%
        }
    %>

    <!-- Aquí puedes agregar más contenido o redirigir al usuario a otra página según tus necesidades -->
</body>
</html>
