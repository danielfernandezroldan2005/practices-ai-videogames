package solitario;

import agentesolitario.AgenteSolitario;

public class AgenteSolitario2Col extends AgenteSolitario<Solitario> {

    public AgenteSolitario2Col(Solitario solitario) {
        super(solitario);
    }

    @Override
    public boolean esFinal(Solitario solitario) {
        return solitario.esObjetivo();
    }
}
