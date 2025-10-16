<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <title>Información sobre el Truco</title>
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
      <section class="acordeon-container">
        <div class="acordeon-item">
          <button class="acordeon-titulo">📜 Historia del Truco</button>
          <div class="acordeon-contenido">
            <p>
              El truco es un juego de cartas tradicional argentino que se
              remonta al siglo XIX...
            </p>
          </div>
        </div>

        <div class="acordeon-item">
          <button class="acordeon-titulo">🃏 Reglas Básicas</button>
          <div class="acordeon-contenido">
            <ul>
              <li>Se juega con una baraja española de 40 cartas.</li>
              <li>Las cartas tienen jerarquía específica.</li>
              <li>Se puede cantar Truco, Retruco y Vale Cuatro.</li>
            </ul>
          </div>
        </div>

        <div class="acordeon-item">
          <button class="acordeon-titulo">🤝 Señas y comunicación</button>
          <div class="acordeon-contenido">
            <p>
              Las señas se realizan con gestos sutiles: guiños, labios, cejas...
            </p>
          </div>
        </div>

        <div class="acordeon-item">
          <button class="acordeon-titulo">🔢 Jerarquía de cartas</button>
          <div class="acordeon-contenido">
            <ol>
              <li>1 de Espada</li>
              <li>1 de Basto</li>
              <li>7 de Espada</li>
              <li>7 de Oro</li>
              <li>3, 2, 1 (Copa/Oro), 12, 11, 10, 7 (Copa/Basto), 6, 5, 4</li>
            </ol>
          </div>
        </div>
      </section>
    </main>
    <script src="../js/acordeon.js"></script>

    <footer>
      <p>&copy; 2025 Truco Argento</p>
    </footer>
  </body>
</html>
