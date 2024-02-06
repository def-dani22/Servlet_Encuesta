<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seleccionar Tipo de Seguro</title>
</head>
<body>
    <h2>Seleccionar Tipo de Seguro</h2>
    <form action="ProcesarSeleccion.jsp" method="post">
        <input type="radio" name="tipoSeguro" value="viaje" id="viaje">
        <label for="viaje">Seguro de Viaje</label>
        
        <input type="radio" name="tipoSeguro" value="estudiante" id="estudiante">
        <label for="estudiante">Seguro de Estudiante</label>

        <br>

        <input type="submit" value="Continuar">
    </form>
</body>
</html>
