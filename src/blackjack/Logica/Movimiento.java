package blackjack.Logica;

import DeckOfCards.CartaInglesa;

public class Movimiento {

    private int jugador;
    private CartaInglesa carta;
    private String tipo;

    public Movimiento(int jugador, CartaInglesa carta, String tipo) {
        this.jugador= jugador;
        this.carta=carta;
        this.tipo= tipo;
    }

    public int getJugador() {
        return jugador;
    }

    public CartaInglesa getCarta() {
        return carta;
    }

    public String getTipo() {
        return tipo;
    }
}