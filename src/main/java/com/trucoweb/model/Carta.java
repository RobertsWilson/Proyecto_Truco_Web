package com.trucoweb.model;

import java.io.Serializable;

public class Carta implements Serializable {

    // Interfaz marcadora, necesaria para guardar este objeto en la HttpSession.
    private static final long serialVersionUID = 1L;

    private String palo;     // ESPADA, BASTO, ORO, COPA
    private int numero;      // 1-7, 10-12
    private int valorTruco;  // (1-14)
    private int valorEnvido; // El valor para el envido (0-7)
    private String idImagen; // Ej: "1_ESPADA" para buscar el .png

    public Carta(String palo, int numero, int valorTruco, int valorEnvido) {
        this.palo = palo;
        this.numero = numero;
        this.valorTruco = valorTruco;
        this.valorEnvido = valorEnvido;
        this.idImagen = numero + "_" + palo; //id para la imagen
    }




    public String getPalo() {
        return palo;
    }

    public int getNumero() {
        return numero;
    }

    public int getValorTruco() {
        return valorTruco;
    }

    public int getValorEnvido() {
        return valorEnvido;
    }

    public String getIdImagen() {
        return idImagen;
    }


    @Override
    public String toString() {
        return numero + " de " + palo + " (T:" + valorTruco + ", E:" + valorEnvido + ")";
    }
}
