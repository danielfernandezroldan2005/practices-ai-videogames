package pancakes;

import java.util.LinkedList;
import java.util.Scanner;

public class TestPancake {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escriba la secuencia de enteros.");
        String entrada = scanner.nextLine();
        String[] partes = entrada.trim().split("\\s+");
        int[] tab = new int[partes.length];
        for(int i = 0; i < partes.length; i++) {
            tab[i] = Integer.parseInt(partes[i]);
        }

        Pancake pancake_inicial = new Pancake(tab);
        AgentePancake agente_pancake = new AgentePancake(pancake_inicial);

        LinkedList<Pancake> solucion = agente_pancake.amplitud();

        if(solucion != null){
            System.out.println("Solución (" + solucion.size() + " movimientos):");
            for(Pancake p: solucion) {
                System.out.println(p);
            }
        }
    }
}
