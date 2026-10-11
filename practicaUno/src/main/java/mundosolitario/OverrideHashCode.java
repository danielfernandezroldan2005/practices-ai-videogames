package mundosolitario;

/**
 * Clase abstracta para recordar la redefinición del método hashCode y, en consecuencia,
 * el método equals.
 * 
 * @author Lorenzo Mandow
 *
 */
public abstract class OverrideHashCode {
	
	 /**
     * Forzamos la re-definición de hashCode y, en consecuencia, de equals.
     * Si dos objetos son equals, deben proporcionar el mismo hashCode.
     * 
     * El método hashCode es importante para métodos de resolución de
     * problemas que usen tablas hash indexadas con las representaciones de estados
     * (por ejemplo, implementaciones de A*).
     */
    public abstract int hashCode ();
    public abstract boolean equals(Object obj);

}
