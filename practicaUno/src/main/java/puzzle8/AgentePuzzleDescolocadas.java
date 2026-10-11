package puzzle8;

public class AgentePuzzleDescolocadas extends AgentePuzzleH0 {
    public AgentePuzzleDescolocadas(Puzzle p) {
        super(p);
    }

    @Override
    public int h(Puzzle e) {
        // Calculamos número de piezas descolocadas.
        int num_descolocadas = 0;
        int cont = 0;

        for(int i = 0; i < e.tab.length; i++) {
            for(int j = 0; j < e.tab[0].length; j++) {
                int actual = e.tab[i][j];

                if(actual != cont && actual != 0) {
                    num_descolocadas++;
                } // if si está descolocada

                cont++;
            } // for columnas
        } // for filas

        return num_descolocadas;
    }
}
