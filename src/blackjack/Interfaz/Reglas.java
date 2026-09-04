package blackjack.Interfaz;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Reglas {

    private EnlaceBlackJack enlace;

    public Reglas(EnlaceBlackJack enlace) {
        this.enlace = enlace;
    }

    public void mostrar(Stage stage) {
        VBox pantalla = new VBox(25);
        pantalla.setAlignment(Pos.CENTER);
        pantalla.setPadding(new Insets(30));
        pantalla.setStyle("-fx-background-color: #0b5e3a;");

        Label titulo = new Label("REGLAS DEL BLACKJACK");
        titulo.setStyle("-fx-text-fill: white;-fx-font-size: 30px;-fx-font-weight: bold;");

        Label textoReglas = new Label(leerReglas());
        textoReglas.setWrapText(true);
        textoReglas.setMaxWidth(850);
        textoReglas.setStyle("-fx-text-fill: #1f2937;-fx-font-size: 16px;-fx-line-spacing: 4px;");

        VBox panel = new VBox(textoReglas);
        panel.setPadding(new Insets(30));
        panel.setMaxWidth(900);
        panel.setStyle("-fx-background-color: #f5f5dc;-fx-background-radius: 15;");

        ScrollPane scroll = new ScrollPane(panel);
        scroll.setFitToWidth(true);
        scroll.setMaxWidth(900);
        scroll.setPrefHeight(580);
        scroll.setStyle("-fx-background-color: transparent;-fx-background: transparent;");

        Button regresar = new Button("REGRESAR");
        regresar.setStyle(ComponentesVisuales.estiloBoton());

        regresar.setOnAction(e -> {
            MenuPrincipal menu = new MenuPrincipal(enlace);
            menu.mostrar(stage);
        });

        pantalla.getChildren().addAll(titulo, scroll, regresar);

        Scene scene = new Scene(pantalla, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());

        stage.setScene(scene);
        stage.setX(0);
        stage.setY(0);
        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        stage.setMaximized(true);
        stage.show();
    }

    private String leerReglas() {
        StringBuilder texto = new StringBuilder();

        try {
            InputStream archivo = getClass().getResourceAsStream("reglas.txt");
            BufferedReader lector = new BufferedReader(new InputStreamReader(archivo));
            String linea;

            while ((linea = lector.readLine()) != null) {
                texto.append(linea).append("\n");
            }

            lector.close();
        } catch (Exception e) {
            return "No se pudieron cargar las reglas.";
        }

        return texto.toString();
    }
}