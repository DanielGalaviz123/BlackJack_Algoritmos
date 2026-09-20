package blackjack.Logica;

import DeckOfCards.CartaInglesa;

public class Movimiento {

    private int jugador;
    private CartaInglesa carta;

    public Movimiento(int jugador, CartaInglesa carta) {
        this.jugador = jugador;
        this.carta = carta;
    }

    public int getJugador() {
        return jugador;
    }

    public CartaInglesa getCarta() {
        return carta;
    }
}