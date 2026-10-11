package jarras;

import agentesolitario.AgenteSolitario;

public class AgenteJarras extends AgenteSolitario<Jarra> {
    /**
     * Guarda el estado de salida.
     *
     * @param jarra estado de salida
     */
    protected AgenteJarras(Jarra jarra) {
        super(jarra);
    }

    @Override
    public boolean esFinal(Jarra estado) {
        // ¿Qué condición debe cumplirse sobre 'estado' para considerarlo meta?
        return estado.getContenido_G() == 2;
    }
}
