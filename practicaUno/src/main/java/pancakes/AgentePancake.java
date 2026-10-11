package pancakes;

import agentesolitario.AgenteSolitario;

public class AgentePancake extends AgenteSolitario<Pancake> {
    protected AgentePancake(Pancake p) {
        super(p);
    }

    @Override
    public boolean esFinal(Pancake e) {
        return e.esObjetivo();
    }
}
