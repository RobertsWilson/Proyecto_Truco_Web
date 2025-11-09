<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Mi Perfil - Truco Argento</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/perfil.css" />
</head>
<body>

    <main>

        <div class="volver-inicio-container">
         <a href="${pageContext.request.contextPath}/index.jsp" class="boton-accion boton-volver">Volver al Inicio</a>
        </div>

        <h2>Tu Perfil</h2>

        <c:if test="${not empty mensaje}">
            <p class="mensaje-feedback">${mensaje}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/PerfilServlet" method="POST" class="form-perfil">
            <div class="form-group">
                <label for="nombre">Nombre de Usuario:</label>
                <input type="text" id="nombre" name="nombre" value="<c:out value='${usuario.nombre}'/>" required>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="<c:out value='${usuario.email}'/>" required>
            </div>

            <div class="form-group">
                <label for="avatar">Ruta del Avatar:</label>
                <input type="text" id="avatar" name="avatar" value="<c:out value='${usuario.avatar}'/>" required>
                </div>

            <div class="form-group">
                <button type="submit" class="boton-primario">Guardar Cambios</button>
            </div>
        </form>




        <div class="acciones-container acciones-wrapper">
            <a href="${pageContext.request.contextPath}/logout" class="boton-accion boton-logout">
                Cerrar Sesión
            </a>
            <hr>

            <form action="${pageContext.request.contextPath}/EliminarUsuarioServlet" method="POST"
                  onsubmit="return confirm('¿Estás seguro de que quieres eliminar tu cuenta? Esta acción es irreversible.');">
                <button type="submit" class="boton-accion boton-eliminar">Eliminar Cuenta</button>
            </form>

        </div>
    </main>

</body>
</html>