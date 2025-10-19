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
import java.util.ArrayList;
import java.util.List;

public class RankingServlet extends HttpServlet implements AdmConnexion {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> ranking = new ArrayList<>();
        String sql = "SELECT nombre, victorias, avatar FROM usuarios ORDER BY victorias DESC";

        try (Connection conn = obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNombre(rs.getString("nombre"));
                usuario.setVictorias(rs.getInt("victorias"));
                usuario.setAvatar(rs.getString("avatar"));
                ranking.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error al cargar el ranking.");
            return;
        }

        // Enviar la lista de ranking al JSP
        request.setAttribute("ranking", ranking);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/ranking.jsp");
        dispatcher.forward(request, response);
    }
}