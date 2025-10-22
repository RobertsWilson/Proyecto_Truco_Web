package com.trucoweb.servlets;

import com.trucoweb.dao.UsuarioDAO;
import com.trucoweb.model.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class PerfilServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

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

        if (session.getAttribute("mensaje") != null) {
            request.setAttribute("mensaje", session.getAttribute("mensaje"));
            session.removeAttribute("mensaje");
        }

        //Usar el método getById() de la interfaz DAO
        Usuario usuario = usuarioDAO.getById(idUsuario); //

        if (usuario != null) {
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/pages/perfil.jsp").forward(request, response);
        } else {
            session.invalidate();
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

        //Preparar el objeto modelo con los datos del formulario
        int idUsuario = (int) session.getAttribute("id_usuario");
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String avatar = request.getParameter("avatar");

        Usuario usuario = new Usuario(); //
        usuario.setId_usuario(idUsuario);
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setAvatar(avatar);

        try {
            //Usar el método update() de la interfaz DAO
            usuarioDAO.update(usuario); //

            //Actualizar la sesión
            session.setAttribute("usuarioNombre", nombre);
            session.setAttribute("usuarioAvatar", avatar);
            session.setAttribute("mensaje", "¡Perfil actualizado con éxito!");

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("mensaje", "Error al actualizar el perfil.");
        }

        response.sendRedirect(request.getContextPath() + "/PerfilServlet");
    }
}