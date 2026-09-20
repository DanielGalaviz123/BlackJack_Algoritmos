package blackjack.Interfaz;

import DeckOfCards.CartaInglesa;
import blackjack.Logica.Dealer;
import blackjack.Logica.JuegoBlackJack;
import blackjack.Logica.Jugador;
import blackjack.Logica.Movimiento;

import java.util.ArrayList;

public class EnlaceBlackJack {

    private JuegoBlackJack juego;
    private int turnoActual;

    public void iniciarJuego(ArrayList<String> nombres) {
        juego = new JuegoBlackJack();

        String[] arregloNombres = nombres.toArray(new String[0]);
        juego.iniciarJuego(nombres.size(), arregloNombres);
        turnoActual = 0;
    }

    public void nuevaRonda() {
        juego.nuevaRonda();
        turnoActual = 0;
    }

    public Jugador getJugadorActual() {
        return juego.getJugadores().get(turnoActual);
    }

    public CartaInglesa pedirCarta() {
        return juego.pedirCartaJugador(turnoActual);
    }

    public Movimiento undo() {

        Movimiento movimiento = juego.undo();

        if (movimiento != null) {
            turnoActual = movimiento.getJugador();
        }

        return movimiento;
    }

    public boolean jugadorSePaso() {
        return getJugadorActual().getManoJugador().sePaso();
    }

    public boolean siguienteJugador() {
        turnoActual++;
        return turnoActual < juego.getJugadores().size();
    }

    public void jugarTurnoDealer() {
        juego.turnoDealer();
    }

    public String obtenerResultado(Jugador jugador) {
        return juego.obtenerResultado(jugador);
    }

    public JuegoBlackJack getJuego() {
        return juego;
    }

    public Dealer getDealer() {
        return juego.getDealer();
    }

    public boolean isMazoNuevo() {
        return juego.isMazoNuevo();
    }
}