// language: java
package com.trucoweb.servlets;

import com.trucoweb.dao.UsuarioDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class EliminarUsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.usuarioDAO = new UsuarioDAO(); //Instanciar
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("id_usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
            return;
        }

        int idUsuario = (int) session.getAttribute("id_usuario");

        //Usar el DAO para eliminar
        boolean exito = usuarioDAO.eliminarUsuario(idUsuario);

        if (exito) {
            // Si se borró, invalidar la sesión
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp?mensaje=cuenta_eliminada");
        } else {
            // Si falló, redirigir al perfil con un error
            session.setAttribute("mensaje", "Error: No se pudo eliminar la cuenta.");
            response.sendRedirect(request.getContextPath() + "/PerfilServlet");
        }
    }
}