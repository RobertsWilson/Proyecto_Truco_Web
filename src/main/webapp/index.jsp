<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Truco Argento</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>

<header>
    <h1>Truco Argento</h1>
    <nav>
        <ul>
            <li><a href="index.jsp">Inicio</a></li>
            <li><a href="pages/juego.jsp">Jugar</a></li>
            <li><a href="pages/info.jsp">Información</a></li>

            <%-- Si NO está logueado, mostrar Login y Registro --%>
            <% if (session.getAttribute("usuarioNombre") == null) { %>
                <li><a href="pages/login.jsp">Login</a></li>
                <li><a href="pages/registro.jsp">Registrarse</a></li>
            <% } %>
        </ul>
    </nav>

    <%-- Si está logueado, mostrar avatar arriba a la derecha --%>
    <% if (session.getAttribute("usuarioNombre") != null) { %>
        <div class="usuario-avatar">
            <img src="img/avatar_default.jpg" class="avatar" alt="Usuario">
        </div>
    <% } %>
</header>

<main>
    <h2>Bienvenido a Truco Argento</h2>

    <% if (session.getAttribute("usuarioNombre") != null) { %>
        <p>Hola, <strong><%= session.getAttribute("usuarioNombre") %></strong> 👋</p>
        <a class="btn" href="pages/juego.jsp">¡Continuar jugando!</a>
    <% } else { %>
        <p>¡Preservá la tradición jugando al truco argentino!</p>
        <a class="btn" href="pages/login.jsp">Iniciar sesión</a>
        <a class="btn" href="pages/juego.jsp">Jugar como invitado</a>
    <% } %>
</main>

<footer>
    <p>&copy; 2025 Truco Argento</p>
</footer>

</body>
</html>
