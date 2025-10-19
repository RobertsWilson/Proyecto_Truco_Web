// language: java
package com.trucoweb.servlets;

import com.trucoweb.db.AdmConnexion;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarUsuarioServlet extends HttpServlet implements AdmConnexion {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // 1. Verificar si el usuario está logueado
        if (session == null || session.getAttribute("id_usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
            return;
        }

        // 2. Obtener el ID de la sesión
        int idUsuario = (int) session.getAttribute("id_usuario");

        // 3. Ejecutar el borrado
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        try (Connection conn = obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                // 4. Si se borró, invalidar la sesión (logout)
                session.invalidate();
                // 5. Redirigir al login con un mensaje de éxito
                response.sendRedirect(request.getContextPath() + "/pages/login.jsp?mensaje=cuenta_eliminada");
            } else {
                // 6. Si falló, redirigir al perfil con un error
                session.setAttribute("mensaje", "Error: No se pudo eliminar la cuenta.");
                response.sendRedirect(request.getContextPath() + "/PerfilServlet");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // 6. Si falló, redirigir al perfil con un error
            session.setAttribute("mensaje", "Error de base de datos al eliminar la cuenta.");
            response.sendRedirect(request.getContextPath() + "/PerfilServlet");
        }
    }
}