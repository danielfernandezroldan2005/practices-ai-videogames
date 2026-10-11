package puzzle8;

public class AgentePuzzleManhattan extends AgentePuzzleH0 {

    public AgentePuzzleManhattan(Puzzle p) {
        super(p);
    }

    @Override
    public int h(Puzzle e) {
        int distanciaTotal = 0;
        int filas = e.tab.length;
        int columnas = e.tab[0].length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int valor = e.tab[i][j];

                // El hueco (0) no cuenta en el cálculo
                if (valor != 0) {
                    int filaMeta = valor / columnas;
                    int colMeta  = valor % columnas;

                    distanciaTotal += Math.abs(i - filaMeta) + Math.abs(j - colMeta);
                }
            }
        }

        return distanciaTotal;
    }
}