// language: java
package com.trucoweb.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays; // <-- Importado para Streams
import java.util.stream.Collectors; // <-- Importado para Streams

/**
 * Representa el mazo de 40 cartas.
 * Se encarga de crear las cartas, barajarlas y repartirlas.
 */
public class Mazo {

    private List<Carta> cartas;

    /**
     * Al crear un mazo, automáticamente se crean las 40 cartas
     * y se les asigna sus valores de truco y envido.
     * * Esta versión utiliza Streams de Java 8 para generar las cartas.
     */
    public Mazo() {
        String[] palos = {"ESPADA", "BASTO", "ORO", "COPA"};
        int[] numeros = {1, 2, 3, 4, 5, 6, 7, 10, 11, 12};

        this.cartas = Arrays.stream(palos) // 1. Crea un Stream de palos (["ESPADA", ...])
                .flatMap(palo ->                // 2. Por cada palo, "aplana" un nuevo stream:
                        Arrays.stream(numeros)        // 3. ...un Stream de números ([1, 2, 3...])
                                .mapToObj(numero ->       // 4. ...convertido en un Stream de Cartas
                                        // 5. Se crea la carta para cada par (palo, numero)
                                        new Carta(palo, numero,
                                                calcularValorTruco(palo, numero),
                                                calcularValorEnvido(numero))
                                )
                )
                .collect(Collectors.toList()); // 6. Colecciona todas las 40 cartas en una lista


    }

    /**
     * Mezcla el mazo de forma aleatoria.
     */
    public void barajar() {
        Collections.shuffle(this.cartas);
    }

    /**
     * Saca una carta del mazo.
     * Al usar .remove(), nos aseguramos de que esta carta
     * no pueda volver a ser repartida (evitando duplicados).
     * @return La carta de arriba del mazo, o null si el mazo está vacío.
     */
    public Carta repartirCarta() {
        if (this.cartas.isEmpty()) {
            return null;
        }
        return this.cartas.remove(0); // Saca la primera carta de la lista
    }

    /**
     * Reparte una mano completa de 3 cartas.
     * Simplemente llama a repartirCarta() tres veces.
     * @return Una nueva lista (List<Carta>) con 3 cartas.
     */
    public List<Carta> repartirMano() {
        List<Carta> mano = new ArrayList<>();

        mano.add(repartirCarta());
        mano.add(repartirCarta());
        mano.add(repartirCarta());

        return mano;
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
     * Calcula el "poder" o "ranking" de una carta para el Truco.
     * 14 = más alta (Ancho de Espada)
     * 1 = más baja (4s)
     */
    private int calcularValorTruco(String palo, int numero) {
        switch (numero) {
            // "Matas"
            case 1:
                if (palo.equals("ESPADA")) return 14; // 1ro - Ancho de Espada
                if (palo.equals("BASTO")) return 13;  // 2do - Ancho de Basto
                return 8; // 7mo - Anchos Falsos (Oro y Copa)
            case 7:
                if (palo.equals("ESPADA")) return 12; // 3ro - Siete de Espada
                if (palo.equals("ORO")) return 11;    // 4to - Siete de Oro
                return 4; // 11vo - Sietes Falsos (Basto y Copa)

            // Cartas de valor
            case 3: return 10; // 5to - Todos los 3
            case 2: return 9;  // 6to - Todos los 2

            // "Negras" (Figuras)
            case 12: return 7; // 8vo - Todos los Reyes (12)
            case 11: return 6; // 9no - Todos los Caballos (11)
            case 10: return 5; // 10mo - Todas las Sotas (10)

            // Cartas comunes
            case 6: return 3; // 12vo - Todos los 6
            case 5: return 2; // 13vo - Todos los 5
            case 4: return 1; // 14vo - Todos los 4 (los más bajos)

            default: return 0; // Nunca debería pasar
        }
    }
}