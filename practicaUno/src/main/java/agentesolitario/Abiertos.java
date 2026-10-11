package agentesolitario;

import java.util.PriorityQueue;

import mundosolitario.RepresentacionEstadoOptimizacion;

/**
 * "Lista" de nodos ABIERTOS para el algoritmo A*, implementada mediante una cola con prioridad.
 * Incluye una clase interior que implementa los nodos guardados en la PriorityQueue.
 * 
 * @author Lorenzo Mandow
 * @version 2018-10-03

 */

public class Abiertos<E extends RepresentacionEstadoOptimizacion<E>> {
	PriorityQueue<NodoAB> abiertos; //"Lista" de Abiertos
    
	/**
	 * Nodos a guardar en la lista de Abiertos. Están formados por un estado n y sus valor de f(n) y g(n).
	 * 
	 * El valor numérico es una estimación heurística de coste, f(n). El "orden natural" de los objetos NodoPQ será por orden
	 * creciente de f(n). En caso de empate en el valor de f(n), se desempata por mayor valor de g(n).
	 *
	 * El estado en principio debería aparecer una única vez en la lista, por lo que el método de comparación "equals"
	 * tiene en cuenta sólo este componente del NodoPQ para las comparaciones de igualdad.
	 * 
	 * El orden definido por compareTo y equals es por tanto inconsistente desde un punto de vista formal, ya que objetos
	 * que sean equals pueden ser uno mayor que otro (o viceversa). Sin embargo, esta situación nunca se dará en una lista
	 * de nodos Abiertos bien gestionada al no generarse nunca duplicidades de estados en la misma.
	 */
	public class NodoAB implements Comparable<NodoAB>

	{
		private int f;      //prioridad (estimación de coste)
		private int g;	    //prioridad secundaria (coste real acumulado)
	    private E estado;   //estado
	    

	    public NodoAB(int f, int g, E e) {
	        this.f = f;
	        this.g = g;
	        this.estado = e;
	    }

	    public int getF()  {
	        return this.f;
	    }
	    
	    public int getG() {
	    	return this.g;
	    }
	    
	    public E getEstado() {
	        return this.estado;
	    }
	    
	    /**
	     * Orden natural por valor creciente de f. En caso de empate se considera
	     * más prioritario el de mayor valor de g.
	     */
	    @Override
	    public int compareTo(Abiertos<E>.NodoAB e) {
	    	if (this.f == e.getF()){
	    		return e.getG() - this.getG();   //mejor cuanto mayor g
	    	} else {
		    	return this.f - e.getF();	     //mejor cuanto menor f
	    	}
	    }

	    /**
	     * Método generado automáticamente con Eclipse
	     */
	    
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			NodoAB other = (NodoAB) obj;
			if (estado == null) {
				if (other.estado != null)
					return false;
			} else if (!estado.equals(other.estado))
				return false;
			return true;
		}
	}
	/////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Crea una lista de Abiertos que inicialmente contiene el estado e con valor f y g.
	 * @param f Valor de f(n)
	 * @param g Valor de g(n)
	 * @param e Estado
	 */
	public Abiertos(int f, int g, E e){
		this.abiertos = new PriorityQueue<>();
		this.abiertos.offer(new NodoAB(f, g, e));
	}
	
	/**
	 * @return true si la lista está vacía, false en otro caso.
	 */
	public boolean isEmpty(){
		return this.abiertos.peek() == null;
	}
	
	/**
	 * @return El primer estado de la lista, borrándolo de la misma. Genera un error si la lista está vacía.
	 */
	public E poll(){
		return this.abiertos.poll().getEstado();
	}
	
	/**
	 * Elimina el estado e de la lista, independientemente de su valor de f(n)
	 */
	public void remove (E e){
		//El valor de f o g correspondiente al estado e es indiferente, ya que
		//el método equals de los NodoAB está definido únicamente sobre el estado.
		this.abiertos.remove(new NodoAB(0, 0, e));
	}
	
	/**
	 * Inserta el estado e en la lista con el valor f indicado. Para una correcta
	 * gestión de la lista, no debería llamarse NUNCA a este método de modo que pueda
	 * haber estados repetidos en la misma (debe llamarse a remove previamente).
	 * @param f
	 * @param e
	 */
	public void offer(int f, int g, E e){
		abiertos.offer(new NodoAB(f, g, e));
	}
}
