<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Registro - TrucoWeb</title>
  <link rel="stylesheet" href="../css/main.css">
</head>
<body>

<main class="form-container">
  <h2>Crear cuenta</h2>
  <form action="${pageContext.request.contextPath}/RegistroServlet" method="post">
    <label>Nombre:</label>
    <input type="text" name="nombre" required>

    <label>Email:</label>
    <input type="email" name="email" required>

    <label>Contraseña:</label>
    <input type="password" name="password" required>

    <button type="submit" class="btn">Registrarme</button>
  </form>
</main>

</body>
</html>
