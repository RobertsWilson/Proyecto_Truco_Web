package com.trucoweb.model;

public class Usuario {
    private int id;
    private String nombreUsuario;
    private String email;
    private String password;
    private int victorias;
    private String avatar;


    public Usuario() { }


    public Usuario(String nombreUsuario, String email, String password, String avatar) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.victorias = 0;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

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
                "id=" + id +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", email='" + email + '\'' +
                ", victorias=" + victorias +
                '}';
    }
}
