package mundosolitario;

/**
 * Representacion de un estado o situación del mundo
 * correspondiente a un "mundo de agente solitario" (determinista, totalmente observable,
 * conocido, y donde el agente resolutor es el único que puede realizar cambios). Por
 * ejemplo, puzle-8, trasvase entre jarras, o misioneros y caníbales.
 * 
 * Incluye el método costeArco para algoritmos de optimización.
 * 
 * @author Lorenzo Mandow
 * @version 04-10-2018
 */
public interface RepresentacionEstadoOptimizacion<E extends RepresentacionEstadoOptimizacion<E>> extends RepresentacionEstado<E> {


    /** 
     * @return coste correspondiente al arco entre el estado actual y el recibido
     * como argumento (se supone que existe tal arco).  
     */

    public int costeArco(E eDestino);
    
}
