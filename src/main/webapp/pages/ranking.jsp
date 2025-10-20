<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Ranking - Truco Argento</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
    <link href="https://fonts.googleapis.com/css2?family=Cinzel+Decorative:wght@400;700&display=swap" rel="stylesheet"/>
</head>
<body>
    <header>
      <h1>Truco Argento</h1>
      <nav>
        <ul>
          <li><a href="${pageContext.request.contextPath}/index.jsp">Inicio</a></li>
          <li><a href="${pageContext.request.contextPath}/pages/juego.jsp">Jugar</a></li>
          <li><a href="${pageContext.request.contextPath}/pages/info.jsp">Información</a></li>
          <li><a href="<%=request.getContextPath()%>/PerfilServlet">Mi Perfil</a></li>
          </ul>
      </nav>
    </header>

    <main>
      <h2>Ranking de Jugadores</h2>

      <table class="ranking-table">
        <thead>
            <tr>
                <th>Avatar</th>
                <th>Usuario</th>
                <th>Victorias</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="usuario" items="${ranking}">
                <tr>
                    <td><img src="<%=request.getContextPath()%>/${usuario.avatar}" alt="Avatar"></td>
                    <td><c:out value="${usuario.nombre}" /></td>
                    <td><c:out value="${usuario.victorias}" /></td>
                </tr>
            </c:forEach>
        </tbody>
      </table>

    </main>
</body>
</html>