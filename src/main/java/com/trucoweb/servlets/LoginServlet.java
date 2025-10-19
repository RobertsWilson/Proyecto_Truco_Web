// language: java
package com.trucoweb.servlets;

import com.trucoweb.db.AdmConnexion;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class LoginServlet extends HttpServlet implements AdmConnexion {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        String sql = "SELECT id_usuario, password, nombre, avatar FROM usuarios WHERE email = ? OR nombre = ?";
        try (Connection conn = obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, login);
            ps.setString(2, login);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password");
                    if (BCrypt.checkpw(password, storedHash)) {
                        int idObtenido = rs.getInt("id_usuario"); // Obtener el ID
                        String nombreObtenido = rs.getString("nombre");
                        String avatarObtenido = rs.getString("avatar"); // Obtener el avatar

                        HttpSession session = request.getSession();
                        session.setAttribute("id_usuario", idObtenido); // <-- ¡VITAL!
                        session.setAttribute("usuarioNombre", nombreObtenido);
                        session.setAttribute("usuarioAvatar", avatarObtenido); // <-- NUEVO

                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                        return;
                    }
                }
                response.sendRedirect(request.getContextPath() + "/pages/login.jsp?error=1");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error en el login");
        }
    }
}
