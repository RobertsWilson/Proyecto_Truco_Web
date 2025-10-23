package com.trucoweb.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa el mazo de 40 cartas.
 * Se encarga de crear las cartas, barajarlas y repartirlas.
 */
public class Mazo {

    private List<Carta> cartas;

    /**
     * Al crear un mazo, automáticamente se crean las 40 cartas
     * y se les asigna sus valores de truco y envido.
     */
    public Mazo() {
        this.cartas = new ArrayList<>();

        String[] palos = {"ESPADA", "BASTO", "ORO", "COPA"};
        int[] numeros = {1, 2, 3, 4, 5, 6, 7, 10, 11, 12};

        for (String palo : palos) {
            for (int numero : numeros) {
                // Se calculan los valores para esta carta específica
                int valorTruco = calcularValorTruco(palo, numero);
                int valorEnvido = calcularValorEnvido(numero);

                // Se crea la carta y se añade al mazo
                this.cartas.add(new Carta(palo, numero, valorTruco, valorEnvido));
            }
        }
    }
    /**
     * Mezcla el mazo de forma aleatoria.
     */
    public void barajar() {
        Collections.shuffle(this.cartas);
    }

    /**
     * Saca una carta del mazo.
     * Al usar .remove(), asegura de que esta carta
     * no pueda volver a ser repartida (evitando duplicados).
     * return La carta de arriba del mazo, o null si el mazo está vacío.
     */
    public Carta repartirCarta() {
        if (this.cartas.isEmpty()) {
            return null;
        }
        return this.cartas.remove(0); // Saca la primera carta de la lista
    }


    /**
     * Calcula el valor de una carta para el Envido.
     */
    private int calcularValorEnvido(int numero) {
        if (numero >= 10) {
            return 0; // 10, 11 y 12 valen 0
        }
        return numero; // 1-7 valen su propio número
    }

    /**
     * Calcula el valor de una carta para el Truco.
     * 14 - Ancho de Espada
     * 1 - 4 - Cartas más bajas
     */
    private int calcularValorTruco(String palo, int numero) {
        switch (numero) {
            //
            case 1:
                if (palo.equals("ESPADA")) return 14; //  Ancho de Espada
                if (palo.equals("BASTO")) return 13;  // Ancho de Basto
                return 8; //Anchos Falsos (Oro y Copa)
            case 7:
                if (palo.equals("ESPADA")) return 12; //Siete de Espada
                if (palo.equals("ORO")) return 11;    //Siete de Oro
                return 4; //Sietes Falsos (Basto y Copa)

            // Cartas de valor
            case 3: return 10; //Todos los 3
            case 2: return 9;  //6to - Todos los 2


            case 12: return 7; //Todos los Reyes (12)
            case 11: return 6; //Todos los Caballos (11)
            case 10: return 5; //Todas las Sotas (10)


            case 6: return 3; //Todos los 6
            case 5: return 2; //Todos los 5
            case 4: return 1; //Todos los 4 (los más bajos)

            default: return 0; // si esto pasa = ERROR
        }
    }
}