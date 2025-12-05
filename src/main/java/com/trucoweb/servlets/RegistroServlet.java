package com.trucoweb.servlets;

import com.trucoweb.dao.UsuarioDAO;
import com.trucoweb.model.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;

public class RegistroServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

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

        // Encriptar password
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
        String avatarPorDefecto = "img/avatar_default.jpg";

        // Crear objeto Usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(hashed);
        nuevoUsuario.setAvatar(avatarPorDefecto);

        try {
            //Insertar en la BD
            usuarioDAO.insert(nuevoUsuario);

            // 2. RECUPERAR EL USUARIO COMPLETO (necesitamos el ID generado por la BD)
            // Reutilizamos tu método existente buscarPorLogin usando el email
            Usuario usuarioRegistrado = usuarioDAO.buscarPorLogin(email);

            if (usuarioRegistrado != null) {
                // Se crea la sesion directamente tras el registro
                HttpSession session = request.getSession();
                session.setAttribute("id_usuario", usuarioRegistrado.getId_usuario());
                session.setAttribute("usuarioNombre", usuarioRegistrado.getNombre());
                session.setAttribute("usuarioAvatar", usuarioRegistrado.getAvatar());

                //Redirigir al Index
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                // Si por alguna razón falla la recuperación, enviarlo al login como respaldo
                response.sendRedirect(request.getContextPath() + "/pages/login.jsp?registro=exitoso");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/pages/registro.jsp?error=1");
        }
    }
}