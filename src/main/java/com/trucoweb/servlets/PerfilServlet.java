// language: java
package com.trucoweb.servlets;

import com.trucoweb.dao.UsuarioDAO; // <-- 1. Importar
import com.trucoweb.model.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class PerfilServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    //Se instancia el DAO
    @Override
    public void init() throws ServletException {
        super.init();
        this.usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("id_usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
            return;
        }

        int idUsuario = (int) session.getAttribute("id_usuario");

        // Manejar mensajes
        if (session.getAttribute("mensaje") != null) {
            request.setAttribute("mensaje", session.getAttribute("mensaje"));
            session.removeAttribute("mensaje");
        }

        //Usar el DAO para buscar al usuario
        Usuario usuario = usuarioDAO.buscarPorId(idUsuario);

        if (usuario != null) {
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/pages/perfil.jsp").forward(request, response);
        } else {
            // Error: el usuario de la sesión no se encontró en la BD
            session.invalidate(); // Invalidamos la sesión por seguridad
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp?error=perfil_no_encontrado");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("id_usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
            return;
        }

        int idUsuario = (int) session.getAttribute("id_usuario");
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String avatar = request.getParameter("avatar");

        // Creamos un objeto Usuario para pasarlo al DAO
        Usuario usuario = new Usuario();
        usuario.setId_usuario(idUsuario);
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setAvatar(avatar);

        //Usar el DAO para actualizar
        boolean exito = usuarioDAO.actualizarPerfil(usuario);

        if (exito) {
            // Actualizar los datos de la sesión
            session.setAttribute("usuarioNombre", nombre);
            session.setAttribute("usuarioAvatar", avatar);
            session.setAttribute("mensaje", "¡Perfil actualizado con éxito!");
        } else {
            session.setAttribute("mensaje", "Error al actualizar el perfil.");
        }

        response.sendRedirect(request.getContextPath() + "/PerfilServlet");
    }
}