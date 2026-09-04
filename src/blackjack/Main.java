package blackjack;

import blackjack.Interfaz.PruebaFX;
import blackjack.Logica.JuegoBlackJack;
import javafx.application.Application;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("---- BLACKJACK ----");
        System.out.println("1. Modo consola");
        System.out.println("2. Interfaz grafica");

        int opcion = leerNumero(scanner, "Selecciona una opcion: ", 1, 2);

        switch (opcion) {
            case 1:
                ejecutarConsola(scanner);
                break;
            case 2:
                Application.launch(PruebaFX.class, args);
                break;
        }
    }

    private static void ejecutarConsola(Scanner scanner) {
        JuegoBlackJack juego = new JuegoBlackJack();

        int cantidad = leerNumero(scanner, "Cantidad de jugadores (1-4): ", 1, 4);
        String[] nombres = new String[cantidad];

        for (int i = 0; i < cantidad; i++) {
            String nombre;

            do {
                System.out.print("Nombre del jugador " + (i + 1) + ": ");
                nombre = scanner.nextLine().trim();

                if (nombre.isEmpty()) {
                    System.out.println("El nombre no puede estar vacio.");
                }
            } while (nombre.isEmpty());

            nombres[i] = nombre;
        }

        juego.flujoJuego(cantidad, nombres);
    }

    private static int leerNumero(Scanner scanner, String mensaje, int minimo, int maximo) {
        int numero;

        while (true) {
            System.out.print(mensaje);

            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                scanner.nextLine();

                if (numero >= minimo && numero <= maximo) {
                    return numero;
                }

                System.out.println("Ingresa un numero entre " + minimo + " y " + maximo + ".");
            } else {
                System.out.println("Debes ingresar un numero.");
                scanner.nextLine();
            }
        }
    }
}