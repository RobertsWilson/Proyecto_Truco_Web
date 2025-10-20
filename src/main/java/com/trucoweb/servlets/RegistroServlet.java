package com.trucoweb.servlets;

import com.trucoweb.dao.UsuarioDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;

public class RegistroServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    //Usar init() para instanciar el DAO una sola vez
    @Override
    public void init() throws ServletException {
        super.init();
        this.usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //Hashear la contraseña con BCrypt
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));

        try {
            //Usar el DAO para crear el usuario
            boolean exito = usuarioDAO.crearUsuario(nombre, email, hashed);

            if (exito) {
                // Redirigir al login si el registro fue exitoso
                response.sendRedirect(request.getContextPath() + "/pages/login.jsp?registro=exitoso");
            } else {
                // Manejar error de registro
                response.sendRedirect(request.getContextPath() + "/pages/registro.jsp?error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar error inesperado
            response.sendRedirect(request.getContextPath() + "/pages/registro.jsp?error=2");
        }
    }
}