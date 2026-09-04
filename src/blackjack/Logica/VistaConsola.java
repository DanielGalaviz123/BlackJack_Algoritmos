package blackjack.Logica;

import java.util.Scanner;

public class VistaConsola {

    private Scanner scanner = new Scanner(System.in);

    public void mostrarJugador(Jugador jugador) {
        System.out.println("Jugador: " +jugador.getNombreJugador());
        System.out.println("Cartas: " +jugador.getManoJugador().getMano());
        System.out.println("Valor: " +jugador.getManoJugador().cacularValor());
        System.out.println();
    }

    public void mostrarDealer(Dealer dealer) {
        System.out.println("Dealer");
        System.out.println("Cartas: " + dealer.getManoDealer().getMano());
        //System.out.println("Valor: " + dealer.getManoDealer().cacularValor());
        System.out.println();
    }

    public int pedirOPlantarse() {
        int opcion;

        do {
            System.out.println("1. Pedir carta");
            System.out.println("2. Plantarse");
            System.out.print("Selecciona una opcion: ");
            opcion = scanner.nextInt();

            if (opcion != 1 && opcion != 2) {
                System.out.println("Opcion no valida.");
            }

        } while (opcion != 1 && opcion != 2);

        return opcion;
    }

    public int otraRondaOSalir() {
        int opcion;

        do {
            System.out.print("Selecciona una opcion: ");
            opcion = scanner.nextInt();

            if (opcion != 1 && opcion != 2) {
                System.out.println("Opcion no valida.");
            }

        } while (opcion != 1 && opcion != 2);

        return opcion;
    }
}