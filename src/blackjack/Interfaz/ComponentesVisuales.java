package blackjack.Interfaz;

import DeckOfCards.CartaInglesa;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ComponentesVisuales {

    public static String estiloBoton() {
        return "-fx-background-color: #238636;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;" +
                "-fx-padding: 10 18 10 18;" +
                "-fx-cursor: hand;";
    }

    public static VBox crearCartaVisual(CartaInglesa carta) {

        VBox cartaVisual = new VBox(5);

        cartaVisual.setPrefSize(80, 120);
        cartaVisual.setMaxSize(80, 120);
        cartaVisual.setAlignment(Pos.CENTER);

        cartaVisual.setStyle("-fx-background-color: white;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
        );

        String textoCarta= carta.toString().replace("\uFE0E", "").replace("\uFE0F", "");

        Label contenido= new Label(textoCarta);

        contenido.setStyle("-fx-font-size: 24px;" + "-fx-font-weight: bold;");

        cartaVisual.getChildren().add(contenido);

        return cartaVisual;
    }
}