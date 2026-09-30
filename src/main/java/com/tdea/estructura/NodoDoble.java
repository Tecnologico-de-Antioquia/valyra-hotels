package src.main.java.com.tdea.estructura;


public class NodoDoble<T> {
    private T valor;
    private NodoSimple<T> anterior;
    private NodoSimple<T> siguiente;

    public NodoDoble(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }

    public void setAnterior(NodoSimple<T> anteriorNodo) {
        this.anterior = anteriorNodo;
    }

    public NodoSimple<T> getAnterior() {
        return this.anterior;
    }

    public void setSiguiente(NodoSimple<T> siguienteNodo) {
        this.siguiente = siguienteNodo;
    }

    public NodoSimple<T> getSiguiente() {
        return this.siguiente;
    }
}
