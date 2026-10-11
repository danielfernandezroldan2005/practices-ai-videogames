package jarras;

import java.util.List;

public class TestJarras {
    public static void main (String[] args) {
        Jarra estadoInicial = new Jarra(0, 0);

        AgenteJarras agente = new AgenteJarras(estadoInicial);

        List<Jarra> solucion = agente.amplitud();

        if (solucion == null || solucion.isEmpty()) {
            System.out.println("No se ha encontrado solución.");
        } else {
            System.out.println("Solución encontrada en " + (solucion.size() - 1) + " pasos:");
            for (Jarra paso : solucion) {
                System.out.println(" -> " + paso);
            }
        }
    }
}
