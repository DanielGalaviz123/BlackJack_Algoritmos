package blackjack.Interfaz;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MenuPrincipal {

    private EnlaceBlackJack enlace;

    public MenuPrincipal(EnlaceBlackJack enlace) {
        this.enlace = enlace;
    }

    public void mostrar(Stage stage) {
        VBox menu = new VBox(20);
        menu.setAlignment(Pos.CENTER);
        menu.setStyle("-fx-background-color: #0b5e3a;");

        Image imagenLogo = new Image("file:C:/Users/danie/OneDrive/Pictures/Capturas de pantalla/logoBlack.png");

        ImageView logo = new ImageView(imagenLogo);
        logo.setFitWidth(250);
        logo.setPreserveRatio(true);

        Button jugar = new Button("JUGAR");
        Button reglas = new Button("REGLAS");

        jugar.setStyle(ComponentesVisuales.estiloBoton());
        reglas.setStyle(ComponentesVisuales.estiloBoton());

        jugar.setOnAction(e -> { SeleccionJugadores seleccion = new SeleccionJugadores(enlace);
            seleccion.mostrar(stage);
        });

        reglas.setOnAction(e -> {Reglas pantallaReglas = new Reglas(enlace);
            pantallaReglas.mostrar(stage);
        });

        menu.getChildren().addAll(logo, jugar, reglas);

        Scene scene = new Scene(menu, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());

        stage.setTitle("Blackjack");
        stage.setScene(scene);
        stage.setX(0);
        stage.setY(0);
        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        stage.setMaximized(true);
        stage.show();
    }
}