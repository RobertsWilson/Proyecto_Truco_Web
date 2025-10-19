<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mi Perfil - Truco Argento</title>
    <link rel="stylesheet" href="../css/main.css">
    <style>
        .form-container img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            display: block;
            margin: 0 auto 1rem;
        }
        .mensaje {
            padding: 10px;
            border-radius: 5px;
            text-align: center;
            margin-bottom: 1rem;
            color: white;
        }
        .exito { background-color: green; }
        .error { background-color: red; }
        .delete-btn {
            background-color: #990000;
            border: none;
            cursor: pointer;
            margin-top: 1rem;
        }
        .delete-btn:hover { background-color: #cc0000; }
    </style>
</head>
<body>

<main class="form-container">

    <img src="<%=request.getContextPath()%>/${usuario.avatar}" alt="Avatar actual">

    <h2>Mi Perfil</h2>

    <c:if test="${not empty mensaje}">
        <p class="mensaje ${mensaje.startsWith('Error') ? 'error' : 'exito'}">
            <c:out value="${mensaje}"/>
        </p>
    </c:if>

    <form action="<%=request.getContextPath()%>/PerfilServlet" method="post">
        <label>Nombre:</label>
        <input type="text" name="nombre" value="${usuario.nombre}" required>

        <label>Email:</label>
        <input type="email" name="email" value="${usuario.email}" required>

        <label>Ruta del Avatar (ej: img/mi_foto.png):</label>
        <input type="text" name="avatar" value="${usuario.avatar}" required>

        <button type="submit" class="btn">Guardar Cambios</button>
    </form>

    <hr style="margin: 2rem 0;">

    <form action="<%=request.getContextPath()%>/EliminarUsuarioServlet" method="post"
          onsubmit="return confirm('¿Estás seguro de que quieres eliminar tu cuenta? Esta acción es irreversible.');">
        <button type="submit" class="btn delete-btn">Eliminar mi cuenta</button>
    </form>

    <p><a href="index.jsp">Volver al inicio</a></p>

</main>

</body>
</html>