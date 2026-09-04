package blackjack.Interfaz;

import DeckOfCards.CartaInglesa;
import blackjack.Logica.Dealer;
import blackjack.Logica.Jugador;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.ArrayList;

public class MesaBlackJack {

    private EnlaceBlackJack enlace;
    private VBox tablaJugadores;
    private VBox zonaJugador;
    private HBox manoJugador;
    private HBox cartasDealer;
    private Label nombreJugadorActual;
    private Label mensajeMazo;
    private Button pedir;
    private Button plantarse;
    private Button siguiente;
    private Button otraRonda;
    private boolean mostrarMensajeMazo;

    public MesaBlackJack(EnlaceBlackJack enlace) {
        this.enlace = enlace;
    }

    public void mostrar(Stage stage, ArrayList<String> nombres) {
        BorderPane mesa = new BorderPane();
        mesa.setStyle("-fx-background-color: #0b5e3a;");mesa.setStyle("-fx-background-image: url('file:C:/Users/danie/Downloads/fonoBlackJACK.png');-fx-background-size: cover;-fx-background-position: center center;-fx-background-repeat: no-repeat;");

        tablaJugadores = new VBox(10);
        tablaJugadores.setStyle("-fx-padding: 20;");
        actualizarTablaJugadores();

        VBox zonaDealer = new VBox(15);
        zonaDealer.setAlignment(Pos.CENTER);

        Label dealerTitulo = new Label("DEALER");
        dealerTitulo.setStyle("-fx-text-fill: white;-fx-font-size: 22px;");

        cartasDealer = new HBox(10);
        cartasDealer.setAlignment(Pos.CENTER);
        actualizarDealer();
        zonaDealer.getChildren().addAll(dealerTitulo, cartasDealer);

        BorderPane superior = new BorderPane();

        mensajeMazo = new Label();
        mensajeMazo.setStyle("-fx-text-fill: white;-fx-font-size: 16px;-fx-font-weight: bold;-fx-padding: 20;");

        superior.setLeft(tablaJugadores);
        superior.setCenter(zonaDealer);
        superior.setRight(mensajeMazo);
        mesa.setTop(superior);

        if (mostrarMensajeMazo) {
            actualizarMensajeMazo();
        }

        zonaJugador = new VBox(20);
        zonaJugador.setAlignment(Pos.CENTER);

        nombreJugadorActual = new Label();
        nombreJugadorActual.setStyle("-fx-text-fill: white;-fx-font-size: 24px;");

        manoJugador = new HBox(10);
        manoJugador.setAlignment(Pos.CENTER);

        pedir = new Button("PEDIR");
        plantarse = new Button("PLANTARSE");
        siguiente = new Button("SIGUIENTE");
        otraRonda = new Button("OTRA RONDA");

        pedir.setStyle(ComponentesVisuales.estiloBoton());
        plantarse.setStyle(ComponentesVisuales.estiloBoton());
        siguiente.setStyle(ComponentesVisuales.estiloBoton());
        otraRonda.setStyle(ComponentesVisuales.estiloBoton());

        siguiente.setVisible(false);
        otraRonda.setVisible(false);

        HBox botones = new HBox(20);
        botones.setAlignment(Pos.CENTER);
        botones.setTranslateX(120);
        botones.getChildren().addAll(pedir, plantarse, siguiente, otraRonda);

        zonaJugador.getChildren().addAll(nombreJugadorActual, manoJugador, botones);
        mesa.setCenter(zonaJugador);

        mostrarJugadorActual();

        pedir.setOnAction(e -> {
            CartaInglesa carta = enlace.pedirCarta();
            manoJugador.getChildren().add(ComponentesVisuales.crearCartaVisual(carta));
            actualizarTablaJugadores();

            Jugador jugador = enlace.getJugadorActual();
            int total = jugador.getManoJugador().cacularValor();
            nombreJugadorActual.setText(jugador.getNombreJugador() + " - Total: " + total);

            if (total == 21) {
                nombreJugadorActual.setText(jugador.getNombreJugador() + " llego a 21");
                pedir.setVisible(false);
                plantarse.setVisible(false);
                siguiente.setVisible(true);
            } else if (enlace.jugadorSePaso()) {
                nombreJugadorActual.setText(jugador.getNombreJugador() + " se paso con " + total);
                pedir.setVisible(false);
                plantarse.setVisible(false);
                siguiente.setVisible(true);
            }
        });

        plantarse.setOnAction(e -> pasarSiguienteJugador());

        siguiente.setOnAction(e -> {
            siguiente.setVisible(false);
            pasarSiguienteJugador();
        });

        otraRonda.setOnAction(e -> {
            enlace.nuevaRonda();
            mostrarMensajeMazo = true;
            mostrar(stage, nombres);
        });

        Scene scene = new Scene(mesa, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());

        stage.setScene(scene);
        stage.setX(0);
        stage.setY(0);
        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        stage.setMaximized(true);
        stage.show();
    }

    private void mostrarJugadorActual() {
        Jugador jugador = enlace.getJugadorActual();
        manoJugador.getChildren().clear();

        for (CartaInglesa carta : jugador.getManoJugador().getMano()) {
            manoJugador.getChildren().add(ComponentesVisuales.crearCartaVisual(carta));
        }

        int total = jugador.getManoJugador().cacularValor();
        nombreJugadorActual.setText(jugador.getNombreJugador() + " - Total: " + total);

        pedir.setVisible(true);
        plantarse.setVisible(true);
        siguiente.setVisible(false);
        pedir.setDisable(false);
        plantarse.setDisable(false);

        if (total == 21) {
            nombreJugadorActual.setText(jugador.getNombreJugador() + " tiene 21");
            pedir.setVisible(false);
            plantarse.setVisible(false);
            siguiente.setVisible(true);
        }
    }

    private void pasarSiguienteJugador() {
        boolean quedanJugadores = enlace.siguienteJugador();

        if (quedanJugadores) {
            mostrarJugadorActual();
        } else {
            terminarRonda();
        }
    }

    private void terminarRonda() {
        pedir.setVisible(false);
        plantarse.setVisible(false);
        siguiente.setVisible(false);
        nombreJugadorActual.setText("Turno del Dealer");

        enlace.jugarTurnoDealer();
        actualizarDealer();
        actualizarTablaJugadores();
        mostrarResultados();
        otraRonda.setVisible(true);
    }

    private void actualizarTablaJugadores() {
        tablaJugadores.getChildren().clear();

        Label titulo = new Label("JUGADORES");
        titulo.setStyle("-fx-text-fill: white;-fx-font-size: 18px;-fx-font-weight: bold;");
        tablaJugadores.getChildren().add(titulo);

        for (Jugador jugador : enlace.getJuego().getJugadores()) {
            int total = jugador.getManoJugador().cacularValor();
            Label jugadorLabel = new Label(jugador.getNombreJugador() + "     " + total);
            jugadorLabel.setStyle("-fx-text-fill: white;-fx-font-size: 16px;");
            tablaJugadores.getChildren().add(jugadorLabel);
        }
    }

    private void actualizarDealer() {
        cartasDealer.getChildren().clear();
        Dealer dealer = enlace.getDealer();

        for (CartaInglesa carta : dealer.getManoDealer().getMano()) {
            cartasDealer.getChildren().add(ComponentesVisuales.crearCartaVisual(carta));
        }
    }

    private void mostrarResultados() {
        manoJugador.getChildren().clear();

        int valorDealer = enlace.getDealer().getManoDealer().cacularValor();
        nombreJugadorActual.setText("Dealer termino con " + valorDealer);

        VBox resultados = new VBox(10);
        resultados.setAlignment(Pos.CENTER);

        Label tituloResultados = new Label("RESULTADOS");
        tituloResultados.setStyle("-fx-text-fill: white;-fx-font-size: 22px;-fx-font-weight: bold;");
        resultados.getChildren().add(tituloResultados);

        for (Jugador jugador : enlace.getJuego().getJugadores()) {
            int valorJugador = jugador.getManoJugador().cacularValor();
            String resultado = enlace.obtenerResultado(jugador);

            Label resultadoJugador = new Label(jugador.getNombreJugador() + " (" + valorJugador + ") - " + resultado);
            resultadoJugador.setStyle("-fx-text-fill: white;-fx-font-size: 18px;");
            resultados.getChildren().add(resultadoJugador);
        }

        zonaJugador.getChildren().add(2, resultados);
    }

    private void actualizarMensajeMazo() {
        if (enlace.isMazoNuevo()) {
            mensajeMazo.setText("Barajeando mazo");
        } else {
            mensajeMazo.setText("Partida con el mismo mazo");
        }
    }


}