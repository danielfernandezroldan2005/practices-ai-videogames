
package agentesolitario;

import mundosolitario.RepresentacionEstado;

/**
 * Nodo para un árbol de búsqueda básico que incluye únicamente una referencia al estado padre (p. ej.
 * búsqueda en amplitud).
 * 
 * @authors Lorenzo Mandow 
 * @version 02-10-2018
 * 
 */
public class NodoBasico<E extends RepresentacionEstado<E>> {

    private E padre = null;

    /**
     * @param padre del NodoBasico
     */
    public NodoBasico(E p){
        this.padre = p;
    }

    public E getPadre(){
        return padre;
    }
    
}
