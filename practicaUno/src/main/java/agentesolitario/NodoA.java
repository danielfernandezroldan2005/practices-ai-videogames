
package agentesolitario;

import mundosolitario.RepresentacionEstadoOptimizacion;

/**
 * Nodo para un árbol de búsqueda tipo A* que incluye referencia al estado padre y el valor de g(n).
 * 
 * @authors Lorenzo Mandow
 * @version 02-10-2018
 * 
 */
public class NodoA<E extends RepresentacionEstadoOptimizacion<E>> extends NodoBasico<E> {

    private int g;              // g(n) = coste del camino guardado en el árbol desde el nodo a la raíz

    /**
     * @param p estado padre del NodoA
     * @param g valor de g(n) del NodoA
     */
    public NodoA(E p, int g){
        super(p);
        this.g = g;
    }
    
    public int getG(){
        return g;
    }
    
}
