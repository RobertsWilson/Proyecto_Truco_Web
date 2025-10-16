<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <title>Jugar al Truco</title>
    <link rel="stylesheet" href="../css/main.css" />

    <link
      href="https://fonts.googleapis.com/css2?family=Cinzel+Decorative:wght@400;700&display=swap"
      rel="stylesheet"
    />
  </head>
  <body>
    <header>
      <h1>Truco Argento</h1>
      <nav>
        <ul>
          <li><a href="../index.jsp">Inicio</a></li>
          <li><a href="juego.jsp">Jugar</a></li>
          <li><a href="info.jsp">Información</a></li>
        </ul>
      </nav>
    </header>

    <main>
      <h2>Partida contra el Bot</h2>
      <p>(Prototipo inicial)</p>
      <div id="mesa">
        <button onclick="jugarCarta()">Jugar Carta</button>
        <div id="resultado"></div>
      </div>
    </main>

    <script src="../js/juego.js"></script>
  </body>
</html>
