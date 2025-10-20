package com.trucoweb.servlets;

import com.trucoweb.dao.UsuarioDAO;
import com.trucoweb.model.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;

public class LoginServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.usuarioDAO = new UsuarioDAO(); //Instanciar el DAO
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            //Usar el DAO para buscar al usuario
            Usuario usuario = usuarioDAO.buscarPorLogin(login);

            //Verificar la contraseña usando BCrypt
            if (usuario != null && BCrypt.checkpw(password, usuario.getPassword())) {

                //Crear la sesión
                HttpSession session = request.getSession();
                session.setAttribute("id_usuario", usuario.getId_usuario());
                session.setAttribute("usuarioNombre", usuario.getNombre());
                session.setAttribute("usuarioAvatar", usuario.getAvatar());

                response.sendRedirect(request.getContextPath() + "/index.jsp");

            } else {
                // Usuario o contraseña incorrecta
                response.sendRedirect(request.getContextPath() + "/pages/login.jsp?error=1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp?error=2"); // Error genérico
        }
    }
}