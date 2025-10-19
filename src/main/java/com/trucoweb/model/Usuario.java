package com.trucoweb.model;

public class Usuario {
    private int id_usuario;
    private String nombre;
    private String email;
    private String password;
    private int victorias;
    private String avatar;


    public Usuario() { }


    public Usuario(String nombreUsuario, String email, String password, String avatar) {
        this.nombre = nombreUsuario;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.victorias = 0;
    }

    // Getters y Setters
    public int getId_usuario() { return id_usuario; }
    public void setId_usuario(int id_usuario) { this.id_usuario = id_usuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getVictorias() { return victorias; }
    public void setVictorias(int victorias) { this.victorias = victorias; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id_usuario +
                ", nombreUsuario='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", victorias=" + victorias +
                '}';
    }
}
