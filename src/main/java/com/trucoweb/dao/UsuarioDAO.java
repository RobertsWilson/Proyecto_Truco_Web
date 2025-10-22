// language: java
package com.trucoweb.dao;

import com.trucoweb.interfaces.AdmConnexion;
import com.trucoweb.interfaces.DAO; // <-- 1. Importar la interfaz
import com.trucoweb.model.Usuario; //

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) para la entidad Usuario.
 * Implementa la interfaz genérica DAO.
 */
// Implementar la interfaz para <Usuario, Integer>
public class UsuarioDAO implements AdmConnexion, DAO<Usuario, Integer> {


    @Override
    public void insert(Usuario usuario) {
        // Asumimos que el objeto Usuario ya tiene el password hasheado
        String sql = "INSERT INTO usuarios (email, password, nombre, avatar) VALUES (?, ?, ?, ?)";
        try (Connection conn = obtenerConexion(); //
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getAvatar());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void update(Usuario usuario) {
        // Este método actualiza solo la información del perfil, NO la contraseña.
        String sql = "UPDATE usuarios SET nombre = ?, email = ?, avatar = ? WHERE id_usuario = ?";
        try (Connection conn = obtenerConexion(); //
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getAvatar());
            ps.setInt(4, usuario.getId_usuario());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer idUsuario) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        try (Connection conn = obtenerConexion(); //
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario getById(Integer idUsuario) {
        String sql = "SELECT nombre, email, avatar, victorias FROM usuarios WHERE id_usuario = ?";
        Usuario usuario = null;

        try (Connection conn = obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId_usuario(idUsuario);
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setAvatar(rs.getString("avatar"));
                    usuario.setVictorias(rs.getInt("victorias"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public List<Usuario> getAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios ORDER BY nombre ASC";

        try (Connection conn = obtenerConexion(); //
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                usuario.setAvatar(rs.getString("avatar"));
                usuario.setVictorias(rs.getInt("victorias"));

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public boolean existsById(Integer idUsuario) {
        String sql = "SELECT 1 FROM usuarios WHERE id_usuario = ?";
        try (Connection conn = obtenerConexion(); //
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Devuelve true si encontró algo, false si no
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Busca un usuario por su email o nombre de usuario (para el login).
     * @param login El email O el nombre de usuario.
     * @return Un objeto Usuario con datos de sesión (incluyendo hash de password).
     */
    public Usuario buscarPorLogin(String login) {
        String sql = "SELECT id_usuario, password, nombre, avatar FROM usuarios WHERE email = ? OR nombre = ?";
        Usuario usuario = null;

        try (Connection conn = obtenerConexion(); //
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, login);
            ps.setString(2, login);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId_usuario(rs.getInt("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setAvatar(rs.getString("avatar"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    /**
     * Obtiene la lista de usuarios ordenados por victorias para el ranking.
     * return Una Lista de objetos Usuario (solo con datos públicos).
     */
    public List<Usuario> obtenerRanking() {
        List<Usuario> ranking = new ArrayList<>();
        String sql = "SELECT nombre, victorias, avatar FROM usuarios ORDER BY victorias DESC";

        try (Connection conn = obtenerConexion(); //
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
        }
        return ranking;
    }
}