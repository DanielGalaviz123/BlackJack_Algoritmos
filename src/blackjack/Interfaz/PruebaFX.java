package blackjack.Interfaz;

import javafx.application.Application;
import javafx.stage.Stage;

public class PruebaFX extends Application {

    @Override
    public void start(Stage stage) {

        EnlaceBlackJack enlace = new EnlaceBlackJack();

        MenuPrincipal menu = new MenuPrincipal(enlace);

        menu.mostrar(stage);
    }


    public static void main(String[] args) {

        launch(args);
    }
}