// language: java
package com.trucoweb.servlets;

import com.trucoweb.db.AdmConnexion;
import com.trucoweb.model.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PerfilServlet extends HttpServlet implements AdmConnexion {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // No crear nueva sesión

        // 1. Verificar si el usuario está logueado
        if (session == null || session.getAttribute("id_usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
            return;
        }

        // 2. Recuperar el ID del usuario de la sesión
        int idUsuario = (int) session.getAttribute("id_usuario");

        // 3. (Opcional) Manejar mensajes de éxito/error del POST
        if (session.getAttribute("mensaje") != null) {
            request.setAttribute("mensaje", session.getAttribute("mensaje"));
            session.removeAttribute("mensaje"); // Limpiar el mensaje
        }

        // 4. Buscar los datos actuales del usuario en la BD
        String sql = "SELECT nombre, email, avatar FROM usuarios WHERE id_usuario = ?";
        Usuario usuario = null;

        try (Connection conn = obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId_usuario(idUsuario);
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setAvatar(rs.getString("avatar"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error al cargar el perfil.");
            return;
        }

        // 5. Enviar el objeto usuario al JSP y hacer forward
        request.setAttribute("usuario", usuario);
        request.getRequestDispatcher("/pages/perfil.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);

        // 1. Verificar si el usuario está logueado
        if (session == null || session.getAttribute("id_usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
            return;
        }

        // 2. Recuperar el ID de la sesión
        int idUsuario = (int) session.getAttribute("id_usuario");

        // 3. Obtener los datos del formulario
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String avatar = request.getParameter("avatar");
        // (Aquí iría la validación de datos: que no estén vacíos, que el email sea válido, etc.)

        // 4. Actualizar la base de datos
        String sql = "UPDATE usuarios SET nombre = ?, email = ?, avatar = ? WHERE id_usuario = ?";
        try (Connection conn = obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, avatar);
            ps.setInt(4, idUsuario);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("mensaje", "Error al actualizar el perfil.");
            response.sendRedirect(request.getContextPath() + "/PerfilServlet"); // Redirige al doGet
            return;
        }

        // 5. Actualizar los datos de la sesión
        session.setAttribute("usuarioNombre", nombre);
        session.setAttribute("usuarioAvatar", avatar);

        // 6. Redirigir de vuelta al Perfil
        session.setAttribute("mensaje", "¡Perfil actualizado con éxito!");
        response.sendRedirect(request.getContextPath() + "/PerfilServlet");
    }
}