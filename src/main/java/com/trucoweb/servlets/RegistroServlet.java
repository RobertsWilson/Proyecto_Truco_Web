// language: java
package com.trucoweb.servlets;

import com.trucoweb.db.Conexion;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class RegistroServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));

        String sql = "INSERT INTO usuarios (email, password, nombre) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, hashed);
            ps.setString(3, nombre);
            ps.executeUpdate();

            response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error en el registro");
        }
    }
}
