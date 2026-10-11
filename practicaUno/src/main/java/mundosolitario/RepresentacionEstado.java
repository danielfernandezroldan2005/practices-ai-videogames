package mundosolitario;

import java.util.List;

/**
 * Representacion de un estado o situación del mundo
 * correspondiente a un "mundo de agente solitario" (determinista, totalmente observable,
 * conocido, y donde el agente resolutor es el único que puede realizar cambios). Por
 * ejemplo, puzle-8, trasvase entre jarras, o misioneros y caníbales.
 * 
 * @author Lorenzo Mandow
 * @version 02-10-2018
 */
public interface RepresentacionEstado<E extends RepresentacionEstado<E>> {

    /**
     * @return Lista de estados alcanzables en un paso desde este estado. 
     */
    public List<E> calculaSucesores();
    
}
