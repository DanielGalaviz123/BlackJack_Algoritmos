package Estructuras;

public class Pila<T> {

    private T[] pila;
    private int tope;

    public Pila(int capacidad) {
        pila = (T[]) new Object[capacidad];
        tope = -1;
    }

    public boolean isLLeno(){return tope == pila.length - 1 ? true : false;}

    public boolean isVacio(){
        return tope == -1 ? true : false;
    }

    public void push(T dato) {
        if (isLLeno()) {
            System.out.println("Desbordamiento");
            return;
        }else{
            tope++;
            pila[tope] = dato;
        }

    }

    public T pop() {
        if (tope == -1) {
            System.out.println("Pila vacia");
            return null;
        }

        T dato = pila[tope];
        pila[tope] = null;
        tope--;

        return dato;
    }

    public T peek() {
        if (tope == -1) {
            return null;
        }

        return pila[tope];
    }

    public void clear() {
        while (!isVacio()) {
            pop();
        }
    }

    public int size() {
        return tope + 1;
    }
}