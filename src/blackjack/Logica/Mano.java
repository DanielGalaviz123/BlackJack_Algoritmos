package blackjack.Logica;

import DeckOfCards.CartaInglesa;

import java.util.ArrayList;

public class Mano {
    private ArrayList<CartaInglesa> mano;

    public Mano() {
        mano = new ArrayList<>();
    }

    public void addCard(CartaInglesa carta) {
        mano.add(carta);
    }

    public boolean checkAs(){
        boolean doubleAS;
        if(mano.get(0).getValor() == 14 && mano.get(1).getValor() == 14 ){
            doubleAS = true;
        }else{
            doubleAS =false;
        }
        return doubleAS;
    }

    public int valueAs(int valorFinal){
        int resAs = valorFinal + 11;
        if(resAs<=21){
            return resAs;
        }else{
            return valorFinal + 1;

        }

    }


    /*que necesito para calcular valor?
    cuantas cartas son
    valor de cada carta
    sumar y total
    con getValor() de la clase carta
     */
    public int cacularValor() {
        //operador ternario para no utilzar otro if
        int valorFinal = checkAs() ? 12 : 0;
        int valorInicio = checkAs() ? 2 : 0;

        int cantidadAses = checkAs() ? 2 : 0;

        for (int i = valorInicio; i < mano.size(); i++) {
            if (mano.get(i).getValor() == 11 || mano.get(i).getValor() == 12 || mano.get(i).getValor() == 13 ){
                int valorMono=10;
                valorFinal=valorFinal+valorMono;
            }else if(mano.get(i).getValor()==14){
                //llamar metodo valueAs
                valorFinal= valueAs(valorFinal);
                cantidadAses++;
            }else{
                valorFinal=valorFinal+mano.get(i).getValor();
            }
        }

        if (valorFinal > 21 && cantidadAses > 0) {
            valorFinal = valorFinal - 10;
        }
        return valorFinal;

    }

    public void limpiarMano() {
        mano.clear();
    }

    public boolean sePaso(){
        return mano.size() >= 2 && cacularValor()>21;
    }

    public boolean tieneBlackJack(){
        return mano.size() == 2 && cacularValor() == 21;
    }


    public ArrayList<CartaInglesa> getMano(){
        return mano;
    }
}