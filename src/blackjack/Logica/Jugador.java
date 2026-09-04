package blackjack.Logica;

public class Jugador {

    private String nombreJugador;
    private Mano manoJugador;
    //private int apuesta;

    public Jugador(String nombreJugador,Mano manoJugador){
        this.nombreJugador=nombreJugador;
        this.manoJugador=manoJugador;
       // this.apuesta=apuesta;

    }



    public String getNombreJugador(){
        return nombreJugador;
    }

    public Mano getManoJugador(){
        return manoJugador;
    }
}
