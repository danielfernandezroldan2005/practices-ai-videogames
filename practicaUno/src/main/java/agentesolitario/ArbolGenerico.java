
package agentesolitario;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import mundosolitario.RepresentacionEstado;


/**
 *
 * Arbol de busqueda genérico que encapsula un HashMap.
 * 
 * Cada nodo corresponde a un estado de tipo E, y para el mismo se
 * guarda un conjunto de información de tipo N, que incluirá al menos la referencia al padre.
 * Internamente se asocia cada estado con su nodo mediante un HashMap. 
 * 
 * El padre de un nodo raíz debe ser null.
 *
 * @authors Lorenzo Mandow (2018)
 * 
 * version 2018-10-02
 */

public class ArbolGenerico<E extends RepresentacionEstado<E>, N extends NodoBasico<E>> {

    private HashMap<E,N> tabla;
    
    public ArbolGenerico() {
        this.tabla = new HashMap<E, N>();
    }
   
    /**
     * Inserta en el árbol el estado e con la información del nodo n. Esta
     * operación destruye cualquier otra aparición previa del estado e en el
     * árbol.
     * 
     * @param e Un estado
     * @param n Un nodo con la información del estado
     */
    public void put(E e, N n) {
        tabla.put(e, n);
    }

    public boolean contains(E e) {
        return tabla.containsKey(e);
    }
    
    public N get(E e) {
        return tabla.get(e);
    }

    /**
     * @param e  un estado que debe estar contenido obligatoriamente en el árbol.
     * @return lista de estados en el árbol desde el estado e hasta uno raíz (su padre es null).
     */
    public List<E> recuperaSolucion(E e) {      
        LinkedList<E> c = new LinkedList<E>();
        
        c.add(e);
        while(!esRaiz(e)){
        	e = get(e).getPadre();
        	c.add(e);
        }//while

        Collections.reverse(c);
        return c;
    }
    
    /**
     * @param e un estado
     * @return true si el estado e es estado raiz del arbol, false en otro caso. 
     */
    private boolean esRaiz(E e) {
    	N info = tabla.get(e);
        return info != null && info.getPadre()==null; 
    }
    
}