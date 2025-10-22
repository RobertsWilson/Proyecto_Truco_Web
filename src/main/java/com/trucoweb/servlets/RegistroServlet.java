package com.trucoweb.servlets;

import com.trucoweb.dao.UsuarioDAO;
import com.trucoweb.model.Usuario;
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
        String avatar = request.getParameter("avatar");

        // La contraseña se encripta antes de guardar ese valor en la BD (12 es el numero de rondas de encriptado)
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));

        String avatarPorDefecto = "img/avatar_default.jpg";

        // 2. Crear el objeto Usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(hashed);
        nuevoUsuario.setAvatar(avatarPorDefecto);

        try {
            //Usar el método insert()
            usuarioDAO.insert(nuevoUsuario); //

            response.sendRedirect(request.getContextPath() + "/pages/login.jsp?registro=exitoso");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/pages/registro.jsp?error=1");
        }
    }
}