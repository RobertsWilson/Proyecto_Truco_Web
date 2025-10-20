package com.trucoweb.dao;

import com.trucoweb.db.AdmConnexion;
import com.trucoweb.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements AdmConnexion {

    /**
     * Inserta un nuevo usuario en la base de datos.
     * parametro nombre El nombre del usuario.
     * parametro email El email del usuario.
     * parametro hashedPassword La contraseña ya hasheada con BCrypt.
     * return true si el registro fue exitoso, false si falló.
     */
    public boolean crearUsuario(String nombre, String email, String hashedPassword) {
        String sql = "INSERT INTO usuarios (email, password, nombre) VALUES (?, ?, ?)";
        try (Connection conn = obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, hashedPassword);
            ps.setString(3, nombre);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Busca un usuario por su email o nombre de usuario (para el login).
     * parametro login El email O el nombre de usuario.
     * return Un objeto Usuario con los datos (incluyendo el hash del password),
     * o null si no se encuentra.
     */
    public Usuario buscarPorLogin(String login) {
        String sql = "SELECT id_usuario, password, nombre, avatar FROM usuarios WHERE email = ? OR nombre = ?";
        Usuario usuario = null;

        try (Connection conn = obtenerConexion();
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
     * Busca un usuario por su ID.
     * Se usa para cargar la página de perfil.
     * parametro idUsuario El ID del usuario.
     * return Un objeto Usuario con los datos, o null si no se encuentra.
     */
    public Usuario buscarPorId(int idUsuario) {
        String sql = "SELECT nombre, email, avatar FROM usuarios WHERE id_usuario = ?";
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
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    /**
     * Actualiza los datos del perfil de un usuario en la BD.
     * parametro usuario El objeto Usuario con los datos (ID, nombre, email, avatar) a actualizar.
     * return true si la actualización fue exitosa, false si falló.
     */
    public boolean actualizarPerfil(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, email = ?, avatar = ? WHERE id_usuario = ?";
        try (Connection conn = obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getAvatar());
            ps.setInt(4, usuario.getId_usuario());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina un usuario de la base de datos usando su ID.
     * parametro idUsuario El ID del usuario a eliminar.
     * return true si la eliminación fue exitosa, false si falló.
     */
    public boolean eliminarUsuario(int idUsuario) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        try (Connection conn = obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene la lista de usuarios ordenados por victorias para el ranking.
     * return Una Lista de objetos Usuario.
     */
    public List<Usuario> obtenerRanking() {
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
            // Retorna una lista vacía en caso de error
        }
        return ranking;
    }
}