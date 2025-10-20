<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mi Perfil - Truco Argento</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
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