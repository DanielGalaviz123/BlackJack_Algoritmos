package blackjack.Logica;

import DeckOfCards.CartaInglesa;
import DeckOfCards.Mazo;

import java.util.ArrayList;


public class JuegoBlackJack {
    private ArrayList<Jugador> jugadors;
    private Dealer dealer;
    private Mazo mazo;

    private int cartasUsadas;
    private boolean mazoNuevo;

    private static final int TOTAL_CARTAS = 52;
    private static final double PORCENTAJE_CAMBIO_MAZO = 0.40;

    private VistaConsola vista = new VistaConsola();


    //No necesario porque en la clase de la profe ya esta creado Mazo mazo
    //ArrayList<CartaInglesa> cartasJuego = new ArrayList<>();

    //1.crear un mazo
    private void crearMazo() {

        mazo = new Mazo();
        cartasUsadas = 0;
    }

    private CartaInglesa obtenerCartaMazo() {
        CartaInglesa carta = mazo.obtenerUnaCarta();
        cartasUsadas++;
        return carta;
    }

    private void crearDealer() {
        dealer = new Dealer(new Mano());
    }

    //This method creates the specified nombres of players
    private void crearJugadores(int cantidad, String[] nombres) {
        jugadors = new ArrayList<>();

        for (int i = 0; i < cantidad; i++) {
            Jugador jugador = new Jugador("Jugador" + (i + 1) + ":" + nombres[i], new Mano());
            jugadors.add(jugador);
        }

    }

    private void repartirCartas() {
        //ciclo donde reparta primero una carta a cada jugador, despues a dealer y de una segunda vuelta.
        //almacenar valores del mazo al nuevo array de jugadores y de dealer.

        for (int i = 0; i < 2; i++) {

            for (int j = 0; j < jugadors.size(); j++) {
                CartaInglesa carta = obtenerCartaMazo();
                carta.makeFaceUp();
                jugadors.get(j).getManoJugador().addCard(carta);
            }
            //agregar carta a dealer
            CartaInglesa carta = obtenerCartaMazo();
            if(i==0) {
                carta.makeFaceUp();
            }
            dealer.getManoDealer().addCard(carta);

        }
    }




    private void creacionObjetos(int cantidad, String[] nombres) {
        crearMazo();
        crearDealer();
        crearJugadores(cantidad, nombres);
    }



    public void flujoJuego(int cantidad, String[] nombres) {

        creacionObjetos(cantidad, nombres);
        repartirCartas();

        boolean seguirJugando = true;

        while (seguirJugando) {

            vista.mostrarDealer(dealer);

            for (int i = 0; i < jugadors.size(); i++) {
                vista.mostrarJugador(jugadors.get(i));
                turnoJugador(i);
            }

            turnoDealer();

            System.out.println("\n----- RESULTADOS -----");

            for (int i = 0; i < jugadors.size(); i++) {
                comprobarValorFinal(i);
            }

            System.out.println("\n1. Otra ronda");
            System.out.println("2. Salir");

            int opcion = vista.otraRondaOSalir();

            if (opcion == 1) {
                nuevaRonda();
            } else {
                seguirJugando = false;
            }
        }
    }




   /*think
   the entire flow of de game
1. Create players
2. Create a dealer
3. Deal cards
4. Calculate values against the dealer
4. Enable the “Hit” and “Stand” buttons
5. Switch turns and verify winners
6. Clear players' hands
7. Repeat cycle


    */


    public void turnoJugador(int i) {
        //.first, check blackjack
        //.second, check double as

        //Es una manere de controlar los turnos de los jugadores
        boolean turnoActivo = true;
        boolean firstFilter = jugadors.get(i).getManoJugador().tieneBlackJack();

        if (firstFilter) {
            System.out.println("Tienes black jack");
            jugadors.get(i).getManoJugador().cacularValor();
            turnoActivo = false;
        }

        do {

            if (!turnoActivo) {
                break;
            }

            int opcion = vista.pedirOPlantarse();
            switch (opcion) {
                case 1:
                   // CartaInglesa carta = mazo.obtenerUnaCarta();
                   // carta.makeFaceUp();
                   // jugadors.get(i).getManoJugador().addCard(carta);
                    CartaInglesa carta = pedirCartaJugador(i);
                    vista.mostrarJugador(jugadors.get(i));

                    if (jugadors.get(i).getManoJugador().sePaso()) {
                        System.out.println("Se paso y retirar mano");
                        //jugadors.get(i).getManoJugador().getMano().clear();
                        turnoActivo = false;
                    }
                    break;

                case 2:
                    System.out.println("Te plantaste en " + jugadors.get(i).getManoJugador().cacularValor());
                    turnoActivo = false;
                    break;

                default:
                    System.out.println("Valor no valido");
            }
        } while (turnoActivo);

    }


    public void turnoDealer() {
        //total es de la carta que muestra
        //voltea carta y suma el total de las dos cartas
        //comprueba si el total es 17 o mas y se planta
        //si se planta compara totales con los todos los jugadores para definir ganadores
        //si es igual o menos a 16 pide
        //comprueba si se paso y total
        //se repite hasta llegar a 17 o mas

        boolean controlDealer = true;

        // revelar carta oculta
        dealer.getManoDealer().getMano().get(1).makeFaceUp();

        vista.mostrarDealer(dealer);

        if (dealer.getManoDealer().tieneBlackJack()) {
            controlDealer = false;
            return;
        }

        int valorinicial = dealer.getManoDealer().cacularValor();


        if (valorinicial >= 17) {
            System.out.println("Dealer se planta en " + dealer.getManoDealer().cacularValor());
            //System.out.println("Dealer se planta en " + dealer.getManoDealer().cacularValor());
            controlDealer = false;
        } else {
            do {

                CartaInglesa carta = obtenerCartaMazo();
                carta.makeFaceUp();
                dealer.getManoDealer().addCard(carta);
                vista.mostrarDealer(dealer);
                if (dealer.getManoDealer().sePaso()) {
                    System.out.println("El dealer se paso con " + dealer.getManoDealer().cacularValor());
                    //dealer.getManoDealer().getMano().clear();
                    controlDealer = false;
                } else if (dealer.getManoDealer().cacularValor() >= 17) {

                    System.out.println("El dealer se planta en " + dealer.getManoDealer().cacularValor());
                    controlDealer = false;

                } else {
                    controlDealer = true;


                }

            } while (controlDealer);

        }
    }

    public void comprobarValorFinal(int i) {
        /*
        Obtener el valor de dealer
        Recorrer los distintos valores de los jugadores
        Comparar cada valor contra el dealer
         */

        int valorDealer = dealer.getManoDealer().cacularValor();
        int valorJugador = jugadors.get(i).getManoJugador().cacularValor();

        boolean dentroDealer = (valorDealer <= 21 ) ? true : false;
        boolean sePasoJugador = (valorJugador >= 22) ? true : false;
        //boolean mayorDealer = (valorDealer > valorJugador) ? true : false;


        if ((sePasoJugador && dentroDealer) || (valorJugador<valorDealer && valorDealer<=21)) {
            System.out.println("Dealer le gano a " + jugadors.get(i).getNombreJugador());
        } else if ((valorDealer == valorJugador) && valorJugador<21) {
            System.out.println("Dealer tiene tablas con: " + jugadors.get(i).getNombreJugador());
        } else {
            System.out.println("Dealer perdio contra: " + jugadors.get(i).getNombreJugador());
        }


    }

    //esto para enlace

    public void iniciarJuego(int cantidad, String[] nombres) {
        creacionObjetos(cantidad, nombres);
        repartirCartas();
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadors;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public CartaInglesa pedirCartaJugador(int i) {
        CartaInglesa carta = obtenerCartaMazo();
        carta.makeFaceUp();
        jugadors.get(i).getManoJugador().addCard(carta);
        return carta;
    }

    public String obtenerResultado(Jugador jugador) {

        int valorJugador = jugador.getManoJugador().cacularValor();
        int valorDealer = dealer.getManoDealer().cacularValor();

        if (valorJugador > 21) {
            return "PERDIO";
        }

        if (valorDealer > 21) {
            return "GANO";
        }

        if (valorJugador > valorDealer) {
            return "GANO";
        }

        if (valorJugador < valorDealer) {
            return "PERDIO";
        }

        return "EMPATE";
    }

    public void nuevaRonda() {
        System.out.println("\n---NUEVA RONDA---");
        System.out.println("Cartas restantes antes: " + getCartasRestantes());

        for (Jugador jugador : jugadors) {
            jugador.getManoJugador().limpiarMano();
        }

        dealer.getManoDealer().limpiarMano();

        int cartasRestantes = TOTAL_CARTAS - cartasUsadas;
        int limite = (int) (TOTAL_CARTAS * PORCENTAJE_CAMBIO_MAZO);

        if (cartasRestantes <= limite) {
            System.out.println("Barajando mazo nuevo...");
            crearMazo();
            mazoNuevo = true;
        } else {
            System.out.println("Se continua utilizando el mismo mazo.");
            mazoNuevo = false;
        }

        repartirCartas();

        System.out.println("Cartas restantes despues de repartir: " + getCartasRestantes());
    }

    public boolean isMazoNuevo() {
        return mazoNuevo;
    }


    public int getCartasRestantes() {
        return TOTAL_CARTAS - cartasUsadas;
    }




}



