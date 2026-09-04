package blackjack.Interfaz;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SeleccionJugadores {

    private EnlaceBlackJack enlace;

    public SeleccionJugadores(EnlaceBlackJack enlace) {
        this.enlace = enlace;
    }

    public void mostrar(Stage stage) {

        HBox pantalla = new HBox();
        pantalla.setStyle("-fx-background-color: #0b5e3a;");

        Separator linea = new Separator();
        linea.setOrientation(Orientation.VERTICAL);

        VBox ladoIzquierdo = new VBox(20);
        ladoIzquierdo.setAlignment(Pos.CENTER);
        ladoIzquierdo.setMaxWidth(Double.MAX_VALUE);

        Label tituloCantidad = new Label("Selecciona cantidad de jugadores");
        tituloCantidad.setStyle("-fx-text-fill: #f5f5dc;" + "-fx-font-size: 26px;" + "-fx-font-weight: bold;");

        ComboBox<Integer> cantidadJugadores = new ComboBox<>();
        cantidadJugadores.getItems().addAll(1, 2, 3, 4);
        cantidadJugadores.setPrefSize(220, 50);

        cantidadJugadores.setStyle(
                "-fx-font-size: 17px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-radius: 12;" +
                        "-fx-cursor: hand;"
        );

        ladoIzquierdo.getChildren().addAll(tituloCantidad, cantidadJugadores);

        //derecho
        VBox ladoDerecho = new VBox(20);
        ladoDerecho.setAlignment(Pos.CENTER);
        ladoDerecho.setMaxWidth(Double.MAX_VALUE);

        Label tituloNombres = new Label("Nombres de jugadores");
        tituloNombres.setStyle("-fx-text-fill: #f5f5dc;" + "-fx-font-size: 26px;" + "-fx-font-weight: bold;");

        ladoDerecho.getChildren().add(tituloNombres);

        ArrayList<TextField> camposNombres = new ArrayList<>();

        //ejecutar dependiendo cantidad de jugadores
        cantidadJugadores.setOnAction(e -> {

            int cantidad = cantidadJugadores.getValue();

            camposNombres.clear();
            ladoDerecho.getChildren().clear();
            ladoDerecho.getChildren().add(tituloNombres);

            //creacion de espacios
            for (int i = 1; i <= cantidad; i++) {

                TextField nombreJugador = new TextField();

                nombreJugador.setPromptText("Nombre del jugador " + i);
                nombreJugador.setPrefSize(320, 45);
                nombreJugador.setMaxWidth(320);

                nombreJugador.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-text-fill: #1f2937;" +
                                "-fx-font-size: 16px;" +
                                "-fx-background-radius: 10;" +
                                "-fx-border-radius: 10;" +
                                "-fx-border-color: #d1d5db;" +
                                "-fx-border-width: 1;"
                );

                camposNombres.add(nombreJugador);
                ladoDerecho.getChildren().add(nombreJugador);
            }



            Button comenzar = new Button("COMENZAR");
            comenzar.setPrefSize(220, 50);
            comenzar.setStyle(ComponentesVisuales.estiloBoton());

            ladoDerecho.getChildren().add(comenzar);

            comenzar.setOnAction(event -> {

                ArrayList<String> nombres = new ArrayList<>();

                for (TextField campo : camposNombres) {
                    nombres.add(campo.getText());
                }

                enlace.iniciarJuego(nombres);

                MesaBlackJack mesa = new MesaBlackJack(enlace);
                mesa.mostrar(stage, nombres);
            });
        });


        HBox.setHgrow(ladoIzquierdo, Priority.ALWAYS);
        HBox.setHgrow(ladoDerecho, Priority.ALWAYS);

        pantalla.getChildren().addAll(ladoIzquierdo, linea, ladoDerecho);

        Scene scene = new Scene(pantalla, 1200, 700);

        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setMaximized(true);
    }
}