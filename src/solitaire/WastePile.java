package solitaire;

import DeckOfCards.CartaInglesa;
import Estructuras.Pila;

import java.util.ArrayList;

/**
 * Modela el montículo donde se colocan las cartas
 * que se extraen de Draw pile.
 *
 * @author (Cecilia Curlango Rosas)
 * @version (2025-2)
 */
public class WastePile {
    private Pila<CartaInglesa> cartas;

    public WastePile() {
        cartas = new Pila<>(52);
    }

    public void addCartas(ArrayList<CartaInglesa> nuevas) {
        for (CartaInglesa carta : nuevas) {
            cartas.push(carta);
        }
    }

    public ArrayList<CartaInglesa> emptyPile() {
        ArrayList<CartaInglesa> pile = new ArrayList<>();
        if (!cartas.isVacio()) {
            while (!cartas.isVacio()) {
                pile.add(0, cartas.pop());
            }
        }
        return pile;
    }

    /**
     * Obtener la última carta sin removerla.
     * @return Carta que está encima. Si está vacía, es null.
     */
    public CartaInglesa verCarta() {
        CartaInglesa regresar = null;
        if (!cartas.isVacio()) {
            regresar = cartas.peek();
        }
        return regresar;
    }

    public CartaInglesa getCarta() {
        CartaInglesa regresar = null;
        if (!cartas.isVacio()) {
            regresar = cartas.pop();
        }
        return regresar;
    }

    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        if (cartas.isVacio()) {
            stb.append("---");
        } else {
            CartaInglesa regresar = cartas.peek();
            regresar.makeFaceUp();
            stb.append(regresar.toString());
        }
        return stb.toString();
    }

    public boolean hayCartas() {
        return !cartas.isVacio();
    }
}