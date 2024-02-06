<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formulario de Seguro de Viaje</title>
</head>
<body>
    <h2>Formulario de Seguro de Viaje</h2>

    <%!
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
    %>

    <% 
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
    %>

    <p>Cliente: <%= nombreCliente %> <%= apellidoCliente %></p>

    <form action="ProcesarSeguroViaje.jsp" method="post">
        <!-- Aquí puedes agregar campos adicionales para la información del seguro de viaje -->

        <label for="tipoSeguroViaje">Seleccionar Tipo de Seguro de Viaje:</label>
        <select name="tipoSeguroViaje">
            <% 
                // Obtener y mostrar los tipos de seguros de viaje desde la base de datos
                try {
                    ResultSet rsTiposSeguroViaje = obtenerTiposSeguroViaje();

                    while (rsTiposSeguroViaje.next()) {
                        String tipoPoliza = rsTiposSeguroViaje.getString("tipoPoliza");
            %>
                        <option value="<%= tipoPoliza %>"><%= tipoPoliza %></option>
            <%
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            %>
        </select>

        <br>

        <input type="hidden" name="cedulaCliente" value="<%= cedulaCliente %>">
        <input type="submit" value="Continuar">
    </form>
</body>
</html>
