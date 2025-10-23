TrucoWeb 🃏
TrucoWeb es una aplicación web desarrollada en Java que permite a los usuarios jugar partidas del popular juego de cartas "Truco Argentino". El proyecto incluye un sistema completo de gestión de usuarios (registro, inicio de sesión, perfil) y una lógica de juego contra un bot.

Características Principales
Gestión de Usuarios (CRUD):

Registro de nuevos usuarios.

Inicio de sesión y cierre de sesión seguros.

Gestión de perfil: Los usuarios pueden ver y actualizar su nombre, email y avatar.

Eliminación de cuenta.

Seguridad:

Hashing de contraseñas en la base de datos utilizando jBCrypt.

Gestión de sesiones (HttpSession) para proteger las rutas.

Juego:

Lógica para jugar partidas 1v1 contra un bot (en desarrollo).

Manejo de estado de la partida a través de la sesión del servidor.

Comunidad:

Ranking de Jugadores: Una página pública que muestra a los usuarios ordenados por sus victorias.

Reglas del Juego: Una página de información estática con un acordeón de JavaScript que explica las reglas.

Arquitectura y Tecnologías
El proyecto está construido siguiendo una arquitectura MVC (Modelo-Vista-Controlador) y utiliza el patrón de diseño DAO (Data Access Object) para el acceso a la base de datos.

Tecnologías Utilizadas
Backend:

Java (Jakarta EE)

Servlets

JSP (JavaServer Pages) con JSTL

Frontend:

HTML5

CSS3 (con archivos separados para cada componente)

JavaScript (para interactividad como el acordeón y la lógica del juego)

Base de Datos:

MySQL

Dependencias (Maven):

jakarta.servlet-api

jakarta.servlet.jsp.jstl

mysql-connector-j (Driver de MySQL)

jbcrypt (Para hashing de contraseñas)

Estructura del Proyecto
com.trucoweb.model: Contiene las clases de entidad (POJOs) como Usuario.java.

com.trucoweb.db: Maneja la conexión a la base de datos (AdmConnexion.java).

com.trucoweb.interfaces: Define interfaces genéricas como DAO.java.

com.trucoweb.dao: Implementa la lógica de acceso a datos (UsuarioDAO.java).

com.trucoweb.servlets: Contiene los controladores que manejan las peticiones HTTP (ej. LoginServlet, PerfilServlet).

src/main/webapp: Contiene las vistas (JSP), archivos estáticos (CSS, JS, imágenes) y el descriptor de despliegue (WEB-INF/web.xml).