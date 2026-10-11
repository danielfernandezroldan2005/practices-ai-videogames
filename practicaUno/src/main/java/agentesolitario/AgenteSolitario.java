package agentesolitario;

import java.util.LinkedList;
import java.util.List;

import mundosolitario.OverrideHashCode;
import mundosolitario.RepresentacionEstadoOptimizacion;


/**
 * Agente para resolver problemas definidos sobre el espacio de estados implícito en la clase E.
 * 
 * Para crear un problema hay que proporcionar el estado de salida, y un método que compruebe la
 * condición objetivo (esFinal).
 * 
 * La solución corresponderá a una secuencia válida de estados en el espacio de estados
 * definido por E, desde el estado de salida hasta otro final (que cumpla la condición
 * objetivo).
 * 
 * @author Lorenzo Mandow (2018), Jose Miguel Horcas Aguilera (2009)
 * @version 2018-10-02
 */
public abstract class AgenteSolitario<E extends OverrideHashCode & RepresentacionEstadoOptimizacion<E>> { 
	public E salida;
    
	/**
	 * Guarda el estado de salida.
	 * @param e estado de salida
	 */
    protected AgenteSolitario(E e){
    	this.salida = e;
    }
    
    /**
     * Comprueba la condición objetivo para el estado e.
     * @return true si el argumento es un estado objetivo, false en otro caso.
     */
    public abstract boolean esFinal(E e);
    
   /**
     * Función heurística cuyo valor por defecto corresponde a una búsqueda a ciegas.
     *
     * @param  e   un estado
     * @return     0
     */
    public int h(E e)
    {
        return 0;
    }

       
    /*******************************************************************************************
     * Búsqueda a ciegas primero en amplitud.
     * 
     * @return Lista de estados del camino solución, o null si no se encontró solución.
     */
       
    public LinkedList<E> amplitud (){
        E e = this.salida;
        ArbolGenerico<E, NodoBasico<E>> a = new ArbolGenerico<>();
        a.put(e, new NodoBasico<E>(null));
    
        LinkedList<E> abiertos = new LinkedList<E>();
        
               
        while (e!=null && !this.esFinal(e)) {
            for (E e2 : (List<E>)e.calculaSucesores()){//lista) {//Ettado.hijos()) {
                if (!a.contains(e2)) {
                    abiertos.offer(e2);
                    a.put(e2, new NodoBasico<E>(e));
                }//if
            }//for
            e = abiertos.poll();
        }//while

        if (e==null) {
            return null;
        } else {
            return (LinkedList<E>)a.recuperaSolucion(e);
        }//if e
    }
    
    /**
     * Algoritmo A* con heuristico monotono (consistente).
     * 
     * @return  Lista de estados del camino solución, o null si no se encontró solución.
     */
    public List<E> aMono() {
        E e = this.salida;
        ArbolGenerico<E, NodoA<E>> arbol = new ArbolGenerico<>();   //Arbol de búsqueda
        
        arbol.put(e, new NodoA<E>(null,0));
        
        Abiertos<E> abiertos = new Abiertos<>(this.h(e), 0, e);      //"Lista" de abiertos
        boolean exito = false;  //será true si se encontró solución
        int gNuevo;
        int it = 0;    //contador de expansiones
        
        while (!abiertos.isEmpty()){ 
        	e = abiertos.poll();
        	if(this.esFinal(e)){ 
        		exito = true;
        		break;}    //Fin con éxito
        	it++;
            for (E e2 : (List<E>)e.calculaSucesores()){
                gNuevo =  arbol.get(e).getG() + e.costeArco(e2); 
                if (!arbol.contains(e2) || gNuevo < arbol.get(e2).getG()) {
                    if (arbol.contains(e2)) {
                        abiertos.remove(e2);
                    }
                    abiertos.offer(gNuevo + this.h(e2), gNuevo, e2);   
                    arbol.put(e2, new NodoA<E>(e, gNuevo));
                }//if !a
            }//for e2
        }//while
        
        if (!exito) {
            return null;
        } else {
            List<E> solucion = arbol.recuperaSolucion(e);
            System.out.println("Nodos expandidos: " + it);
            System.out.println("Longitud de la solucion: " + solucion.toArray().length);
            System.out.println("Coste de la solucion: " + arbol.get(e).getG());
            return solucion;
        }//if !exito
    }

    
    /**
     * Búsqueda con retroceso con profundidad acotada.
     * 
     * @param cota Profundidad maxima, tomando como 0 la profundidad de la raíz.
     * @return Lista de estados del camino solución, o null si no se encontró solución.
     */
    public List<E> backtrackC(int cota) {
        return backtrackC(this.salida, cota, 0);
    }

    /**
     * @param e     Estado.
     * @param cota  Profundidad máxima.
     * @param prof  Profundidad actual.
     */
    private List<E> backtrackC(E e, int cota, int prof) {
        List<E> res = new LinkedList<E>();
        
        if (this.esFinal(e)) {
            res.add(0, e);
            return res;
        } else if (prof >= cota) {
            return null;                                // poda
        } else {
            for (E e2 : (List<E>)e.calculaSucesores()){ 
                res = backtrackC(e2, cota, prof+1);
                if (res != null) {
                    res.add(0, e);
                    return res;
                }
            }
            return null;
        }
    }   
    
    /**
     * Algoritmo Backtrack Iterative Deepening.
     * 
     * @return      Lista de estados del camino solución.
     */
    
    public List<E> bid() {
        return bid(this.salida, 0);
    }
    
    /**
     * @param e     Estado de salida.
     * @param cota  Profundidad de la búsqueda inicial.
	 */
    
    private List<E> bid(E e, int cota) {
        List<E> res;
                
        res =  this.backtrackC(cota);
        if (res != null) {
            return res;
        } else {
            return bid(e, cota+1);
        }
    }
  
}
