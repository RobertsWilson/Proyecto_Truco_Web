<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login - Truco Argento</title>
    <link rel="stylesheet" href="../css/main.css">
</head>
<body>

<main class="form-container">
    <h2>Iniciar sesión</h2>

    <%-- Si viene con error --%>
    <% if (request.getParameter("error") != null) { %>
        <p style="color:red;">Usuario o contraseña incorrectos</p>
    <% } %>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <label>Email o Nombre de usuario:</label>
        <input type="text" name="login" required>

        <label>Contraseña:</label>
        <input type="password" name="password" required>

        <button type="submit" class="btn">Ingresar</button>
    </form>

    <p>¿No tenés cuenta? <a href="registro.jsp">Registrate aquí</a></p>
</main>

</body>
</html>
