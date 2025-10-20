// language: java
package com.trucoweb.servlets;

import com.trucoweb.dao.UsuarioDAO;
import com.trucoweb.model.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class RankingServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    // Instancia del DAO
    @Override
    public void init() throws ServletException {
        super.init();
        this.usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 5. Usar el DAO para obtener el ranking
        // El DAO se encarga del try-catch y de devolver una lista (vacía en caso de error)
        List<Usuario> ranking = usuarioDAO.obtenerRanking();

        // Enviar la lista de ranking al JSP
        request.setAttribute("ranking", ranking);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/ranking.jsp");
        dispatcher.forward(request, response);
    }
}